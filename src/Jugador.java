import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Jugador {
    private String numeroDeJugador;
    private int puntos;
    List<Carta> cartasPorJugador;
    private boolean haSalido=false;

    public Jugador (String numeroDeJugador){
     this.numeroDeJugador=numeroDeJugador;
     this.puntos=0;
     this.cartasPorJugador=new ArrayList<Carta>();
}
    public void recibirCartas(Carta carta){
            cartasPorJugador.add(carta);
    }

    public void mostrarMano() {
        int numeroDeCartaDeljugador=0;
        System.out.println("Mano de " + numeroDeJugador + ":");
        Iterator<Carta> it = cartasPorJugador.iterator();
        while (it.hasNext()) {
            numeroDeCartaDeljugador++;
            Carta c = it.next();
            System.out.println(numeroDeCartaDeljugador + "- " + c);
        }
        System.out.println();
    }

    public void recogerDescarte(List<Carta> listacomun){
            if(!listacomun.isEmpty()){
                Carta c=listacomun.removeFirst();
                this.cartasPorJugador.add(c);
                System.out.println(c);
            }
    }

    public void setHasalido(boolean haSalido){
        this.haSalido=haSalido;
    }

    /*public void elegirCartas(Carta cartaElegida){
        Iterator<Carta> it = cartasPorJugador.iterator();
        List <Carta>manoDelJugador=new ArrayList<>();
        while(it.hasNext()){
            Carta c=it.next();
                manoDelJugador.add(c);
                it.remove();

        }

    }
*/

   /* public void tirarCartas(List <Carta> listaJugador){
        System.out.println("Que cartas quieres tirar");
        Scanner sc = new Scanner(System.in);

        if(!listaJugador.isEmpty()){

            this.cartasPorJugador.remove(c);
            System.out.println(c);
        }
    }*/


}
