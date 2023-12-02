package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {
    // Variables con la información de conexión a la base de datos
    private static final String JDBC_URL = 
   "jdbc:mysql://localhost:3306/control_estudiantes?useSSL=false&useTimezone=true&serverTimeZone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "admin";
    
    // Creación del objeto BasicDataSource de Apache Commons DBCP
    private static BasicDataSource dataSource;

    static {
        try {
            // Carga del controlador de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // Manejo de excepción en caso de error en la carga del controlador
            throw new RuntimeException("Error al cargar el controlador de MySQL", e);
        }
    }

    // Método que retorna el objeto DataSource para obtener la conexión
    public static DataSource getDataSource() {
        // Verificación si el objeto BasicDataSource es nulo
        if (dataSource == null) {
            dataSource = new BasicDataSource();
            // Configuración de los parámetros de conexión a la base de datos
            dataSource.setUrl(JDBC_URL);
            dataSource.setUsername(JDBC_USER);
            dataSource.setPassword(JDBC_PASSWORD);
            dataSource.setInitialSize(50); // Establece el tamaño inicial de la conexión
        }
        return dataSource;
    }

    // Método que retorna la conexión con la base de datos
    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    // Métodos para cerrar objetos ResultSet, PreparedStatement y Connection
    public static void Close(ResultSet rs) {
        try {
            rs.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public static void Close(PreparedStatement stmt) {
        try {
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public static void Close(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
