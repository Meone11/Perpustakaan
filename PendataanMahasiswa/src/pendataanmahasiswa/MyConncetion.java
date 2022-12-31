/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pendataanmahasiswa;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author meone
 */
public class MyConncetion {
    
    //Make a Connection
    public static Connection getConnection(){
        
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/PendataanMahasiswa", "root", "");
            
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        return con;
        
    }
}
