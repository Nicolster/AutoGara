package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelFrame10 extends JPanel {
    private JLabel lblLuna;
    private JLabel lblAn;
    private JTextField txtLuna;
    private JTextField txtAn;
    private JButton btnAfiseazaPasageri;
    private JTextArea txtAreaPasageri;

    public PanelFrame10() {
        initializeComponents();
        addComponentsToFrame();
    }

    private void initializeComponents() {
        lblLuna = new JLabel("Luna:");
        lblAn = new JLabel("An:");
        txtLuna = new JTextField(10);
        txtAn = new JTextField(10);
        btnAfiseazaPasageri = new JButton("Afișează Pasageri");
        txtAreaPasageri = new JTextArea(10, 30);
        txtAreaPasageri.setEditable(false);
    }

    private void addComponentsToFrame() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblLuna, gbc);
        gbc.gridy++;
        panel.add(lblAn, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(txtLuna, gbc);
        gbc.gridy++;
        panel.add(txtAn, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(btnAfiseazaPasageri, gbc);
        gbc.gridy++;
        panel.add(new JScrollPane(txtAreaPasageri), gbc);

        add(panel);
    }

    public int getLuna() {
        return Integer.parseInt(txtLuna.getText());
    }

    public int getAn() {
        return Integer.parseInt(txtAn.getText());
    }

    public void afiseazaPasageri(String listaPasageri) {
        txtAreaPasageri.setText(listaPasageri);
    }

    public void addAfiseazaPasageriListener(ActionListener listener) {
        btnAfiseazaPasageri.addActionListener(listener);
    }
}
