import javax.swing.*;
import java.awt.*;

public class PanelMain extends JPanel {

    public PanelMain(){
        setLayout(null);

        JLabel lblPanelMain = new JLabel("Lucru Individual");
        lblPanelMain.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPanelMain.setHorizontalAlignment(SwingConstants.CENTER);
        lblPanelMain.setBounds(165,110,239,31);
        add(lblPanelMain, BorderLayout.CENTER);

        ImageIcon imageIcon = new ImageIcon("lib/logo-3.png");
        JLabel lblImage = new JLabel(imageIcon);
        lblImage.setBounds(195, 150, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblImage, BorderLayout.CENTER);

    }
}
