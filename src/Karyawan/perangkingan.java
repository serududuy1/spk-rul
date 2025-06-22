/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karyawan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author serud
 */
public class perangkingan extends javax.swing.JPanel {
 private Connection conn = new koneksi().connect();
    PreparedStatement ps;
    Statement stat;
    DefaultTableModel tabelNormalisasi;
    DefaultTableModel tabelPeringkat;
    String sql, sqli, mysql;
    DefaultTableModel tabmode;
    public String lap;
      JasperReport JasRep;
      JasperPrint JasPrin;
      Map param = new HashMap();
      JasperDesign JasDes;
    /**
     * Creates new form perangkingan
     */
    public perangkingan() {
        initComponents();
    }
    
    public void tabelModelNormalisasi(){
       tabelNormalisasi = new DefaultTableModel();
       tabelNormalisasi.addColumn("NIK");
       tabelNormalisasi.addColumn("Nama");
       tabelNormalisasi.addColumn("C1");
       tabelNormalisasi.addColumn("C2");
       tabelNormalisasi.addColumn("C3");
       tabelNormalisasi.addColumn("C4");
       tblnormalisasi.setModel(tabelNormalisasi);
    }
    public void cariperingkat(){
    LinkedList mm = new LinkedList();
        try{
             String sqli ="SELECT * from sub_kriteria";
            java.sql.Statement stat = conn.createStatement();
            ResultSet cariperingkat = stat.executeQuery(sqli);
            tabelmodelPeringkat();
            while (cariperingkat.next()){
                 mm.add(cariperingkat.getString("C1"));
                 mm.add(cariperingkat.getString("C2"));
                 mm.add(cariperingkat.getString("C3"));
                 mm.add(cariperingkat.getString("C4"));
            }
            for (int t = 0; t < tblnormalisasi.getRowCount(); t++) {
                String mysql = "Delete FROM hasil WHERE nik  ";
                PreparedStatement state = conn.prepareStatement(mysql);
                state.executeUpdate();
            }
            for (int x = 0; x < tblnormalisasi.getRowCount(); x++){
                double r1; 
                double r2;
                double r3;
                double r4;
                double w;
                r1 = (Float.valueOf(tblnormalisasi.getValueAt(x, 2).toString())*Float.valueOf(mm.get(0).toString()));
                r2=(Float.valueOf(tblnormalisasi.getValueAt(x, 3).toString())*Float.valueOf(mm.get(1).toString()));
                r3=(Float.valueOf(tblnormalisasi.getValueAt(x, 4).toString())*Float.valueOf(mm.get(2).toString()));
                r4=(Float.valueOf(tblnormalisasi.getValueAt(x, 5).toString())*Float.valueOf(mm.get(3).toString()));
                w=r1+r2+r3+r4;
//                w=w/4*100;
                String sql = "insert into hasil (nik, nilai) values"+"('"+tblnormalisasi.getValueAt(x, 0).toString()+"' , "+" '"+w+"') ";
                PreparedStatement state = conn.prepareStatement(sql);
                state.executeUpdate();
            }
            tabelModelNormalisasi();
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(this, ex);
        }
    }    
    
    public void tabelmodelPeringkat(){
        Object[] Baris = {"NIK","Nama Karyawan","Nilai"};
        tabelPeringkat = new DefaultTableModel(null, Baris);
        tblperingkat.setModel(tabelPeringkat);
       try{
            String mysql ="SELECT hasil.nilai, karyawan.nik, karyawan.nama from hasil INNER JOIN karyawan ON hasil.nik=karyawan.nik ORDER BY nilai desc";
            java.sql.Statement stat = conn.createStatement();
            ResultSet peringkat = stat.executeQuery(mysql);
            while (peringkat.next()){
                String a = peringkat.getString("nik");
                String b = peringkat.getString("nama");
                String c = peringkat.getString("nilai"); 
                String[] data = {a,b,c};
               tabelPeringkat.addRow(data); 
            }
        }catch (Exception e){
        }    
    }
public void normalisasi(){
        LinkedList max = new LinkedList();
        LinkedList mn = new LinkedList();            
        try{
            String sql = "select max(c1), max(c2), max(c3), min(c4) from bobot ";
            java.sql.Statement stat = conn.createStatement();
            ResultSet normalisasi = stat.executeQuery(sql);
            while (normalisasi.next()){
                max.add(normalisasi.getString(1));
                max.add(normalisasi.getString(2));
                max.add(normalisasi.getString(3));
                max.add(normalisasi.getString(4));
            }
            String sqli = "select bobot.c1, bobot.c2, bobot.c3, bobot.c4, "
                    + "karyawan.nik, karyawan.nama "
                    + "from bobot INNER JOIN karyawan ON bobot.nik=karyawan.nik";
            ResultSet res2 = stat.executeQuery(sqli);
            tabelModelNormalisasi();
            while (res2.next()){
                    tabelNormalisasi.addRow(new Object[]{
                    res2.getString("nik"),
                    res2.getString("nama"),
                    (res2.getFloat("c1")/Float.valueOf(max.get(0).toString())),
                    (res2.getFloat("c2")/Float.valueOf(max.get(1).toString())),
                    (res2.getFloat("c3")/Float.valueOf(max.get(2).toString())),
//                    (Float.valueOf(max.get(3).toString())/res2.getFloat("c4"))});
                    (res2.getFloat("c4")/Float.valueOf(max.get(2).toString()))
                    });
                    
                            //(Float.valueOf(max.get(3).toString())/res2.getFloat("keterlamban"))});
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(this, ex);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblperingkat = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblnormalisasi = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(248, 248, 255));

        tblperingkat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblperingkat);

        jButton1.setBackground(java.awt.Color.gray);
        jButton1.setText("Cek Normalisasi");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tblnormalisasi.setAutoCreateRowSorter(true);
        tblnormalisasi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblnormalisasi);

        jButton2.setBackground(java.awt.Color.gray);
        jButton2.setText("Cetak");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(java.awt.Color.lightGray);
        jButton3.setText("Cek Peringkat'");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(414, 414, 414)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(269, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(234, 234, 234))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(255, 255, 255)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 56, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(401, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       normalisasi();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         cariperingkat();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try{
            JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream("perangkinganreport.jasper"),null,conn);
            JasperViewer.viewReport(print, false);

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static volatile javax.swing.JTable tblnormalisasi;
    public static volatile javax.swing.JTable tblperingkat;
    // End of variables declaration//GEN-END:variables
}
