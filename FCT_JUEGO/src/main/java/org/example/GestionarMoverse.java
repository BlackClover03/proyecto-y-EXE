package org.example;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

/**
 * Clase auxiliar encargada de gestionar los cambios de escena dentro de la aplicación JavaFX.
 */
public class GestionarMoverse {

    /**
     * Cambia la escena actual por una nueva, basada en el archivo FXML indicado.
     *
     * @param event     Evento de acción que desencadena el cambio de escena (normalmente desde un botón).
     * @param fxmlPath  Ruta del archivo FXML a cargar (por ejemplo, "/vistas/Pantalla2.fxml").
     * @param titulo    Título de la nueva ventana/escena.
     */
    public static void cambiarEscena(ActionEvent event, String fxmlPath, String titulo) {
        try {
            // Cargar el archivo FXML de la nueva escena
            FXMLLoader loader = new FXMLLoader(GestionarMoverse.class.getResource(fxmlPath));
            Parent root = loader.load();

            // Obtener la ventana actual desde el botón que lanzó el evento y cerrarla
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();

            // Crear una nueva ventana (Stage) y asignar la nueva escena
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.setTitle(titulo);
            newStage.setResizable(false); // Desactiva redimensionamiento para mantener interfaz fija
            newStage.show();

        } catch (IOException e) {
            // Imprime el error si no se puede cargar el FXML
            e.printStackTrace();
        }
    }
}
