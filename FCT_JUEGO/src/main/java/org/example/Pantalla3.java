package org.example;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.HashMap;
import java.util.Map;

public class Pantalla3 {

    @FXML
    public void irAInicio(ActionEvent event) {
        GestionarMoverse.cambiarEscena(event, "/org/example/Pantalla1.fxml", "Pantalla Inicio");
    }

    @FXML
    public void irAInventario(ActionEvent event) {
        GestionarMoverse.cambiarEscena(event, "/org/example/Pantalla2.fxml", "Pantalla de Inventario");
    }

    private static Map<String, Arma> armas;
    private static Map<String, Objeto> objetos;

    @FXML private Button botonAdquirirEspadon;
    @FXML private Button botonAdquirirEspada;
    @FXML private Button botonAdquirirPistola;
    @FXML private Button botonAdquirirGuantes;
    @FXML private Button botonAdquirirRectificador;
    @FXML private Button botonAdquirirObjeto1;
    @FXML private Button botonAdquirirObjeto2;
    @FXML private Button botonAdquirirObjeto3;
    @FXML private Button botonAdquirirObjeto4;
    @FXML private Button botonAdquirirObjeto5;
    @FXML private TextArea Estadisticas_Espadon;
    @FXML private TextArea Estadisticas_Espada;
    @FXML private TextArea Estadisticas_Pistola;
    @FXML private TextArea Estadisticas_Guantes;
    @FXML private TextArea Estadisticas_Rectificador;
    @FXML private TextArea Estadisticas_Objeto1;
    @FXML private TextArea Estadisticas_Objeto2;
    @FXML private TextArea Estadisticas_Objeto3;
    @FXML private TextArea Estadisticas_Objeto4;
    @FXML private TextArea Estadisticas_Objeto5;
    @FXML private Label label_dineroActual;
    @FXML private void botonAdquirirEspadon(ActionEvent event) {
        comprarArma("Arma1");
    }
    @FXML private void botonAdquirirEspada(ActionEvent event) {
        comprarArma("Arma2");
    }
    @FXML private void botonAdquirirPistola(ActionEvent event) {
        comprarArma("Arma3");
    }
    @FXML private void botonAdquirirGuantes(ActionEvent event) {
        comprarArma("Arma4");
    }
    @FXML private void botonAdquirirRectificador(ActionEvent event) {
        comprarArma("Arma5");
    }
    @FXML private void botonAdquirirObjeto1(ActionEvent event) {
        comprarObjeto("Objeto1");
    }
    @FXML private void botonAdquirirObjeto2(ActionEvent event) {
        comprarObjeto("Objeto2");
    }
    @FXML private void botonAdquirirObjeto3(ActionEvent event) {
        comprarObjeto("Objeto3");
    }
    @FXML private void botonAdquirirObjeto4(ActionEvent event) {
        comprarObjeto("Objeto4");
    }
    @FXML private void botonAdquirirObjeto5(ActionEvent event) {
        comprarObjeto("Objeto5");
    }

    private Cuenta cuentaUsuario = Pantalla1.getCuentaUsuario();

    @FXML
    public void initialize(){
        inicializarTienda();
        asignarEstadisticas();
        ocultarBotonesComprados();
        label_dineroActual.textProperty().bind(Bindings.format("%, d", cuentaUsuario.getCantidad_concha_caparazon()));
    }

    private void inicializarTienda(){
        inicializarArmas();
        inicializarObjetos();
    }

    private void inicializarObjetos(){
        objetos = new HashMap<>();
        objetos.put("Objeto1", new Objeto("Objeto1", 800, 15000, 500, 10000));
        objetos.put("Objeto2", new Objeto("Objeto2", 3000, 5000, 700, 16000));
        objetos.put("Objeto3", new Objeto("Objeto3", 900, 4000, 7000, 13000));
        objetos.put("Objeto4", new Objeto("Objeto4", 1200, 8000, 900, 12500));
        objetos.put("Objeto5", new Objeto("Objeto5", 2500, 3000, 5000, 18000));
    }

    private void inicializarArmas(){
        armas = new HashMap<>();
        armas.put("Arma1", new Arma("Arma1", 5000, 2000, 300, 18000));
        armas.put("Arma2", new Arma("Arma2", 2500, 3000, 500, 17000));
        armas.put("Arma3", new Arma("Arma3", 1000, 9000, 400, 10000));
        armas.put("Arma4", new Arma("Arma4", 5300, 1000, 200, 19000));
        armas.put("Arma5", new Arma("Arma5", 900, 15000, 2000, 17000));
    }

    private void asignarEstadisticas(){
        Estadisticas_Espadon.setText(armas.get("Arma1").obtenerEstadisticas());
        Estadisticas_Espada.setText(armas.get("Arma2").obtenerEstadisticas());
        Estadisticas_Pistola.setText(armas.get("Arma3").obtenerEstadisticas());
        Estadisticas_Guantes.setText(armas.get("Arma4").obtenerEstadisticas());
        Estadisticas_Rectificador.setText(armas.get("Arma5").obtenerEstadisticas());
        Estadisticas_Objeto1.setText(objetos.get("Objeto1").obtenerEstadisticas());
        Estadisticas_Objeto2.setText(objetos.get("Objeto2").obtenerEstadisticas());
        Estadisticas_Objeto3.setText(objetos.get("Objeto3").obtenerEstadisticas());
        Estadisticas_Objeto4.setText(objetos.get("Objeto4").obtenerEstadisticas());
        Estadisticas_Objeto5.setText(objetos.get("Objeto5").obtenerEstadisticas());
    }

