package com.example.springbootproject.task.controller;

import com.example.springbootproject.task.entity.Category;
import com.example.springbootproject.task.entity.Product;
import com.example.springbootproject.task.entity.Supplier;
import com.example.springbootproject.task.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getProduct(){
        return productRepository.findAll();
    }

    record newProductRequest(String name, String description, Double price, Integer quantityInStock){}

    @PostMapping
    public void addProduct(@RequestBody newProductRequest request) {
        Product product = new Product();
        product.setName(request.name);
        product.setDescription(request.description);
        product.setPrice(request.price);
        product.setQuantityInStock(request.quantityInStock);
        productRepository.save(product);
    }

    @DeleteMapping("{productId}")
    public void delProduct(@PathVariable("productId") Integer id){
        productRepository.deleteById(id);
    }

    @PutMapping("{productId}")
    public void updateProduct(@PathVariable("productId") Integer id, @RequestBody newProductRequest request) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            if (request.name != null) {
                existingProduct.setName(request.name);
            }
            if (request.description != null) {
                existingProduct.setDescription(request.description);
            }
            if (request.price != null) {
                existingProduct.setPrice(request.price);
            }
            if (request.quantityInStock != null){
                existingProduct.setQuantityInStock(request.quantityInStock);
            }
            productRepository.save(existingProduct);
        } else {
            throw new IllegalArgumentException("Invalid product ID: " + id);
        }
    }

    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable Integer categoryId) {
        Category category = new Category();
        category.setId(categoryId);
        return productRepository.findByCategory(category);
    }

    @GetMapping("/supplier/{supplierId}")
    public List<Product> getProductsBySupplier(@PathVariable Integer supplierId) {
        Supplier supplier = new Supplier();
        supplier.setId(supplierId);
        return productRepository.findBySupplier(supplier);
    }

    @GetMapping("/lowstock/{quantity}")
    public List<Product> getProductsLowStock(@PathVariable int quantity) {
        return productRepository.findByQuantityInStockLessThan(quantity);
    }

}
