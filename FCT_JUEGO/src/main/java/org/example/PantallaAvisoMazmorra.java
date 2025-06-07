package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PantallaAvisoMazmorra {

    @FXML private TextArea estadisticas_actuales;

    private Cuenta cuenta;
    private Personaje personajeSeleccionado;

    public void setDatos(Cuenta cuenta, Personaje personajeSeleccionado) {
        this.cuenta = cuenta;
        this.personajeSeleccionado = personajeSeleccionado;
        mostrarEstadisticasTotales();
    }

    private void mostrarEstadisticasTotales() {
        int ataque = personajeSeleccionado.getAtaque();
        int defensa = personajeSeleccionado.getDefensa();
        int vida = personajeSeleccionado.getVida();

        for (Arma arma : cuenta.getArmasObtenidas()) {
            if (arma.getNombre().equals(cuenta.getArmaSeleccionada())) {
                ataque += arma.getAtaque();
                defensa += arma.getDefensa();
                vida += arma.getVida();
            }
        }

        for (Objeto objeto : cuenta.getObjetosObtenidos()) {
            if (objeto.getNombre().equals(cuenta.getObjetoSeleccionado())) {
                ataque += objeto.getAtaque();
                defensa += objeto.getDefensa();
                vida += objeto.getVida();
            }
        }

        estadisticas_actuales.setText("ATAQUE: " + ataque + "\nDEFENSA: " + defensa + "\nVIDA: " + vida);
    }

    @FXML
    public void volver(ActionEvent event) {
        Node source = (Node) event.getSource();
        AnchorPane avisoPane = (AnchorPane) source.getParent();
        AnchorPane parent = (AnchorPane) avisoPane.getParent();
        parent.getChildren().remove(avisoPane);
    }

    @FXML
    public void continuar(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/org/example/Pantalla4.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Pantalla Mazmorra");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
