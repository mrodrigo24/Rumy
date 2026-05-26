import java.util.*;

public class Visualizador {
    Map<Integer, Carta> opciones = new HashMap<>();
    Mesa mesa=new Mesa();
    public void imprimeInicio(){
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
        Collections.sort(jugador.getCartasPorJugador());
        mostrarMano(jugador);
        Iterator<Carta> it = jugador.getCartasPorJugador().iterator();
        while (it.hasNext()) {
            numeroDeCartaDeljugador++;
            Carta c = it.next();
            opciones.put(numeroDeCartaDeljugador,c);
            System.out.println(numeroDeCartaDeljugador + "- " + c);
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
