import Controller.*;

import Model.dao.CursaDAO;
import Model.dao.RezervareDAO;
import Model.dao.TraseuDAO;
import View.*;
import Model.dao.PasagerDAO;

import javax.swing.*;
import java.awt.*;


import java.sql.SQLException;

public class MyForms extends JFrame {

    public MyForms(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 590, 450);
        setTitle("Java GUI Tutorial");

        PanelMain panelMain = new PanelMain();
        getContentPane().add(panelMain);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuMain = new JMenu("Menu");
        JMenu menuCRUD = new JMenu("CRUD");

        JMenuItem menu1 = getMenu1();

        JMenuItem menu2 = getMenu2();

        JMenuItem menu3 = getMenu3();

        JMenuItem menu4 = getMenu4();

        JMenuItem menu5 = getMenu5();

        JMenuItem menu6 = getMenu6();

        JMenuItem menu7 = getMenu7();

        JMenuItem menu8 = getMenu8();

        JMenuItem menu9 = getMenu9();

        JMenuItem menu10 = getMenu10();

        JMenuItem menucurse = getMenucurse();

        JMenuItem menupasageri = getMenupasageri();

        JMenuItem menurezervari = getMenurezervari();

        JMenuItem menutraseu = getMenutraseu();


        menuCRUD.add(menucurse);
        menuCRUD.add(menupasageri);
        menuCRUD.add(menurezervari);
        menuCRUD.add(menutraseu);
        menuMain.add(menu1);
        menuMain.add(menu2);
        menuMain.add(menu3);
        menuMain.add(menu4);
        menuMain.add(menu5);
        menuMain.add(menu6);
        menuMain.add(menu7);
        menuMain.add(menu8);
        menuMain.add(menu9);
        menuMain.add(menu10);

