import org.w3c.dom.ls.LSOutput;
import java.util.*;
public class Juego {
    public static void main(String[] args) {
        int numero = -1;
        Mazo maz = new Mazo(2);
        List<Carta> descarte = new ArrayList<>();
        List<Jugador> jugadores = new ArrayList<>();
        List<Carta> jugadaTemporal = new ArrayList<>();
        ValidadorRummy valRumy = new ValidadorRummy();
        //Scanner scan = new Scanner(System.in);
        int turno = 0;
        for (int i = 0; i < 4; i++) {
            jugadores.add(new Jugador("Jugador" + (i)));
        }
        turno = (turno + 1) % 4;
        //while(jugadores.get(turno).)
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                jugadores.get(i).recibirCartas(maz.cogerCarta());
            }
        }
        boolean alguienHaGanado = false;
        while (!alguienHaGanado) {
            Jugador jugadorActual = jugadores.get(turno);
            descarte.add(maz.cogerCarta());
            jugadores.get(turno).recogerDescarte(descarte);
            jugadores.get(turno).mostrarMano();
            jugadores.get(turno).sacarCartas(jugadaTemporal);
            jugadores.get(turno).hacerBackupmanoJugador();
            jugadores.get(turno).mostrarMano();
            jugadores.get(turno).mostrarDescarte(jugadaTemporal);
            //
            //valRumy.comprobar(jugadaTemporal,jugadorActual);
            if (jugadorActual.alguienHaGanado(jugadorActual.getCartasPorJugador())) {
                alguienHaGanado = true;
                System.out.println("¡Ganador: " + jugadorActual + "!");
            } else
                turno = (turno + 1) % 4;

            if (valRumy.sumaPuntos(jugadaTemporal) > 30) {
                System.out.println("seguimos jugando");
            } else {
                System.out.println("No es mayor de 20");
                jugadores.get(turno).restaurarmano();
                jugadores.get(turno).mostrarMano();
            }
        }
    }
}
             //    turno = (turno+1)%4;
            //}


