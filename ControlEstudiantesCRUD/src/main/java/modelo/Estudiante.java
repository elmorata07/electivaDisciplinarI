package modelo;

public class Estudiante {

    // Atributos
    private int idEstudiante; // Identificador único del estudiante
    private String nombre; // Nombre del estudiante
    private String apellido; // Apellido del estudiante
    private String email; // Correo electrónico del estudiante
    private String telefono; // Número de teléfono del estudiante
    private double edad; // Edad del estudiante

    // Constructor con un parámetro para el id del estudiante
    public Estudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    // Constructor sin parámetros
    public Estudiante() {
    }

    // Constructor con todos los parámetros
    public Estudiante(int idEstudiante, String nombre, String apellido, String email,
            String telefono, double edad) {
        this.idEstudiante = idEstudiante;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.edad = edad;
    }

    // Constructor sin el id del estudiante
    public Estudiante(String nombre, String apellido, String email, String telefono, double edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.edad = edad;
    }

    // Métodos getters y setters para todos los atributos
    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getEdad() {
        return edad;
    }

    public void setEdad(double edad) {
        this.edad = edad;
    }

}
