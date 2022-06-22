package com.platzi.market.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categorias")
@Getter
@Setter
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria", nullable = false)
    private String idCategoria;

    @Column(length = 45, nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Boolean estado;

    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos;
}
