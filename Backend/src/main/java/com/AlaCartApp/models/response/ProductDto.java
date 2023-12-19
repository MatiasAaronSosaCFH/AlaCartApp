package com.AlaCartApp.models.response;

import com.AlaCartApp.models.entity.Category;
import com.AlaCartApp.models.entity.Image;
import com.AlaCartApp.models.entity.SubCategory;
import com.AlaCartApp.models.request.CategoryDto;
import com.AlaCartApp.models.request.QualificationDto;
import com.AlaCartApp.models.request.SubCategoryDto;
import lombok.Data;
import java.util.List;


@Data
public class ProductDto {

    Long id;
    String name;
    Double price;
    List<QualificationDto> qualifications;
    CategoryDto category;
    String description;
    List<Image> images;
    Boolean state;
    SubCategoryDto subCategory;

}
