
package primeraEntregaCN1v;

import auxiliar.Polinomio;

/**
 * Ejercicio 1 de la entrega.
 * Mostramos por pantalla las primeras 7 derivadas evaluadas en el punto x=2 del polinomio proporcionado.
 * @author AlejandroTorreblanca
 */
public class Ejercicio1 {

    public static void main(String[] args) {
        double[] coef={-9,0,2.6,-12,0,32,1};
        Polinomio p = new Polinomio(coef);
        double x=2;
        int n=7;
        double[] D = p.evalDerivadas(x, n);
        for (int i = 0; i <= n; i++) 
            System.out.println("Derivada "+i+"ésima\t D[" + i + "]=" + D[i]);
    }
    
}