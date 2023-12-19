package com.AlaCartApp.models.request;

import com.AlaCartApp.models.entity.Product;
import com.AlaCartApp.models.response.ProductDto;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class QualificationDto {

    private Long id;
    private Integer score;
    private ProductDto product;

}
