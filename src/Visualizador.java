import java.util.*;

public class Visualizador {
    Map<Integer, Carta> opciones = new HashMap<>();

    public void imprimeInicio(Mesa mesa){
        System.out.println("\n=== ESTADO DE LA MESA ===");
        System.out.println(mesa.toString());
        System.out.println("==========================");
        System.out.println("\nAntes de robar:");
    }

    public void mostrarMano(Jugador jugador) {
        List<Carta> cartas = jugador.getCartasPorJugador();
        System.out.println("\nMano de " + jugador.toString() + ":");
        for (int i = 0; i < cartas.size(); i++) {
            System.out.println((i + 1) + " - " + cartas.get(i));        }
        System.out.println();
    }
    
    public void mostarNumeroDescarte(Jugador jugador){
        int numeroDeCartaDeljugador=0;
        List<Carta> copiaOrdenada = new ArrayList<>(jugador.getCartasPorJugador());
        List<Carta> sacoDeNulls = new ArrayList<>();
        sacoDeNulls.add(null);
        copiaOrdenada.removeAll(sacoDeNulls);
        Collections.sort(copiaOrdenada);
        System.out.println("\n=== TU MANO (ORDENADA) ===");
        //mostrarMano(jugador);
        for (Carta c : copiaOrdenada) {

            int numeroParaPulsar = jugador.getCartasPorJugador().indexOf(c) + 1; //

            // Guardamos en tu mapa de opciones
            opciones.put(numeroParaPulsar, c);

            // Imprimimos
            System.out.println(numeroParaPulsar + " - " + c); //
        }

        System.out.println();
    }

    public void imprimirMesa (List < List < Carta >> jugadasEnMesa) {
        int numeroDeJugada=1;
        for (List<Carta> jugada : jugadasEnMesa) {
            System.out.print("Jugada "+numeroDeJugada + ":");
            for (Carta carta : jugada) {
                System.out.print(carta + " ");
            }
            numeroDeJugada++;
            System.out.println();
        }
    }

}
