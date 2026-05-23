public enum Palos {
    CORAZONES("♥", "\u001B[31m"),    // Rojo
    DIAMANTES("♦", "\u001B[31m"),    // Rojo
    PICAS("♠", "\u001B[37m"),        // Blanco
    TREBOLES("♣", "\u001B[34m"),
    COMODIN("🃏", "\u001B[35m");     // Azul

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