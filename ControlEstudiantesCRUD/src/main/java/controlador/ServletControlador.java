package controlador;

// Importación de clases necesarias
//import modelo.EstudianteDaoJDBC;
//import modelo.Estudiante;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import modelo.Estudiante;
import modelo.EstudianteDaoJDBC;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

// Declaración del servlet y la URL asociada
@WebServlet(name = "ServletControlador", urlPatterns = {"/ServletControlador"})
public class ServletControlador extends HttpServlet {

    // Método para la edición de un estudiante
    protected void editarEstudiante(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idEstudiante = Integer.parseInt(request.getParameter("idEstudiante"));
        Estudiante estudiante = new EstudianteDaoJDBC().buscar(new Estudiante(idEstudiante));
        request.setAttribute("estudiante", estudiante);
        String jspEditar = "/WEB-INF/vista/estudiante/editarEstudiante.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    // Método para la acción por defecto
    protected void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Se lista la información de todos los estudiantes
        List<Estudiante> estudiantes = new EstudianteDaoJDBC().listar();
        System.out.println("estudiantes = " + estudiantes);
        // Se crea una sesión y se establecen los atributos "estudiantes" y "totalEstudiantes"
        HttpSession sesion = request.getSession();
        sesion.setAttribute("estudiantes", estudiantes);
        sesion.setAttribute("totalEstudiantes", estudiantes.size());

        // Se redirige al archivo "estudiantes.jsp"
        response.sendRedirect("estudiantes.jsp");
    }

    protected void modificarEstudiante(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idEstudiante = Integer.parseInt(request.getParameter("idEstudiante"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        double edad = 0;

        String edadString = request.getParameter("edad");
        if (edadString != null && !"".equals(edadString)) {
            edad = Double.parseDouble(edadString);
        }
        Estudiante estudiante = new Estudiante(idEstudiante, nombre, apellido, email, telefono, edad);
        int registrosModificados = new EstudianteDaoJDBC().actualizar(estudiante);
        this.accionDefault(request, response);
    }
    
    protected void eliminarEstudiante(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idEstudiante = Integer.parseInt(request.getParameter("idEstudiante"));
        Estudiante estudiante = new Estudiante(idEstudiante);
        int registrosModificados = new EstudianteDaoJDBC().eliminar(estudiante);
        this.accionDefault(request, response);
    }
    
    protected void insertarEstudiante(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        double edad = 0;

        String edadString = request.getParameter("edad");
        if (edadString != null && !"".equals(edadString)) {
            edad = Double.parseDouble(edadString);
        }
        Estudiante estudiante = new Estudiante(nombre, apellido, email, telefono, edad);
        int registrosModificados = new EstudianteDaoJDBC().insertar(estudiante);
        this.accionDefault(request, response);
    }
    
     // Método para el manejo de solicitudes GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            // Selección de la acción a realizar según el parámetro "accion" enviado
            switch (accion) {
                // En caso de que la acción sea "editar", se llama al método correspondiente
                case "editar":
                    this.editarEstudiante(request, response);
                    break;
                // En caso de que la acción sea "eliminar", se llama al método correspondiente
                case "eliminar":
                    this.eliminarEstudiante(request, response);
                    break;
                // Si no se encuentra una acción válida, se llama al método por defecto
                default:
                    this.accionDefault(request, response);
            }

        } else {
            // Si no se especifica una acción, se llama al método por defecto
            this.accionDefault(request, response);
        }
    }
    
    // Método para el manejo de solicitudes POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            // Selección de la acción a realizar según el parámetro "accion" enviado
            switch (accion) {
                // En caso de que la acción sea "insertar", se llama al método correspondiente
                case "insertar":
                    this.insertarEstudiante(request, response);
                    break;
                // En caso de que la acción sea "modificar", se llama al método correspondiente
                case "modificar":
                    this.modificarEstudiante(request, response);
                    break;
                // En caso de que la acción sea "eliminar", se llama al método correspondiente
                case "eliminar":
                    this.eliminarEstudiante(request, response);
                    break;
                // Si no se encuentra una acción válida, se llama al método por defecto
                default:
                    this.accionDefault(request, response);
            }

        } else {
            // Si no se especifica una acción, se llama al método por defecto
            this.accionDefault(request, response);
        }
    }

}
