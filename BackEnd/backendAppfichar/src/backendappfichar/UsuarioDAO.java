/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backendappfichar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedromiras
 */
public class UsuarioDAO {
    public boolean registrar(String nombre, String email, String password) {
    String sql = "INSERT INTO usuarios (nombre, email, password_hash) VALUES (?, ?, ?)";

    String passwordHash = Utils.sha256(password);

    try (Connection con = ConexionBD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, passwordHash);

            int filasInsertadas = ps.executeUpdate();

            return filasInsertadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }
    public Usuario login(String email, String pswdHash) {
        String sql = "select id, nombre, email from usuarios where email = ? and password_hash = ?";
    
        try (Connection con = ConexionBD.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)) {
        
            ps.setString(1, email);
            ps.setString(2, pswdHash);
        
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String correo = rs.getString("email");
                
                    return new Usuario(id, nombre, correo);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error en login: " + ex.getMessage());
        }
        return null;
    }
    
}
