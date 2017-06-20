/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligaye2;

/**
 *
 * @author Camila Ferreira y Sebastián Rodríguez
 */
public class Vuelo {
    
    private Double coordXi;
    private Double coordYi;
    private Double coordXf;
    private Double coordYf;
    private int capacidad_paquetes;
    private int costo;
    private int tiempo_minutos;

    public Vuelo(Double coordXi, Double coordYi, Double coordXf, Double coordYf) {
        this.coordXi = coordXi;
        this.coordYi = coordYi;
        this.coordXf = coordXf;
        this.coordYf = coordYf;
    }
    
    public Vuelo(Double coordXi, Double coordYi, Double coordXf, Double coordYf, int capacidad_paquetes, int costo, int tiempo_minutos) {
        this.coordXi = coordXi;
        this.coordYi = coordYi;
        this.coordXf = coordXf;
        this.coordYf = coordYf;
        this.capacidad_paquetes = capacidad_paquetes;
        this.costo = costo;
        this.tiempo_minutos = tiempo_minutos;
    }

    public Double getCoordXi() {
        return coordXi;
    }

    public void setCoordXi(Double coordXi) {
        this.coordXi = coordXi;
    }

    public Double getCoordYi() {
        return coordYi;
    }

    public void setCoordYi(Double coordYi) {
        this.coordYi = coordYi;
    }

    public Double getCoordXf() {
        return coordXf;
    }

    public void setCoordXf(Double coordXf) {
        this.coordXf = coordXf;
    }

    public Double getCoordYf() {
        return coordYf;
    }

    public void setCoordYf(Double coordYf) {
        this.coordYf = coordYf;
    }

    public int getCapacidad_paquetes() {
        return capacidad_paquetes;
    }

    public void setCapacidad_paquetes(int capacidad_paquetes) {
        this.capacidad_paquetes = capacidad_paquetes;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getTiempo_minutos() {
        return tiempo_minutos;
    }

    public void setTiempo_minutos(int tiempo_minutos) {
        this.tiempo_minutos = tiempo_minutos;
    }
    
    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vuelo other = (Vuelo) obj;
		if ((coordXi != other.coordXi && coordYi != other.coordYi) && 
                        (coordXf != other.coordXf && coordYf != other.coordYf))
			return false;
		return true;
	}
    
}
