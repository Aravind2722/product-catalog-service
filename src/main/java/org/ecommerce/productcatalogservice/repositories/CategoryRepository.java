package org.ecommerce.productcatalogservice.repositories;

import org.ecommerce.productcatalogservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByNameEquals(String name);
    Category save(Category category);
}
