import java.util.List;

public class ValidadorRummy {
    private final int PUNTOS_MINIMOS_SALIDA = 10; //

    public int getPUNTOS_MINIMOS_SALIDA() {
        return PUNTOS_MINIMOS_SALIDA; //
    }

    public boolean comprobar(List<Carta> jugadaTemporal, Jugador jugadorActual) {
        // 1. Intentamos tratar la jugada como un Grupo
        Jugada jugadaPropuesta = new Grupo(jugadaTemporal);

        // Si no es un grupo válido, intentamos tratarla como una Escalera
        if (!jugadaPropuesta.validarJugada()) {
            jugadaPropuesta = new Escalera(jugadaTemporal);

            // Si tampoco es una escalera válida, la formación no sirve
            if (!jugadaPropuesta.validarJugada()) {
                return false; // [cite: 1513]
            }
        }

        // 2. Si llegamos aquí, la formación es válida (es Grupo o Escalera).
        // Validamos la condición de salida si el jugador aún no ha salido.
        if (!jugadorActual.isHaSalido()) {
            int puntosDeLaJugada = jugadaPropuesta.calcularPuntos();

            if (puntosDeLaJugada >= PUNTOS_MINIMOS_SALIDA) {
                jugadorActual.setHasalido(true);
                System.out.println("Jugada válida. Has salido con " + puntosDeLaJugada + " puntos.");
                return true;
            } else {
                System.out.println("Puntos insuficientes para salir. Llevas: " + puntosDeLaJugada); // [cite: 1520]
                return false;
            }
        }

        // Si el jugador ya había salido previamente, cualquier grupo/escalera válido es aceptado
        return true;
    }
}