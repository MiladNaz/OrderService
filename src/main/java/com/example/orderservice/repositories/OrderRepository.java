package com.example.orderservice.repositories;

import com.example.orderservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Customer, Long> {
}
