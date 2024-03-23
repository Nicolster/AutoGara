package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelFrame5 extends JPanel {
    private JTextField txtDestinatie;
    private JButton btnAfiseaza;
    private JTextArea textArea;

    public PanelFrame5() {
        setLayout(null);

        JLabel labelDestinatie = new JLabel("Introduceți destinația:");
        labelDestinatie.setBounds(50, 30, 200, 25);
        add(labelDestinatie);

        txtDestinatie = new JTextField();
        txtDestinatie.setBounds(50, 60, 200, 25);
        add(txtDestinatie);

        btnAfiseaza = new JButton("Afisează lista cursurilor");
        btnAfiseaza.setBounds(50, 90, 200, 25);
        add(btnAfiseaza);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(50, 130, 500, 200);
        add(scrollPane);
    }

    public void addAfiseazaCursuriListener(ActionListener listener) {
        btnAfiseaza.addActionListener(listener);
    }

    public String getDestinatie() {
        return txtDestinatie.getText();
    }

    public void afiseazaListaCursuri(String listaCursuri) {
        if (listaCursuri.isEmpty()) {
            textArea.setText("Nu există curse pentru destinația specificată.");
        } else {
            textArea.setText(listaCursuri);
        }
    }
}
