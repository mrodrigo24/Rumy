import java.util.ArrayList;
import java.util.List;

public class Juego {
    public static void main(String[] args) {
        Mazo maz = new Mazo();
        List<Carta> descarte = new ArrayList<>();
        List<Jugador> jugadores = new ArrayList<>();
        int turno=0;
            for (int i = 0; i < 4; i++) {
                jugadores.add(new Jugador("Jugador" + (i)));
            }
            turno = (turno + 1) % 4;

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 10; j++) {
                    jugadores.get(i).recibirCartas(maz.cogerCarta());
                }
            }
            while (turno<4){
                descarte.add(maz.cogerCarta());
                System.out.println(turno);
                jugadores.get(turno).mostrarMano();
                jugadores.get(turno).recogerDescarte(descarte);

                turno = (turno+1)%4;
            }


    }
}
