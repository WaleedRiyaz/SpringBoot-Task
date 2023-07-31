package com.example.springbootproject.task.repository;

import com.example.springbootproject.task.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {}
