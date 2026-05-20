public enum Palos {
    CORAZONES("♥", "\u001B[31m"),    // Rojo (Se ve bien en ambos)
    DIAMANTES("♦", "\u001B[31m"),    // Rojo (Se ve bien en ambos)
    PICAS("♠", "\u001B[37m"),        // Blanco (Se verá en fondo negro)
    TREBOLES("♣", "\u001B[34m"),
    COMODIN("🃏", "\u001B[35m");     // Azul (Opcional, para diferenciar de las picas)

    private final String icono;
    private final String color;
    private final String RESET = "\u001B[0m";

    Palos(String icono, String color) {
        this.icono = icono;
        this.color = color;
    }

    @Override
    public String toString() {
        return color + icono + RESET;
    }
}