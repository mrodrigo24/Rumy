import java.util.*;
public class Juego {
    public static void main(String[] args) {
        int numero = -1;
        boolean alguienHaGanado = false;
        Mazo maz = new Mazo(2);
        Mesa mesa = new Mesa();
        List<Carta> descarte = new ArrayList<>();
        List<Jugador> jugadores = new ArrayList<>();
        List<Carta> jugadaTemporal = new ArrayList<>();
        ValidadorRummy valRumy = new ValidadorRummy();
        int turno = 0;
        for (int i = 0; i < 4; i++) {
            jugadores.add(new Jugador("Jugador" + (i)));
            jugadores.get(i).repartir(maz);
        }

        while (!alguienHaGanado) {
            Jugador jugadorActual = jugadores.get(turno);
            jugadorActual.deDondeRobar(descarte,maz);
            jugadorActual.hacerBackupmanoJugador();
            jugadorActual.mostrarMano();
            jugadorActual.sacarCartas(jugadaTemporal);

            if (!jugadaTemporal.isEmpty()) {
                if (valRumy.comprobar(jugadaTemporal, jugadorActual)) {
                    System.out.println("Jugada válida.");
                    mesa.agregarJugada(jugadaTemporal);
                    if (jugadorActual.alguienHaGanado(jugadorActual.getCartasPorJugador())) {
                        alguienHaGanado = true;
                        System.out.println("¡Ganador: " + jugadorActual + "!");
                    }
                } else {
                    // Si la jugada NO es válida, devolvemos las cartas

                    System.out.println("Jugada inválida o puntos insuficientes.");
                    jugadorActual.restaurarmano();
                    jugadorActual.mostrarMano();
                    System.out.println("Tira una carta a la mesa");
                }
            }
                descarte.add(jugadorActual.hacerDescarte());


                jugadaTemporal.clear();
                if (!alguienHaGanado) {
                    turno = (turno + 1) % 4;
                    System.out.println("\n--- CAMBIO DE TURNO ---");
                }
            }
        }
    }







