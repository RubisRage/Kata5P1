package kata5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Kata5 {

    private static void createNewTable(Connection conn) throws SQLException{
        String sql = "CREATE TABLE IF NOT EXISTS email(\n"
                        + "id integer primary key autoincrement,\n"
                        + "direccion text NOT NULL);";
        
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
    }
    
    public static void main(String[] args) {
        String url = "jdbc:sqlite:KATA5.db";
        String sql = "SELECT * FROM PEOPLE";
        
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
            
            while(rs.next()) {
                System.out.println(rs.getInt("id") + "\t" 
                                   + rs.getString("name") + "\t"
                                   + rs.getString("Apellidos") + "\t"
                                   + rs.getString("Departamento") + "\t");
            }
            
            createNewTable(conn);
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        
        
    }
    
}
