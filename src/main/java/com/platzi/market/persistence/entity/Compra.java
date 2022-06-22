package com.platzi.market.persistence.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "compras")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra", nullable = false)
    private Integer idCompra;

    @Column
    private LocalDateTime fecha;

    @Column(name = "medio_pago")
    private Character medioPago;

    @Column(length = 300)
    private Integer comentario;

    @Column
    private Character estado;

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "producto")
    private List<ComprasProducto> productos;
}