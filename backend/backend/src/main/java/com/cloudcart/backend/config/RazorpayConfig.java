package com.cloudcart.backend.config;

import com.razorpay.RazorpayClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorpayConfig {

    @Bean
    public RazorpayClient razorpayClient() throws Exception {

        return new RazorpayClient(
                "rzp_test_T8tLjNLBPY7fBk",
                "FiXLvz1aa16mUfHQxr9zudme"
        );
    }
}