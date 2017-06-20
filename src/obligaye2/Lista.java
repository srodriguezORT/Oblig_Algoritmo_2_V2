package obligaye2;

import java.util.Iterator;

public class Lista<T> implements Iterable<T> {

    private NodoLista<T> inicio;
    private int size;

    public Lista() {
    }

    public int getSize() {
        return size;
    }

    public Lista(NodoLista<T> inicio) {
        this.inicio = inicio;
        this.size = 0;
    }

    public void agregarInicio(T dato) {
        inicio = new NodoLista<T>(dato, inicio);
        size++;
    }

    public void agregarFin(T dato) {
        if (inicio == null) {
            agregarInicio(dato);
        } else {
            agregarFinRec(dato, inicio);
            size++;
        }
    }

    private void agregarFinRec(T dato, NodoLista<T> nodo) {
        if (nodo.sig == null) {
            nodo.sig = new NodoLista<T>(dato);
        } else {
            agregarFinRec(dato, nodo.sig);
        }
    }

    public void mostrar() {
        mostrarRec(inicio);
    }

    private void mostrarRec(NodoLista<T> nodo) {
        if (nodo != null) {
            System.out.println(nodo.dato);
            mostrarRec(nodo.sig);
        }
    }

    public boolean existe(T aBuscar) {
        NodoLista<T> aux = inicio;
        while (aux != null) {
            if (aux.dato.equals(aBuscar)) {
                return true;
            } else {
                aux = aux.sig;
            }
        }
        return false;

    }

    public T buscar(T aBuscar) {
        NodoLista<T> aux = inicio;
        while (aux != null) {
            if (aux.dato.equals(aBuscar)) {
                return aux.dato;
            } else {
                aux = aux.sig;
            }
        }
        return null;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {

            NodoLista<T> aux = inicio;

            public boolean hasNext() {
                return aux != null;
            }

            public T next() {
                T ret = aux.dato;
                aux = aux.sig;
                return ret;
            }

            public void remove() {
                // No hay nadie!
            }

        };
    }

}
