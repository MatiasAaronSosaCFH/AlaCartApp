package com.AlaCartApp.controller;

import com.AlaCartApp.models.request.SubCategoryDto;
import com.AlaCartApp.service.implementation.SubCategoryServiceImp;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sub-categories")
@RequiredArgsConstructor
public class SubCategoryController {

    private final SubCategoryServiceImp subCategoryServiceImp;

    @GetMapping
    public ResponseEntity<?> findAllAvailable(){
        return new ResponseEntity<>(subCategoryServiceImp.findAllAvailable(), HttpStatus.OK);
    }

    @GetMapping("/un-available")
    public ResponseEntity<?> findAllUnAvailable(){
        List<SubCategoryDto> categories = subCategoryServiceImp.findAllUnAvailable();
        if (categories != null) return new ResponseEntity<>(categories, HttpStatus.OK);
        return new ResponseEntity<>("There are no categories unavailable", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    private ResponseEntity<?> findAll(){
        return new ResponseEntity<>(subCategoryServiceImp.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        SubCategoryDto category = subCategoryServiceImp.findAvailableAndId(id);
        if (category != null) return new ResponseEntity<>(category, HttpStatus.OK);
        else return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name){
        SubCategoryDto category = subCategoryServiceImp.findByName(name);
        if (category != null) return new ResponseEntity<>(category, HttpStatus.OK);
        else return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createSubCategory(@RequestBody @Valid SubCategoryDto categoryDto){
        SubCategoryDto category = subCategoryServiceImp.createSubCategory(categoryDto);

        if (category != null) return new ResponseEntity<>(category, HttpStatus.CREATED);
        else return new ResponseEntity<>("bad request",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/change-name")
    public ResponseEntity<?> changeName(@RequestParam @NotBlank String name, @RequestParam @NotBlank Long id){
        SubCategoryDto category = subCategoryServiceImp.changeName(name,id);
        if (category != null) return new ResponseEntity<>(category, HttpStatus.OK);
        return new ResponseEntity<>("category not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/change-available")
    public ResponseEntity<?> changeAvailable(@RequestParam @NotBlank Boolean available, @RequestParam @NotBlank Long id){
        SubCategoryDto category;
        if (available) {
            category = subCategoryServiceImp.makeAvailable(id);
            if (category != null) return new ResponseEntity<>(category, HttpStatus.OK);
            else return new ResponseEntity<>("category not found", HttpStatus.NOT_FOUND);
        }else {
            category = subCategoryServiceImp.makeUnAvailable(id);
            if (category != null) return new ResponseEntity<>(category, HttpStatus.OK);
            else return new ResponseEntity<>("category not found", HttpStatus.NOT_FOUND);
        }
    }
}
