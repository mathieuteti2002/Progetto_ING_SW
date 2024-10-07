package Interfacce;

import Classi.Persona;

public interface PersonaFactory {
    Persona createPersona(String nickname, String pwd);

	Persona createPersona(String user, String passw, String em, int lvl);
}