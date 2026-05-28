import java.util.ArrayList;
import java.util.List;

public class JugadaGoes {
    public static Jugada crearJugada(List<Carta> jugadaTemporal) {
        Jugada nuevaJugada = new Grupo(new ArrayList<>(jugadaTemporal));
        if (!nuevaJugada.validarJugada()) {
            nuevaJugada = new Escalera(new ArrayList<>(jugadaTemporal));
        }
        return nuevaJugada;
    }
}
