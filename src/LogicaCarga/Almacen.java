/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaCarga;

import LogicaCarga.ZonasEnvio.Zona;
import ObjetosProyecto.Caja;

/**
 * Clase que representa un almacén de cajas para una zona específica
 * @author juan
 * @author Camilo
 * @author Rodrigo
 */

public class Almacen {
    
    private Zona zona;
    private EstructurasDeDatos.Lista<Caja> cajas;
    
    public Almacen(Zona zona) {
        this.zona = zona;
        this.cajas = new EstructurasDeDatos.Lista<>(10);
    }
    
    /**
     * Agrega una caja al almacén
     * @param caja La caja a agregar
     */
    public void agregarCaja(Caja caja) {
        cajas.agregarAlFinal(caja);
    }
    
    /**
     * Ordena las cajas en el almacén por distancia de envío (menor a mayor)
     * Implementación del algoritmo de ordenamiento por selección
     */
    public void ordenarPorDistancia() {
        int n = cajas.tamano();
        
        for (int i = 0; i < n - 1; i++) {
            int indiceMenor = i;
            
            for (int j = i + 1; j < n; j++) {
                if (cajas.obtenerDe(j).getGuia().getDistanciaEnvio() < 
                    cajas.obtenerDe(indiceMenor).getGuia().getDistanciaEnvio()) {
                    indiceMenor = j;
                }
            }
            
            
            if (indiceMenor != i) {
                Caja temp = cajas.obtenerDe(i);
                
                
                cajas.eliminarDe(i);
                cajas.insertarEn(i, cajas.obtenerDe(indiceMenor - 1));
                
                cajas.eliminarDe(indiceMenor);
                cajas.insertarEn(indiceMenor, temp);
            }
        }
    }
    
    /**
     * Verifica si el almacén está vacío
     * @return true si el almacén no tiene cajas, false en caso contrario
     */
    public boolean estaVacio() {
        return cajas.estaVacia();
    }
    
    /**
     * Obtiene todas las cajas del almacén
     * @return Lista de cajas en el almacén
     */
    public EstructurasDeDatos.Lista<Caja> getCajas() {
        return cajas;
    }
    
    /**
     * Obtiene la zona del almacén
     * @return La zona asignada al almacén
     */
    public Zona getZona() {
        return zona;
    }
    
    /**
     * Vacía el almacén eliminando todas las cajas
     */
    public void vaciar() {
        while (!cajas.estaVacia()) {
            cajas.eliminarDe(0);
        }
    }
    
    /**
     * Obtiene el número de cajas en el almacén
     * @return El número de cajas almacenadas
     */
    public int cantidadCajas() {
        return cajas.tamano();
    }
    
}
