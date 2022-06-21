package org.acme.resource;


import org.acme.service.AuthenticationService;
import org.acme.service.OrderService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/order")
public class OrderResource {

    @Inject
    private OrderService orderService;

    @Inject
    private AuthenticationService authenticationService;

//    @GET("/")
//    public Response<List<Order>> getAllOrders(@PathParam("token") String token) throws AuthenticationFailException {
//        //validate token
//        authenticationService.authenticate(token);
//        //rettrieve user
//        User user = authenticationService.getUser(token);
//        //place the order
//        orderService.placeOrder(user);
//        return new Response<>(new ApiResponse(true, "Order has been placed", HttpStatus.CREATED));
//
//    }

//    @GET
//    @Path("/{id}")
//    public Response<Object> getOrderById(@PathParam("id") Integer id, @PathParam("token") String token)
//            throws AuthenticationFailException {
//        //validate token
//        authenticationService.authenticate(token);
//        try {
//            Order order = orderService.getOrder(id);
//            return new Response<>(order, HttpStatus.OK);
//        }
//        catch (OrderNotFoundException e){
//            return new Response<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }
}
