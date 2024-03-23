package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ExportFrame extends JFrame {
    private JTextField txtNumeFisier;
    private JButton btnExport;

    public ExportFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Export lista curselor solicitate");
        setBounds(100, 100, 400, 150);
        getContentPane().setLayout(null);

        JLabel lblNumeFisier = new JLabel("Nume fișier:");
        lblNumeFisier.setBounds(20, 20, 100, 25);
        getContentPane().add(lblNumeFisier);

        txtNumeFisier = new JTextField();
        txtNumeFisier.setBounds(130, 20, 200, 25);
        getContentPane().add(txtNumeFisier);
        txtNumeFisier.setColumns(10);

        btnExport = new JButton("Export");
        btnExport.setBounds(130, 60, 100, 25);
        getContentPane().add(btnExport);
    }

    public void addExportListener(ActionListener listener) {
        btnExport.addActionListener(listener);
    }

    public String getNumeFisier() {
        return txtNumeFisier.getText();
    }
}
