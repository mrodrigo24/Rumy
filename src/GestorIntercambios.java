import java.util.ArrayList;
import java.util.List;

public class GestorIntercambios {

    public Carta averiguarCartaComodin(List<Jugada> jugadasEnMesa, int numero) {

        Jugada jugadaSeleccionada = jugadasEnMesa.get(numero);

        System.out.println("--- PRUEBA DE JUGADA SELECCIONADA ---");
        System.out.println(jugadaSeleccionada.toString());
        System.out.println("-------------------------------------\n");

        // 2. Extraemos las cartas que hay dentro de la jugada
        // (Asegúrate de que tu clase Jugada tenga el método getListaRecibida() o similar)
        List<Carta> cartasDeLaJugada = jugadaSeleccionada.getListaRecibida();

        // 3. Simulamos la carta que tú tienes en la mano y quieres poner
        // Vamos a decir que tienes el SIETE de CORAZONES en tu mano
        Carta cartaQueYoOfrezco = new Carta(Simbolo.SIETE, Palos.CORAZONES);
        System.out.println("Tú ofreces desde tu mano: " + cartaQueYoOfrezco);

        // 4. Buscamos en qué posición (índice) está el comodín en la mesa
        int posicionComodin = -1;
        for (int i = 0; i < cartasDeLaJugada.size(); i++) {
            if (cartasDeLaJugada.get(i).getSimbolo() == Simbolo.COMODIN) {
                posicionComodin = i;
                break;
            }
        }

        // 5. Si encontramos un comodín, hacemos la magia matemática
        if (posicionComodin != -1) {
            System.out.println("-> Comodín detectado en la posición índice: " + posicionComodin);

            // Miramos la carta que está justo antes del comodín (en este caso, el 6 de corazones)
            Carta cartaVecina = cartasDeLaJugada.get(posicionComodin - 1);

            // Averiguamos qué valor interno en la escalera tiene la vecina y le sumamos 1
            int valorMatematicoEsperado = cartaVecina.getSimbolo().getOrdenEscalera() + 1;

            // 6. Comprobamos si tu carta coincide en número y en palo
            if (cartaQueYoOfrezco.getSimbolo().getOrdenEscalera() == valorMatematicoEsperado
                    && cartaQueYoOfrezco.getPalo() == cartaVecina.getPalo()) {

                System.out.println("¡SÍ SE PUEDE! Tu carta sustituye perfectamente al comodín. La escalera seguiría igual de válida.");
                // Aquí devolveríamos el Comodín rescatado para guardarlo en tu mano
                return cartasDeLaJugada.get(posicionComodin);
            } else {
                System.out.println("¡NO SE PUEDE! Esa carta no es la que el comodín está sustituyendo.");
            }
        } else {
            System.out.println("No hay ningún comodín en esta jugada.");
        }
        return null;
    }

    // MÉTODO DE PRUEBA CONTROLADO
    public static void main(String[] args) {
        List<Jugada> mesaSimulada = new ArrayList<>();
        List<Carta> cartasIniciales = new ArrayList<>();

        // Fabricamos una escalera: 5 de Corazones, 6 de Corazones y un Comodín (que hace de 7)
        cartasIniciales.add(new Carta(Simbolo.CINCO, Palos.CORAZONES));
        cartasIniciales.add(new Carta(Simbolo.SEIS, Palos.CORAZONES));
        cartasIniciales.add(new Carta(Simbolo.COMODIN, Palos.COMODIN)); // El comodín en tercera posición

        Jugada jugadaPrueba = new Escalera(cartasIniciales);
        mesaSimulada.add(jugadaPrueba);

        GestorIntercambios gestor = new GestorIntercambios();

        // Probamos a analizar la jugada de la posición 0
        gestor.averiguarCartaComodin(mesaSimulada, 0);
    }
}