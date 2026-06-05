import java.io.Serializable;
import java.util.List;

public class ControladorTurno implements Serializable {
    private Visualizador visual;

    public ControladorTurno(Visualizador visual) {
        this.visual = visual;
    }


    public void deDondeRobar(Jugador jugador, Mazo mazoPrincipal, MazoDescarte mazoDescarte) {
        //Descarte vacioMazoPrincipal
        if (mazoDescarte.estaVacio()) {
            System.out.println("Robas del mazo");
            jugador.recibirCartas(mazoPrincipal.cogerCarta());
            return;
        }

        // haycartasMostramosLaUltima
        System.out.println("Última carta en el mazoDescarte: " + mazoDescarte.verUltimaCarta());
        System.out.println("¿De dónde quieres robar? \n1 - mazoPrincipal (Oculta) \n2 - Mazo de Descarte");
        int numero = LectorTeclado.leerEnteroEnRango(1, 2);

        switch (numero) {
            case 1:
                jugador.recibirCartas(mazoPrincipal.cogerCarta());
                System.out.println("Has robado del mazo principal.");
                break;
            case 2:
                jugador.recibirCartas(mazoDescarte.tomarUltimaCarta());
                System.out.println("Has recogido la carta del descarte.");
                break;
            default:
                System.out.println("Opción no válida. Robas del mazo principal por defecto.");
                jugador.recibirCartas(mazoPrincipal.cogerCarta());
                break;
        }
    }


    public Carta hacerDescarte(Jugador jugador, Visualizador visual) {
        Carta cartaTirada = null;

        while (cartaTirada == null) {
            System.out.println("¿Qué número de carta quieres descartar?");
            int numero = LectorTeclado.leerEntero();

            cartaTirada = jugador.elegirCarta(numero, visual);

            if (cartaTirada == null) {
                System.out.println("\n¡Error! Ese número no corresponde a ninguna carta válida de tu mano.");
                System.out.println("Por favor, vuelve a mirar tu mano actualizada e intenta de nuevo:");

                visual.mostarNumeroDescarte(jugador);
            }
        }

        return cartaTirada;
    }


    public void seleccionarCartaParaMesa(Jugador jugador, Mesa mesa) {
        System.out.println("¿Qué carta quieres agregar a la mesa?");
        int numeroCarta = LectorTeclado.leerEntero();
        Carta cartaSeleccionada = jugador.elegirCarta(numeroCarta, this.visual);

        if (cartaSeleccionada != null) {
            System.out.println("¿Número de jugada de la mesa para anyadir?");
            int numJugada = LectorTeclado.leerEntero();
            int indiceMesa = numJugada - 1;

            // Validamos inidice apra sber que e3xiste
            if (indiceMesa >= 0 && indiceMesa < mesa.getJugadasEnMesa().size()) {
                if (mesa.anyadirCartaAJugada(indiceMesa, cartaSeleccionada)) {
                    System.out.println("OK. Carta anyadida a la jugada " + numJugada + ".");
                } else {
                    System.out.println("Movimiento no válido. La carta regresa a tu mano.");
                    jugador.volverLaCartaAlMazodeJugador(cartaSeleccionada);
                }
            } else {
                System.out.println("La jugada número " + numJugada + " no existe en la mesa. La carta regresa a tu mano.");
                jugador.volverLaCartaAlMazodeJugador(cartaSeleccionada);
            }
        } else {
            System.out.println("El número introducido da error o esa carta ya fue retirada.");
        }
    }
}