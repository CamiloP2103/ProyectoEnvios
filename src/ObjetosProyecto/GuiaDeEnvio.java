/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ObjetosProyecto;

/**
 *
 * 
 * @author juan
 * @author Camilo
 * @author Rodrigo
 */
public class GuiaDeEnvio {
    
    private String idCaja;
    private Cliente cliente;
    private String direccionDestino;
    private double distanciaEnvio;
    private double pesoFacturado;
    private double costoEnvio;

    public GuiaDeEnvio(String idCaja, Cliente cliente, String direccionDestino,double distanciaEnvio, double pesoFacturado) {
        this.idCaja = idCaja;
        this.cliente = cliente;
        this.direccionDestino = direccionDestino;
        this.distanciaEnvio = distanciaEnvio;
        this.pesoFacturado = pesoFacturado;
        this.costoEnvio = pesoFacturado * 3.0;
    }

    /**
     * @return the idCaja
     */
    public String getIdCaja() {
        return idCaja;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @return the direccionDestino
     */
    public String getDireccionDestino() {
        return direccionDestino;
    }

    /**
     * @return the distanciaEnvio
     */
    public double getDistanciaEnvio() {
        return distanciaEnvio;
    }

    /**
     * @return the pesoFacturado
     */
    public double getPesoFacturado() {
        return pesoFacturado;
    }

    /**
     * @return the costoEnvio
     */
    public double getCostoEnvio() {
        return costoEnvio;
    }

    /**
     * @param idCaja the idCaja to set
     */
    public void setIdCaja(String idCaja) {
        this.idCaja = idCaja;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @param direccionDestino the direccionDestino to set
     */
    public void setDireccionDestino(String direccionDestino) {
        this.direccionDestino = direccionDestino;
    }

    /**
     * @param distanciaEnvio the distanciaEnvio to set
     */
    public void setDistanciaEnvio(double distanciaEnvio) {
        this.distanciaEnvio = distanciaEnvio;
    }

    /**
     * @param pesoFacturado the pesoFacturado to set
     */
    public void setPesoFacturado(double pesoFacturado) {
        this.pesoFacturado = pesoFacturado;
    }

    /**
     * @param costoEnvio the costoEnvio to set
     */
    public void setCostoEnvio(double costoEnvio) {
        this.costoEnvio = costoEnvio;
    }


    
}
