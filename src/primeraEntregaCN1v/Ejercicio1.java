/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primeraEntregaCN1v;

import ORG.netlib.math.complex.Complex;
import auxiliar.Polinomio;

/**
 *
 * @author Alejandro
 */
public class Ejercicio1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double[] coef={-9,0,2.6,-12,0,32,1};
        Polinomio p = new Polinomio(coef);
        double x=2;
        int n=7;
        double[] D = p.evalDerivadas(x, n);
        for (int i = 0; i <= n; i++) {
            System.out.println("derivada "+i+"ésima\t D[" + i + "]=" + D[i]);
        }
        System.out.println("Polinomio desarollado en potencias de (x-"+x+") ");
        System.out.print("p(x) = "+ D[0]+" ");
        Complex[] C = new Complex[D.length];
        for (int i = 0; i <= n; i++) {
            C[i]= new Complex(D[i]);
        }
        for (int i = 1; i <= Math.min(n,p.grado()); i++) { 
            if(i%4==0)System.out.println("");
            for (int j = i; j <= Math.min(n, p.grado()); j++) {
                C[j]=C[j].scale(1./i);    
            } 
            System.out.print("+ "+C[i]+"(x - "+x+")^"+i);
        }
        System.out.println("");
        System.out.println("p(x) = "+ p);
    }
}