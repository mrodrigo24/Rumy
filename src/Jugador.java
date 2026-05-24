import java.util.*;
public class Jugador {
    private String numeroDeJugador;
    private int puntos;
    private List<Carta> cartasPorJugador;//La mano de cada jugador
    private List<Carta> backupCartasPorJugador;
    Map<Integer, Carta> opciones = new HashMap<>();
    private boolean haSalido=false;
    Scanner scan = new Scanner(System.in);
    //int numero = -1;
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
                Carta c=listacomun.removeLast();
                this.cartasPorJugador.add(c);
            }
    }

    public void setHasalido(boolean haSalido){
        this.haSalido=haSalido;
    }

    /*public Carta elegirCarta(int numero){
        Iterator<Carta> it = cartasPorJugador.iterator();
        Carta cartaExtraida = null;
        Carta objetivo=opciones.get(numero-1);
        while(it.hasNext()){
           Carta c=it.next();
           if(c.equals(objetivo)){
               cartaExtraida=c;
               //it.remove();
               break;
           }
                   }
        return cartaExtraida;
    }*/
    public Carta elegirCarta(int numero) {
        Carta cartaExtraida = cartasPorJugador.get(numero - 1);
        cartasPorJugador.set(numero - 1, null);
        return cartaExtraida;

    }


    public List  sacarCartas(List <Carta> listaMazo) {
        int numero = -1;
        while (numero != 0) {
            System.out.println("Introduce el numero, cero para salir");
             numero=LectorTeclado.leerEnteroEnRango(0,cartasPorJugador.size());
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
    public void deDondeRobar(Mesa mesa, Mazo mazo){
        if (mesa.getMazoDescarte().isEmpty()) {
            System.out.println("El mazo de descarte vacío. Robas del mazo principal.");
            recibirCartas(mazo.cogerCarta());
            return;
        }
        System.out.println("Última carta en el descarte: " + mesa.getMazoDescarte().getLast());
        System.out.println("¿De dónde quieres robar? \n1 - Mazo Principal (Oculta) \n2 - Mazo de Descarte");
        int numero = scan.nextInt();
        switch (numero){
            case 1:
                recibirCartas(mazo.cogerCarta());
                break;
            case 2:
                recogerDescarte(mesa.getMazoDescarte());
                break;
            default:
                System.out.println("Robas del mazo principal.");
                recibirCartas(mazo.cogerCarta());
                break;
        }
    }

    public Carta hacerDescarte() {
        Carta cartaTirada = null;

        while (cartaTirada == null) {
            System.out.println("¿Qué numero de carta quieres descartar?");
            int numero = scan.nextInt();

            cartaTirada = elegirCarta(numero);

            if (cartaTirada == null) {
                System.out.println("¡Error! Ese numero no corresponde a ninguna carta de tu mano. nuevamente.");
            }
        }
        return cartaTirada;
    }

    public Carta seleccionarCartaParaMesa(Mesa mesa, ValidadorRummy valRumy) {
        System.out.println("que carta quieres agregar a la mesa?");
        int numeroJugada = scan.nextInt();
        Carta cartaSeleccionada = elegirCarta(numeroJugada);
        if (cartaSeleccionada != null) {
            validarColocacionEnMesa(cartaSeleccionada, mesa, valRumy);
        } else {
            System.out.println("El numero da error");
        }
        return cartaSeleccionada;
    }

    private void validarColocacionEnMesa(Carta carta, Mesa mesa, ValidadorRummy valRumy) {
        System.out.println("¿numero de jugada de la mesa para anyadir?");
        int numJugada = scan.nextInt();
        int indiceMesa = numJugada - 1;

        if (indiceMesa >= 0 && indiceMesa < mesa.getJugadasEnMesa().size()) {
            List<Carta> jugadaObjetivo = mesa.getJugadasEnMesa().get(indiceMesa);
            // Validamos si la carta encaja
            if (valRumy.comprobarAmpliacion(jugadaObjetivo, carta)) {
                System.out.println("ok Carta anyadida a la jugada " + numJugada + ".");
                mesa.anyadirCartaAJugada(indiceMesa, carta);
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
}
