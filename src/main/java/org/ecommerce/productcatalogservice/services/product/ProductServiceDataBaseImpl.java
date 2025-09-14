package org.ecommerce.productcatalogservice.services.product;

import org.ecommerce.productcatalogservice.exceptions.ProductNotFoundException;
import org.ecommerce.productcatalogservice.models.Category;
import org.ecommerce.productcatalogservice.models.Product;
import org.ecommerce.productcatalogservice.repositories.CategoryRepository;
import org.ecommerce.productcatalogservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class ProductServiceDataBaseImpl implements ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceDataBaseImpl(CategoryRepository categoryRepository,
                                      ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) throw new ProductNotFoundException("Product with id " + id + " does not exist.");

        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(String title, String description, Double price, String categoryName, String imageUrl) {
        Optional<Category> categoryOptional = categoryRepository.findByNameEquals(categoryName);
        Category category = new Category();
        if (categoryOptional.isPresent()) category = categoryOptional.get();
        category.setName(categoryName);
        Category savedCategory = categoryRepository.save(category);

        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(savedCategory);
        product.setImage(imageUrl);

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, String title, String description, Double price, String categoryName, String imageUrl) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) throw new ProductNotFoundException("Product with id " + id + " does not exist.");

        Product product = productOptional.get();
        if (title != null) product.setTitle(title);
        if (description != null) product.setDescription(description);
        if (price != null) product.setPrice(price);
        if (imageUrl != null) product.setImage(imageUrl);
        if (categoryName != null) {
            Optional<Category> categoryoptional = categoryRepository.findByNameEquals(categoryName);
            Category category = new Category();
            if (categoryoptional.isPresent()) category = categoryoptional.get();
            category.setName(categoryName);
            Category savedCategory = categoryRepository.save(category);
//          Foreign key relationship is mapped by Product class. Hence, no need for the following commented steps
//            Category oldCategory = product.getCategory();
//            oldCategory.getProducts().remove(product);
//            if (newCategory.getProducts() == null) newCategory.setProducts(new ArrayList<>());
//            newCategory.getProducts().add(product);
            product.setCategory(savedCategory);
        }
        return productRepository.save(product);
    }

    @Override
    public Product replaceProduct(Long id, String title, String description, Double price, String categoryName, String imageUrl) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) throw new ProductNotFoundException("Product with id " + id + " does not exist.");
        Product product = productOptional.get();

        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImage(imageUrl);

        Optional<Category> categoryOptional = categoryRepository.findByNameEquals(categoryName);
        Category category = new Category();
        if (categoryOptional.isPresent()) category = categoryOptional.get();
        category.setName(categoryName);
        Category savedCategory = categoryRepository.save(category);

        product.setCategory(savedCategory);

        return productRepository.save(product);
    }
    @Transactional
    @Override
    public void deleteProduct(Long id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) throw new ProductNotFoundException("Product with id " + id + " does not exist.");
        productRepository.deleteById(id);
    }
}
