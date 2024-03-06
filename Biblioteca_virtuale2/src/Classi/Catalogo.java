package Classi;
import java.util.ArrayList;
import java.util.List;

import Eccezioni.LibroNonTrovatoException;

public class Catalogo {
	public List<Libro> getLista_libri() {
		return lista_libri;
	}

	public void setLista_libri(List<Libro> lista_libri) {
		this.lista_libri = lista_libri;
	}

	public List<Libro> lista_libri;
	
	private boolean contieneISBN(String ISBN) {
        for (Libro libro : lista_libri) {
            if (libro.getISBN().equals(ISBN)) {
                return true;
            }
        }
        return false;
    }
	
	public void aggiungi_libro(Libro l) { //aggiungo un libro
		if(!contieneISBN(l.getISBN())) {
			lista_libri.add(l);
		}
		else 
		{
			System.out.println("Libro già presente");
			return;
		}
	}
	
	public void rimuovi_libro(Libro l) { //tolgo un libro
		lista_libri.remove(l);
	}
	
	public Libro cerca_libro(String titolo) throws LibroNonTrovatoException { //ricerca per Titolo
		for(Libro l:lista_libri) {
			if(l.getTitolo().equals(titolo))
				return l;
		}
	    throw new LibroNonTrovatoException("Libro con il titolo : " + titolo + " non trovato");
	}
	
	//COSTRUTTORE
	public Catalogo(List<Libro> l) {
		this.lista_libri=l;
		this.lista_libri=new ArrayList<Libro>();
	}

	public Catalogo() {
		this.lista_libri=new ArrayList<Libro>();
	}
}
