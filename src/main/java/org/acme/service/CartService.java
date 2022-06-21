package org.acme.service;

import org.acme.exceptions.CartItemNotExistException;
import org.acme.model.Cart;
import org.acme.model.Product;
import org.acme.model.User;
import org.acme.repository.CartRepository;
import org.acme.resource.request.AddToCartRequest;
import org.acme.resource.request.CartItemRequest;
import org.acme.resource.request.CartRequest;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Singleton
public class CartService {

    @Inject
    private CartRepository cartRepository;

    public CartService() {
    }

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void addToCart(AddToCartRequest addToCartRequest, Product product, User user){
        Cart cart = new Cart(product, addToCartRequest.getQuantity(), user);
        cartRepository.persist(cart);
    }

    public CartRequest listCartItems(User user){
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreateDataDesc(user);
        List<CartItemRequest> cartItemRequests = new ArrayList<>();
        for (Cart cart:cartList){
            CartItemRequest cartItemRequest = getRequestFromCart(cart);
            cartItemRequests.add(cartItemRequest);
        }
        double totalCost = 0;
        for (CartItemRequest cartItemRequest :cartItemRequests){
            totalCost += (cartItemRequest.getProduct().getPrice() * cartItemRequest.getQuantity());
        }
        return new CartRequest(cartItemRequests, totalCost);
    }

    public static CartItemRequest getRequestFromCart(Cart cart){
        return new CartItemRequest(cart);
    }

    public void updateCartItem(AddToCartRequest cartRequest, User user, Product product){
        Cart cart = cartRepository.findByIdOptional(cartRequest.getId());
        cart.setQuantity(cartRequest.getQuantity());
        cart.setCreatedDate(new Date());
        cartRepository.persist(cart);
    }

    public void deleteCartItem(int id, int userId) throws CartItemNotExistException{
        if (cartRepository.findById(id) == null)
        throw new CartItemNotExistException("Cart id is invalid : " + id);
        cartRepository.findById(id);
    }

    public void deleteCartItems(int userId){
        cartRepository.deleteAll();
    }

    public void deleteUserCartItems(User user){
        cartRepository.deleteByUser(user);
    }
}
