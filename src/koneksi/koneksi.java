package koneksi;
import java.sql.*;

public class koneksi {
     private Connection conn;
    public Connection connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Berhasil conn");
        }catch(ClassNotFoundException ex){
            System.out.println("Gagal conn "+ex);
        }
        String url = "jdbc:mysql://localhost/spk_rul";
        
        try{
            conn = DriverManager.getConnection(url,"root","");
            System.out.println("Berhasil conn Database");
        }catch(SQLException ex){
            System.out.println("Gagal conn Database "+ex);
        }
        return conn;
    }
}
