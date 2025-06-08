package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;

/**
 * Clase {@code Pantalla2} controladora de la pantalla de Inventario.
 *
 * Permite al usuario visualizar y seleccionar armas y objetos equipables,
 * y muestra las estadísticas actualizadas del personaje.
 */
public class Pantalla2 {

    /**
     * Cambia la escena a la pantalla de inicio.
     * @param event Evento que desencadena el cambio.
     */
    @FXML
    public void irAInicio(ActionEvent event) {
        GestionarMoverse.cambiarEscena(event, "/org/example/Pantalla1.fxml", "Pantalla Inicio");
    }

    /**
     * Cambia la escena a la pantalla de tienda.
     * @param event Evento que desencadena el cambio.
     */
    @FXML
    public void irATienda(ActionEvent event) {
        GestionarMoverse.cambiarEscena(event, "/org/example/Pantalla3.fxml", "Pantalla de la Tienda");
    }

    //Elementos del fxml traducidos al controlador para usarse
    @FXML private Label label_nombre;
    @FXML private TextArea Estadisticas_personaje;
    @FXML private ComboBox<String> selectorArma;
    @FXML private ComboBox<String> selectorObjeto;
    @FXML private ImageView Plantilla_Arma;
    @FXML private ImageView Plantilla_Objeto;

    /**
     * Cuenta del usuario actual.
     */
    Cuenta cuenta = Pantalla1.getCuentaUsuario();

    /**
     * Personaje del usuario actual.
     */
    Personaje personaje = cuenta.getPersonaje();

    /**
     * Inicializa la interfaz al cargar la pantalla.
     * Configura nombre, estadísticas, selectores e imágenes.
     */
    @FXML
    private void initialize() {
        label_nombre.setText(Pantalla1.getCuentaUsuario().getNombre());
        inicializar_estadisticas_base();
        inicializar_selectores();
        configurar_selectores();
        actualizarEstadisticasConEquipo();
    }

    /**
     * Muestra las estadísticas base del personaje sin equipo aplicado.
     */
    public void inicializar_estadisticas_base(){
        if (personaje != null) {
            Estadisticas_personaje.setText("ESTADÍSTICAS TOTALES\n" +
                    "Ataque: " + personaje.getAtaque() + "\n" +
                    "Defensa: " + personaje.getDefensa() + "\n" +
                    "Vida: " + personaje.getVida());
        }
    }

    /**
     * Inicializa los ComboBox con las armas y objetos obtenidos por el usuario.
     * También selecciona y muestra el equipo actualmente guardado.
     */
    public void inicializar_selectores(){
        selectorArma.getItems().add("Ninguna");
        for (Arma arma : cuenta.getArmasObtenidas()) {
            selectorArma.getItems().add(arma.getNombre());
        }
        selectorObjeto.getItems().add("Ninguna");
        for (Objeto objeto : cuenta.getObjetosObtenidos()) {
            selectorObjeto.getItems().add(objeto.getNombre());
        }

        String armaGuardada = cuenta.getArmaSeleccionada();
        if (armaGuardada != null) {
            selectorArma.setValue(armaGuardada);
            Plantilla_Arma.setImage(new javafx.scene.image.Image(getClass().getResourceAsStream("/Imagenes/" + armaGuardada + ".png")));
        } else {
            selectorArma.setValue("Ninguna");
        }

        String objetoGuardado = cuenta.getObjetoSeleccionado();
        if (objetoGuardado != null) {
            selectorObjeto.setValue(objetoGuardado);
            Plantilla_Objeto.setImage(new javafx.scene.image.Image(getClass().getResourceAsStream("/Imagenes/" + objetoGuardado + ".png")));
        } else {
            selectorObjeto.setValue("Ninguna");
        }

    }

    /**
     * Configura las acciones a realizar cuando el usuario selecciona un arma u objeto.
     * Al cambiar la selección, actualiza la imagen y las estadísticas.
     */
    public void configurar_selectores() {
        selectorArma.setOnAction(event -> {
            String armaSeleccionada = selectorArma.getValue();
            if (armaSeleccionada != null && !armaSeleccionada.equals("Ninguna")) {
                cuenta.setArmaSeleccionada(armaSeleccionada);
                Plantilla_Arma.setImage(new javafx.scene.image.Image(getClass().getResourceAsStream("/Imagenes/" + armaSeleccionada + ".png")));
            } else {
                cuenta.setArmaSeleccionada(null);
                Plantilla_Arma.setImage(null);
            }
            actualizarEstadisticasConEquipo();
        });

        selectorObjeto.setOnAction(event -> {
            String objetoSeleccionado = selectorObjeto.getValue();
            if (objetoSeleccionado != null && !objetoSeleccionado.equals("Ninguna")) {
                cuenta.setObjetoSeleccionado(objetoSeleccionado);
                Plantilla_Objeto.setImage(new javafx.scene.image.Image(getClass().getResourceAsStream("/Imagenes/" + objetoSeleccionado + ".png")));
            } else {
                cuenta.setObjetoSeleccionado(null);
                Plantilla_Objeto.setImage(null);
            }
            actualizarEstadisticasConEquipo();
        });
    }

    /**
     * Actualiza las estadísticas del personaje sumando los valores del arma y objeto seleccionados.
     * Muestra el resultado en el TextArea correspondiente.
     */
    public void actualizarEstadisticasConEquipo() {
        int ataqueTotal = personaje.getAtaque();
        int defensaTotal = personaje.getDefensa();
        int vidaTotal = personaje.getVida();

        String armaSeleccionada = selectorArma.getValue();
        String objetoSeleccionado = selectorObjeto.getValue();

        if (armaSeleccionada != null) {
            for (Arma arma : cuenta.getArmasObtenidas()) {
                if (arma.getNombre().equals(armaSeleccionada)) {
                    ataqueTotal += arma.getAtaque();
                    defensaTotal += arma.getDefensa();
                    vidaTotal += arma.getVida();
                    break;
                }
            }
        }

        if (objetoSeleccionado != null) {
            for (Objeto objeto : cuenta.getObjetosObtenidos()) {
                if (objeto.getNombre().equals(objetoSeleccionado)) {
                    ataqueTotal += objeto.getAtaque();
                    defensaTotal += objeto.getDefensa();
                    vidaTotal += objeto.getVida();
                    break;
                }
            }
        }

        Estadisticas_personaje.setText("ESTADÍSTICAS TOTALES\n" +
                "Ataque: " + ataqueTotal + "\n" +
                "Defensa: " + defensaTotal + "\n" +
                "Vida: " + vidaTotal);
    }
}
