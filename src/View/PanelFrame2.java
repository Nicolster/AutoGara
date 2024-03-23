package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelFrame2 extends JPanel {
    private JTextField txtNumePasager, txtNumeCursa;
    private JButton btnAfiseaza;
    private JTextArea textArea;

    public PanelFrame2() {
        setLayout(null);

        JLabel labelNumePasager = new JLabel("Nume Pasager:");
        labelNumePasager.setBounds(50, 30, 200, 25);
        add(labelNumePasager);

        txtNumePasager = new JTextField();
        txtNumePasager.setBounds(50, 60, 200, 25);
        add(txtNumePasager);

        JLabel labelNumeCursa = new JLabel("Nume Cursa:");
        labelNumeCursa.setBounds(50, 90, 200, 25);
        add(labelNumeCursa);

        txtNumeCursa = new JTextField();
        txtNumeCursa.setBounds(50, 120, 200, 25);
        add(txtNumeCursa);

        btnAfiseaza = new JButton("Afiseaza locurile rezervate");
        btnAfiseaza.setBounds(50, 150, 200, 25);
        add(btnAfiseaza);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(50, 180, 500, 200);
        add(scrollPane);
    }

    public String getNumePasager() {
        return txtNumePasager.getText();
    }

    public String getNumeCursa() {
        return txtNumeCursa.getText();
    }

    public void setAfiseazaLocuriRezervateListener(ActionListener listener) {
        btnAfiseaza.addActionListener(listener);
    }

    public void displayLocuriRezervate(String locuriRezervate) {
        textArea.setText(locuriRezervate);
    }
}
