/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn1vPractica7;
import auxiliar.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Alexjandro
 */
public class Ejercicio1 {

    public static class F implements FuncionDeriv{
        @Override
        public double eval(double x) {
            double y=Math.exp(x)-x*x-x-1;
            return  y*y;
        }
        public double derivada(double x){
            double y=Math.exp(x)-x*x-x-1;
            double dy=2*y*(Math.exp(x)-2*x-1);
            return dy;
        }
    }
    
    public static class G1 implements Funcion{
        F f=new F();
        @Override
        public double eval(double x) {
            return x-f.eval(x)/f.derivada(x);
        }
    }    
        
    public static class G2 implements Funcion{
        F f=new F();
        @Override
        public double eval(double x) {
            return x-f.eval(x);
        }    
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        F f= new F();
        double x0=0.5;
        double prec=0.5E-8;
        int nmaxit=100;
        try {
            double x=MetodosEcuacionNoLineal.newton(f, x0, prec, nmaxit);
            System.out.println("cero (newton) en "+x);
            System.out.println("");
        } catch (MetodosEcuacionNoLineal.ERROR ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        double x1=1;
        try {
            double x=MetodosEcuacionNoLineal.secante(f, x0, x1, prec, nmaxit);
            System.out.println("cero (secante) en "+x);
            System.out.println("");

        } catch (MetodosEcuacionNoLineal.ERROR ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            G1 g1=new G1();
            G2 g2=new G2();
            try {
            double x=MetodosEcuacionNoLineal.steffensenPF(g1, x1, prec, nmaxit);
            System.out.println("cero (steffensen) en "+x);
            } catch (MetodosEcuacionNoLineal.ERROR ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
            double x=MetodosEcuacionNoLineal.steffensenPF(g2, x1, prec, nmaxit);
            System.out.println("cero (steffensen) en "+x);
            } catch (MetodosEcuacionNoLineal.ERROR ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        try {
            double x=MetodosEcuacionNoLineal.newtonRaizMultiple(f, x1, prec, nmaxit);
            System.out.println("cero (Newton multiple) en "+ x);
        } catch (MetodosEcuacionNoLineal.ERROR ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
