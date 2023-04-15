package com.rasmusclausen.storeproject.repository;

import com.rasmusclausen.storeproject.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByPlatformContainingAndNameContaining(String platform, String name, Pageable pageable);
    Page<Product> findByPlatformContaining(String platform, Pageable pageable);
    Page<Product> findByNameContaining(String name, Pageable pageable);


    @Query("SELECT COUNT(p) FROM Product p WHERE UPPER(p.platform) = UPPER(:platform)")
    Long getCountByPlatform(@Param("platform") String platform);

    Page<Product> findByPlatformContainingIgnoreCaseAndNameContainingIgnoreCase(String platform, String name, Pageable pageable);

    Page<Product> findByPlatformContainingIgnoreCase(String platform, Pageable pageable);

    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
