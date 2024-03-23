package Controller;

import Model.Curse;
import Model.DatabaseConnection;
import Model.Pasageri;
import Model.dao.PasagerDAO;
import View.PanelFrame1;
import View.PasageriFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PasagerController {
    private PanelFrame1 view;

    private PasageriFrame pasageriview;
    private PasagerDAO pasagerDAO;

    public PasagerController(PanelFrame1 view, PasagerDAO pasagerDAO) {
        this.view = view;
        this.pasagerDAO = pasagerDAO;

        this.view.addAfiseazaPasageriListener(new AfiseazaPasageriListener());
    }

    public PasagerController(PasageriFrame pasageriview, PasagerDAO pasagerDAO) {
        this.pasageriview = pasageriview;
        this.pasagerDAO = pasagerDAO;

        this.pasageriview.setAfiseazaPasageriListener(new AfiseazaPasageriiListener());
        this.pasageriview.setStergePasageriListener(new StergePasageriListener());
        this.pasageriview.setActualizeazaPasageriListener(new ActualizeazaPasageriListener());
        this.pasageriview.setAdaugaPasageriListener(new AdaugaPasageriListener());
    }

    private class ActualizeazaPasageriListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Obțineți datele actualizate pentru cursă din interfață
                String numeCursa = pasageriview.getNume();

                // Alte date actualizate, de exemplu numărul de locuri disponibile, tipul de transport etc.

                // Verificați dacă o cursă este selectată în tabel
                int selectedRow = pasageriview.getTable().getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(pasageriview, "Selectați o cursă pentru a o actualiza.", "Eroare", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Obțineți ID-ul cursului selectat
                int cursaID = (int) pasageriview.getTable().getValueAt(selectedRow, 0); // presupunând că ID-ul cursului este primul câmp afișat în tabel

                // Deschideți conexiunea la baza de date
                Connection connection = DatabaseConnection.getConnection();

                // Aici puteți apela metoda din CursaDAO pentru a actualiza cursa în baza de date
                pasagerDAO.updatePasager(connection, cursaID, numeCursa);

                // Închideți conexiunea la baza de date
                connection.close();

                // Afișați un mesaj de confirmare că actualizarea a fost realizată cu succes
                JOptionPane.showMessageDialog(pasageriview, "Cursa a fost actualizată cu succes.", "Succes", JOptionPane.INFORMATION_MESSAGE);

                // Reafișați lista de curse pentru a reflecta modificările
                // Aici puteți apela metoda pentru a reafișa datele din tabel
                // displayAllCurse();

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(pasageriview, "Eroare la actualizarea cursei.", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class StergePasageriListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                int selectedRow = pasageriview.getTable().getSelectedRow();
                // Obțineți cursa pe care doriți să o ștergeți din interfață sau din altă sursă relevantă
                int pasagerID = (int) pasageriview.getTable().getValueAt(selectedRow, 0); // Presupunând că aveți o metodă pentru a obține ID-ul cursei selectate din interfață

                // Utilizați CursaDAO pentru a șterge cursa din baza de date
                pasagerDAO.deletePasager(pasagerID);

                // Afișați un mesaj de confirmare utilizatorului
                JOptionPane.showMessageDialog(pasageriview, "Cursa a fost ștearsă cu succes!", "Ștergere reușită", JOptionPane.INFORMATION_MESSAGE);

                // Reafișați lista de curse pentru a reflecta ștergerea

            } catch (SQLException ex) {
                // Gestionați eventualele erori
                ex.printStackTrace();
                JOptionPane.showMessageDialog(pasageriview, "Eroare la ștergerea cursei.", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class AdaugaPasageriListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Obținerea datelor de la interfață
                String nume = pasageriview.getNume();


                // Crearea unei noi curse
                Pasageri Pasager = new Pasageri(nume);

                // Adăugarea cursei în baza de date utilizând CursaDAO
                pasagerDAO.addPasager(Pasager);

                // Afișarea unui mesaj de succes
                JOptionPane.showMessageDialog(pasageriview, "Pasager a fost adăugată cu succes!", "Succes", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                // Afișarea unui mesaj de eroare în caz de eșec
                ex.printStackTrace();
                JOptionPane.showMessageDialog(pasageriview, "Eroare la adăugarea pasagerului.", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class AfiseazaPasageriiListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                List<Pasageri> pasageri = pasagerDAO.getAllPasageri();
                Object[][] data = new Object[pasageri.size()][2];
                int index = 0;
                for (Pasageri pasager : pasageri) {
                    data[index][0] = pasager.getId();
                    data[index][1] = pasager.getNume();
                    index++;
                }
                pasageriview.setTableData(data, new Object[]{"PasagerID", "Nume"});
            } catch (SQLException ex) {
                ex.printStackTrace();
                // Tratarea erorilor de la baza de date
            }
        }
    }

    private class AfiseazaPasageriListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String numeCursa = view.getNumeCursa();

                Connection connection = DatabaseConnection.getConnection();
                List<Pasageri> pasageri = pasagerDAO.getPasageriByCursa(connection, numeCursa);

                StringBuilder listaPasageri = new StringBuilder();
                if (!pasageri.isEmpty()) {
                    for (Pasageri pasager : pasageri) {
                        listaPasageri.append(pasager.getNume()).append("\n");
                    }
                } else {
                    listaPasageri.append("Nu există pasageri pentru cursa specificată.");
                }

                view.afiseazaPasageri(listaPasageri.toString());

                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
