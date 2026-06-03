import java.util.ArrayList;
import java.util.List;

public class MazoDescarte {
    private final List<Carta> cartasDescarte;

    public MazoDescarte() {
        this.cartasDescarte = new ArrayList<>();
    }

    public boolean estaVacio() {
        return cartasDescarte.isEmpty();
    }

    public void meterCarta(Carta crt) {
        cartasDescarte.add(crt);
    }

    public Carta verUltimaCarta() {
        return cartasDescarte.getLast();
    }

    public Carta tomarUltimaCarta() {
        return cartasDescarte.removeLast();
    }
}