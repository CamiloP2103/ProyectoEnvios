/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaCarga;

import LogicaCarga.ZonasEnvio.Zona;
import ObjetosProyecto.Caja;

/**
 * Clase que representa un camión para transportar cajas
 * @author juan
 * @author Camilo
 * @author Rodrigo
 */

public class Camion {
    
    private Zona zona;
    private EstructurasDeDatos.Pila<Caja> cargaCajas;
    private final double alturaCamion = 250;  
    private final double anchoCamion = 200;   
    private final double largoCamion = 400;   
    private final double pesoMaximo = 4000;   
    private double pesoActual;
    
    public Camion(Zona zona) {
        this.zona = zona;
        this.cargaCajas = new EstructurasDeDatos.Pila<>(20);
        this.pesoActual = 0;
    }
    
    /**
     * Carga una caja en el camión
     * @param caja La caja a cargar
     * @return true si la caja se cargó exitosamente, false si no hay espacio
     */
    public boolean cargarCaja(Caja caja) {
        
        if (caja.getAltoCaja() > alturaCamion || 
            caja.getAnchoCaja() > anchoCamion || 
            caja.getLargoCaja() > largoCamion) {
            return false;
        }
        
        
        if (pesoActual + caja.getPesoCaja() > pesoMaximo) {
            return false;
        }
        
        cargaCajas.push(caja);
        pesoActual += caja.getPesoCaja();
        return true;
    }
    
    /**
     * Verifica si el camión está vacío
     * @return true si el camión no tiene cajas, false en caso contrario
     */
    public boolean estaVacio() {
        return cargaCajas.isEmpty();
    }
    
    /**
     * Obtiene el número de cajas en el camión
     * @return El número de cajas cargadas
     */
    public int numeroCajas() {
        return cargaCajas.size();
    }
    
    /**
     * Verifica si el camión puede ser despachado (tiene al menos la mitad de su capacidad en peso)
     * @return true si el camión puede ser despachado, false en caso contrario
     */
    public boolean puedeDespachar() {
        return pesoActual >= (pesoMaximo / 2);
    }
    
    /**
     * Despacha el camión y devuelve las cajas entregadas
     * @return Una lista con las cajas entregadas en orden
     */
    public EstructurasDeDatos.Lista<Caja> despachar() {
        if (!puedeDespachar()) {
            return null;
        }
        
        EstructurasDeDatos.Lista<Caja> cajasEntregadas = new EstructurasDeDatos.Lista<>(cargaCajas.size());
        
        while (!cargaCajas.isEmpty()) {
            Caja caja = cargaCajas.pop();
            cajasEntregadas.agregarAlFinal(caja);
        }
        
        pesoActual = 0;
        return cajasEntregadas;
    }
    
    /**
     * Obtiene la pila de cajas cargadas en el camión
     * @return La pila de cajas
     */
    public EstructurasDeDatos.Pila<Caja> getCajas() {
        return cargaCajas;
    }
    
    /**
     * Obtiene la zona asignada al camión
     * @return La zona del camión
     */
    public Zona getZona() {
        return zona;
    }
    
    /**
     * Obtiene el peso actual de la carga
     * @return El peso actual en kg
     */
    public double getPesoActual() {
        return pesoActual;
    }
    
    /**
     * Obtiene el peso máximo que puede cargar el camión
     * @return El peso máximo en kg
     */
    public double getPesoMaximo() {
        return pesoMaximo;
    }
    
    /**
     * Calcula el porcentaje de carga del camión
     * @return Porcentaje de carga en relación al peso máximo
     */
    public double getPorcentajeCarga() {
        return (pesoActual / pesoMaximo) * 100;
    }
    
}
