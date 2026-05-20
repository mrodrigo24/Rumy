public class Carta implements Comparable <Carta>{
    private Simbolo simbolo;
    private Palos  palo;
    Valores valorPorCarta;

    public Carta(Simbolo simbolo,Palos palo,Valores valorPorCarta){
        this.simbolo=simbolo;
        this.palo=palo;
        this.valorPorCarta=valorPorCarta;
    }
    public Valores getValorPorCarta() {
        return valorPorCarta;
    }
    public Palos getPalo() {
        return palo;
    }

    public void setPalo(Palos palo) {
        this.palo = palo;
    }

    public Simbolo getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(Simbolo simbolo) {
        this.simbolo = simbolo;
    }

    @Override
    public int compareTo(Carta crt) {
        return Integer.compare(this.simbolo.getValorNumerico(), crt.simbolo.getValorNumerico());
    }

    @Override
    public String toString() {
        return "[" + simbolo.getRepresentacion() + " de " + palo + "]";
    }
}
