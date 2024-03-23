package View;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class PasageriFrame extends JPanel {

    private JTextField txtNume;
    private JButton btnAdauga, btnAfiseaza, btnActualizeaza, btnSterge;
    private JTable table;
    private DefaultTableModel tableModel;

    public PasageriFrame() {
        setLayout(new BorderLayout());

        // Panel pentru adăugarea unei curse
        JPanel addPanel = new JPanel(new FlowLayout());
        JLabel lblNume = new JLabel("Nume:");
        txtNume = new JTextField(10);

        btnAdauga = new JButton("Adauga");
        addPanel.add(lblNume);
        addPanel.add(txtNume);
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
                        // Actualizați și alte textfield-uri după nevoie
                    }
                }
            }
        });

    }

    // Metoda pentru adăugarea unui ascultător pentru butonul de adăugare
    public void setAdaugaPasageriListener(ActionListener listener) {
        btnAdauga.addActionListener(listener);
    }

    // Metoda pentru adăugarea unui ascultător pentru butonul de afișare
    public void setAfiseazaPasageriListener(ActionListener listener) {
        btnAfiseaza.addActionListener(listener);
    }

    // Metoda pentru adăugarea unui ascultător pentru butonul de actualizare
    public void setActualizeazaPasageriListener(ActionListener listener) {
        btnActualizeaza.addActionListener(listener);
    }

    // Metoda pentru adăugarea unui ascultător pentru butonul de ștergere
    public void setStergePasageriListener(ActionListener listener) {
        btnSterge.addActionListener(listener);
    }

    // Metoda pentru obținerea numelui cursei introdus în câmpul text
    public String getNume() {
        return txtNume.getText();
    }

    // Metoda pentru obținerea destinației introdusă în câmpul text


    // Metoda pentru obținerea capacității introduse în câmpul text


    // Metoda pentru obținerea tipului de transport introdus în câmpul text






    public JTable getTable() {
        return table;
    }

    public void setTableData(Object[][] data, Object[] columnNames) {
        tableModel.setDataVector(data, columnNames);
    }
}


