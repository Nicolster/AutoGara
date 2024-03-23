package Model;

public class Curse {
    private int cursaID;
    private String nume;
    private String destinatie;
    private int capacitate;
    private String tipTransport;

    private String TraseuNume;


    public Curse() {
    }

    public Curse(int cursaID, String nume, String destinatie, int capacitate, String tipTransport, String TraseuNume) {
        this.cursaID = cursaID;
        this.nume = nume;
        this.destinatie = destinatie;
        this.capacitate = capacitate;
        this.tipTransport = tipTransport;
        this.TraseuNume = TraseuNume;
    }

    public Curse(String nume, String destinatie, int capacitate, String tipTransport, int TraseulID) {
        this.nume = nume;
        this.destinatie = destinatie;
        this.capacitate = capacitate;
        this.tipTransport = tipTransport;
    }

    public Curse(String tipTransport, int capacitate) {
        this.tipTransport = tipTransport;
        this.capacitate = capacitate;
    }

    public int getCursaID() {
        return cursaID;
    }

    public void setCursaID(int cursaID) {
        this.cursaID = cursaID;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getDestinatie() {
        return destinatie;
    }

    public void setDestinatie(String destinatie) {
        this.destinatie = destinatie;
    }

    public int getCapacitate() {
        return capacitate;
    }

    public void setCapacitate(int capacitate) {
        this.capacitate = capacitate;
    }

    public String getTipTransport() {
        return tipTransport;
    }

    public void setTipTransport(String tipTransport) {
        this.tipTransport = tipTransport;
    }

    public String getTraseulNume() {
        return TraseuNume;
    }

    public void setTraseulNume(String TraseuNume) {
        this.TraseuNume = TraseuNume;
    }

    @Override
    public String toString() {
        return "Cursa{" +
                "cursaID=" + cursaID +
                ", nume='" + nume + '\'' +
                ", destinatie='" + destinatie + '\'' +
                ", capacitate=" + capacitate +
                ", tipTransport='" + tipTransport + '\'' +
                '}';
    }
}
