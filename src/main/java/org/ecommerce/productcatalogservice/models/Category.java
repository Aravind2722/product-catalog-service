package org.ecommerce.productcatalogservice.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Getter
@Setter
public class Category extends BaseModel {
    @Column(nullable = false, unique = true)
    private String name;

    private String description;
    private String image;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @Fetch(FetchMode.JOIN) // However, JPA ignores fetch mode
    private List<Product> products;
}
