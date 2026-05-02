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
        int numero1=this.valor.getNumero();
        int numero2=crt.getValor().getNumero();
        if (numero1<numero2) {
            return -1;
        }
        if (numero1>numero2) {
            return 1;
        }

        return 0;
    }

    @Override
    public String toString() {
        return " (" + valor + " de " +palo + ')';
    }
}
