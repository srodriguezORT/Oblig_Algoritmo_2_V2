package obligaye2;

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
	 * Inserta un v�rtice en el grafo.
	 * Pre: El grafo no est� lleno.
	 * @param v V�rtice
	 */
	public void agregarVertice(Ciudad c)
	{
		cant++;
		
		//Busco primer hueco
		int pos = 0;
		while(ciudades[pos]!= null)
			pos++;
		ciudades[pos] = c;
	}
	
	/**
	 * Inserta un v�rtice en el grafo.
	 * Pre: El grafo no est� lleno.
	 * @param v V�rtice
	 */
	public boolean existeVertice(Ciudad c)
	{
		for (int i = 0; i < tope; i++)
			if(ciudades[i] == c)
				return true;
		return false;
	}
	
	/**
	 * Borra al v�rtice v del grafo
	 * Pre: existeVertice(v)
	 * @param v V�rtice
	 */
	public void borrarVertice(Ciudad c)
	{
		for (int i = 0; i < tope; i++)
			if(ciudades[i] == c)
			{
				ciudades[i] = null;
				cant--;
				for (int j = 0; j < tope; j++){
					matAdy[i][j].setExiste(false);
					matAdy[j][i].setExiste(false);
				}
			}
	}
	
	/**
	 * Inserta a la arista (vO, vD) de peso peso en el grafo.
	 * Pre: existeVertice(vO) && existeVertice(vD)
	 * @param vO V�rtice Origen
	 * @param vD V�rtice Destino
	 * @param peso
	 */
	public void agregarArista(Ciudad cO, Ciudad cD, Vuelo v)
	{
		int posO = buscarPos(cO);
		int posD = buscarPos(cD);
		matAdy[posO][posD].setExiste(true);
		matAdy[posO][posD].setV(v);
		matAdy[posD][posO].setExiste(true);
		matAdy[posD][posO].setV(v);
	}
	
	/**
	 * Borra a la arista (vO, vD) del grafo.
	 * Pre: existeVertice(vO) && existeVertice(vD)
	 * @param vO V�rtice Origen
	 * @param vD V�rtice Destino
	 * @param peso
	 */
	public void borrarArista(Ciudad cO, Ciudad cD)
	{
		int posO = buscarPos(cO);
		int posD = buscarPos(cD);
		matAdy[posO][posD].setExiste(false);
		matAdy[posD][posO].setExiste(false);
	}

	/**
	 * Determina si existe la arista entre vO y vD.
	 * Pre: existeVertice(vO) && existeVertice(vD)
	 * @param vO V�rtice Origen
	 * @param vD V�rtice Destino
	 * @return true si existe la arista; false en caso contrario.
	 */
	public boolean existeArista(Ciudad cO, Ciudad cD)
	{
		int posO = buscarPos(cO);
		int posD = buscarPos(cD);
		return matAdy[posO][posD].isExiste();
	}
	
	/**
	 * Determina si el grafo est� lleno
	 * @return true si est� lleno; false en caso contrario
	 */
	public boolean estaLleno(){
		return cant == tope;
	}
	
	/**
	 * Determina si el grafo est� vac�o
	 * @return true si est� vac�o; false en caso contrario
	 */
	public boolean estaVacio(){
		return cant == 0;
	}
	
	/**
	 * Retorna una Lista con los v�rtices adyacentes al v�rtice v
	 * Pre: existeVertice(v)
	 * @param v V�rtice Origen
	 * @return Lista con los v�rtices adyacentes al v�rtice v
	 */
	public Lista obtenerAdyacentes(Ciudad c)
	{
		Lista ret = new Lista();
		int pos = buscarPos(c);
		for (int i = 0; i < tope; i++)
			if(matAdy[pos][i].isExiste())
				ret.agregarFin(ciudades[i]);
		return ret;
	}
	
	
	
	private int buscarPos(Ciudad c)
	{
		for (int i = 0; i < tope; i++)
			if(ciudades[i] == c)
				return i;
		return -1;
	}
	
}
