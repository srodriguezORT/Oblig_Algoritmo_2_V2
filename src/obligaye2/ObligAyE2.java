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
public class ObligAyE2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Sistema s = new Sistema();
        s.inicializarSistema(20,25.7823072,-80.3010442,"MIAMI",-34.8198625,-56.3702928,"MONTEVIDEO");
        s.registrarCiudad("BUENOS AIRES",-34.6153716 ,-58.5734063);
        s.registrarCiudad("SANTIAGO DE CHILE",-33.4718999,-70.9100247);
        s.mapaEstado();
    }
    
}
