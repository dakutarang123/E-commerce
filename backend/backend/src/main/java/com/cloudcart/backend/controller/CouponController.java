package com.cloudcart.backend.controller;

import com.cloudcart.backend.entity.Coupon;
import com.cloudcart.backend.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coupon")
@CrossOrigin(origins = "http://localhost:3000")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @GetMapping("/{code}")
    public Coupon getCoupon(
            @PathVariable String code){

        return couponService.validateCoupon(code);

    }

}