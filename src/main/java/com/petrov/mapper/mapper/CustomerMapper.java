package com.petrov.mapper.mapper;

import com.petrov.mapper.dto.CustomerDto;
import com.petrov.mapper.entity.CustomerEntity;

import java.util.List;

public interface CustomerMapper {

    CustomerDto toDto(CustomerEntity customerEntity);

    CustomerEntity toEntity(CustomerDto dto);

    List<CustomerDto> toDtoList(List<CustomerEntity> users);

    List<CustomerEntity> toEntityList(List<CustomerDto> users);


}
