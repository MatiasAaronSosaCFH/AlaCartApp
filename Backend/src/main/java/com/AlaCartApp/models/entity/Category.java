package com.AlaCartApp.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "available")
    private Boolean available;
    //@OneToMany
    //@JoinColumn(name = "sub_category_id", referencedColumnName = "id")
    //List<SubCategory> subCategoryList;
}
