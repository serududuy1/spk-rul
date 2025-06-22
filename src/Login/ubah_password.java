package Login;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import koneksi.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ubah_password extends javax.swing.JFrame {
    private Connection conn = new koneksi().connect();
    Statement stat;
    ResultSet rs;
    PreparedStatement ps;
    String sql,sqli;
    public ubah_password() {
        initComponents();
        placeholder();
        setLocationRelativeTo(getRootPane());
        Locale lokasi = new Locale("id","ID");
        Locale.setDefault(lokasi);
        
        Icon i = jLabel1.getIcon();
        ImageIcon icon = (ImageIcon)i;
        Image image = icon.getImage().getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(),Image.SCALE_SMOOTH);
        jLabel1.setIcon(new ImageIcon(image));
        
    }
    
    public void placeholder(){
        addPlaceholderStyle(txtusername);
        addPlaceholderStyle(password_field);
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
    
    //coding update
    private void update(){
        int OK = JOptionPane.showConfirmDialog(null, "Ubah Password ?", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        String user, pass;
            user =  txtusername.getText();
            pass = password_field.getText();
            String sql = "SELECT * FROM admin WHERE username = '" + txtusername.getText() + "'"; 
        if(OK==0){
            String sqli = "update admin set password='"+pass+"' where username='"+user+"'";
         try {
            PreparedStatement lanjut = conn.prepareStatement(sqli);
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
                if(hasil.next()){
                    if(hasil.getString("username").equals(user)){
                        txtusername.requestFocus();
                        lanjut.executeUpdate();
                        JOptionPane.showMessageDialog(this, "Password Berhasil Diubah");
                    }
                    else{
                        JOptionPane.showMessageDialog(this,"Username Hampir Benar");
                    }
                }
                else{
                    
                     JOptionPane.showMessageDialog(this,"Username Salah");
                     password_field.setText("");
                }
            }catch (Exception e){
            }
        }   
        }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnllogin = new javax.swing.JPanel();
        lbllogin = new javax.swing.JLabel();
        lblusername = new javax.swing.JLabel();
        lblpassword = new javax.swing.JLabel();
        lblingin_login = new javax.swing.JLabel();
        txtusername = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        password_field = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ubah Password");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        pnllogin.setBackground(new java.awt.Color(255, 255, 255));
        pnllogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbllogin.setFont(new java.awt.Font("Century Gothic", 1, 34)); // NOI18N
        lbllogin.setForeground(new java.awt.Color(102, 102, 102));
        lbllogin.setText("Ubah Password");
        pnllogin.add(lbllogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        lblusername.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lblusername.setForeground(new java.awt.Color(102, 102, 102));
        lblusername.setText("Username");
        pnllogin.add(lblusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));

        lblpassword.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lblpassword.setForeground(new java.awt.Color(102, 102, 102));
        lblpassword.setText("Password");
        pnllogin.add(lblpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, -1, -1));

        lblingin_login.setBackground(new java.awt.Color(29, 162, 253));
        lblingin_login.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblingin_login.setForeground(new java.awt.Color(29, 162, 253));
        lblingin_login.setText("Ingin Login ?");
        lblingin_login.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblingin_login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblingin_loginMouseClicked(evt);
            }
        });
        pnllogin.add(lblingin_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 320, -1, -1));

        txtusername.setText("Username");
        txtusername.setBorder(null);
        txtusername.setPreferredSize(new java.awt.Dimension(147, 37));
        txtusername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtusernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtusernameFocusLost(evt);
            }
        });
        pnllogin.add(txtusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 214, -1));
        pnllogin.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 220, 10));

        password_field.setText("Password");
        password_field.setBorder(null);
        password_field.setPreferredSize(new java.awt.Dimension(147, 37));
        password_field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                password_fieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                password_fieldFocusLost(evt);
            }
        });
        pnllogin.add(password_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 160, -1));
        pnllogin.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 214, 10));

        jButton1.setText("Simpan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        pnllogin.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/shopndrive.jpg"))); // NOI18N
        jLabel1.setLabelFor(jLabel1);
        jLabel1.setToolTipText("");
        jLabel1.setAlignmentY(0.0F);
        jLabel1.setDisabledIcon(null);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setMaximumSize(new java.awt.Dimension(500, 500));
        jLabel1.setMinimumSize(new java.awt.Dimension(500, 500));
        jLabel1.setPreferredSize(new java.awt.Dimension(100, 100));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnllogin, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnllogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblingin_loginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblingin_loginMouseClicked
        this.setVisible(false);
        Login rp = new Login();
        rp.setVisible(true);
    }//GEN-LAST:event_lblingin_loginMouseClicked

    private void btnubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnubahActionPerformed
        update();
        password_field.setText("");
  
    }//GEN-LAST:event_btnubahActionPerformed

    private void txtusernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtusernameFocusGained
         if(txtusername.getText().equals("Username"))
        {
            txtusername.setText(null);
            txtusername.requestFocus();
            resetPlaceholderStyle(txtusername);
        }
    }//GEN-LAST:event_txtusernameFocusGained

    private void txtusernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtusernameFocusLost
        if(txtusername.getText().length()==0)
        {
            addPlaceholderStyle(txtusername);
            txtusername.setText("Username");
        }
    }//GEN-LAST:event_txtusernameFocusLost

    private void password_fieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_password_fieldFocusGained
          if(password_field.getText().equals("Password"))
        {
            password_field.setText(null);
            password_field.requestFocus();
            resetPlaceholderStyle(password_field);
        }
    }//GEN-LAST:event_password_fieldFocusGained

    private void password_fieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_password_fieldFocusLost
        if(password_field.getText().length()==0)
        {
            addPlaceholderStyle(password_field);
            password_field.setText("Password");
        }
    }//GEN-LAST:event_password_fieldFocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        update();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ubah_password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ubah_password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ubah_password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ubah_password.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ubah_password().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblingin_login;
    private javax.swing.JLabel lbllogin;
    private javax.swing.JLabel lblpassword;
    private javax.swing.JLabel lblusername;
    private javax.swing.JPasswordField password_field;
    private javax.swing.JPanel pnllogin;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables
}
