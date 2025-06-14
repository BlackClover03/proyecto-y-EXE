package org.example;
import java.util.List;
import java.util.Random;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controlador de la Pantalla4 del juego.
 * Gestiona el combate por turnos entre el personaje del jugador y un enemigo,
 * incluyendo ataques, habilidades, turnos, recompensas y transición entre pantallas.
 *
 * Esta clase carga las estadísticas del jugador y del enemigo, administra la interfaz gráfica
 * y la lógica de combate, así como la gestión de recompensas al finalizar cada sala.
 */
public class Pantalla4 {
    private Cuenta cuentaUsuario = Pantalla1.getCuentaUsuario();
    private String nombreSala = "Sala 1";
    private String EstadoMazmorra = "perdida";
    private int recompensa;
    private int turnosCombate = 1;

    private int ataquetotalPJ;
    private int defensatotalPJ;
    private int vidatotalPJ;
    private int puntosCargaUltimatePJ = 0;
    private int CDHabilidadElementalPJ = 0;
    private String Armaequipada;
    private int vidaMaximaPJ;

    private Enemigo enemigoactual;
    private int ataquetotalE;
    private int defensatotalE;
    private int vidatotalE;
    private int puntosCargaUltimateE = 0;
    private int CDHabilidadElementalE = 0;
    private int vidaMaximaE;

    @FXML private Label labelDineroTotalGanado;
    @FXML private Label labelTurnoActual;
    @FXML private Label labelVidaPJ;
    @FXML private ProgressBar barraVidaPJ;
    @FXML private Label labelVidaEnemigo;
    @FXML private ProgressBar barraVidaEnemigo;
    @FXML private ImageView ImagenPJ;
    @FXML private ImageView ImagenEnemigo;
    @FXML private ImageView fondoSala;
    @FXML private Button btn_ataqueBasico;
    @FXML private Button btn_habilidadElemental;
    @FXML private Button btn_ultimate;
    @FXML private Button btn_salirDeMazmorra;
    @FXML private Label labelpuntosultimate;
    @FXML private TextArea Texto_Combate;
    @FXML private Label labelCD;

    /**
     * Método que se ejecuta al inicializar la pantalla.
     * Carga al enemigo y al personaje, configura la interfaz gráfica y asigna acciones a los botones.
     */
    @FXML
    public void initialize() {
        seleccionarYcargarEnemigo();
        CargarPersonaje();

        seleccionarImagenPJ();
        seleccionarImagenEnemigo();

        labelDineroTotalGanado.setText("DINERO TOTAL GANADO: " + recompensa);
        labelVidaPJ.setText("VIDA: " + vidatotalPJ + "/" + vidaMaximaPJ);
        labelVidaEnemigo.setText("VIDA: " + vidatotalE + "/" + vidaMaximaE);
        barraVidaPJ.setProgress((double) vidatotalPJ / vidaMaximaPJ);
        barraVidaEnemigo.setProgress((double) vidatotalE / vidaMaximaE);

        btn_salirDeMazmorra.setOnAction(this::salirDeMazmorra);
        btn_ataqueBasico.setOnAction(this::eventoAtaqueBasico);
        btn_habilidadElemental.setOnAction(this::eventoHabilidadElemental);
        btn_ultimate.setOnAction(this::eventoUltimatePJ);

        ReiniciarDatosSalas();
        actualizarFondoSala();
        actualizarTurno();
        actualizarLabelPuntosUltimatePJ();
        actualizarLabelCD();
    }

    /**
     * Carga las estadísticas base del enemigo seleccionado.
     *
     * @param original El enemigo del que se copiarán las estadísticas.
     */
    public void cargarEstadisticasEnemigo(Enemigo original) {
        this.ataquetotalE = original.getAtaqueE();
        this.defensatotalE = original.getDefensaE();
        this.vidatotalE = original.getVidaE();
        this.vidaMaximaE = vidatotalE;
        this.puntosCargaUltimateE = original.getPuntosCargaUltimateE();
    }

