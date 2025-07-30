import java.util.ArrayList;
import java.util.Scanner;

public class MenuPersonas {
    private final Scanner consola;
    private final ArrayList<Persona> personas;

    public MenuPersonas() {
        consola = new Scanner(System.in);
        personas = new ArrayList<>();
    }

    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            try {
                salir = ejecutarOperacion();
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private void mostrarMenu() {
        System.out.print("""
                        *** Listado Personas ***
                        1. Agregar
                        2. Listar
                        3. Salir
                        Proporciona la opción:\s""");
    }

    private boolean ejecutarOperacion() {
        int opcion = Integer.parseInt(consola.nextLine());

        switch (opcion) {
            case 1 -> agregarPersona();
            case 2 -> listarPersonas();
            case 3 -> {
                System.out.println("Saliendo del programa...");
                return true;
            }
            default -> System.out.println("Opción no válida. Intenta de nuevo.");
        }
        return false;
    }

    private void agregarPersona() {
        System.out.print("Proporciona el nombre: ");
        String nombre = consola.nextLine();
        System.out.print("Proporciona el teléfono: ");
        String telefono = consola.nextLine();
        System.out.print("Proporciona el email: ");
        String email = consola.nextLine();

        Persona persona = new Persona(nombre, telefono, email);
        personas.add(persona);
        System.out.println("Persona agregada: " + persona);
    }

    private void listarPersonas() {
        System.out.println("\n*** Listado de Personas ***");
        if (personas.isEmpty()) {
            System.out.println("No hay personas registradas.");
        } else {
            /*
             Podemos usar cualquiera de estas tres formas:
             usando labda
             personas.forEach((persona) -> System.out.println(persona));
             usando referencia
             personas.forEach(System.out::println);
             */
            for (Persona p : personas) {
                System.out.println(p);
            }
        }
    }
}
