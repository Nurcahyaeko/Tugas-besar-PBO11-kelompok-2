package tugasbesarkelompok2.koneksi;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import tugasbesarkelompok2.impl.BarangDaoImpl;
import tugasbesarkelompok2.service.BarangDao;

/**
 *
 * @author User
 */
public class koneksi {

    private static Connection connection;
    private static BarangDao barangDao;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {

            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setURL("jdbc:mysql://localhost:3306/dbbarang");
            dataSource.setUser("root");
            dataSource.setPassword("");
            connection = dataSource.getConnection();
        }

        return connection;
    }

    public static BarangDao getBarangDao() throws SQLException {
        if (barangDao == null) {
            barangDao = new BarangDaoImpl(getConnection());
        }

        return barangDao;
    }
    
    
      public static Connection con;
    public static Statement stm;
    
    public static Connection config(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/dbbarang", "root", "");
            stm = con.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "koneksi gagal "+e.getMessage());
        }
        return con;
    }
    public static int execute(String SQL) {
        int status = 0;
       Connection coneksi = config();
        try {
            Statement st = coneksi.createStatement();
            status = st.executeUpdate(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
    public static ResultSet executeQuery(String SQL) {
        ResultSet rs = null;
        Connection coneksi = config();
        try {
            Statement st = coneksi.createStatement();
            rs = st.executeQuery(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }}