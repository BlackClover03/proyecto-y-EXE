package org.example;

/**
 * Clase que representa un objeto dentro del juego.
 * Cada objeto puede otorgar estadísticas adicionales como ataque, vida y defensa,
 * y tiene un coste asociado en la moneda del juego (conchas/caparazones).
 */
public class Objeto {
    // Atributos del objeto
    String nombre;
    int ataque = 0;
    int vida = 0;
    int defensa = 0;
    int coste = 0;

    /**
     * Constructor para crear un nuevo objeto con sus atributos definidos.
     *
     * @param nombre  Nombre del objeto.
     * @param ataque  Puntos de ataque que otorga.
     * @param vida    Puntos de vida que otorga.
     * @param defensa Puntos de defensa que otorga.
     * @param coste   Coste en conchas/caparazones.
     */
    public Objeto(String nombre, int ataque, int vida, int defensa, int coste) {
        this.nombre = nombre;
        this.ataque = ataque;
        this.vida = vida;
        this.defensa = defensa;
        this.coste = coste;
    }

    // Métodos getters
    public String getNombre() {
        return nombre;
    }
    public int getAtaque() {
        return ataque;
    }
    public int getVida() {
        return vida;
    }
    public int getDefensa() {
        return defensa;
    }
    public int getCoste() {
        return coste;
    }

    // Métodos setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }
    public void setVida(int vida) {
        this.vida = vida;
    }
    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }
    public void setCoste(int coste) {
        this.coste = coste;
    }

    /**
     * Devuelve una cadena con las estadísticas del objeto,
     * útil para mostrar información al jugador.
     *
     * @return Texto con las estadísticas del objeto.
     */
    public String obtenerEstadisticas() {
        return String.format("Ataque: %d\nDefensa: %d\nVida: %d\nCoste: %d", ataque, defensa, vida, coste);
    }
}
