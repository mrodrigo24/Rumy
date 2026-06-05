import java.io.Serializable;
import java.util.List;

public abstract class Jugada implements Serializable {
    protected List<Carta> listaRecibida;


    public Jugada(List<Carta> cartasIniciales) {
        this.listaRecibida = cartasIniciales;
    }

    public List<Carta> getListaRecibida() {
        return listaRecibida;
    }

    public abstract boolean validarJugada();
    public abstract boolean anyadirCarta(Carta crt);
    public abstract int calcularPuntos();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Carta carta : listaRecibida) {
            sb.append(carta.toString()).append(" ");
        }
        return sb.toString().trim();
    }

}