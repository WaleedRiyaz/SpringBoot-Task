package com.example.springbootproject.task.controller;

import com.example.springbootproject.task.entity.Category;
import com.example.springbootproject.task.entity.Product;
import com.example.springbootproject.task.repository.CategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public List<Category> getCategory(){
        return categoryRepository.findAll();
    }

    record newCategoryRequest(String name, String description){}

    @PostMapping
    public void addCategory(@RequestBody newCategoryRequest request) {
        Category category = new Category();
        category.setName(request.name);
        category.setDescription(request.description);
        categoryRepository.save(category);
    }

    @DeleteMapping("{categoryId}")
    public void delCategory(@PathVariable("categoryId") Integer id){
        categoryRepository.deleteById(id);
    }

    @PutMapping("{categoryId}")
    public void updateCategory(@PathVariable("categoryId") Integer id, @RequestBody newCategoryRequest request) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            if (request.name != null) {
                existingCategory.setName(request.name);
            }
            if (request.description != null) {
                existingCategory.setDescription(request.description);
            }
            categoryRepository.save(existingCategory);
        } else {
            throw new IllegalArgumentException("Invalid category ID: " + id);
        }
    }

}



