package com.example.bankapp.mapper;

import com.example.bankapp.dto.ProductDTO;
import com.example.bankapp.entity.Product;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "name", target = "name", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "interestRate", target = "interestRate", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "status", target = "status", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "minLimit", target = "minLimit", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateProduct(ProductDTO updatedProduct, @MappingTarget Product existingProduct);

    @Mapping(source = "id", target = "id", qualifiedByName = "uuidToString")
    ProductDTO mapToDto(Product existingProduct);

    List<ProductDTO> mapToListDto(List<Product> productList);

    @Named("uuidToString")
    default String uuidToString(UUID uuid){
        return uuid.toString();
    }
}
