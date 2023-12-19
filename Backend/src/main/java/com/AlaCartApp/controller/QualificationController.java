package com.AlaCartApp.controller;

import com.AlaCartApp.models.entity.Qualification;
import com.AlaCartApp.models.request.QualificationDto;
import com.AlaCartApp.models.response.ProductDto;
import com.AlaCartApp.service.abstraction.QualificationService;
import com.AlaCartApp.service.implementation.ProductServiceImpl;
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
@RequestMapping("/ratings")
public class QualificationController {

    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private QualificationService qualificationServiceImpl;

    @PostMapping
    public ResponseEntity<?> rateProduct(@RequestBody QualificationDto qualification) {
        if (qualification.getScore() == null || qualification.getScore() < 0 || qualification.getScore()>5) {
            return new ResponseEntity<>("Value not supported", HttpStatus.BAD_REQUEST);
        }
        QualificationDto qu = qualificationServiceImpl.rateProduct(qualification);

        return new ResponseEntity<>(qu,HttpStatus.CREATED);
    }
    
    @GetMapping("/{productId}")
    public ResponseEntity<?> getRateByProduct(@PathVariable Long productId) {
        List<QualificationDto> rates = qualificationServiceImpl.getRateByProduct(productId);
        if (rates.isEmpty()) return new ResponseEntity<>("NO RATES",HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(rates,HttpStatus.OK);
    }
    
    @GetMapping("/rate/{productId}")
    public ResponseEntity<?> getRateFromProduct(@PathVariable Long id) {
        Double rate = qualificationServiceImpl.getRateById(id);
        if (rate == null) return new ResponseEntity<>("No rate", HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(rate, HttpStatus.OK);
    }

}
