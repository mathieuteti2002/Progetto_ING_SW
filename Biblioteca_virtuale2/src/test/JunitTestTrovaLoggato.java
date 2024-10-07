package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import GUI.GUI_bibliotecaUtente;

class JunitTestTrovaLoggato {

	private GUI_bibliotecaUtente guiBibliotecaUtente;

    public void setUp() {
        try {
			guiBibliotecaUtente = new GUI_bibliotecaUtente();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  // Inizializza la classe prima di ogni test
    }

    @Test
    public void testUtenteLoggato() {
        // Simula il caso in cui un utente è attualmente loggato
        String utenteLoggato = guiBibliotecaUtente.trovaloggato();
        assertEquals("utente1", utenteLoggato, "Dovrebbe restituire 'utente1' come utente loggato");
    }

    @Test
    public void testUltimoUtenteLoggato() {
        // Simula il caso in cui nessun utente è attualmente loggato, ma l'ultimo loggato ha isLogged = 1
        String utenteLoggato = guiBibliotecaUtente.trovaloggato();
        assertEquals("ultimoUtenteLoggato", utenteLoggato, "Dovrebbe restituire 'ultimoUtenteLoggato' se nessun utente è attualmente loggato");
    }

    @Test
    public void testNessunUtenteLoggato() {
        // Simula il caso in cui non ci sono utenti loggati e l'ultimo loggato non è disponibile
        String utenteLoggato = guiBibliotecaUtente.trovaloggato();
        assertEquals("", utenteLoggato, "Dovrebbe restituire una stringa vuota se nessun utente è loggato");
    }


}