package com.platzi.market.web.controller;

import com.platzi.market.domain.Purchase;
import com.platzi.market.persistence.CompraRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Purchase Controller", description = "This controller defines all endpoints used to get product data")
@RestController
@RequestMapping("purchases")
public class PurchaseController {
    private final CompraRepository compraRepository;

    public PurchaseController(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    @GetMapping()
    private ResponseEntity<List<Purchase>> getAll() {
        return new ResponseEntity<>(compraRepository.getAll(), HttpStatus.OK);
    }

    @GetMapping("client/{clientId}")
    private ResponseEntity<List<Purchase>> getByClientId(@PathVariable String clientId) {
        return compraRepository.getByClient(clientId)
                .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    private ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(compraRepository.save(purchase), HttpStatus.OK);
    }

}
