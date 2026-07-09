package com.cloudcart.backend.service;

import com.cloudcart.backend.entity.Order;
import com.cloudcart.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    public Order saveOrder(Order order) {

        Order savedOrder =
                orderRepository.save(order);

        cartService.clearCart(
                order.getUserEmail());

        try {

            emailService.sendOrderConfirmation(
                    savedOrder);

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return savedOrder;

    }

    public List<Order> getOrders(String email) {

        return orderRepository.findByUserEmail(email);

    }

    public ByteArrayInputStream getInvoice(Long id) {

        Order order =
                orderRepository.findById(id)
                        .orElseThrow();

        return invoiceService.generateInvoice(order);

    }

}