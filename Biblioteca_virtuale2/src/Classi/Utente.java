package Classi;
import java.util.ArrayList;
import java.util.List;

public class Utente {
	/**
	 * @param username
	 * @param password
	 * @param email
	 * @param l_prenotati
	 */
	private String username;
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public int getLvlAccesso() {
		return lvlAccesso;
	}

	public List<Libro> getL_prenotati() {
		return l_prenotati;
	}

	private String password;
	private String email;
	private int lvlAccesso;
	private List<Libro> l_prenotati; //lista dei libri presi dall'utente
	
	public void PrenotaLibro(Libro l) {
		l_prenotati.add(l);
	}
	
	//COSTRUTTORE
	public Utente(String user, String passw, String em, int lvl ) {
		this.username = user;
		this.password = passw;
		this.email = em;
		this.lvlAccesso=lvl;
		this.l_prenotati=new ArrayList<>(); //lista dei libri inizializzato a 0
	}

	
	
}
