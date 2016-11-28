/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn1vPractica6;

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
        double[] coef={-2,-5,7,-4,1};
        Polinomio p= new Polinomio(coef);
        Complex[] d=p.evalDerivadas(Complex.real(3), 4);
        for (int i = 0; i < 4; i++) {
            System.out.println("p["+i+"]= "+d[i]);
        }

    }
    
}
