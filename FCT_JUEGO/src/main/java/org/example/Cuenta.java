package org.example;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.util.ArrayList;

/**
 * Clase que representa la cuenta de un jugador.
 *
 * Contiene información sobre el nombre del jugador, su cantidad de moneda del juego (conchas caparazón),
 * el personaje asociado, las armas y objetos que ha obtenido, así como las armas y objetos actualmente seleccionados.
 */
public class Cuenta {

    // Atributos
    String nombre;
    IntegerProperty cantidad_concha_caparazon = new SimpleIntegerProperty(10000);
    private Personaje personaje;
    private ArrayList<Arma> armasObtenidas;
    private ArrayList<Objeto> objetosObtenidos;
    private String armaSeleccionada;
    private String objetoSeleccionado;

    /**
     * Constructor que crea una cuenta con un nombre y un personaje por defecto.
     *
     * @param nombre Nombre del jugador
     */
    public Cuenta(String nombre) {
        this.nombre = nombre;
        this.personaje = new Personaje();
        this.armasObtenidas = new ArrayList<>();
        this.objetosObtenidos = new ArrayList<>();
    }

    // Getters
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

    // Setters
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

    // Métodos de lógica

    /**
     * Intenta comprar un arma.
     * Si el jugador tiene suficiente dinero, se descuenta el coste y se añade al inventario.
     *
     * @param arma Arma que se desea comprar
     * @return true si la compra fue exitosa, false si no hay suficiente dinero
     */
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

    /**
     * Intenta comprar un objeto.
     * Si el jugador tiene suficiente dinero, se descuenta el coste y se añade al inventario.
     *
     * @param objeto Objeto que se desea comprar
     * @return true si la compra fue exitosa, false si no hay suficiente dinero
     */
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

    /**
     * Devuelve el objeto `Arma` correspondiente al nombre del arma seleccionada.
     *
     * @return Objeto `Arma` actualmente seleccionado, o null si no se encuentra
     */
    public Arma getArmaSeleccionadaObjeto() {
        for (Arma a : armasObtenidas) {
            if (a.getNombre().equals(armaSeleccionada)) {
                return a;
            }
        }
        return null;
    }

    /**
     * Devuelve el objeto `Objeto` correspondiente al nombre del objeto seleccionado.
     *
     * @return Objeto `Objeto` actualmente seleccionado, o null si no se encuentra
     */
    public Objeto getObjetoSeleccionadoObjeto() {
        for (Objeto o : objetosObtenidos) {
            if (o.getNombre().equals(objetoSeleccionado)) {
                return o;
            }
        }
        return null;
    }
}
