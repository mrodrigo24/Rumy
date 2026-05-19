
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

// O también puedes usar: import java.util.*;

public class Mesa {
    // Ahora ya no te dará error aquí:
    private List<List<Carta>> jugadasEnMesa;
    private List<Carta> mazoDescarte;

    // Tu constructor para inicializar las listas
    public Mesa() {
        this.jugadasEnMesa = new ArrayList<>();
        this.mazoDescarte=new ArrayList<>();
    }

    public void agregarJugada(List <Carta> jugadaTemporal){
        List<Carta> copiaJugada = new ArrayList<>(jugadaTemporal);
        this.jugadasEnMesa.add(copiaJugada);
    }

    public Carta robarDelMazo(List <Carta> mazoDescarte){
        if (!mazoDescarte.isEmpty()){
            return mazoDescarte.removeLast();
        }
        return null;
    }

    public void tirarAlDescarte(Carta crt){
        mazoDescarte.add(crt);
    }


    public String toString() {

        // Usamos StringBuilder para ir pegando los textos de forma eficiente
        StringBuilder sb = new StringBuilder();
        sb.append("=== JUGADAS EN LA MESA ===\n");

        int numeroJugada = 1;
        // Bucle 1: Recorremos cada grupo/escalera que hay en la mesa
        for (List<Carta> jugada : jugadasEnMesa) {
            sb.append("Jugada ").append(numeroJugada).append(": ");

            // Bucle 2: Recorremos las cartas de esa jugada específica
            for (Carta carta : jugada) {
                sb.append(carta.toString()).append(" ");
            }

            sb.append("\n"); // Salto de línea para la siguiente jugada
            numeroJugada++;
        }
        sb.append("==========================");

        return sb.toString(); // Devolvemos el texto completo ya montado
    }
}





