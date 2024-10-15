package interfacce;

import classi.Libro;
import classi.Persona;
import classi.Utente;

public class UtenteFactory implements PersonaFactory {
    @Override
    public Persona createPersona(String user, String passw, String em, int lvl) {
        return new Utente(user, passw, em, lvl);
    }

	@Override
	public Persona createPersona(String nickname, String pwd) {
		// TODO Auto-generated method stub
		return null;
	}
}