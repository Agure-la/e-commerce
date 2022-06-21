package org.acme.service;

import org.acme.exceptions.ProductNotExistException;
import org.acme.model.Category;
import org.acme.model.Product;
import org.acme.repository.ProductRepository;
import org.acme.resource.request.ProductRequest;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class ProductService {

    @Inject
    private ProductRepository productRepository;

    public List<ProductRequest> listProducts(){
        List<Product> products = productRepository.findAll().list();
        List<ProductRequest> productRequests = new ArrayList<>();
        for (Product product :products){
            ProductRequest productRequest = getRequestFromProduct(product);
            productRequests.add(productRequest);
        }
        return productRequests;
    }

    public static ProductRequest getRequestFromProduct(Product product){
        ProductRequest productRequest = new ProductRequest(product);
        return productRequest;
    }

    public static Product getProductFromRequest(ProductRequest productRequest, Category category){
        Product product = new Product(productRequest, category);
        return product;
    }

    public void addProduct(ProductRequest productRequest, Category category){
        Product product = getProductFromRequest(productRequest, category);
        productRepository.persist(product);
    }

    public void updateProduct(Integer productId, ProductRequest productRequest, Category category){
        Product product = getProductFromRequest(productRequest,category);
        product.setId(productId);
        productRepository.persist(product);
    }

    public Product getProductById(Integer productId) throws ProductNotExistException{
        Optional<Product> optionalProduct = productRepository.findByIdOptional(productId);
        if (!optionalProduct.isPresent())
            throw new ProductNotExistException("Product id is invalid " + productId);
        return optionalProduct.get();
    }
}
