package com.petrov.mapper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderCreationRequestDto {

    private Long customerId;

    private OrderDto orderDto;

}
