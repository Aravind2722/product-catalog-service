package org.ecommerce.productcatalogservice.dtos.fakestore_dtos;


import lombok.Getter;
import lombok.Setter;
import org.ecommerce.productcatalogservice.models.Category;
import org.ecommerce.productcatalogservice.models.Product;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;

    public Product toProduct() {
        Product product = new Product();
        product.setId(this.getId());
        product.setTitle(this.getTitle());
        product.setPrice(this.getPrice());
        product.setDescription(this.getDescription());
        product.setImage(this.getImage());
        Category category1 = new Category();
        category1.setName(this.getCategory());
        product.setCategory(category1);

        return product;
    }
}
