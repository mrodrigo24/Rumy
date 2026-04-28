public class Juego {
    public static void main(String[] args) {
        Mazo maz=new Mazo();
        Carta carta1=maz.cogerCarta();
        Carta carta2=maz.cogerCarta();
        System.out.println(carta1);
        System.out.println(carta2);
        System.out.println(maz.cuantasCartasQuedan());
        Jugador jugadorActual=new Jugador("Pedro");

    }
}