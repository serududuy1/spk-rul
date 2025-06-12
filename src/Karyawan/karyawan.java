/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karyawan;

import java.awt.Color;
import koneksi.koneksi;
import java.sql.*;
import javax.swing.JOptionPane;

import java.sql.ResultSet;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;
import static Karyawan.penilaian.tblpenilaian;
import static Karyawan.perangkingan.tblnormalisasi;
import static Karyawan.perangkingan.tblperingkat;
import java.awt.Font;
import javax.swing.JTextField;
import static menuUtama.dashbor.lbljumlah_karyawan;
import static menuUtama.dashbor.table1;
/**
 *
 * @author serud
 */
public class karyawan extends javax.swing.JPanel {
    
    private Connection conn = new koneksi().connect(); 

    /**
     * Creates new form NewJPanel
     */
    DefaultTableModel tabmode;
    private DefaultTableModel tabelNormalisasi;
    private DefaultTableModel tabelPeringkat;
    private DefaultTableModel tabelpenilaian;
    
    public karyawan() {
        initComponents();
         datatabel();  
         
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
        jButton4.setEnabled(false);
        
    }

    public void datatabel(){              
        Object[] Baris = {"NIK Karyawan","Nama Karyawan","Alamat","Jenis Kelamin","Jabatan"};
        tabmode = new DefaultTableModel(null, Baris);
        tblkaryawan.setModel(tabmode);
//        table1.setModel(tabmode);;
        String sql = "select  karyawan.nik, karyawan.nama, karyawan.alamat, karyawan.jenis_kelamin, "
                + "jabatan.jabatan from karyawan INNER JOIN jabatan ON karyawan.nik=jabatan.nik ";
            {
             int row=tabmode.getRowCount();
             for (int i=0;i<row;i++){tabmode.removeRow(0);}
            } 
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("nik");
                String b = hasil.getString("nama");
                String c = hasil.getString("alamat");
                String d = hasil.getString("jenis_kelamin");
                String e = hasil.getString("jabatan");
                String[] data = {a,b,c,d,e};
                tabmode.addRow(data);
            }
        }catch (Exception e){
        }
        int b = tabmode.getRowCount();
        lbljumlah_karyawan.setText(""+b);    //label total karyawan yg diform karyawan,total data didatbel dipanggil ke label total
//        lbltotal_karyawan.setText(""+b); //label total karyawan yg dimenu utama,total data didata tabel dipanggil ke label total
    }  
//    public void tabelModelNormalisasi(){
//       tabelNormalisasi = new DefaultTableModel();
//       tabelNormalisasi.addColumn("NIK");
//       tabelNormalisasi.addColumn("Nama");
//       tabelNormalisasi.addColumn("C1");
//       tabelNormalisasi.addColumn("C2");
//       tabelNormalisasi.addColumn("C3");
//       tabelNormalisasi.addColumn("C4");
//       tblnormalisasi.setModel(tabelNormalisasi);
//    } 
    public void caridata(String key){
        Object[] Baris = {"NIK Karyawan","Nama Karyawan","Alamat","Jenis Kelamin","Jabatan"};
        tabmode = new DefaultTableModel(null, Baris);
        tblkaryawan.setModel(tabmode);
        String sql = "select  karyawan.nik, karyawan.nama, karyawan.alamat, karyawan.jenis_kelamin, "
                + "jabatan.jabatan from karyawan INNER JOIN jabatan ON karyawan.nik=jabatan.nik where nama like '%"+txtcari.getText()+"%' "
                 + "or alamat like '%"+txtcari.getText()+"%' or jenis_kelamin like '%"+txtcari.getText()+"%' "
                 + "or jabatan like '%"+txtcari.getText()+"%'";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("nik");
                String b = hasil.getString("nama");
                String c = hasil.getString("alamat");
                String d = hasil.getString("jenis_kelamin");
                String e = hasil.getString("jabatan");
                String[] data = {a,b,c,d,e};
                tabmode.addRow(data);
            }
        }catch (Exception e){
        }
     }
    
    public void addPlaceholderStyle(JTextField textField){
    Font font = textField.getFont();
    textField.setFont(font);
    textField.setForeground(Color.gray);
    }
    
    public void resetPlaceholderStyle(JTextField textField){
    Font font = textField.getFont();
    textField.setFont(font);
    textField.setForeground(Color.black);
    }
    public void bersih(){
        txtnik.setText("");
        txtalamat.setText("");
        txtnama.setText("");
        jComboBox2.getModel().setSelectedItem("Pilih Jenis Kelamin");
        jComboBox1.getModel().setSelectedItem("Pilih Jabatan");
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
        jButton4.setEnabled(false);
    }
