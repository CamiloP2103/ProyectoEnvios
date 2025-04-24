/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaCarga;

/**
 * Clase que representa una zona de envío con un nombre asociado.
 * @author juan
 * @author Camilo
 * @author Rodrigo
 */

public class ZonasEnvio {
    
    public enum Zona {
        
        NORTE("Norte"),
        SUR("Sur"),
        ESTE("Este"),
        OESTE("Oeste"),
        CENTRO("Centro");

        private final String nombre;

        Zona(String nombre) {
            this.nombre = nombre;
        }

        public String getNombre() {
            return nombre;
        }
    }
    
}
