package com.petrov.mapper.mapper;

import com.petrov.mapper.dto.OrderDto;
import com.petrov.mapper.entity.OrderEntity;

import java.util.List;

public interface OrderMapper {
    OrderDto toDto(OrderEntity orderEntity);

    OrderEntity toEntity(OrderDto dto);

    List<OrderDto> toDtoList(List<OrderEntity> orderEntityList);

    List<OrderEntity> toEntityList(List<OrderDto> orderDtoList);
}
