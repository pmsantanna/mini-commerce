package com.minicommerce.dto;

import java.util.List;

public class OrderRequestDTO {

    public String customerName;
    public String customerEmail;
    public List<ItemDTO> items;

    public static class ItemDTO {
        public Long productId;
        public int quantity;
    }
}
