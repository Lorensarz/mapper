package com.petrov.mapper.mapper;

import com.petrov.mapper.dto.CustomerDto;
import com.petrov.mapper.entity.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomerMapperImpl implements CustomerMapper {

    private final OrderMapper orderMapper;

    @Override
    public CustomerDto toDto(CustomerEntity customerEntity) {
        CustomerDto dto = new CustomerDto();
        dto.setId(customerEntity.getId());
        dto.setEmail(customerEntity.getEmail());
        dto.setLastName(customerEntity.getLastName());
        dto.setFirstName(customerEntity.getFirstName());
        dto.setContactNumber(customerEntity.getContactNumber());

        if (customerEntity.getOrders() != null) {
            dto.setOrders(orderMapper.toDtoList(customerEntity.getOrders()));
        }

        return dto;
    }

    @Override
    public CustomerEntity toEntity(CustomerDto customerDto) {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(customerDto.getId());
        entity.setEmail(customerDto.getEmail());
        entity.setLastName(customerDto.getLastName());
        entity.setFirstName(customerDto.getFirstName());
        entity.setContactNumber(customerDto.getContactNumber());

        if (customerDto.getOrders() != null) {
            entity.setOrders(orderMapper.toEntityList(customerDto.getOrders()));
        }

        return entity;
    }

    @Override
    public List<CustomerDto> toDtoList(List<CustomerEntity> customerEntityList) {
        List<CustomerDto> dtoList = new ArrayList<>();
        for (CustomerEntity entity : customerEntityList) {
            dtoList.add(toDto(entity));
        }
        return dtoList;
    }

    @Override
    public List<CustomerEntity> toEntityList(List<CustomerDto> customerDtoList) {
        List<CustomerEntity> entityList = new ArrayList<>();
        for (CustomerDto dto : customerDtoList) {
            entityList.add(toEntity(dto));
        }
        return entityList;
    }
}
