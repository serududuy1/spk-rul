package Login;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import menuUtama.menuUtama;
import static menuUtama.menuUtama.usernamee;

public class Login extends javax.swing.JFrame {
    private Connection conn = new koneksi().connect();
    
    public Login() {
        initComponents();
        setLocationRelativeTo(getRootPane());
        Locale lokasi = new Locale("id","ID");
        Locale.setDefault(lokasi);
        placeholder();
        
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnllogin = new javax.swing.JPanel();
        lbllogin = new javax.swing.JLabel();
        lblusername = new javax.swing.JLabel();
        lblpassword1 = new javax.swing.JLabel();
        lbllupa_password = new javax.swing.JLabel();
        txtusername = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        password_field = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(698, 250));
        setName("Login"); // NOI18N

        jPanel1.setBackground(new java.awt.Color(51, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnllogin.setBackground(new java.awt.Color(255, 255, 255));
        pnllogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbllogin.setFont(new java.awt.Font("Century Gothic", 1, 34)); // NOI18N
        lbllogin.setForeground(new java.awt.Color(102, 102, 102));
        lbllogin.setText("LOGIN");
        pnllogin.add(lbllogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 30, -1, -1));

        lblusername.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lblusername.setForeground(new java.awt.Color(102, 102, 102));
        lblusername.setText("Username");
        pnllogin.add(lblusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));

        lblpassword1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lblpassword1.setForeground(new java.awt.Color(102, 102, 102));
        lblpassword1.setText("Password");
        pnllogin.add(lblpassword1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, -1, -1));

        lbllupa_password.setBackground(new java.awt.Color(29, 162, 253));
        lbllupa_password.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbllupa_password.setForeground(new java.awt.Color(29, 162, 253));
        lbllupa_password.setText("Lupa Password ?");
        lbllupa_password.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbllupa_password.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbllupa_passwordMouseClicked(evt);
            }
        });
        pnllogin.add(lbllupa_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, -1, -1));

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

        jButton1.setBackground(new java.awt.Color(153, 153, 153));
        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        pnllogin.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, -1, -1));

        jPanel1.add(pnllogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 0, 350, 450));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/shopndrive.jpg"))); // NOI18N
        jLabel1.setToolTipText("");
        jLabel1.setAlignmentY(0.0F);
        jLabel1.setDisabledIcon(null);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setMaximumSize(new java.awt.Dimension(500, 500));
        jLabel1.setMinimumSize(new java.awt.Dimension(500, 500));
        jLabel1.setPreferredSize(new java.awt.Dimension(100, 100));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 450));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lbllupa_passwordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbllupa_passwordMouseClicked
        this.setVisible(false);
        ubah_password rp = new ubah_password();
        rp.setVisible(true);
    }//GEN-LAST:event_lbllupa_passwordMouseClicked

    private void btnloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnloginActionPerformed
          
    }//GEN-LAST:event_btnloginActionPerformed

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
        try {
            java.sql.Statement stat = conn.createStatement();
            String sql = "SELECT * FROM admin WHERE username = '" + txtusername.getText() + "' and password = '" + password_field.getText() + "'"; 
            ResultSet hasil = stat.executeQuery(sql);
            if (hasil.next()) {
                dispose();
                System.out.println(hasil);
                if (hasil.getString("status").equals("admin")) {
                    JOptionPane.showMessageDialog(this, "Login Berhasil");
                    menuUtama men_utama = new  menuUtama();
                    men_utama.setVisible(true);
                    men_utama.admin();
                    usernamee.setText(hasil.getString(2));
                }
                else if (hasil.getString("status").equals("user")){
                    JOptionPane.showMessageDialog(this, "Login Berhasil");
                     menuUtama men_utama = new  menuUtama();
                    men_utama.setVisible(true);
                    men_utama.user();
                    usernamee.setText(hasil.getString(2));
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Username atau Password Salah");
                txtusername.setText("");
                password_field.setText("");
                txtusername.requestFocus();
            }
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbllogin;
    private javax.swing.JLabel lbllupa_password;
    private javax.swing.JLabel lblpassword1;
    private javax.swing.JLabel lblusername;
    private javax.swing.JPasswordField password_field;
    private javax.swing.JPanel pnllogin;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables
}
