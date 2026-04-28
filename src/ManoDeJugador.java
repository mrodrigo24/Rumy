import java.util.ArrayList;
import java.util.List;

public class ManoDeJugador {
    private List<Carta> manoDeJugador= new ArrayList<>();
    private final int LIMITE_CARTAS = 6;

    public void recibirCarta(Mazo mazo) {
        if (!mazo.estaVacio() && manoDeJugador.size() < LIMITE_CARTAS) {
            Carta c = mazo.cogerCarta();
            manoDeJugador.add(c);
        }
    }

    @Override
    public String toString() {
        return "Mi mano actual es: " + manoDeJugador;
    }

}
