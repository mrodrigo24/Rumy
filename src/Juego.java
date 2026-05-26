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

       public Juego(){
           this.alguienHaGanado = false;
           this.turno = 0;
           this.maz = new Mazo(2);
           this.mesa = new Mesa();
           this.jugadores = mesa.prepararJugadores(maz);
           this.jugadaTemporal = new ArrayList<>();
           this.valRumy = new ValidadorRummy();
           this.visual=new Visualizador();
       }


        public void jugar() {

            while (!alguienHaGanado) {
                Jugador jugadorActual = jugadores.get(turno);//primero cogemos turno
                // --- mostramos mesa y  mano ---

                System.out.println(mesa.toString());

                visual.mostrarMano(jugadorActual);

                //Fase de Robo
                jugadorActual.deDondeRobar(mesa, maz);//preguntamos de donde sacamos del mazo o de la mesa
                jugadorActual.hacerBackupmanoJugador();

                //Camino A, el jugador aun no ha salido
                if (!jugadorActual.isHaSalido()) {  //Sihasalidocon10
                    System.out.println("\nNo has salido. Intenta hacer tus " + valRumy.getPUNTOS_MINIMOS_SALIDA() + " puntos");
                    visual.mostarNumeroDescarte(jugadorActual);
                    jugadorActual.sacarCartas(jugadaTemporal);
                    if (!jugadaTemporal.isEmpty()) {
                        if (valRumy.comprobar(jugadaTemporal, jugadorActual)) {
                            System.out.println("Jugada válida. Has salido");

                            // Polimorfismo: Intentamos empaquetar como Grupo, si no es válido, es una Escalera
                            Jugada nuevaJugada = new Grupo(new ArrayList<>(jugadaTemporal));
                            if (!nuevaJugada.validarJugada()) {
                                nuevaJugada = new Escalera(new ArrayList<>(jugadaTemporal));
                            }

                            // Ahora sí, agregamos el objeto Jugada polimórfico a la mesa
                            mesa.agregarJugada(nuevaJugada);

                            jugadorActual.setHasalido(true); //
                            jugadorActual.eliminarCartasDelaMano(jugadaTemporal); //
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
                    System.out.println("\n¿Qué deseas hacer? \n1 - crear una nueva jugada \n2 - anyadir una carta a la mesa"); //
                    int opcion = LectorTeclado.leerEnteroEnRango(1, 2); //

                    if (opcion == 1) {
                        jugadorActual.sacarCartas(jugadaTemporal); //
                        if (!jugadaTemporal.isEmpty()) { //
                            if (valRumy.comprobar(jugadaTemporal, jugadorActual)) { //
                                System.out.println("Jugada valida "); //

                                // Polimorfismo: Identificamos si es Grupo o Escalera e instanciamos la Jugada real
                                Jugada nuevaJugada = new Grupo(new ArrayList<>(jugadaTemporal));
                                if (!nuevaJugada.validarJugada()) {
                                    nuevaJugada = new Escalera(new ArrayList<>(jugadaTemporal));
                                }

                                // Ahora sí, agregamos el objeto Jugada a la mesa
                                mesa.agregarJugada(nuevaJugada);

                                jugadorActual.setHasalido(true); //
                                jugadorActual.eliminarCartasDelaMano(jugadaTemporal); //
                                visual.mostarNumeroDescarte(jugadorActual); //

                                jugadaTemporal.clear(); //
                                if (jugadorActual.alguienHaGanado(jugadorActual.getCartasPorJugador())) { //
                                    alguienHaGanado = true; //
                                    System.out.println("Ganador " + jugadorActual + "!"); //
                                }
                            } else {
                                System.out.println("Jugada no valida"); //
                                jugadorActual.restaurarmano(); //
                                jugadaTemporal.clear(); //
                            }
                        }
                    } else if (opcion == 2) {
                        // Quitamos valRumy de los parámetros porque Mesa y Jugada ya se validan solas de forma polimórfica
                        jugadorActual.seleccionarCartaParaMesa(mesa);
                    }
                }

                //final de turno
                if (!alguienHaGanado) {
                    mesa.tirarAlDescarte(jugadorActual.hacerDescarte());
                    jugadorActual.limpiarVaciosDeLaMano();
                    jugadaTemporal.clear();
                    turno = (turno + 1) % 4;
                    System.out.println("\n--- CAMBIO DE TURNO ---");
                }

            } //cierre de while
        }
    }//cierre de juego










