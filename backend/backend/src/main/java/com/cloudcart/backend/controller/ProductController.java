package com.cloudcart.backend.controller;

import com.cloudcart.backend.dto.ProductDTO;
import com.cloudcart.backend.entity.Product;
import com.cloudcart.backend.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }



    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "Product Deleted";
    }
    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @RequestBody Product product) {

        Product existingProduct =
                productService.getProduct(id);

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setImageUrl(product.getImageUrl());

        // ADD THIS
        existingProduct.setDescription(
                product.getDescription()
        );

        return productService.saveProduct(existingProduct);
    }



    @GetMapping("/page")
    public Page<Product> getProducts(
            @RequestParam int page,
            @RequestParam int size) {

        return productService.getProducts(page, size);
    }

    @GetMapping("/sort")
    public List<Product> getProductsSorted(@RequestParam String field) {
        return productService.getProductsSorted(field);
    }

    @GetMapping("/search-page")
    public Page<Product> searchProducts(
            @RequestParam String keyword,
            @RequestParam int page,
            @RequestParam int size) {

        return productService
                .searchProducts(keyword, page, size);
    }


    @PostMapping
    public Product addProduct(
            @Valid @RequestBody ProductDTO productDTO) {

        Product product = new Product();

        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setCategory(productDTO.getCategory());
        product.setImageUrl(productDTO.getImageUrl());

        // THIS LINE MUST EXIST
        product.setDescription(
                productDTO.getDescription()
        );

        return productService.saveProduct(product);
    }

    @PostMapping(
            value = "/upload",
            consumes = "multipart/form-data"
    )
    public String uploadImage(
            @RequestParam("file") MultipartFile file)
            throws IOException {

        // Absolute path where images will be stored
        String uploadPath = "C:/Users/TARANG PATEL/Downloads/backend/backend/src/upload";

        File uploadDir = new File(uploadPath);

        // Create folder if it doesn't exist
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Generate unique filename
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        // Save file
        File destination = new File(uploadDir, fileName);

        file.transferTo(destination);

        // Return image path
        return "/uploads/" + fileName;
    }
    @GetMapping("/{id}")
    public Product getProductById(
            @PathVariable Long id) {

        return productService.getProductById(id);
    }

    @GetMapping("/similar/{category}")
    public List<Product> getSimilarProducts(
            @PathVariable String category) {

        System.out.println(category);

        return productService.getSimilarProducts(category);
    }
    @GetMapping("/search/{keyword}")
    public List<Product> searchProducts(
            @PathVariable String keyword) {

        return productService.searchProducts(keyword);

    }
    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(
            @PathVariable String category) {

        return productService.getProductsByCategory(category);

    }
    @GetMapping("/featured")
    public List<Product> getFeaturedProducts() {

        return productService.getFeaturedProducts();

    }
    @GetMapping("/related/{id}")
    public List<Product> getRelatedProducts(@PathVariable Long id) {
        return productService.getRelatedProducts(id);
    }


}