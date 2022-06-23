package com.platzi.market.persistence;

import com.platzi.market.persistence.crud.ProductoCrudRepository;
import com.platzi.market.persistence.entity.Producto;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository {
    private final ProductoCrudRepository productoCrudRepository;

    public ProductoRepository(ProductoCrudRepository poductoCrudReposity) {
        this.productoCrudRepository = poductoCrudReposity;
    }

    public List<Producto> getAll() {
        return (List<Producto>) productoCrudRepository.findAll();
    }

    public List<Producto> getByCategoria(String idCategoria) {
        return productoCrudRepository.findByCategoria_IdCategoria(idCategoria);
    }

    public List<Producto> getByCategoriaOrderByNombreAsc(String idCategoria) {
        return productoCrudRepository.findByCategoria_IdCategoria(idCategoria, Sort.by("nombre").ascending());
    }

    public Optional<List<Producto>> getEscasos(Integer cantidad) {
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
    }

    public Optional<Producto> getProducto(Integer idProducto) {
        return productoCrudRepository.findById(idProducto);
    }

    public Producto save(Producto producto) {
        return productoCrudRepository.save(producto);
    }

    public void delete(Integer idProducto) {
        productoCrudRepository.deleteById(idProducto);
    }
}