//    public void normalisasi(){
//        LinkedList max = new LinkedList();
//        LinkedList mn = new LinkedList();            
//        try{
//              String sql = "select max(nilai_jam_kerja), max(nilai_absensi), max(nilai_kerapihan), min(nilai_keterlambatan) from rating_kecocokan ";
//            java.sql.Statement stat = conn.createStatement();
//            ResultSet normalisasi = stat.executeQuery(sql);
//            while (normalisasi.next()){
//                max.add(normalisasi.getString(1));
//                max.add(normalisasi.getString(2));
//                max.add(normalisasi.getString(3));
//                max.add(normalisasi.getString(4));
//            }
//            String sqli = "select rating_kecocokan.nilai_jam_kerja, rating_kecocokan.nilai_absensi, rating_kecocokan.nilai_kerapihan, rating_kecocokan.nilai_keterlambatan, "
//                    + "karyawan.nik, karyawan.nama "
//                    + "from rating_kecocokan INNER JOIN karyawan ON rating_kecocokan.nik=karyawan.nik";
//            ResultSet res2 = stat.executeQuery(sqli);
//            tabelModelNormalisasi();
//            while (res2.next()){
//                    tabelNormalisasi.addRow(new Object[]{
//                    res2.getString("nik"),
//                    res2.getString("nama"),
//                    (res2.getFloat("nilai_jam_kerja")/Float.valueOf(max.get(0).toString())),
//                    (res2.getFloat("nilai_absensi")/Float.valueOf(max.get(1).toString())),
//                    (res2.getFloat("nilai_kerapihan")/Float.valueOf(max.get(2).toString())),
//                    (Float.valueOf(max.get(3).toString())/res2.getFloat("nilai_keterlambatan"))});
//                    //(res2.getFloat("keterlambatan")/Float.valueOf(max.get(3).toString()))});
//                            //(Float.valueOf(max.get(3).toString())/res2.getFloat("keterlamban"))});
//            }
//        } catch (SQLException ex){
//            JOptionPane.showMessageDialog(this, ex);
//        }
//    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        txtcari = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        lbldata_karyawan = new javax.swing.JLabel();
        lblnik = new javax.swing.JLabel();
        lblnama = new javax.swing.JLabel();
        lblalamat = new javax.swing.JLabel();
        lbljk = new javax.swing.JLabel();
        lbljabatan = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtalamat = new javax.swing.JTextField();
        txtnama = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        txtnik = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblkaryawan = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();

        setBackground(new java.awt.Color(248, 248, 255));

        jButton4.setBackground(java.awt.Color.red);
        jButton4.setText("hapus");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(java.awt.Color.yellow);
        jButton5.setText("Cetak");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        txtcari.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtcari.setText("Cari Data Karyawan");
        txtcari.setBorder(null);
        txtcari.setCaretColor(new java.awt.Color(102, 102, 0));
        txtcari.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtcariFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtcariFocusLost(evt);
            }
        });
        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcariKeyReleased(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbldata_karyawan.setBackground(new java.awt.Color(204, 204, 204));
        lbldata_karyawan.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbldata_karyawan.setText("Data Karyawan");

        lblnik.setBackground(new java.awt.Color(204, 204, 204));
        lblnik.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        lblnik.setForeground(new java.awt.Color(102, 102, 102));
        lblnik.setText("NIK Karyawan");

        lblnama.setBackground(new java.awt.Color(204, 204, 204));
        lblnama.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        lblnama.setForeground(new java.awt.Color(102, 102, 102));
        lblnama.setText("Nama");

        lblalamat.setBackground(new java.awt.Color(204, 204, 204));
        lblalamat.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        lblalamat.setForeground(new java.awt.Color(102, 102, 102));
        lblalamat.setText("Alamat");

        lbljk.setBackground(new java.awt.Color(204, 204, 204));
        lbljk.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        lbljk.setForeground(new java.awt.Color(102, 102, 102));
        lbljk.setText("Jenis Kelamin");

        lbljabatan.setBackground(new java.awt.Color(204, 204, 204));
        lbljabatan.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        lbljabatan.setForeground(new java.awt.Color(102, 102, 102));
        lbljabatan.setText("Jabatan");

        jButton1.setBackground(java.awt.Color.green);
        jButton1.setText("simpan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(java.awt.Color.cyan);
        jButton2.setText("ubah");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtnama.setText(" ");
        txtnama.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtnamaFocusGained(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Jabatan", "Manager", "Accounting", "Spv", "Teknisi", "Karyawan" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Jenis Kelamin", "Laki-Laki", "Perempuan" }));

        jButton7.setBackground(java.awt.SystemColor.controlHighlight);
        jButton7.setText("reset");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbljabatan)
                            .addComponent(lbljk)
                            .addComponent(lblalamat)
                            .addComponent(lblnama)
                            .addComponent(lblnik)
                            .addComponent(lbldata_karyawan)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.LEADING, 0, 252, Short.MAX_VALUE)
                                .addComponent(txtnik, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtalamat, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtnama, javax.swing.GroupLayout.Alignment.LEADING)))))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbldata_karyawan)
                .addGap(35, 35, 35)
                .addComponent(lblnik)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnik, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(lblnama)
                .addGap(14, 14, 14)
                .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblalamat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtalamat, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbljk)
                .addGap(8, 8, 8)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbljabatan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        tblkaryawan.setAutoCreateRowSorter(true);
        tblkaryawan.setModel(new javax.swing.table.DefaultTableModel(
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
        tblkaryawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblkaryawanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblkaryawan);

        jButton6.setText("Cari");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 762, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void txtcariFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcariFocusGained
        if(txtcari.getText().equals("Cari Data Karyawan"))
        {
            txtcari.setText(null);
            txtcari.requestFocus();
            //            resetPlaceholderStyle(txtcari);
        }
    }//GEN-LAST:event_txtcariFocusGained

    private void txtcariFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcariFocusLost
        if(txtcari.getText().length()==0)
        {
            //            addPlaceholderStyle(txtcari);
            txtcari.setText("Cari Data Karyawan");
        }
    }//GEN-LAST:event_txtcariFocusLost

    private void txtcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyReleased
        String key=txtcari.getText();
        System.out.println(key);

                if(key!=""){
                        caridata(key);
                    }else{
                        datatabel();
                    }
    }//GEN-LAST:event_txtcariKeyReleased

    private void tblkaryawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkaryawanMouseClicked
        int bar = tblkaryawan.getSelectedRow();
        txtnik.setText(tabmode.getValueAt(bar, 0).toString());
        txtnama.setText(tabmode.getValueAt(bar, 1).toString());
        txtalamat.setText(tabmode.getValueAt(bar, 2).toString());
        jComboBox2.getModel().setSelectedItem(tabmode.getValueAt(bar, 3).toString());
        jComboBox1.getModel().setSelectedItem(tabmode.getValueAt(bar, 4).toString());
        txtnik.setEditable(false);
        jButton4.setEnabled(true);
        jButton1.setEnabled(false);
