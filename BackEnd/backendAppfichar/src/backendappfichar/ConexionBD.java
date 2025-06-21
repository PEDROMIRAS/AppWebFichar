/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backendappfichar;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author pedromiras
 */
public class ConexionBD {
    private static final String URL= "jdbc:mysql://localhost:3306/fichador_app?useUnicode=true&characterEncoding=UTF-8";
    private static final String USR = "root";
    private static final String PSWD = "";
    
    public static Connection getConexion(){
        try{
           return DriverManager.getConnection(URL,USR,PSWD);
        }catch(SQLException ex){
            System.out.println("Error al conectar: " + ex.getMessage());
            return null;
        }
    }
}
