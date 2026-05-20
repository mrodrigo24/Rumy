import java.util.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ValidadorRummy {

    public int sumaPuntos(List<Carta> listaRecibida) {
        int sumaPuntos = 0;
        Collections.sort(listaRecibida);
        Iterator<Carta> it = listaRecibida.iterator();
        while (it.hasNext()) {
            Carta actual = it.next();
            sumaPuntos += actual.getSimbolo().getValorNumerico();
        }

        System.out.println(sumaPuntos);
        System.out.println("Ha pasado 2");
        return sumaPuntos;
    }

    public boolean Grupos(List<Carta> listaRecibida) {
        if (listaRecibida.size() < 3) return false;

        List<Palos> palosYaVistos = new ArrayList<>();
        int numeroReferencia = listaRecibida.get(0).getSimbolo().getValorNumerico();

        for (Carta actual : listaRecibida) {
            // 1. Verificar si el palo ya ha salido (Regla de palos distintos)
            if (palosYaVistos.contains(actual.getPalo())) {
                return false;
            }
            palosYaVistos.add(actual.getPalo());

            // 2. Verificar que todas las cartas tengan el mismo número
            if (actual.getSimbolo().getValorNumerico() != numeroReferencia) {
                return false;
            }
        }
        return true;
    }

    public boolean Escaleras(List<Carta> listaRecibida) {
        // 1. El mínimo siempre primero
        if (listaRecibida.size() < 3) {
            return false;
        }

        // 2. Ordenar es vital para que n, n+1, n+2 funcione
        Collections.sort(listaRecibida);

        // 3. Tomamos la referencia del primer palo
        Palos paloReferencia = listaRecibida.get(0).getPalo();

        // 4. Empezamos a comparar desde la segunda carta (índice 1)
        for (int i = 1; i < listaRecibida.size(); i++) {
            Carta actual = listaRecibida.get(i);
            Carta anterior = listaRecibida.get(i - 1);

            // Comprobamos que el palo sea el mismo que el de la primera carta
            if (!actual.getPalo().equals(paloReferencia)) {
                return false;
            }

            // Comprobamos que el número sea exactamente uno más que el anterior
            if (actual.getValorPorCarta().getNumero() != anterior.getValorPorCarta().getNumero()+ 1) {
                return false;
            }
        }
        return true; // Si pasa todo el bucle, es una escalera perfecta
    }

    public boolean comprobar(List<Carta> jugadaTemporal, Jugador jugadorActual) {
        boolean esFormacionValida = Grupos(jugadaTemporal) || Escaleras(jugadaTemporal);

        if (!esFormacionValida) {
            return false;
        }

        if (!jugadorActual.isHaSalido()) {
            if (sumaPuntos(jugadaTemporal) >= 30) {
                jugadorActual.setHasalido(true);
                System.out.println("Ha pasado 1");
                return true;
            } else {
                System.out.println(sumaPuntos(jugadaTemporal));
                return false;
            }
        }
        return true;
    }
}




