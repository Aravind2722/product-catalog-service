package org.ecommerce.productcatalogservice.repositories;

import org.ecommerce.productcatalogservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    Product save(Product product);
    @Override
    List<Product> findAll();

    @Override
    Optional<Product> findById(Long id);

    void deleteById(Long id);
}
