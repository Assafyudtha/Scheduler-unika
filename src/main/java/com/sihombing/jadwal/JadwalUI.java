/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.sihombing.jadwal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rizky David L
 */
public class JadwalUI extends javax.swing.JFrame {
    Main awalan = new Main();
private DefaultTableModel tbldosen;
private DefaultTableModel tblmtk;
koneksi dbconnect = new koneksi();
Connection konnection = dbconnect.getConnection();
codeGenerator code = new codeGenerator();
// Tes Upload Ke Github
    /**
     * Creates new form JadwalUI
     */
    public JadwalUI() {
        initComponents();
        tbldosen = (DefaultTableModel) jTable2.getModel();
        tblmtk = (DefaultTableModel) jTable1.getModel();
        showDsn();
        showMtk();
        for(jenismatkul tipe : jenismatkul.values()){
            jComboBox1.addItem(tipe);
        }
    }
    
    private void showDsn(){
        try {
        tbldosen.setRowCount(0);
        Connection connection = dbconnect.getConnection();
        String query= "select * from dosen";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {                
                String id = result.getString("id");
                String nama = result.getString("namadsn");
                tbldosen.addRow(new  Object[]{id,nama});
            }
            
            jTable2.setModel(tbldosen);
            result.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
        e.printStackTrace();
        }
    }
    
        private void showMtk(){
        try {
        tblmtk.setRowCount(0);
        Connection connection = dbconnect.getConnection();
        String query= "select * from matakuliah";
        
            Statement statement = connection.createStatement();
            Statement dosenstate = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {                
                String id = result.getString("kdmtk");
                String nama = result.getString("Matkul");
                String jlh = result.getString("Jumlah_mhs");
                int jenis = result.getInt("jenismtk");
                String dosen = result.getString("dosen");
                int jatah = result.getInt("jlh_waktu");
                String sdosen="select namadsn from dosen where id="+ dosen;
                String dosnam = "";
                try (ResultSet result2 = dosenstate.executeQuery(sdosen);){
                    if(result2.next()){
                        dosnam = result2.getString("namadsn");
                    }
                }
                
                jenismatkul jenismtk = null;
                if (jenis == 1){
                    jenismtk = jenismatkul.Teori;
                    }else{
                    jenismtk = jenismatkul.Praktek;

                }
                tblmtk.addRow(new  Object[]{id,nama,jlh,jenismtk,dosnam,jatah});
            }
            
            jTable1.setModel(tblmtk);
            result.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
        e.printStackTrace();
        }
    }
        
        private void hapusMTK(){
        int[] selectedRows = jTable1.getSelectedRows();
        List<Integer> rowsToDelete = new ArrayList<>();
        for(int selectedRow:selectedRows){
            rowsToDelete.add(selectedRow);
        }
        
         try{
             Connection connection = dbconnect.getConnection();
             String sql = "delete from matakuliah where kdmtk =?";

             PreparedStatement statement = connection.prepareStatement(sql);

             for(int selectedRow:rowsToDelete){
                 String dosId = jTable1.getValueAt(selectedRow, 0).toString();
                 statement.setString(1, dosId);
                 code.deleteID(Integer.parseInt(dosId));
                 int rowsDeleted = statement.executeUpdate();
                 if(rowsDeleted>0){
                     System.out.println("Berhasil Dihapus");
                     JOptionPane.showMessageDialog(null, "Berhasil Di Hapus");
                 }else{
                     System.out.println("Gagal menghapus");
                     JOptionPane.showMessageDialog(null, "Gagal Di Hapus");
                 }
             }
             DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
             for(int i = rowsToDelete.size()-1; i>=0; i--){
                 int selectedRow = rowsToDelete.get(i);
                 model.removeRow(selectedRow);
             }
// Sampai sini
         }catch(SQLException e){
             e.printStackTrace();
         }
showMtk();
        }
        
private void tambahMTK(Connection connection){
code.addExistingID(jTable1);
int newID = code.generateID(jTable1);
int selectedRow = jTable2.getSelectedRow();
int columnIndex= 0;
int jatah = Integer.parseInt(jTextField4.getText());
String noDosen = null;
if(selectedRow !=-1){
    Object idDos = jTable2.getValueAt(selectedRow, columnIndex);
     noDosen = idDos.toString();
}

String name = jTextField2.getText();
jenismatkul type = (jenismatkul) jComboBox1.getSelectedItem();
int jenis = type.getValue();
String jlh = jTextField3.getText()  ;
if (!name.isEmpty()) {
try{
    String sql = "insert into matakuliah (kdmtk,Matkul,jumlah_mhs,jenismtk,dosen,jlh_waktu) values (?,?,?,?,?,?)";

    try(PreparedStatement statement = connection.prepareStatement(sql)){
        statement.setInt(1, newID);
        statement.setString(2, name);
        statement.setInt(4, jenis);
        statement.setInt(3, Integer.parseInt(jlh));
        statement.setString(5, noDosen);
        statement.setInt(6, jatah);

        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0){
            JOptionPane.showMessageDialog(null, "Sukses Di Simpan");
        }else{
            JOptionPane.showMessageDialog(null, "Gagal Untuk menyimpan");
        }

    }
}catch(SQLException e){
    e.printStackTrace();
}
// Clear input fields
    jTextField2.setText("");
    jTextField3.setText("");

}
showMtk();
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Matakuliah:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Matkul", "Nama Matkul", "Jumlah", "Tipe", "Dosen", "Jumlah Waktu"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama Dosen"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jButton1.setText("Generate ID");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Masukkan");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Jenis:");

        jLabel4.setText("Jumlah Mahasiswa :");

        jButton3.setText("Hapus");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel5.setText("Jumlah Waktu:");

        jLabel6.setText("Pilih Dosen untuk Matakuliah yang akan dibuat");

        jButton4.setText("Menu Utama");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox1, 0, 93, Short.MAX_VALUE)
                                    .addComponent(jTextField2)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                                    .addComponent(jTextField4))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(411, 411, 411)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        tambahMTK(konnection);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        hapusMTK();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
awalan.setVisible(true);
        setVisible(false);         // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<jenismatkul> jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
