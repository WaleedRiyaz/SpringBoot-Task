package com.example.springbootproject.task.controller;

import com.example.springbootproject.task.entity.Product;
import com.example.springbootproject.task.entity.Supplier;
import com.example.springbootproject.task.repository.SupplierRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/supplier")
public class SupplierController {
    private final SupplierRepository supplierRepository;

    public SupplierController(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @GetMapping
    public List<Supplier> getSupplier(){
        return supplierRepository.findAll();
    }

    record newSupplierRequest(String name, Long contactNumber, String email){}

    @PostMapping
    public void addSupplier(@RequestBody newSupplierRequest request) {
        Supplier supplier = new Supplier();
        supplier.setName(request.name);
        supplier.setContactNumber(request.contactNumber);
        supplier.setEmail(request.email);
        supplierRepository.save(supplier);
    }

    @DeleteMapping("{supplierId}")
    public void delSupplier(@PathVariable("supplierId") Integer id){
        supplierRepository.deleteById(id);
    }

    @PutMapping("{supplierId}")
    public void updateProduct(@PathVariable("supplierId") Integer id, @RequestBody newSupplierRequest request) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);

        if (optionalSupplier.isPresent()) {
            Supplier existingSupplier = optionalSupplier.get();
            if (request.name != null) {
                existingSupplier.setName(request.name);
            }
            if (request.contactNumber != null) {
                existingSupplier.setContactNumber(request.contactNumber);
            }
            if (request.email != null) {
                existingSupplier.setEmail(request.email);
            }
            supplierRepository.save(existingSupplier);
        } else {
            throw new IllegalArgumentException("Invalid supplier ID: " + id);
        }
    }
}
