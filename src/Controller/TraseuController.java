package Controller;

import Model.DatabaseConnection;
import Model.Pasageri;
import Model.Traseuri;
import Model.dao.CursaDAO;
import Model.dao.TraseuDAO;
import View.PanelFrame4;
import View.TraseuriFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TraseuController {
    private PanelFrame4 view;
    private TraseuriFrame traseuriview;
    private CursaDAO cursaDAO;
    private TraseuDAO traseuDAO;

    public TraseuController(PanelFrame4 view, CursaDAO cursaDAO) {
        this.view = view;
        this.cursaDAO = cursaDAO;

        this.view.addAfiseazaCursuriListener(new AfiseazaCursuriListener());
    }

    public TraseuController(TraseuriFrame traseuriview, TraseuDAO traseuDAO) {
        this.traseuriview = traseuriview;
        this.traseuDAO = traseuDAO;

        this.traseuriview.setActualizeazaTraseuListener(new ActualizeazaTraseuListener());
        this.traseuriview.setAdaugaTraseuListener(new AdaugaTraseuListener());
        this.traseuriview.setAfiseazaTraseuListener(new AfiseazaTraseuListener());
        this.traseuriview.setStergeTraseuListener(new StergeTraseuListener());
    }

    private class StergeTraseuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                int selectedRow = traseuriview.getTable().getSelectedRow();
                // Obțineți cursa pe care doriți să o ștergeți din interfață sau din altă sursă relevantă
                int traseuID = (int) traseuriview.getTable().getValueAt(selectedRow, 0); // Presupunând că aveți o metodă pentru a obține ID-ul cursei selectate din interfață

                // Utilizați CursaDAO pentru a șterge cursa din baza de date
                traseuDAO.deleteTraseu(traseuID);

                // Afișați un mesaj de confirmare utilizatorului
                JOptionPane.showMessageDialog(traseuriview, "Cursa a fost ștearsă cu succes!", "Ștergere reușită", JOptionPane.INFORMATION_MESSAGE);

                // Reafișați lista de curse pentru a reflecta ștergerea

            } catch (SQLException ex) {
                // Gestionați eventualele erori
                ex.printStackTrace();
                JOptionPane.showMessageDialog(traseuriview, "Eroare la ștergerea cursei.", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class AdaugaTraseuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Obținerea datelor de la interfață
                String nume = traseuriview.getNume();


                // Crearea unei noi curse
                Traseuri traseuri = new Traseuri(nume);

                // Adăugarea cursei în baza de date utilizând CursaDAO
                traseuDAO.addTraseu(traseuri);

                // Afișarea unui mesaj de succes
                JOptionPane.showMessageDialog(traseuriview, "Pasager a fost adăugată cu succes!", "Succes", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                // Afișarea unui mesaj de eroare în caz de eșec
                ex.printStackTrace();
                JOptionPane.showMessageDialog(traseuriview, "Eroare la adăugarea pasagerului.", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class AfiseazaTraseuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                List<Traseuri> traseuri = traseuDAO.getAllTraseuri();
                Object[][] data = new Object[traseuri.size()][2];
                int index = 0;
                for (Traseuri traseu : traseuri) {
                    data[index][0] = traseu.getTraseuid();
                    data[index][1] = traseu.getNume();
                    index++;
                }
                traseuriview.setTableData(data, new Object[]{"TraseulID", "Nume"});
            } catch (SQLException ex) {
                ex.printStackTrace();
                // Tratarea erorilor de la baza de date
            }
        }
    }

    private class ActualizeazaTraseuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Obțineți datele actualizate pentru cursă din interfață
                String Nume = traseuriview.getNume();

                // Alte date actualizate, de exemplu numărul de locuri disponibile, tipul de transport etc.

                // Verificați dacă o cursă este selectată în tabel
                int selectedRow = traseuriview.getTable().getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(traseuriview, "Selectați o cursă pentru a o actualiza.", "Eroare", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Obțineți ID-ul cursului selectat
                int traseuID = (int) traseuriview.getTable().getValueAt(selectedRow, 0); // presupunând că ID-ul cursului este primul câmp afișat în tabel

                // Deschideți conexiunea la baza de date
                Connection connection = DatabaseConnection.getConnection();

                // Aici puteți apela metoda din CursaDAO pentru a actualiza cursa în baza de date
                traseuDAO.updateTraseu(connection, traseuID, Nume);

                // Închideți conexiunea la baza de date
                connection.close();

                // Afișați un mesaj de confirmare că actualizarea a fost realizată cu succes
                JOptionPane.showMessageDialog(traseuriview, "Cursa a fost actualizată cu succes.", "Succes", JOptionPane.INFORMATION_MESSAGE);

                // Reafișați lista de curse pentru a reflecta modificările
                // Aici puteți apela metoda pentru a reafișa datele din tabel
                // displayAllCurse();

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(traseuriview, "Eroare la actualizarea cursei.", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class AfiseazaCursuriListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String numeTraseu = view.getNumeTraseu();

                Connection connection = DatabaseConnection.getConnection();
                String listaCursuri = cursaDAO.getListaCursuriByTraseu(connection, numeTraseu);

                if (listaCursuri != null) {
                    view.afiseazaListaCursuri(listaCursuri);
                } else {
                    view.afiseazaListaCursuri("Nu există curse pentru traseul specificat.");
                }

                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
