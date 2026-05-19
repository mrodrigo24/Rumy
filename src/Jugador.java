import java.util.*;
public class Jugador {
    private String numeroDeJugador;
    private int puntos;
    private List<Carta> cartasPorJugador;
    private List<Carta> backupCartasPorJugador;
    Map<Integer, Carta> opciones = new HashMap<>();
    private boolean haSalido=false;
    Scanner scan = new Scanner(System.in);
    int numero = -1;
    public Jugador (String numeroDeJugador){
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

    public void mostrarMano() {
        Collections.sort(this.cartasPorJugador);
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
                Carta c=listacomun.removeFirst();
                this.cartasPorJugador.add(c);
            }
    }

    public void setHasalido(boolean haSalido){
        this.haSalido=haSalido;
    }

    public Carta elegirCarta(int numero){
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

    public List  sacarCartas(List <Carta> listaMazo) {
        while (numero != 0) {
            System.out.println("Introduce el numero, cero para salir");
            numero = scan.nextInt();
            if (numero == 0) {
                break;
            }
            Carta seleccionada = elegirCarta(numero);
            if (seleccionada != null) {
                listaMazo.add(seleccionada);
                System.out.println("Has añadido: " + seleccionada);
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
    public void deDondeRobar(List <Carta> descarte, Mazo mazo){
        if (descarte.isEmpty()) {
            System.out.println("El mazo de descarte está vacío. Robas del mazo principal automáticamente.");
            recibirCartas(mazo.cogerCarta());
            return;
        }
        System.out.println("Última carta en el descarte: " + descarte.getLast());
        System.out.println("¿De dónde quieres robar? \n1 - Mazo Principal (Oculta) \n2 - Mazo de Descarte");
        numero = scan.nextInt();
        switch (numero){
            case 1:
                recibirCartas(mazo.cogerCarta());
                break;
            case 2:
                recogerDescarte(descarte);
                break;
            default:
                System.out.println("Opción no válida. Por defecto robas del mazo principal.");
                recibirCartas(mazo.cogerCarta());
                break;
        }
    }

    public Carta hacerDescarte() {
        Carta cartaTirada = null;


        while (cartaTirada == null) {
            System.out.println("¿Qué número de carta quieres descartar?");
            numero = scan.nextInt();

            cartaTirada = elegirCarta(numero);

            if (cartaTirada == null) {
                System.out.println("¡Error! Ese número no corresponde a ninguna carta de tu mano. Inténtalo de nuevo.");
            }
        }

        return cartaTirada;
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
}
