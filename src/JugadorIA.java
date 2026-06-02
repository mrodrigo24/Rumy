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

        // Creamos un simulacro: la máquina intenta enviar TODA su mano al validador
        List<Carta> todasMisCartas = new ArrayList<>(this.getCartasPorJugador());

        // Quitamos los posibles nulls por seguridad en la copia
        todasMisCartas.removeIf(c -> c == null);

        if (!todasMisCartas.isEmpty()) {
            // Le preguntamos a tu validador si la combinación de toda la mano es legal
            if (valRumy.comprobar(todasMisCartas, this,reglas)) {
                // Si el validador da el OK, creamos la jugada y la subimos a la mesa
                Jugada nuevaJugada = JugadaGoes.crearJugada(todasMisCartas);
                mesa.agregarJugada(nuevaJugada);

                // Vaciamos su mano real porque ha colocado todas las cartas
                this.getCartasPorJugador().clear();
                System.out.println(this + " (IA) ha colocado una jugada maestra en la mesa.");
            } else {
                System.out.println(this + " (IA) no tiene combinaciones válidas en este turno. Pasa de fase.");
            }
        }
    }

    /**
     * REGLA 3 AUTOMÁTICA: ¿Qué carta tira al descarte?
     */
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
