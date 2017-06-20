package obligaye2;

public class Arco {

    private boolean existe;
    private Vuelo v;

    public Arco() {
        //this.existe = false;
        //this.peso = 0;
    }

    public Arco(Vuelo v) {
        this.existe = true;
        this.v = v;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public Vuelo getV() {
        return v;
    }

    public void setV(Vuelo v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "Arco{" + "existe=" + existe + ", v=" + v + '}';
    }

}
