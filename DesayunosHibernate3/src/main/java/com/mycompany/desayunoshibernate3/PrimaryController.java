/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.desayunoshibernate3;

import com.mycompany.desayunoshibernate3.models.Pedido;
import com.mycompany.desayunoshibernate3.models.Producto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author hierr
 */
public class PrimaryController implements Initializable {


    @FXML
    private Label txtFechaHora;
    @FXML
    private Label pedidosPendientes;
    @FXML
    private Button btnVerCarta;
    @FXML
    private Button btnAñadirProducto;
    @FXML
    private Button btnAñadirPedido;
    @FXML
    private TableView<?> tabla;
    @FXML
    private TableColumn<Pedido, Long> cId;
    @FXML
    private TableColumn<Producto, Long> cIdProducto;
    @FXML
    private TableColumn<Pedido, String> cFecha;
    @FXML
    private TableColumn<Pedido, String> cEstado;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    @FXML
    private void cambiarEstado(MouseEvent event) {
    }

}
