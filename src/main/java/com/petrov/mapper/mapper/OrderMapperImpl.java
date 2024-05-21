package com.petrov.mapper.mapper;

import com.petrov.mapper.dto.OrderDto;
import com.petrov.mapper.entity.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class OrderMapperImpl implements OrderMapper {

    private final ProductMapper productMapper;

    @Override
    public OrderDto toDto(OrderEntity orderEntity) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(orderEntity.getId());
        orderDto.setCustomerEntity(orderEntity.getCustomerEntity());
        orderDto.setProducts(productMapper.toDtoList(orderEntity.getProducts()));
        orderDto.setOrderDate(orderEntity.getOrderDate());
        orderDto.setShippingAddres(orderEntity.getShippingAddres());
        orderDto.setTotalPrice(orderEntity.getTotalPrice());
        orderDto.setOrderStatus(orderEntity.getOrderStatus());
        return orderDto;
    }

    @Override
    public OrderEntity toEntity(OrderDto orderDto) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderDto.getId());
        orderEntity.setOrderDate(orderDto.getOrderDate());
        orderEntity.setCustomerEntity(orderDto.getCustomerEntity());
        orderEntity.setShippingAddres(orderDto.getShippingAddres());
        orderEntity.setTotalPrice(orderDto.getTotalPrice());
        orderEntity.setOrderStatus(orderDto.getOrderStatus());
        orderEntity.setProducts(productMapper.toEntityList(orderDto.getProducts()));
        return orderEntity;
    }

    @Override
    public List<OrderDto> toDtoList(List<OrderEntity> orderEntityList) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntityList) {
            orderDtoList.add(toDto(orderEntity));
        }
        return orderDtoList;
    }

    @Override
    public List<OrderEntity> toEntityList(List<OrderDto> orderDtoList) {
        List<OrderEntity> orderEntityList = new ArrayList<>();
        for (OrderDto orderDto : orderDtoList) {
            orderEntityList.add(toEntity(orderDto));
        }
        return orderEntityList;
    }
}
