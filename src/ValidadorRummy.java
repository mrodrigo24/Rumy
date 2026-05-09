import java.util.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ValidadorRummy {

    int sumaPuntos = 0;

    public int sumaPuntos(List<Carta> listaRecibida) {
        Collections.sort(listaRecibida);
        Iterator<Carta> it = listaRecibida.iterator();
        while (it.hasNext()) {
            Carta actual = it.next();
            sumaPuntos += actual.getValor().getNumero();
        }
       return sumaPuntos;
    }

    public boolean Grupos(List<Carta> listaRecibida) {
        Iterator<Carta> it = listaRecibida.iterator();
        List<Palos> palosYaVistos = new ArrayList<>();
        Palos ultimoPalo = null;
        int contador = 0;
        if (listaRecibida.size() < 3) {
            return false;
        }
        int numeroReferencia = listaRecibida.get(0).getValor().getNumero();
        while (it.hasNext()) {
            Carta actual = it.next();
            if (palosYaVistos.contains(actual.getPalo())) {
                return false;
            } else {
                palosYaVistos.add(actual.getPalo());
            }

            if (actual.getValor().getNumero() != numeroReferencia) {
                return false;
            }
            Palos paloActual = actual.getPalo();
            if (!paloActual.equals(ultimoPalo)) {
                contador++;
            }
            ultimoPalo = paloActual;
        }
        return true;
    }


    public boolean Escaleras(List<Carta> listaRecibida) {
        Carta anterior = null;
        Collections.sort(listaRecibida);
        if (listaRecibida.size() < 3) {
            return false;
        }
        Iterator<Carta> it = listaRecibida.iterator();
        Palos paloReferencia = listaRecibida.get(0).getPalo();

            while (it.hasNext()) {
                Carta actual = it.next();
                if (anterior!=null){
                    if (actual.getPalo() != paloReferencia) {
                        return false;
                    }

                }
                if(anterior!=null){
                    if (actual.getValor().getNumero() != anterior.getValor().getNumero() + 1) {
                        return false;
                }

                }
                anterior=actual;
            }




        return true;
    }
    //integrar ahora estos dos métodos en tu función comprobar para ver si el jugador puede finalmente bajar sus 30 puntos

}




