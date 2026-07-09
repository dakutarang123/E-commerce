package com.cloudcart.backend.entity;

import jakarta.persistence.*;

@Entity
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String type;     // PERCENT or FLAT

    private double value;

    private boolean active;

    public Coupon() {
    }

    public Coupon(Long id, String code, String type,
                  double value, boolean active) {
        this.id = id;
        this.code = code;
        this.type = type;
        this.value = value;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}