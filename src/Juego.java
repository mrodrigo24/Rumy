import java.util.*;

public class Juego {
    private boolean alguienHaGanado;
    private int turno;
    private Mazo maz;
    private Mesa mesa;
    private List<Jugador> jugadores;
    private List<Carta> jugadaTemporal;
    private ValidadorRummy valRumy;
    private Visualizador visual;
    private ControladorTurno controladorTurno;
    private GestorIntercambios gestorIntercambios;

    public Juego() {
        this.alguienHaGanado = false;
        this.turno = 0;
        this.maz = new Mazo(2);
        this.mesa = new Mesa();
        this.jugadores = mesa.prepararJugadores(maz);
        this.jugadaTemporal = new ArrayList<>();
        this.valRumy = new ValidadorRummy();
        this.visual = new Visualizador();
        this.controladorTurno = new ControladorTurno(this.visual);
        this.gestorIntercambios = new GestorIntercambios();
    }


    public void jugar() {
        while (!alguienHaGanado) {
            Jugador jugadorActual = jugadores.get(turno);
            this.visual.imprimeInicio(this.mesa);
            this.visual.imprimirMesa(this.mesa.getJugadasEnMesa());
            visual.mostrarMano(jugadorActual);

            // jugador auto
            if (jugadorActual instanceof JugadorIA) {
                JugadorIA bot = (JugadorIA) jugadorActual;

                System.out.println("\n--- TURNO AUTOMÁTICO DE " + bot + " ---");

                // 1. Fase de Robo Automática
                bot.decidirRoboAutomatico(this.maz, this.mesa.getMazoDescarte());

                // 2. Fase de Jugadas Automática
                bot.decidirJugadasAutomaticas(this.valRumy, this.mesa);

                // 3. Fase de Descarte Automática
                Carta cartaTirada = bot.decidirDescarteAutomatico();
                if (cartaTirada != null) {
                    mesa.tirarAlDescarte(cartaTirada);
                    System.out.println(bot + " ha descartado " + cartaTirada);
                }

                // Finalizamos el turno del Bot y pasamos al siguiente
                turno = (turno + 1) % 4;
                System.out.println("\n-- CAMBIO DE TURNO --\n");

            } else {
                // 👤 CAMINO HUMANO (Todo tu menú interactivo va metido aquí dentro)
                jugadorActual.hacerBackupmanoJugador();

                // Fase de Robo humana
                controladorTurno.deDondeRobar(jugadorActual, maz, mesa.getMazoDescarte());

                // --- Camino A: El jugador humano aun no ha salido ---
                if (!jugadorActual.isHaSalido()) {
                    System.out.println("\nNo has salido. Intenta hacer tus " + valRumy.getPUNTOS_MINIMOS_SALIDA() + " puntos");
                    jugadorActual.sacarCartas(jugadaTemporal, visual);

                    if (!jugadaTemporal.isEmpty()) {
                        if (valRumy.comprobar(jugadaTemporal, jugadorActual)) {
                            System.out.println("Jugada válida. Has salido");
                            Jugada nuevaJugada = JugadaGoes.crearJugada(jugadaTemporal);
                            mesa.agregarJugada(nuevaJugada);
                            jugadorActual.setHasalido(true);
                            jugadorActual.eliminarCartasDelaMano(jugadaTemporal);

                            visual.mostarNumeroDescarte(jugadorActual);

                            if (jugadorActual.alguienHaGanado(jugadorActual.getCartasPorJugador())) {
                                alguienHaGanado = true;
                                System.out.println("¡Ganador: " + jugadorActual + "!");
                            }
                        } else {
                            System.out.println("Jugada no valida o puntos insuficientes.");
                            jugadorActual.restaurarmano();
                            visual.mostarNumeroDescarte(jugadorActual);
                        }
                    }
                } else {
                    // --- Camino B: El jugador humano ya salió previamente ---
                    boolean terminarTurnoActual = false;
                    while (!terminarTurnoActual) {
                        System.out.println("\n¿Qué deseas hacer? \n1 - crear una nueva jugada \n2 - anyadir una carta a la mesa\n3 - robar carta de la mesa\n4 - terminar turno y descartar");
                        int opcion = LectorTeclado.leerEnteroEnRango(1, 4);

                        if (opcion == 1) {
                            jugadorActual.sacarCartas(jugadaTemporal, visual);
                            if (!jugadaTemporal.isEmpty()) {
                                if (valRumy.comprobar(jugadaTemporal, jugadorActual)) {
                                    System.out.println("Jugada valida ");
                                    Jugada jugadaPropuesta = JugadaGoes.crearJugada(jugadaTemporal);
                                    mesa.agregarJugada(jugadaPropuesta);

                                    jugadorActual.eliminarCartasDelaMano(jugadaTemporal);

                                    visual.mostarNumeroDescarte(jugadorActual);

                                    jugadaTemporal.clear();
                                    if (jugadorActual.alguienHaGanado(jugadorActual.getCartasPorJugador())) {
                                        alguienHaGanado = true;
                                        terminarTurnoActual = true;
                                        System.out.println("Ganador " + jugadorActual + "!");
                                    }
                                } else {
                                    System.out.println("Jugada no valida");
                                    jugadorActual.restaurarmano();
                                    jugadaTemporal.clear();
                                }
                            } else {
                                System.out.println("Has cancelado la creación de la jugada. Volviendo al menú.");
                            }

                        } else if (opcion == 2) {
                            controladorTurno.seleccionarCartaParaMesa(jugadorActual, mesa);
                            if (jugadorActual.alguienHaGanado(jugadorActual.getCartasPorJugador())) {
                                alguienHaGanado = true;
                                terminarTurnoActual = true;
                                System.out.println("Ganador " + jugadorActual + "!");
                            }
                        } else if (opcion == 3) {
                            System.out.println("\n--- Iniciando fase de intercambio con el tablero ---");
                            this.gestorIntercambios.solicitarYProcesarIntercambio(jugadorActual, this.mesa);
                        } else if (opcion == 4) {
                            System.out.println("Finalizando fase de jugadas. Procediendo al descarte obligatorio.");
                            terminarTurnoActual = true;
                        }
                    }
                }

                // --- Final de turno exclusivo del Humano ---
                if (!alguienHaGanado) {

                    mesa.tirarAlDescarte(controladorTurno.hacerDescarte(jugadorActual, this.visual));

                    jugadaTemporal.clear();
                    turno = (turno + 1) % 4;
                    System.out.println("\n--- CAMBIO DE TURNO ---");
                }
            } // Fin del bloque ELSE (Humano)
        } // Fin del bucle WHILE principal
    } // Fin del método jugar()
} // Fin de la clase Juego