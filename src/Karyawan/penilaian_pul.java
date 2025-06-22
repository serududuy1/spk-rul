/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karyawan;

import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import static menuUtama.dashbor.lbljumlah_karyawan;
import static menuUtama.dashbor.table1;
import java.awt.Color;
import static Karyawan.karyawan.tblkaryawan;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static Karyawan.penilaian.tblpenilaian;
import static Karyawan.perangkingan.tblnormalisasi;
import static Karyawan.perangkingan.tblperingkat;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author serud
 */
public class penilaian_pul extends javax.swing.JPanel {
    private Connection conn = new koneksi().connect(); 

    /**
     * Creates new form NewJPanel
     */
    DefaultTableModel tabmode;
    private DefaultTableModel tabelNormalisasi;
    private DefaultTableModel tabelPeringkat;
    private DefaultTableModel tabelpenilaian;
    
    String Kehadiran, Sikap,Kedisiplinan,Hasil_Kerja;
    Float NilaiKehadiran, NilaiSikap, NilaiKedisiplinan, NilaiHasil_Kerja;
    public penilaian_pul() {
        initComponents();
         datatabel();  
        txtnama.setEditable(false);
//        jTextField3.setEditable(false);
//        jTextField4.setEditable(false);
//        jTextField5.setEditable(false);
//        jTextField6.setEditable(false);
        valueComboBox();
    }
    public void datatabel(){              
        Object[] Baris = {"NIK","Nama ","Kehadiran","Sikap","Kedisiplinan","Hasil Kerja"};
        tabmode = new DefaultTableModel(null, Baris);
        tblpenilaian.setModel(tabmode);
//        table1.setModel(tabmode);;
        String sql = "select karyawan.nik,karyawan.nama,bobot.c1,bobot.c2,bobot.c3,bobot.c4  from karyawan\n" +
"    inner join bobot on karyawan.nik = bobot.nik";
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
                String g = hasil.getString("c1");
                String h = hasil.getString("c2");
                String i = hasil.getString("c3");
                String j = hasil.getString("c4");
                String[] data = {a,b,g,h,i,j};
                tabmode.addRow(data);
            }
        }catch (Exception e){
        }
        int b = tabmode.getRowCount();
        lbljumlah_karyawan.setText(""+b);    //label total karyawan yg diform karyawan,total data didatbel dipanggil ke label total
//        lbltotal_karyawan.setText(""+b); //label total karyawan yg dimenu utama,total data didata tabel dipanggil ke label total
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
        txtkehadiran.setText("");
        txtsikap.setText("");
        txtkedisiplinan.setText("");
        txthasilkerja.setText("");
        txtnama.setText("");
        jComboBox1.getModel().setSelectedItem("Pilih");
//        jComboBox3.getModel().setSelectedItem("Kehadiran");
//        jComboBox2.getModel().setSelectedItem("Sikap");
//        jComboBox4.getModel().setSelectedItem("Kedisiplinan");
//        jComboBox5.getModel().setSelectedItem("Hasil Kerja");
        jButton1.setEnabled(true);
