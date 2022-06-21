package org.acme.resource;

import org.acme.exceptions.AuthenticationFailException;
import org.acme.exceptions.CartItemNotExistException;
import org.acme.exceptions.ProductNotExistException;
import org.acme.model.Product;
import org.acme.model.User;
import org.acme.resource.request.AddToCartRequest;
import org.acme.resource.request.CartRequest;
import org.acme.service.AuthenticationService;
import org.acme.service.CartService;
import org.acme.service.ProductService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/cart")
public class CartResource {

    @Inject
    private CartService cartService;

    @Inject
    private ProductService productService;

    @Inject
    private AuthenticationService authenticationService;

    @POST
    @Path("/add")
    public Response addToCart(AddToCartRequest addToCartRequest, @PathParam("token") String token)
        throws AuthenticationFailException, ProductNotExistException{
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        Product product = productService.getProductById(addToCartRequest.getProductId());
        System.out.println("product to add" + product.getName());
        cartService.addToCart(addToCartRequest, product, user);

        return Response.ok().build();
    }

    @GET
    @Path("/")
    public Response getCartItems(@PathParam("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        CartRequest cartRequest = cartService.listCartItems(user);

        return Response.ok().build();
    }

    @PUT
    @Path("/update/{cartItemId}")
    public Response updateCartItem(AddToCartRequest addToCartRequest, @PathParam("token") String token)
        throws AuthenticationFailException, ProductNotExistException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        Product product = productService.getProductById(addToCartRequest.getProductId());
        cartService.updateCartItem(addToCartRequest, user, product);

        return Response.ok().build();
    }

    @DELETE
    @Path("/delete/{cartItemId}")
    public Response deleteCartItem(@PathParam("cartItemId") int itemId, @PathParam("token") String token)
        throws AuthenticationFailException, CartItemNotExistException{
        authenticationService.authenticate(token);
        int userId = authenticationService.getUser(token).getId();
        cartService.deleteCartItem(itemId, userId);

        return Response.ok().build();
    }
}
