import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorArchivos {

    public static void guardarArchivo(ArrayList<Persona> personas, String nombreArchivo) {
        try (PrintWriter writer = new PrintWriter(nombreArchivo)) {
            for (Persona p : personas) {
                writer.println(p.toString());
            }
            System.out.println("Personas guardadas en: " + nombreArchivo);

        } catch (IOException e) {
            System.out.println("Error al guardar en archivo: " + e.getMessage());
        }
    }

    public static ArrayList<Persona> cargarPersonas(String nombreDeArchivo) {
        ArrayList<Persona> personas = new ArrayList<>();

        File archivo = new File(nombreDeArchivo);
        if (!archivo.exists()) {
            return personas; // Devuelve lista vacía si el archivo no existe
        }

        try (Scanner lector = new Scanner(archivo)) {
            while (lector.hasNextLine()) {//significa ¿hay otra línea para leer en el archivo?"
                String linea = lector.nextLine(); //si hay mas líneas por leer entonces las lee con nextLine.
                String[] partes = linea.split(",");//separa el texto en partes cada vez que encuentra una coma.
                if (partes.length == 3) {
                    String nombre = partes[0].trim();
                    String telefono = partes[1].trim();
                    String email = partes[2].trim();
                    personas.add(new Persona(nombre, telefono, email));
                }
            }
            System.out.println("Personas cargadas con éxito desde " + nombreDeArchivo);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return personas;
    }

}



