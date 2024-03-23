package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelFrame8 extends JFrame {
    private JTextField txtLocalitate;
    private JButton btnCauta;
    private JTextArea textArea;

    public PanelFrame8() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Afișare cursă după localitate");
        setBounds(100, 100, 400, 300);
        getContentPane().setLayout(null);

        JLabel lblLocalitate = new JLabel("Localitate:");
        lblLocalitate.setBounds(20, 20, 100, 25);
        getContentPane().add(lblLocalitate);

        txtLocalitate = new JTextField();
        txtLocalitate.setBounds(130, 20, 200, 25);
        getContentPane().add(txtLocalitate);

        btnCauta = new JButton("Caută");
        btnCauta.setBounds(130, 50, 100, 25);
        getContentPane().add(btnCauta);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(20, 90, 360, 150);
        getContentPane().add(scrollPane);
    }

    public void addCautaListener(ActionListener listener) {
        btnCauta.addActionListener(listener);
    }

    public String getLocalitate() {
        return txtLocalitate.getText();
    }

    public void afiseazaCursa(String cursa) {
        textArea.setText(cursa);
    }
}
