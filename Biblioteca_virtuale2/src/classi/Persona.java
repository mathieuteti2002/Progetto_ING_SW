package classi;

public abstract class Persona {
    protected String nome;
    protected String cognome;

    // Costruttore della classe base
    public Persona(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    // Metodo astratto da implementare nelle classi derivate
    public abstract void mostraRuolo();
}
