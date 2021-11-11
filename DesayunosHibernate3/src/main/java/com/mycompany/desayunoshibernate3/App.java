/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.desayunoshibernate3;

import com.mycompany.desayunoshibernate3.models.Pedido;
import com.mycompany.desayunoshibernate3.models.Producto;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
//import models.Pedido;
//import models.Producto;

/**
 *
 * @author hierr
 */
public class App extends Application {

    public void initialize(URL url, ResourceBundle rb) {
        mostrarmenu();
    }


    @Override
    public void start(Stage stage) throws IOException {
       mostrarmenu(); 
    }

    public static void mostrarmenu() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));

        System.out.println("====Menú de opciones====");
        System.out.println("0- Ver Carta");
        System.out.println("1-Añadir producto a la carta");
        System.out.println("2-Añadir pedido");
        System.out.println("3-Eliminar pedido");
        System.out.println("4-Editar estado de pedido");
        System.out.println("5-Mostar pedidos para hoy");
        System.out.println("6-Mostar el histórico de pedidos");
        System.out.println("7-Salir");
        System.out.println("#################################");
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();

        switch (opcion) {
            case 0:
                mostrarCarta();
                mostrarmenu();
                break;
            case 1:
                anadirProducto();
                mostrarmenu();
                break;
            case 2:
                anadirPedido();
                mostrarmenu();
                break;
            case 3:
                eliminarPedido();
                mostrarmenu();
                break;
            case 4:
                cambiarEstado();
                mostrarmenu();
                break;
            case 5:
                comandasHoy();
                mostrarmenu();
                break;
            case 6 :
                mostrarPedidos();
                mostrarmenu();
                break;
            case 7:
                System.exit(0);
                break;

        }
    }

    public static void mostrarCarta() {
        Session s = HibernateUtil.getSessionFactory().openSession();

        Query q = s.createQuery("FROM Producto");
        q.list().forEach((e) -> System.out.println(e));

    }

    public static void anadirProducto() {

        Producto p = new Producto();
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre del producto");
        p.setNombre(sc.nextLine());
        System.out.println("Introduce el precio del producto");
        p.setPrecio(sc.nextDouble());
        System.out.println("Introduce una descripcion");
        Scanner sc1 = new Scanner(System.in);
        p.setDescripcion(sc1.nextLine());

        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = s.beginTransaction();
        s.save(p);
        tr.commit();

    }

    public static void mostrarPedidos() {
        Session s = HibernateUtil.getSessionFactory().openSession();

        Query q = s.createQuery("FROM Pedido");
        q.list().forEach((e) -> System.out.println(e));
    }

    public static void anadirPedido() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        
        

        Session s = HibernateUtil.getSessionFactory().openSession();
        Pedido nuevo = new Pedido();
        Scanner sc = new Scanner(System.in);
        Producto producto = new Producto();

        System.out.println("Introduce el nombre del producto");
        String nombreProducto = sc.nextLine();
        Query q = s.createQuery("FROM Producto p WHERE p.nombre=:name");
        q.setParameter("name", nombreProducto);
        producto = (Producto) q.list().get(0);

        
        
        nuevo.setFecha(now.toString());
        nuevo.setEstado("En espera");
        nuevo.setProducto(producto);

        try {
            Transaction tr = s.beginTransaction();
            s.save(nuevo);
            tr.commit();
            System.out.println("Pedido añadido correctamente");
        } catch (Exception e) {
            System.out.println("Error añadiendo el pedido");
        }

    }

    public static void eliminarPedido() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Producto producto = new Producto();
        
        
        System.out.println("Escribe el nombre del producto que quieres eliminar de la lista de pedidos");
        Scanner sc = new Scanner(System.in);
        String nombreProducto = sc.nextLine();
        
//      Primero busco el producto con el nombre que el usuario ha introducido
//      Creo un objeto Producto que el usuario quiere eliminar
        Query q = s.createQuery("FROM Producto p WHERE p.nombre=:name");
        q.setParameter("name", nombreProducto);
        producto=(Producto) q.list().get(0);
        
        
//      Busco en la clase Pedido los pedidos que tienen el id de producto que el usuario quiere eliminar
        Pedido eliminado = new Pedido();

        Query buscarPedido = s.createQuery("From Pedido p WHERE p.producto=:idproducto");
        buscarPedido.setParameter("idproducto",producto);
        eliminado = (Pedido) buscarPedido.list().get(0);
        
//       Habiendo encontrado los pedidos de ese producto,elimino uno de ellos

        Transaction tr = s.beginTransaction();
        s.remove(eliminado);
        tr.commit();
       
    }

    public static void cambiarEstado() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        
//      Muestro la lista de pedidos    
        mostrarPedidos();
        Scanner sc = new Scanner(System.in);
        Pedido cambiado = new Pedido();
        
//      Busco el pedido que tiene el ID que introduce el usuario
        System.out.println("ID del pedido a actualizar");
        Long id=sc.nextLong();
        cambiado=s.load(Pedido.class,id);
        
//        Le cambio el estado a ese pedido       
        System.out.println("Introduce nuevo estado");
        String nuevoEstado = sc.next();
        cambiado.setEstado(nuevoEstado);
        
        try {
            Transaction tr = s.beginTransaction();
            s.update(cambiado);
            tr.commit();
            System.out.println("Pedido modificado correctamente");
        } catch (Exception e) {
            System.out.println("Error modificando el producto");
        }       

    }

    public static void comandasHoy() {

Session s = HibernateUtil.getSessionFactory().openSession();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDateTime now = LocalDateTime.now();

        System.out.println(now);
    Query q = s.createQuery("FROM Pedido p WHERE p.estado=:estadoActual and p.fecha = current_date()");
    q.setParameter("estadoActual","En espera");
//    q.setParameter("fechaHoy", );
    q.list().forEach((e) -> System.out.println(e));
    }

    
}
