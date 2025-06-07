package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemigo {
    private String nombre;
    private int ataque;
    private int defensa;
    private int vida;
    private int puntosCargaUltimate = 0;

    public Enemigo(String nombre, int ataque, int defensa, int vida) {
        this.nombre = nombre;
        this.ataque = ataque;
        this.defensa = defensa;
        this.vida = vida;
    }

    public String getNombreE() {
        return nombre;
    }
    public int getAtaqueE() {
        return ataque;
    }
    public int getDefensaE() {
        return defensa;
    }
    public int getVidaE() {
        return vida;
    }
    public int getPuntosCargaUltimateE() {
        return puntosCargaUltimate;
    }

    public int ataque_Basico(int ataquetotalE, int defensatotalPJ) {
        return calcularDanio(ataquetotalE, defensatotalPJ, 1.4);
    }

    public int habilidad_elemental(int ataquetotalE, int defensatotalPJ) {
        return calcularDanio(ataquetotalE, defensatotalPJ, 2.6);
    }

    public int ultimate(int ataquetotalE, int defensatotalPJ) {
        return calcularDanio(ataquetotalE, defensatotalPJ, 4.5);
    }

    private int calcularDanio(int ataque, int defensaObjetivo, double multiplicador) {
        int danioBase = (int) (ataque * multiplicador);
        int danioFinal = danioBase - defensaObjetivo;
        return Math.max(danioFinal, 0);
    }

    public int EleccionEnemigo(int CDHabilidadElementalE, int puntosCargaUltimateE) {
//        1 = ataque basico
//        2 = habilidad elemental
//        3 = ultimate
        if (puntosCargaUltimateE < 3) {
            if (CDHabilidadElementalE > 0) {
                return 1;
            } else {
                return new Random().nextBoolean() ? 1 : 2;
            }
        } else {
            return 3;
        }
    }

    public class DatosEnemigos {

        public static final List<Enemigo> EnemigosSala1 = new ArrayList<>();
        public static final List<Enemigo> EnemigosSala2 = new ArrayList<>();
        public static final List<Enemigo> EnemigosSala3 = new ArrayList<>();
        public static final List<Enemigo> EnemigosSalaJefe = new ArrayList<>();

        static {
            EnemigosSala1.add(new Enemigo("enemigo1_1", 700, 400, 15000));
            EnemigosSala1.add(new Enemigo("enemigo1_2", 500, 600, 14000));
            EnemigosSala1.add(new Enemigo("enemigo1_3", 600, 500, 14500));

            EnemigosSala2.add(new Enemigo("enemigo2_1", 900, 700, 28000));
            EnemigosSala2.add(new Enemigo("enemigo2_2", 700, 900, 30000));
            EnemigosSala2.add(new Enemigo("enemigo2_3", 800, 800, 29000));

            EnemigosSala3.add(new Enemigo("enemigo3_1", 1100, 1000, 40000));
            EnemigosSala3.add(new Enemigo("enemigo3_2", 900, 1200, 38000));
            EnemigosSala3.add(new Enemigo("enemigo3_3", 1000, 1100, 39000));

            EnemigosSalaJefe.add(new Enemigo("enemigo4_1", 2000, 2000, 60000));
            EnemigosSalaJefe.add(new Enemigo("enemigo4_2", 1900, 1600, 58000));
            EnemigosSalaJefe.add(new Enemigo("enemigo4_3", 2100, 1300, 59000));
        }
    }
}
