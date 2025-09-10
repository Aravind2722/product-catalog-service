package org.ecommerce.productcatalogservice.dtos;


import lombok.Getter;
import lombok.Setter;
import org.ecommerce.productcatalogservice.models.Product;

@Getter
@Setter
public class GetSingleProductResponseDto {
    private Long id;
    private String title;
    private String description;
    private String image;
    private String categoryName;

    public static GetSingleProductResponseDto fromProduct(Product product) {
        GetSingleProductResponseDto responseDto = new GetSingleProductResponseDto();
        responseDto.setId(product.getId());
        responseDto.setDescription(product.getDescription());
        responseDto.setTitle(product.getTitle());
        responseDto.setImage(product.getImage());
        responseDto.setCategoryName(product.getCategory().getName());

        return responseDto;
    }
}
