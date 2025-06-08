package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Controlador de la pantalla de explicación del juego.
 * Permite iniciar el juego solicitando el nombre del usuario o volver a la pantalla inicial.
 */
public class PantallaExplicacion {

    /**
     * Maneja el evento de iniciar el juego. Solicita el nombre del usuario
     * y, si es válido, crea una nueva Cuenta y cambia a la pantalla de selección de personaje.
     *
     * @param event Evento generado al presionar el botón "Iniciar Juego".
     */
    @FXML
    private void iniciarJuego(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Ingreso de Nombre");
        dialog.setHeaderText("Introduce tu nombre de usuario");
        dialog.setContentText("Nombre:");

        dialog.showAndWait().ifPresent(nombre -> {
            if (nombre != null && !nombre.trim().isEmpty()) {
                // Crear cuenta del usuario con el nombre ingresado
                Cuenta cuentausuario = new Cuenta(nombre);

                try {
                    // Cargar la pantalla de selección de personaje (Pantalla1)
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/Pantalla1.fxml"));
                    Parent root = loader.load();

                    // Obtener el controlador de Pantalla1 y pasarle la cuenta
                    Pantalla1 pantallaInicio = loader.getController();
                    pantallaInicio.setCuentaUsuario(cuentausuario);

                    // Cambiar de escena
                    Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    stage.close();

                    Stage newStage = new Stage();
                    newStage.setScene(new Scene(root));
                    newStage.setTitle("Pantalla Inicio");
                    newStage.setResizable(false);
                    newStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // Mostrar alerta si el nombre es inválido
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Nombre inválido");
                alert.setHeaderText("El nombre ingresado no es válido");
                alert.setContentText("Por favor, ingresa un nombre para continuar.");
                alert.showAndWait();
            }
        });
    }

    /**
     * Maneja el evento de cancelar. Vuelve a la pantalla inicial del juego.
     *
     * @param event Evento generado al presionar el botón "Cancelar".
     */
    @FXML
    private void cancelar(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/PantallaInicial.fxml"));
            AnchorPane root = loader.load();

            // Cerrar ventana actual y mostrar la inicial
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();

            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.setResizable(false);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
