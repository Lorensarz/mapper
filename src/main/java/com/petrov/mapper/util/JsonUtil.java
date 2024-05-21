package com.petrov.mapper.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petrov.mapper.dto.CustomerDto;
import com.petrov.mapper.dto.OrderCreationRequestDto;
import com.petrov.mapper.dto.OrderDto;
import com.petrov.mapper.dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class JsonUtil {

    private final ObjectMapper objectMapper;

    public JsonUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting to JSON", e);
        }
    }

    public CustomerDto toCustomerDto(String json) {
        try {
            return objectMapper.readValue(json, CustomerDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting from JSON", e);
        }
    }

    public OrderCreationRequestDto toOrderCreationRequestDto(String json) {
        try {
            return objectMapper.readValue(json, OrderCreationRequestDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting from JSON", e);
        }
    }

    public OrderDto toOrderDto(String json) {
        try {
            return objectMapper.readValue(json, OrderDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting from JSON", e);
        }
    }

    public ProductDto toProductDto(String json) {
        try {
            return objectMapper.readValue(json, ProductDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting from JSON", e);
        }
    }
}
