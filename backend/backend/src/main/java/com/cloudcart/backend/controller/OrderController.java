package com.cloudcart.backend.controller;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;


import com.cloudcart.backend.entity.Order;
import com.cloudcart.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order placeOrder(
            @RequestBody Order order) {

        return orderService.saveOrder(order);

    }

    @GetMapping("/{email}")
    public List<Order> getOrders(
            @PathVariable String email) {

        return orderService.getOrders(email);

    }

    @GetMapping("/invoice/{id}")
    public ResponseEntity<InputStreamResource>
    downloadInvoice(
            @PathVariable Long id) {

        ByteArrayInputStream invoice =
                orderService.getInvoice(id);

        HttpHeaders headers =
                new HttpHeaders();

        headers.add(
                "Content-Disposition",
                "attachment; filename=invoice.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(invoice));

    }

}