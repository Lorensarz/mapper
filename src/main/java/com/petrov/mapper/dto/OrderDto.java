package com.petrov.mapper.dto;

import com.petrov.mapper.entity.CustomerEntity;
import com.petrov.mapper.entity.enums.Status;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;

    private List<ProductDto> products;

    @Nullable
    private BigDecimal totalPrice;

    private LocalDateTime orderDate;

    private CustomerEntity customerEntity;

    private String shippingAddres;

    private Status orderStatus;
}
