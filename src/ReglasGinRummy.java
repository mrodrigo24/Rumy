import java.io.Serializable;

public class ReglasGinRummy implements ReglasJuego, Serializable {
    @Override
    public int getPUNTOS_MINIMOS_SALIDA() {
        // En la variante argentina se usan 10 puntos.
        return 0;
    }
    @Override
    public int cuantasRepartimos() {
        // En el Rummy Argentino estándar se reparten 9 cartas al inicio
        return 9;
    }


}
