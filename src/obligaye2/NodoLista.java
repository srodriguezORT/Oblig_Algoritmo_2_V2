package obligaye2;

public class NodoLista<T> {

	public T dato;
	public NodoLista<T> sig;

	public NodoLista(T dato, NodoLista<T> sig) {
		this.dato = dato;
		this.sig = sig;
	}
	
	public NodoLista(T dato) {
		this.dato = dato;
	}

	@Override
	public String toString() {
		return dato+"";
	}
	
	
}