    /**
     * Selecciona aleatoriamente un enemigo según la sala actual y lo carga.
     */
    public void seleccionarYcargarEnemigo() {
        List<Enemigo> lista = null;

        switch (nombreSala) {
            case "Sala 1":
                lista = Enemigo.DatosEnemigos.EnemigosSala1;
                break;
            case "Sala 2":
                lista = Enemigo.DatosEnemigos.EnemigosSala2;
                break;
            case "Sala 3":
                lista = Enemigo.DatosEnemigos.EnemigosSala3;
                break;
            case "Sala Jefe":
                lista = Enemigo.DatosEnemigos.EnemigosSalaJefe;
                break;
            default:
                System.out.println("Sala desconocida: " + nombreSala);
                return;
        }

        if (lista != null && !lista.isEmpty()) {
            enemigoactual = lista.get(new Random().nextInt(lista.size()));
            cargarEstadisticasEnemigo(enemigoactual);
        }
    }

    /**
     * Carga y suma las estadísticas del personaje, arma y objeto seleccionados por el jugador.
     */
    public void CargarPersonaje() {
        Cuenta cuenta = Pantalla1.getCuentaUsuario();

        Personaje personaje = cuenta.getPersonaje();
        Arma arma = cuenta.getArmaSeleccionadaObjeto();
        Objeto objeto = cuenta.getObjetoSeleccionadoObjeto();

        int ataquePersonaje = personaje != null ? personaje.getAtaque() : 0;
        int defensaPersonaje = personaje != null ? personaje.getDefensa() : 0;
        int vidaPersonaje = personaje != null ? personaje.getVida() : 0;

        int ataqueArma = arma != null ? arma.getAtaque() : 0;
        int defensaArma = arma != null ? arma.getDefensa() : 0;
        int vidaArma = arma != null ? arma.getVida() : 0;

        int ataqueObjeto = objeto != null ? objeto.getAtaque() : 0;
        int defensaObjeto = objeto != null ? objeto.getDefensa() : 0;
        int vidaObjeto = objeto != null ? objeto.getVida() : 0;

        ataquetotalPJ = ataquePersonaje + ataqueArma + ataqueObjeto;
        defensatotalPJ = defensaPersonaje + defensaArma + defensaObjeto;
        vidatotalPJ = vidaPersonaje + vidaArma + vidaObjeto;
        vidaMaximaPJ = vidatotalPJ;

        Armaequipada = arma != null ? arma.getNombre() : "Sin arma";
    }

    /**
     * Actualiza la etiqueta y la barra de vida del personaje en la interfaz.
     *
     * @param nuevaVida Nueva cantidad de vida del personaje.
     */
    public void actualizarVidaPJ(int nuevaVida) {
        this.vidatotalPJ = Math.max(nuevaVida, 0);
        labelVidaPJ.setText("VIDA: " + vidatotalPJ + "/" + vidaMaximaPJ);
        barraVidaPJ.setProgress((double) vidatotalPJ / vidaMaximaPJ);
    }

    /**
     * Actualiza la etiqueta y la barra de vida del enemigo en la interfaz.
     *
     * @param nuevaVida Nueva cantidad de vida del enemigo.
     */
    public void actualizarVidaEnemigo(int nuevaVida) {
        this.vidatotalE = Math.max(nuevaVida, 0);
        labelVidaEnemigo.setText("VIDA: " + vidatotalE + "/" + vidaMaximaE);
        barraVidaEnemigo.setProgress((double) vidatotalE / vidaMaximaE);
    }

    /**
     * Cambia la imagen de fondo según la sala actual.
     */
    public void actualizarFondoSala() {
        String nombreImagen;
        switch (nombreSala) {
            case "Sala 1":
                nombreImagen = "sala1.png";
                break;
            case "Sala 2":
                nombreImagen = "sala2.png";
                break;
            case "Sala 3":
                nombreImagen = "sala3.png";
                break;
            case "Sala Jefe":
                nombreImagen = "salajefe.png";
                break;
            default:
                nombreImagen = "sala1.png";
                break;
        }

        try {
            fondoSala.setImage(new javafx.scene.image.Image(getClass().getResource("/Imagenes/" + nombreImagen).toExternalForm()));
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen de fondo: " + nombreImagen);
        }
    }

