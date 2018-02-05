/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugasbesarkelompok2.view;

import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import tugasbesarkelompok2.controller.BarangController;
import tugasbesarkelompok2.entity.Barang;
import tugasbesarkelompok2.error.BarangException;
import tugasbesarkelompok2.event.BarangListener;
import tugasbesarkelompok2.koneksi.koneksi;
import tugasbesarkelompok2.model.BarangModel;
import tugasbesarkelompok2.model.TabelBarangModel;
import tugasbesarkelompok2.model.TabelCari;
import tugasbesarkelompok2.service.BarangDao;


/**
 *
 * @author User
 */
public class BarangView extends javax.swing.JFrame implements BarangListener, ListSelectionListener {
  Connection con;

  String sql;
    
    DefaultTableModel tabmodel;
    private TabelBarangModel tabelModel;
    private BarangModel model;
    private BarangController controller;
    private TabelCari tabelCariCari;
    private TableModel model1;
            Statement stat;
    ResultSet rs;
    String tgl, data, a, b;
    
        public void kodeotomatis(){
         try {
            Connection k = new koneksi().config();
            String query = "select MAX(right(kode,3)) as no from barang";
            Statement s = k.createStatement();
            ResultSet r = s.executeQuery(query);
            while (r.next()) {
                if (r.first() == false) {
                    txtTambahKode.setText("001");
                } else {
                    r.last();

                    int id = r.getInt(1) + 1;

                    String no = String.valueOf(id);
                    int noLong = no.length();
                    for (int i = 0; i < 3 - noLong; i++) {
                        no = "0" + no;
                    }
                    txtTambahKode.setText("00" + no);
                }
            }
            
            
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        }
    }
 
    
    public BarangView() {
         
        
        tabelModel = new TabelBarangModel();
        
        model = new BarangModel();
        model.setListener(this);
        
        controller = new BarangController();
        controller.setModel(model);
        
        initComponents();
        
        tabelStok.getSelectionModel().addListSelectionListener(this);
        
        tabelStok.setModel(tabelModel);
 //       koneksi db = new koneksi();

 
 

    }
    public void bersih(){
    for (Component cek : this.panelTambahData.getComponents()){
        if (cek instanceof JTextField){
            ((JTextField)cek).setText("");
        }
       }}
     public void bersih1(){
     for (Component cek : this.panelJualBarang.getComponents()){
    if (cek instanceof JTextField){
      ((JTextField)cek).setText("");
                 
     }}}
     
