package com.AlaCartApp.controller;

import com.AlaCartApp.models.entity.Qualification;
import com.AlaCartApp.service.abstraction.QualificationService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author WilderVlz
 */
@RestController
@RequestMapping("/rating")
public class QualificationController {

    @Autowired
    private QualificationService qualificationServiceImpl;

    @PostMapping("/rate")
    public ResponseEntity<?> rateProduct(@RequestBody Qualification qualification) {
        try {
            List<Integer> ratings = this.qualificationServiceImpl.rateProduct(qualification);
            return new ResponseEntity<>(ratings, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid score. Score must be between 1 and 5.", HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Product not found with specified ID.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while processing the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/product-id/{productId}")
    public ResponseEntity<?> getRateById(@PathVariable Long productId) {
        try {
            List<Integer> ratings = this.qualificationServiceImpl.getRateById(productId);
            return new ResponseEntity<>(ratings, HttpStatus.OK);
        } catch(EntityNotFoundException e) {
            return new ResponseEntity<>("Product not found with specified ID.", HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/product-name/{productName}")
    public ResponseEntity<?> getRateByName(@PathVariable String productName) {
        try{
            List<Integer> ratings = this.qualificationServiceImpl.getRateByName(productName);
            return new ResponseEntity<>(ratings, HttpStatus.OK);
        } catch(EntityNotFoundException e) {
            return new ResponseEntity<>("Product not found with specified name.", HttpStatus.NOT_FOUND);
        }
    }

}
