public enum Simbolo {
    AS("A", 1), DOS("2", 2), TRES("3", 3), CUATRO("4", 4), CINCO("5", 5),
    SEIS("6", 6), SIETE("7", 7), OCHO("8", 8), NUEVE("9", 9), DIEZ("10", 10),
    JACK("J", 10), QUEEN("Q", 10), KING("K", 10);

    private final String representacion;
    private final int valorNumerico;

    Simbolo(String representacion, int valorNumerico) {
        this.representacion = representacion;
        this.valorNumerico = valorNumerico;
    }

    public String getRepresentacion() {
        return representacion;
    }
    public int getValorNumerico() { return valorNumerico; }
}
