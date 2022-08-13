package com.platzi.market.domain.service;

import com.platzi.market.persistence.CompraRepository;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    private final CompraRepository compraRepository;

    public PurchaseService(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }


}
