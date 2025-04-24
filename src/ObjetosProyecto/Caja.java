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
public class Caja {

    private double altoCaja;
    private double anchoCaja;
    private double largoCaja;
    private double pesoCaja;
    private double pesoVolumetrico;
    private GuiaDeEnvio guia;

    public Caja(double altoCaja, double anchoCaja, double largoCaja, double peso, GuiaDeEnvio guia) {
        this.altoCaja = altoCaja;
        this.anchoCaja = anchoCaja;
        this.largoCaja = largoCaja;
        this.pesoCaja = peso;
        this.guia = guia;
        this.pesoVolumetrico = (largoCaja * anchoCaja * altoCaja) / 5000.0;
    }

    /**
     * @return the altoCaja
     */
    public double getAltoCaja() {
        return altoCaja;
    }

    /**
     * @return the anchoCaja
     */
    public double getAnchoCaja() {
        return anchoCaja;
    }

    /**
     * @return the largoCaja
     */
    public double getLargoCaja() {
        return largoCaja;
    }

    /**
     * @return the pesoCaja
     */
    public double getPesoCaja() {
        return pesoCaja;
    }

    /**
     * @return the pesoVolumetrico
     */
    public double getPesoVolumetrico() {
        return pesoVolumetrico;
    }

    /**
     * @return the guia
     */
    public GuiaDeEnvio getGuia() {
        return guia;
    }

    /**
     * @param altoCaja the altoCaja to set
     */
    public void setAltoCaja(double altoCaja) {
        this.altoCaja = altoCaja;
    }

    /**
     * @param anchoCaja the anchoCaja to set
     */
    public void setAnchoCaja(double anchoCaja) {
        this.anchoCaja = anchoCaja;
    }

    /**
     * @param largoCaja the largoCaja to set
     */
    public void setLargoCaja(double largoCaja) {
        this.largoCaja = largoCaja;
    }

    /**
     * @param pesoCaja the pesoCaja to set
     */
    public void setPesoCaja(double pesoCaja) {
        this.pesoCaja = pesoCaja;
    }

    /**
     * @param pesoVolumetrico the pesoVolumetrico to set
     */
    public void setPesoVolumetrico(double pesoVolumetrico) {
        this.pesoVolumetrico = pesoVolumetrico;
    }

    /**
     * @param guia the guia to set
     */
    public void setGuia(GuiaDeEnvio guia) {
        this.guia = guia;
    }

    
    
}

