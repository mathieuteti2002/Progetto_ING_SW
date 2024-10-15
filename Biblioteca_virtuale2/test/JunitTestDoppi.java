package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gui.RegisterUtente;

class JunitTestDoppi {
    private RegisterUtente registerUtente;

 
    public void setUp() {
        registerUtente = new RegisterUtente();  // Inizializza la classe prima di ogni test
    }

    @Test
    public void testNessunDuplicato() {
        // Simula il caso in cui non ci sono duplicati
        boolean risultato = registerUtente.doppi("redaselmani0@email.com", "reda");
        assertTrue(risultato, "Dovrebbe restituire true quando non ci sono duplicati");
    }

    @Test
    public void testEmailDuplicata() {
        // Simula il caso in cui l'email esiste gia
        boolean risultato = registerUtente.doppi("mathteti@email.com", "math");
        assertFalse(risultato, "Dovrebbe restituire false quando l'email � gi� presente");
    }

    @Test
    public void testUtenteDuplicato() {
        // Simula il caso in cui l'utente esiste gia
        boolean risultato = registerUtente.doppi("riccardobonfanti@email.com", "riccardo");
        assertFalse(risultato, "Dovrebbe restituire false quando l'utente � gi� presente");
    }

    @Test
    public void testEmailEUtenteDuplicati() {
        // Simula il caso in cui sia l'email che l'utente esistono gia
        boolean risultato = registerUtente.doppi("emailesistente@email.com", "utenteesistente");
        assertFalse(risultato, "Dovrebbe restituire false quando sia l'email che l'utente sono gi� presenti");
    }

}