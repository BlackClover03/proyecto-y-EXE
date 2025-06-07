package org.example;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Pantalla1 {

    private static Cuenta cuentaUsuario;

    public void setCuentaUsuario(Cuenta cuentaUsuario) {
        this.cuentaUsuario = cuentaUsuario;
    }
    public static Cuenta getCuentaUsuario() {
        return cuentaUsuario;
    }

    @FXML
    private AnchorPane rootPane;

    @FXML
    public void irAMazmorra(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/PantallaAvisoMazmorra.fxml"));
            AnchorPane aviso = loader.load();

            PantallaAvisoMazmorra controlador = loader.getController();
            Personaje personajePrincipal = new Personaje();
            controlador.setDatos(Pantalla1.getCuentaUsuario(), personajePrincipal);

            aviso.setLayoutX((rootPane.getWidth() - aviso.getPrefWidth()) / 2);
            aviso.setLayoutY((rootPane.getHeight() - aviso.getPrefHeight()) / 2);

            rootPane.getChildren().add(aviso);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void irAInventario(ActionEvent event) {
        GestionarMoverse.cambiarEscena(event, "/org/example/Pantalla2.fxml", "Pantalla de Inventario");
    }

    @FXML
    public void irATienda(ActionEvent event) {
        GestionarMoverse.cambiarEscena(event, "/org/example/Pantalla3.fxml", "Pantalla de la Tienda");
    }

}
