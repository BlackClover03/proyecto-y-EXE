package org.example;

/**
 * Clase que representa un arma en el juego.
 *
 * Un arma puede tener valores de ataque, vida, defensa y coste asociados.
 * Esta clase proporciona métodos para acceder y modificar esos atributos,
 * así como un método para obtener una descripción de sus estadísticas.
 */
public class Arma {
    // Atributos del arma
    String nombre;
    int ataque = 0;
    int vida = 0;
    int defensa = 0;
    int coste = 0;

    /**
     * Constructor para crear un arma con estadísticas específicas.
     *
     * @param nombre Nombre del arma
     * @param ataque Valor de ataque que proporciona el arma
     * @param vida Valor de vida adicional que proporciona el arma
     * @param defensa Valor de defensa que proporciona el arma
     * @param coste Coste del arma (por ejemplo, en monedas del juego)
     */
    public Arma(String nombre, int ataque, int vida, int defensa, int coste) {
        this.nombre = nombre;
        this.ataque = ataque;
        this.vida = vida;
        this.defensa = defensa;
        this.coste = coste;
    }

    // Getters

    /**
     * @return el nombre del arma
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return el valor de ataque del arma
     */
    public int getAtaque() {
        return ataque;
    }

    /**
     * @return el valor de vida adicional del arma
     */
    public int getVida() {
        return vida;
    }

    /**
     * @return el valor de defensa del arma
     */
    public int getDefensa() {
        return defensa;
    }

    /**
     * @return el coste del arma
     */
    public int getCoste() {
        return coste;
    }

    // Setters

    /**
     * Establece un nuevo nombre para el arma.
     * @param nombre el nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece un nuevo valor de ataque.
     * @param ataque el nuevo valor de ataque
     */
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    /**
     * Establece un nuevo valor de vida.
     * @param vida el nuevo valor de vida
     */
    public void setVida(int vida) {
        this.vida = vida;
    }

    /**
     * Establece un nuevo valor de defensa.
     * @param defensa el nuevo valor de defensa
     */
    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    /**
     * Establece un nuevo coste para el arma.
     * @param coste el nuevo coste
     */
    public void setCoste(int coste) {
        this.coste = coste;
    }

    /**
     * Devuelve una cadena con las estadísticas completas del arma.
     *
     * @return Cadena formateada con ataque, defensa, vida y coste.
     */
    public String obtenerEstadisticas() {
        return String.format("Ataque: %d\nDefensa: %d\nVida: %d\nCoste: %d", ataque, defensa, vida, coste);
    }
}
