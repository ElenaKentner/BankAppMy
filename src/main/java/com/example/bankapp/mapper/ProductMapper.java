package com.example.bankapp.mapper;

import com.example.bankapp.dto.ProductDTO;
import com.example.bankapp.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "name", target = "name", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "interestRate", target = "interestRate", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "status", target = "status", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "minLimit", target = "minLimit", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProduct(ProductDTO updatedProduct, @MappingTarget Product existingProduct);

    ProductDTO mapToDto(Product existingProduct);
}
