package com.AlaCartApp.models.mapper;

import com.AlaCartApp.models.entity.Product;
import com.AlaCartApp.models.response.ProductDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;
@Mapper(componentModel = "spring")


public interface ProductMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "category", target = "category")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "images", target = "images")
    @Mapping(source = "subCategory", target = "subCategory")
    @Mapping(source = "qualifications", target = "qualifications")

    ProductDto toProductDTO(Product product);
    List<ProductDto> toProductsDTO(List<Product> products);
    @InheritInverseConfiguration
    Product toProduct(ProductDto productDto);

}
