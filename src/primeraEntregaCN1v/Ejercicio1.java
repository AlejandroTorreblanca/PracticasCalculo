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
    }
}