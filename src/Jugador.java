import java.io.Serializable;
import java.util.*;
public class Jugador implements Serializable {
    private int numeroDeJugador;
    private int puntos;
    private List<Carta> cartasPorJugador;//La mano de cada jugador
    private List<Carta> backupCartasPorJugador;
    private boolean haSalido=false;

    public Jugador (int numeroDeJugador){
         this.numeroDeJugador=numeroDeJugador;
         this.puntos=0;
         this.cartasPorJugador=new ArrayList<Carta>();
    }

    public void repartir(Mazo mazoDelJuego, int cantidadCartas){
            for (int j = 0; j < cantidadCartas; j++) {
                recibirCartas(mazoDelJuego.cogerCarta());
            }
    }

    public void recibirCartas(Carta carta){
            cartasPorJugador.add(carta);
    }


    public void setHasalido(boolean haSalido){
        this.haSalido=haSalido;
    }

    //polimorfismo
    public Carta elegirCarta(int numero, Visualizador visual) {
        // Buscamos la carta
        Carta cartaSeleccionada = visual.getOpciones().get(numero);

        // Controlamos que cartatiene alfo
        if (cartaSeleccionada == null) {
            return null;
        }

        // borramos la carta
        this.cartasPorJugador.remove(cartaSeleccionada);

        // la ponemos en la jugada
        return cartaSeleccionada;
    }
    public Carta elegirCarta(int indice) {

        if (indice >= 0 && indice < cartasPorJugador.size()) {
            return cartasPorJugador.remove(indice);
        }
        return null;
    }

    public List  sacarCartas(List <Carta> listaMazo,Visualizador visual) {
        int numero = -1;
        visual.mostarNumeroDescarte(this);
        while (numero != 0) {
            System.out.println("\n-------------------------------------------");
            System.out.println("Introduce el numero, cero para salir");
             numero=LectorTeclado.leerEnteroEnRango(0,cartasPorJugador.size());
            if (numero == 0) {
                break;
            }
            Carta seleccionada = elegirCarta(numero, visual);
            if (seleccionada != null) {
                listaMazo.add(seleccionada);
                System.out.println("Has añadido: " + seleccionada);
                //visual.mostarNumeroDescarte(this);
            } else {
               System.out.println("¡Error! El número " + numero + " no corresponde a ninguna carta.");
            }
        }
        return listaMazo;
    }

    public List<Carta> hacerBackupmanoJugador(){
        this.backupCartasPorJugador=new ArrayList<>(cartasPorJugador);
        return this.backupCartasPorJugador;
    }

    public void restaurarmano(){
        if(this.backupCartasPorJugador!=null){
            this.cartasPorJugador=new ArrayList<>(backupCartasPorJugador);
        }
    }

    public boolean alguienHaGanado(List <Carta> cartasPorJugador){
        if (!cartasPorJugador.isEmpty()){
            return false;
        }
        return true;
    }

    public void eliminarCartasDelaMano(List<Carta> ListaTemporal) {

        cartasPorJugador.removeAll(ListaTemporal);

    }

    public boolean isHaSalido(){
        return this.haSalido;
    }
    public List<Carta> getCartasPorJugador() {
        return cartasPorJugador;
    }
    public void volverLaCartaAlMazodeJugador(Carta crt) {
        this.cartasPorJugador.add(crt);
    }
    public String toString() {
        return "Jugador " + numeroDeJugador ;
    }


}
