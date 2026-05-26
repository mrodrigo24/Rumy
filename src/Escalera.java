import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Escalera extends Jugada {

    public Escalera(List<Carta> cartasIniciales) {
        super(cartasIniciales);
    }

    public boolean validarJugada() {
        // primero miramos el minimo
        if (listaRecibida.size() < 3) {
            return false;
        }
        Palos paloReferencia = null;
        List<Integer> numerosEnteros = new ArrayList<>();
        int contadorDeComodines = 0;
        //ordenamos segun el interface Comparable de Cartas
        Collections.sort(listaRecibida);

        // Cogemos el pirmer palo
        for (Carta c : listaRecibida) {
            if (c.getSimbolo() != Simbolo.COMODIN) {
                paloReferencia = c.getPalo();
                break;
            }
        }

        for (Carta c : listaRecibida) {
            if (c.getSimbolo() == Simbolo.COMODIN) {
                contadorDeComodines++;
            } else {
                numerosEnteros.add(c.getSimbolo().getOrdenEscalera());
            }

        }
        for (Carta c : listaRecibida) {
            if (c.getSimbolo() != Simbolo.COMODIN && !c.getPalo().equals(paloReferencia)) {
                return false; // si una carta tiene otro palo no es escalera
            }
        }


        //Empezmos a comparar desde la segunda carta
        for (int i = 0; i < numerosEnteros.size() - 1; i++) {
            int actual = numerosEnteros.get(i);
            int siguiente = numerosEnteros.get(i + 1);

            int distancia = siguiente - actual;

            if (distancia <= 0) {
                return false;
            }
            int huecosNecesarios = distancia - 1;
            contadorDeComodines -= huecosNecesarios;

            if (contadorDeComodines < 0) {
                return false;
            }

        }
        if (numerosEnteros.isEmpty()) {
            return false;
        }
        if (numerosEnteros.get(0) - contadorDeComodines < 1) {
            return false;
        }
        if (numerosEnteros.get(numerosEnteros.size() - 1) + contadorDeComodines > 13) {
            return false;
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
        int sumaTotal = 0;
        // Ordenamos las cartas primero para que coincidan con la lógica de la escalera
        Collections.sort(listaRecibida);

        int contadorComodines = 0;
        int valorPrimeraCartaReal = 0;

        for (Carta c : listaRecibida) {
            if (c.getSimbolo() == Simbolo.COMODIN) {
                contadorComodines++;
            } else {
                valorPrimeraCartaReal = c.getSimbolo().getValorNumerico();
                break;
            }
        }

        // Calculamos el valor numérico ideal de la primera carta (restando los comodines que van delante)
        int inicio = valorPrimeraCartaReal - contadorComodines;

        // Sumamos de manera consecutiva según el tamaño de la escalera
        for (int i = 0; i < listaRecibida.size(); i++) {
            sumaTotal += (inicio + i);
        }

        return sumaTotal;
    }
}





