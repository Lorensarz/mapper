package com.petrov.mapper.mapper;

import com.petrov.mapper.dto.ProductDto;
import com.petrov.mapper.entity.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDto toDto(ProductEntity productEntity) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(productEntity.getProductId());
        productDto.setName(productEntity.getName());
        productDto.setDescription(productEntity.getDescription());
        productDto.setPrice(productEntity.getPrice());
        productDto.setQuantityInStock(productEntity.getQuantityInStock());
        return productDto;
    }

    @Override
    public ProductEntity toEntity(ProductDto productDto) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductId(productDto.getProductId());
        productEntity.setName(productDto.getName());
        productEntity.setDescription(productDto.getDescription());
        productEntity.setPrice(productDto.getPrice());
        productEntity.setQuantityInStock(productDto.getQuantityInStock());
        return productEntity;
    }

    @Override
    public List<ProductDto> toDtoList(List<ProductEntity> productEntityList) {
        List<ProductDto> productDtoList = new ArrayList<>();
        for (ProductEntity productEntity : productEntityList) {
            productDtoList.add(toDto(productEntity));
        }
        return productDtoList;
    }

    @Override
    public List<ProductEntity> toEntityList(List<ProductDto> productDtoList) {
        List<ProductEntity> productEntityList = new ArrayList<>();
        for (ProductDto productDto : productDtoList) {
            productEntityList.add(toEntity(productDto));
        }
        return productEntityList;
    }
}
