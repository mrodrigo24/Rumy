
import java.util.*;
public class Juego {
    public static void main(String[] args) {
        int numero = -1;
        Mazo maz = new Mazo(2);
        List<Carta> descarte = new ArrayList<>();
        List<Jugador> jugadores = new ArrayList<>();
        List<Carta> jugadaTemporal = new ArrayList<>();
        ValidadorRummy valRumy = new ValidadorRummy();
        boolean alguienHaGanado = false;
        int turno = 0;
        for (int i = 0; i < 4; i++) {
            jugadores.add(new Jugador("Jugador" + (i)));
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                jugadores.get(i).recibirCartas(maz.cogerCarta());
            }
        }

        while (!alguienHaGanado) {
            Jugador jugadorActual = jugadores.get(turno);
            jugadorActual.hacerBackupmanoJugador();
            // Fase de robo
            if (!maz.estaVacio()) {
                Carta cartaRobada = maz.cogerCarta();
                descarte.add(cartaRobada);
                jugadorActual.recogerDescarte(descarte);
            }
            Collections.sort(jugadorActual.getCartasPorJugador());
            jugadorActual.mostrarMano();
            jugadorActual.sacarCartas(jugadaTemporal);

            if (!jugadaTemporal.isEmpty()) {

                if (valRumy.comprobar(jugadaTemporal, jugadorActual)) {
                    System.out.println("Jugada válida.");
                    if (jugadorActual.alguienHaGanado(jugadorActual.getCartasPorJugador())) {
                        alguienHaGanado = true;
                        System.out.println("¡Ganador: " + jugadorActual + "!");
                    }
                } else {
                    // Si la jugada NO es válida, devolvemos las cartas
                    System.out.println("Jugada inválida o puntos insuficientes.");
                    jugadorActual.restaurarmano();
                }
                // FINAL DEL TURNO: Estas líneas deben ejecutarse SIEMPRE (fuera de los IF de arriba)
                jugadaTemporal.clear();
                if (!alguienHaGanado) {
                    turno = (turno + 1) % 4;
                    System.out.println("\n--- CAMBIO DE TURNO ---");
                }
            }
        }
    }
}





