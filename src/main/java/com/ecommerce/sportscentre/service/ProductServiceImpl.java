package com.ecommerce.sportscentre.service;

import com.ecommerce.sportscentre.entity.Product;
import com.ecommerce.sportscentre.model.ProductResponse;
import com.ecommerce.sportscentre.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse getProductById(Integer productId) {
        log.info("Fetching the product by Id: {}", productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new RuntimeException(String.format("Product with Id: %d doesn't exist", productId)));
        ProductResponse productResponse = convertToProductResponse(product);
        log.info("Fetched Product with Product Id: {}", productId);
        return productResponse;
    }
    @Override
    public List<ProductResponse> getProducts() {
        log.info("Fetching the products......");
        List<Product> productList = productRepository.findAll();
        List<ProductResponse> productResponses = productList.stream()
                .map(this::convertToProductResponse)
                .toList();
        log.info("Fetched All Products.......");
        return productResponses;
    }

    private ProductResponse convertToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .pictureUrl(product.getPictureUrl())
                .productBrand(product.getBrand().getName())
                .productType(product.getType().getName())
                .build();
    }
}
