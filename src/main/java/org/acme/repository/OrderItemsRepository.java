package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.acme.model.OrderItem;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderItemsRepository implements PanacheRepositoryBase<OrderItem, Integer> {
}
