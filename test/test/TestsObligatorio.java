package test;

import obligaye2.ISistema;
import obligaye2.Sistema;
import obligaye2.TipoRetorno;
import obligaye2.TipoRetorno.TipoError;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestsObligatorio {


    //Juego de datos:
		//MIAMI 25.7823072,-80.3010442
		//MONTEVIDEO -34.8198625,-56.3702928
		//SANTIAGO -33.4724727,-70.9100275
		//SAN PABLO -23.6821604,-46.8755003
		//RIO DE JANEIRO -22.9109878,-43.7285354
		//BUENOS AIRES -34.6156541,-58.573407
		//PORTO ALEGRE -30.1087957,-51.3169919
		//CARACAS 10.4683612,-67.0304549
		//CIUDAD DE PANAMA 9.0831986,-79.592407
		//ASUNCION -25.2968361,-57.66813
		//LOS ANGELES 34.0201812,-118.6919284
		
		@Test
		public void testInicializarSistema(){
			ISistema s = new Sistema();
			assertEquals(TipoRetorno.TipoError.ERROR_1, s.inicializarSistema(0,25.7823072,-80.3010442,"MIAMI",-34.8198625,-56.3702928,"MONTEVIDEO").retorno);
			assertEquals(TipoRetorno.TipoError.ERROR_1, s.inicializarSistema(-1,25.7823072,-80.3010442,"MIAMI",-34.8198625,-56.3702928,"MONTEVIDEO").retorno);
			assertEquals(TipoRetorno.TipoError.ERROR_2, s.inicializarSistema(2,25.7823072,-80.3010442,"",-34.8198625,-56.3702928,"MONTEVIDEO").retorno);
			assertEquals(TipoRetorno.TipoError.ERROR_2, s.inicializarSistema(2,25.7823072,-80.3010442,"MIAMI",-34.8198625,-56.3702928,"").retorno);
			assertEquals(TipoRetorno.TipoError.ERROR_3, s.inicializarSistema(2,-34.8198625,-56.3702928,"MIAMI",-34.8198625,-56.3702928,"MONTEVIDEO").retorno);
			assertEquals(TipoRetorno.TipoError.OK, s.inicializarSistema(20,25.7823072,-80.3010442,"MIAMI",-34.8198625,-56.3702928,"MONTEVIDEO").retorno);
		}
		
		@Test
		public void testRegistrarCliente(){
			ISistema s = new Sistema();
			s.inicializarSistema(20,25.7823072,-80.3010442,"MIAMI",-34.8198625,-56.3702928,"MONTEVIDEO");
			
			assertEquals(TipoRetorno.TipoError.ERROR_1, s.registrarCliente("AAAAA", "Nombre", "direccion", "sososososo@soso.com").retorno);
			assertEquals(TipoRetorno.TipoError.ERROR_2, s.registrarCliente("5.555.555-5", "Nombre", "direccion", "sososososososo.com").retorno);
			assertEquals(TipoRetorno.TipoError.ERROR_2, s.registrarCliente("5.555.555-5", "Nombre", "direccion", "sosososos@@ososo.com").retorno);
			assertEquals(TipoRetorno.TipoError.ERROR_2, s.registrarCliente("5.555.555-5", "Nombre", "direccion", "sosososososos@.com").retorno);
			assertEquals(TipoRetorno.TipoError.OK, s.registrarCliente("5.555.555-5", "Nombre", "direccion", "sososososo@soso.com").retorno);
			assertEquals(TipoRetorno.TipoError.ERROR_3, s.registrarCliente("5.555.555-5", "Nombre", "direccion", "sososososo@soso.com").retorno);
		}
		
		@Test
		public void testRegistrarCompra(){
			ISistema s = new Sistema();
			s.inicializarSistema(20,25.7823072,-80.3010442,"MIAMI",-34.8198625,-56.3702928,"MONTEVIDEO");
			
			assertEquals(TipoRetorno.TipoError.ERROR_1, s.registrarCompra("5.555.555-5",11111,"Javatar").retorno);
			s.registrarCliente("5.555.555-5", "Nombre", "direccion", "sososososo@soso.com");
			assertEquals(TipoRetorno.TipoError.OK, s.registrarCompra("5.555.555-5",11111,"Javatar").retorno);
		}
		
		@Test
		public void testEnviosUrgente() {
			ISistema s = new Sistema();
			s.inicializarSistema(20,-34.8198625,-56.3702928,"MONTEVIDEO",25.7823072,-80.3010442,"MIAMI");
			
			registrarClientes(s);
			s.registrarCompra("5.555.555-5",11111,"Javatar");
			s.registrarCompra("5.555.555-5",22222,"Javatar 2");
			s.registrarCompra("5.555.555-5",33333,"Javatar 3");
			s.registrarCompra("5.555.555-5",44444,"Javatar 4");
			s.registrarCompra("5.555.555-5",55555,"Javatar 5");
			assertEquals(TipoRetorno.TipoError.ERROR_4, s.enviarComprasUrgenteCliente("5.555.555-5", 1).retorno);
			registrarCiudadesYVuelos(s);
			assertEquals(TipoRetorno.TipoError.ERROR_1, s.enviarComprasUrgenteCliente("5.555.555-5", 0).retorno);
			assertEquals(TipoRetorno.TipoError.ERROR_1, s.enviarComprasUrgenteCliente("5.555.555-5", 0).retorno);
			assertEquals(TipoRetorno.TipoError.ERROR_1, s.enviarComprasUrgenteCliente("5.555.555-5",-1).retorno);
			assertEquals(TipoRetorno.TipoError.ERROR_2, s.enviarComprasUrgenteCliente("5.989.989-5",1).retorno);
			assertEquals(TipoRetorno.TipoError.ERROR_3, s.enviarComprasUrgenteCliente("5.555.555-5",6).retorno);
			
			TipoRetorno ret = s.enviarComprasUrgenteCliente("5.555.555-5",1);
			assertEquals(ret.valorEntero, 280);
			assertTrue(ret.valorString.replaceAll("\\s+","").equals("MIAMI|LOSANGELES|SANTIAGO|MONTEVIDEO"));

			TipoRetorno ret2 = s.enviarComprasUrgenteCliente("5.555.555-5",2);
			assertEquals(ret2.valorEntero, 490);
			assertTrue(ret2.valorString.replaceAll("\\s+","").equals("MIAMI|LOSANGELES|BUENOSAIRES|PORTOALEGRE|MONTEVIDEO"));
		}
		
		@Test
		public void testEnviosEstandar() {
			ISistema s = new Sistema();
			s.inicializarSistema(20,-34.8198625,-56.3702928,"MONTEVIDEO",25.7823072,-80.3010442,"MIAMI");
			
			registrarClientes(s);
			s.registrarCompra("5.555.555-5",11111,"Javatar");
			s.registrarCompra("5.555.555-5",22222,"Javatar 2");
			s.registrarCompra("5.555.555-5",33333,"Javatar 3");
			s.registrarCompra("5.555.555-5",44444,"Javatar 4");
			s.registrarCompra("5.555.555-5",55555,"Javatar 5");
			assertEquals(TipoRetorno.TipoError.ERROR_4, s.enviarComprasEstandarCliente("5.555.555-5", 1).retorno);
			registrarCiudadesYVuelos(s);                               
			assertEquals(TipoRetorno.TipoError.ERROR_1, s.enviarComprasEstandarCliente("5.555.555-5", 0).retorno);
			assertEquals(TipoRetorno.TipoError.ERROR_1, s.enviarComprasEstandarCliente("5.555.555-5", 0).retorno);
			assertEquals(TipoRetorno.TipoError.ERROR_1, s.enviarComprasEstandarCliente("5.555.555-5",-1).retorno);
			assertEquals(TipoRetorno.TipoError.ERROR_2, s.enviarComprasEstandarCliente("5.989.989-5",1).retorno);
			assertEquals(TipoRetorno.TipoError.ERROR_3, s.enviarComprasEstandarCliente("5.555.555-5",6).retorno);
			
			TipoRetorno ret = s.enviarComprasEstandarCliente("5.555.555-5",1);
			assertEquals(ret.valorEntero, 55);
			assertTrue(ret.valorString.replaceAll("\\s+","").equals("MIAMI|SANPABLO|PORTOALEGRE|MONTEVIDEO"));

			TipoRetorno ret2 = s.enviarComprasEstandarCliente("5.555.555-5",1);
			assertEquals(ret2.valorEntero, 183);
			assertTrue(ret2.valorString.replaceAll("\\s+","").equals("MIAMI|CARACAS|ASUNCION|BUENOSAIRES|PORTOALEGRE|MONTEVIDEO"));
		}

		//Para uso interno de las pruebas
		public void registrarClientes(ISistema sistema) {
			assertEquals(TipoRetorno.TipoError.OK, sistema.registrarCliente("5.555.555-5", "Omar", "Oviedo 123", "omar@gmail.com").retorno);	
			assertEquals(TipoRetorno.TipoError.OK, sistema.registrarCliente("9.999.999-9", "Jorge", "Ellauri 456", "jorge@gmail.com").retorno);
			assertEquals(TipoRetorno.TipoError.OK, sistema.registrarCliente("1.111.111-1", "Maria", "Missouri 893", "maria@gmail.com").retorno);
			assertEquals(TipoRetorno.TipoError.OK, sistema.registrarCliente("2.222.222-2", "Ximena", "Montero 1122", "ximena@gmail.com").retorno);
			assertEquals(TipoRetorno.TipoError.OK, sistema.registrarCliente("4.444.444-4", "Analia", "Cuareim 455", "analia@gmail.com").retorno);
			assertEquals(TipoRetorno.TipoError.OK, sistema.registrarCliente("3.333.333-3", "Nicolas", "Uruguay 1234", "nicolas@gmail.com").retorno);
		}
		
		//Para uso interno de las pruebas
		public void registrarCiudadesYVuelos(ISistema sistema) {
			assertEquals(TipoRetorno.TipoError.OK, sistema.registrarCiudad("SANTIAGO", -33.4724727,-70.9100275).retorno);
			assertEquals(TipoRetorno.TipoError.OK, sistema.registrarCiudad("SAN PABLO", -23.6821604,-46.8755003).retorno);
			assertEquals(TipoRetorno.TipoError.OK, sistema.registrarCiudad("RIO DE JANEIRO", -22.9109878,-43.7285354).retorno);
			assertEquals(TipoRetorno.TipoError.OK, sistema.registrarCiudad("BUENOS AIRES", -34.6156541,-58.573407).retorno);
			assertEquals(TipoRetorno.TipoError.OK, sistema.registrarCiudad("PORTO ALEGRE", -30.1087957,-51.3169919).retorno);
			assertEquals(TipoRetorno.TipoError.OK, sistema.registrarCiudad("CARACAS", 10.4683612,-67.0304549).retorno);
			assertEquals(TipoRetorno.TipoError.OK, sistema.registrarCiudad("CIUDAD DE PANAMA", 9.0831986,-79.592407).retorno);
			assertEquals(TipoRetorno.TipoError.OK, sistema.registrarCiudad("ASUNCION", -25.2968361,-57.66813).retorno);
			assertEquals(TipoRetorno.TipoError.OK, sistema.registrarCiudad("LOS ANGELES", 34.0201812,-118.6919284).retorno);
			
			sistema.registrarVuelo(25.7823072,-80.3010442, 10.4683612,-67.0304549, 1, 10, 200);//MIAMI - CARACAS
			sistema.registrarVuelo(25.7823072,-80.3010442, 34.0201812,-118.6919284, 100, 120, 40);//MIAMI - LA
			sistema.registrarVuelo(25.7823072,-80.3010442, -23.6821604,-46.8755003, 1, 40, 520);//MIAMI - SP
			sistema.registrarVuelo(10.4683612,-67.0304549, -22.9109878,-43.7285354, 10, 200, 400);//CARACAS - RIO
			sistema.registrarVuelo(10.4683612,-67.0304549, -25.2968361,-57.66813, 10, 150, 320);//CARACAS - ASUNCION
			sistema.registrarVuelo(34.0201812,-118.6919284, 33.4724727,-70.9100275, 100, 500, 180);//LA - SANTIAGO
			sistema.registrarVuelo(34.0201812,-118.6919284, -34.6156541,-58.573407, 10, 300, 200);//LA - BS.AS.
			sistema.registrarVuelo(-25.2968361,-57.66813, -34.6156541,-58.573407, 10, 8, 140);//ASUNCION - BS.AS
			sistema.registrarVuelo(-22.9109878,-43.7285354, -30.1087957,-51.3169919, 10, 200, 300);//RIO - POA
			sistema.registrarVuelo(-23.6821604,-46.8755003, -30.1087957,-51.3169919, 10, 5, 140);//SP - POA
			sistema.registrarVuelo(-23.6821604,-46.8755003, -34.6156541,-58.573407, 10, 5, 100);//BS.AS - POA
			sistema.registrarVuelo(-33.4724727,-70.9100275, -34.8198625,-56.3702928, 1, 120, 60);//SANTIAGO-MVD
			sistema.registrarVuelo(-30.1087957,-51.3169919, -34.8198625,-56.3702928, 5, 10, 150);//POA-MVD
			
		}
	
}
