package org.example;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.util.ArrayList;

public class Cuenta {
    String nombre;
    IntegerProperty cantidad_concha_caparazon = new SimpleIntegerProperty(10000);
    private Personaje personaje;
    private ArrayList<Arma> armasObtenidas;
    private ArrayList<Objeto> objetosObtenidos;
    private String armaSeleccionada;
    private String objetoSeleccionado;

    public Cuenta(String nombre) {
        this.nombre = nombre;
        this.personaje = new Personaje();
        this.armasObtenidas = new ArrayList<>();
        this.objetosObtenidos = new ArrayList<>();
    }

    public Personaje getPersonaje() {
        return personaje;
    }
    public String getNombre() {
        return nombre;
    }
    public IntegerProperty getCantidad_concha_caparazon() {
        return cantidad_concha_caparazon;
    }
    public ArrayList<Arma> getArmasObtenidas() {
        return armasObtenidas;
    }
    public ArrayList<Objeto> getObjetosObtenidos() {
        return objetosObtenidos;
    }
    public String getArmaSeleccionada() {
        return armaSeleccionada;
    }
    public String getObjetoSeleccionado() {
        return objetoSeleccionado;
    }
    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setCantidad_concha_caparazon(IntegerProperty cantidad_concha_caparazon) {
        this.cantidad_concha_caparazon = cantidad_concha_caparazon;
    }
    public void setArmasObtenidas(ArrayList<Arma> armasObtenidas) {
        this.armasObtenidas = armasObtenidas;
    }
    public void setObjetosObtenidos(ArrayList<Objeto> objetosObtenidos) {
        this.objetosObtenidos = objetosObtenidos;
    }
    public void setArmaSeleccionada(String armaSeleccionada) {
        this.armaSeleccionada = armaSeleccionada;
    }
    public void setObjetoSeleccionado(String objetoSeleccionado) {
        this.objetoSeleccionado = objetoSeleccionado;
    }

    public boolean comprarArma(Arma arma) {
        if (cantidad_concha_caparazon.get() >= arma.getCoste()) {
            armasObtenidas.add(arma);
            cantidad_concha_caparazon.set(cantidad_concha_caparazon.get() - arma.getCoste());
            return true;
        } else{
            System.out.println("Dinero insuficiente para comprar " + arma.getNombre());
        }
        return false;
    }

    public boolean comprarObjeto(Objeto objeto) {
        if (cantidad_concha_caparazon.get() >= objeto.getCoste()) {
            objetosObtenidos.add(objeto);
            cantidad_concha_caparazon.set(cantidad_concha_caparazon.get() - objeto.getCoste());
            return true;
        } else{
            System.out.println("Dinero insuficiente para comprar " + objeto.getNombre());
        }
        return false;
    }

    public Arma getArmaSeleccionadaObjeto() {
        for (Arma a : armasObtenidas) {
            if (a.getNombre().equals(armaSeleccionada)) {
                return a;
            }
        }
        return null;
    }

    public Objeto getObjetoSeleccionadoObjeto() {
        for (Objeto o : objetosObtenidos) {
            if (o.getNombre().equals(objetoSeleccionado)) {
                return o;
            }
        }
        return null;
    }
}
