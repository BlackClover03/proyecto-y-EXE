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

public class PantallaExplicacion {

    @FXML
    private void iniciarJuego(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Ingreso de Nombre");
        dialog.setHeaderText("Introduce tu nombre de usuario");
        dialog.setContentText("Nombre:");

        dialog.showAndWait().ifPresent(nombre -> {
            if (nombre != null && !nombre.trim().isEmpty()) {
                Cuenta cuentausuario = new Cuenta(nombre);

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/Pantalla1.fxml"));
                    Parent root = loader.load();

                    Pantalla1 pantallaInicio = loader.getController();
                    pantallaInicio.setCuentaUsuario(cuentausuario);

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
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Nombre inválido");
                alert.setHeaderText("El nombre ingresado no es válido");
                alert.setContentText("Por favor, ingresa un nombre para continuar.");
                alert.showAndWait();
            }
        });
    }

    @FXML
    private void cancelar(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/PantallaInicial.fxml"));
            AnchorPane root = loader.load();

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
