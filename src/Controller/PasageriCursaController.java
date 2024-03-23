package Controller;

import Model.DatabaseConnection;
import Model.dao.PasagerDAO;
import View.PanelFrame6;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class PasageriCursaController {
    private PanelFrame6 view;
    private PasagerDAO pasagerDAO;

    public PasageriCursaController(PanelFrame6 view, PasagerDAO pasagerDAO) {
        this.view = view;
        this.pasagerDAO = pasagerDAO;

        this.view.addAfiseazaPasageriListener(new AfiseazaPasageriListener());
    }

    private class AfiseazaPasageriListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String numeCursa = view.getNumeCursa();
                String data = view.getData();

                Connection connection = DatabaseConnection.getConnection();
                String listaPasageri = pasagerDAO.getListaPasageriByCursaSiData(connection, numeCursa, data);

                if (listaPasageri != null) {
                    view.afiseazaListaPasageri(listaPasageri);
                } else {
                    view.afiseazaListaPasageri("Nu există pasageri pentru cursa și data specificată.");
                }

                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
