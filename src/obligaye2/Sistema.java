package obligaye2;

import java.awt.Desktop;
import java.net.URI;
import java.net.URL;
import obligaye2.TipoRetorno.TipoError;

public class Sistema implements ISistema {

    int cantMaxCiu;
    Lista<Cliente> clientes;
    Lista<Ciudad> ciudades;
    Lista<Vuelo> vuelos;
    Lista<Compra> compras;
    Lista<Empresa> empresas;

    Grafo g = new Grafo(cantMaxCiu);

    @Override
    public TipoRetorno inicializarSistema(int cantCiudadesMax, Double CoordXDestino, Double CoordYDestino,
            String nombreCiudadDestino, Double CoordXOrigen, Double CoordYOrigen, String nombreCiudadOrigen) {
        if (cantCiudadesMax < 2) {
            return new TipoRetorno(TipoError.ERROR_1);
        } else if (nombreCiudadDestino.equals("") || nombreCiudadOrigen.equals("")) {
            return new TipoRetorno(TipoError.ERROR_2);
        } else if ((CoordXDestino.equals(CoordXOrigen)) && (CoordYDestino.equals(CoordYOrigen))) {
            return new TipoRetorno(TipoError.ERROR_3);
        }
        cantMaxCiu = cantCiudadesMax;
        ciudades = new Lista<>();
        clientes = new Lista<>();
        vuelos = new Lista<>();
        compras = new Lista<>();
        empresas = new Lista<>();
        ciudades.agregarFin(new Ciudad(nombreCiudadDestino, CoordXDestino, CoordYDestino));
        ciudades.agregarFin(new Ciudad(nombreCiudadOrigen, CoordXOrigen, CoordYOrigen));
        return new TipoRetorno(TipoError.OK);
    }

    @Override
    public TipoRetorno destruirSistema() {
        ciudades = null;
        clientes = null;
        vuelos = null;
        compras = null;
        empresas = null;
        return new TipoRetorno(TipoError.OK);
    }

    @Override
    public TipoRetorno registrarCliente(String cedula, String nombre, String direccion, String email) {

        Cliente aux = new Cliente(cedula);
        if (!Cliente.esValidaCi(cedula)) {
            return new TipoRetorno(TipoError.ERROR_1);
        } else if (!Cliente.esValidoEmail(email)) {
            return new TipoRetorno(TipoError.ERROR_2);
        } else if (clientes.existe(aux)) {
            return new TipoRetorno(TipoError.ERROR_3);
        }

        clientes.agregarInicio(new Cliente(cedula, nombre, direccion, email));
        return new TipoRetorno(TipoError.OK);

    }

    @Override
    public TipoRetorno registrarCompra(String cedula, int trackingId, String empresa) {

        Cliente auxCli = new Cliente(cedula);
        Compra auxCom = new Compra(trackingId, empresa);
        Empresa auxE = new Empresa(empresa);
        if (!clientes.existe(auxCli)) {
            return new TipoRetorno(TipoError.ERROR_1);
        }
        if (!empresas.existe(auxE)) {
            empresas.agregarFin(auxE);
        }
        clientes.buscar(auxCli).getCompras().agregarFin(auxCom);
        compras.agregarFin(auxCom);
        empresas.buscar(auxE).setCantCompras(empresas.buscar(auxE).getCantCompras() + 1);
        return new TipoRetorno(TipoError.OK);
    }

    @Override
    public TipoRetorno registrarCiudad(String Nombre, Double coordX, Double coordY) {

        Ciudad aux = new Ciudad(coordX, coordY);
        if (ciudades.getSize() == cantMaxCiu) {
            return new TipoRetorno(TipoError.ERROR_1);
        } else if (Nombre.equals("")) {
            return new TipoRetorno(TipoError.ERROR_2);
        } else if (ciudades.existe(aux)) {
            return new TipoRetorno(TipoError.ERROR_3);
        }

        ciudades.agregarInicio(new Ciudad(Nombre, coordX, coordY));
        return new TipoRetorno(TipoError.OK);

    }

