package org.example;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Clase {@code Pantalla1} controladora de la interfaz principal del juego.
 *
 * Permite al usuario navegar hacia la mazmorra, la tienda o el inventario.
 * También gestiona la cuenta del usuario actual.
 */
public class Pantalla1 {

    /**
     * Cuenta del usuario actualmente logueado o en uso.
     */
    private static Cuenta cuentaUsuario;

    /**
     * Indica si la ventana de aviso de la mazmorra ya está abierta.
     */
    private boolean avisoMazmorraAbierto = false;

    /**
     * Establece la cuenta del usuario.
     * @param cuentaUsuario Objeto {@link Cuenta} con los datos del usuario.
     */
    public void setCuentaUsuario(Cuenta cuentaUsuario) {
        this.cuentaUsuario = cuentaUsuario;
    }

    /**
     * Devuelve la cuenta del usuario actual.
     * @return Objeto {@link Cuenta} del usuario.
     */
    public static Cuenta getCuentaUsuario() {
        return cuentaUsuario;
    }

    /**
     * Panel raíz de la escena. Usado para insertar otras vistas como ventanas emergentes.
     */
    @FXML
    private AnchorPane rootPane;

    /**
     * Muestra la pantalla de aviso antes de entrar a la mazmorra.
     * Carga el archivo FXML correspondiente y pasa los datos del usuario y personaje principal.
     *
     * @param event Evento que desencadena un cambio como por ejemplo un clic a un botón.
     */
    @FXML
    public void irAMazmorra(ActionEvent event) {
        if (avisoMazmorraAbierto) {
            return; // Evita abrir múltiples veces la escena
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/PantallaAvisoMazmorra.fxml"));
            AnchorPane aviso = loader.load();

            PantallaAvisoMazmorra controlador = loader.getController();
            Personaje personajePrincipal = new Personaje();
            controlador.setPantallaPrincipal(this);
            controlador.setDatos(Pantalla1.getCuentaUsuario(), personajePrincipal);

            aviso.setLayoutX((rootPane.getWidth() - aviso.getPrefWidth()) / 2);
            aviso.setLayoutY((rootPane.getHeight() - aviso.getPrefHeight()) / 2);

            rootPane.getChildren().add(aviso);
            avisoMazmorraAbierto = true;

            aviso.setOnMouseClicked(e -> {
                rootPane.getChildren().remove(aviso);
                avisoMazmorraAbierto = false;
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void notificarCierreAvisoMazmorra() {
        avisoMazmorraAbierto = false;
    }

    /**
     * Cambia la escena a la pantalla de Inventario.
     *
     * @param event Evento que desencadena el cambio.
     */
    @FXML
    public void irAInventario(ActionEvent event) {
        GestionarMoverse.cambiarEscena(event, "/org/example/Pantalla2.fxml", "Pantalla de Inventario");
    }

    /**
     * Cambia la escena a la pantalla de Tienda.
     *
     * @param event Evento que desencadena el cambio.
     */
    @FXML
    public void irATienda(ActionEvent event) {
        GestionarMoverse.cambiarEscena(event, "/org/example/Pantalla3.fxml", "Pantalla de la Tienda");
    }

}
