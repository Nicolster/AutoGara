package Model;

public class Traseuri {

    private int traseuid;
    private String Nume;

    public Traseuri(int traseuid, String Nume) {
        this.traseuid = traseuid;
        this.Nume = Nume;
    }

    public Traseuri(String Nume) {
        this.Nume = Nume;
    }

    public int getTraseuid() {
        return traseuid;
    }

    public String getNume() {
        return Nume;
    }

}
