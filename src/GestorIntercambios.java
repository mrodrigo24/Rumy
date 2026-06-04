import java.util.List;

public class GestorIntercambios {


    public void solicitarYProcesarIntercambio(Jugador jugador, Mesa mesa) {
        // Conseguimos el acceso al nuevo contenedor inteligente de jugadas
        JugadasEnMesa jugadas = mesa.getJugadasEnMesa();

        // Si no hay jugadas en la mesa, cancelamos inmediatamente
        if (jugadas.size() == 0) {
            System.out.println("No hay jugadas en la mesa.");
            return;
        }

        // Preguntamos por la carta de la mano del jugador
        System.out.println("¿Qué número de carta de tu mano quieres ofrecer para el intercambio?");
        int numeroMano = LectorTeclado.leerEnteroEnRango(1, jugador.getCartasPorJugador().size());

        // Extraemos temporalmente la carta de su mano (pone un null en su lugar)
        Carta cartaOfrecida = jugador.elegirCarta(numeroMano);
        if (cartaOfrecida == null) {
            System.out.println("Error: Esa carta ya no está disponible.");
            return;
        }

        // Preguntamos por la jugada de la mesa
        System.out.println("¿A qué número de jugada de la mesa quieres dirigir el intercambio?");
        int numeroJugadaMesa = LectorTeclado.leerEnteroEnRango(1, jugadas.size());
        int indiceJugada = numeroJugadaMesa - 1; // Ajustamos al índice base 0 de las listas

        // Preguntamos qué carta de esa jugada se quiere llevar
        Jugada jugadaSeleccionada = jugadas.get(indiceJugada);
        System.out.println("Cartas disponibles en la Jugada " + numeroJugadaMesa + ":");
        List<Carta> cartasEnJugada = jugadaSeleccionada.getListaRecibida();
        for (int i = 0; i < cartasEnJugada.size(); i++) {
            System.out.println((i + 1) + " - " + cartasEnJugada.get(i));
        }

        System.out.println("¿Qué número de carta de la mesa te quieres llevar a tu mano?");
        int numeroCartaMesa = LectorTeclado.leerEnteroEnRango(1, cartasEnJugada.size());
        int indiceCartaMesa = numeroCartaMesa - 1;

        // LLAMADA AL TABLERO: Procesamos el intercambio real con las 3 Reglas de Oro
        Carta cartaRescatada = jugadas.intercambiarCarta(indiceJugada, cartaOfrecida, indiceCartaMesa);

        if (cartaRescatada != null) {
            // ÉXITO: El intercambio fue legal. Guardamos la carta de la mesa en la mano del jugador
            jugador.volverLaCartaAlMazodeJugador(cartaRescatada);
            System.out.println("¡Perfecto! Has recibido " + cartaRescatada + " en tu mano.");
        } else {
            // FRACASO: El movimiento rompió las reglas. Devolvemos la carta ofrecida a la mano del jugador
            jugador.volverLaCartaAlMazodeJugador(cartaOfrecida);
            System.out.println("El trato se ha cancelado. Tu carta regresa a tu mano.");
        }

        }
}