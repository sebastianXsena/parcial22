/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.*;

/**
 *
 * @author Ing. Narvaez Mejia
 */
public class Clientes {
   
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    
public ResultSet listarCliente() {
    Conector db = new Conector();
    ResultSet rs = null;

    try {
        db.conectar();
        String query = "SELECT * FROM clientes";
        rs = db.executeSelect(query);
    } catch (SQLException e) {
        System.err.println("Error al listar los clientes Metodo: " + e.getMessage());
    }

    return rs;
}
   
    // Método para insertar un nuevo cliente en la base de datos
    public int guardarCliente(String nombre, String apellido, String direccion, String telefono) throws SQLException {
        Conector db = new Conector();
        db.conectar();
        String query = "INSERT INTO clientes (nombre, apellido, direccion, telefono) VALUES (?, ?, ?, ?)";
        return db.executeUpdate(query, nombre, apellido, direccion, telefono);
    }

    // Método para actualizar un cliente existente en la base de datos
    public int actualizarCliente(int id, String nombre, String apellido, String direccion, String telefono) throws SQLException {
        Conector db = new Conector();
        db.conectar();
        String query = "UPDATE clientes SET nombre = ?, apellido = ?, direccion = ?, telefono = ? WHERE id = ?";
        return db.executeUpdate(query, nombre, apellido, direccion, telefono, id);
    }

    // Método para eliminar un cliente de la base de datos
    public int eliminarCliente(int id) throws SQLException {
        Conector db = new Conector();
        db.conectar();
        String query = "DELETE FROM clientes WHERE id = ?";
        return db.executeUpdate(query, id);
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

   public boolean guardarUsuario(String nombre, String apellido, String sexo, 
                           String cargo, String username, String email, String clave) 
                           throws SQLException {
    Connection con = null;
    PreparedStatement ps = null;
    
    try {
        // 1. Obtener conexión a la base de datos
        con = Conexion.getConnection(); // Asume que tienes una clase Conexión
        
        // 2. Crear sentencia SQL preparada
        String sql = "INSERT INTO usuarios (nombre, apellido, sexo, cargo, username, email, clave) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
        ps = con.prepareStatement(sql);
        
        // 3. Establecer parámetros
        ps.setString(1, nombre);
        ps.setString(2, apellido);
        ps.setString(3, sexo);
        ps.setString(4, cargo);
        ps.setString(5, username);
        ps.setString(6, email);
        ps.setString(7, clave); // Deberías encriptar la contraseña
        
        // 4. Ejecutar inserción
        int resultado = ps.executeUpdate();
        
        // 5. Retornar true si se insertó correctamente
        return resultado > 0;
        
    } finally {
        // 6. Cerrar recursos
        if (ps != null) ps.close();
        if (con != null) con.close();
    }
}

    private static class Conexion {

        private static Connection getConnection() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public Conexion() {
            
            
        }
        public boolean guardarUsuario(String nombre, String apellido, String sexo, 
                           String cargo, String username, String email, String clave) {
    // Implementación simple de guardado
    try {
        Connection con = Conexion.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO usuarios VALUES (?,?,?,?,?,?,?)");
        
        ps.setString(1, nombre);
        ps.setString(2, apellido);
        // ... setear otros campos
        
        return ps.executeUpdate() > 0;
    } catch (Exception ex) {
        return false;
    }
}

public ResultSet listarUsuarios() {
    try {
        Connection con = Conexion.getConnection();
        return con.createStatement().executeQuery("SELECT * FROM usuarios");
    } catch (Exception ex) {
        return null;
    }
}
    }
    }

    

