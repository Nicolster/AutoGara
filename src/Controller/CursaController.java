package Controller;

import Model.DatabaseConnection;
import Model.Curse;
import Model.dao.CursaDAO;
import View.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CursaController {
    private PanelFrame3 view3;
    private PanelFrame5 view5;
    private PanelFrame8 view8;

    private PanelFrame9 viewDestinatii;

    private PanelFrame10 view10;

    private CurseFrame cursaview;
    private CursaDAO cursaDAO;

    public CursaController(PanelFrame3 view3, CursaDAO cursaDAO) {
        this.view3 = view3;
        this.cursaDAO = cursaDAO;

        this.view3.addAfiseazaInfoCursaListener(new AfiseazaInfoCursaListener());
    }

    public CursaController(PanelFrame5 view5, CursaDAO cursaDAO) {
        this.view5 = view5;
        this.cursaDAO = cursaDAO;

        this.view5.addAfiseazaCursuriListener(new AfiseazaCursuriListener());
    }

    public CursaController(PanelFrame8 view8, CursaDAO cursaDAO) {
        this.view8 = view8;
        this.cursaDAO = cursaDAO;

        this.view8.addCautaListener(new CautaListener());
    }

    public CursaController(PanelFrame9 viewDestinatii, CursaDAO cursaDAO) {
        this.viewDestinatii = viewDestinatii;
        this.cursaDAO = cursaDAO;

        this.viewDestinatii.addAfiseazaDestinatiiListener(new AfiseazaDestinatiiListener());
    }

    public CursaController(PanelFrame10 view10, CursaDAO cursaDAO) {
        this.view10 = view10;
        this.cursaDAO = cursaDAO;

        this.view10.addAfiseazaPasageriListener(new AfiseazaPasageriListener());
    }

    public CursaController(CurseFrame cursaview, CursaDAO cursaDAO) {
        this.cursaview = cursaview;
        this.cursaDAO = cursaDAO;

        // Adăugare de ascultători pentru butoane
        this.cursaview.setAdaugaListener(new AdaugaListener());
        this.cursaview.setAfiseazaListener(new AfiseazaListener());
        this.cursaview.setActualizeazaListener(new ActualizeazaListener());
        this.cursaview.setStergeListener(new StergeListener());
    }

    // Listener pentru butonul de adăugare
    private class AdaugaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Obținerea datelor de la interfață
                String nume = cursaview.getNume();
                String destinatie = cursaview.getDestinatie();
                int capacitate = cursaview.getCapacitate();
                String tipTransport = cursaview.getTipTransport();
                int TraseulID = Integer.parseInt(cursaview.getTraseulID());

                // Crearea unei noi curse
                Curse cursa = new Curse(nume, destinatie, capacitate, tipTransport, TraseulID);

                // Adăugarea cursei în baza de date utilizând CursaDAO
                cursaDAO.addCursa(cursa);

                // Afișarea unui mesaj de succes
                JOptionPane.showMessageDialog(cursaview, "Cursa a fost adăugată cu succes!", "Succes", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                // Afișarea unui mesaj de eroare în caz de eșec
                ex.printStackTrace();
                JOptionPane.showMessageDialog(cursaview, "Eroare la adăugarea cursei.", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Listener pentru butonul de afișare
    private class AfiseazaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                List<Curse> curse = cursaDAO.getAllCurse();
                Object[][] data = new Object[curse.size()][6];
                int index = 0;
                for (Curse cursa : curse) {
                    data[index][0] = cursa.getCursaID();
                    data[index][1] = cursa.getNume();
                    data[index][2] = cursa.getDestinatie();
                    data[index][3] = cursa.getCapacitate();
                    data[index][4] = cursa.getTipTransport();
                    data[index][5] = cursa.getTraseulNume();
                    index++;
                }
                cursaview.setTableData(data, new Object[]{"ID", "Nume", "Destinatie", "Capacitate", "TipTransport", "TraseulNume"});
            } catch (SQLException ex) {
                ex.printStackTrace();
                // Tratarea erorilor de la baza de date
            }
        }
    }

    // Listener pentru butonul de actualizare
    private class ActualizeazaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Verificați dacă o cursă este selectată în tabel
                int selectedRow = cursaview.getTable().getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(cursaview, "Selectați o cursă pentru a o actualiza.", "Eroare", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Obțineți datele actualizate pentru cursă din interfață
                String numeCursa = cursaview.getNume();
                String destinatie = cursaview.getDestinatie();
                String tipTransport = cursaview.getTipTransport();
                int capacitate = cursaview.getCapacitate();


                // Obțineți ID-ul cursului selectat
                int cursaID = (int) cursaview.getTable().getValueAt(selectedRow, 0);

                // Deschideți conexiunea la baza de date
                Connection connection = DatabaseConnection.getConnection();

                cursaview.setNume(numeCursa);
                cursaview.setDestinatie(destinatie);
                cursaview.setCapacitate(capacitate);
                cursaview.setTipTransport(tipTransport);

                // Actualizați cursa în baza de date
                cursaDAO.updateCursa(connection, cursaID, numeCursa, destinatie, capacitate, tipTransport);

                // Închideți conexiunea la baza de date
                connection.close();

                // Afișați un mesaj de confirmare că actualizarea a fost realizată cu succes
                JOptionPane.showMessageDialog(cursaview, "Cursa a fost actualizată cu succes.", "Succes", JOptionPane.INFORMATION_MESSAGE);

                // Reafișați lista de curse pentru a reflecta modificările
                // Aici puteți apela metoda pentru a reafișa datele din tabel
                // displayAllCurse();

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(cursaview, "Eroare la actualizarea cursei.", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
    }



    // Listener pentru butonul de ștergere
    private class StergeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                int selectedRow = cursaview.getTable().getSelectedRow();
                // Obțineți cursa pe care doriți să o ștergeți din interfață sau din altă sursă relevantă
                int cursaID = (int) cursaview.getTable().getValueAt(selectedRow, 0); // Presupunând că aveți o metodă pentru a obține ID-ul cursei selectate din interfață

                // Utilizați CursaDAO pentru a șterge cursa din baza de date
                cursaDAO.deleteCursa(cursaID);

                // Afișați un mesaj de confirmare utilizatorului
                JOptionPane.showMessageDialog(cursaview, "Cursa a fost ștearsă cu succes!", "Ștergere reușită", JOptionPane.INFORMATION_MESSAGE);

                // Reafișați lista de curse pentru a reflecta ștergerea

            } catch (SQLException ex) {
                // Gestionați eventualele erori
                ex.printStackTrace();
                JOptionPane.showMessageDialog(cursaview, "Eroare la ștergerea cursei.", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
    }



    private class AfiseazaInfoCursaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String numeCursa = view3.getNumeCursa();

                Connection connection = DatabaseConnection.getConnection();
                Curse infoCursa = cursaDAO.getInfoCursa(connection, numeCursa);

                if (infoCursa != null) {
                    String result = "Tip transport: " + infoCursa.getTipTransport() + "\n" +
                            "Capacitate: " + infoCursa.getCapacitate();
                    view3.afiseazaInfoCursa(result);
                } else {
                    view3.afiseazaInfoCursa("Nu există informații despre cursa specificată.");
                }

                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private class AfiseazaCursuriListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String destinatie = view5.getDestinatie();

                Connection connection = DatabaseConnection.getConnection();
                String listaCursuri = cursaDAO.getListaCursuriByDestinatie(connection, destinatie);

                if (listaCursuri != null) {
                    view5.afiseazaListaCursuri(listaCursuri);
                } else {
                    view5.afiseazaListaCursuri("Nu există curse pentru destinația specificată.");
                }

                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private class CautaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String localitate = view8.getLocalitate();
            try {
                List<String> numeCursuri = cursaDAO.getCursaByLocalitate(localitate);
                if (!numeCursuri.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    for (String numeCursa : numeCursuri) {
                        sb.append(numeCursa).append("\n");
                    }
                    view8.afiseazaCursa(sb.toString());
                } else {
                    view8.afiseazaCursa("Nu s-au găsit curse pentru localitatea specificată.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                view8.afiseazaCursa("A apărut o eroare la căutarea curselor. Verificați consola pentru detalii.");
            }
        }
    }

    private class AfiseazaDestinatiiListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                List<String> destinatii = cursaDAO.getDestinatiiDisponibile();

                viewDestinatii.displayDestinatii(destinatii);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(viewDestinatii, "Eroare la obținerea destinațiilor disponibile.", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class AfiseazaPasageriListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int luna = view10.getLuna();
            int an = view10.getAn();
            try {
                List<String> pasageri = cursaDAO.getListaPasageriByLunaSiAn(luna, an);
                if (!pasageri.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    for (String pasager : pasageri) {
                        sb.append(pasager).append("\n");
                    }
                    view10.afiseazaPasageri(sb.toString());
                } else {
                    view10.afiseazaPasageri("Nu s-au găsit pasageri pentru luna și anul specificate.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                view10.afiseazaPasageri("A apărut o eroare la obținerea listei de pasageri. Verificați consola pentru detalii.");
            }
        }
    }

}
