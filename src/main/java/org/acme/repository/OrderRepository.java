package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.acme.model.Order;
import org.acme.model.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public interface OrderRepository extends PanacheRepositoryBase<Order, Integer> {

    List<Order> findAllByUserOrderByCreateDataDesc(User user);
}
