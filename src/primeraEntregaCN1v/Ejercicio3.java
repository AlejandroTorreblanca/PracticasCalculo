/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primeraEntregaCN1v;

import auxiliar.*;

/**
 *
 * @author Alexjandro
 */
public class Ejercicio3 {

    public static class F implements Funcion{
        public double eval(double x){
          return (100/x*x)*Math.sin(10/x);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        F f=new F();
        
        double aux=MetodosFunciones.IntegralAdaptativaIt(f, 1, 3, 0.0001, 4);
        
        System.out.println("resultado= "+ aux);
    }
    
}
