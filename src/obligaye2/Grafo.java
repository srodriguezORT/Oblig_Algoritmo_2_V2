package obligaye2;

import java.util.ArrayList;
import java.util.Iterator;

public class Grafo {
    
    private Ciudad[] ciudades;
    private Arco[][] matAdy;
    private int cant;
    private int tope;

    public Grafo(int tope) {

        this.tope = tope;		
        //this.cant = 0;

        this.ciudades = new Ciudad[tope];		
        this.matAdy = new Arco[tope][tope];
        for (int i = 0; i < tope; i++)
            for (int j = 0; j < tope; j++)
                matAdy[i][j] = new Arco();
    }

    /**
     * Inserta una ciudad en el grafo.
     * Pre: El grafo no está lleno.
     * @param c Ciudad
     */
    public void agregarCiudad(Ciudad c){
        cant++;		
        //Busco primer hueco
        int pos = 0;
        while(ciudades[pos]!= null)
            pos++;
            ciudades[pos] = c;
    }

    /**
     * Inserta una ciudad en el grafo.
     * Pre: El grafo no está lleno.
     * @param c Ciudad
     */
    public boolean existeCiudad(Ciudad c){
        for (int i = 0; i < tope; i++)
            if(ciudades[i] == c)
                return true;
        return false;
    }

    /**
     * Borra la ciudad c del grafo
     * Pre: existeCiudad(c)
     * @param c Ciudad
     */
    public void borrarCiudad(Ciudad c){
        for (int i = 0; i < tope; i++)
            if(ciudades[i] == c) {
                ciudades[i] = null;
                cant--;
                for (int j = 0; j < tope; j++){
                    matAdy[i][j].setExiste(false);
                    matAdy[j][i].setExiste(false);
                }
            }
    }

    /**
     * Inserta a la arista (cO, cD) de Vuelo v en el grafo.
     * Pre: existeCiudad(cO) && existeCiudad(cD)
     * @param cO Ciudad Origen
     * @param cD Ciudad Destino
     * @param v
     */
    public void agregarArista(Ciudad cO, Ciudad cD, Vuelo v){
        int posO = buscarPos(cO);
        int posD = buscarPos(cD);
        matAdy[posO][posD].setExiste(true);
        matAdy[posO][posD].setV(v);
        matAdy[posD][posO].setExiste(true);
        matAdy[posD][posO].setV(v);
    }

    /**
     * Borra a la arista (cO, cD) del grafo.
     * Pre: existeCiudad(cO) && existeCiudad(cD)
     * @param cO Ciudad Origen
     * @param cD Ciudad Destino
     */
    public void borrarArista(Ciudad cO, Ciudad cD){
        int posO = buscarPos(cO);
        int posD = buscarPos(cD);
        matAdy[posO][posD].setExiste(false);
        matAdy[posD][posO].setExiste(false);
    }

    /**
     * Determina si existe la arista entre cO y cD.
     * Pre: existeCiudad(cO) && existeCiudad(cD)
     * @param cO Ciudad Origen
     * @param cD Ciudad Destino
     * @return true si existe la arista; false en caso contrario.
     */
    public boolean existeArista(Ciudad cO, Ciudad cD){
        int posO = buscarPos(cO);
        int posD = buscarPos(cD);
        return matAdy[posO][posD].isExiste();
    }

    /**
     * Determina si el grafo está lleno
     * @return true si está lleno; false en caso contrario
     */
    public boolean estaLleno(){
        return cant == tope;
    }

    /**
     * Determina si el grafo está vacío
     * @return true si est� vacío; false en caso contrario
     */
    public boolean estaVacio(){
        return cant == 0;
    }

    /**
     * Retorna una Lista con las ciudades adyacentes a la Ciudad c
     * Pre: existeCiudad(c)
     * @param c Ciudad Origen
     * @return Lista con las ciudades adyacentes a la Ciudad c
     */
    public Lista obtenerAdyacentes(Ciudad c){
        Lista ret = new Lista();
        int pos = buscarPos(c);
        for (int i = 0; i < tope; i++)
            if(matAdy[pos][i].isExiste())
                ret.agregarFin(ciudades[i]);
        return ret;
    }	


    private int buscarPos(Ciudad c){
            for (int i = 0; i < tope; i++)
                    if(ciudades[i] == c)
                            return i;
            return -1;
    }
    
    public TipoRetorno hayRutaPedidoUrgente(Ciudad cO, Ciudad cD){
        
        int[] dist = new int[tope];
        Ciudad[] anterior = new Ciudad[tope];
        boolean[] vis = new boolean[tope];
        
        //for(int i=0; i<tope; dist[i] = Integer.MAX_VALUE, anterior[i++] = null);
        for(int i=0; i<tope; i++){
            dist[i] = Integer.MAX_VALUE;
            anterior[i++] = null;
        }
        
        dist[buscarPos(cO)] = 0;
        vis[buscarPos(cO)] = true;
        
        Lista<Ciudad> lCiudades = this.obtenerAdyacentes(cO);
        Iterator<Ciudad> iter = lCiudades.iterator();
        while(iter.hasNext()){
            Ciudad c = iter.next();
            dist[buscarPos(c)] = matAdy[buscarPos(cO)][buscarPos(c)].getV().getTiempo_minutos();
            anterior[buscarPos(c)] = cO;
        }        
        for(int k=0; k<tope-2; k++){
            int candidato = -1;
            int min = Integer.MAX_VALUE;
            for(int i=0; i<tope; i++){
                if(!vis[i] && dist[i] < min){
                    candidato = i;
                    min = dist[i];
                }
            }
            if(candidato == -1){
                return new TipoRetorno(TipoRetorno.TipoError.ERROR_4);
            }
            vis[candidato] = true;
            for(int i=0; i<tope; i++){
                if(!vis[i] && matAdy[candidato][i].isExiste() 
                           && dist[candidato]+matAdy[candidato][i].getV().getTiempo_minutos() < dist[i]){
                    dist[i] = dist[candidato] + matAdy[candidato][i].getV().getTiempo_minutos();
                    anterior[i] = ciudades[candidato];
                }
            }           
        }
        String ret = "";
        if(dist[buscarPos(cD)] == Integer.MAX_VALUE){
            return new TipoRetorno(TipoRetorno.TipoError.ERROR_4);
        }else{
            Lista<Ciudad> aux = new Lista<Ciudad>();
            while(!cO.equals(cD)){
                aux.agregarInicio(ciudades[buscarPos(cD)]);
                cD = anterior[buscarPos(cD)];
            }
            aux.agregarInicio(ciudades[buscarPos(cD)]);
            for(Ciudad c: aux){
                ret += c.getNombre() + "|";
            }
            ret = ret.substring(0, ret.length()-2);
        }
        int d = dist[buscarPos(cD)]; 
        return new TipoRetorno(d, ret);
    }
	
}