//         System.out.println(jComboBox2.getSelectedItem());
        jButton2.setEnabled(true);
    }//GEN-LAST:event_tblkaryawanMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        txtnik.setText("");
        txtalamat.setText("");
        txtnama.setText("");
        jComboBox2.getModel().setSelectedItem("Pilih Jenis Kelamin");
        jComboBox1.getModel().setSelectedItem("Pilih Jabatan");
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
        jButton4.setEnabled(false);
        tblkaryawan.getSelectionModel().clearSelection();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String  valid = txtalamat.getText().trim();
        String valid2 = txtnama.getText().trim();
        if (valid.isEmpty() || valid2.isEmpty() ) {
           JOptionPane.showMessageDialog(null, "Harap Masukkan input", "Alert", JOptionPane.ERROR_MESSAGE);
        }else{
            try{
                String sql = "insert into karyawan values (?,?,?,?,?,?)";
                PreparedStatement stat = conn.prepareStatement(sql);
//                stat.setInt(1, Integer.parseInt(txtnik.getText()));
                stat.setString(1, txtnik.getText());
                stat.setString(2, txtnama.getText());
                stat.setString(3, txtalamat .getText());
//                stat.setString(4, jComboBox1.getSelectedItem().toString());
                stat.setString(4, jComboBox2.getSelectedItem().toString());
                stat.setString(5,"0");
                stat.setString(6,"0");
//                stat.setString(6,"");
                System.out.println( stat);
                stat.executeUpdate();
//                jTextField1.requestFocus();
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Disimpan "+e);
            }
            try{
                String sql = "insert into jabatan values (?,?)";
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, txtnik .getText());
                stat.setString(2, jComboBox1.getSelectedItem().toString());
                    stat.executeUpdate();
            }
            catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Data Gagal Disimpan "+e);
            }
