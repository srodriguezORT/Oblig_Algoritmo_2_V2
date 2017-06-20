/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligaye2;

/**
 *
 * @author Sebastian
 */
public class Ciudad {
    
    private String nombre;
    private Double coordX;
    private Double coordY;

    public Ciudad (Double coordX, Double coordY){
        this.coordX = coordX;
        this.coordY = coordY;
    }
    
    public Ciudad(String nombre, Double coordX, Double coordY) {
        this.nombre = nombre;
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getCoordX() {
        return coordX;
    }

    public void setCoordX(Double coordX) {
        this.coordX = coordX;
    }

    public Double getCoordY() {
        return coordY;
    }

    public void setCoordY(Double coordY) {
        this.coordY = coordY;
    }
    
    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ciudad other = (Ciudad) obj;
		if (coordX != other.coordX && coordY != other.coordY)
			return false;
		return true;
	}
}