    /**
     * Reinicia los datos del combate entre salas, como los turnos y los cooldowns.
     */
    public void ReiniciarDatosSalas() {
        turnosCombate = 1;
        CDHabilidadElementalPJ = 0;
        puntosCargaUltimatePJ = 0;
        CDHabilidadElementalE = 0;
        puntosCargaUltimateE = 0;
        if (Texto_Combate != null) {
            Texto_Combate.setText("");
        }
    }

    /**
     * Establece la imagen del personaje jugador en la interfaz.
     */
    public void seleccionarImagenPJ(){
        try {
            ImagenPJ.setImage(new javafx.scene.image.Image(
                    getClass().getResource("/Imagenes/imagenPJprincipal.png").toExternalForm()));
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen del personaje.");
        }
    }

    /**
     * Establece la imagen del enemigo actual en la interfaz.
     */
    public void seleccionarImagenEnemigo() {
        try {
            String nombreEnemigo = enemigoactual.getNombreE();
            String nombreArchivo = nombreEnemigo + ".png";
            ImagenEnemigo.setImage(new javafx.scene.image.Image(
                    getClass().getResource("/Imagenes/" + nombreArchivo).toExternalForm()));
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen del enemigo.");
        }
    }

    /**
     * Comprueba el estado del combate para saber si ha terminado.
     *
     * @return "victoria" si el enemigo fue derrotado, "derrota" si el jugador fue derrotado,
     *         o "combate" si sigue activo.
     */
    public String comprobarEstadoCombate() {
        if (vidatotalE <= 0) {
            return "victoria";
        } else if (vidatotalPJ <= 0) {
            return "derrota";
        }
        return "combate";
    }

    /**
     * Calcula y otorga la recompensa basada en el resultado del combate.
     *
     * @param resultadoCombate El resultado del combate ("victoria" o "derrota").
     */
    private void gestionarRecompensa(String resultadoCombate) {
        Random rand = new Random();
        if (resultadoCombate.equals("victoria")) {
            int recompensaGanada = 0;

            switch (nombreSala) {
                case "Sala 1":
                case "Sala 2":
                case "Sala 3":
                    recompensaGanada = rand.nextInt(3501) + 500;
                    break;
                case "Sala Jefe":
                    recompensaGanada = rand.nextInt(5001) + 1000;
                    break;
            }

            if (EstadoMazmorra.equals("ganada")) {
                cuentaUsuario.getCantidad_concha_caparazon().set(
                        cuentaUsuario.getCantidad_concha_caparazon().get() + recompensaGanada
                );
                EstadoMazmorra = "perdida";
            }
            recompensa += recompensaGanada;
            labelDineroTotalGanado.setText("Dinero total ganado: " + recompensa);

        } else if (resultadoCombate.equals("derrota")) {
            int dineroFinal = (int)(recompensa * 0.6);
            cuentaUsuario.getCantidad_concha_caparazon().set(
                    cuentaUsuario.getCantidad_concha_caparazon().get() + dineroFinal
            );
            System.out.println("Has sido derrotado. Se añade a la cuenta un 60% de la recompensa: " + dineroFinal);
            labelDineroTotalGanado.setText("Dinero total ganado: " + recompensa);
        }
    }

