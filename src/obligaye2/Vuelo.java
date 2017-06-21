/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligaye2;

import java.util.Objects;

/**
 *
 * @author Camila Ferreira y Sebastián Rodríguez
 */
public class Vuelo {
    
    /*private Double coordXi;
    private Double coordYi;
    private Double coordXf;
    private Double coordYf;*/
    private Ciudad cOrigen;
    private Ciudad cDestino;
    private int capacidad_paquetes;
    private int costo;
    private int tiempo_minutos;

    public Vuelo(Ciudad cOrigen, Ciudad cDestino) {
        this.cOrigen = cOrigen;
        this.cDestino = cDestino;
    }

    public Vuelo(Ciudad cOrigen, Ciudad cDestino, int capacidad_paquetes, int costo, int tiempo_minutos) {
        this.cOrigen = cOrigen;
        this.cDestino = cDestino;
        this.capacidad_paquetes = capacidad_paquetes;
        this.costo = costo;
        this.tiempo_minutos = tiempo_minutos;
    }

    public Ciudad getcOrigen() {
        return cOrigen;
    }

    public void setcOrigen(Ciudad cOrigen) {
        this.cOrigen = cOrigen;
    }

    public Ciudad getcDestino() {
        return cDestino;
    }

    public void setcDestino(Ciudad cDestino) {
        this.cDestino = cDestino;
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
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    /*@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vuelo other = (Vuelo) obj;
        if (!Objects.equals(this.cOrigen, other.cOrigen)) {
            return false;
        }
        if (!Objects.equals(this.cDestino, other.cDestino)) {
            return false;
        }
        return true;
    }*/    
    
}
