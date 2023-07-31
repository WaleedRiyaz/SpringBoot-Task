package com.example.springbootproject.task.repository;

import com.example.springbootproject.task.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {}
