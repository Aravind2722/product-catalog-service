package org.ecommerce.productcatalogservice.dtos.fakestore_dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateFakeStoreProductRequestDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String category;
    private String image;
}
