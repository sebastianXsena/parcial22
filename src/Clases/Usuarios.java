/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Clases;
import Clases.Conector;
import java.sql.*;

/**
 *
 * @author Ing. Narvaez Mejia
 */
public class Usuarios {
    String nombre;
    String apellido;
    String username;
    String Email;
    String Cargo;
    String Sexo;
    String Clave;

    // Método para verificar las credenciales del usuario
    public boolean verificarCredenciales(String pUsername, String pPassword) {
        Conector db = new Conector();
        
        try {
            db.conectar();
            String query = "SELECT * FROM usuarios WHERE username = ? AND clave = ?";
            ResultSet rs = db.executeSelect(query, pUsername, pPassword);
            return rs.next();
            
        } catch (SQLException e) {
            System.err.println("Error al verificar las credenciales: " + e.getMessage());
            return false;
        } finally {
           db.desconectar();
        }
    }
    
    public ResultSet listarUsuarios() {
        Conector db = new Conector();
        ResultSet rs = null;

        try {
            db.conectar();
            String query = "SELECT * FROM usuarios";
            rs = db.executeSelect(query);
        } catch (SQLException e) {
            System.err.println("Error al listar los usuarios: " + e.getMessage());
        }

        return rs;
    }
   
    // Método para insertar un nuevo usuario en la base de datos
    public int guardarUsuario(String Nombre, String Apellido, String Username, String Email, 
                             String Cargo, String Sexo, String Clave) throws SQLException {
        Conector db = new Conector();
        db.conectar();
        String query = "INSERT INTO usuarios (Nombre, Apellido, Username, Email, Cargo, Sexo, clave) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        return db.executeUpdate(query, Nombre, Apellido, Username, Email, Cargo, Sexo, Clave);
    }

    // Método para actualizar un usuario existente en la base de datos
    public int actualizarUsuario(int id, String Nombre, String Apellido, String Username, String Email, 
                               String Cargo, String Sexo, String Clave) throws SQLException {
        Conector db = new Conector();
        db.conectar();
        String query = "UPDATE usuarios SET Nombre = ?, Apellido = ?, Username = ?, Email = ?, "
                     + "Cargo = ?, Sexo = ?, clave = ? WHERE id = ?";
        return db.executeUpdate(query, Nombre, Apellido, Username, Email, Cargo, Sexo, Clave, id);
    }

    // Método para eliminar un usuario de la base de datos
    public int eliminarUsuario(int id) throws SQLException {
        Conector db = new Conector();
        db.conectar();
        String query = "DELETE FROM usuarios WHERE id = ?";
        return db.executeUpdate(query, id);
    }
}

