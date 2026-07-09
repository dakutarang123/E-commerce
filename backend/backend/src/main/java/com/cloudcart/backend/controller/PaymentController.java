package com.cloudcart.backend.controller;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {

    @Autowired
    private RazorpayClient razorpayClient;

    @PostMapping("/create")
    public String createOrder(@RequestParam Double amount)
            throws Exception {

        JSONObject options = new JSONObject();

        options.put("amount", amount * 100);

        options.put("currency", "INR");

        options.put("receipt", "order_rcptid_11");

        Order order = razorpayClient.orders.create(options);

        return order.toString();
    }
}