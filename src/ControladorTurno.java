import java.util.List;

public class ControladorTurno {
    private Visualizador visual;

    public ControladorTurno(Visualizador visual) {
        this.visual = visual;
    }

    /**
     * Gestiona de forma interactiva la fase de robo del jugador al inicio de su turno.
     */
    public void deDondeRobar(Jugador jugador, Mazo mazoPrincipal, MazoDescarte mazoDescarte) {
        // 1. Si el descarte está vacío, se le obliga a robar del mazo principal
        if (mazoDescarte.estaVacio()) {
            System.out.println("El mazo de descarte está vacío. Robas del mazo principal.");
            jugador.recibirCartas(mazoPrincipal.cogerCarta());
            return;
        }

        // 2. Si hay cartas, se le muestra la última y se le da a elegir
        System.out.println("Última carta en el descarte: " + mazoDescarte.verUltimaCarta());
        System.out.println("¿De dónde quieres robar? \n1 - Mazo Principal (Oculta) \n2 - Mazo de Descarte");
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

    /**
     * Gestiona el proceso interactivo de selección de una carta para descartar al final del turno.
     */
    public Carta hacerDescarte(Jugador jugador) {
        Carta cartaTirada = null;
        while (cartaTirada == null) {
            System.out.println("¿Qué número de carta quieres descartar?");
            int numero = LectorTeclado.leerEntero();
            cartaTirada = jugador.elegirCarta(numero);

            if (cartaTirada == null) {
                System.out.println("¡Error! Ese número no corresponde a ninguna carta válida de tu mano. Intenta nuevamente.");
            }
        }
        return cartaTirada;
    }

    /**
     * Gestiona la lógica interactiva para acomodar una carta de la mano en una jugada existente de la mesa.
     */
    public void seleccionarCartaParaMesa(Jugador jugador, Mesa mesa) {
        System.out.println("¿Qué carta quieres agregar a la mesa?");
        int numeroCarta = LectorTeclado.leerEntero();
        Carta cartaSeleccionada = jugador.elegirCarta(numeroCarta);

        if (cartaSeleccionada != null) {
            System.out.println("¿Número de jugada de la mesa para anyadir?");
            int numJugada = LectorTeclado.leerEntero();
            int indiceMesa = numJugada - 1;

            // Validamos que el índice de la jugada elegida exista en la mesa
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