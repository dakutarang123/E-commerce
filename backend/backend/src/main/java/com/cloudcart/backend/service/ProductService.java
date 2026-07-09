package com.cloudcart.backend.service;

import com.cloudcart.backend.entity.Product;
import com.cloudcart.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudcart.backend.exception.ResourceNotFoundException;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }



    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public Product getProduct(Long id){

        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id " + id));
    }
    public Page<Product> getProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }
    public List<Product> getProductsSorted(String field) {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }
    public Page<Product> searchProducts(
            String keyword,
            int page,
            int size) {

        Pageable pageable = PageRequest.of(page, size);

        return productRepository
                .findByNameContaining(keyword, pageable);
    }
    public Product getProductById(Long id) {

        return productRepository
                .findById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Product Not Found"
                        )
                );
    }
    public List<Product> getSimilarProducts(String category) {

        return productRepository.findByCategory(category);

    }
    public List<Product> searchProducts(String keyword) {

        return productRepository.searchProducts(keyword);

    }
    public List<Product> getProductsByCategory(String category) {

        return productRepository.findByCategoryIgnoreCase(category);

    }
    public List<Product> getFeaturedProducts() {

        return productRepository.findByFeaturedTrue();

    }
    public List<Product> getRelatedProducts(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product Not Found"));

        return productRepository.findByCategory(product.getCategory())
                .stream()
                .filter(p -> !p.getId().equals(id))
                .limit(8)
                .toList();
    }
}