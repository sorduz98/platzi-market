package com.platzi.market.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "compras")
@Getter
@Setter
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra", nullable = false)
    private Integer idCompra;

    @Column(name = "id_cliente", nullable = false, length = 20)
    private String idCliente;

    @Column
    private LocalDateTime fecha;

    @Column(name = "medio_pago")
    private Character medioPago;

    @Column(length = 300)
    private String comentario;

    @Column
    private Character estado;

    @ManyToOne
    @JoinColumn(name = "id_cliente", insertable = false, updatable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "compra", cascade = { CascadeType.ALL })
    private List<ComprasProducto> productos;
}