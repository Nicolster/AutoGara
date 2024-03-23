package Controller;

import Model.DatabaseConnection;
import Model.dao.CursaDAO;
import View.ExportFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ExportController {
    private ExportFrame view;
    private CursaDAO cursaDAO;
    private ActionListener exportSuccessMessage;

    public ExportController(ExportFrame view, CursaDAO cursaDAO) {
        this.view = view;
        this.cursaDAO = cursaDAO;

        this.view.addExportListener(new ExportListener());
    }

    public void addExportSuccessMessage(ActionListener listener) {
        this.exportSuccessMessage = listener;
    }

    private class ExportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String numeFisier = view.getNumeFisier();
                if (numeFisier.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Numele fișierului nu poate fi gol.", "Eroare", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Connection connection = DatabaseConnection.getConnection();
                cursaDAO.exportListaCurselorSolicitate(connection, numeFisier);
                connection.close();

                if (exportSuccessMessage != null) {
                    exportSuccessMessage.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
                }

                view.dispose();
            } catch (SQLException | IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(view, "A apărut o eroare la exportarea listei curselor solicitate. Verificați consola pentru detalii.", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
