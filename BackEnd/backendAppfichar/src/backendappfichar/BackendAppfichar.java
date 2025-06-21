/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package backendappfichar;
import java.util.Scanner;
/**
 *
 * @author pedromiras
 */
public class BackendAppfichar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        FichajeDAO fichajeDAO = new FichajeDAO();
        try (Scanner scanner = new Scanner(System.in)) {
            OUTER:
            while (true) {
                System.out.println("\n=== APP FICHADOR ===");
                System.out.println("1. Registrar usuario");
                System.out.println("2. Iniciar sesión");
                System.out.println("3. Salir");
                System.out.print("Elige una opción: ");
                String opcion = scanner.nextLine();
                switch (opcion) {
                    case "1" ->                     {
                        // Registro
                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Contraseña: ");
                        String password = scanner.nextLine();
                        boolean registrado = usuarioDAO.registrar(nombre, email, password);
                        if (registrado) {
                            System.out.println("Usuario registrado correctamente.");
                        } else {
                            System.out.println("Error al registrar usuario.");
                        }                          }
                    case "2" ->                     {
                        // Login
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Contraseña: ");
                        String password = scanner.nextLine();
                        String hash = Utils.sha256(password);
                        Usuario usuario = usuarioDAO.login(email, hash);
                        if (usuario != null) {
                            System.out.println("Bienvenido " + usuario.getNombre());
                            
                            // Menú de fichaje
                            boolean fichando = true;
                            while (fichando) {
                                System.out.println("\n--- FICHAR ---");
                                System.out.println("1. Fichar entrada");
                                System.out.println("2. Fichar salida");
                                System.out.println("3. Cerrar sesión");
                                System.out.print("Elige una opción: ");
                                
                                String fichajeOpcion = scanner.nextLine();
                                
                                switch (fichajeOpcion) {
                                    case "1" -> {
                                        if (fichajeDAO.registrarFichaje(usuario.getId(), "entrada")) {
                                            System.out.println("Entrada registrada correctamente.");
                                        } else {
                                            System.out.println("Error al fichar entrada.");
                                        }
                                    }
                                    case "2" -> {
                                        if (fichajeDAO.registrarFichaje(usuario.getId(), "salida")) {
                                            System.out.println("Salida registrada correctamente.");
                                        } else {
                                            System.out.println("Error al fichar salida.");
                                        }
                                    }
                                    case "3" -> fichando = false;
                                    default -> System.out.println("Opción no válida.");
                                }
                            }
                            
                        } else {
                            System.out.println("Email o contraseña incorrectos.");
                        }                          }
                    case "3" -> {
                        System.out.println("Saliendo de la app...");
                        break OUTER;
                    }
                    default -> System.out.println("Opción no válida.");
                }
            }
        }
    }
    
}
