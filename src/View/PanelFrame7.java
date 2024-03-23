package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelFrame7 extends JPanel {
    private JButton btnExport;

    public PanelFrame7() {
        setLayout(null);

        btnExport = new JButton("Export");
        btnExport.setBounds(50, 50, 150, 30);
        add(btnExport);
    }

    public void addExportListener(ActionListener listener) {
        btnExport.addActionListener(listener);
    }
}
