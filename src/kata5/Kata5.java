package kata5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class Kata5 {

    private static void createNewTable(Connection conn) throws SQLException{
        String sql = """
                     CREATE TABLE IF NOT EXISTS email(
                     id integer primary key autoincrement,
                     direccion text NOT NULL);""";
        
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
        
        try(BufferedReader br = new BufferedReader(new FileReader("email.txt")))
        {
            String email;
            String insertSql = """
                               INSERT INTO email(direccion) VALUES (?)
                               """;
            PreparedStatement pstmt = conn.prepareStatement(insertSql);
            while((email = br.readLine()) != null){
                pstmt.setString(1,email);
                pstmt.executeUpdate();
            }
            
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
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
