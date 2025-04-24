/**
 * Package que contiene la implementación de la estructura de datos Stack.
 */

package EstructurasDeDatos;

import java.util.Iterator;

/**
 * Implementación de una pila (Stack) genérica en Java siguiendo la filosofía LIFO (Ultimo en Entrar, Primero en Salir).
 * La pila se redimensiona automáticamente cuando alcanza su capacidad máxima.
 *
 * @param <Item> Tipo de elementos que almacenará la pila.
 * 
 * @author juan
 * @author Camilo
 * @author Rodrigo
 */

public class Pila<Item> implements Iterable <Item> {
    private Item a[];
    private int count;
    
    /**
     * Constructor que inicializa la pila con una capacidad dada.
     *
     * @param initialCapacity Capacidad inicial de la pila
     */
    
    public Pila(int initialCapacity) {
        a = (Item[]) new Object[initialCapacity];
        count = 0;
    }
    
    /**
     * Redimensiona el arreglo interno de la pila.
     *
     * @param newCapacity Nueva capacidad del arreglo
     */
    private void resize(int newCapacity) {
        Item[] temp = (Item[]) new Object[newCapacity];
        for (int i = 0; i < count; i++) {
            temp[i] = a[i];
        }
        a = temp; 
    }
    
    /**
     * Agrega un elemento a la pila.
     * Si la pila está llena, su tamaño se duplica automáticamente.
     *
     * @param item Elemento a agregar
     */
    public void push(Item item) {
        if (count == a.length) {
            resize(2 * a.length); 
        }
        a[count++] = item;
    }
    
    /**
     * Extrae y devuelve el elemento en la cima de la pila.
     *
     * @return Elemento en la cima de la pila
     * @throws RuntimeException Si la pila está vacía
     */
    public Item pop() {
        if (isEmpty()) {
            System.out.println("Advertencia: La pila esta vacia.");
            return null; 
        }
        Item item = a[--count];
        a[count] = null; 
        
        
        if (count > 0 && count == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }
    
    /**
     * Devuelve el elemento en la cima de la pila sin eliminarlo.
     *
     * @return Elemento en la cima de la pila
     * @throws RuntimeException Si la pila está vacía
     */
    public Item peek() {
        if (isEmpty()) {
            System.out.println("Advertencia: La pila esta vacia.");
            return null; 
        }
        return a[count - 1];
    }
    
    /**
     * Verifica si la pila está vacía.
     *
     * @return true si la pila está vacía, false en caso contrario
     */
    public boolean isEmpty() {
        return count == 0;
    }
    
    /**
     * Devuelve el número de elementos en la pila.
     *
     * @return Número de elementos en la pila
     */
    public int size() {
        return count;
    }
    
    /**
     * Inserta un elemento en una posición específica de la pila.
     *
     * @param index Posición donde insertar el elemento.
     * @param item Elemento a insertar.
     */
    public void insertAt(int index, Item item) {
        if (index < 0 || index > count) {
            System.out.println("Error: Posición fuera de rango.");
            return;
        }
        if (count == a.length) {
            resize(2 * a.length);
        }
        for (int i = count; i > index; i--) {
            a[i] = a[i - 1];
        }
        a[index] = item;
        count++;
        System.out.println("Elemento insertado en la posición " + index);
    }

    /**
     * Elimina un elemento en una posición específica de la pila.
     *
     * @param index Posición del elemento a eliminar.
     */
    public void removeAt(int index) {
        if (index < 0 || index >= count) {
            System.out.println("Error: Posición fuera de rango.");
            return;
        }
        for (int i = index; i < count - 1; i++) {
            a[i] = a[i + 1];
        }
        a[--count] = null;
        System.out.println("Elemento eliminado en la posición " + index);
    }
    
    /**
     * Implementación de la interfaz Iterable para recorrer la pila con for-each.
     *
     * @return Un iterador que recorre la pila de arriba hacia abajo.
     */
    @Override
    public Iterator<Item> iterator() {
        return new StackIterator();
    }
    
    /**
     * Clase interna que implementa Iterator para recorrer la pila.
     */
    private class StackIterator implements Iterator<Item> {
        private int current = count - 1; 

        @Override
        public boolean hasNext() {
            return current >= 0; 
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                System.out.println("No hay más elementos en la pila.");
            }
            return a[current--]; 
        }
    
    }
}
