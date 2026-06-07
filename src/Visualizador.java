import java.io.Serializable;
import java.util.*;

public class Visualizador implements Serializable {
    Map<Integer, Carta> opciones = new HashMap<>();

    public Map<Integer, Carta> getOpciones() {
        return this.opciones;
    }
    public void imprimeInicio(Mesa mesa){
        System.out.println("\n=== ESTADO DE LA MESA ===");
        System.out.println(mesa.toString());
        System.out.println("==========================");
        System.out.println("\nAntes de robar:");
    }

    public void mostrarMano(Jugador jugador) {
        List<Carta> cartas = jugador.getCartasPorJugador();
        System.out.println("\nMano de " + jugador + ":");
        for (int i = 0; i < cartas.size(); i++) {
            System.out.println((i + 1) + " - " + cartas.get(i));        }
        System.out.println();
    }

    public void mostarNumeroDescarte(Jugador jugador){
        //Creamos la copia limpia y la ordenamos
        List<Carta> copiaOrdenada = new ArrayList<>(jugador.getCartasPorJugador());
        Collections.sort(copiaOrdenada);

        // Llimpiamos el mapa de opciones anteriores
        opciones.clear();
        System.out.println("\n=== TU MANO (ORDENADA) ===");

        // Usamos un bucle clasico con indice para que los números vayan seguidos: 1, 2, 3...
        for (int i = 0; i < copiaOrdenada.size(); i++) {
            Carta c = copiaOrdenada.get(i);


            int numeroParaPulsar = i + 1;

            // Guardamos el mapa de opciones
            opciones.put(numeroParaPulsar, c);

            // Imprimimos de manera limpia y consecutiva
            System.out.println(numeroParaPulsar + " - " + c);
        }
        System.out.println();
    }
    public void imprimirMesa(JugadasEnMesa jugadasEnMesa) {
        System.out.println(jugadasEnMesa);
    }

    public void imprimirMenu(){
        System.out.println( """
        === BIENVENIDO AL RUMMY EN JAVA ===

        1. Jugar al Rummy Argentino (9 cartas, 10 pts para salir)

        2. Jugar al Rummikub (14 cartas, 30 pts para salir)

        3. Jugar al Gin Rummy (10 cartas, 0 pts para salir)

        Selecciona una opción:""");
    }

}
