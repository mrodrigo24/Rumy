import java.util.ArrayList;
import java.util.List;

public class JugadasEnMesa {
    private List<Jugada> listaJugadas;

    // Constructor que tú creaste
    public JugadasEnMesa() {
        this.listaJugadas = new ArrayList<>();
    }

    // Agrega una nueva jugada al tablero (Grupo o Escalera)
    public void agregarJugada(Jugada nuevaJugada) {
        this.listaJugadas.add(nuevaJugada);
    }

    // Da acceso a la lista completa de jugadas
    public List<Jugada> getListaJugadas() {
        return this.listaJugadas;
    }

    // Devuelve cuántas jugadas hay expuestas en la mesa
    public int size() {
        return this.listaJugadas.size();
    }

    // Obtiene una jugada específica por su índice
    public Jugada get(int indice) {
        return this.listaJugadas.get(indice);
    }


    public Carta intercambiarCarta(int indice, Carta cartaOfrecida, int indiceCartaMesa) {

        if (indice < 0 || indice >= listaJugadas.size()) {
            System.out.println("Error: La jugada seleccionada no existe.");
            return null;
        }

        Jugada jugada = listaJugadas.get(indice);
        List<Carta> cartas = jugada.getListaRecibida();

        if (cartas.size() < 3) {
            System.out.println("Error: No puedes retirar cartas de una jugada con menos de 3 cartas.");
            return null;
        }

        if (indiceCartaMesa < 0 || indiceCartaMesa >= cartas.size()) {
            System.out.println("Error: La carta seleccionada de la mesa no existe.");
            return null;
        }

        List<Carta> copiaOriginal = new ArrayList<>(cartas);
        Carta cartaRetirada = cartas.get(indiceCartaMesa);

        cartas.remove(indiceCartaMesa);
        cartas.add(cartaOfrecida);

        if (jugada.validarJugada()) {
            System.out.println("¡Intercambio realizado con éxito!");
            return cartaRetirada;
        } else {
            System.out.println("Movimiento ilegal: La combinación resultante en la mesa no es válida.");
            jugada.getListaRecibida().clear();
            jugada.getListaRecibida().addAll(copiaOriginal);
            return null;
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== JUGADAS EN LA MESA ===\n");
        int numeroJugada = 1;
        for (Jugada jugada : listaJugadas) {
            sb.append("Jugada ").append(numeroJugada).append(": ");
            sb.append(jugada.toString()).append("\n");
            numeroJugada++;
        }
        sb.append("==========================");
        return sb.toString();
    }
}