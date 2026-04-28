import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo {

    public Mazo(){
        inicializarMazo();
        barajar();
    }

    private List<Carta> cartas =new ArrayList<>();
    public void inicializarMazo(){
       for(Valores v:Valores.values()){
           for (Palos p :Palos.values()){
              cartas.add(new Carta(v,p));
           }
       }
    }

    public void barajar(){
        Collections.shuffle(cartas);
    }

    public Carta cogerCarta(){
        return cartas.removeFirst();
    }

    public int cuantasCartasQuedan(){
        return cartas.size();
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
