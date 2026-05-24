import java.util.Scanner;
public class LectorTeclado {
public static int leerEntero(){
    Scanner scan = new Scanner(System.in);
    while (!scan.hasNextInt()) {
        System.out.println("¡Error! Introduce posición de la carta.");
        scan.next(); // Limpiamos la letra
    }
    return scan.nextInt();
}

public static int leerEnteroEnRango(int min, int max){
            int numero=0;
            boolean valido=false;

            while(!valido){
                numero=LectorTeclado.leerEntero();
                if (numero >= min && numero <= max) {
                    valido = true;
                } else {
                    System.out.println("Debe estar entre " + min + " y " + max + ".");
                }
            }
    return numero;
}
}