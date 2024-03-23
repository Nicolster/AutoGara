package Model;

import java.sql.Date;


public class Rezervari {

    private int RezervareID;
    private String PasagerID;
    private String CursaID;
    private Date DataRezervare;
    private String numePasager;
    private String numeCursa;
    private int numarLocuri;

    public Rezervari(String numePasager, String numeCursa, int numarLocuri) {
        this.numePasager = numePasager;
        this.numeCursa = numeCursa;
        this.numarLocuri = numarLocuri;
    }

    public Rezervari(int rezervareID, String numePasager, String numeCursa, int numarLocuri, Date dataRezervare) {
        this.RezervareID = rezervareID;
        this.numePasager = numePasager;
        this.numeCursa = numeCursa;
        this.numarLocuri = numarLocuri;
        this.DataRezervare = dataRezervare;
    }

    public String getNumePasager() {
        return numePasager;
    }

    public void setNumePasager(String numePasager) {
        this.numePasager = numePasager;
    }

    public String getNumeCursa() {
        return numeCursa;
    }

    public void setNumeCursa(String numeCursa) {
        this.numeCursa = numeCursa;
    }

    public int getNumarLocuri() {
        return numarLocuri;
    }



    public void setNumarLocuri(int numarLocuri) {
        this.numarLocuri = numarLocuri;
    }

    public int getRezervareID() {
        return RezervareID;
    }

//    public void setRezervareID(String rezervareID) {
//        RezervareID = rezervareID;
//    }

    public String getPasagerID() {
        return PasagerID;
    }

    public void setPasagerID(String pasagerID) {
        PasagerID = pasagerID;
    }

//    public String getCursaID() {
//        return CursaID;
//    }

    public void setCursaID(String cursaID) {
        CursaID = cursaID;
    }

    public java.sql.Date getDataRezervare() {
        return DataRezervare;
    }

    public void setDataRezervare(Date dataRezervare) {
        DataRezervare = dataRezervare;
    }
}
