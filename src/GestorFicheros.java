import java.io.*;
import java.util.List;

public class GestorFicheros {

    public static void guardarPartida(Juego partida, String nombreArchivo) {
        // Al meter los streams dentro del paréntesis del try, Java los cierra automáticamente al terminar
        try (FileOutputStream fileOut = new FileOutputStream(nombreArchivo);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            // El método mágico que escribe todo el árbol de objetos de golpe
            objectOut.writeObject(partida);
            System.out.println("Partida guardada correctamente en '" + nombreArchivo + "'.");

        } catch (IOException e) {
            System.out.println("¡Error al intentar guardar la partida!: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public static Juego cargarPartida(String nombreArchivo) {
        try (FileInputStream fileIn = new FileInputStream(nombreArchivo);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            // Leemos el objeto genérico y le hacemos un "cast" (conversión) a la clase Juego
            Juego partidaCargada = (Juego) objectIn.readObject();
            System.out.println("Partida recuperada con éxito desde '" + nombreArchivo + "'.");
            return partidaCargada;

        } catch (FileNotFoundException e) {
            System.out.println("No se encontró ninguna partida guardada con el nombre '" + nombreArchivo + "'.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("¡Error al intentar cargar la partida!: " + e.getMessage());
            e.printStackTrace();
        }
        return null; // Si algo falla, devolvemos null para que el programa sepa que no pudo cargar nada
    }

    public static void guardarLog(List<String> historial, String nombreArchivo) {
        // El try-with-resources asegura que el archivo se cierre correctamente al terminar
        try (FileWriter fw = new FileWriter(nombreArchivo,true);
             PrintWriter pw = new PrintWriter(fw)) {

            // Recorremos la lista línea por línea y la escribimos en el fichero
            if (!historial.isEmpty()) {
                pw.println(historial.get(historial.size() - 1));
            }
        } catch (IOException e) {
            System.out.println("¡Error al intentar guardar el archivo de log!: " + e.getMessage());
        }
    }
}