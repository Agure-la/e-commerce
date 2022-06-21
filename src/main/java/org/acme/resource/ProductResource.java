package org.acme.resource;

import org.acme.model.Category;
import org.acme.resource.request.ProductRequest;
import org.acme.service.CategoryService;
import org.acme.service.ProductService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/product")
public class ProductResource {

    @Inject
    private ProductService productService;

    @Inject
    private CategoryService categoryService;

//    @GET
//    @Path("/")
//    public Response <List<ProductRequest>> getProducts(){
//        List<ProductRequest> body = productService.listProducts();
//        return new Response<List<ProductRequest>>(body, HttpStatus.OK);
//    }

    @POST
    @Path("/add")
    public Response addProduct(ProductRequest productRequest){
        Optional<Category> optionalCategory = categoryService.readCategory(productRequest.getCategoryId());
        if (!optionalCategory.isPresent()){
            return null;
        }

        Category category = optionalCategory.get();
        productService.addProduct(productRequest, category);

        return Response.ok().build();
    }

    @POST
    @Path("/add")
    public Response updateProduct(@PathParam("productId") Integer productId, ProductRequest productRequest){
        Optional<Category> optionalCategory = categoryService.readCategory(productRequest.getCategoryId());
        if (!optionalCategory.isPresent()){
            return null;
        }
        Category category = optionalCategory.get();
        productService.updateProduct(productId, productRequest, category);
        return Response.ok().build();
    }
}
