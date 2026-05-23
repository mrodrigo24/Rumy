public enum Simbolo {
    AS("A", 1,1),
    DOS("2", 2,2),
    TRES("3", 3,3),
    CUATRO("4", 4,4),
    CINCO("5", 5,5),
    SEIS("6", 6,6),
    SIETE("7", 7,7),
    OCHO("8", 8,8),
    NUEVE("9", 9,9),
    DIEZ("10", 10,10),
    JACK("J", 10,11),
    QUEEN("Q", 10,12),
    KING("K", 10,13),
    COMODIN("*", 0,0);

    private final String representacion;
    private final int valorNumerico;
    private final int ordenEscalera;

    Simbolo(String representacion, int valorNumerico, int ordenEscalera) {
        this.representacion = representacion;
        this.valorNumerico = valorNumerico;
        this.ordenEscalera=ordenEscalera;
    }

    public String getRepresentacion() {
        return representacion;
    }
    public int getValorNumerico() {
        return valorNumerico;
    }

    public int getOrdenEscalera() {
        return ordenEscalera;
    }

}
