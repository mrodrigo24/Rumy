import java.util.List;

public class ValidadorRummy {

    public boolean comprobar(List<Carta> jugadaTemporal, Jugador jugadorActual, ReglasJuego reglas) {
        //Intentamos tratar la jugada como un Grupo
        Jugada jugadaPropuesta = JugadaGoes.crearJugada(jugadaTemporal);
          if (!jugadaPropuesta.validarJugada()) {
                return false;
            }


        // Validamos la condición de salida si el jugador aún no ha salido.
        if (!jugadorActual.isHaSalido()) {
            int puntosDeLaJugada = jugadaPropuesta.calcularPuntos();

            if (puntosDeLaJugada >= reglas.getPUNTOS_MINIMOS_SALIDA()) {
                jugadorActual.setHasalido(true);
                System.out.println("Jugada válida. Has salido con " + puntosDeLaJugada + " puntos.");
                return true;
            } else {
                System.out.println("Puntos insuficientes para salir. Llevas: " + puntosDeLaJugada);
                return false;
            }
        }

        // Si el jugador ya había salido previamente, cualquier grupo/escalera válido es aceptado
        return true;
    }
}