import org.w3c.dom.ls.LSOutput;
import java.util.*;

public class Juego {
    public static void main(String[] args) {
        int numero=-1;
        Mazo maz = new Mazo();

        List<Carta>   descarte = new ArrayList<>();
        List<Jugador> jugadores = new ArrayList<>();
        List<Carta>   jugadaTemporal=new ArrayList<>();
        ValidadorRummy valRumy=new ValidadorRummy();
        //Scanner scan = new Scanner(System.in);
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

                Jugador jugadorActual=jugadores.get(turno);
                descarte.add(maz.cogerCarta());
                jugadores.get(turno).recogerDescarte(descarte);
                jugadores.get(turno).mostrarMano();

                valRumy.comprobar(jugadaTemporal,jugadorActual);
                System.out.println("El valor son siete"+valRumy.sumaPuntos(jugadaTemporal));

                //    turno = (turno+1)%4;
            }
    }

