package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gui.Login;

class JunitTestLogin {
	private Login login;

	public void setUp() {
		login = new Login(); // Inizializza la classe prima di ogni test
	}

	@Test
	public void testUtenteNormale() {
		// Simula un utente normale con livello 1
		boolean risultato = login.ricercaPassword("reda", "selmani"); //
		assertTrue(risultato, "Dovrebbe restituire true per un utente normale");
	}

	@Test
	public void testAdmin() {
		// Simula un admin con livello 0
		boolean risultato = login.ricercaPassword("admin", "admin");
		assertFalse(risultato, "Dovrebbe restituire false per un admin");
	}

	@Test
	public void testCredenzialiErrate() {
		// Simula il caso in cui l'utente o la password non sono corretti
		boolean risultato = login.ricercaPassword("prova", "errata");
		assertFalse(risultato, "Dovrebbe restituire false per credenziali errate");
	}

}