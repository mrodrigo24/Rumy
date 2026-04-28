import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Jugador {
    private String nombre;
    private int puntos;
    private List<Carta> mano = new ArrayList<>();


    public Jugador(String nombre){
        this.nombre=nombre;
    }
    public void recibirCarta(Carta c){
        mano.add(c);
    }

    public void imprimir(){
        Iterator<Carta> it = mano.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }


}
