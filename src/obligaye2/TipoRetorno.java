package obligaye2;

public class TipoRetorno {

    public enum TipoError {OK, ERROR_1, ERROR_2, ERROR_3,     
         ERROR_4, ERROR_5, ERROR_6, NO_IMPLEMENTADA};

    public int valorEntero;
    public String valorString;
    public TipoError retorno;
        
    public TipoRetorno(TipoError retorno) {
    	super();
    	this.retorno = retorno;
    }
    
    public TipoRetorno(int valor) {
    	super();
    	this.valorEntero = valor;
    	this.retorno = TipoError.OK;
    }
    
    public TipoRetorno(String valor) {
    	super();
    	this.valorString = valor;
    	this.retorno = TipoError.OK;
    }
    
    public TipoRetorno(int valor, String valorStr) {
    	super();
    	this.valorEntero = valor;
    	this.valorString = valorStr;
    	this.retorno = TipoError.OK;
    }
}

