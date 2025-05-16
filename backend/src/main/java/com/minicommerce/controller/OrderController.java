package com.minicommerce.controller;

import com.minicommerce.dto.OrderRequestDTO;
import com.minicommerce.model.Order;
import com.minicommerce.model.OrderItem;
import com.minicommerce.model.Product;
import com.minicommerce.repository.OrderRepository;
import com.minicommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderRepository orderRepo;

    @PostMapping
    public Order create(@RequestBody Order order) {
        order.setCreatedAt(LocalDateTime.now());
        return orderRepo.save(order);
    }

    @GetMapping
    public List<OrderResponse> list() {
        return orderRepo.findAll().stream().map(OrderResponse::from).toList();
    }

    // DTO interno
    record OrderResponse(Long id, String date, List<ProductInfo> products) {
        static OrderResponse from(Order order) {
            return new OrderResponse(
                    order.getId(),
                    order.getCreatedAt().toLocalDate().toString(),
                    order.getProducts().stream().map(p -> new ProductInfo(p.getName(), p.getPrice())).toList()
            );
        }
    }

    record ProductInfo(String name, double price) {}
}
