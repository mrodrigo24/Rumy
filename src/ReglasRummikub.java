public class ReglasRummikub implements ReglasJuego{
    @Override
    public int getPUNTOS_MINIMOS_SALIDA() {
        // En la variante argentina se usan 10 puntos.
        return 30;
    }
    @Override
    public int cuantasRepartimos() {
        // En el Rummy Argentino estándar se reparten 9 cartas al inicio
        return 14;
    }

}
