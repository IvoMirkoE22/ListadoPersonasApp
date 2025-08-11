import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorArchivos {

    public static void guardarArchivo(ArrayList<Persona> personas, String nombreArchivo) {
        if (personas.isEmpty()) {
            System.out.println("No hay personas para guardar.");
            return;
        }

        File archivo = new File(nombreArchivo);

        // Si existe, pedir confirmación
        if (archivo.exists()) {
            Scanner confirmacion = new Scanner(System.in);
            System.out.println("El archivo ya existe. ¿Desea sobreescribirlo? (s/n)");
            String respuesta = confirmacion.nextLine().trim().toLowerCase();
            if (!respuesta.equals("s")) {
                System.out.println("Operación cancelada. No se guardaron los datos.");
                return;
            }
        }
        // try-catch: asegura cerrar el escritor automáticamente
        try (FileWriter escritor = new FileWriter(nombreArchivo)) {
            for (Persona p : personas) {
                escritor.write(p.getNombre() + "," + p.getTelefono() + "," + p.getEmail() + "\n");
            }
            System.out.println("Lista guardada correctamente en: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    public static ArrayList<Persona> cargarPersonas(String nombreArchivo) {
        ArrayList<Persona> personas = new ArrayList<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty()) {
                    String[] partes = linea.split(",");
                    if (partes.length == 3) {
                        String nombre = partes[0].trim();
                        String telefono = partes[1].trim();
                        String email = partes[2].trim();
                        personas.add(new Persona(nombre, telefono, email));
                    }
                }
            }
            System.out.println("Personas cargadas con éxito desde: " + nombreArchivo);
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return personas;
    }

    public static void eliminarArchivo(String nombreArchivo){
        Scanner confirmacionE = new Scanner(System.in);
        System.out.print("¿Está seguro que desea eliminar el archivo " + nombreArchivo + "? (s/n): ");
        String respuesta = confirmacionE.nextLine().trim().toLowerCase();
        if (!respuesta.equals("s")) {
            System.out.println("Operación cancelada.");
            return;
        }
            File archivo = new File(nombreArchivo);
            if (!archivo.exists()) {
                System.out.println("El archivo que desea eliminar no existe");
            }
                if (archivo.delete()) {
                    System.out.println("Archivo eliminado correctamente");
                } else {
                    System.out.println("No se puede eliminar el archivo. Verifique si el archivo está en uso o si tiene permisos.");
                }
    }
}