    private void mostrarMensaje(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    private void comprarArma(String nombreArma) {
        Arma arma = armas.get(nombreArma);
        if (arma != null) {
            if (cuentaUsuario.comprarArma(arma)) {
                mostrarMensaje("Compra Exitosa", "Has comprado " + arma.getNombre() + " por " + arma.getCoste() + " monedas.");
                ocultarBotonArma(arma.getNombre());
            } else {
                mostrarMensaje("Compra Fallida", "No tienes suficiente dinero para comprar " + arma.getNombre() + ".");
            }
        } else {
            mostrarMensaje("Error", "El " + nombreArma + " no existe.");
        }
    }

    private void ocultarBotonArma(String nombreArma) {
        switch (nombreArma) {
            case "Arma1": botonAdquirirEspadon.setVisible(false); Estadisticas_Espadon.setText("ADQUIRIDO"); break;
            case "Arma2": botonAdquirirEspada.setVisible(false); Estadisticas_Espada.setText("ADQUIRIDO"); break;
            case "Arma3": botonAdquirirPistola.setVisible(false); Estadisticas_Pistola.setText("ADQUIRIDO"); break;
            case "Arma4": botonAdquirirGuantes.setVisible(false); Estadisticas_Guantes.setText("ADQUIRIDO"); break;
            case "Arma5": botonAdquirirRectificador.setVisible(false); Estadisticas_Rectificador.setText("ADQUIRIDO"); break;
        }
    }

    private void comprarObjeto(String nombreObjeto) {
        Objeto objeto = objetos.get(nombreObjeto);
        if (objeto != null) {
            if (cuentaUsuario.comprarObjeto(objeto)) {
                mostrarMensaje("Compra Exitosa", "Has comprado " + objeto.getNombre() + " por " + objeto.getCoste() + " monedas.");
                ocultarBotonObjeto(nombreObjeto);
            } else {
                mostrarMensaje("Compra Fallida", "No tienes suficiente dinero para comprar " + objeto.getNombre() + ".");
            }
        } else {
            mostrarMensaje("Error", "El " + nombreObjeto + " no existe.");
        }
    }

    private void ocultarBotonObjeto(String nombreObjeto) {
        switch (nombreObjeto) {
            case "Objeto1": botonAdquirirObjeto1.setVisible(false); Estadisticas_Objeto1.setText("ADQUIRIDO"); break;
            case "Objeto2": botonAdquirirObjeto2.setVisible(false); Estadisticas_Objeto2.setText("ADQUIRIDO"); break;
            case "Objeto3": botonAdquirirObjeto3.setVisible(false); Estadisticas_Objeto3.setText("ADQUIRIDO"); break;
            case "Objeto4": botonAdquirirObjeto4.setVisible(false); Estadisticas_Objeto4.setText("ADQUIRIDO"); break;
            case "Objeto5": botonAdquirirObjeto5.setVisible(false); Estadisticas_Objeto5.setText("ADQUIRIDO"); break;
        }
    }

    private void ocultarBotonesComprados() {
        for (Arma arma : cuentaUsuario.getArmasObtenidas()) {
            switch (arma.getNombre()) {
                case "Arma1": botonAdquirirEspadon.setVisible(false); Estadisticas_Espadon.setText("ADQUIRIDO"); break;
                case "Arma2": botonAdquirirEspada.setVisible(false); Estadisticas_Espada.setText("ADQUIRIDO"); break;
                case "Arma3": botonAdquirirPistola.setVisible(false); Estadisticas_Pistola.setText("ADQUIRIDO"); break;
                case "Arma4": botonAdquirirGuantes.setVisible(false); Estadisticas_Guantes.setText("ADQUIRIDO"); break;
                case "Arma5": botonAdquirirRectificador.setVisible(false); Estadisticas_Rectificador.setText("ADQUIRIDO"); break;
            }
        }

        for (Objeto objeto : cuentaUsuario.getObjetosObtenidos()) {
            switch (objeto.getNombre()) {
                case "Objeto1": botonAdquirirObjeto1.setVisible(false); Estadisticas_Objeto1.setText("ADQUIRIDO"); break;
                case "Objeto2": botonAdquirirObjeto2.setVisible(false); Estadisticas_Objeto2.setText("ADQUIRIDO"); break;
                case "Objeto3": botonAdquirirObjeto3.setVisible(false); Estadisticas_Objeto3.setText("ADQUIRIDO"); break;
                case "Objeto4": botonAdquirirObjeto4.setVisible(false); Estadisticas_Objeto4.setText("ADQUIRIDO"); break;
                case "Objeto5": botonAdquirirObjeto5.setVisible(false); Estadisticas_Objeto5.setText("ADQUIRIDO"); break;
            }
        }
    }


}
