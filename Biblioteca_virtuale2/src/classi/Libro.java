package classi;

import javax.swing.ImageIcon;

public class Libro {
	//INIZIO CLASSI DI GET
    public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public int getQuantitaDisponibile() {
		return quantitaDisponibile;
	}

	public void setQuantitaDisponibile(int quantitaDisponibile) {
		this.quantitaDisponibile = quantitaDisponibile;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public int getAnnoPubblicazione() {
		return annoPubblicazione;
	}

	public void setAnnoPubblicazione(int annoPubblicazione) {
		this.annoPubblicazione = annoPubblicazione;
	}
	public String getCopertina() {
		// TODO Auto-generated method stub
		return copertina;
	}
	//FINE CLASSI DI GET


	/**
	 * @param iSBN
	 * @param titolo
	 * @param autore
	 * @param quantitaDisponibile
	 * @param genere
	 * @param annoPubblicazione
	 * @param genere
	 * 
	 */
	private String ISBN; // Il numero ISBN del libro
	private String titolo; // Il titolo del libro
    private String autore; // L'autore del libro
    private int quantitaDisponibile; // La quantit� di copie disponibili del libro
    private String genere; // Il genere del libro (ad es. fantascienza, romanzo, ecc.)
    private int annoPubblicazione; // L'anno di pubblicazione del libro
	private String copertina; //Copertina del libro

   

	//COSTRUTTORE
	public Libro(String iSBN, String titolo, String autore, int quantitaDisponibile, String genere,
			int annoPubblicazione, String cop) {
		this.ISBN = iSBN;
		this.titolo = titolo;
		this.autore = autore;
		this.quantitaDisponibile = quantitaDisponibile;
		this.genere = genere;
		this.annoPubblicazione = annoPubblicazione;
		this.copertina=cop;
	}

	public Libro() {
		// TODO Auto-generated constructor stub
	}

	
	
    
}
