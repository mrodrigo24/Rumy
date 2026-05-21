import java.util.*;
import java.util.Collections;
import java.util.List;

public class ValidadorRummy {
    private final int PUNTOS_MINIMOS_SALIDA = 10;
    public int sumaPuntos(List<Carta> listaRecibida) {
        int sumaTotal = 0;
        Collections.sort(listaRecibida);

        if (Grupos(listaRecibida)){
            int valorGrupo=0;
            for(Carta c: listaRecibida){
                if(c.getSimbolo()!=Simbolo.COMODIN){
                    valorGrupo=c.getSimbolo().getValorNumerico();
                    break;
                }
            }
            sumaTotal=valorGrupo* listaRecibida.size();
        } else {
            //Es Escalera
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
            int inicio = valorPrimeraCartaReal - contadorComodines;
            for (int i = 0; i < listaRecibida.size(); i++) {
                sumaTotal += (inicio + i);
            }
        }
        return sumaTotal;
        }





    public boolean Grupos(List<Carta> listaRecibida) {
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

    public boolean Escaleras(List<Carta> listaRecibida) {

        // 1. El mínimo siempre primero
        if (listaRecibida.size() < 3) {
            return false;
        }
        Palos paloReferencia = null;
        List<Integer> numerosEnteros=new ArrayList<>();
        int contadorDeComodines=0;
        // 2. Ordenar es vital para que n, n+1, n+2 funcione
        Collections.sort(listaRecibida);

        // 3. Tomamos la referencia del primer palo
        for (Carta c : listaRecibida) {
            if (c.getSimbolo() != Simbolo.COMODIN) {
                paloReferencia = c.getPalo();
                break; // ¡Encontrado! Salimos del bucle inmediatamente
            }
        }

        for (Carta c : listaRecibida){
            if(c.getSimbolo()==Simbolo.COMODIN){
                contadorDeComodines++;
            } else{
                numerosEnteros.add(c.getValorPorCarta().getNumero());
            }

        }
        for (Carta c : listaRecibida) {
            if (c.getSimbolo() != Simbolo.COMODIN && !c.getPalo().equals(paloReferencia)) {
                return false; // Si una carta real tiene otro palo, no es escalera
            }
        }


        // 4. Empezamos a comparar desde la segunda carta (índice 1)
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

    public boolean comprobar(List<Carta> jugadaTemporal, Jugador jugadorActual) {
        boolean esFormacionValida = Grupos(jugadaTemporal) || Escaleras(jugadaTemporal);

        if (!esFormacionValida) {
            return false;
        }

        if (!jugadorActual.isHaSalido()) {
            if (sumaPuntos(jugadaTemporal) >= PUNTOS_MINIMOS_SALIDA) {
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





