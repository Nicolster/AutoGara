package Controller;


import Model.DatabaseConnection;
import Model.Rezervari;
import View.PanelFrame2;
import Model.dao.RezervareDAO;
import View.RezervariFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.sql.Date;
import java.util.List;

public class RezervareController {
    private PanelFrame2 view;

    private RezervariFrame rezervareview;
    private RezervareDAO rezervareDAO;

    public RezervareController(PanelFrame2 view, RezervareDAO rezervareDAO) {
        this.view = view;
        this.rezervareDAO = rezervareDAO;
        this.view.setAfiseazaLocuriRezervateListener(new AfiseazaLocuriRezervateListener());
    }

    public RezervareController(RezervariFrame rezervareview, RezervareDAO rezervareDAO) {
        this.rezervareview = rezervareview;
        this.rezervareDAO = rezervareDAO;
        this.rezervareview.setActualizeazaRezervareListener(new ActualizeazaRezervareListener());
        this.rezervareview.setAdaugaRezervareListener(new AdaugaRezervareListener());
        this.rezervareview.setAfiseazaRezervareListener(new AfiseazaRezervareListener());
        this.rezervareview.setStergeRezervareListener(new StergeRezervareListener());
    }

    private class ActualizeazaRezervareListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Obțineți datele actualizate pentru rezervare din interfață
                String numePasager = rezervareview.getPasagerID();
                String numeCursa = rezervareview.getCursaID();
                int numarLocuri = rezervareview.getNumarLocuri();
                Date dataRezervare = rezervareview.getDataRezervare();

                // Verificați dacă o rezervare este selectată în tabel
                int selectedRow = rezervareview.getTable().getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(rezervareview, "Selectați o rezervare pentru a o actualiza.", "Eroare", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Obțineți ID-ul rezervării selectate
                int rezervareID = (int) rezervareview.getTable().getValueAt(selectedRow, 0); // presupunând că ID-ul rezervării este primul câmp afișat în tabel

                // Deschideți conexiunea la baza de date
                Connection connection = DatabaseConnection.getConnection();

                // Apelați metoda din rezervareDAO pentru a actualiza rezervarea în baza de date
                rezervareDAO.updateRezervare(connection, rezervareID, numePasager, numeCursa, numarLocuri, dataRezervare);

                // Închideți conexiunea la baza de date
                connection.close();

                // Afișați un mesaj de confirmare că actualizarea a fost realizată cu succes
                JOptionPane.showMessageDialog(rezervareview, "Rezervarea a fost actualizată cu succes.", "Succes", JOptionPane.INFORMATION_MESSAGE);

                // Reafișați lista de rezervări pentru a reflecta modificările
                // Aici puteți apela metoda pentru a reafișa datele din tabel
                // displayAllRezervari();

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(rezervareview, "Eroare la actualizarea rezervării.", "Eroare", JOptionPane.ERROR_MESSAGE);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private class StergeRezervareListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int selectedRow = rezervareview.getTable().getSelectedRow();
                // Obțineți cursa pe care doriți să o ștergeți din interfață sau din altă sursă relevantă
                int rezervareID = (int) rezervareview.getTable().getValueAt(selectedRow, 0); // Presupunând că aveți o metodă pentru a obține ID-ul cursei selectate din interfață

                // Utilizați CursaDAO pentru a șterge cursa din baza de date
                rezervareDAO.deleteRezervare(rezervareID);

                // Afișați un mesaj de confirmare utilizatorului
                JOptionPane.showMessageDialog(rezervareview, "Rezervarea a fost ștearsă cu succes!", "Ștergere reușită", JOptionPane.INFORMATION_MESSAGE);

                // Reafișați lista de curse pentru a reflecta ștergerea

            } catch (SQLException ex) {
                // Gestionați eventualele erori
                ex.printStackTrace();
                JOptionPane.showMessageDialog(rezervareview, "Eroare la ștergerea rezervarii.", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class AdaugaRezervareListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Obținerea datelor de la interfață
                String numePasager = rezervareview.getPasagerID();
                String numeCursa = rezervareview.getCursaID();
                int numarLocuri = rezervareview.getNumarLocuri();
                Date dataRezervare = rezervareview.getDataRezervare();

                // Validarea datelor introduse
                if (numePasager.isEmpty() || numeCursa.isEmpty() || numarLocuri <= 0) {
                    JOptionPane.showMessageDialog(rezervareview, "Introduceți datele complete pentru a adăuga o rezervare.", "Eroare", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Deschiderea conexiunii la baza de date
                Connection connection = DatabaseConnection.getConnection();

                // Adăugarea rezervării în baza de date utilizând RezervareDAO
                rezervareDAO.addRezervare(connection, numePasager, numeCursa, numarLocuri, dataRezervare);

                // Închiderea conexiunii la baza de date
                connection.close();

                // Afișarea unui mesaj de succes
                JOptionPane.showMessageDialog(rezervareview, "Rezervarea a fost adăugată cu succes!", "Succes", JOptionPane.INFORMATION_MESSAGE);



            } catch (SQLException ex) {
                // Afișarea unui mesaj de eroare în caz de eșec
                ex.printStackTrace();
                JOptionPane.showMessageDialog(rezervareview, "Eroare la adăugarea rezervării.", "Eroare", JOptionPane.ERROR_MESSAGE);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private class AfiseazaRezervareListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                List<Rezervari> rezervari = rezervareDAO.getAllRezervari();
                Object[][] data = new Object[rezervari.size()][5];
                int index = 0;
                for (Rezervari rezervare : rezervari) {
                    data[index][0] = rezervare.getRezervareID();
                    data[index][1] = rezervare.getNumePasager(); // Obține numele pasagerului
                    data[index][2] = rezervare.getNumeCursa(); // Obține numele cursei
                    data[index][3] = rezervare.getNumarLocuri();
                    data[index][4] = rezervare.getDataRezervare();
                    index++;
                }
                rezervareview.setTableData(data, new Object[]{"RezervareID", "NumePasager", "NumeCursa", "NumarLocuri", "DataRezervare"});
            } catch (SQLException ex) {
                ex.printStackTrace();
                // Tratarea erorilor de la baza de date
            }
        }
    }

    private class AfiseazaLocuriRezervateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String numePasager = view.getNumePasager();
                String numeCursa = view.getNumeCursa();

                Connection connection = DatabaseConnection.getConnection();
                List<Rezervari> rezervari = rezervareDAO.getRezervariByPasagerAndCursa(connection, numePasager, numeCursa);

                StringBuilder locuriRezervate = new StringBuilder();
                for (Rezervari rezervare : rezervari) {
                    locuriRezervate.append("Loc ").append(rezervare.getNumarLocuri()).append("\n");
                }

                view.displayLocuriRezervate(locuriRezervate.toString());

                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
