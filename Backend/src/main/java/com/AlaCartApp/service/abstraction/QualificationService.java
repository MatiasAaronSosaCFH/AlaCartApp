package com.AlaCartApp.service.abstraction;

import com.AlaCartApp.models.entity.Qualification;
import com.AlaCartApp.models.request.QualificationDto;

import java.util.List;

/**
 *
 * @author WilderVlz
 */
public interface QualificationService {

    QualificationDto rateProduct(QualificationDto qualification);
    Double getRateById(Long productId);
    List<QualificationDto> getRateByProduct(Long id);

}
