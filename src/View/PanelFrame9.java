package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class PanelFrame9 extends JPanel {

    private JButton btnAfiseazaDestinatii;
    private JTextArea textArea;

    public PanelFrame9() {
        setLayout(null);

        btnAfiseazaDestinatii = new JButton("Afiseaza Destinatii");
        btnAfiseazaDestinatii.setBounds(50, 90, 200, 25);
        add(btnAfiseazaDestinatii);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(50, 130, 500, 200);
        add(scrollPane);
    }

    public void addAfiseazaDestinatiiListener(ActionListener listener) {
        btnAfiseazaDestinatii.addActionListener(listener);
    }

    public void displayDestinatii(List<String> destinatii) {
        StringBuilder sb = new StringBuilder();
        for (String destinatie : destinatii) {
            sb.append(destinatie).append("\n");
        }
        textArea.setText(sb.toString());
    }
}


