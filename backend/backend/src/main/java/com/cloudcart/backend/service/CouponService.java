package com.cloudcart.backend.service;

import com.cloudcart.backend.entity.Coupon;
import com.cloudcart.backend.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    public Coupon validateCoupon(String code){

        return couponRepository
                .findByCode(code)
                .orElse(null);

    }

}