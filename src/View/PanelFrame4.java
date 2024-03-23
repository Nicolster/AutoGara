package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelFrame4 extends JPanel {
    private JTextField txtNumeTraseu;
    private JButton btnAfiseaza;
    private JTextArea textArea;

    public PanelFrame4() {
        setLayout(null);

        JLabel labelNumeTraseu = new JLabel("Introduceți numele traseului auto:");
        labelNumeTraseu.setBounds(50, 30, 250, 25);
        add(labelNumeTraseu);

        txtNumeTraseu = new JTextField();
        txtNumeTraseu.setBounds(50, 60, 200, 25);
        add(txtNumeTraseu);

        btnAfiseaza = new JButton("Afisează lista curselor");
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

    public String getNumeTraseu() {
        return txtNumeTraseu.getText();
    }

    public void afiseazaListaCursuri(String listaCursuri) {
        if (listaCursuri.isEmpty()) {
            textArea.setText("Nu există curse pentru traseul specificat.");
        } else {
            textArea.setText(listaCursuri);
        }
    }
}
