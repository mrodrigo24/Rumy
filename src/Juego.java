import java.util.*;
public class Juego {
        private  boolean alguienHaGanado;
        private int turno;
        private Mazo maz;
        private Mesa mesa;
        private List<Jugador> jugadores;
        private List<Carta> jugadaTemporal;
        private ValidadorRummy valRumy;
        private Visualizador visual;
        private ControladorTurno controladorTurno;
       public Juego(){
           this.alguienHaGanado = false;
           this.turno = 0;
           this.maz = new Mazo(2);
           this.mesa = new Mesa();
           this.jugadores = mesa.prepararJugadores(maz);
           this.jugadaTemporal = new ArrayList<>();
           this.valRumy = new ValidadorRummy();
           this.visual=new Visualizador();
           this.controladorTurno = new ControladorTurno(this.visual);
       }

        public void jugar() {

            while (!alguienHaGanado) {

                Jugador jugadorActual = jugadores.get(turno);//primero cogemos turno
                // --- mostramos mesa y  mano ---

                System.out.println(mesa.toString());

                visual.mostrarMano(jugadorActual);

                //Fase de Robo
                controladorTurno.deDondeRobar(jugadorActual, maz, mesa.getMazoDescarte());//preguntamos de donde sacamos del mazo o de la mesa
                jugadorActual.hacerBackupmanoJugador();

                //Camino A, el jugador aun no ha salido
                if (!jugadorActual.isHaSalido()) {  //Sihasalidocon10
                    System.out.println("\nNo has salido. Intenta hacer tus " + valRumy.getPUNTOS_MINIMOS_SALIDA() + " puntos");
                    //visual.mostarNumeroDescarte(jugadorActual);
                    jugadorActual.sacarCartas(jugadaTemporal, visual);
                    if (!jugadaTemporal.isEmpty()) {
                        if (valRumy.comprobar(jugadaTemporal, jugadorActual)) {
                            System.out.println("Jugada válida. Has salido");
                            Jugada nuevaJugada = JugadaGoes.crearJugada(jugadaTemporal);
                            mesa.agregarJugada(nuevaJugada);
                            jugadorActual.setHasalido(true); //
                            jugadorActual.eliminarCartasDelaMano(jugadaTemporal); //
                            jugadorActual.limpiarVaciosDeLaMano();
                            visual.mostarNumeroDescarte(jugadorActual); //

                            if (jugadorActual.alguienHaGanado(jugadorActual.getCartasPorJugador())) { //
                                alguienHaGanado = true; //
                                System.out.println("¡Ganador: " + jugadorActual + "!"); //
                            }
                        } else {
                            System.out.println("Jugada no valida o puntos insuficientes."); //
                            jugadorActual.restaurarmano(); //
                            visual.mostarNumeroDescarte(jugadorActual); //
                        }
                    }
                } else {
                    boolean terminarTurnoActual = false;
                    while (!terminarTurnoActual) {
                    System.out.println("\n¿Qué deseas hacer? \n1 - crear una nueva jugada \n2 - anyadir una carta a la mesa\n3 - robar carta de la mesa\n4 - terminar turno y descartar"); //
                    int opcion = LectorTeclado.leerEnteroEnRango(1, 4); //

                    if (opcion == 1) {
                        jugadorActual.sacarCartas(jugadaTemporal,visual);
                        if (!jugadaTemporal.isEmpty()) {
                            if (valRumy.comprobar(jugadaTemporal, jugadorActual)) {
                                System.out.println("Jugada valida ");
                                Jugada jugadaPropuesta = JugadaGoes.crearJugada(jugadaTemporal);
                                // Ahora sí, agregamos el objeto Jugada a la mesa
                                mesa.agregarJugada(jugadaPropuesta);

                                //jugadorActual.setHasalido(true);
                                jugadorActual.eliminarCartasDelaMano(jugadaTemporal);
                                jugadorActual.limpiarVaciosDeLaMano();
                                visual.mostarNumeroDescarte(jugadorActual);

                                jugadaTemporal.clear();
                                if (jugadorActual.alguienHaGanado(jugadorActual.getCartasPorJugador())) { //
                                    alguienHaGanado = true;
                                    terminarTurnoActual = true;
                                    System.out.println("Ganador " + jugadorActual + "!");
                                }
                            } else {
                                System.out.println("Jugada no valida");
                                jugadorActual.restaurarmano();
                                jugadaTemporal.clear();
                            }
                        } else{
                            System.out.println("Has cancelado la creación de la jugada. Volviendo al menú.");
                        }

                    } else if (opcion == 2) {
                        // Quitamos valRumy de los parámetros porque Mesa y Jugada ya se validan solas de forma polimórfica
                        controladorTurno.seleccionarCartaParaMesa(jugadorActual,mesa);
                        if (jugadorActual.alguienHaGanado(jugadorActual.getCartasPorJugador())) {
                            alguienHaGanado = true;
                            terminarTurnoActual = true;
                            System.out.println("Ganador " + jugadorActual + "!");
                        }
                    } else if (opcion==3){

                    }
                else if (opcion == 4) {
                        // 3. El jugador decide voluntariamente finalizar sus acciones
                        System.out.println("Finalizando fase de jugadas. Procediendo al descarte obligatorio.");
                        terminarTurnoActual = true;
                    }


            }
        }

                //final de turno
                if (!alguienHaGanado) {
                    jugadorActual.limpiarVaciosDeLaMano();
                    mesa.tirarAlDescarte(controladorTurno.hacerDescarte(jugadorActual));

                    jugadaTemporal.clear();
                    turno = (turno + 1) % 4;
                    System.out.println("\n--- CAMBIO DE TURNO ---");
                }

            } //cierre de while
        }
    }//cierre de juego










