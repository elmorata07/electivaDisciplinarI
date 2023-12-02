package modelo;

// Importar clases necesarias para conectarse a la base de datos
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// Clase que se encarga de hacer operaciones CRUD en la tabla estudiante de la base de datos
public class EstudianteDaoJDBC {

    // Consultas SQL para hacer las operaciones CRUD
    private static final String SQL_SELECT
            = "SELECT id_estudiante, nombre, apellido, email, telefono, edad FROM estudiante";
    private static final String SQL_SELECT_BY_ID
            = "SELECT id_estudiante, nombre, apellido, email, telefono, edad FROM estudiante WHERE id_estudiante=?";
    private static final String SQL_INSERT
            = "INSERT INTO estudiante(nombre, apellido, email, telefono, edad) VALUES(?,?,?,?,?)";
    private static final String SQL_UPDATE
            = "UPDATE estudiante SET nombre=?, apellido=?, email=?, telefono=?, edad=? WHERE id_estudiante=?";
    private static final String SQL_DELETE
            = "DELETE FROM estudiante WHERE id_estudiante=?";


    public List<Estudiante> listar() {
        List<Estudiante> estudiantes = new ArrayList<>();
        // Conexión a la base de datos y consulta SQL
        try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_SELECT); ResultSet rs = stmt.executeQuery()) {
            // Recorrido de resultados y creación de objetos Estudiante
            while (rs.next()) {
                int idEstudiante = rs.getInt("id_estudiante");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                double edad = rs.getDouble("edad");

                Estudiante estudiante = new Estudiante(idEstudiante, nombre, apellido, email, telefono, edad);
                estudiantes.add(estudiante);
            }
        } catch (Exception e) {
            System.out.println("Error al listar los estudiantes: " + e.getMessage());
        }
        return estudiantes;
    }

    // Método para buscar un estudiante por su id
    public Estudiante buscar(Estudiante estudiante) {
        // Conexión a la base de datos y consulta SQL
        try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_ID)) {
            stmt.setInt(1, estudiante.getIdEstudiante());
            // Recorrido de resultados y actualización del objeto Estudiante pasado como parámetro
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    String email = rs.getString("email");
                    String telefono = rs.getString("telefono");
                    double edad = rs.getDouble("edad");
                    estudiante.setNombre(nombre);
                    estudiante.setApellido(apellido);
                    estudiante.setEmail(email);
                    estudiante.setTelefono(telefono);
                    estudiante.setEdad(edad);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar el estudiante: " + e.getMessage());
        }
        return estudiante;
    }

    // Método para insertar un nuevo estudiante en la base de datos
    public int insertar(Estudiante estudiante) {
        int rows = 0;
        // Conexión a la base de datos y consulta SQL
        try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)) {
            // Se establecen los parámetros de la consulta preparada
            stmt.setString(1, estudiante.getNombre());
            stmt.setString(2, estudiante.getApellido());
            stmt.setString(3, estudiante.getEmail());
            stmt.setString(4, estudiante.getTelefono());
            stmt.setDouble(5, estudiante.getEdad());
            // Se ejecuta la consulta y se obtiene el número de filas afectadas
            rows = stmt.executeUpdate();
        } catch (Exception e) {
            // Se maneja cualquier excepción que se produzca durante la ejecución de la consulta
            System.out.println("Error al insertar el estudiante: " + e.getMessage());
        }

        return rows;
    }
    
    // Método para actualizar un estudiante existente en la base de datos
    public int actualizar(Estudiante estudiante) {
        int rows = 0;
        try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE)) {
            // Se establecen los parámetros de la consulta preparada
            stmt.setString(1, estudiante.getNombre());
            stmt.setString(2, estudiante.getApellido());
            stmt.setString(3, estudiante.getEmail());
            stmt.setString(4, estudiante.getTelefono());
            stmt.setDouble(5, estudiante.getEdad());
            stmt.setInt(6, estudiante.getIdEstudiante());
            // Se ejecuta la consulta y se obtiene el número de filas afectadas
            rows = stmt.executeUpdate();
        } catch (Exception e) {
            // Se maneja cualquier excepción que se produzca durante la ejecución de la consulta
            System.out.println("Error al actualizar el estudiante: " + e.getMessage());
        }
        return rows;
    }
    
    // Método para eliminar un estudiante existente en la base de datos
    public int eliminar(Estudiante estudiante) {
        int rows = 0;
        try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_DELETE)) {
            // Se establecen los parámetros de la consulta preparada
            stmt.setInt(1, estudiante.getIdEstudiante());
            // Se ejecuta la consulta y se obtiene el número de filas afectadas
            rows = stmt.executeUpdate();
        } catch (Exception e) {
            // Se maneja cualquier excepción que se produzca durante la ejecución de la consulta
            System.out.println("Error al eliminar el estudiante: " + e.getMessage());
        }
        return rows;
    }
}