    public void ShowData(){
        try {
            Object [] row = {"Kode", "namaBarang", "jenisBarang", "tipeBarang","jumlah","harga"};
            tabmodel = new DefaultTableModel(null, row);
            tabelStok.setModel(tabmodel);
            tabelstok1.setModel(tabmodel);
            Connection k = new koneksi().config();
            String query = "select * from barang ";
            Statement s = k.createStatement();
            ResultSet r = s.executeQuery(query);
            while (r.next()) {
                String a = r.getString("kode");
                String b = r.getString("namaBarang");
                String c = r.getString("jenisBarang");
                String d = r.getString("tipeBarang");
                String e = r.getString("jumlah");
                String f = r.getString("harga");

                String[] data = {a,b,c,d,e,f};
                tabmodel.addRow(data);
            }
        }catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        }
    }
    
        public void Showhistori(){
        try {
            Object [] row = {"nama", "namaBarang", "jenis", "tipe","jumlah","tanggal"};
            tabmodel = new DefaultTableModel(null, row);
            stok.setModel(tabmodel);
           // tabelstok1.setModel(tabmodel);
            Connection k = new koneksi().config();
            String query = "select penjualan.nama,barang.namaBarang,barang."
                    + "jenisBarang,barang.tipeBarang,penjualan.jumlah,penjualan.tanggal from barang "
                    + "inner join penjualan on barang.kode=penjualan.kode  ";
            Statement s = k.createStatement();
            ResultSet r = s.executeQuery(query);
            while (r.next()) {
                String a = r.getString("nama");
                String b = r.getString("namaBarang");
                String c = r.getString("jenisBarang");
                String d = r.getString("tipeBarang");
                String e = r.getString("jumlah");
                String f = r.getString("tanggal");

                String[] data = {a,b,c,d,e,f};
                tabmodel.addRow(data);
            }
        }catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        }
        

    }
       
        
    public JTextField getCari() {
        return cari;
    }

    public void setCari(JTextField cari) {
        this.cari = cari;
    }

    public JTable getTabelStok() {
        return tabelStok;
    }

    public JTextField getTxtTambahHarga() {
        return txtTambahHarga;
    }

    public JTextField getTxtTambahJumlah() {
        return txtTambahJumlah;
    }

    public JTextField getTxtTambahJenis() {
        return txtTambahJenis;
    }

    public JTextField getTxtTambahKode() {
        return txtTambahKode;
    }

    public JTextField getTxtTambahNamaBarang() {
        return txtTambahNamaBarang;
    }

    public JTextField getTxtTambahTipe() {
        return txtTambahTipe;
    }
    
    
    /**
     * Creates new form LoginRegister
     */
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Login = new javax.swing.JPanel();
        btnLogin = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        txt_pass = new javax.swing.JPasswordField();
        btnCreate = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Register = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jPasswordField3 = new javax.swing.JPasswordField();
        jPasswordField4 = new javax.swing.JPasswordField();
        haveAccount = new javax.swing.JLabel();
        btnRegCreate = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        background2 = new javax.swing.JLabel();
        Menu = new javax.swing.JPanel();
        listMenu = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        btnTambahData = new javax.swing.JLabel();
        btnJualBarang = new javax.swing.JLabel();
        btnCekstok = new javax.swing.JLabel();
        btnAbout = new javax.swing.JLabel();
        btnLogout = new javax.swing.JLabel();
        btnHistoriPenjualan = new javax.swing.JLabel();
        panelWelcome = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        panelWelcome1 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        panelTambahData = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtTambahKode = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtTambahNamaBarang = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtTambahJenis = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtTambahTipe = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtTambahHarga = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        txtTambahJumlah = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        panelJualBarang = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jlKode = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jlJumlah = new javax.swing.JTextField();
        btnJual = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelstok1 = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        nmbarang = new javax.swing.JTextField();
        jlpembeli = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jlTipe = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        tanggal = new com.toedter.calendar.JDateChooser();
        id = new javax.swing.JTextField();
        panelCekStok = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        cari = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelStok = new javax.swing.JTable();
        jLabel34 = new javax.swing.JLabel();
        txtTambahTipe1 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        txtTambahNamaBarang1 = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txtTambahJumlah1 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        txtTambahJenis1 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        txtTambahHarga1 = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        cariKode = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        panelHistori = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        stok = new javax.swing.JTable();
        panelAbout = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DEESTOCK");
        setResizable(false);

        Login.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLogin.setBackground(new java.awt.Color(75, 191, 224));
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
        btnLogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel45.setFont(new java.awt.Font("Berlin Sans FB", 1, 36)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("GO");
        btnLogin.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, 50));

        Login.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 340, 140, 60));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/GETSTARTED 2.jpg"))); // NOI18N
        Background.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BackgroundMouseEntered(evt);
            }
        });
        Login.add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 255));
        jLabel2.setText("HELLO");
        Login.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/password.png"))); // NOI18N
        Login.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 260, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/username.png"))); // NOI18N
        Login.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 220, -1, -1));

        txt_name.setBackground(new java.awt.Color(0, 0, 0, 50));
        txt_name.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_name.setForeground(new java.awt.Color(204, 255, 255));
        Login.add(txt_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 170, -1));

        txt_pass.setBackground(new java.awt.Color(0, 0, 0, 50));
        txt_pass.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_pass.setForeground(new java.awt.Color(204, 255, 255));
        txt_pass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_passMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txt_passMouseEntered(evt);
            }
        });
        Login.add(txt_pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 260, 170, -1));

        btnCreate.setBackground(new java.awt.Color(153, 153, 153));
        btnCreate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCreateMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCreateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCreateMouseExited(evt);
            }
        });

        jLabel4.setText("CREATE");

        javax.swing.GroupLayout btnCreateLayout = new javax.swing.GroupLayout(btnCreate);
        btnCreate.setLayout(btnCreateLayout);
        btnCreateLayout.setHorizontalGroup(
            btnCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
            .addGroup(btnCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btnCreateLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        btnCreateLayout.setVerticalGroup(
            btnCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(btnCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btnCreateLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        Login.add(btnCreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 300, 70, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("or");
        Login.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 310, -1, -1));

        Register.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setBackground(new java.awt.Color(204, 204, 255));
        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("REGISTER");
        Register.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 255, 255));
        jLabel7.setText("Username");
        Register.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 200, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 255, 255));
        jLabel9.setText("Phone");
        Register.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 230, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 255, 255));
        jLabel10.setText("Email");
        Register.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 255, 255));
        jLabel11.setText("Last Password");
        Register.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 290, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 255, 255));
        jLabel12.setText("New Password");
        Register.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 320, -1, -1));

        jTextField3.setBackground(new java.awt.Color(0, 0, 0, 50));
        jTextField3.setForeground(new java.awt.Color(255, 255, 255));
        Register.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 200, 200, -1));

        jTextField4.setBackground(new java.awt.Color(0, 0, 0, 50));
        jTextField4.setForeground(new java.awt.Color(255, 255, 255));
        Register.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 230, 200, -1));

        jTextField5.setBackground(new java.awt.Color(0, 0, 0, 50));
        jTextField5.setForeground(new java.awt.Color(255, 255, 255));
        Register.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, 200, -1));

        jPasswordField3.setBackground(new java.awt.Color(0, 0, 0, 50));
        jPasswordField3.setForeground(new java.awt.Color(255, 255, 255));
        Register.add(jPasswordField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 320, 200, -1));

        jPasswordField4.setBackground(new java.awt.Color(0, 0, 0, 50));
        jPasswordField4.setForeground(new java.awt.Color(255, 255, 255));
        Register.add(jPasswordField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 290, 200, -1));

        haveAccount.setBackground(new java.awt.Color(0, 0, 0));
        haveAccount.setForeground(new java.awt.Color(153, 153, 153));
        haveAccount.setText("Do you Have Account ?");
        haveAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                haveAccountMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                haveAccountMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                haveAccountMouseExited(evt);
            }
        });
        Register.add(haveAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 400, -1, 30));

        btnRegCreate.setBackground(new java.awt.Color(153, 153, 153));
        btnRegCreate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegCreateMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegCreateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegCreateMouseExited(evt);
            }
        });

        jLabel14.setText("Create");

        javax.swing.GroupLayout btnRegCreateLayout = new javax.swing.GroupLayout(btnRegCreate);
        btnRegCreate.setLayout(btnRegCreateLayout);
        btnRegCreateLayout.setHorizontalGroup(
            btnRegCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
            .addGroup(btnRegCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btnRegCreateLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel14)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        btnRegCreateLayout.setVerticalGroup(
            btnRegCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(btnRegCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btnRegCreateLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel14)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        Register.add(btnRegCreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 400, 70, 30));

        jCheckBox1.setBackground(new java.awt.Color(0, 0, 0, 0));
        jCheckBox1.setForeground(new java.awt.Color(204, 255, 255));
        jCheckBox1.setText("I Agree for all services");
        Register.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 350, 200, -1));

        background2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        background2.setForeground(new java.awt.Color(204, 255, 255));
        background2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/bgLogin.jpg"))); // NOI18N
        Register.add(background2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Menu.setBackground(new java.awt.Color(34, 49, 63));
        Menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listMenu.setBackground(new java.awt.Color(52, 73, 94));

        jPanel1.setBackground(new java.awt.Color(44, 62, 80));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/c.png"))); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 204, 204));
        jLabel15.setText("ADMIN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jSeparator1)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnTambahData.setForeground(new java.awt.Color(204, 204, 204));
        btnTambahData.setText("T A M B A H  D A T A");
        btnTambahData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTambahDataMouseClicked(evt);
            }
        });

        btnJualBarang.setForeground(new java.awt.Color(204, 204, 204));
        btnJualBarang.setText("J U A L  B A R A N G");
        btnJualBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnJualBarangMouseClicked(evt);
            }
        });

        btnCekstok.setForeground(new java.awt.Color(204, 204, 204));
        btnCekstok.setText("C E K  S T O K");
        btnCekstok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCekstokMouseClicked(evt);
            }
        });

        btnAbout.setBackground(new java.awt.Color(204, 204, 204));
        btnAbout.setForeground(new java.awt.Color(204, 204, 204));
        btnAbout.setText("A B O U T");
        btnAbout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAboutMouseClicked(evt);
            }
        });

        btnLogout.setForeground(new java.awt.Color(204, 204, 204));
        btnLogout.setText("L O G O U T");
        btnLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLogoutMouseClicked(evt);
            }
        });

        btnHistoriPenjualan.setForeground(new java.awt.Color(204, 204, 204));
        btnHistoriPenjualan.setText("  H I S T O R I  ");
        btnHistoriPenjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHistoriPenjualanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout listMenuLayout = new javax.swing.GroupLayout(listMenu);
        listMenu.setLayout(listMenuLayout);
        listMenuLayout.setHorizontalGroup(
            listMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(listMenuLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(btnTambahData))
            .addGroup(listMenuLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(btnJualBarang))
            .addGroup(listMenuLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(btnLogout))
            .addGroup(listMenuLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(btnAbout))
            .addGroup(listMenuLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(listMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnHistoriPenjualan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCekstok, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        listMenuLayout.setVerticalGroup(
            listMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listMenuLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTambahData)
                .addGap(38, 38, 38)
                .addComponent(btnJualBarang)
                .addGap(38, 38, 38)
                .addComponent(btnCekstok)
                .addGap(38, 38, 38)
                .addComponent(btnHistoriPenjualan)
                .addGap(38, 38, 38)
                .addComponent(btnAbout)
                .addGap(79, 79, 79)
                .addComponent(btnLogout))
        );

        Menu.add(listMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 550));

        panelWelcome.setBackground(new java.awt.Color(34, 49, 63));

        jLabel56.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(204, 204, 204));
        jLabel56.setText("membantu memanage barang sehingga anda tidak susah untuk mengelolanya ");

        panelWelcome1.setBackground(new java.awt.Color(34, 49, 63));

        jLabel57.setFont(new java.awt.Font("Cooper Std Black", 0, 24)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(204, 204, 204));
        jLabel57.setText("WELCOME");

        jLabel58.setFont(new java.awt.Font("Hemi Head Rg", 0, 18)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(204, 204, 204));
        jLabel58.setText("DEESTOCK ");

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(204, 204, 204));
        jLabel59.setText("Deestock adalah aplikasi yang dapat membantu anda untuk mengelola barang jualan,");

        javax.swing.GroupLayout panelWelcome1Layout = new javax.swing.GroupLayout(panelWelcome1);
        panelWelcome1.setLayout(panelWelcome1Layout);
        panelWelcome1Layout.setHorizontalGroup(
            panelWelcome1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelWelcome1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(panelWelcome1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel59)
                    .addComponent(jLabel58)
                    .addGroup(panelWelcome1Layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(jLabel57)))
                .addContainerGap(152, Short.MAX_VALUE))
        );
        panelWelcome1Layout.setVerticalGroup(
            panelWelcome1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelWelcome1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel57)
                .addGap(18, 18, 18)
                .addComponent(jLabel58)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel59)
                .addContainerGap(427, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelWelcomeLayout = new javax.swing.GroupLayout(panelWelcome);
        panelWelcome.setLayout(panelWelcomeLayout);
        panelWelcomeLayout.setHorizontalGroup(
            panelWelcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelWelcomeLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel56)
                .addContainerGap(190, Short.MAX_VALUE))
            .addGroup(panelWelcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelWelcomeLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelWelcome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panelWelcomeLayout.setVerticalGroup(
            panelWelcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelWelcomeLayout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(jLabel56)
                .addContainerGap(409, Short.MAX_VALUE))
            .addGroup(panelWelcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelWelcomeLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelWelcome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        Menu.add(panelWelcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 620, 550));

        panelTambahData.setBackground(new java.awt.Color(34, 49, 63));
        panelTambahData.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(204, 204, 204));
        jLabel17.setText("TAMBAH DATA");
        panelTambahData.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 35, -1, -1));

        jLabel18.setForeground(new java.awt.Color(204, 204, 204));
        jLabel18.setText("Kode");
        panelTambahData.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        txtTambahKode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTambahKodeKeyReleased(evt);
            }
        });
        panelTambahData.add(txtTambahKode, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 170, 40));

        jLabel19.setForeground(new java.awt.Color(204, 204, 204));
        jLabel19.setText("Nama");
        panelTambahData.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 191, -1, -1));
        panelTambahData.add(txtTambahNamaBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 170, 40));

        jLabel20.setForeground(new java.awt.Color(204, 204, 204));
        jLabel20.setText("Jenis");
        panelTambahData.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, -1));
        panelTambahData.add(txtTambahJenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 170, 40));

        jLabel21.setForeground(new java.awt.Color(204, 204, 204));
        jLabel21.setText("Tipe");
        panelTambahData.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, -1, -1));

        txtTambahTipe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTambahTipeActionPerformed(evt);
            }
        });
        panelTambahData.add(txtTambahTipe, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, 170, 40));

        jLabel22.setForeground(new java.awt.Color(204, 204, 204));
        jLabel22.setText("Jumlah");
        panelTambahData.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 190, -1, -1));
        panelTambahData.add(txtTambahHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 320, 170, 40));

        jLabel23.setForeground(new java.awt.Color(204, 204, 204));
        jLabel23.setText("Harga");
        panelTambahData.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 290, -1, -1));

        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jLabel24.setText("SIMPAN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 66, Short.MAX_VALUE)
                    .addComponent(jLabel24)
                    .addGap(0, 66, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 13, Short.MAX_VALUE)
                    .addComponent(jLabel24)
                    .addGap(0, 13, Short.MAX_VALUE)))
        );

        panelTambahData.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 450, 170, 40));
        panelTambahData.add(txtTambahJumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 220, 170, 40));

        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel9MouseClicked(evt);
            }
        });

        jLabel5.setText("Reset");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panelTambahData.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 450, 170, 40));

        Menu.add(panelTambahData, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 620, 550));

        panelJualBarang.setBackground(new java.awt.Color(34, 49, 63));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(204, 204, 204));
        jLabel25.setText("JUAL BARANG");

        jLabel30.setForeground(new java.awt.Color(204, 204, 204));
        jLabel30.setText("Kode");

        jlKode.setEnabled(false);

        jLabel31.setForeground(new java.awt.Color(204, 204, 204));
        jLabel31.setText("Jumlah");

        btnJual.setBackground(new java.awt.Color(204, 204, 204));
        btnJual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnJualMouseClicked(evt);
            }
        });

        jLabel32.setText("J U A L");

        javax.swing.GroupLayout btnJualLayout = new javax.swing.GroupLayout(btnJual);
        btnJual.setLayout(btnJualLayout);
        btnJualLayout.setHorizontalGroup(
            btnJualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
            .addGroup(btnJualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btnJualLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel32)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        btnJualLayout.setVerticalGroup(
            btnJualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(btnJualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(btnJualLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel32)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        tabelstok1.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelstok1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabelstok1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelstok1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelstok1);

        jLabel26.setForeground(new java.awt.Color(204, 204, 204));
        jLabel26.setText("Tanggal");

        jLabel27.setForeground(new java.awt.Color(204, 204, 204));
        jLabel27.setText("Nama Barang");

        nmbarang.setEnabled(false);
        nmbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nmbarangActionPerformed(evt);
            }
        });

        jLabel28.setForeground(new java.awt.Color(204, 204, 204));
        jLabel28.setText("Nama Pembeli");

        jlTipe.setEnabled(false);

        jLabel29.setForeground(new java.awt.Color(204, 204, 204));
        jLabel29.setText("Tipe");

        tanggal.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tanggalPropertyChange(evt);
            }
        });

        id.setBackground(new java.awt.Color(34, 49, 63));
        id.setForeground(new java.awt.Color(255, 255, 255));
        id.setEnabled(false);
        id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelJualBarangLayout = new javax.swing.GroupLayout(panelJualBarang);
        panelJualBarang.setLayout(panelJualBarangLayout);
        panelJualBarangLayout.setHorizontalGroup(
            panelJualBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJualBarangLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(549, Short.MAX_VALUE))
            .addGroup(panelJualBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelJualBarangLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(panelJualBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelJualBarangLayout.createSequentialGroup()
                            .addGap(180, 180, 180)
                            .addComponent(jLabel25))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelJualBarangLayout.createSequentialGroup()
                            .addGap(60, 60, 60)
                            .addComponent(jLabel26)
                            .addGap(202, 202, 202)
                            .addComponent(jLabel27))
                        .addGroup(panelJualBarangLayout.createSequentialGroup()
                            .addGap(60, 60, 60)
                            .addComponent(tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(90, 90, 90)
                            .addComponent(nmbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelJualBarangLayout.createSequentialGroup()
                            .addGap(60, 60, 60)
                            .addComponent(jLabel28)
                            .addGap(174, 174, 174)
                            .addComponent(jLabel29))
                        .addGroup(panelJualBarangLayout.createSequentialGroup()
                            .addGap(60, 60, 60)
                            .addComponent(jlpembeli, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(90, 90, 90)
                            .addComponent(jlTipe, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelJualBarangLayout.createSequentialGroup()
                            .addGap(60, 60, 60)
                            .addComponent(jLabel30)
                            .addGap(216, 216, 216)
                            .addComponent(jLabel31))
                        .addGroup(panelJualBarangLayout.createSequentialGroup()
                            .addGap(60, 60, 60)
                            .addComponent(jlKode, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(90, 90, 90)
                            .addComponent(jlJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelJualBarangLayout.createSequentialGroup()
                            .addGap(150, 150, 150)
                            .addComponent(btnJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panelJualBarangLayout.setVerticalGroup(
            panelJualBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJualBarangLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(500, Short.MAX_VALUE))
            .addGroup(panelJualBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelJualBarangLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel25)
                    .addGap(11, 11, 11)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)
                    .addGroup(panelJualBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel26)
                        .addComponent(jLabel27))
                    .addGap(6, 6, 6)
                    .addGroup(panelJualBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nmbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(panelJualBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel28)
                        .addComponent(jLabel29))
                    .addGap(6, 6, 6)
                    .addGroup(panelJualBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jlpembeli, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlTipe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(panelJualBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelJualBarangLayout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelJualBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jlKode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(20, 20, 20)
                    .addComponent(btnJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        Menu.add(panelJualBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 620, 550));

        panelCekStok.setBackground(new java.awt.Color(34, 49, 63));

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(204, 204, 204));
        jLabel33.setText("C E K  S T O K");

        cari.setText("                                   C A R I  B E R D A S A R K A N");
        cari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cariMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cariMouseEntered(evt);
            }
        });
        cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cariKeyReleased(evt);
            }
        });

        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
        });

        jLabel40.setText("HAPUS");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel40)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabelStok.setBackground(new java.awt.Color(34, 49, 63));
        tabelStok.setForeground(new java.awt.Color(204, 204, 204));
        tabelStok.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Kode", "Nama Barang", "Jenis", "Tipe", "Jumlah", "Harga"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelStok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelStokMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelStok);
        if (tabelStok.getColumnModel().getColumnCount() > 0) {
            tabelStok.getColumnModel().getColumn(0).setResizable(false);
            tabelStok.getColumnModel().getColumn(0).setPreferredWidth(20);
            tabelStok.getColumnModel().getColumn(3).setResizable(false);
            tabelStok.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel34.setForeground(new java.awt.Color(204, 204, 204));
        jLabel34.setText("Tipe");

        txtTambahTipe1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTambahTipe1ActionPerformed(evt);
            }
        });

        jLabel39.setForeground(new java.awt.Color(204, 204, 204));
        jLabel39.setText("Nama Barang");

        jLabel41.setForeground(new java.awt.Color(204, 204, 204));
        jLabel41.setText("Jumlah");

        jLabel42.setForeground(new java.awt.Color(204, 204, 204));
        jLabel42.setText("Jenis");

        jLabel43.setForeground(new java.awt.Color(204, 204, 204));
        jLabel43.setText("Harga");

        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel10MouseClicked(evt);
            }
        });

        jLabel44.setText("EDIT");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel44)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel55.setForeground(new java.awt.Color(204, 204, 204));
        jLabel55.setText("Kode");

        javax.swing.GroupLayout panelCekStokLayout = new javax.swing.GroupLayout(panelCekStok);
        panelCekStok.setLayout(panelCekStokLayout);
        panelCekStokLayout.setHorizontalGroup(
            panelCekStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCekStokLayout.createSequentialGroup()
                .addGroup(panelCekStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCekStokLayout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCekStokLayout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addComponent(jLabel33))
                    .addGroup(panelCekStokLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 35, Short.MAX_VALUE))
            .addGroup(panelCekStokLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(panelCekStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCekStokLayout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(174, 174, 174))
                    .addGroup(panelCekStokLayout.createSequentialGroup()
                        .addGroup(panelCekStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel39)
                            .addComponent(txtTambahNamaBarang1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(jLabel41)
                            .addComponent(txtTambahJumlah1))
                        .addGap(69, 69, 69)
                        .addGroup(panelCekStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCekStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtTambahHarga1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTambahJenis1, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel43))
                        .addGap(69, 69, 69)
                        .addGroup(panelCekStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCekStokLayout.createSequentialGroup()
                                .addComponent(jLabel55)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panelCekStokLayout.createSequentialGroup()
                                .addGroup(panelCekStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTambahTipe1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                    .addComponent(cariKode))
                                .addContainerGap(82, Short.MAX_VALUE))))))
        );
        panelCekStokLayout.setVerticalGroup(
            panelCekStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCekStokLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(panelCekStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelCekStokLayout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTambahNamaBarang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCekStokLayout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTambahJenis1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCekStokLayout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTambahTipe1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelCekStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jLabel43)
                    .addComponent(jLabel55))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCekStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCekStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTambahHarga1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cariKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTambahJumlah1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(panelCekStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        Menu.add(panelCekStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 620, 550));

        panelHistori.setBackground(new java.awt.Color(34, 49, 63));

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(204, 204, 204));
        jLabel36.setText("HISTORI PENJUALAN");

        stok.setBackground(new java.awt.Color(34, 49, 63));
        stok.setForeground(new java.awt.Color(204, 204, 204));
        stok.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Kode", "Nama Barang", "Jenis", "Tipe", "Jumlah", "Harga"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(stok);
        if (stok.getColumnModel().getColumnCount() > 0) {
            stok.getColumnModel().getColumn(0).setResizable(false);
            stok.getColumnModel().getColumn(0).setPreferredWidth(20);
            stok.getColumnModel().getColumn(3).setResizable(false);
            stok.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout panelHistoriLayout = new javax.swing.GroupLayout(panelHistori);
        panelHistori.setLayout(panelHistoriLayout);
        panelHistoriLayout.setHorizontalGroup(
            panelHistoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHistoriLayout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addComponent(jLabel36)
                .addContainerGap(201, Short.MAX_VALUE))
            .addGroup(panelHistoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelHistoriLayout.createSequentialGroup()
                    .addGap(31, 31, 31)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(32, Short.MAX_VALUE)))
        );
        panelHistoriLayout.setVerticalGroup(
            panelHistoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHistoriLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel36)
                .addContainerGap(475, Short.MAX_VALUE))
            .addGroup(panelHistoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelHistoriLayout.createSequentialGroup()
                    .addGap(125, 125, 125)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(125, Short.MAX_VALUE)))
        );

        Menu.add(panelHistori, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 620, 550));

        panelAbout.setBackground(new java.awt.Color(34, 49, 63));
        panelAbout.setPreferredSize(new java.awt.Dimension(650, 550));
        panelAbout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(204, 204, 204,60));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Dev : ");
        jPanel6.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 11, -1, -1));

        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/1.png"))); // NOI18N
        jPanel6.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/dondon.png"))); // NOI18N
        jPanel6.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, -1));

        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/oke.jpg"))); // NOI18N
        jPanel6.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, -1, -1));

        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("ERZI HUTAMA  DWIRAMA PUTRA");
        jPanel6.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("DONNY HASSAN HASIBUAN");
        jPanel6.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, -1, -1));

        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("MUHAMMAD NURCAHYA EKO D.");
        jPanel6.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 230, -1, -1));

        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("DEVELOPER");
        jPanel6.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 250, -1, -1));

        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("UI/UX DESIGN");
        jPanel6.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 250, -1, -1));

        jLabel53.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 18)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("DEESTOCK V1 BETA");
        jPanel6.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("DEVELOPER");
        jPanel6.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, -1, -1));

        panelAbout.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 590, 450));

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(204, 204, 204));
        jLabel37.setText("ABOUT");
        panelAbout.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, -1, -1));

        Menu.add(panelAbout, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 620, 550));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Register, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Register, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    Color go = new Color(25,181,254);
    Color awalLogReg = new Color(153,153,153);
    Color rubahLogReg = new Color(204,204,204);
    Color haveAcc = new Color(153,153,255);
    Color ubah = new Color(153, 153, 153,100) ;
    Color biru = new Color(75,191,224);
    
    private void haveAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_haveAccountMouseClicked
        // TODO add your handling code here:
        Login.setVisible(true);
        Register.setVisible(false);
    }//GEN-LAST:event_haveAccountMouseClicked

    private void haveAccountMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_haveAccountMouseEntered
        // TODO add your handling code here:
        haveAccount.setForeground(rubahLogReg);
    }//GEN-LAST:event_haveAccountMouseEntered

    private void haveAccountMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_haveAccountMouseExited
        // TODO add your handling code here:
        haveAccount.setForeground(awalLogReg);
    }//GEN-LAST:event_haveAccountMouseExited

    private void btnRegCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegCreateMouseClicked
        // TODO add your handling code here:
        Register.setVisible(false);
        Login.setVisible(true);
        panelWelcome.setVisible(false);
        panelJualBarang.setVisible(false);
        panelTambahData.setVisible(false);
        panelJualBarang.setVisible(false);
        panelCekStok.setVisible(false);
        panelHistori.setVisible(false);
        panelAbout.setVisible(false);
    }//GEN-LAST:event_btnRegCreateMouseClicked

    private void btnRegCreateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegCreateMouseEntered
        // TODO add your handling code here:
        btnRegCreate.setBackground(rubahLogReg);
    }//GEN-LAST:event_btnRegCreateMouseEntered

    private void btnRegCreateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegCreateMouseExited
        // TODO add your handling code here:
        btnRegCreate.setBackground(awalLogReg);
    }//GEN-LAST:event_btnRegCreateMouseExited

    private void btnTambahDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTambahDataMouseClicked
        // TODO add your handling code here:
                kodeotomatis();
        panelWelcome.setVisible(false);
        panelTambahData.setVisible(true);
        panelJualBarang.setVisible(false);
        panelCekStok.setVisible(false);
        panelHistori.setVisible(false);
        panelAbout.setVisible(false);
    }//GEN-LAST:event_btnTambahDataMouseClicked

    private void btnJualBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnJualBarangMouseClicked
        ShowData();
        // TODO add your handling code here:
        panelWelcome.setVisible(false);
        panelTambahData.setVisible(false);
        panelJualBarang.setVisible(true);
        panelCekStok.setVisible(false);
        panelHistori.setVisible(false);
        panelAbout.setVisible(false);
    }//GEN-LAST:event_btnJualBarangMouseClicked

    private void btnCekstokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCekstokMouseClicked
         ShowData();          // TODO add your handling code here:
        panelWelcome.setVisible(false);
        panelTambahData.setVisible(false);
        panelJualBarang.setVisible(false);
        panelCekStok.setVisible(true);
        panelHistori.setVisible(false);
        panelAbout.setVisible(false);
    }//GEN-LAST:event_btnCekstokMouseClicked

    private void btnAboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAboutMouseClicked
        // TODO add your handling code here:
        panelWelcome.setVisible(false);
        panelTambahData.setVisible(false);
        panelJualBarang.setVisible(false);
        panelCekStok.setVisible(false);
        panelHistori.setVisible(false);
        panelAbout.setVisible(true);
    }//GEN-LAST:event_btnAboutMouseClicked

    private void btnHistoriPenjualanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHistoriPenjualanMouseClicked
Showhistori();        // TODO add your handling code here:
        panelWelcome.setVisible(false);
        panelTambahData.setVisible(false);
        panelJualBarang.setVisible(false);
        panelCekStok.setVisible(false);
        panelHistori.setVisible(true);
        panelAbout.setVisible(false);
    }//GEN-LAST:event_btnHistoriPenjualanMouseClicked

    private void btnLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseClicked
        login br = new login();
                br.show(true);
                this.hide();
        
    }//GEN-LAST:event_btnLogoutMouseClicked

    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked
        controller.resetBarang(this);
        kodeotomatis();
        
        //
    }//GEN-LAST:event_jPanel9MouseClicked

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
                
        controller.insertBarang(this);
       bersih();
       kodeotomatis();
    }//GEN-LAST:event_jPanel2MouseClicked

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
              
        if (cariKode.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Tidak ada data yang bisa di hapus");
            }else{
            String kode = cariKode.getText();
            int pilih;
            pilih = JOptionPane.showConfirmDialog(null, "Hapus data dengan kode = " + kode, "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if(pilih == JOptionPane.YES_OPTION){
            try{
            Connection k = new koneksi().config();
            String query = "delete from barang where kode = '" + cariKode.getText() + "'";
            PreparedStatement ps = k.prepareStatement(query);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data telah terhapus");
          
            ShowData();
            bersih();
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        }
                    }
}
    }//GEN-LAST:event_jPanel8MouseClicked

    private void txtTambahTipeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTambahTipeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTambahTipeActionPerformed

    private void cariMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cariMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_cariMouseEntered

    private void cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariKeyReleased
/*    tabelStok.setModel(tabelModel);
    String cariT = cari.getText();
        try {
            BarangDao dao = koneksi.getBarangDao();
            tabelCariCari.setList(dao.selectCariBarang(cariT));
          
            tabelStok.setModel(tabelCariCari);
        } catch (SQLException ex) {
        Logger.getLogger(BarangView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BarangException ex) {
        Logger.getLogger(BarangView.class.getName()).log(Level.SEVERE, null, ex);
        }
        tabelStok.setModel(tabelCariCari);
        onChange(model);
   */
         try {
            Object [] row = {"Kode", "namaBarang", "jenisBarang", "tipeBarang","jumlah","harga"};
            tabmodel = new DefaultTableModel(null, row);
            tabelStok.setModel(tabmodel);

            Connection k = new koneksi().config();
            String query = "select * from barang where kode like '%" + cari.getText() + "%' or namaBarang like '%" + cari.getText() 
                    + "%'or tipeBarang like '%" + cari.getText() + "%'or jenisBarang like '%" + cari.getText() + "%'";
            Statement s = k.createStatement();
            ResultSet r = s.executeQuery(query);
            while (r.next()) {
                String a = r.getString("kode");
                String b = r.getString("namaBarang");
                String c = r.getString("jenisBarang");
                String d = r.getString("tipeBarang");
                String e = r.getString("jumlah");
                String f = r.getString("harga");

                String[] data = {a,b,c,d,e,f};
                tabmodel.addRow(data);
             
            }
            
        }catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        }   
    }//GEN-LAST:event_cariKeyReleased

    private void cariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cariMouseClicked
    cari.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_cariMouseClicked

    private void txtTambahKodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTambahKodeKeyReleased
               kodeotomatis(); // TODO add your handling code here:
    }//GEN-LAST:event_txtTambahKodeKeyReleased

    private void btnJualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnJualMouseClicked
        String adakosong = "tidak";

        try {
            if ("tidak".equals(adakosong)) {
                String query = "select *from penjualan where id = " + id.getText() + "";

                String kon = id.getText();

                Connection z = new koneksi().config();

                String query1 = "insert into penjualan (kode,  jumlah , tanggal,nama ) values (?,?,?,?)";

                PreparedStatement ps = null;
                ps = z.prepareStatement(query1);
                ps.setString(1, jlKode.getText());
                ps.setString(2, jlJumlah.getText());
                ps.setString(3, tgl);
                ps.setString(4, jlpembeli.getText());
                ps.executeUpdate();
                
                bersih1();
                JOptionPane.showMessageDialog(null, "Berhasil terjual");
            }
            bersih1();
            kodeotomatis();
            ShowData();
        } catch (Exception ex) {
            System.out.println("Error" + ex.getMessage());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnJualMouseClicked

    private void tabelstok1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelstok1MouseClicked
        int baris = tabelstok1.getSelectedRow();

        DefaultTableModel TM = (DefaultTableModel)tabelstok1.getModel();
        try {
            int row = tabelstok1.rowAtPoint(evt.getPoint());
            Connection con = new koneksi().config();
            String query = "select kode, namaBarang, tipeBarang  from barang where kode = '" + tabelstok1.getValueAt(row, 0).toString() + "'";
            Statement s = con.createStatement();
            ResultSet r = s.executeQuery(query);
            while (r.next()) {
                String a = r.getString("kode");
                jlKode.setText(a);
               // String b = r.getString("harga");
                //harga.setText(b);
                String c = r.getString("namaBarang");
                nmbarang.setText(c);
               // String d = r.getString("jumlah");
                //jumlah.setText(d);
                String e = r.getString("tipeBarang");
                jlTipe.setText(e);
                //String f = r.getString("keterangan");
                //keterangan.setText(f);

            }

        }catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        }

    }//GEN-LAST:event_tabelstok1MouseClicked

    private void nmbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nmbarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmbarangActionPerformed

    private void tanggalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tanggalPropertyChange
        if (tanggal.getDate()!=null){
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
            tgl = s.format(tanggal.getDate());
        }
    }//GEN-LAST:event_tanggalPropertyChange

    private void txtTambahTipe1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTambahTipe1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTambahTipe1ActionPerformed

    private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseClicked
try {
            String adakosong = "tidak";
            for (Component cek : jPanel1.getComponents()) {
                if (cek instanceof JTextField) {
                    if (((JTextField) cek).getText().equals("")) {
                        adakosong = "ya";
                    }
                  
//                     else if (cek instanceof JDateChooser) {
//                    if (((JDateChooser) cek).getDate().equals(null)) {
//                        adakosong = "ya";
//                    }
//                    }
               
               }
            }
      if ("tidak".equals(adakosong)) {
          if (cariKode.getText().equals("")){
              JOptionPane.showMessageDialog(null, "Tidak Ada Data Yang Akan Di Edit");
          }else{
              
            String kode = cariKode.getText();
            Connection k = new koneksi().config();
            String query = "update barang set namaBarang=?, jenisBarang=?, tipeBarang=?,"
                    + " jumlah=?, harga=? where kode = '" + cariKode.getText() + "'";
            PreparedStatement ps = k.prepareStatement(query);
            ps.setString(1, txtTambahNamaBarang1.getText());
            ps.setString(2, txtTambahJenis1.getText());
            ps.setString(3, txtTambahTipe1.getText());
            ps.setString(4, txtTambahJumlah1.getText());
            ps.setString(5, txtTambahHarga1.getText());
            ps.executeUpdate();
           
            JOptionPane.showMessageDialog(null, "Data berhasil di ubah");
         
           ShowData();           
            bersih();}
          } else {
                JOptionPane.showMessageDialog(null,"Lengkapi data");
            }
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        }    }//GEN-LAST:event_jPanel10MouseClicked

    private void BackgroundMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackgroundMouseEntered
        // TODO add your handling code here:
        btnLogin.setVisible(true);
        Register.setVisible(false);
        panelWelcome.setVisible(false);
        panelJualBarang.setVisible(false);
        panelTambahData.setVisible(false);
        panelJualBarang.setVisible(false);
        panelCekStok.setVisible(false);
        panelHistori.setVisible(false);
        panelAbout.setVisible(false);
    }//GEN-LAST:event_BackgroundMouseEntered

    private void btnLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseExited
        // TODO add your handling code here:
        btnLogin.setBackground(biru);
    }//GEN-LAST:event_btnLoginMouseExited

    private void btnLoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseEntered
        // TODO add your handling code here:
        btnLogin.setBackground(go);
    }//GEN-LAST:event_btnLoginMouseEntered

    private void btnLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseClicked
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

        /*try {
            sql = "SELECT * FROM admin WHERE username='" + txt_name.getText() + "' AND password='" + txt_pass.getText() + "'";
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
        */
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

    private void btnCreateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCreateMouseExited
        // TODO add your handling code here:
        btnCreate.setBackground(awalLogReg);
    }//GEN-LAST:event_btnCreateMouseExited

    private void btnCreateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCreateMouseEntered
        // TODO add your handling code here:
        btnCreate.setBackground(rubahLogReg);
    }//GEN-LAST:event_btnCreateMouseEntered

    private void btnCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCreateMouseClicked
        // TODO add your handling code here:
        Register.setVisible(true);
        Login.setVisible(false);
        panelWelcome.setVisible(false);
        panelJualBarang.setVisible(false);
        panelTambahData.setVisible(false);
        panelJualBarang.setVisible(false);
        panelCekStok.setVisible(false);
        panelHistori.setVisible(false);
        panelAbout.setVisible(false);
    }//GEN-LAST:event_btnCreateMouseClicked

    private void txt_passMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_passMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_passMouseEntered

    private void txt_passMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_passMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_passMouseClicked

    private void tabelStokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelStokMouseClicked
 int baris = tabelStok.getSelectedRow();

        DefaultTableModel TM = (DefaultTableModel)tabelStok.getModel();
        try {
            int row = tabelStok.rowAtPoint(evt.getPoint());
            Connection con = new koneksi().config();
            String query = "select kode, namaBarang,jenisBarang, tipeBarang,jumlah,harga from barang where kode = '" + tabelStok.getValueAt(row, 0).toString() + "'";
            Statement s = con.createStatement();
            ResultSet r = s.executeQuery(query);
            while (r.next()) {
                String a = r.getString("kode");
                cariKode.setText(a);
               String b = r.getString("harga");
               txtTambahHarga1.setText(b);
                String c = r.getString("namaBarang");
                txtTambahNamaBarang1.setText(c);
               String d = r.getString("jumlah");
               txtTambahJumlah1.setText(d);
                String e = r.getString("tipeBarang");
                txtTambahTipe1.setText(e);
               String f = r.getString("jenisBarang");
               txtTambahJenis1.setText(f);

            }

        }catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        }    }//GEN-LAST:event_tabelStokMouseClicked

    private void idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idActionPerformed
     
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
            java.util.logging.Logger.getLogger(BarangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BarangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BarangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BarangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BarangView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JPanel Login;
    private javax.swing.JPanel Menu;
    private javax.swing.JPanel Register;
    private javax.swing.JLabel background2;
    private javax.swing.JLabel btnAbout;
    private javax.swing.JLabel btnCekstok;
    private javax.swing.JPanel btnCreate;
    private javax.swing.JLabel btnHistoriPenjualan;
    private javax.swing.JPanel btnJual;
    private javax.swing.JLabel btnJualBarang;
    private javax.swing.JPanel btnLogin;
    private javax.swing.JLabel btnLogout;
    private javax.swing.JPanel btnRegCreate;
    private javax.swing.JLabel btnTambahData;
    private javax.swing.JTextField cari;
    private javax.swing.JTextField cariKode;
    private javax.swing.JLabel haveAccount;
    private javax.swing.JTextField id;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JPasswordField jPasswordField4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jlJumlah;
    private javax.swing.JTextField jlKode;
    private javax.swing.JTextField jlTipe;
    private javax.swing.JTextField jlpembeli;
    private javax.swing.JPanel listMenu;
    private javax.swing.JTextField nmbarang;
    private javax.swing.JPanel panelAbout;
    private javax.swing.JPanel panelCekStok;
    private javax.swing.JPanel panelHistori;
    private javax.swing.JPanel panelJualBarang;
    private javax.swing.JPanel panelTambahData;
    private javax.swing.JPanel panelWelcome;
    private javax.swing.JPanel panelWelcome1;
    private javax.swing.JTable stok;
    private javax.swing.JTable tabelStok;
    public javax.swing.JTable tabelstok1;
    private com.toedter.calendar.JDateChooser tanggal;
    private javax.swing.JTextField txtTambahHarga;
    private javax.swing.JTextField txtTambahHarga1;
    private javax.swing.JTextField txtTambahJenis;
    private javax.swing.JTextField txtTambahJenis1;
    private javax.swing.JTextField txtTambahJumlah;
    private javax.swing.JTextField txtTambahJumlah1;
    private javax.swing.JTextField txtTambahKode;
    private javax.swing.JTextField txtTambahNamaBarang;
    private javax.swing.JTextField txtTambahNamaBarang1;
    private javax.swing.JTextField txtTambahTipe;
    private javax.swing.JTextField txtTambahTipe1;
    private javax.swing.JTextField txt_name;
    private javax.swing.JPasswordField txt_pass;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onChange(BarangModel model) {
        txtTambahKode.setText(model.getKode()+"");
        txtTambahNamaBarang.setText(model.getNamaBarang()+"");
        txtTambahJenis.setText(model.getJenisBarang()+"");
        txtTambahTipe.setText(model.getTipeBarang()+"");
        txtTambahJumlah.setText(model.getJumlah()+"");
        txtTambahHarga.setText(model.getHarga()+"");
    }

    @Override
    public void onInsert(Barang barang) {
        tabelModel.add(barang);
    }

    @Override
    public void onDelete() {
        int index = tabelStok.getSelectedRow();
        tabelModel.remove(index);
    }

    @Override
    public void onUpdate(Barang barang) {
        int index = tabelStok.getSelectedRow();
        tabelModel.set(index, barang);
    }
    public void LoadDatabse() throws SQLException, BarangException{
        BarangDao dao = koneksi.getBarangDao();
        
        tabelModel.setList(dao.selectAllBarang());
        
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        
        try {
            Barang model = tabelModel.get(tabelStok.getSelectedRow());
            txtTambahKode.setText(model.getKode()+"");
            txtTambahNamaBarang.setText(model.getNamaBarang()+"");
            txtTambahJenis.setText(model.getJenisBarang()+"");
            txtTambahTipe.setText(model.getTipeBarang()+"");
            txtTambahJumlah.setText(model.getJumlah()+"");
            txtTambahHarga.setText(model.getHarga()+"");
        } catch (IndexOutOfBoundsException exception) {
        }

    }
}

