public class Main {
    public static void main(String[] args) {
        // Instanciamos el objeto del juego
        System.out.println("=== CONTROL DE INICIO DE PARTIDA ===");
        System.out.println("1 - Nueva Partida");
        System.out.println("2 - Reanudar Partida Guardada");
        int opcion=LectorTeclado.leerEnteroEnRango(1,2);
        Juego partida;
            if (opcion==2) {
                //buscamos el archivo binario en la raiz

                partida = GestorFicheros.cargarPartida("partida.dat");
                    //por si no existe el archivo
                if (partida == null) {
                    System.out.println("Nose pudo cargar el archivo iniciando nueva partida");
                    partida = new Juego();
                }
            } else {
                partida=new Juego();

            }
        // Arrancamos la partida
        partida.jugar();
    }
}