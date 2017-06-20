package obligaye2;

import obligaye2.TipoRetorno;

public interface ISistema {
	
	TipoRetorno inicializarSistema (int cantCiudadesMax, Double CoordXDestino, Double CoordYDestino, 
			String nombreCiudadDestino, Double CoordXOrigen, Double CoordYOrigen, String nombreCiudadOrigen) ;
	
	TipoRetorno destruirSistema () ;
	
	TipoRetorno registrarCliente (String cedula, String nombre, String direccion, String email);
	
	TipoRetorno registrarCompra (String cedula, int trackingId, String empresa) ;
	
	TipoRetorno registrarCiudad(String Nombre, Double coordX, Double coordY) ;
	
	TipoRetorno registrarVuelo(Double coordXi, Double coordYi, Double coordXf, Double coordYf, 
			int capacidad_paquetes, int costo, int tiempo_minutos) ;
	
	TipoRetorno reporteEnviosPorEmpresa () ;
	
	TipoRetorno reporteEnviosPorCliente () ;
	
	TipoRetorno reporteEnviosPendientesPorCliente () ;

	TipoRetorno enviarComprasUrgenteCliente(String cedula, int cant_paquetes) ;
	
	TipoRetorno enviarComprasEstandarCliente(String cedula, int cant_paquetes) ;
	
	TipoRetorno mapaEstado();
	
}
