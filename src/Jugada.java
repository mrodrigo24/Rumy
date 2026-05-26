import java.util.List;

public abstract class Jugada {
    protected List<Carta> listaRecibida;

    // Constructor: obliga a que cualquier jugada nazca con sus cartas iniciales
    public Jugada(List<Carta> cartasIniciales) {
        this.listaRecibida = cartasIniciales;
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
        return sb.toString().trim(); // El .trim() quita el espacio en blanco del final
    }

}