package com.example.springbootproject.task.repository;

import com.example.springbootproject.task.entity.Category;
import com.example.springbootproject.task.entity.Product;
import com.example.springbootproject.task.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategory(Category category);
    List<Product> findBySupplier(Supplier supplier);
    List<Product> findByQuantityInStockLessThan(int quantity);
}
