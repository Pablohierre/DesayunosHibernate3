/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.desayunoshibernate3.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author hierr
 */
@Entity
@Table(name="productos")
public class Producto implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String nombre;
    private double precio;
    private String descripcion;

    @OneToMany(mappedBy="producto", fetch=FetchType.EAGER)
    private List<Pedido> pedidos;
    public Producto() {
    }

    public Producto(String nombre, double precio, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "carta{" + "id= " + id + ", nombre=" + nombre + ", precio=" + precio + ", descripcion=" + descripcion + '}';
    }
    
}
