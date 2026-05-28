import java.util.*;
public class Jugador {
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

    public void repartir(Mazo mazoDelJuego){
            for (int j = 0; j < 10; j++) {
                recibirCartas(mazoDelJuego.cogerCarta());
            }
    }

    public void recibirCartas(Carta carta){
            cartasPorJugador.add(carta);
    }

    public void mostrarDescarte(List<Carta> listadescarte) {
        if (listadescarte.isEmpty()) {
            System.out.println("El mazo de descarte está vacío.");
        } else {
            System.out.println("Descarte visto por " + numeroDeJugador + ":");
            int contador = 1;
            Iterator<Carta> it = listadescarte.iterator();

            while (it.hasNext()) {
                Carta c = it.next();
                System.out.println(contador + "- " + c);
                contador++;
            }
        }
    }

    public void recogerDescarte(List<Carta> listacomun){
            if(!listacomun.isEmpty()){
                Carta c=listacomun.removeLast();
                this.cartasPorJugador.add(c);
            }
    }

    public void setHasalido(boolean haSalido){
        this.haSalido=haSalido;
    }


    public Carta elegirCarta(int numero) {
        Carta cartaExtraida = cartasPorJugador.get(numero - 1);
        cartasPorJugador.set(numero - 1, null);
        return cartaExtraida;

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
            Carta seleccionada = elegirCarta(numero);
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

    private void validarColocacionEnMesa(Carta carta, Mesa mesa) {
        System.out.println("¿numero de jugada de la mesa para anyadir?");
        int numJugada = LectorTeclado.leerEntero();
        int indiceMesa = numJugada - 1;

        if (indiceMesa >= 0 && indiceMesa < mesa.getJugadasEnMesa().size()) {
            // Intentamos añadir la carta a la mesa. El propio método valida internamente
            if (mesa.anyadirCartaAJugada(indiceMesa, carta)) {
                System.out.println("ok Carta anyadida a la jugada " + numJugada + ".");
            } else {
                System.out.println("Movimiento no valido. carta regresa a mano.");
                volverLaCartaAlMazodeJugador(carta);
            }
        } else {
            System.out.println("carta no existe en mesa, regresa a mano.");
            volverLaCartaAlMazodeJugador(carta);
        }
    }
    public void eliminarCartasDelaMano(List<Carta> ListaTemporal) {
        List<Carta> sacoDeNulls = new ArrayList<>();
        cartasPorJugador.removeAll(ListaTemporal);
        sacoDeNulls.add(null);
        cartasPorJugador.removeAll(sacoDeNulls);
    }
    public void limpiarVaciosDeLaMano() {
        List<Carta> sacoDeNulls = new ArrayList<>();
        sacoDeNulls.add(null);
        this.cartasPorJugador.removeAll(sacoDeNulls);
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
