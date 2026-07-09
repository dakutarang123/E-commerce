package com.cloudcart.backend.dto;

public class WishlistResponse {

    private Long id;
    private Long productId;

    private String name;
    private String category;
    private Double price;
    private String imageUrl;

    public WishlistResponse() {
    }

    public WishlistResponse(Long id, Long productId,
                            String name,
                            String category,
                            Double price,
                            String imageUrl) {

        this.id = id;
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}