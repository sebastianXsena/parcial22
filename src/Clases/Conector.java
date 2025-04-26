package Clases;

import java.sql.*;

public class Conector {
    // Detalles de conexión
    private static final String URL = "jdbc:mysql://localhost/TecnarApp";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    private Connection connection;

    // Método para establecer la conexión
    public void conectar() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            try {
                this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión exitosa a la base de datos.");
            } catch (SQLException ex) {
                System.err.println("Error al conectar a la base de datos: " + ex.getMessage());
                throw ex; // Relanzar la excepción para que el manejador superior la gestione
            }
        }
    }

    // Método para cerrar la conexión
    public void desconectar() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
                System.out.println("Desconexión exitosa de la base de datos.");
            }
        } catch (SQLException ex) {
            System.err.println("Error al desconectar de la base de datos: " + ex.getMessage());
        }
    }

    // Método para ejecutar un SELECT con parámetros
    public ResultSet executeSelect(String query, Object... parameters) throws SQLException {
        PreparedStatement statement = prepareStatement(query, parameters);
        return statement.executeQuery();
    }

    // Método para ejecutar un SELECT sin parámetros
    public ResultSet executeSelect(String query) throws SQLException {
        PreparedStatement statement = prepareStatement(query);
        return statement.executeQuery(); 
    }

    // Método para ejecutar INSERT, UPDATE o DELETE
    public int executeUpdate(String query, Object... parameters) throws SQLException {
        try (PreparedStatement statement = prepareStatement(query, parameters)) {
            return statement.executeUpdate();
        }
    }

    // Método privado para preparar consultas con parámetros
    private PreparedStatement prepareStatement(String query, Object... parameters) throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            conectar(); // Asegurarse de que la conexión esté abierta
        }
        PreparedStatement statement = this.connection.prepareStatement(query);
        for (int i = 0; i < parameters.length; i++) {
            statement.setObject(i + 1, parameters[i]);
        }
        return statement;
    }

    // Método para obtener la conexión (por ejemplo, para operaciones de lectura)
    public Connection getConnection() {
        return this.connection;
    }
}
