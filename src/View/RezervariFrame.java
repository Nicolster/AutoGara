package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.ParseException;

import java.sql.Date;

public class RezervariFrame extends JPanel {

    private final JTextField  txtPasagerNume;
    private final JTextField txtCursaNume;
    private final JTextField txtNumarLocuri;
    private final JTextField txtDataRezervare;
    private final JButton btnAdauga;
    private final JButton btnAfiseaza;
    private final JButton btnActualizeaza;
    private final JButton btnSterge;
    private final JTable table;
    private final DefaultTableModel tableModel;

    public RezervariFrame() {
        setLayout(new BorderLayout());

        // Panel pentru adăugarea unei curse
        JPanel addPanel = new JPanel(new FlowLayout());
        JLabel lblPasagerID = new JLabel("Pasager:");
        txtPasagerNume = new JTextField(10);
        JLabel lblCursaID = new JLabel("Cursa:");
        txtCursaNume = new JTextField(10);
        JLabel lblNumarLocuri = new JLabel("Numar Locuri:");
        txtNumarLocuri = new JTextField(10);
        JLabel lblDataRezervare = new JLabel("Data Rezervare (YYYY-MM-DD):");
        txtDataRezervare = new JTextField(10);
        btnAdauga = new JButton("Adauga");
        addPanel.add(lblPasagerID);
        addPanel.add(txtPasagerNume);
        addPanel.add(lblCursaID);
        addPanel.add(txtCursaNume);
        addPanel.add(lblNumarLocuri);
        addPanel.add(txtNumarLocuri);
        addPanel.add(lblDataRezervare);
        addPanel.add(txtDataRezervare);
        addPanel.add(btnAdauga);
        add(addPanel, BorderLayout.NORTH);

        // Modelul de tabel
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panel pentru operațiile CRUD
        JPanel crudPanel = new JPanel(new FlowLayout());
        btnAfiseaza = new JButton("Afiseaza");
        btnActualizeaza = new JButton("Actualizeaza");
        btnSterge = new JButton("Sterge");
        crudPanel.add(btnAfiseaza);
        crudPanel.add(btnActualizeaza);
        crudPanel.add(btnSterge);
        add(crudPanel, BorderLayout.SOUTH);


        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Permite doar selecția unei singure linii
        selectionModel.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Extrageți valorile celulelor din linia selectată și le afișați în textfield-uri

                    txtPasagerNume.setText(table.getValueAt(selectedRow, 1).toString());
                    txtCursaNume.setText(table.getValueAt(selectedRow, 2).toString());
                    txtNumarLocuri.setText(table.getValueAt(selectedRow, 3).toString());
                    txtDataRezervare.setText(table.getValueAt(selectedRow, 4).toString());
                    // Actualizați și alte textfield-uri după nevoie
                }
            }
        });

    }

    // Metoda pentru adăugarea unui ascultător pentru butonul de adăugare
    public void setAdaugaRezervareListener(ActionListener listener) {
        btnAdauga.addActionListener(listener);
    }

    // Metoda pentru adăugarea unui ascultător pentru butonul de afișare
    public void setAfiseazaRezervareListener(ActionListener listener) {
        btnAfiseaza.addActionListener(listener);
    }

    // Metoda pentru adăugarea unui ascultător pentru butonul de actualizare
    public void setActualizeazaRezervareListener(ActionListener listener) {
        btnActualizeaza.addActionListener(listener);
    }

    // Metoda pentru adăugarea unui ascultător pentru butonul de ștergere
    public void setStergeRezervareListener(ActionListener listener) {
        btnSterge.addActionListener(listener);
    }

    // Metoda pentru obținerea numelui cursei introdus în câmpul text

    // Metoda pentru obținerea destinației introdusă în câmpul text
    public String getPasagerID() {
        return txtPasagerNume.getText();
    }

    // Metoda pentru obținerea capacității introduse în câmpul text
    public String getCursaID() {
        return txtCursaNume.getText();
    }

    // Metoda pentru obținerea tipului de transport introdus în câmpul text
    public int getNumarLocuri() {
        return Integer.parseInt(txtNumarLocuri.getText());
    }

    public Date getDataRezervare() throws ParseException {
        return Date.valueOf(txtDataRezervare.getText());
    }



    public JTable getTable() {
        return table;
    }

    public void setTableData(Object[][] data, Object[] columnNames) {
        tableModel.setDataVector(data, columnNames);
    }
}


