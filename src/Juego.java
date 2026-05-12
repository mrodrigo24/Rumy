
import java.util.*;
public class Juego {
    public static void main(String[] args) {
        int numero = -1;
        Mazo maz = new Mazo(2);
        List<Carta> descarte = new ArrayList<>();
        List<Jugador> jugadores = new ArrayList<>();
        List<Carta> jugadaTemporal = new ArrayList<>();
        ValidadorRummy valRumy = new ValidadorRummy();
        int turno = 0;
        for (int i = 0; i < 4; i++) {
            jugadores.add(new Jugador("Jugador" + (i)));
        }


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                jugadores.get(i).recibirCartas(maz.cogerCarta());
            }
        }


        boolean alguienHaGanado = false;
        while (!alguienHaGanado) {
            Jugador jugadorActual = jugadores.get(turno);
            jugadorActual.hacerBackupmanoJugador();
            if (!maz.estaVacio()) {

                Carta cartaRobada = maz.cogerCarta();
                descarte.add(cartaRobada);
            } else {
                System.out.println("El mazo se ha agotado. Fin del juego o barajar descarte.");
                alguienHaGanado = true; // O la lógica que prefieras para terminar
            }
            //descarte.add(maz.cogerCarta());
            jugadorActual.recogerDescarte(descarte);
            jugadorActual.mostrarMano();
            jugadorActual.sacarCartas(jugadaTemporal);
            if(valRumy.comprobar(jugadaTemporal,jugadorActual)) {
                System.out.println("Jugada válida.");
                if (jugadorActual.alguienHaGanado(jugadorActual.getCartasPorJugador())) {
                    alguienHaGanado = true;
                    System.out.println("¡Ganador: " + jugadorActual + "!");
                } else
                    System.out.println("Jugada inválida o puntos insuficientes (mínimo 30).");
                jugadorActual.restaurarmano();
                jugadaTemporal.clear();

                if (!alguienHaGanado) {
                    turno = (turno + 1) % 4;
                }
            }
        }
    }
}





