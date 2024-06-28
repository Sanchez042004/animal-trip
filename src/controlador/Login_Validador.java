package controlador;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.SQLException;
import modelo.Conexion;

public class Login_Validador {
    private Conexion conectorBD;

    public Login_Validador(Conexion conector) {
        this.conectorBD = conector;
    }

    public boolean validateCredentials(String usuario, String contrasena) throws SQLException {
        // Crea el objeto SQLServerDataSource
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setServerName("ANDRES\\MSSQLSERVER");
        dataSource.setDatabaseName("AnimalTripOficial");
        dataSource.setUser(usuario);
        dataSource.setPassword(contrasena);

        try {
            // Establece la conexión
            Connection connection = dataSource.getConnection();
            connection.close();

            return true; // Las credenciales son válidas
        } catch (SQLServerException e) {
            e.printStackTrace();
            return false; // Las credenciales son inválidas
        }
    }
}

