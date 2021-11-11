/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.desayunoshibernate3.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author hierr
 */
@Entity
@Table(name="pedidos")
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String fecha;
    private String estado;
    
    @ManyToOne
    @JoinColumn(name="id_producto")
    private Producto producto;

    public Pedido() {
    }

    public Pedido(Producto producto, String estado) {
        this.producto = producto;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Producto getId_producto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "pedidos{" + "id=" + id + ",id_producto "+producto.getId() + ", fecha=" + fecha + ", estado=" + estado + '}';
    }
}
