
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;


public class Mesa {

    private List<List<Carta>> jugadasEnMesa;
    private List<Carta> mazoDescarte;


    public Mesa() {
        this.jugadasEnMesa = new ArrayList<>();
        this.mazoDescarte = new ArrayList<>();
    }

    public List<List<Carta>> getJugadasEnMesa() {
        return jugadasEnMesa;
    }

    public void agregarJugada(List<Carta> jugadaTemporal) {
        List<Carta> copiaJugada = new ArrayList<>(jugadaTemporal);
        this.jugadasEnMesa.add(copiaJugada);
    }

    public Carta robarDelMazo(List<Carta> mazoDescarte) {
        if (!mazoDescarte.isEmpty()) {
            return mazoDescarte.removeLast();
        }
        return null;
    }

    public void tirarAlDescarte(Carta crt) {
        mazoDescarte.add(crt);
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("=== JUGADAS EN LA MESA ===\n");

        int numeroJugada = 1;
        // cada grupo o escalera
        for (List<Carta> jugada : jugadasEnMesa) {
            sb.append("Jugada ").append(numeroJugada).append(": ");

            // recorremos esa jugada
            for (Carta carta : jugada) {
                sb.append(carta.toString()).append(" ");
            }

            sb.append("\n");
            numeroJugada++;
        }
        sb.append("==========================");
        return sb.toString(); // texto
    }

    public void anyadirCartaAJugada(int indiceJugada, Carta carta) {
          jugadasEnMesa.get(indiceJugada).add(carta);
    }

        public void imprimirMesa (List < List < Carta >> jugadasEnMesa) {

            for (List<Carta> jugada : jugadasEnMesa) {
                for (Carta carta : jugada) {
                    System.out.print(carta + " ");
                }
                System.out.println();
            }
        }


}







