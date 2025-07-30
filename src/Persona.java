public class Persona {
    private final int id;
    private String nombre;
    private String telefono;
    private String email;
    private static int numeroPersonas = 0;

    // Constructor vacío
    public Persona() {
        this.id = ++Persona.numeroPersonas;
    }

    // Constructor con argumentos
    public Persona(String nombre, String telefono, String email) {
        this(); // Llama al constructor vacío
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    // Getters
    public int getId() {
        return this.id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    // toString
    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre +
                ", Teléfono: " + telefono + ", Email: " + email;
    }
}
