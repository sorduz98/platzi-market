package com.platzi.market.persistence;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.repository.ProductRepository;
import com.platzi.market.persistence.crud.ProductoCrudRepository;
import com.platzi.market.persistence.entity.Producto;
import com.platzi.market.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {
    private final ProductoCrudRepository productoCrudRepository;
    private final ProductMapper mapper;

    public ProductoRepository(ProductoCrudRepository poductoCrudReposity, ProductMapper mapper) {
        this.productoCrudRepository = poductoCrudReposity;
        this.mapper = mapper;
    }

    @Override
    public List<Product> getAll() {
         List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
         return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int id) {
        List<Producto> productos = productoCrudRepository.findByCategoria_IdCategoria(id);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true)
                .map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(prod -> mapper.toProduct(prod));
    }

    @Override
    public Product save(Product product) {
        Producto productToSave = mapper.toProducto(product);
        Producto productSaved = productoCrudRepository.save(productToSave);
        return mapper.toProduct(productSaved);
    }

    @Override
    public void delete(int productId) {
        productoCrudRepository.deleteById(productId);
    }
}
