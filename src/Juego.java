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
    private ReglasJuego reglas;
    private List<String> historialMovimientos;
    public Juego() {
        this.alguienHaGanado = false;
        this.turno = 0;
        this.jugadaTemporal = new ArrayList<>();
        this.valRumy = new ValidadorRummy();
        this.visual = new Visualizador();
        this.controladorTurno = new ControladorTurno(this.visual);
        this.gestorIntercambios = new GestorIntercambios();
        this.historialMovimientos = new ArrayList<>();
    }


    public void jugar() {
        if (this.jugadores == null) {
            System.out.println("Configurando nueva partida...");
        this.reglas = LectorTeclado.pedirVarianteJuego();
        this.maz = new Mazo(2);
        this.mesa = new Mesa();
        this.jugadores = mesa.prepararJugadores(maz, this.reglas);
        } else {
            System.out.println("Reanudando partida existente");
        }
        while (!alguienHaGanado) {
            Jugador jugadorActual = jugadores.get(turno);
            this.visual.imprimeInicio(this.mesa);
            this.visual.imprimirMesa(this.mesa.getJugadasEnMesa());
            visual.mostrarMano(jugadorActual);

            // jugador auto
            if (jugadorActual instanceof JugadorIA) {
                JugadorIA bot = (JugadorIA) jugadorActual;
                System.out.println("\n--- TURNO AUTOMÁTICO DE " + bot + " ---");

                // Fase de Robo Automática
                bot.decidirRoboAutomatico(this.maz, this.mesa.getMazoDescarte());
                registrarAccion(bot + " ha realizado su fase de robo.");
                // Fase de Jugadas Automática
                bot.decidirJugadasAutomaticas(this.valRumy, this.mesa,reglas);

                // Fase de Descarte Automática
                Carta cartaTirada = bot.decidirDescarteAutomatico();
                if (cartaTirada != null) {
                    mesa.tirarAlDescarte(cartaTirada);
                    System.out.println(bot + " ha descartado " + cartaTirada);
                    registrarAccion(bot + " ha descartado " + cartaTirada);
                }
                if (jugadorActual.alguienHaGanado(jugadorActual.getCartasPorJugador())) {
                    alguienHaGanado = true;
                    registrarAccion("La partida ha finalizado. ¡Ganador: " + jugadorActual + "!");
                    System.out.println("¡Ganador: " + jugadorActual + "!");
                    System.exit(0);
                } else {
                    // Finalizamos el turno del Bot y pasamos al siguiente
                    turno = (turno + 1) % 4;
                    System.out.println("\n-- CAMBIO DE TURNO --\n");
                }
            } else {
                //  yo
                jugadorActual.hacerBackupmanoJugador();

                // fase mia de robo
                controladorTurno.deDondeRobar(jugadorActual, maz, mesa.getMazoDescarte());
                registrarAccion(jugadorActual + " he robado una carta.");
                // todavia no he salido
                if (!jugadorActual.isHaSalido()) {
                    System.out.println("\nNo has salido. Intenta hacer tus " + reglas.getPUNTOS_MINIMOS_SALIDA() + " puntos");
                    jugadorActual.sacarCartas(jugadaTemporal, visual);

                    if (!jugadaTemporal.isEmpty()) {
                        if (valRumy.comprobar(jugadaTemporal, jugadorActual, this.reglas)) {
                            System.out.println("Jugada válida. Has salido");
                            Jugada nuevaJugada = JugadaGoes.crearJugada(jugadaTemporal);
                            mesa.agregarJugada(nuevaJugada);
                            jugadorActual.setHasalido(true);
                            jugadorActual.eliminarCartasDelaMano(jugadaTemporal);

                            visual.mostarNumeroDescarte(jugadorActual);

                            if (jugadorActual.alguienHaGanado(jugadorActual.getCartasPorJugador())) {
                                alguienHaGanado = true;
                                System.out.println("¡Ganador: " + jugadorActual + "!");
                                registrarAccion("La partida ha finalizado. ¡Ganador: " + jugadorActual + "!");
                                System.exit(0);
                            }
                        } else {
                            System.out.println("Jugada no valida o puntos insuficientes.");
                            jugadorActual.restaurarmano();
                            visual.mostarNumeroDescarte(jugadorActual);
                            jugadaTemporal.clear();
                            System.out.println("turno cancelado");
                            turno = (turno + 1) % 4;
                            continue;

                        }
                    }
                } else {
                    // Ya he salido
                    boolean terminarTurnoActual = false;
                    while (!terminarTurnoActual) {
                        System.out.println("\n¿Qué deseas hacer? \n1 - crear una nueva jugada \n2 - anyadir una carta a la mesa\n3 - robar carta de la mesa\n4 - terminar turno y descartar\n5 - guardar y salir");
                        int opcion = LectorTeclado.leerEnteroEnRango(1, 5);

                        if (opcion == 1) {
                            jugadorActual.sacarCartas(jugadaTemporal, visual);
                            if (!jugadaTemporal.isEmpty()) {
                                if (valRumy.comprobar(jugadaTemporal, jugadorActual, this.reglas)) {
                                    System.out.println("Jugada valida ");
                                    Jugada jugadaPropuesta = JugadaGoes.crearJugada(jugadaTemporal);
                                    mesa.agregarJugada(jugadaPropuesta);
                                    jugadorActual.eliminarCartasDelaMano(jugadaTemporal);
                                    registrarAccion(jugadorActual + " ha creado una nueva jugada en la mesa.");
                                    visual.mostarNumeroDescarte(jugadorActual);

                                    jugadaTemporal.clear();
                                    if (jugadorActual.alguienHaGanado(jugadorActual.getCartasPorJugador())) {
                                        alguienHaGanado = true;
                                        terminarTurnoActual = true;
                                        System.out.println("Ganador " + jugadorActual + "!");
                                        registrarAccion("La partida ha finalizado. ¡Ganador: " + jugadorActual + "!");
                                        System.exit(0);
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
                            registrarAccion(jugadorActual + " ha intentado añadir una carta a una jugada de la mesa.");
                            if (jugadorActual.alguienHaGanado(jugadorActual.getCartasPorJugador())) {
                                alguienHaGanado = true;
                                terminarTurnoActual = true;
                                System.out.println("Ganador " + jugadorActual + "!");
                                registrarAccion("La partida ha finalizado. ¡Ganador: " + jugadorActual + "!");
                                System.exit(0);
                            }
                        } else if (opcion == 3) {
                            System.out.println("\n--- Iniciando fase de intercambio con el tablero ---");
                            this.gestorIntercambios.solicitarYProcesarIntercambio(jugadorActual, this.mesa);
                            registrarAccion(jugadorActual + " ha realizado un intercambio con el tablero.");
                        } else if (opcion == 4) {
                            System.out.println("Finalizando fase de jugadas. Procediendo al descarte obligatorio.");
                            terminarTurnoActual = true;
                        }
                        else if (opcion == 5) {
                                GestorFicheros.guardarPartida(this, "partida.dat");
                                System.out.println("Saliendo del juego...");
                                System.exit(0);

                        }
                    }
                }

                // --- Final de turno exclusivo del Humano ---
                if (!alguienHaGanado) {
                    Carta desc = controladorTurno.hacerDescarte(jugadorActual, this.visual);
                    mesa.tirarAlDescarte(desc);
                    registrarAccion(jugadorActual + " se ha descartado de " + desc);
                    jugadaTemporal.clear();
                    turno = (turno + 1) % 4;
                    System.out.println("\n--- CAMBIO DE TURNO ---");
                }
            } // Fin del bloque ELSE (Humano)
        } // Fin del bucle WHILE principal
    } // Fin del método jugar()
    public void registrarAccion(String accion) {
        this.historialMovimientos.add(accion);

        GestorFicheros.guardarLog(this.historialMovimientos, "log_partida.txt");

    }
} // Fin de la clase Juego