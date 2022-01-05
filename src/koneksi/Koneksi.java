package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {

    private Connection koneksi;
        
    private final String URL_DB = "jdbc:mysql://localhost:3306/db_stock_barang",
            USER = "root",
            PASS="";
    public Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Berhasil Koneksi");
        } catch (ClassNotFoundException ex) {
            System.out.println("Gagal Koneksi" + ex);
        }


        try {
            koneksi = DriverManager.getConnection(URL_DB, USER, PASS);
            System.out.println("Berhasil Koneksi Database");
        } catch (SQLException ex) {
            System.out.println("Gagal Koneksi Database" + ex);
        }
        return koneksi;
    }
}
