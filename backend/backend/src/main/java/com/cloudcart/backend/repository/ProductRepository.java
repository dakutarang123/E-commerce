package com.cloudcart.backend.repository;

import com.cloudcart.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository
        extends JpaRepository<Product, Long> {

    List<Product> findByNameContainingIgnoreCase(String name);

    Page<Product> findByNameContaining(String keyword,
                                       Pageable pageable);
    List<Product> findByCategory(String category);
    List<Product> findByCategoryIgnoreCase(String category);
    List<Product> findByFeaturedTrue();


    @Query("""
SELECT p FROM Product p
WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
OR LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%'))
OR LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))
""")
    List<Product> searchProducts(@Param("keyword") String keyword);
}