        menuBar.add(menuCRUD);
        menuBar.add(menuMain);
        setJMenuBar(menuBar);
    }

    private JMenuItem getMenutraseu() {
        JMenuItem menutraseu = new JMenuItem("Traseuri");
        menutraseu.addActionListener(e -> {
            TraseuriFrame paneltraseuri = new TraseuriFrame();
            paneltraseuri.setBorder(BorderFactory.createTitledBorder("Traseuri CRUD"));

            TraseuDAO traseuDAO;
            try {
                traseuDAO = new TraseuDAO();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            new TraseuController(paneltraseuri, traseuDAO);

            replacePanel(paneltraseuri);
        });
        return menutraseu;
    }

    private JMenuItem getMenurezervari() {
        JMenuItem menurezervari = new JMenuItem("Rezervare");
        menurezervari.addActionListener(e -> {
            RezervariFrame panelrezervari = new RezervariFrame();
            panelrezervari.setBorder(BorderFactory.createTitledBorder("Pasageri CRUD"));

            RezervareDAO RezervareDAO;
            try {
                RezervareDAO = new RezervareDAO();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            new RezervareController(panelrezervari, RezervareDAO);

            replacePanel(panelrezervari);
        });
        return menurezervari;
    }

    private JMenuItem getMenupasageri() {
        JMenuItem menupasageri = new JMenuItem("Pasageri");
        menupasageri.addActionListener(e -> {
            PasageriFrame panelpasageri = new PasageriFrame();
            panelpasageri.setBorder(BorderFactory.createTitledBorder("Pasageri CRUD"));

            PasagerDAO PasagerDAO;
            try {
                PasagerDAO = new PasagerDAO();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            new PasagerController(panelpasageri, PasagerDAO);

            replacePanel(panelpasageri);
        });
        return menupasageri;
    }

    private JMenuItem getMenucurse() {
        JMenuItem menucurse = new JMenuItem("Curse");
        menucurse.addActionListener(e -> {
            CurseFrame panelcurse = new CurseFrame();
            panelcurse.setBorder(BorderFactory.createTitledBorder("Curse CRUD"));

            CursaDAO cursaDAO;
            try {
                cursaDAO = new CursaDAO();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            new CursaController(panelcurse, cursaDAO);

            replacePanel(panelcurse);
        });
        return menucurse;
    }

    private JMenuItem getMenu10() {
        JMenuItem menu10 = new JMenuItem("Menu 10 - Lista pasagerilor pentru luna L anul A");
        menu10.addActionListener(e -> {
            PanelFrame10 panel10 = new PanelFrame10();
            panel10.setBorder(BorderFactory.createTitledBorder("Pasageri dupa luna si an"));

            CursaDAO cursaDAO;
            try {
                cursaDAO = new CursaDAO();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            new CursaController(panel10, cursaDAO);

            replacePanel(panel10);
        });
        return menu10;
    }

    private JMenuItem getMenu9() {
        JMenuItem menu9 = new JMenuItem("Menu 9 - Destinații Disponibile");
        menu9.addActionListener(e -> {
            PanelFrame9 panel9 = new PanelFrame9();
            panel9.setBorder(BorderFactory.createTitledBorder("Destinații Disponibile"));

            CursaDAO cursaDAO;
            try {
                cursaDAO = new CursaDAO();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            new CursaController(panel9, cursaDAO);

            replacePanel(panel9);
        });
        return menu9;
    }

    private static JMenuItem getMenu8() {
        JMenuItem menu8 = new JMenuItem("Menu 8 - Afișare cursă după localitate");
        menu8.addActionListener(e -> {
            PanelFrame8 cursaFrame = new PanelFrame8();
            cursaFrame.setTitle("Afișare cursă după localitate");

            CursaDAO cursaDAO;
            try {
                cursaDAO = new CursaDAO();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            new CursaController(cursaFrame, cursaDAO);

            cursaFrame.setVisible(true);
        });
        return menu8;
    }

    private JMenuItem getMenu7() {
        JMenuItem menu7 = new JMenuItem("Menu 7 - Export in fisier txt lista celor mai solicitate curse");
        menu7.addActionListener(e -> {
            PanelFrame7 panel7 = new PanelFrame7();
            panel7.setBounds(100, 100, 300, 200);
            panel7.setBorder(BorderFactory.createTitledBorder("Panel 7"));

            ExportFrame exportFrame = new ExportFrame();
            exportFrame.setTitle("Export Frame");

            CursaDAO cursaDAO;
            try {
                cursaDAO = new CursaDAO();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
             new ExportController(exportFrame, cursaDAO);

            panel7.addExportListener(e1 -> exportFrame.setVisible(true));

            replacePanel(panel7);
        });
        return menu7;
    }

    private JMenuItem getMenu6() {
        JMenuItem menu6 = new JMenuItem("Menu 6 - Lista pasagerilor de la cursa X, la data D.");
        menu6.addActionListener(e -> {
            PanelFrame6 panel6 = new PanelFrame6();
            panel6.setBorder(BorderFactory.createTitledBorder("Panel 6"));

            PasagerDAO pasagerDAO;
            try {
                pasagerDAO = new PasagerDAO();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
             new PasageriCursaController(panel6, pasagerDAO);

            replacePanel(panel6);
        });
        return menu6;
    }

    private JMenuItem getMenu5() {
        JMenuItem menu5 = new JMenuItem("Menu 5 - Lista curselor care au destinaţia X.");
        menu5.addActionListener(e -> {
            PanelFrame5 panel5 = new PanelFrame5();
            panel5.setBorder(BorderFactory.createTitledBorder("Panel 5"));

            CursaDAO cursaDAO;
            try {
                cursaDAO = new CursaDAO();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            new DestinatieController(panel5, cursaDAO);

            replacePanel(panel5);
        });
        return menu5;
    }

    private JMenuItem getMenu4() {
        JMenuItem menu4 = new JMenuItem("Menu 4 - Lista curselor care aparţin traseului auto X");
        menu4.addActionListener(e -> {
            PanelFrame4 panel4 = new PanelFrame4();
            panel4.setBorder(BorderFactory.createTitledBorder("Panel 4"));
            CursaDAO cursaDAO;
            try {
                cursaDAO = new CursaDAO();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            new TraseuController(panel4, cursaDAO);
            replacePanel(panel4);
        });
        return menu4;
    }

    private JMenuItem getMenu3() {
        JMenuItem menu3 = new JMenuItem("Menu 3 - Tipul de transport destinat pentru cursa X şi capacitatea acestuia.");
        menu3.addActionListener(e -> {
            PanelFrame3 panel3 = new PanelFrame3();
            panel3.setBorder(BorderFactory.createTitledBorder("Panel 3"));

            CursaDAO cursaDAO;
            try {
                cursaDAO = new CursaDAO();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            new CursaController(panel3, cursaDAO);

            replacePanel(panel3);
        });
        return menu3;
    }

    private JMenuItem getMenu2() {
        JMenuItem menu2 = new JMenuItem("Menu 2 - Locurile rezervate de pasagerul X la cursa Y");
        menu2.addActionListener(e -> {
            PanelFrame2 panel2 = new PanelFrame2();
            panel2.setBorder(BorderFactory.createTitledBorder("Panel 2"));
            RezervareDAO rezervareDAO;
            try {
                rezervareDAO = new RezervareDAO();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
             new RezervareController(panel2, rezervareDAO);
            replacePanel(panel2);
        });
        return menu2;
    }

    private JMenuItem getMenu1() {
        JMenuItem menu1 = new JMenuItem("Menu 1 - Lista cu pasagerii de la cursa X.");
        menu1.addActionListener(e -> {
            PanelFrame1 panel1 = new PanelFrame1();
            panel1.setBorder(BorderFactory.createTitledBorder("Panel 1"));
            replacePanel(panel1);

            PasagerDAO pasagerDAO;
            try {
                pasagerDAO = new PasagerDAO();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            new PasagerController(panel1, pasagerDAO);
        });
        return menu1;
    }

    private void replacePanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().doLayout();
        revalidate();
        repaint();
    }

    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            MyForms frame = new MyForms();
            frame.setVisible(true);
        });
    }
}
