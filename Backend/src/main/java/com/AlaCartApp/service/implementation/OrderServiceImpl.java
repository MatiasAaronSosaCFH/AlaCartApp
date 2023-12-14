package com.AlaCartApp.service.implementation;

import com.AlaCartApp.enums.State;
import com.AlaCartApp.exception.ResourceNotFoundException;
import com.AlaCartApp.models.entity.Order;
import com.AlaCartApp.models.entity.OrderDetail;
import com.AlaCartApp.models.entity.Product;
import com.AlaCartApp.models.mapper.OrderMapper;
import com.AlaCartApp.models.request.OrderDetailDto;
import com.AlaCartApp.models.request.OrderDto;
import com.AlaCartApp.repository.OrderDetailRepository;
import com.AlaCartApp.repository.OrderRepository;
import com.AlaCartApp.repository.ProductRepository;
import com.AlaCartApp.repository.TableRepository;
import com.AlaCartApp.service.abstraction.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<OrderDto> findAll() {
        return orderMapper.toOrdersDTO(orderRepository.findAll());
    }

    @Override
    public OrderDto create(OrderDto orderDto) {
        orderDto.setDate(LocalDateTime.now());
        List<OrderDetail> listOrderDetail = new ArrayList<>();
        listOrderDetail = orderDto.getDetail();
        Double total = 0D;
        for(OrderDetail orderDetail: listOrderDetail){
            Double price = productRepository.findById(orderDetail.getProduct().getId()).get().getPrice();
            orderDetail.setPrice(price);
            total += orderDetail.getPrice()*orderDetail.getQuantity();
        }
        orderDto.setTotal(total);
        orderDto.setDetail(null);
        orderDto.setState(State.INIT);
        Order order = orderRepository.save(orderMapper.toOrder(orderDto));
        listOrderDetail.forEach(orderDetail -> orderDetail.setOrder(order));
        listOrderDetail.forEach(orderDetail -> orderDetailRepository.save(orderDetail));
        return orderMapper.toOrderDTO(orderRepository.findById(order.getId()).get());
    }

    @Override
    public OrderDto update(Long id, OrderDto orderDto) {
        Optional<Order> orderSaved = orderRepository.findById(id);
        Order order = orderMapper.toOrder(orderDto);
        if(orderSaved.isPresent()){
            Order updatedOrder = orderSaved.get();
            updatedOrder.setState(order.getState());
            updatedOrder.setTotal(order.getTotal());
            updatedOrder.setPaymentMethod(order.getPaymentMethod());
            updatedOrder.setTableEntity(order.getTableEntity());
            if(updatedOrder.getDetail() != order.getDetail()){
                updateDetails(updatedOrder.getDetail(), order.getDetail());
            }
            return orderMapper.toOrderDTO(orderRepository.save(updatedOrder));
        }else{
            throw new ResourceNotFoundException("Order not found with id: " + id);
        }
    }

    private void updateDetails(List<OrderDetail> newDetails, List<OrderDetail> oldDetails) {
        oldDetails.forEach(detail -> {
            Long productId = detail.getProduct().getId();
            boolean flag = false;
            for(OrderDetail newDetail: newDetails){
                if(newDetail.getProduct().getId() == productId){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                orderDetailRepository.deleteById(detail.getId());
            }
        });
        newDetails.forEach(newDetail -> {
            Long productId = newDetail.getProduct().getId();
            boolean flag = false;
            for(OrderDetail detail: oldDetails){
                if(detail.getProduct().getId() == productId){
                    flag = true;
                    if (detail.getQuantity() != newDetail.getQuantity()) {
                        newDetail.setId(detail.getId());
                        orderDetailRepository.save(newDetail);
                    }
                    break;
                }
            }
            if(!flag){
                orderDetailRepository.save(newDetail);
            }
        });
    }

    @Override
    public void delete(Long id) {
        Optional<Order> orderSaved = orderRepository.findById(id);
        if(orderSaved.isPresent()){
            orderSaved.get().setState(State.REJECTED);
            orderRepository.save(orderSaved.get());
        }else{
            throw new ResourceNotFoundException("Order not found with id: " + id);
        }
    }

    @Override
    public OrderDto find(Long id) {
        Optional<Order> orderSaved = orderRepository.findById(id);
        if(orderSaved.isPresent()){
            return orderMapper.toOrderDTO(orderSaved.get());
        }else{
            throw new ResourceNotFoundException("Order not found with id: " + id);
        }
    }
}
