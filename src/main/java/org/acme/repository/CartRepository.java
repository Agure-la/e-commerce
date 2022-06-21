package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.acme.model.Cart;
import org.acme.model.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public interface CartRepository extends PanacheRepositoryBase<Cart, Integer> {

    List<Cart> findAllByUserOrderByCreateDataDesc(User user);

    List<Cart> deleteByUser(User user);
}
