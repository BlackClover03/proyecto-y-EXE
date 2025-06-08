package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controlador de la pantalla inicial del juego.
 * Permite al usuario comenzar una nueva partida.
 */
public class PantallaInicial {

    /**
     * Método llamado al presionar el botón "Nueva Partida".
     * Cambia a la pantalla de explicación usando la clase GestionarMoverse.
     *
     * @param event Evento del botón presionado.
     */
    @FXML
    private void nuevaPartida(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/PantallaExplicacion.fxml"));
            AnchorPane root = loader.load();

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();

            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.setTitle("Pantalla de Explicación del Juego");
            newStage.setResizable(false);
            newStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
