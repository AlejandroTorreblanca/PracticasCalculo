/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segundaEntregaCN1v;

import auxiliar.*;

/**
 *
 * @author Alejandro
 */
public class Ejercicio2 {

    public static class F1 implements Funcion{
        @Override
        public double eval(double x){
            return Math.exp(x)+x*x-x-1;
        }
    }
    
    public static class F2 implements Funcion{
        @Override
        public double eval(double x){
            return x*x*x-x-1;
        }
    }

     public static class F3 implements Funcion{
        @Override
        public double eval(double x){
            return (x-2)*(x-2)*(x-2)*Math.log(2+x*x);
        }
    }
     
    public static void main(String[] args) {
        
       F1 f= new F1();
       F2 g= new F2();
       F3 h= new F3();
       //double raiz=MetodosEcuacionNoLineal.interpolacionInversa3puntos(f, 1., 1.5, 2., 0.000001, 20);
       //double raiz=MetodosEcuacionNoLineal.interpolacionInversa3puntos(g, 1., 1.5, 2., 0.001, 20);
       double raiz=MetodosEcuacionNoLineal.interpolacionInversa3puntos(h, 1., 1.5, 2.5, 0.001, 20);
       
       System.out.println("Raiz en: "+raiz); 
    }
    
}
