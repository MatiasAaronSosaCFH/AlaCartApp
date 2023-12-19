package com.AlaCartApp.controller;

import com.AlaCartApp.models.response.ProductDto;
import com.AlaCartApp.service.abstraction.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/products")
@RestController
public class ProductController {


    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> save(
            @RequestPart(value="files",required = false) List<MultipartFile> postImage,
            @RequestPart(value="request") ProductDto request)
            throws IOException{
        productService.save(postImage, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/find-category-list")
    public ResponseEntity<?> findByCategory(@RequestBody Long categoryId){
        if (categoryId.equals(null) || categoryId < 0) return new ResponseEntity<>("FIELD NOT SUPPORTED", HttpStatus.BAD_REQUEST);
        List<ProductDto> products = productService.findByCategory(categoryId);
        if(products.isEmpty()) return new ResponseEntity<>("Not found products", HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        if (id<0|| id.equals(null)) return new ResponseEntity<>("FIELD NOT SUPPORTED", HttpStatus.BAD_REQUEST);
        return ResponseEntity.of(productService.findById(id));
    }

    @PutMapping()
    public ResponseEntity<ProductDto> update(@RequestPart(value="files",required = false) List<MultipartFile> postImage,
                                             @RequestPart(value="request") ProductDto product) throws IOException{
       return ResponseEntity.of(productService.update(postImage,product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if (id<0|| id.equals(null)) return new ResponseEntity<>("FIELD NOT SUPPORTED", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(productService.delete(id)?HttpStatus.OK:HttpStatus.NOT_FOUND);
    }
}
