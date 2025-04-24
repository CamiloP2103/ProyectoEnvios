/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaCarga;

import LogicaCarga.ZonasEnvio.Zona;
import ObjetosProyecto.Caja;

/**
 * Clase que representa las estadísticas de envío por zona
 * @author juan
 * @author Camilo
 * @author Rodrigo
 */

public class EstadisticasZona {
    
    private Zona zona;
    private double pesoVolumetricoTotal;
    private double costoEnvioTotal;
    private int cajasEntregadas;
    
    public EstadisticasZona(Zona zona) {
        this.zona = zona;
        this.pesoVolumetricoTotal = 0;
        this.costoEnvioTotal = 0;
        this.cajasEntregadas = 0;
    }
    
    /**
     * Registra una caja entregada en las estadísticas
     * @param caja La caja entregada
     */
    public void registrarEntrega(Caja caja) {
        pesoVolumetricoTotal += caja.getPesoVolumetrico();
        costoEnvioTotal += caja.getGuia().getCostoEnvio();
        cajasEntregadas++;
    }
    
    public Zona getZona() {
        return zona;
    }
    
    public double getPesoVolumetricoTotal() {
        return pesoVolumetricoTotal;
    }
    
    public double getCostoEnvioTotal() {
        return costoEnvioTotal;
    }
    
    public int getCajasEntregadas() {
        return cajasEntregadas;
    }
    
    @Override
    public String toString() {
        return "Estadísticas de Zona " + zona.getNombre() + ":\n" +
               "- Peso volumétrico total: " + String.format("%.2f", pesoVolumetricoTotal) + " kg\n" +
               "- Costo total de envíos: $" + String.format("%.2f", costoEnvioTotal) + "\n" +
               "- Cajas entregadas: " + cajasEntregadas;
    }
    
}