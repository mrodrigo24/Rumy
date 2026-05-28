import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;
public class Mesa {
    private List<Jugador> jugadores = new ArrayList<>();
    private List<Jugada> jugadasEnMesa;//lista de listas de jugadas en mesa
    //private List<Carta> mazoDescarte;
    private MazoDescarte descarte;
    public Mesa() {
        this.jugadasEnMesa = new ArrayList<Jugada>();
        this.descarte=new MazoDescarte();
    }

    public List<Jugada> getJugadasEnMesa() {
        return jugadasEnMesa;
    }

    public MazoDescarte getMazoDescarte() {
        return this.descarte;
    }

    public List<Jugador> prepararJugadores(Mazo mazo) {
        for (int i = 0; i < 4; i++) {
            Jugador nuevoJugador = new Jugador(i);
            nuevoJugador.repartir(mazo);
            this.jugadores.add(nuevoJugador);
        }
        return this.jugadores;
    }

    public void agregarJugada(Jugada nuevaJugada) {
        this.jugadasEnMesa.add(nuevaJugada);
    }

    public Carta robarDelDescarte() {
        if (!descarte.estaVacio()) {
            return descarte.tomarUltimaCarta();
        }
        return null;
    }
    public void tirarAlDescarte(Carta crt) {
        descarte.meterCarta(crt);
    }

    public boolean anyadirCartaAJugada(int indiceJugada, Carta carta) {
        Jugada jugadaObj = jugadasEnMesa.get(indiceJugada);
        return jugadaObj.anyadirCarta(carta);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            sb.append("=== JUGADAS EN LA MESA ===\n");
            int numeroJugada = 1;
            // Ahora recorremos objetos de tipo Jugada
            for (Jugada jugada : jugadasEnMesa) {
                sb.append("Jugada ").append(numeroJugada).append(": ");

                // Java llamará automáticamente al toString() que pusimos en la clase Jugada
                sb.append(jugada.toString()).append("\n");

                numeroJugada++;
            }
            sb.append("==========================");
        return sb.toString();
    }
}







