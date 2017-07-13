/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejemplos;

import auxiliar.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Alejandro
 */
public class EcuacionesNoLineales {

    public static class F1 implements Funcion{
        public F1(){
            
        }
        public double eval(double x){
            return x*x*x-2007;
        }
    }
    
    public static class F2 implements FuncionDeriv{
        public F2(){
            
        }
        public double eval(double x){
            return x*x*x*x*x*x-x-1;
        }
        public double derivada(double x){
            return 6*x*x*x*x*x-1;
        }
    }
    
    public static void main(String[] args) throws MetodosEcuacionNoLineal.ERROR {
        
        try {
            F1 f= new F1();
            F2 g= new F2();
            
            //Biseccion
//        double x=MetodosEcuacionNoLineal.biseccion(f, 10, 14, 0.001, 50);

//Newton
//        x=MetodosEcuacionNoLineal.newton(g, 2, 0.000001, 100);
//        //Secante
//        x=MetodosEcuacionNoLineal.secante(g, 2, 1, 0.000001, 100);
//        //Muller, podemos salirnos de la recta real si las condiciones iniciales no son las adecuadas.
//        x=MetodosEcuacionNoLineal.Muller(g, 2, 1.5, 1, 0.000001, 100);
//
        
//        double[] coef={6,-5,1};
//        double[] raices=new double[3];
//        
//        Polinomio p=new Polinomio(coef);
//        System.out.println("p: "+p.toString());
//        try {
//            raices[0]=p.newton(4.5, 0.00001, 200);
//            Polinomio defla=p.deflacion(raices[0]);
//            System.out.println("defla: "+defla.toString());
//            defla.newton(6, 0.000001, 200);
//            
//        } catch (Polinomio.ErrorPolinomios ex) {
//            Logger.getLogger(EcuacionesNoLineales.class.getName()).log(Level.SEVERE, null, ex);
//        }

double[] coef={-1,-3,4,1};
Polinomio p=new Polinomio(coef);
            System.out.println("p:"+p.toString());
p.buscaRaicesAleatorio(0.0001, 2000, 200);
p.newton(-10, 0.00001, 2000);
p.newton(6, 0.00001, 2000);
        } catch (Polinomio.ErrorPolinomios ex) {
            Logger.getLogger(EcuacionesNoLineales.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
