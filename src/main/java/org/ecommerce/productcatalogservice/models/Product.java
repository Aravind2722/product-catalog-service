package org.ecommerce.productcatalogservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.yaml.snakeyaml.representer.BaseRepresenter;

@Entity
@Getter
@Setter
public class Product extends BaseModel {
    private String title;
    private String description;
    private Double price;
//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @ManyToOne
    private Category category;
    private String image;
}
