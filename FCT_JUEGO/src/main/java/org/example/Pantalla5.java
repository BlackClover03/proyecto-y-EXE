package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controlador de la pantalla de resultados de la mazmorra.
 * Muestra la recompensa obtenida y el resultado del combate.
 */
public class Pantalla5 {

    /** Recompensa total obtenida en la mazmorra */
    private int recompensaTotal;

    /** Resultado del combate en la mazmorra (por ejemplo, "Victoria" o "Derrota") */
    private String resultadoMazmorra;

    /**
     * Constructor vacío necesario para el controlador FXML.
     */
    public Pantalla5() {}

    /**
     * Cambia la escena a la pantalla de inicio.
     *
     * @param event El evento de acción generado al hacer clic en el botón correspondiente.
     */
    @FXML
    public void irAInicio(ActionEvent event) {
        GestionarMoverse.cambiarEscena(event, "/org/example/Pantalla1.fxml", "Pantalla Inicio");
    }

    /** Etiqueta para mostrar la recompensa obtenida */
    @FXML private Label labelRecompensa;

    /** Etiqueta para mostrar el resultado de la mazmorra */
    @FXML private Label labelResultado;

    /**
     * Establece los datos de recompensa y resultado y los muestra en pantalla.
     *
     * @param recompensaTotal     Recompensa total obtenida por el jugador.
     * @param resultadoMazmorra   Resultado del combate (por ejemplo, "Victoria" o "Derrota").
     */
    public void setDatos(int recompensaTotal, String resultadoMazmorra) {
        this.recompensaTotal = recompensaTotal;
        this.resultadoMazmorra = resultadoMazmorra;
        mostrarResultados();
    }

    /**
     * Muestra en los labels la recompensa y el resultado almacenados.
     */
    public void mostrarResultados() {
        labelRecompensa.setText(String.valueOf(recompensaTotal));
        labelResultado.setText(resultadoMazmorra);
    }
}
