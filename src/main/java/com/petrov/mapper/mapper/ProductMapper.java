package com.petrov.mapper.mapper;

import com.petrov.mapper.dto.ProductDto;
import com.petrov.mapper.entity.ProductEntity;

import java.util.List;

public interface ProductMapper {
    ProductDto toDto(ProductEntity productEntity);

    ProductEntity toEntity(ProductDto dto);

    List<ProductDto> toDtoList(List<ProductEntity> productEntityList);

    List<ProductEntity> toEntityList(List<ProductDto> productDtoList);
}
