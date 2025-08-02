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
                        3. Editar
                        4. Eliminar Persona
                        5. Buscar Personas
                        6. Salir
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
            case 6 -> {
                System.out.println("Estas seguro de que deseas salir ? (s/n)");
                String respuesta = consola.nextLine();
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
            for (Persona persona : personas) {
                System.out.println(persona);
            }
        }
    }

    private void editarPersonas(){
        listarPersonas();
        if(personas.isEmpty()){
            return;//No hay personas para editar
        }

        System.out.println("Ingrese el numero de la persona que desea editar ");
        System.out.println("Ingrese un número del 0 al "+ (personas.size()-1));
        int indice = Integer.parseInt(consola.nextLine());


        if (indice>= 0 && indice < personas.size()){
            Persona persona = personas.get(indice);

            System.out.println("Persona seleccionada: " + persona);
            System.out.println("Ingrese el nuevo nombre (deje vacio para cancelar): ");
            String nuevoNombre = consola.nextLine();
            
            if(!nuevoNombre.isEmpty()) {
                persona.setNombre(nuevoNombre);
                System.out.println("Nombre editado correctamente");
                String nNombre = persona.getNombre();
                System.out.println(nNombre);
            }else{
                System.out.println("No se realizo ningun cambio");
            }

            System.out.println("Ingrese el nuevo telefono (deje vacio para cancelar): ");
            String nuevoTelefono = consola.nextLine();

            if(!nuevoTelefono.isEmpty()){
                persona.setTelefono(nuevoTelefono);
                System.out.println("Telefono editado correctamente");
                String nTelefono = persona.getTelefono();
                System.out.println(nTelefono);
            }else{
                System.out.println("No se realizo ningun cambio");
            }

            System.out.println("Ingese el nuevo email");
            String nuevoEmail = consola.nextLine();

            if(!nuevoEmail.isEmpty()){
                persona.setEmail(nuevoEmail);
                System.out.println("Email editado correctamente");
                String nEmail = persona.getEmail();
                System.out.println(nEmail);
            }else{
                System.out.println("No se realizo ningun cambio");
            }
        }else{
            System.out.println("Indice inválido");
        }


    }

    private void eliminarPersona(){
        listarPersonas();
        if(personas.isEmpty()){
            System.out.println("No hay personas para eliminar");
            return;
        }

        System.out.println("Ingrese el indice de la persona que desea eliminar: ");
        System.out.println("El indice debe ser un número del 0 al " + (personas.size()-1));
        int indiceE = Integer.parseInt(consola.nextLine());

        if(indiceE >= 0 && indiceE < personas.size()){
            Persona person = personas.get(indiceE);
            System.out.println("Estas seguro que desea elimiar a: "+ person.getNombre()+ "? (s/n)");
            String respuesta = consola.nextLine();

            if(respuesta.equalsIgnoreCase("s")){
                personas.remove(indiceE);
                System.out.println("Persona eliminada exitosamente");
            }else if(respuesta.equalsIgnoreCase("n")){
                System.out.println("Operacion cancelada");
            }else{
                System.out.println("Comando inválido");
            }
        }else{
            System.out.println("Indice inválido");
        }
    }

    public void buscadorNombres(){
        if(personas.isEmpty()){
            System.out.println("No hay personas para buscar");
            return;
        }
        System.out.print("Ingresa el nombre de la persona:");
        String nombreB = consola.nextLine().toLowerCase();

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

}
