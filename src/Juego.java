import java.util.ArrayList;
import java.util.List;

public class Juego {
    public static void main(String[] args) {
        Mazo maz=new Mazo();
        List<Jugador> jugadores=new ArrayList<>();

        for (int i = 0; i <4 ; i++) {
            jugadores.add(new Jugador("Juan"));
        }


        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j <10 ; j++) {
                jugadores.get(i).recibirCarta(maz.cogerCarta());
            }

        }

        for(Jugador jugador : jugadores){
            jugador.imprimir();
        }

    }
}