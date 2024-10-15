package classi;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import eccezioni.LibroNonTrovatoException;

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
			System.out.println("Libro gi� presente");
			return;
		}
	}
	
	public void rimuovi_libro(Libro l) { //tolgo un libro
		lista_libri.remove(l);
	}
	public void rimuovi_tutto() {
	    Iterator<Libro> iterator = lista_libri.iterator();
	    
	    while (iterator.hasNext()) {
	        Libro libro = iterator.next();
	        iterator.remove();
	    }
	}
	
	public void ricarica(Libro l_new) {
	    List<Libro> copiaLista = new ArrayList<>(lista_libri);

	    for (Libro libro : copiaLista) {
	        if (l_new.getISBN().equals(libro.getISBN())) {
	            lista_libri.remove(libro);
	            this.aggiungi_libro(l_new);
	        }
	    }
	}

	public void ordinaPerTitolo(Libro l) {
		String titoloCercato =l.getTitolo();
        // Ordina la lista con un Comparator personalizzato
        Collections.sort(this.lista_libri, new Comparator<Libro>() {
            @Override
            public int compare(Libro libro1, Libro libro2) {
                // Metti il libro cercato in cima
                if (titoloCercato.equalsIgnoreCase(libro1.getTitolo())) {
                    return -1; // libro1 viene prima di libro2
                } else if (titoloCercato.equalsIgnoreCase(libro2.getTitolo())) {
                    return 1; // libro2 viene prima di libro1
                } else {
                    return libro1.getTitolo().compareToIgnoreCase(libro2.getTitolo());
                }
            }
        });
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