//        jButton2.setEnabled(false);
//        jButton3.setEnabled(false);
//        jButton4.setEnabled(false);
        tblpenilaian.getSelectionModel().clearSelection();
    }
    public void onlyNumber(java.awt.event.KeyEvent evt){
        char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter))){
            evt.consume();
        }
    }
    
    public void valueComboBox(){
        jComboBox1.addItem("pilih");
        try{
            String sql = "SELECT * FROM karyawan \n" +
"left join bobot on karyawan.nik = bobot.nik \n" +
"where bobot.c1 is null";
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);

            while(hasil.next()){
                jComboBox1.addItem(hasil.getString("nik"));
            }
        }catch(SQLException e){
            System.err.println("SQL Exception: " + e.getMessage());
        }
    
}
    
    public void normalisasi(){
        LinkedList max = new LinkedList();
        LinkedList mn = new LinkedList();            
        try{
            String sql = "select max(nilai_jam_kerja), max(nilai_absensi), max(nilai_kerapihan), min(nilai_keterlambatan) from rating_kecocokan ";
            java.sql.Statement stat = conn.createStatement();
            ResultSet normalisasi = stat.executeQuery(sql);
            while (normalisasi.next()){
                max.add(normalisasi.getString(1));
                max.add(normalisasi.getString(2));
                max.add(normalisasi.getString(3));
                max.add(normalisasi.getString(4));
            }
            String sqli = "select rating_kecocokan.nilai_jam_kerja, rating_kecocokan.nilai_absensi, rating_kecocokan.nilai_kerapihan, rating_kecocokan.nilai_keterlambatan, "
                    + "karyawan.nik, karyawan.nama "
                    + "from rating_kecocokan INNER JOIN karyawan ON rating_kecocokan.nik=karyawan.nik";
            ResultSet res2 = stat.executeQuery(sqli);
            tabelModelNormalisasi();
            while (res2.next()){
                    tabelNormalisasi.addRow(new Object[]{
                    res2.getString("nik"),
                    res2.getString("nama"),
                    (res2.getFloat("nilai_jam_kerja")/Float.valueOf(max.get(0).toString())),
                    (res2.getFloat("nilai_absensi")/Float.valueOf(max.get(1).toString())),
                    (res2.getFloat("nilai_kerapihan")/Float.valueOf(max.get(2).toString())),
                    (Float.valueOf(max.get(3).toString())/res2.getFloat("nilai_keterlambatan"))});
                    //(res2.getFloat("keterlambatan")/Float.valueOf(max.get(3).toString()))});
                            //(Float.valueOf(max.get(3).toString())/res2.getFloat("keterlamban"))});
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(this, ex);
        }
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
    public void editData(){
    try{
        String sql = "update bobot set c1=?, c2=?, c3=?, c4=? where nik=?";
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1, txtkehadiran.getText());
        stat.setString(2, txtsikap.getText());
        stat.setString(3, txtkedisiplinan.getText());
        stat.setString(4, txthasilkerja.getText());
        stat.setString(5, jComboBox1.getSelectedItem().toString());
        System.out.println( stat);
        stat.executeUpdate();

        JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
        }catch (SQLException e){
        JOptionPane.showMessageDialog(null, "Data Gagal Diubah "+e);
    }
    bersih();
    datatabel();
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
        txtcari = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblpenilaian = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lbldata_penilaian = new javax.swing.JLabel();
        lblnik = new javax.swing.JLabel();
        txtnama = new javax.swing.JTextField();
        lblnik1 = new javax.swing.JLabel();
        lblalamat = new javax.swing.JLabel();
        txtkehadiran = new javax.swing.JTextField();
        lblabsensi = new javax.swing.JLabel();
        txtsikap = new javax.swing.JTextField();
        lbljabatan = new javax.swing.JLabel();
        txtkedisiplinan = new javax.swing.JTextField();
        lblketerlambatan = new javax.swing.JLabel();
        txthasilkerja = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();

        jPanel1.setBackground(new java.awt.Color(248, 248, 255));
        jPanel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

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

        jButton9.setText("Cari");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblpenilaian.setModel(new javax.swing.table.DefaultTableModel(
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
        tblpenilaian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblpenilaianMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblpenilaian);

        jButton4.setBackground(java.awt.Color.gray);
        jButton4.setText("hapus");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(java.awt.Color.lightGray);
        jButton5.setText("Cetak");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        lbldata_penilaian.setBackground(new java.awt.Color(204, 204, 204));
        lbldata_penilaian.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbldata_penilaian.setText("Data Penilaian");

        lblnik.setBackground(new java.awt.Color(204, 204, 204));
        lblnik.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        lblnik.setForeground(new java.awt.Color(102, 102, 102));
        lblnik.setText("NIK Karyawan");

        txtnama.setText(" ");

        lblnik1.setBackground(new java.awt.Color(204, 204, 204));
        lblnik1.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        lblnik1.setForeground(new java.awt.Color(102, 102, 102));
        lblnik1.setText("Nama Karyawan");

        lblalamat.setBackground(new java.awt.Color(204, 204, 204));
        lblalamat.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        lblalamat.setForeground(new java.awt.Color(102, 102, 102));
        lblalamat.setText("Kehadiran (C1)");

        txtkehadiran.setText(" ");
        txtkehadiran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtkehadiranKeyTyped(evt);
            }
        });

        lblabsensi.setBackground(new java.awt.Color(204, 204, 204));
        lblabsensi.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        lblabsensi.setForeground(new java.awt.Color(102, 102, 102));
        lblabsensi.setText("Sikap (C2)");

        txtsikap.setText(" ");
        txtsikap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtsikapKeyTyped(evt);
            }
        });

        lbljabatan.setBackground(new java.awt.Color(204, 204, 204));
        lbljabatan.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        lbljabatan.setForeground(new java.awt.Color(102, 102, 102));
        lbljabatan.setText("Kedisiplinan (C3)");

        txtkedisiplinan.setText(" ");
        txtkedisiplinan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtkedisiplinanKeyTyped(evt);
            }
        });

        lblketerlambatan.setBackground(new java.awt.Color(204, 204, 204));
        lblketerlambatan.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        lblketerlambatan.setForeground(new java.awt.Color(102, 102, 102));
        lblketerlambatan.setText("Hasil Kerja (C3)");

        txthasilkerja.setText(" ");
        txthasilkerja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txthasilkerjaKeyTyped(evt);
            }
        });

        jButton1.setBackground(java.awt.Color.gray);
        jButton1.setText("simpan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(java.awt.Color.lightGray);
        jButton2.setText("ubah");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton7.setBackground(java.awt.Color.gray);
        jButton7.setText("Clear");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jComboBox1.setMaximumRowCount(100);
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jComboBox1MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lbldata_penilaian))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtnama, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(lblketerlambatan))
                                    .addComponent(lblnik1)
                                    .addComponent(lbljabatan)
                                    .addComponent(lblabsensi)
                                    .addComponent(lblalamat)
                                    .addComponent(lblnik)
                                    .addComponent(txtkehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtsikap, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtkedisiplinan, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txthasilkerja, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lbldata_penilaian)
                .addGap(23, 23, 23)
                .addComponent(lblnik)
                .addGap(5, 5, 5)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblnik1)
                .addGap(12, 12, 12)
                .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblalamat)
                .addGap(10, 10, 10)
                .addComponent(txtkehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(lblabsensi)
                .addGap(5, 5, 5)
                .addComponent(txtsikap, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(lbljabatan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtkedisiplinan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblketerlambatan)
                .addGap(5, 5, 5)
                .addComponent(txthasilkerja, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(4, 4, 4)))
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(295, 295, 295))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 687, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtcariFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcariFocusGained
        if(txtcari.getText().equals("Cari Data Karyawan"))
        {
            txtcari.setText(null);
            txtcari.requestFocus();
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
    }//GEN-LAST:event_txtcariKeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       try{
            JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream("report1.jasper"),null,conn);
            JasperViewer.viewReport(print, false);

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String  valid = txtnama.getText().trim();
        String valid2 = lblnik.getText().trim();
        if (valid.isEmpty() || valid2.isEmpty() ) {
           JOptionPane.showMessageDialog(null, "Harap Masukkan input", "Alert", JOptionPane.ERROR_MESSAGE);
        }else{
            try{
                String sql = "insert into bobot (`nik`, `c1`, `c2`, `c3`, `c4`) values (?,?,?,?,?)";
                PreparedStatement stat = conn.prepareStatement(sql);
//                stat.setInt(1, Integer.parseInt(txtnik.getText()));
//                stat.setString(1, "0");
                stat.setString(1, jComboBox1.getSelectedItem().toString());
//                stat.setString(2, txtnama.getText());
//                stat.setString(3, jComboBox3.getSelectedItem().toString());
//                stat.setString(4, jComboBox2.getSelectedItem().toString());
//                stat.setString(5, jComboBox4.getSelectedItem().toString());
//                stat.setString(6, jComboBox5.getSelectedItem().toString());
                stat.setString(2, txtkehadiran.getText());
                stat.setString(3, txtsikap.getText());
                stat.setString(4, txtkedisiplinan.getText());
                stat.setString(5, txthasilkerja.getText());
//                stat.setString(7, txtnama.getText());
//                stat.setString(9, txtnama.getText());
//                stat.setString(10, txtnama.getText());
//                stat.setString(11, txtnama.getText());
//                stat.setString(6,"");
                System.out.println( stat);
                stat.executeUpdate();
//                jTextField1.requestFocus();
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Disimpan "+e);
            }
            
            bersih();
            datatabel();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
//     
bersih();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void tblpenilaianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblpenilaianMouseClicked
        int bar = tblpenilaian.getSelectedRow();
        
        jComboBox1.getModel().setSelectedItem(tabmode.getValueAt(bar, 0).toString());
        txtnama.setText(tabmode.getValueAt(bar, 1).toString());
//        txtnama.setText(tabmode.getValueAt(bar, 1).toString());
//        jComboBox3.getModel().setSelectedItem(tabmode.getValueAt(bar, 2).toString());
//        jComboBox2.getModel().setSelectedItem(tabmode.getValueAt(bar, 3).toString());
//        jComboBox4.getModel().setSelectedItem(tabmode.getValueAt(bar, 4).toString());
//        jComboBox5.getModel().setSelectedItem(tabmode.getValueAt(bar, 5).toString());
        txtkehadiran.setText(tabmode.getValueAt(bar, 2).toString());
        txtsikap.setText(tabmode.getValueAt(bar, 3).toString());
        txtkedisiplinan.setText(tabmode.getValueAt(bar, 4).toString());
        txthasilkerja.setText(tabmode.getValueAt(bar, 5).toString());
//        
//        jButton4.setEnabled(true);
        jButton1.setEnabled(false);
//         System.out.println(jComboBox2.getSelectedItem());
//        jButton2.setEnabled(true);
    }//GEN-LAST:event_tblpenilaianMouseClicked

    private void jComboBox1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseEntered
        
