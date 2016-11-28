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
 * @author Alexjandro
 */
public class Ejercicio1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double[] coef={1,32,-12,2.6,0,-9};
        Polinomio p= new Polinomio(coef);
        double[] d1=p.evalDerivadas(2, 2);
        double[] d2=p.evalDerivadas(2, 4);
        double[] d3=p.evalDerivadas(2, 6);
        double[] d4=p.evalDerivadas(2, 7);
        System.out.println("Derivada 2");
        for (int i = 0; i < 2; i++) 
            System.out.println("p["+i+"]= "+d1[i]);  
        System.out.println("Derivada 4");
        for (int i = 0; i < 4; i++) 
            System.out.println("p["+i+"]= "+d2[i]);  
        System.out.println("Derivada 6");
        for (int i = 0; i < 6; i++) 
            System.out.println("p["+i+"]= "+d3[i]);  
        System.out.println("Derivada 7");
        for (int i = 0; i < 7; i++) 
            System.out.println("p["+i+"]= "+d4[i]);  
    }
}