public class Carta implements Comparable <Carta>{
    private Valores valor;
    private Palos  palo;
    public Carta(Valores valor,Palos palo){
        this.valor=valor;
        this.palo=palo;
    }

    public Valores getValor() {
        return valor;
    }

    public void setValor(Valores valor) {
        this.valor = valor;
    }

    public Palos getPalo() {
        return palo;
    }

    public void setPalo(Palos palo) {
        this.palo = palo;
    }

    @Override
    public int compareTo(Carta crt) {

        int compNumero = Integer.compare(this.valor.getNumero(), crt.getValor().getNumero());

        if (compNumero != 0) {
            return compNumero;
        }

        return this.palo.compareTo(crt.getPalo());
    }
    @Override
    public String toString() {
        return " (" + valor + " de " +palo + ')';
    }
}
