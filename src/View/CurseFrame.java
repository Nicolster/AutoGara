package View;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class CurseFrame extends JPanel {

    private JTextField txtNume, txtDestinatie, txtCapacitate, txtTipTransport, txtTraseulNume;
    private JButton btnAdauga, btnAfiseaza, btnActualizeaza, btnSterge;
    private JTable table;
    private DefaultTableModel tableModel;

    public CurseFrame() {
        setLayout(new BorderLayout());

        // Panel pentru adăugarea unei curse
        JPanel addPanel = new JPanel(new FlowLayout());
        JLabel lblNume = new JLabel("Nume:");
        txtNume = new JTextField(10);
        JLabel lblDestinatie = new JLabel("Destinatie:");
        txtDestinatie = new JTextField(10);
        JLabel lblCapacitate = new JLabel("Capacitate:");
        txtCapacitate = new JTextField(10);
        JLabel lblTipTransport = new JLabel("Tip transport:");
        txtTipTransport = new JTextField(10);
        JLabel lblTraseulID = new JLabel("Traseu Nume:");
        txtTraseulNume = new JTextField(10);
        btnAdauga = new JButton("Adauga");
        addPanel.add(lblNume);
        addPanel.add(txtNume);
        addPanel.add(lblDestinatie);
        addPanel.add(txtDestinatie);
        addPanel.add(lblCapacitate);
        addPanel.add(txtCapacitate);
        addPanel.add(lblTipTransport);
        addPanel.add(txtTipTransport);
        addPanel.add(lblTraseulID);
        addPanel.add(txtTraseulNume);
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
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        // Extrageți valorile celulelor din linia selectată și le afișați în textfield-uri

                        txtNume.setText(table.getValueAt(selectedRow, 1).toString());
                        txtDestinatie.setText(table.getValueAt(selectedRow, 2).toString());
                        txtCapacitate.setText(table.getValueAt(selectedRow, 3).toString());
                        txtTipTransport.setText(table.getValueAt(selectedRow, 4).toString());
                        txtTraseulNume.setText(table.getValueAt(selectedRow, 5).toString());
                    }
                }
            }
        });

    }

    // Metoda pentru adăugarea unui ascultător pentru butonul de adăugare
    public void setAdaugaListener(ActionListener listener) {
        btnAdauga.addActionListener(listener);
    }

    // Metoda pentru adăugarea unui ascultător pentru butonul de afișare
    public void setAfiseazaListener(ActionListener listener) {
        btnAfiseaza.addActionListener(listener);
    }

    // Metoda pentru adăugarea unui ascultător pentru butonul de actualizare
    public void setActualizeazaListener(ActionListener listener) {
        btnActualizeaza.addActionListener(listener);
    }

    // Metoda pentru adăugarea unui ascultător pentru butonul de ștergere
    public void setStergeListener(ActionListener listener) {
        btnSterge.addActionListener(listener);
    }

    // Metoda pentru obținerea numelui cursei introdus în câmpul text
    public String getNume() {
        return txtNume.getText();
    }

    public void setNume(String nume){
        txtNume.setText(nume);
    }

    // Metoda pentru obținerea destinației introdusă în câmpul text
    public String getDestinatie() {
        return txtDestinatie.getText();
    }
    public void setDestinatie(String destinatie){
        txtDestinatie.setText(destinatie);
    }

    // Metoda pentru obținerea capacității introduse în câmpul text
    public int getCapacitate() {
        return Integer.parseInt(txtCapacitate.getText());
    }
    public void setCapacitate(int capacitate){
        txtCapacitate.setText(String.valueOf(capacitate));
    }

    // Metoda pentru obținerea tipului de transport introdus în câmpul text
    public String getTipTransport() {
        return txtTipTransport.getText();
    }
    public void setTipTransport(String tipTransport){
        txtTipTransport.setText(tipTransport);
    }

    public String getTraseulID() {
        return txtTraseulNume.getText();
    }
    public void setTraseulID(String traseulID){
        txtTraseulNume.setText(String.valueOf(traseulID));
    }



    public JTable getTable() {
        return table;
    }

    public void setTableData(Object[][] data, Object[] columnNames) {
        tableModel.setDataVector(data, columnNames);
    }
}


