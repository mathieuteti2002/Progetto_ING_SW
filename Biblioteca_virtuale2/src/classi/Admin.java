package classi;

public class Admin extends Persona {
    private String nickname;
    private String pwd;

    public Admin(String nickname, String pwd) {
        super(nickname, ""); // Passiamo il nickname come nome e lasciamo vuoto il cognome
        this.nickname = nickname;
        this.pwd = pwd;
    }

    @Override
    public void mostraRuolo() {
        System.out.println("Sono un Admin.");
    }
}
