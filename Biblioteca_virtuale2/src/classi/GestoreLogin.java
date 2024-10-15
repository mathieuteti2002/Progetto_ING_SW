package classi;

import interfacce.AdminFactory;
import interfacce.PersonaFactory;
import interfacce.UtenteFactory;

public class GestoreLogin {
    
    public Persona login(String tipoUtente, String nicknameOrUsername, String pwdOrPassw, String email, int lvl) {
        PersonaFactory factory;
        Persona persona = null;

        // Determiniamo quale factory usare
        if (tipoUtente.equalsIgnoreCase("admin")) {
            factory = new AdminFactory();
            persona = factory.createPersona(nicknameOrUsername, pwdOrPassw);
        } else if (tipoUtente.equalsIgnoreCase("utente")) {
            factory = new UtenteFactory();
            persona = factory.createPersona(nicknameOrUsername, pwdOrPassw, email, lvl);
        }

        return persona; // Restituisce l'oggetto creato
    }
}
