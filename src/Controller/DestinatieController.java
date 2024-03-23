package Controller;

import Model.DatabaseConnection;
import Model.dao.CursaDAO;
import View.PanelFrame5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class DestinatieController {
    private PanelFrame5 view;
    private CursaDAO cursaDAO;

    public DestinatieController(PanelFrame5 view, CursaDAO cursaDAO) {
        this.view = view;
        this.cursaDAO = cursaDAO;

        this.view.addAfiseazaCursuriListener(new AfiseazaCursuriListener());
    }

    private class AfiseazaCursuriListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String destinatie = view.getDestinatie();

                Connection connection = DatabaseConnection.getConnection();
                String listaCursuri = cursaDAO.getListaCursuriByDestinatie(connection, destinatie);

                if (listaCursuri != null) {
                    view.afiseazaListaCursuri(listaCursuri);
                } else {
                    view.afiseazaListaCursuri("Nu există curse pentru destinația specificată.");
                }

                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
