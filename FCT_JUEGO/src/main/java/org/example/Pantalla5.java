package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Pantalla5 {
    private int recompensaTotal;
    private String resultadoMazmorra;

    public Pantalla5() {}

    @FXML
    public void irAInicio(ActionEvent event) {
        GestionarMoverse.cambiarEscena(event, "/org/example/Pantalla1.fxml", "Pantalla Inicio");
    }

    @FXML private Label labelRecompensa;
    @FXML private Label labelResultado;

    public void setDatos(int recompensaTotal, String resultadoMazmorra) {
        this.recompensaTotal = recompensaTotal;
        this.resultadoMazmorra = resultadoMazmorra;
        mostrarResultados();
    }

    public void mostrarResultados() {
        labelRecompensa.setText(String.valueOf(recompensaTotal));
        labelResultado.setText(resultadoMazmorra);
    }
}
