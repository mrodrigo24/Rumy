public class Carta {
    private Valores valor;
    private Palos  palo;
    public Carta(Valores valor,Palos palo){
        this.valor=valor;
        this.palo=palo;
    }
    @Override
    public String toString() {
        return " (" + valor +
                " de " + palo +
                ')';
    }
}
