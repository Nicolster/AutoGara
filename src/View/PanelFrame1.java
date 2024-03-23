package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelFrame1 extends JPanel {

    private JTextField txtNumeCursa;
    private JButton btnAfiseaza;
    private JTextArea textArea;

    // Constructor
    public PanelFrame1() {
        setLayout(null);

        JLabel labelNumeCursa = new JLabel("Introduceti numele cursei:");
        labelNumeCursa.setBounds(50, 30, 200, 25);
        add(labelNumeCursa);

        txtNumeCursa = new JTextField();
        txtNumeCursa.setBounds(50, 60, 200, 25);
        add(txtNumeCursa);

        btnAfiseaza = new JButton("Afiseaza pasagerii");
        btnAfiseaza.setBounds(50, 90, 200, 25);
        add(btnAfiseaza);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(50, 130, 500, 200);
        add(scrollPane);
    }

    public void addAfiseazaPasageriListener(ActionListener listener) {
        btnAfiseaza.addActionListener(listener);
    }

    public String getNumeCursa() {
        return txtNumeCursa.getText();
    }

    public void afiseazaPasageri(String pasageri) {
        if (pasageri.isEmpty()) {
            textArea.setText("Nu există pasageri pentru cursa specificată.");
        } else {
            textArea.setText(pasageri);
        }
    }

}
