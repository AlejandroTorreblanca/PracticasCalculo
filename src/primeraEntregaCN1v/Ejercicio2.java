/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primeraEntregaCN1v;
import auxiliar.*;

/**
 * Ejercicio 2 de la entrega.
 * Mostramos por pantalla las tres primeras derivadas en el punto x=0 del polinomio proporcionado
 * Para realizarlo interpolamos la curva, por tanto obtendremos una aproximación de las derivadas.
 * Podemos comprobar que según lo grande que establezcamos N conseguimos un mejor o peor aproximación del 
 * polinomio. Con N=50 ya conseguimos una aproximación aceptable.
 * @author AlejandroTorreblanca
 */
public class Ejercicio2 {

    public static class FRunge implements Funcion{
        @Override
        public double eval(double x){
            return 1./(1+25*x*x);
        }
    }
    
    public static void main(String[] args) {

        double[] valoresExactos={1,0,-50};
        FRunge f=new FRunge();
        int N=50;
        double a=-1;
        double b=1;
        double x=0;
        int n=3;
        double[][] equiTabla=MetodosFunciones.tablaGraficaTchev(f, N, a, b);
        PolinomioInterpolador p= new PolinomioInterpolador(equiTabla);
        double[] D=p.evalDerivadas(x, n);
        for (int i = 0; i < 3; i++) 
            System.out.println("Valor de la derivada "+i+"ésima calculada: "+D[i]+ "\t Valor exacto de la derivada: "+valoresExactos[i]);
    }
}
