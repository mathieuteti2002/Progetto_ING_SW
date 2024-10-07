package Interfacce;

import Classi.Admin;
import Classi.Persona;

public class AdminFactory implements PersonaFactory {
    @Override
    public Persona createPersona(String nickname, String pwd) {
        return new Admin(nickname, pwd);
    }

	@Override
	public Persona createPersona(String user, String passw, String em, int lvl) {
		// TODO Auto-generated method stub
		return null;
	}
}