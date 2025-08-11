import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class MenuPersonas {
    private  final  Scanner consola;
    private  ArrayList<Persona> personas;


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
                        3. Editar
                        4. Eliminar Persona
                        5. Buscar Personas
                        6. Ordenar Alfabéticamente
                        7. Guardar Archivo
                        8. Cargar Archivo
                        9. Eliminar Archivo
                        10. Salir
                        Proporciona la opción:\s""");
    }

    private boolean ejecutarOperacion() {
        int opcion = Integer.parseInt(consola.nextLine());

        switch (opcion) {
            case 1 -> agregarPersona();
            case 2 -> listarPersonas();
            case 3 -> editarPersonas();
            case 4 -> eliminarPersona();
            case 5 -> buscadorNombres();
            case 6 -> ordenarAlfabeticamente();
            case 7 -> {
                System.out.println("Ingrese el nombre del archivo que desea guardar");
                String nombreArchivo = consola.nextLine().trim();
                GestorArchivos.guardarArchivo(personas,nombreArchivo);
            }
            case 8 -> {
                System.out.println("Ingrese el nombre del archivo que desea abrir");
                String nombreArchivoAbrir = consola.nextLine().trim();
                personas = GestorArchivos.cargarPersonas(nombreArchivoAbrir);
            }
            case 9 -> {
                System.out.println("Ingrese el nombre del archivo que desea eliminar");
                String nombreArchivoE = consola.nextLine().trim();
                GestorArchivos.eliminarArchivo(nombreArchivoE);
            }
            case 10 -> {
                System.out.println("Estas seguro de que deseas salir ? (s/n)");
                String respuesta = consola.nextLine().trim();
                if(respuesta.equalsIgnoreCase("s")) {
                    System.out.println("Saliendo del programa...");
                    return true;
                }else if(respuesta.equalsIgnoreCase("n")){
                    System.out.println("Operacion cancelada");
                }else{
                    System.out.println("comando inválido");
                }
            }
            default -> System.out.println("Opción no válida. Intenta de nuevo.");
        }
        return false;
    }

    private void agregarPersona() {
        System.out.print("Proporciona el nombre: ");
        String nombre = consola.nextLine().trim();
        System.out.print("Proporciona el teléfono: ");
        String telefono = consola.nextLine().trim();
        System.out.print("Proporciona el email: ");
        String email = consola.nextLine().trim();

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
            for (int i = 0; i < personas.size(); i++) {
                Persona p = personas.get(i);
                String resultado = p.toString();
                System.out.println(i + "_" + resultado);
            }
        }
    }

    private void editarPersonas() {
        if (personas.isEmpty()) {
            System.out.println("No hay personas para editar.");
            return;
        }

        listarPersonas();
        System.out.println("Ingrese el número de la persona que desea editar (o -1 para cancelar):");
        System.out.println("Debe ingresar un numero del 0 al "+ personas.size());


        int indice;
        try {
            indice = Integer.parseInt(consola.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar un número válido.");
            return;
        }

        if (indice == -1) {
            System.out.println("Edición cancelada.");
            return;
        }

        if (indice >= 0 && indice < personas.size()) {
            Persona persona = personas.get(indice);

            System.out.println("Seleccionado: " + persona);

            // Editar nombre
            System.out.print("Nuevo nombre (dejar vacío para no cambiar): ");
            String nuevoNombre = consola.nextLine();
            if (!nuevoNombre.isEmpty()) {
                System.out.print("¿Seguro que desea cambiar el nombre de '" + persona.getNombre() + "' a '" + nuevoNombre + "'? (s/n): ");
                String confirmacion = consola.nextLine().trim();
                if (confirmacion.equalsIgnoreCase("s")) {
                    persona.setNombre(nuevoNombre);
                    System.out.println("Nombre editado correctamente.");
                } else if (confirmacion.equalsIgnoreCase("n")) {
                    System.out.println("Cambio de nombre cancelado.");
                } else {
                    System.out.println("Opción inválida. No se modificó el nombre.");
                }
            }

            // Editar teléfono
            System.out.print("Nuevo teléfono (dejar vacío para no cambiar): ");
            String nuevoTelefono = consola.nextLine();
            if (!nuevoTelefono.isEmpty()) {
                System.out.print("¿Seguro que desea cambiar el teléfono de '" + persona.getTelefono() + "' a '" + nuevoTelefono + "'? (s/n): ");
                String confirmacion = consola.nextLine().trim();
                if (confirmacion.equalsIgnoreCase("s")) {
                    persona.setTelefono(nuevoTelefono);
                    System.out.println("Teléfono editado correctamente.");
                } else if (confirmacion.equalsIgnoreCase("n")) {
                    System.out.println("Cambio de teléfono cancelado.");
                } else {
                    System.out.println("Opción inválida. No se modificó el teléfono.");
                }
            }

            // Editar email
            System.out.print("Nuevo email (dejar vacío para no cambiar): ");
            String nuevoEmail = consola.nextLine();
            if (!nuevoEmail.isEmpty()) {
                System.out.print("¿Seguro que desea cambiar el email de '" + persona.getEmail() + "' a '" + nuevoEmail + "'? (s/n): ");
                String confirmacion = consola.nextLine().trim();
                if (confirmacion.equalsIgnoreCase("s")) {
                    persona.setEmail(nuevoEmail);
                    System.out.println("Email editado correctamente.");
                } else if (confirmacion.equalsIgnoreCase("n")) {
                    System.out.println("Cambio de email cancelado.");
                } else {
                    System.out.println("Opción inválida. No se modificó el email.");
                }
            }

        } else {
            System.out.println("Índice inválido.");
        }
    }

    private void eliminarPersona() {
        if (personas.isEmpty()) {
            System.out.println("No hay personas para eliminar.");
            return;
        }

        listarPersonas();
        System.out.println("Ingrese el número de la persona que desea eliminar (o -1 para cancelar):");


        int indice;
        try {
            indice = Integer.parseInt(consola.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar un número válido.");
            return;
        }

        if (indice == -1) {
            System.out.println("Eliminación cancelada.");
            return;
        }

        if (indice >= 0 && indice < personas.size()) {
            Persona persona = personas.get(indice);
            System.out.print("¿Está seguro que desea eliminar a " + persona.getNombre() + "? (s/n): ");
            String confirmacion = consola.nextLine().trim();
            if (confirmacion.equalsIgnoreCase("s")) {
                personas.remove(indice);
                System.out.println("Persona eliminada correctamente.");
            } else if (confirmacion.equalsIgnoreCase("n")) {
                System.out.println("Eliminación cancelada.");
            } else {
                System.out.println("Opción inválida. No se realizó ninguna acción.");
            }
        } else {
            System.out.println("Índice inválido.");
        }
    }


    public void buscadorNombres(){
        if(personas.isEmpty()){
            System.out.println("No hay personas para buscar");
            return;
        }
        System.out.print("Ingresa el nombre de la persona:");
        String nombreB = consola.nextLine().trim().toLowerCase();

        boolean encontrado = false;

        for(int i = 0; i < personas.size();i++){
            Persona persons = personas.get(i);
            String nombreBuscado = persons.getNombre().toLowerCase();

            if(nombreBuscado.contains(nombreB)){
                System.out.println("["+ i +"]" + persons);
                encontrado = true;
            }

        }

        if(!encontrado){
            System.out.println("No se encontraron coincidencias");
        }
    }

    public void ordenarAlfabeticamente() {
        if (personas.isEmpty()) {
            System.out.println("No hay personas cargadas para ordenar.");
            return;
        }

        System.out.println("¿Desea ordenar la lista alfabéticamente? (s/n)");
        String respuesta = consola.nextLine().trim().toLowerCase();

        if (respuesta.equals("s")) {
            // Copiamos la lista original
            ArrayList<Persona> nombreOrdenado = new ArrayList<>(personas);

            // Ordenamos por nombre (ignorando mayúsculas/minúsculas)
            nombreOrdenado.sort(Comparator.comparing(p -> p.getNombre().toLowerCase()));

            System.out.println("Listado de personas ordenadas alfabéticamente:");
            for (int i = 0; i < nombreOrdenado.size(); i++) {
                Persona p = nombreOrdenado.get(i);
                System.out.println(p.toString());
            }
        } else if(respuesta.equals("n")) {
            System.out.println("Operación cancelada.");
        } else{
            System.out.println("Opción inválida");
        }
    }


}
