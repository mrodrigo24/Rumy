import java.util.*;
public class Juego {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean alguienHaGanado = false;
        int turno = 0;
        int opcion;
        Mazo maz = new Mazo(2);
        Mesa mesa = new Mesa();
        List<Jugador> jugadores = mesa.prepararJugadores(maz);
        List<Carta> jugadaTemporal = new ArrayList<>();
        ValidadorRummy valRumy = new ValidadorRummy();

        while (!alguienHaGanado) {
            Jugador jugadorActual = jugadores.get(turno);//primero cogemos turno
            jugadorActual.hacerBackupmanoJugador();

            // --- mostramos mesa y  mano ---
            System.out.println("\n=== ESTADO DE LA MESA ===");
            mesa.imprimirMesa(mesa.getJugadasEnMesa());
            System.out.println("==========================");
            System.out.println("\nAntes de robar:");
            jugadorActual.mostrarMano();
            jugadorActual.deDondeRobar(mesa, maz);//preguntamos de donde sacamos del mazo o de la mesa
            //jugadorActual.mostrarMano();

            //Camino A, el jugador aun no ha salido
            if (!jugadorActual.isHaSalido()) {  //Sihasalidocon10
                System.out.println("\nNo has salido. Intenta hacer tus " + valRumy.getPUNTOS_MINIMOS_SALIDA() + " puntos");
                //jugadorActual.mostrarMano();
                jugadorActual.sacarCartas(jugadaTemporal);

                if (!jugadaTemporal.isEmpty()) {
                    if (valRumy.comprobar(jugadaTemporal, jugadorActual)) {
                        System.out.println("Jugada válida. Has salido");
                        mesa.agregarJugada(jugadaTemporal);
                        jugadorActual.setHasalido(true);
                        jugadorActual.mostrarMano();

                        if (jugadorActual.alguienHaGanado(jugadorActual.getCartasPorJugador())) {
                            alguienHaGanado = true;
                            System.out.println("¡Ganador: " + jugadorActual + "!");
                        }
                    } else {
                        System.out.println("Jugada inválida o puntos insuficientes.");
                        jugadorActual.restaurarmano();
                        jugadorActual.mostrarMano();
                    }
                }
            } else {
                System.out.println("\n¿Qué deseas hacer? \n1 - crear una nueva jugada \n2 - anyadir una carta a la mesa");
                opcion = LectorTeclado.leerEnteroEnRango(1, 2);


                if (opcion == 1) {
                    jugadorActual.sacarCartas(jugadaTemporal);
                    if (!jugadaTemporal.isEmpty()) {
                        if (valRumy.comprobar(jugadaTemporal, jugadorActual)) {
                            System.out.println("Jugada Valida");
                            mesa.agregarJugada(jugadaTemporal);
                            jugadorActual.mostrarMano();

                            if (jugadorActual.alguienHaGanado(jugadorActual.getCartasPorJugador())) {
                                alguienHaGanado = true;
                                System.out.println("Ganador" + jugadorActual + "!");

                            }
                        } else {
                            System.out.println("Jugada Invalidad");
                            jugadorActual.restaurarmano();
                            jugadorActual.mostrarMano();
                        }
                    }
                } else if (opcion == 2) {
                    jugadorActual.seleccionarCartaParaMesa(mesa, valRumy);
                }
            }

            //final de turno
            if (!alguienHaGanado){
                mesa.tirarAlDescarte(jugadorActual.hacerDescarte());
                jugadaTemporal.clear();
                turno = (turno + 1) % 4;
                System.out.println("\n--- CAMBIO DE TURNO ---");
            }

         } //cierre de while
        }//cierre de main
    }//cierre de juego










