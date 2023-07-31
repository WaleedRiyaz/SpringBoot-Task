package com.example.springbootproject.task.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Supplier {
    @Id
    @SequenceGenerator(
            name = "supplier_id_sequence",
            sequenceName = "supplier_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "supplier_id_sequence"
    )

    private Integer id;
    private String name;
    private Long contactNumber;
    private String email;

    //many products:
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<Product> productList = new ArrayList<>();
}
