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
public class Empresa {
    
    private String nombre;
    private int cantCompras;

    public Empresa(String nombre) {
        this.nombre = nombre;
        this.cantCompras = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantCompras() {
        return cantCompras;
    }

    public void setCantCompras(int cantCompras) {
        this.cantCompras = cantCompras;
    }
    
    
}
