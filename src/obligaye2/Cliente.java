package obligaye2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Camila Ferreira y Sebastián Rodríguez
 */
public class Cliente {

    private String cedulaCliente;
    private String nombreCliente;
    private String direccionCliente;
    private String emailCliente;
    private Lista<Compra> compras;

    public Cliente(String cedula) {
        this.cedulaCliente = cedula;
    }

    public Cliente(String cedulaCliente, String nombreCliente, String direccionCliente, String emailCliente) {
        this.cedulaCliente = cedulaCliente;
        this.nombreCliente = nombreCliente;
        this.direccionCliente = direccionCliente;
        this.emailCliente = emailCliente;
        this.compras = new Lista<Compra>();
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public Lista<Compra> getCompras() {
        return compras;
    }

    public void setCompras(Lista<Compra> compras) {
        this.compras = compras;
    }

    @Override
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
        Cliente other = (Cliente) obj;
        if (cedulaCliente != other.cedulaCliente) {
            return false;
        }
        return true;
    }

    /**
     * Retorna true si el e-mail del cliente es válido, false en caso contrario.
     *
     * @param email
     * @return boolean
     */
    public static boolean esValidoEmail(String email) {

        Pattern controlEmail = Pattern.compile("^[a-zA-Z0-9._%+-]+\\@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
        Matcher m = controlEmail.matcher(email);
        return m.find();
    }

    /**
     * Retorna true si la cédula del cliente es válida, false en caso contrario.
     *
     * @param cedula
     * @return boolean
     */
    public static boolean esValidaCi(String cedula) {
        Pattern controlCi = Pattern.compile("(([0-9]{1,3}[.-]?[0-9]{3})|([0-9]([.-]?[0-9]{3}){2}))([-]?[0-9])?$");
        Matcher m = controlCi.matcher(cedula);
        return m.find();
    }

    public int pendientesEnvio() {
        int cant = 0;
        for (Compra c : this.compras) {
            if (!c.isEnviado()) {
                cant++;
            }
        }
        return cant;
    }

}
