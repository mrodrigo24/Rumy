import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo implements Serializable {
    private final List<Carta> cartas =new ArrayList<>();
    public Mazo(int  num){
        for (int i = 0; i < num; i++) {
            inicializarMazo();
        }
        barajar();
    }

    public void inicializarMazo(){

       for(Simbolo v : Simbolo.values()){
           if (v == Simbolo.COMODIN) continue;
           for (Palos p :Palos.values()) {
               if (p == Palos.COMODIN) continue;
               cartas.add(new Carta(v, p));
           }
       }
        cartas.add(new Carta(Simbolo.COMODIN, Palos.COMODIN));
        cartas.add(new Carta(Simbolo.COMODIN, Palos.COMODIN));
    }

    public void barajar(){
        Collections.shuffle(cartas);
    }

    public Carta cogerCarta(){
        if (cartas.isEmpty()){
            System.out.println("¡Mazo vacio!");
            return null;
        }
        return cartas.removeFirst();
    }

    @Override
    public String toString() {
        return "Mazo{" + "cartas=" + cartas + '}';
    }
}
