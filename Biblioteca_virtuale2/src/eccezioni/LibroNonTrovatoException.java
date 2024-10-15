package eccezioni;
//Classe per avere l'Eccezione personalizzata 
public class LibroNonTrovatoException extends Exception {
	public LibroNonTrovatoException() {
        super();
    }

    public LibroNonTrovatoException(String message) {
        super(message);
    }
}
