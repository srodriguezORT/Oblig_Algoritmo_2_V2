package obligaye2;

/**
 *
 * @author Camila Ferreira y Sebastián Rodríguez
 */
public class Compra {
    
    private int trackingId;
    private String empresa;
    private boolean enviado;

    public Compra(int trackingId, String empresa) {
        this.trackingId = trackingId;
        this.empresa = empresa;
        this.enviado = false;
    }

    public int getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(int trackingId) {
        this.trackingId = trackingId;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    
    public boolean isEnviado() {
        return enviado;
    }

    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }

}
