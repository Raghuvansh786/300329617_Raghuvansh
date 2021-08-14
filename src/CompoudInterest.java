import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Fri Aug 13 16:03:17 PDT 2021
 */



/**
 * @author unknown
 */
public class CompoudInterest extends JFrame {

        public static ArrayList<Savings> list = new ArrayList<>();

            Connections con = new Connections();
            Connection conobj = con.connect();

    public CompoudInterest() throws SQLException, ClassNotFoundException {
        initComponents();
    }

    private void btnaddActionPerformed(ActionEvent e) throws SQLException {
        // TODO add your code here

        //Taking the customer info
        String custnum = txtcustnum.getText();
        String custname = txtcustname.getText();
        String deposit = txtdeposit.getText();
        String years = txtyears.getText();
        String type = "";
        if(cbox1.getSelectedItem().equals("Savings-Delux")) {
            type =  "Savings-Delux";

        }
        if(cbox1.getSelectedItem().equals("Savings-Regular")) {
            type = "Savings-Regular";
        }


        list.add(new Savings(custnum,custname,deposit,years,type));




        String query2 = "Select * from savingstable where custno =?";
        PreparedStatement query = conobj.prepareStatement(query2);
        query.setString(1,custnum);

        ResultSet rs  =query.executeQuery();
        if(rs.isBeforeFirst()) {
            JOptionPane.showMessageDialog(null, "The record is already in the system");
            return;
        }

        String query1 ="Insert into savingstable values(?,?,?,?,?)";
        query =conobj.prepareStatement(query1);

        String[][] array = new String[list.size()][5];
        for(int i=0; i< list.size(); ++i) {
            array[i][0] = list.get(i).getCustnmber();
            array[i][1] = list.get(i).getCustmame();
            array[i][2] = list.get(i).getIntdeposit();
            array[i][3] = list.get(i).getYears();
            array[i][4] = list.get(i).getType();
        }


        String[] coloums = {"Number", "Name", "Deposit", "Years", "Type of Savings"};

        DefaultTableModel model = new DefaultTableModel(array, coloums);
        jtdata2.setModel(model);

        query.setString(1,custnum);
        query.setString(2,custname);
        query.setString(3,deposit);
        query.setString(4,years);
        query.setString(5,type);


        query.executeUpdate();
        JOptionPane.showMessageDialog(null, "One record is Added");
        updateTable();

        txtcustnum.setText("");
        txtcustname.setText("");
        txtdeposit.setText("");
        txtyears.setText("");
        cbox1.setSelectedIndex(1);

    }

    private void jtdata2MouseClicked(MouseEvent e) {
        // TODO add your code here


        DefaultTableModel model = (DefaultTableModel)jtdata2.getModel();
        int index = jtdata2.getSelectedRow();

        txtcustnum.setText(model.getValueAt(index,0).toString());
        txtcustname.setText(model.getValueAt(index,1).toString());
        txtdeposit.setText(model.getValueAt(index,2).toString());
        txtyears.setText(model.getValueAt(index,3).toString());
        String type = model.getValueAt(index,4).toString();

        if(type.equals("Savings-Delux")) {
            cbox1.setSelectedItem("Savings-Delux");
        }

        if(type.equals("Savings-Regular")) {
            cbox1.setSelectedItem("Savings-Regular");
        }


    }

    private void btneditActionPerformed(ActionEvent e) throws SQLException {
        // TODO add your code here

        DefaultTableModel model = (DefaultTableModel) jtdata2.getModel();
        int index = jtdata2.getSelectedRow();
        String oldValue = model.getValueAt(index,0).toString();

        //deleting the old record
        for(int i=0; i<  list.size(); ++i) {
            if (oldValue.equals(list.get(i).getCustnmber())) {
                list.remove(i);
            }
        }

        //Addig the completely new record
        String custnum = txtcustnum.getText();
        String custname = txtcustname.getText();
        String deposit = txtdeposit.getText();
        String years = txtyears.getText();
        String type = "";
        if(cbox1.getSelectedItem().equals("Savings-Delux")) {
            type =  "Savings-Delux";

        }
        if(cbox1.getSelectedItem().equals("Savings-Regular")) {
            type = "Savings-Regular";
        }

        list.add(new Savings(custnum,custname,deposit,years,type));

        String[][] array = new String[list.size()][5];
        for(int i=0; i< list.size(); ++i) {
            array[i][0] = list.get(i).getCustnmber();
            array[i][1] = list.get(i).getCustmame();
            array[i][2] = list.get(i).getIntdeposit();
            array[i][3] = list.get(i).getYears();
            array[i][4] = list.get(i).getType();
        }


        String[] coloums = {"Number", "Name", "Deposit", "Years", "Type of Savings"};

        DefaultTableModel model5 = new DefaultTableModel(array, coloums);
        jtdata2.setModel(model5);

        String query ="Update savingstable set custno=?, custname=?, cdep=?, nyears=?, savtype=? where custno=?";
        PreparedStatement query2 = conobj.prepareStatement(query);

        //updating the database
        query2.setString(1,custnum);
        query2.setString(2,custname);
        query2.setString(3,deposit);
        query2.setString(4,years);
        query2.setString(5,type);
        query2.setString(6,oldValue);

        query2.executeUpdate();
        JOptionPane.showMessageDialog(null, "Record edited");
        updateTable();

        //Setting the boxes to null
        txtcustnum.setText("");
        txtcustname.setText("");
        txtdeposit.setText("");
        txtyears.setText("");
        cbox1.setSelectedIndex(1);


    }

