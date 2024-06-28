package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

    private Connection conexion;

    public Conexion() {
        String conexionUrl = "jdbc:sqlserver://localhost:1433;"
                + "database=AnimalTripOficial;"
                + "user=sa;"
                + "password=hola123;"
                + "loginTimeout=30;";

        try {
            conexion = DriverManager.getConnection(conexionUrl);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public Connection getConexion() {
        return conexion;
    }


    public String obtenerNombreCliente(String documento) {
        String nombre_cliente = "";

        try {
            // Obtener la conexión a la base de datos
            Connection connection = getConexion();

            // Crear el objeto Statement para ejecutar la consulta
            Statement statement = connection.createStatement();

            // Ejecutar la consulta para obtener el nombre del cliente
            String query = "SELECT nombre_cliente FROM dbo.Cliente WHERE documento_cliente = '" + documento + "'";
            ResultSet resultSet = statement.executeQuery(query);

            // Verificar si hay un resultado y obtener el nombre del cliente
            if (resultSet.next()) {
                nombre_cliente = resultSet.getString("nombre_cliente");
            }

            // Cerrar los recursos
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombre_cliente;
    }
    
    public String obtenerDocumentoCliente(String contraseña) {
        String documento_cliente = "";

        try {
            // Obtener la conexión a la base de datos
            Connection connection = getConexion();

            // Crear el objeto Statement para ejecutar la consulta
            Statement statement = connection.createStatement();

            // Ejecutar la consulta para obtener el documento del cliente
            String query = "SELECT documento_cliente FROM dbo.Cliente WHERE contraseña_cliente = '" + contraseña + "'";
            ResultSet resultSet = statement.executeQuery(query);

            // Verificar si hay un resultado y obtener el nombre del cliente
            if (resultSet.next()) {
                documento_cliente = resultSet.getString("documento_cliente");
            }

            // Cerrar los recursos
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return documento_cliente;
    }
}
