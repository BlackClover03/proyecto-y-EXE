package org.example;

public class Arma {
    String nombre;
    int ataque = 0;
    int vida = 0;
    int defensa = 0;
    int coste = 0;

    public Arma(String nombre, int ataque, int vida, int defensa, int coste) {
        this.nombre = nombre;
        this.ataque = ataque;
        this.vida = vida;
        this.defensa = defensa;
        this.coste = coste;
    }

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

    public String obtenerEstadisticas() {
        return String.format("Ataque: %d\nDefensa: %d\nVida: %d\nCoste: %d", ataque, defensa, vida, coste);
    }
}
