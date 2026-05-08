import java.util.*;

public class Jugador {
    private String numeroDeJugador;
    private int puntos;
    List<Carta> cartasPorJugador;
    Map<Integer, Carta> opciones = new HashMap<>();
    private boolean haSalido=false;
    Scanner scan = new Scanner(System.in);
    int numero = -1;
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
            opciones.put(numeroDeCartaDeljugador,c);
            System.out.println(numeroDeCartaDeljugador + "- " + c);
        }
        System.out.println();
    }

    public void recogerDescarte(List<Carta> listacomun){
            if(!listacomun.isEmpty()){
                Carta c=listacomun.removeFirst();
                this.cartasPorJugador.add(c);

            }
    }

    public void setHasalido(boolean haSalido){
        this.haSalido=haSalido;
    }

    public Carta elegirCartas(int numero){
        Iterator<Carta> it = cartasPorJugador.iterator();
        Carta cartaExtraida = null;
        Carta objetivo=opciones.get(numero);

        while(it.hasNext()){
           Carta c=it.next();
           if(c.equals(objetivo)){
               cartaExtraida=c;
               it.remove();
               break;

           }
                   }

        return cartaExtraida;
    }


    public Carta elegirNumero(int numero){
        while (numero != 0) {
            System.out.println("Introduce el numero, cero para salir");
            numero = scan.nextInt();
            Carta seleccionada= elegirCartas(numero);
            return seleccionada;

    }

    public boolean alguienHaGanado(List <Carta> cartasPorJugador){
        if (!cartasPorJugador.isEmpty()){
            return false;
        }
        return true;
    }

    public List<Carta> getCartasPorJugador() {
        return cartasPorJugador;
    }

    public void volverLaCartaAlMazodeJugador(Carta crt) {
        this.cartasPorJugador.add(crt);
    }
}
