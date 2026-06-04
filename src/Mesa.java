import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
public class Mesa implements Serializable {
    private List<Jugador> jugadores = new ArrayList<>();
    private JugadasEnMesa jugadasEnMesa;
    private MazoDescarte descarte;

    public Mesa() {
        this.jugadasEnMesa = new JugadasEnMesa();
        this.descarte=new MazoDescarte();
    }

    public JugadasEnMesa getJugadasEnMesa() {
        return this.jugadasEnMesa;
    }
    public MazoDescarte getMazoDescarte() {
        return this.descarte;
    }

    public List<Jugador> prepararJugadores(Mazo mazo, ReglasJuego reglas) {
        this.jugadores.clear();
        // El jugador 0 sera humano
        Jugador humano = new Jugador(0);
        humano.repartir(mazo,reglas.cuantasRepartimos());
        this.jugadores.add(humano);

        // 1, 2 y 3
        for (int i = 1; i < 4; i++) {
            JugadorIA bot = new JugadorIA(i);
            bot.repartir(mazo,reglas.cuantasRepartimos());
            this.jugadores.add(bot);
        }
        return this.jugadores;
    }





    public void agregarJugada(Jugada nuevaJugada) {
        this.jugadasEnMesa.agregarJugada(nuevaJugada);
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
        Jugada jugadaObj = this.jugadasEnMesa.get(indiceJugada);
        return jugadaObj.anyadirCarta(carta);
    }

    @Override
    public String toString() {
        return this.jugadasEnMesa.toString();
    }
}