//        try{
//            String sql = "select * from karyawan";
//            java.sql.Statement stat = conn.createStatement();
//            ResultSet hasil = stat.executeQuery(sql);
//
//            while(hasil.next()){
//                txtnik.setText(hasil.getString("nik"));
//            }
//        }catch(SQLException e){
//            System.err.println("SQL Exception: " + e.getMessage());
//        }
    }//GEN-LAST:event_jComboBox1MouseEntered

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged

//            jComboBox1.getSelectedItem();
            try{
            String sql = "select * from karyawan left join bobot\n" +
"on bobot.nik = karyawan.nik where karyawan.nik = '"+jComboBox1.getSelectedItem()+"'";
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);

            while(hasil.next()){
                txtnama.setText(hasil.getString("nama"));
//                jComboBox3.getModel().setSelectedItem(hasil.getString("label_c1"));
//                jComboBox2.getModel().setSelectedItem(hasil.getString("label_c2"));
//                jComboBox4.getModel().setSelectedItem(hasil.getString("label_c3"));
//                jComboBox5.getModel().setSelectedItem(hasil.getString("label_c4"));
//                jComboBox3.getModel().setSelectedItem(tabmode.getValueAt(bar, 2).toString());
//                jComboBox2.getModel().setSelectedItem(tabmode.getValueAt(bar, 3).toString());
//                jComboBox4.getModel().setSelectedItem(tabmode.getValueAt(bar, 4).toString());
//                jComboBox5.getModel().setSelectedItem(tabmode.getValueAt(bar, 5).toString());
//                jTextField3.setText(tabmode.getValueAt(bar, 6).toString());
//                jTextField4.setText(tabmode.getValueAt(bar, 7).toString());
//                jTextField5.setText(tabmode.getValueAt(bar, 8).toString());
//                jTextField6.setText(tabmode.getValueAt(bar, 9).toString());
            }
        }catch(SQLException e){
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void txtkehadiranKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtkehadiranKeyTyped
        onlyNumber(evt);
    }//GEN-LAST:event_txtkehadiranKeyTyped

    private void txtsikapKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsikapKeyTyped
        onlyNumber(evt);
    }//GEN-LAST:event_txtsikapKeyTyped

    private void txtkedisiplinanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtkedisiplinanKeyTyped
        onlyNumber(evt);
    }//GEN-LAST:event_txtkedisiplinanKeyTyped

    private void txthasilkerjaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txthasilkerjaKeyTyped
        onlyNumber(evt);
    }//GEN-LAST:event_txthasilkerjaKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        editData();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int OK = JOptionPane.showConfirmDialog(null, "Hapus", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if(OK==0){
            String sql = "delete from bobot where nik = '"+jComboBox1.getSelectedItem()+"'";
            try{
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                jComboBox1.requestFocus();
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus "+e);
            }
        }
        bersih();
        datatabel();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseClicked
        
    }//GEN-LAST:event_jComboBox1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblabsensi;
    private javax.swing.JLabel lblalamat;
    private javax.swing.JLabel lbldata_penilaian;
    private javax.swing.JLabel lbljabatan;
    private javax.swing.JLabel lblketerlambatan;
    private javax.swing.JLabel lblnik;
    private javax.swing.JLabel lblnik1;
    public static volatile javax.swing.JTable tblpenilaian;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txthasilkerja;
    private javax.swing.JTextField txtkedisiplinan;
    private javax.swing.JTextField txtkehadiran;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtsikap;
    // End of variables declaration//GEN-END:variables
}
