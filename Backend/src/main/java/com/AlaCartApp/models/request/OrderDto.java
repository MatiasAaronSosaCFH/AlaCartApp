package com.AlaCartApp.models.request;

import com.AlaCartApp.enums.State;

import com.AlaCartApp.models.entity.OrderDetail;
import com.AlaCartApp.models.entity.TableEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {

    private Long id;
    private TableEntity tableEntity;
    private LocalDateTime date;
    private List<OrderDetail> detail;
    private String paymentMethod;
    private Double total;
    private State state;





}
