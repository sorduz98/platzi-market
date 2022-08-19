package com.platzi.market.web.controller;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("products")
@Tag(name = "Product Controller", description = "This controller defines all endpoints used to get product data")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(
            summary = "Get all supermarket products",
            description = "Get all supermarket products, it requires an auth token to perform operation"
    )
    @GetMapping("")
    public ResponseEntity<List<Product>> getAll() {
        try {
            return new ResponseEntity(productService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            summary = "Search product by id",
            description = "Search a product with an productId, it requires an auth token to perform operation"
    )
    @GetMapping("{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable int productId) {
        try {
            return productService.getProduct(productId)
                    .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            summary = "Filter products by category and more...",
            description = "Filter products by category and more, it requires an auth token to perform operation"
    )
    @GetMapping("filter")
    public ResponseEntity<List<Product>> getAllAndFilter(@RequestParam Optional<Integer> categoryId) {
        try {
            if(categoryId.isPresent()) {
                return productService.getByCategory(categoryId.get())
                        .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
            } else {
                return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            summary = "Create a new product",
            description = "Create a new product, it requires an auth token and ADMIN role to perform operation"
    )
    @PostMapping("")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        try {
            return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
        } catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            summary = "Delete product",
            description = "Create a new product, it requires an auth token and ADMIN role to perform operation"
    )
    @DeleteMapping("/{productId}")
    public ResponseEntity<Boolean> delete(@PathVariable int productId) {
        try {
            return new ResponseEntity<>(productService.delete(productId), HttpStatus.OK);
        } catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