    private void btndelActionPerformed(ActionEvent e) throws SQLException {
        // TODO add your code here

        //Taking the customer info
        String custnum = txtcustnum.getText();
        String custname = txtcustname.getText();
        String deposit = txtdeposit.getText();
        String years = txtyears.getText();
        String type = "";
        if(cbox1.getSelectedItem().equals("Savings-Delux")) {
            type =  "Savings-Delux";

        }
        if(cbox1.getSelectedItem().equals("Savings-Regular")) {
            type = "Savings-Regular";
        }

        //deleting the record from the table

        DefaultTableModel model = (DefaultTableModel) jtdata2.getModel();
        int index = jtdata2.getSelectedRow();
        String oldValue = model.getValueAt(index,0).toString();
        for(int i=0; i<  list.size(); ++i) {
            if (oldValue.equals(list.get(i).getCustnmber())) {
                list.remove(i);
            }
        }

        //Query to delete the specific record
        String query = "Delete From savingstable Where custno=?";
        PreparedStatement query2 = conobj.prepareStatement(query);

        //Double checking with the user
        JOptionPane.showConfirmDialog(null, "Do you really want to delete the record");
        query2.setString(1,oldValue);
        query2.executeUpdate();
        updateTable();

        //Setting the boxes to null
        txtcustnum.setText("");
        txtcustname.setText("");
        txtdeposit.setText("");
        txtyears.setText("");
        cbox1.setSelectedIndex(1);

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        label1 = new JLabel();
        txtcustnum = new JTextField();
        label2 = new JLabel();
        txtcustname = new JTextField();
        label3 = new JLabel();
        txtdeposit = new JTextField();
        label4 = new JLabel();
        txtyears = new JTextField();
        label5 = new JLabel();
        cbox1 = new JComboBox<>();
        scrollPane2 = new JScrollPane();
        jtdata2 = new JTable();
        scrollPane1 = new JScrollPane();
        jtdata1 = new JTable();
        btnadd = new JButton();
        btnedit = new JButton();
        btndel = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- label1 ----
        label1.setText("Enter the Customer Number");
        contentPane.add(label1, "cell 0 0");

        //---- txtcustnum ----
        txtcustnum.setColumns(17);
        contentPane.add(txtcustnum, "cell 1 0");

        //---- label2 ----
        label2.setText("Enter the Customer Name");
        contentPane.add(label2, "cell 0 1");
        contentPane.add(txtcustname, "cell 1 1");

        //---- label3 ----
        label3.setText("Enter the Initial Deposit");
        contentPane.add(label3, "cell 0 2");
        contentPane.add(txtdeposit, "cell 1 2");

        //---- label4 ----
        label4.setText("Enter the Number of years");
        contentPane.add(label4, "cell 0 3");
        contentPane.add(txtyears, "cell 1 3");

        //---- label5 ----
        label5.setText("Choose the type of saving");
        contentPane.add(label5, "cell 0 4");

        //---- cbox1 ----
        cbox1.setModel(new DefaultComboBoxModel<>(new String[] {
            "Savings-Delux",
            "Savings-Regular"
        }));
        contentPane.add(cbox1, "cell 1 4");

        //======== scrollPane2 ========
        {

            //---- jtdata2 ----
            jtdata2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    jtdata2MouseClicked(e);
                }
            });
            scrollPane2.setViewportView(jtdata2);
        }
        contentPane.add(scrollPane2, "cell 0 5");

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(jtdata1);
        }
        contentPane.add(scrollPane1, "cell 1 5");

        //---- btnadd ----
        btnadd.setText("Add");
        btnadd.addActionListener(e -> {
            try {
                btnaddActionPerformed(e);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        contentPane.add(btnadd, "cell 0 6");

        //---- btnedit ----
        btnedit.setText("Edit");
        btnedit.addActionListener(e -> {
            try {
                btneditActionPerformed(e);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        contentPane.add(btnedit, "cell 0 6");

        //---- btndel ----
        btndel.setText("Delete");
        btndel.addActionListener(e -> {
            try {
                btndelActionPerformed(e);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        contentPane.add(btndel, "cell 0 6");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel label1;
    private JTextField txtcustnum;
    private JLabel label2;
    private JTextField txtcustname;
    private JLabel label3;
    private JTextField txtdeposit;
    private JLabel label4;
    private JTextField txtyears;
    private JLabel label5;
    private JComboBox<String> cbox1;
    private JScrollPane scrollPane2;
    private JTable jtdata2;
    private JScrollPane scrollPane1;
    private JTable jtdata1;
    private JButton btnadd;
    private JButton btnedit;
    private JButton btndel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        CompoudInterest form1 = new CompoudInterest();
        form1.setVisible(true);
        form1.updateTable();
        form1.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public void updateTable() throws SQLException {

        String query ="Select * From savingstable";
        PreparedStatement query1 = conobj.prepareStatement(query);
        ResultSet rs = query1.executeQuery();

        DefaultTableModel model2 = (DefaultTableModel)jtdata2.getModel();

        rs.last();

        int rowcount = rs.getRow();

        rs.beforeFirst();

        String[][] array = new String[0][];

        if(rowcount > 0) {
            array =new String[rowcount][5];

        }

        int j=0;
        while(rs.next()) {

            array[j][0] = rs.getString("custno");
            array[j][1] = rs.getString("custname");
            array[j][2] = rs.getString("cdep");
            array[j][3] = rs.getString("nyears");
            array[j][4] = rs.getString("savtype");
            ++j;

        }

        String[] coloums = {"Number", "Name", "Deposit", "Years", "Type of Savings"};

        DefaultTableModel model = new DefaultTableModel(array, coloums);
        jtdata2.setModel(model);


    }

}
