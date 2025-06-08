package org.example;

/**
 * La clase {@code Personaje} representa a un personaje jugable en el sistema de combate del juego.
 *
 * Contiene atributos básicos como ataque, defensa, vida y puntos de carga de la habilidad definitiva.
 * Además, proporciona métodos para calcular el daño infligido mediante diferentes tipos de ataques.
 */
public class Personaje {
    int ataque = 700;
    int defensa = 200;
    int vida = 1500;
    int PuntosCargaUltimate = 0;

    /**
     * Constructor por defecto que inicializa un personaje con estadísticas base.
     */
    public Personaje() {}

    // Getters
    public int getAtaque() {
        return ataque;
    }
    public int getDefensa() {
        return defensa;
    }
    public int getVida() {
        return vida;
    }
    public int getPuntosCargaUltimate() {
        return PuntosCargaUltimate;
    }

    //Setters
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }
    public void setVida(int vida) {
        this.vida = vida;
    }
    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }
    public void setPuntosCargaUltimate(int puntosCargaUltimate) {
        PuntosCargaUltimate = puntosCargaUltimate;
    }

    /**
     * Calcula el daño de un ataque básico en función del arma equipada.
     *
     * @param armaequipada nombre del arma equipada.
     * @param ataquetotalPJ ataque total del personaje, incluyendo equipo.
     * @param defensatotalE defensa total del enemigo.
     * @return daño infligido al enemigo.
     */
    public int ataque_Basico(String armaequipada, int ataquetotalPJ, int defensatotalE){
        double multiplicador = switch (armaequipada) {
            case "Arma1" -> 1.3;  // Espadón
            case "Arma2" -> 1.8;  // Espada
            case "Arma3" -> 1.2;  // Pistola
            case "Arma4" -> 1.4;  // Guantes
            case "Arma5" -> 2.1;  // Rectificador
            case "Sin arma" -> 2.0; // sin arma
            default -> 1.0;
        };
        return calcularDanio(ataquetotalPJ, defensatotalE, multiplicador);
    }

    /**
     * Calcula el daño de una habilidad elemental en función del arma equipada.
     *
     * @param armaequipada nombre del arma equipada.
     * @param ataquetotalPJ ataque total del personaje.
     * @param defensatotalE defensa total del enemigo.
     * @return daño infligido al enemigo.
     */
    public int habilidad_elemental(String armaequipada, int ataquetotalPJ, int defensatotalE){
        double multiplicador = switch (armaequipada) {
            case "Arma1" -> 2.2;  // Espadón
            case "Arma2" -> 2.7;  // Espada
            case "Arma3" -> 2.1;  // Pistola
            case "Arma4" -> 2.3;  // Guantes
            case "Arma5" -> 3.5;  // Rectificador
            case "Sin arma" -> 3.0; // sin arma
            default -> 1.0;
        };
        return calcularDanio(ataquetotalPJ, defensatotalE, multiplicador);
    }

    /**
     * Calcula el daño de la habilidad definitiva (ultimate) en función del arma equipada.
     *
     * @param armaequipada nombre del arma equipada.
     * @param ataquetotalPJ ataque total del personaje.
     * @param defensatotalE defensa total del enemigo.
     * @return daño infligido al enemigo.
     */
    public int ultimate(String armaequipada, int ataquetotalPJ, int defensatotalE){
        double multiplicador = switch (armaequipada) {
            case "Arma1" -> 4.3;  // Espadón
            case "Arma2" -> 4.9;  // Espada
            case "Arma3" -> 4.0;  // Pistola
            case "Arma4" -> 4.5;  // Guantes
            case "Arma5" -> 5.5;  // Rectificador
            case "Sin arma" -> 8.0; // sin arma
            default -> 1.0;
        };
        return calcularDanio(ataquetotalPJ, defensatotalE, multiplicador);
    }

    /**
     * Método interno que calcula el daño final en función del ataque, defensa del enemigo y multiplicador.
     *
     * @param ataque ataque total del personaje.
     * @param defensaEnemigo defensa total del enemigo.
     * @param multiplicador modificador según tipo de ataque y arma.
     * @return daño final (como mínimo 0).
     */
    private int calcularDanio(int ataque, int defensaEnemigo, double multiplicador) {
        int danioBase = (int) (ataque * multiplicador);
        int danioFinal = danioBase - defensaEnemigo;
        return Math.max(danioFinal, 0);
    }
}
