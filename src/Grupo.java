import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Grupo extends Jugada implements Serializable {

    public Grupo(List<Carta> cartasIniciales) {
        super(cartasIniciales);
    }

    public boolean validarJugada() {
        if (listaRecibida.size() < 3) return false;
        List<Palos> palosYaVistos = new ArrayList<>();
        int numeroReferencia=-1;//primeraCarta no comodin

        for (Carta c : listaRecibida) {
            if (c.getSimbolo() != Simbolo.COMODIN) {
                numeroReferencia = c.getSimbolo().getValorNumerico();
                break;
            }
        }
        if (numeroReferencia == -1) return false;
        for (Carta actual : listaRecibida) {
            if (actual.getSimbolo() == Simbolo.COMODIN) {
                continue;
            }
            // 1. ver si el palo ha salico
            if (palosYaVistos.contains(actual.getPalo())) {
                return false;
            }
            palosYaVistos.add(actual.getPalo());

            // 2. vr que todas tienen = numero
            if (actual.getSimbolo().getValorNumerico() != numeroReferencia) {
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean anyadirCarta(Carta crt) {
        listaRecibida.add(crt);
        if (!validarJugada()) {
            listaRecibida.remove(crt);
            return false;
        }
        return true;
    }

    @Override
    public int calcularPuntos() {
        int valorGrupo = 0;
        for (Carta c : listaRecibida) {
            if (c.getSimbolo() != Simbolo.COMODIN) {
                valorGrupo = c.getSimbolo().getValorNumerico();
                break;
            }
        }
        return valorGrupo * listaRecibida.size();
    }
}
