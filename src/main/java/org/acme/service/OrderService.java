package org.acme.service;

import org.acme.exceptions.OrderNotFoundException;
import org.acme.model.Order;
import org.acme.model.OrderItem;
import org.acme.model.User;
import org.acme.repository.OrderItemsRepository;
import org.acme.repository.OrderRepository;
import org.acme.resource.request.CartItemRequest;
import org.acme.resource.request.CartRequest;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Singleton
public class OrderService {

    @Inject
    private CartService cartService;

    @Inject
    OrderRepository orderRepository;

    @Inject
    OrderItemsRepository orderItemsRepository;

    public void placeOrder(User user, String sessionId){

        CartRequest cartRequest = cartService.listCartItems(user);

        List<CartItemRequest> cartItemRequestList = cartRequest.getCartItems();

        //create order and save it
        Order newOrder = new Order();
        newOrder.setCreatedDate(new Date());
       // newOrder.setSessionId(sessionId);
        newOrder.setUser(user);
        newOrder.setTotalPrice(cartRequest.getTotalCost());
        orderRepository.persist(newOrder);

        for (CartItemRequest cartItemRequest :cartItemRequestList){
            //create orderitem and save each
            OrderItem orderItem = new OrderItem();
            orderItem.setCreatedDate(new Date());
            orderItem.setPrice(cartItemRequest.getProduct().getPrice());
            orderItem.setProduct(cartItemRequest.getProduct());
            orderItem.setQuantity(cartItemRequest.getQuantity());
            orderItem.setOrder(newOrder);

            //add to order item list
            orderItemsRepository.persist(orderItem);
        }
        cartService.deleteUserCartItems(user);
    }

    public List<Order> listOrders(User user){
        return orderRepository.findAllByUserOrderByCreateDataDesc(user);
    }

    public Order getOrder(Integer orderId) throws OrderNotFoundException{
        Optional<Order> order = orderRepository.findByIdOptional(orderId);
        if (order.isPresent()){
            return order.get();
        }
        throw new OrderNotFoundException("Order not found");
    }
}
