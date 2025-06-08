package org.example;

/**
 * Clase principal del proyecto.
 *
 * Esta clase actúa como punto de entrada de la aplicación. Su único propósito es
 * delegar la ejecución al método `main` de la clase `Inicio`.
 *
 * Pertenece al paquete `org.example`.
 */
public class Main {
    /**
     * Método principal de la aplicación.
     *
     * Este método es el primer método que se ejecuta al iniciar la aplicación.
     * Redirige la ejecución al método `main` de la clase `Inicio`, pasando los
     * mismos argumentos recibidos desde la línea de comandos.
     *
     * @param args los argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        Inicio.main(args);
    }
}