//            bersih();
            datatabel();
    //        normalisasi();
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtnamaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnamaFocusGained
        if(txtnik.getText().equals("2121..."))
        {
            txtnik.setText(null);
            txtnik.requestFocus();
            resetPlaceholderStyle(txtnik);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtnamaFocusGained

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int OK = JOptionPane.showConfirmDialog(null, "Hapus", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if(OK==0){
            String sql = "delete from karyawan where nik = '"+txtnik.getText()+"'";
//            String sql2 = "delete from jataban where nik = '"+txtnik.getText()+"'";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
//                PreparedStatement stat2 = conn.prepareStatement(sql2);
                stat.executeUpdate();
//                stat2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                txtnik.requestFocus();
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus "+e);
            }
        }
        bersih();
        datatabel();
//        normalisasi();
//        datatabel_penilaian();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       try{
            String sql = "update karyawan set nik=?, nama=?, alamat=?, jenis_kelamin=? where nik=?";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtnik.getText());
            stat.setString(2, txtnama.getText());
            stat.setString(3, txtalamat.getText());
            stat.setString(4, jComboBox2.getSelectedItem().toString());
            stat.setString(5, txtnik.getText());
            stat.executeUpdate();
            txtnik.requestFocus();
            
            String sql2 = "update jabatan set nik=?, jabatan=? where nik=?";
            PreparedStatement stat2 = conn.prepareStatement(sql2);
            stat2.setString(1, txtnik.getText());
            stat2.setString(2, jComboBox1.getSelectedItem().toString());
            stat2.setString(3, txtnik.getText());
            stat2.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah "+e);
        }
            bersih();
//            IDOtomatis();
            datatabel();
//            normalisasi();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblalamat;
    private javax.swing.JLabel lbldata_karyawan;
    private javax.swing.JLabel lbljabatan;
    private javax.swing.JLabel lbljk;
    private javax.swing.JLabel lblnama;
    private javax.swing.JLabel lblnik;
    public static volatile javax.swing.JTable tblkaryawan;
    private javax.swing.JTextField txtalamat;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnik;
    // End of variables declaration//GEN-END:variables
}
