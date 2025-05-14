package com.minicommerce.controller;

import com.minicommerce.dto.OrderRequestDTO;
import com.minicommerce.model.Order;
import com.minicommerce.model.OrderItem;
import com.minicommerce.model.Product;
import com.minicommerce.repository.OrderRepository;
import com.minicommerce.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderRepository orderRepo;
    private final ProductRepository productRepo;

    public OrderController(OrderRepository orderRepo, ProductRepository productRepo) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }

    @GetMapping
    public List<Order> findAll() {
        return orderRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        return orderRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderRequestDTO dto) {
        Order order = new Order();
        order.setCustomerName(dto.customerName);
        order.setCustomerEmail(dto.customerEmail);

        List<OrderItem> items = dto.items.stream().map(itemDto -> {
            Optional<Product> productOpt = productRepo.findById(itemDto.productId);
            if (productOpt.isEmpty()) {
                throw new RuntimeException("Produto não encontrado: ID " + itemDto.productId);
            }

            Product product = productOpt.get();

            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(itemDto.quantity);
            item.setPrice(product.getPrice());
            item.setOrder(order); // associa ao pedido

            return item;
        }).collect(Collectors.toList());

        double total = items.stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum();
        order.setTotal(total);
        order.setItems(items);

        Order saved = orderRepo.save(order);
        return ResponseEntity.ok(saved);
    }
}