    /**
     * Gestiona el flujo tras finalizar el combate y realiza la transición de pantalla si es necesario.
     *
     * @param event Evento de acción para identificar el origen de la llamada.
     */
    public void gestionarResultadoCombate(javafx.event.ActionEvent event) {
        String resultado = comprobarEstadoCombate();
        gestionarRecompensa(resultado);
        if (resultado.equals("victoria")) {
            switch (nombreSala) {
                case "Sala 1":
                    nombreSala = "Sala 2";
                    break;
                case "Sala 2":
                    nombreSala = "Sala 3";
                    break;
                case "Sala 3":
                    nombreSala = "Sala Jefe";
                    break;
                case "Sala Jefe":
                    EstadoMazmorra = "ganada";
                    try {

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/Pantalla5.fxml"));
                        Parent root = loader.load();

                        Pantalla5 controller = loader.getController();
                        controller.setDatos(recompensa, "VICTORIA");

                        Scene scene = new Scene(root);
                        Stage stage;
                        if (event != null) {
                            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        } else {
                            stage = (Stage) barraVidaPJ.getScene().getWindow();
                        }
                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.show();
                    } catch (Exception e) {
                        System.out.println("Error al cargar Pantalla5.fxml: " + e.getMessage());
                        e.printStackTrace();
                    }
                    return;
            }
            seleccionarYcargarEnemigo();
            actualizarFondoSala();
            initialize();
        } else if (resultado.equals("derrota")) {
            try {
                int dineroFinal = (int)(recompensa * 0.6);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/Pantalla5.fxml"));
                Parent root = loader.load();

                Pantalla5 controller = loader.getController();
                controller.setDatos(dineroFinal, "DERROTA");

                Scene scene = new Scene(root);
                Stage stage;
                if (event != null) {
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                } else {
                    stage = (Stage) barraVidaPJ.getScene().getWindow();
                }
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                System.out.println("Error al cargar Pantalla5.fxml: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private boolean enemigoEnTurno = false;

    /**
     * Actualiza el turno en la interfaz, determina si es el turno del jugador o del enemigo,
     * y activa la lógica correspondiente.
     */
    private void actualizarTurno() {
        labelTurnoActual.setText("Turno actual: " + turnosCombate);

        if (turnosCombate % 2 == 1) {
            enemigoEnTurno = false;
            Texto_Combate.appendText("TURNO " + turnosCombate + "\n");
            Texto_Combate.appendText("ES TU TURNO\n");
            btn_ataqueBasico.setVisible(true);
            btn_habilidadElemental.setVisible(true);
            btn_ultimate.setVisible(true);
        } else if (!enemigoEnTurno) {
            enemigoEnTurno = true;
            btn_ataqueBasico.setVisible(false);
            btn_habilidadElemental.setVisible(false);
            btn_ultimate.setVisible(false);
            turnoEnemigo();
        }
    }

    /**
     * Ejecuta el turno del enemigo, elige un ataque y lo aplica después de una pausa.
     */
    private void turnoEnemigo() {
        Texto_Combate.appendText("TURNO " + turnosCombate + "\n");
        Texto_Combate.appendText("ES TURNO DEL ENEMIGO...\n");

        PauseTransition pausa1 = new PauseTransition(Duration.seconds(3));
        pausa1.setOnFinished(event1 -> {
            int ataqueSeleccionado = enemigoactual.EleccionEnemigo(CDHabilidadElementalE, puntosCargaUltimateE);
            String tipoAtaque;
            switch (ataqueSeleccionado) {
                case 1: tipoAtaque = "ataque básico"; break;
                case 2: tipoAtaque = "habilidad elemental"; break;
                case 3: tipoAtaque = "ultimate"; break;
                default: tipoAtaque = "ataque desconocido"; break;
            }
            final String finalTipoAtaque = tipoAtaque;
            Texto_Combate.appendText("El enemigo va a usar: " + finalTipoAtaque + "...\n");

            PauseTransition pausa2 = new PauseTransition(Duration.seconds(3));
            pausa2.setOnFinished(event2 -> {
                int danio = 0;
                if (ataqueSeleccionado == 1) {
                    danio = enemigoactual.ataque_Basico(ataquetotalE, defensatotalPJ);
                    if (CDHabilidadElementalE > 0) CDHabilidadElementalE--;
                    if (puntosCargaUltimateE < 3) puntosCargaUltimateE++;
                } else if (ataqueSeleccionado == 2) {
                    danio = enemigoactual.habilidad_elemental(ataquetotalE, defensatotalPJ);
                    CDHabilidadElementalE = 2;
                    if (puntosCargaUltimateE < 3) puntosCargaUltimateE++;
                } else if (ataqueSeleccionado == 3) {
                    danio = enemigoactual.ultimate(ataquetotalE, defensatotalPJ);
                    puntosCargaUltimateE = 0;
                }

                registrarAccionCombate("Enemigo", finalTipoAtaque, danio);
                int nuevaVidaPJ = vidatotalPJ - danio;
                actualizarVidaPJ(nuevaVidaPJ);
                turnosCombate++;

                String resultado = comprobarEstadoCombate();
                if (resultado.equals("derrota")) {
                    Texto_Combate.appendText("Has sido derrotado.\n");
                    PauseTransition pausa3 = new PauseTransition(Duration.seconds(3));
                    pausa3.setOnFinished(event3 -> {
                        gestionarResultadoCombate(null);
                    });
                    pausa3.play();
                } else {
                    actualizarTurno();
                }
            });
            pausa2.play();
        });
        pausa1.play();
    }

    /**
     * Ejecuta la lógica del ataque básico del personaje jugador.
     * Calcula el daño, actualiza la vida del enemigo, registra la acción y gestiona el turno y estado del combate.
     *
     * @param event El evento de acción que desencadena este ataque.
     */
    private void eventoAtaqueBasico(ActionEvent event) {
        Cuenta cuenta = Pantalla1.getCuentaUsuario();
        Personaje personaje = cuenta.getPersonaje();
        int danio = personaje.ataque_Basico(Armaequipada, ataquetotalPJ, defensatotalE);
        int nuevaVidaEnemigo = vidatotalE - danio;
        actualizarVidaEnemigo(nuevaVidaEnemigo);
        registrarAccionCombate(cuentaUsuario.getNombre(), "ataque basico", danio);
        turnosCombate++;
        puntosCargaUltimatePJ ++;
        actualizarLabelPuntosUltimatePJ();
        if (CDHabilidadElementalPJ > 0) {
            CDHabilidadElementalPJ--;
        }
        actualizarLabelCD();

        String resultado = comprobarEstadoCombate();
        if (resultado.equals("victoria")) {
            Texto_Combate.appendText("Has derrotado al enemigo!\n");
            PauseTransition pausa4 = new PauseTransition(Duration.seconds(5));
            pausa4.setOnFinished(event1 -> {
                gestionarResultadoCombate(event);
            });
            pausa4.play();
        } else {
            actualizarTurno();
        }
    }

    /**
     * Ejecuta la habilidad elemental del personaje jugador, si no está en tiempo de espera.
     * Aplica daño al enemigo, actualiza la interfaz, registra la acción y gestiona el turno y el estado del combate.
     * Si la habilidad aún está en cooldown, muestra un mensaje en el registro de combate.
     *
     * @param event El evento de acción que desencadena esta habilidad.
     */
    private void eventoHabilidadElemental(ActionEvent event) {
        Cuenta cuenta = Pantalla1.getCuentaUsuario();
        Personaje personaje = cuenta.getPersonaje();
        if (CDHabilidadElementalPJ == 0) {
            int danio = personaje.habilidad_elemental(Armaequipada, ataquetotalPJ, defensatotalE);
            int nuevaVidaEnemigo = vidatotalE - danio;
            actualizarVidaEnemigo(nuevaVidaEnemigo);
            registrarAccionCombate(cuentaUsuario.getNombre(), "habilidad elemental", danio);
            CDHabilidadElementalPJ = 2;
            turnosCombate++;
            puntosCargaUltimatePJ ++;
            actualizarLabelPuntosUltimatePJ();
            actualizarLabelCD();

            String resultado = comprobarEstadoCombate();
            if (resultado.equals("victoria")) {
                Texto_Combate.appendText("Has derrotado al enemigo!\n");
                PauseTransition pausa5 = new PauseTransition(Duration.seconds(5));
                pausa5.setOnFinished(event1 -> {
                    gestionarResultadoCombate(event);
                });
                pausa5.play();
            } else {
                actualizarTurno();
            }
        } else{
            String mensaje = "La Habilidad Elemental esta en espera de poder utilizarse, usa otro ataque.\n";
            Texto_Combate.appendText(mensaje);
        }
    }

    /**
     * Ejecuta el ataque especial "Ultimate" del personaje jugador, si ha acumulado suficientes puntos de carga.
     * Reinicia los puntos de carga tras el uso, actualiza la interfaz, registra la acción y verifica el estado del combate.
     * Si no se ha alcanzado el número necesario de puntos de carga, muestra un mensaje al jugador.
     *
     * @param event El evento de acción que desencadena el uso de la ultimate.
     */
    private void eventoUltimatePJ(ActionEvent event) {
        Cuenta cuenta = Pantalla1.getCuentaUsuario();
        Personaje personaje = cuenta.getPersonaje();
        if (puntosCargaUltimatePJ == 3){
            int danio = personaje.ultimate(Armaequipada, ataquetotalPJ, defensatotalE);
            int nuevaVidaEnemigo = vidatotalE - danio;
            actualizarVidaEnemigo(nuevaVidaEnemigo);
            registrarAccionCombate(cuentaUsuario.getNombre(), "ultimate", danio);
            turnosCombate++;
            puntosCargaUltimatePJ = 0;
            actualizarLabelPuntosUltimatePJ();

            String resultado = comprobarEstadoCombate();
            if (resultado.equals("victoria")) {
                Texto_Combate.appendText("Has derrotado al enemigo!\n");
                PauseTransition pausa6 = new PauseTransition(Duration.seconds(5));
                pausa6.setOnFinished(event1 -> {
                    gestionarResultadoCombate(event);
                });
                pausa6.play();
            } else {
                actualizarTurno();
            }
        }else{
            String mensaje = "La Ultimate esta en espera de poder utilizarse, usa otro ataque.\n";
            Texto_Combate.appendText(mensaje);
        }
    }

    /**
     * Permite al jugador salir de la mazmorra antes de terminarla.
     * Muestra una ventana de confirmación y, si se acepta, cambia la escena a la pantalla inicial.
     *
     * @param event El evento de acción que desencadena esta salida.
     */
    @FXML
    private void salirDeMazmorra(ActionEvent event) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("¿Estás seguro de que quieres abandonar la mazmorra?");
        alert.setContentText("Si sales ahora, perderás todo el progreso hecho hasta este momento.");

        javafx.scene.control.ButtonType botonAceptar = new javafx.scene.control.ButtonType("Aceptar");
        javafx.scene.control.ButtonType botonCancelar = new javafx.scene.control.ButtonType("Cancelar", javafx.scene.control.ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(botonAceptar, botonCancelar);

        alert.showAndWait().ifPresent(respuesta -> {
            if (respuesta == botonAceptar) {
                try {
                    GestionarMoverse.cambiarEscena(event, "/org/example/Pantalla1.fxml", "Pantalla Inicio");
                } catch (Exception e) {
                    System.out.println("Error al volver a Pantalla1: " + e.getMessage());
                }
            }
        });
    }

    /**
     * Actualiza la etiqueta que muestra los puntos de carga de la ultimate del personaje.
     * Limita el máximo de puntos de carga a 3.
     */
    private void actualizarLabelPuntosUltimatePJ() {
        if (puntosCargaUltimatePJ > 3) {
            puntosCargaUltimatePJ = 3;
        }
        labelpuntosultimate.setText("PUNTOS ACTUALES: " + puntosCargaUltimatePJ + "/3");
    }

    /**
     * Actualiza la etiqueta que indica el cooldown (tiempo de espera) restante para volver a usar la habilidad elemental.
     * Limita el valor máximo del cooldown a 2 turnos.
     */
    private void actualizarLabelCD(){
        if (CDHabilidadElementalPJ > 2){
            CDHabilidadElementalPJ = 2;
        }
        labelCD.setText("TIEMPO ESPERA: " + CDHabilidadElementalPJ + "/2 TURNOS");
    }

    /**
     * Registra una acción en el área de texto del combate.
     * Muestra el tipo de ataque realizado, el nombre del atacante y el daño causado.
     *
     * @param nombreAtacante Nombre del personaje que ha realizado el ataque.
     * @param tipoAtaque Tipo de ataque usado (por ejemplo: "ataque básico", "habilidad elemental", "ultimate").
     * @param danhoRealizado Daño total infligido al enemigo.
     */
    public void registrarAccionCombate(String nombreAtacante, String tipoAtaque, int danhoRealizado) {
        String mensaje = nombreAtacante + " ha usado " + tipoAtaque + " y ha hecho " + danhoRealizado + " de daño.\n";
        Texto_Combate.appendText(mensaje);
    }
}
