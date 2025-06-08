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

/**
 * Controlador de la pantalla de aviso antes de entrar a la mazmorra.
 * Muestra las estadísticas totales del personaje con el arma y el objeto seleccionados.
 * Permite al jugador confirmar su decisión de continuar o volver.
 */
public class PantallaAvisoMazmorra {

    @FXML private TextArea estadisticas_actuales;

    private Pantalla1 pantallaPrincipal;
    private Cuenta cuenta;
    private Personaje personajeSeleccionado;

    public void setPantallaPrincipal(Pantalla1 pantallaPrincipal) {
        this.pantallaPrincipal = pantallaPrincipal;
    }

    /**
     * Establece los datos necesarios para calcular y mostrar las estadísticas combinadas
     * del personaje, arma y objeto seleccionados por la cuenta.
     *
     * @param cuenta La cuenta del jugador.
     * @param personajeSeleccionado El personaje elegido por el jugador.
     */
    public void setDatos(Cuenta cuenta, Personaje personajeSeleccionado) {
        this.cuenta = cuenta;
        this.personajeSeleccionado = personajeSeleccionado;
        mostrarEstadisticasTotales();
    }

    /**
     * Calcula y muestra en el TextArea las estadísticas finales del personaje
     * teniendo en cuenta el arma y el objeto seleccionados.
     */
    private void mostrarEstadisticasTotales() {
        int ataque = personajeSeleccionado.getAtaque();
        int defensa = personajeSeleccionado.getDefensa();
        int vida = personajeSeleccionado.getVida();

        // Sumar estadísticas del arma seleccionada
        for (Arma arma : cuenta.getArmasObtenidas()) {
            if (arma.getNombre().equals(cuenta.getArmaSeleccionada())) {
                ataque += arma.getAtaque();
                defensa += arma.getDefensa();
                vida += arma.getVida();
            }
        }

        // Sumar estadísticas del objeto seleccionado
        for (Objeto objeto : cuenta.getObjetosObtenidos()) {
            if (objeto.getNombre().equals(cuenta.getObjetoSeleccionado())) {
                ataque += objeto.getAtaque();
                defensa += objeto.getDefensa();
                vida += objeto.getVida();
            }
        }

        estadisticas_actuales.setText("ATAQUE: " + ataque + "\nDEFENSA: " + defensa + "\nVIDA: " + vida);
    }

    /**
     * Cierra el panel de aviso y vuelve a la pantalla anterior (pantalla de selección).
     *
     * @param event Evento de acción generado por el botón "Volver".
     */
    @FXML
    public void volver(ActionEvent event) {
        Node source = (Node) event.getSource();
        AnchorPane avisoPane = (AnchorPane) source.getParent();
        AnchorPane parent = (AnchorPane) avisoPane.getParent();
        parent.getChildren().remove(avisoPane);

        if (pantallaPrincipal != null) {
            pantallaPrincipal.notificarCierreAvisoMazmorra();
        }
    }

    /**
     * Continúa hacia la pantalla de la mazmorra (Pantalla4.fxml).
     *
     * @param event Evento de acción generado por el botón "Continuar".
     */
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
