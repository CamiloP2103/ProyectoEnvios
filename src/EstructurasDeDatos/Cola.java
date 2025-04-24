/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EstructurasDeDatos;

/**
 * Paquete que contiene la implementación de la estructura de datos Cola.
 * @author juan
 * @author Camilo
 * @author Rodrigo
 */

import java.util.Iterator;

/**
 * Implementación de una cola genérica en Java siguiendo la filosofía FIFO (Primero en Entrar, Primero en Salir).
 * La cola se redimensiona automáticamente cuando alcanza su capacidad máxima.
 *
 * @param <Item> Tipo de elementos que almacenará la cola.
 * 
 * @author juane
 */
public class Cola<Item> implements Iterable<Item> {
    private Item[] elementos;   
    private int frente;         
    private int finalCola;      
    private int cantidad;       
    private int capacidad;      

    /**
     * Constructor que inicializa la cola con una capacidad dada.
     *
     * @param capacidadInicial Capacidad inicial de la cola
     */
    public Cola(int capacidadInicial) {
        capacidad = capacidadInicial;
        elementos = (Item[]) new Object[capacidad];
        frente = 0;
        finalCola = -1;
        cantidad = 0;
    }

    /**
     * Redimensiona el arreglo interno de la cola.
     *
     * @param nuevaCapacidad Nueva capacidad del arreglo
     */
    private void redimensionar(int nuevaCapacidad) {
        Item[] temporal = (Item[]) new Object[nuevaCapacidad];
        
        
        for (int i = 0; i < cantidad; i++) {
            temporal[i] = elementos[(frente + i) % capacidad];
        }
        
        elementos = temporal;
        capacidad = nuevaCapacidad;
        frente = 0;
        finalCola = cantidad - 1;
    }

    /**
     * Agrega un elemento al final de la cola (encolar).
     * Si la cola está llena, su tamaño se duplica automáticamente.
     *
     * @param elemento Elemento a agregar
     */
    public void encolar(Item elemento) {
        if (cantidad == capacidad) {
            redimensionar(2 * capacidad); 
        }
        
        finalCola = (finalCola + 1) % capacidad; 
        elementos[finalCola] = elemento;
        cantidad++;
    }

    /**
     * Extrae y devuelve el elemento al frente de la cola (desencolar).
     *
     * @return Elemento al frente de la cola
     */
    public Item desencolar() {
        if (estaVacia()) {
            System.out.println("Advertencia: La cola esta vacia.");
            return null;
        }
        
        Item elemento = elementos[frente];
        elementos[frente] = null; 
        frente = (frente + 1) % capacidad; 
        cantidad--;
        
        
        if (cantidad > 0 && cantidad == capacidad / 4) {
            redimensionar(capacidad / 2);
        }
        
        return elemento;
    }

    /**
     * Devuelve el elemento al frente de la cola sin eliminarlo.
     *
     * @return Elemento al frente de la cola
     */
    public Item mirar() {
        if (estaVacia()) {
            System.out.println("Advertencia: La cola esta vacia.");
            return null;
        }
        return elementos[frente];
    }

    /**
     * Verifica si la cola está vacía.
     *
     * @return true si la cola está vacía, false en caso contrario
     */
    public boolean estaVacia() {
        return cantidad == 0;
    }

    /**
     * Devuelve el número de elementos en la cola.
     *
     * @return Número de elementos en la cola
     */
    public int tamano() {
        return cantidad;
    }
    
    /**
     * Devuelve la capacidad actual de la Lista.
     *
     * @return Capacidad actual del arreglo interno
     */
    public int capacidadActual() {
        return capacidad;
    }

    /**
     * Implementación de la interfaz Iterable para recorrer la cola con for-each.
     *
     * @return Un iterador que recorre la cola de frente a final.
     */
    @Override
    public Iterator<Item> iterator() {
        return new IteradorCola();
    }

    /**
     * Clase interna que implementa Iterador para recorrer la cola.
     */
    private class IteradorCola implements Iterator<Item> {
        private int actual = 0; 

        @Override
        public boolean hasNext() {
            return actual < cantidad;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                System.out.println("No hay mas elementos en la cola.");
            }
            
            Item elemento = elementos[(frente + actual) % capacidad];
            actual++;
            return elemento;
        }
    }
}
