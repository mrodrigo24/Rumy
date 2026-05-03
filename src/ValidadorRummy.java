import java.util.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ValidadorRummy {

    Scanner scan = new Scanner(System.in);
    int numero=-1;
    int sumaPuntos=0;

        public boolean comprobar(List<Carta> listaRecibida,Jugador jugadorActual){
            while (numero!=0){
                System.out.println("Introduce el numero, cero para salir");
                numero= scan.nextInt();
                if (numero!=0){
                    Carta seleccionada=jugadorActual.elegirCartas(numero);
                    if (seleccionada!=null){
                        listaRecibida.add(seleccionada);
                    }
                }
            }
            return true;
        }

        public int sumaPuntos(List<Carta> listaRecibida){
            //Collections.sort(listaRecibida);
            Iterator<Carta> it = listaRecibida.iterator();
            while(it.hasNext()){
                Carta actual= it.next();
                sumaPuntos+=actual.getValor().getNumero();
            }
            //System.out.println("El valor es "+sumaPuntos);
            return sumaPuntos;
        }
    }


