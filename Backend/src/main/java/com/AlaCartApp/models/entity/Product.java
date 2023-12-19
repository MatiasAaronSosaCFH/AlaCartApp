package com.AlaCartApp.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nombre", nullable = false)
    private String name;
    @Column(name = "precio", nullable = false)
    private Double price;
    @ManyToOne()
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
    @ManyToOne()
    @JoinColumn(name = "sub_category_id", referencedColumnName = "id")
    private SubCategory subCategory;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "estado" , nullable = false)
    private Boolean state;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name="product_id")
    private List<Image> images;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    List<Qualification> qualifications;
}
