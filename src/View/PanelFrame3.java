package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelFrame3 extends JPanel {
    private JTextField txtNumeCursa;
    private JButton btnAfiseaza;
    private JTextArea textArea;

    public PanelFrame3() {
        setLayout(null);

        JLabel labelNumeCursa = new JLabel("Introduceți numele cursei:");
        labelNumeCursa.setBounds(50, 30, 200, 25);
        add(labelNumeCursa);

        txtNumeCursa = new JTextField();
        txtNumeCursa.setBounds(50, 60, 200, 25);
        add(txtNumeCursa);

        btnAfiseaza = new JButton("Afisează informațiile despre cursă");
        btnAfiseaza.setBounds(50, 90, 250, 25);
        add(btnAfiseaza);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(50, 130, 500, 200);
        add(scrollPane);
    }

    public void addAfiseazaInfoCursaListener(ActionListener listener) {
        btnAfiseaza.addActionListener(listener);
    }

    public String getNumeCursa() {
        return txtNumeCursa.getText();
    }

    public void afiseazaInfoCursa(String infoCursa) {
        textArea.setText(infoCursa);
    }
}
