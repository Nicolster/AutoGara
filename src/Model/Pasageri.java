package Model;

public class Pasageri {

    private int id;
    private String nume;

    public Pasageri(String nume) {
        this.nume = nume;
    }
    public Pasageri(int id, String nume) {
        this.id = id;
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
