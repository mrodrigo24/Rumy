import java.util.*;

public class Juego {
    public static void main(String[] args) {
        int numero=-1;
        Mazo maz = new Mazo();
        int sumaPuntos=0;
        List<Carta>   descarte = new ArrayList<>();
        List<Jugador> jugadores = new ArrayList<>();
        List<Carta>   jugadaTemporal=new ArrayList<>();
        Scanner scan = new Scanner(System.in);
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
            //while (turno<=4){
                descarte.add(maz.cogerCarta());
                //System.out.println(turno);
                //jugadores.get(turno).mostrarMano();
                jugadores.get(turno).recogerDescarte(descarte);
                jugadores.get(turno).mostrarMano();
               // jugadores.get(turno).tirarcartas(descarte);

                while (numero!=0){
                    System.out.println("Introduce el numero, cero para salir");
                    numero= scan.nextInt();
                    if (numero!=0){
                        Carta seleccionada=jugadores.get(turno).elegirCartas(numero);
                        if (seleccionada!=null){
                            jugadaTemporal.add(seleccionada);
                        }

                    }

                }
        Collections.sort(jugadaTemporal);
        Iterator it = jugadaTemporal.iterator();

        while(it.hasNext()){
            //Collections.sort(jugadaTemporal);
            System.out.println("Has elegido"+it.next());
        }
                //    turno = (turno+1)%4;
            }
    }

