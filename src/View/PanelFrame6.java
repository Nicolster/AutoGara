package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelFrame6 extends JPanel {
    private JTextField txtNumeCursa, txtData;
    private JButton btnAfiseaza;
    private JTextArea textArea;

    public PanelFrame6() {
        setLayout(null);

        JLabel labelNumeCursa = new JLabel("Introduceți numele cursei:");
        labelNumeCursa.setBounds(50, 30, 200, 25);
        add(labelNumeCursa);

        txtNumeCursa = new JTextField();
        txtNumeCursa.setBounds(50, 60, 200, 25);
        add(txtNumeCursa);

        JLabel labelData = new JLabel("Introduceți data (YYYY-MM-DD):");
        labelData.setBounds(50, 90, 250, 25);
        add(labelData);

        txtData = new JTextField();
        txtData.setBounds(50, 120, 200, 25);
        add(txtData);

        btnAfiseaza = new JButton("Afisează lista pasagerilor");
        btnAfiseaza.setBounds(50, 150, 200, 25);
        add(btnAfiseaza);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(50, 180, 500, 200);
        add(scrollPane);
    }

    public void addAfiseazaPasageriListener(ActionListener listener) {
        btnAfiseaza.addActionListener(listener);
    }

    public String getNumeCursa() {
        return txtNumeCursa.getText();
    }

    public String getData() {
        return txtData.getText();
    }

    public void afiseazaListaPasageri(String listaPasageri) {
        if (listaPasageri.isEmpty()) {
            textArea.setText("Nu există pasageri pentru cursa și data specificată.");
        } else {
            textArea.setText(listaPasageri);
        }
    }
}
