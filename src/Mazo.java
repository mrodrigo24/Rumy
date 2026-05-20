import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo {
    private List<Carta> cartas =new ArrayList<>();
    public Mazo(int  num){
        for (int i = 0; i < num; i++) {
            inicializarMazo();
        }
        barajar();
    }

    public void inicializarMazo(){
        Valores[] arrayValores = Valores.values();
       for(Simbolo v:Simbolo.values()){
           if (v == Simbolo.COMODIN) continue;
           for (Palos p :Palos.values()) {
               if (p == Palos.COMODIN) continue;
               Valores h = arrayValores[v.ordinal()];
               cartas.add(new Carta(v, p, h));
           }
       }
        cartas.add(new Carta(Simbolo.COMODIN, Palos.COMODIN, Valores.COMODIN));
        cartas.add(new Carta(Simbolo.COMODIN, Palos.COMODIN, Valores.COMODIN));
    }

    public void barajar(){
        Collections.shuffle(cartas);
    }

    public Carta cogerCarta(){
        if (cartas.isEmpty()){
            System.out.println("¡Aviso: El mazo está vacío!");
            return null;
        }
        return cartas.removeFirst();
    }

    public boolean estaVacio() {
        return cartas.isEmpty();
    }


    @Override
    public String toString() {
        return "Mazo{" +
                "cartas=" + cartas +
                '}';
    }
}
