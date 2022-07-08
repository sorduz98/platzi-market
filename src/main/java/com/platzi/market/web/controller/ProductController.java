package com.platzi.market.web.controller;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getAll() {
        try {
            return new ResponseEntity(productService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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

    @PostMapping("")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        try {
            return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
        } catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Boolean> delete(@PathVariable int productId) {
        try {
            return new ResponseEntity<>(productService.delete(productId), HttpStatus.OK);
        } catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
