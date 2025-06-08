package org.example;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal que lanza la aplicación JavaFX.
 * Esta clase es la encargada de cargar la primera interfaz del juego
 * desde el archivo FXML correspondiente.
 */
public class Inicio extends Application{

    /**
     * Método que se ejecuta al iniciar la aplicación.
     * Carga la escena principal desde el archivo FXML y la muestra en pantalla.
     *
     * @param primaryStage Ventana principal de la aplicación.
     * @throws Exception Si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Cargar el archivo FXML que contiene la interfaz inicial
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/PantallaInicial.fxml"));

        // Crear la escena usando el nodo raíz cargado desde el FXML
        Scene scene = new Scene(root);

        // Configurar la ventana principal
        primaryStage.setTitle("WUTHERING WAVES CREADO POR DAVID");   // Título de la ventana
        primaryStage.setScene(scene);                                // Establecer la escena
        primaryStage.setResizable(false);                            // Desactivar redimensionamiento
        primaryStage.show();                                         // Mostrar la ventana
    }

    /**
     * Método principal que lanza la aplicación.
     *
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        launch(args);   // Llama al método start() e inicia JavaFX
    }
}
