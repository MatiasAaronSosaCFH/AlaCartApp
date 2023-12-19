package com.AlaCartApp.service.implementation;

import com.AlaCartApp.models.entity.Product;
import com.AlaCartApp.models.entity.Qualification;
import com.AlaCartApp.models.mapper.QualificationMapper;
import com.AlaCartApp.models.request.QualificationDto;
import com.AlaCartApp.repository.ProductRepository;
import com.AlaCartApp.repository.QualificationRepository;
import com.AlaCartApp.service.abstraction.QualificationService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author WilderVlz
 */
@Service
@Transactional
public class QualificationServiceImpl implements QualificationService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private QualificationMapper qualificationMapper;
    @Autowired
    private QualificationRepository qualificationRepository;

    @Override
    public QualificationDto rateProduct(QualificationDto qualification) {
        return qualificationMapper.toQualificationDTO(qualificationRepository.save(qualificationMapper.toQualification(qualification)));
    }


    @Override
    public Double getRateById(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) return  null;
        List<Qualification> qualifications = product.getQualifications();

        Double promedio = 0.0;
        Double finalPromedio = promedio;
        if (qualifications.isEmpty()) return null;
        qualifications.stream().map(q -> finalPromedio + q.getScore());
        System.out.println(promedio);
        promedio = finalPromedio / qualifications.size();
        return promedio;
    }

    @Override
    public List<QualificationDto> getRateByProduct(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) return null;
        List<QualificationDto> ratings = qualificationMapper.toQualificationsDTO(product.getQualifications());
        return ratings;
    }

}
