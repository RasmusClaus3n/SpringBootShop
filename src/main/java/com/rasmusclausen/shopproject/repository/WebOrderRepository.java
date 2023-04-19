package com.rasmusclausen.shopproject.repository;

import com.rasmusclausen.shopproject.entity.WebOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebOrderRepository extends JpaRepository<WebOrder, Long> {
}
