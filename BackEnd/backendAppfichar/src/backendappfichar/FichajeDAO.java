/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backendappfichar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author pedromiras
 */
public class FichajeDAO {
    //Metodo para fichar(entrada o salida)
    
    public boolean registrarFichaje(int usuarioID, String tipo){
        String sql = "insert into fichajes (usuario_id, tipo) values(?,?)";
        
        if (!tipo.equals("entrada") && !tipo.equals("salida")) {
            System.out.println("Tipo de fichaje inválido.");
            return false;
        }
        
        try(Connection con = ConexionBD.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, usuarioID);
            ps.setString(2, tipo);
            
            int filasInsertadas = ps.executeUpdate();
            return filasInsertadas > 0;
            
        }catch(SQLException ex){
            System.out.println("Error al registrar el fichaje: " + ex.getMessage());
            return false;
        }
    }
}
