import java.util.ArrayList;
import java.util.List;

public class JugadorIA extends Jugador {

    public JugadorIA(int numeroDeJugador) {
        super(numeroDeJugador);
    }


    public void decidirRoboAutomatico(Mazo mazoPrincipal, MazoDescarte mazoDescarte) {
        // Si el descarte está vacio robo del mazo
        if (mazoDescarte.estaVacio()) {
            this.recibirCartas(mazoPrincipal.cogerCarta());
            System.out.println(this + " (IA) ha robado del mazo principal.");
            return;
        }

        // Si hay carta en el descarte, miramos si es un Comodín
        Carta ultimaCartaDescarte = mazoDescarte.verUltimaCarta();
        if (ultimaCartaDescarte.getSimbolo() == Simbolo.COMODIN) {
            // ¡A la máquina le encantan los comodines! Lo coge sin dudarlo
            this.recibirCartas(mazoDescarte.tomarUltimaCarta());
            System.out.println(this + " (IA) vio un Comodín y robó del descarte.");
        } else {
            // Si es una carta normal, prefiere arriesgarse con el mazo oculto
            this.recibirCartas(mazoPrincipal.cogerCarta());
            System.out.println(this + " (IA) ha robado del mazo principal.");
        }
    }


    public void decidirJugadasAutomaticas(ValidadorRummy valRumy, Mesa mesa, ReglasJuego reglas) {
        System.out.println(this + " (IA) está analizando sus cartas para jugar...");

        // 1. Hacemos una copia limpia de la mano de la IA para trabajar sobre ella
        List<Carta> manoIA = new ArrayList<>(this.getCartasPorJugador());
        manoIA.removeIf(c -> c == null);

        boolean haJugadoAlgo = true;

        // 2. El bucle se repetirá mientras la IA siga encontrando combinaciones y tenga cartas
        while (haJugadoAlgo && manoIA.size() >= 3) {
            haJugadoAlgo = false;
            List<Carta> combinacionEncontrada = null;

            // 3. Algoritmo combinatorio: Buscamos tríos (subconjuntos de 3 cartas)
            for (int i = 0; i < manoIA.size() - 2; i++) {
                for (int j = i + 1; j < manoIA.size() - 1; j++) {
                    for (int k = j + 1; k < manoIA.size(); k++) {

                        // Creamos un trío temporal para probar
                        List<Carta> trioPrueba = new ArrayList<>();
                        trioPrueba.add(manoIA.get(i));
                        trioPrueba.add(manoIA.get(j));
                        trioPrueba.add(manoIA.get(k));

                        // Le preguntamos al validador si este trío específico es legal
                        if (valRumy.comprobar(trioPrueba, this, reglas)) {
                            combinacionEncontrada = trioPrueba;
                            haJugadoAlgo = true;
                            break; // Rompemos el bucle 'k'
                        }
                    }
                    if (haJugadoAlgo) break; // Rompemos el bucle 'j'
                }
                if (haJugadoAlgo) break; // Rompemos el bucle 'i'
            }

            // 4. Si encontramos un trío válido, lo bajamos a la mesa
            if (haJugadoAlgo && combinacionEncontrada != null) {
                Jugada nuevaJugada = JugadaGoes.crearJugada(combinacionEncontrada);
                mesa.agregarJugada(nuevaJugada);

                // Quitamos las cartas de la mano real del bot y de nuestra copia de análisis
                this.eliminarCartasDelaMano(combinacionEncontrada);
                manoIA.removeAll(combinacionEncontrada);

                System.out.println(this + " (IA) ha colocado una jugada en la mesa: " + nuevaJugada);
            }
        }

        if (!haJugadoAlgo) {
            System.out.println(this + " (IA) no tiene combinaciones válidas en este turno. Pasa de fase.");
        }
    }


    public Carta decidirDescarteAutomatico() {
        for (int i = 0; i < this.getCartasPorJugador().size(); i++) {
            Carta c = this.getCartasPorJugador().get(i);
            if (c != null) {
                // Llamamos a elegirCarta pasándole el índice directo 'i' (base 0)
                return this.elegirCarta(i);
            }
        }
        return null;
    }
}
