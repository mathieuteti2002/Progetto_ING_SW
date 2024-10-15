package classi;
import java.util.ArrayList;
import java.util.List;

public class Utente extends Persona {
    private String username;
    private String password;
    private String email;
    private int lvlAccesso;
    private List<String> l_prenotati;

    public Utente(String user, String passw, String em, int lvl) {
        super(user, ""); // Passiamo il nome come username e lasciamo vuoto il cognome
        this.username = user;
        this.password = passw;
        this.email = em;
        this.lvlAccesso = lvl;
        this.l_prenotati = new ArrayList<>(); // lista dei libri inizializzata a 0
    }

    @Override
    public void mostraRuolo() {
        System.out.println("Sono un Utente.");
    }
}