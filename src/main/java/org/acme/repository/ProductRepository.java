package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.acme.model.Product;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface ProductRepository extends PanacheRepositoryBase<Product, Integer> {
}
