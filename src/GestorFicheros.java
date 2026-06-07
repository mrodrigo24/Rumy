import java.io.*;
import java.util.List;

public class GestorFicheros {

    public static void guardarPartida(Juego partida, String nombreArchivo) {

        try (FileOutputStream fileOut = new FileOutputStream(nombreArchivo);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            // El metodo escribe el arbol de objetos de golpe
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

            // cast
            Juego partidaCargada = (Juego) objectIn.readObject();
            System.out.println("Partida recuperada con éxito desde '" + nombreArchivo + "'.");
            return partidaCargada;

        } catch (FileNotFoundException e) {
            System.out.println("No se encontró ninguna partida guardada con el nombre '" + nombreArchivo + "'.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("¡Error al intentar cargar la partida!: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static void guardarLog(List<String> historial, String nombreArchivo) {

        try (FileWriter fw = new FileWriter(nombreArchivo,true);
             PrintWriter pw = new PrintWriter(fw)) {


            if (!historial.isEmpty()) {
                pw.println(historial.get(historial.size() - 1));
            }
        } catch (IOException e) {
            System.out.println("¡Error al intentar guardar el archivo de log!: " + e.getMessage());
        }
    }
}