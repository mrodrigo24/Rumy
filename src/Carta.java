public class Carta implements Comparable <Carta>{
    private Simbolo simbolo;
    private Palos  palo;

    public Carta(Simbolo simbolo,Palos palo){
        this.simbolo=simbolo;
        this.palo=palo;
    }

    public Palos getPalo() {
        return palo;
    }


    public Simbolo getSimbolo() {
        return simbolo;
    }


    @Override
    public int compareTo(Carta crt) {
        return Integer.compare(this.simbolo.getOrdenEscalera(), crt.simbolo.getOrdenEscalera());
    }

    @Override
    public String toString() {
        return "[" + simbolo.getRepresentacion() + " de " + palo + "]";
    }
}
