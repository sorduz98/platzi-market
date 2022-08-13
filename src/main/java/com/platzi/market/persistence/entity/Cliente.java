package com.platzi.market.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "clientes")
@Getter
@Setter
public class Cliente {
    @Id
    @Column(length = 20, nullable = false)
    private String id;

    @Column(length = 40)
    private String nombre;

    @Column(length = 100)
    private String apellidos;

    @Column
    private Long celular;

    @Column(length = 80)
    private String direccion;

    @Column(name = "correo_electronico", length = 70)
    private String correoElectronico;

    @OneToMany(mappedBy = "cliente")
    private List<Compra> compras;
}
