package org.acme.service;

import org.acme.model.OrderItem;
import org.acme.repository.OrderItemsRepository;
import org.acme.resource.request.OrderItemsRequest;
import org.hibernate.DuplicateMappingException;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class OrderItemService {

    @Inject
    private OrderItemsRepository orderItemsRepository;

    public void addOrderedProducts(OrderItem orderItem) throws Exception{
        orderItemsRepository.persist(orderItem);
    }

}