    @Override
    public TipoRetorno registrarVuelo(Double coordXi, Double coordYi, Double coordXf, Double coordYf,
            int capacidad_paquetes, int costo, int tiempo_minutos) {

        Ciudad auxO = new Ciudad(coordXi, coordYi);
        Ciudad auxD = new Ciudad(coordXf, coordYf);
        Vuelo auxV = new Vuelo(coordXi, coordYi, coordXf, coordYf);
        if (capacidad_paquetes <= 0) {
            return new TipoRetorno(TipoError.ERROR_1);
        } else if (costo <= 0) {
            return new TipoRetorno(TipoError.ERROR_2);
        } else if (tiempo_minutos <= 0) {
            return new TipoRetorno(TipoError.ERROR_3);
        } else if (!ciudades.existe(auxO) || !ciudades.existe(auxD)) {
            return new TipoRetorno(TipoError.ERROR_4);
        } else if (vuelos.existe(auxV)) {
            return new TipoRetorno(TipoError.ERROR_5);
        }

        vuelos.agregarFin(new Vuelo(coordXi, coordYi, coordXf, coordYf, capacidad_paquetes, costo, tiempo_minutos));
        return new TipoRetorno(TipoError.OK);

    }

    @Override
    public TipoRetorno reporteEnviosPorEmpresa() {

        String valorString = "";
        for (Empresa x : empresas) {

            valorString += x.getNombre() + ";" + x.getCantCompras() + "|";
        }
        valorString = valorString.substring(0, valorString.length() - 2);
        return new TipoRetorno(valorString);
    }

    @Override
    public TipoRetorno reporteEnviosPorCliente() {

        String valorString = "";
        int cant = 0;
        for (Cliente x : clientes) {

            for (Compra c : x.getCompras()) {
                if (c.isEnviado()) {
                    cant++;
                }
            }

            valorString += x.getNombreCliente() + ";" + cant + "|";
        }

        valorString = valorString.substring(0, valorString.length() - 2);
        return new TipoRetorno(valorString);
    }

    @Override
    public TipoRetorno reporteEnviosPendientesPorCliente() {
        String valorString = "";
        String ids = "";
        for (Cliente x : clientes) {

            for (Compra c : x.getCompras()) {
                if (!c.isEnviado()) {
                    ids += c.getTrackingId() + ";";
                }
            }
            ids = ids.substring(0, ids.length() - 2);
            valorString += x.getNombreCliente() + ";" + ids + "|";
        }

        valorString = valorString.substring(0, valorString.length() - 2);
        return new TipoRetorno(valorString);
    }

    @Override
    public TipoRetorno enviarComprasUrgenteCliente(String cedula, int cant_paquetes) {

        Cliente auxCli = new Cliente(cedula);
        if (cant_paquetes <= 0) {
            return new TipoRetorno(TipoError.ERROR_1);
        } else if (!clientes.existe(auxCli)) {
            return new TipoRetorno(TipoError.ERROR_2);
        } else if (clientes.buscar(auxCli).pendientesEnvio() < cant_paquetes) {
            return new TipoRetorno(TipoError.ERROR_3);
        }//else if(){
        //return new TipoRetorno(TipoError.ERROR_4);
        //}

        return new TipoRetorno(TipoError.OK);
    }

    @Override
    public TipoRetorno enviarComprasEstandarCliente(String cedula, int cant_paquetes) {

        Cliente auxCli = new Cliente(cedula);
        if (cant_paquetes <= 0) {
            return new TipoRetorno(TipoError.ERROR_1);
        } else if (!clientes.existe(auxCli)) {
            return new TipoRetorno(TipoError.ERROR_2);
        } else if (clientes.buscar(auxCli).pendientesEnvio() < cant_paquetes) {
            return new TipoRetorno(TipoError.ERROR_3);
        }//else if(){
        //return new TipoRetorno(TipoError.ERROR_4);
        //}

        return new TipoRetorno(TipoError.OK);
    }

    @Override
    public TipoRetorno mapaEstado() {

        String cities = "";
                for (Ciudad x : ciudades) {
            
                   cities += x.getCoordX()+","+ x.getCoordY()+"|";
            }
            cities = cities.substring(0, cities.length()-2);
         
        String URL
                = "https://maps.googleapis.com/maps/api/staticmap?center=Colombia&size=1024x600&zoom=3&maptype=roadmap&"
                + "markers=color:red%7Clabel:C%7C|"+cities
                + "&key=AIzaSyDhfTvXeR1_KtzXelJWoMu64w9RaZ7bmE8";

        try {

            URL url = new URL(URL);
            URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);

            Desktop desktop = java.awt.Desktop.getDesktop();
            desktop.browse(uri);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new TipoRetorno(TipoError.NO_IMPLEMENTADA);
    }

}
