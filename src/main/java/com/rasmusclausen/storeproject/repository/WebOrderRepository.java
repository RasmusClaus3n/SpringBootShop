package com.rasmusclausen.storeproject.repository;

import com.rasmusclausen.storeproject.entity.WebOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebOrderRepository extends JpaRepository<WebOrder, Long> {
}
