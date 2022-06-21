package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.acme.model.Category;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface CategoryRepository extends PanacheRepositoryBase<Category, Integer> {

    Category findByCategoryName(String categoryName);
}
