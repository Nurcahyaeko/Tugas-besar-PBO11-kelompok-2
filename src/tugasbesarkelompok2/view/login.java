/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasbesarkelompok2.view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import tugasbesarkelompok2.koneksi.koneksi;

/**
 *
 * @author Erzi Hutama
 */
public class login extends javax.swing.JFrame {

    /**
     * Creates new form login
     */
    public login() {
        initComponents();
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
        txt_name = new javax.swing.JTextField();
        txt_pass = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JPanel();
        bt_login = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DEESTOCK");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_name.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 14)); // NOI18N
        txt_name.setForeground(new java.awt.Color(102, 102, 102));
        txt_name.setText("            U S E R  N A M E");
        txt_name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_nameMouseClicked(evt);
            }
        });
        jPanel1.add(txt_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 270, 230, 50));

        txt_pass.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 14)); // NOI18N
        txt_pass.setForeground(new java.awt.Color(102, 102, 102));
        txt_pass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_passMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txt_passMouseEntered(evt);
            }
        });
        jPanel1.add(txt_pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 330, 230, 50));

        btnLogin.setBackground(new java.awt.Color(153, 153, 153));
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLoginMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLoginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLoginMouseExited(evt);
            }
        });

        bt_login.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        bt_login.setForeground(new java.awt.Color(255, 255, 255));
        bt_login.setText("L O G I N");

        javax.swing.GroupLayout btnLoginLayout = new javax.swing.GroupLayout(btnLogin);
        btnLogin.setLayout(btnLoginLayout);
        btnLoginLayout.setHorizontalGroup(
            btnLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnLoginLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(bt_login)
                .addContainerGap(70, Short.MAX_VALUE))
        );
        btnLoginLayout.setVerticalGroup(
            btnLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bt_login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 400, 230, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/Deestock login3.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 550));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLoginMouseExited

    private void btnLoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLoginMouseEntered

    private void btnLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseClicked
        String a = null,b = null;
        try {
            Connection k = new koneksi().config();
            String query = "select * from admin where username = '" + txt_name.getText() + "'AND password ='" + txt_pass.getText() +"'";
            Statement s = k.createStatement();
            ResultSet r = s.executeQuery(query);
            while(r.next()){

                a = r.getString("username");
                b = r.getString("password");

            }
            if ((a!=null)&&(b!=null)){
                JOptionPane.showMessageDialog(null, "Login Berhasil");
                BarangView br = new BarangView();
                br.show(true);
                this.hide();
            }
            else if((txt_name.getText().equals("") && (txt_pass.getText().equals("")))){
                JOptionPane.showMessageDialog(null, "Silahkan isi username dan password!");
            }else{
                JOptionPane.showMessageDialog(null, "Login Gagal");
            }

        } catch (SQLException ex) {
            ex.getMessage();
        }

        /*  try {
            sql = "SELECT * FROM adm WHERE username='" + txt_name.getText() + "' AND password='" + txt_pass.getText() + "'";
            rs = stat.executeQuery(sql);
            if (rs.next()) {
                if (txt_name.getText().equals(rs.getString("username")) && txt_pass.getText().equals(rs.getString("password"))) {
                    JOptionPane.showMessageDialog(null, "berhasil login");

                }
            } else {
                JOptionPane.showMessageDialog(null, "username atau password salah");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        //menu mn = new menu();
        //mn.setVisible(true);
        //dispose();

        /*        try {
            sql = "SELECT * FROM adm WHERE username='" + username.getText() + "' AND password='" + password.getText() + "'";
            rs = stat.executeQuery(sql);
            if (rs.next()) {
                if (username.getText().equals(rs.getString("username")) && password.getText().equals(rs.getString("password"))) {
                    JOptionPane.showMessageDialog(null, "berhasil login");
                    listMenu.setVisible(true);
                    Menu.setVisible(true);
                    Register.setVisible(false);
                    Login.setVisible(false);
                    panelWelcome.setVisible(true);
                    panelJualBarang.setVisible(false);
                    panelTambahData.setVisible(false);
                    panelJualBarang.setVisible(false);
                    panelCekStok.setVisible(false);
                    panelHistori.setVisible(false);
                    panelAbout.setVisible(false);

                }
            } else {
                JOptionPane.showMessageDialog(null, "username atau password salah");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }*/

        //menu mn = new menu();
        //mn.setVisible(true);
        //dispose();
    }//GEN-LAST:event_btnLoginMouseClicked

    private void txt_passMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_passMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_passMouseEntered

    private void txt_passMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_passMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_passMouseClicked

    private void txt_nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_nameMouseClicked
txt_name.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nameMouseClicked

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
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bt_login;
    private javax.swing.JPanel btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txt_name;
    private javax.swing.JPasswordField txt_pass;
    // End of variables declaration//GEN-END:variables
}
