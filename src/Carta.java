public class Carta {
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
    public String toString() {
        return " (" + valor + " de " +palo + ')';
    }
}
