/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn1vPractica7;

import auxiliar.*;

/**
 *
 * @author Alejandro
 */
public class Ejercicio1 {

    public static class F implements FuncionDeriv{
        public double eval(double x){
            double y=Math.exp(x)-x*x-x-1;
            return y*y;
        }
        public double derivada(double x){
            double y=Math.exp(x)-x*x-x-1;
            double dy= 2*y*(Math.exp(x)-2*x-1);
            return dy;
        }
    }
    public static class G1 implements Funcion{
        F f=new F();
        public double eval(double x){
            return x- f.eval(x)/f.derivada(x);
        }
    }
    public static class G2 implements Funcion{
        F f=new F();
        public double eval(double x){
            return x+f.eval(x);
        }
    }

    public static void main(String[] args) {
        //1) newton empezando en x0=0.5;
        F f= new F();
        double x0=0.5;
        double prec=0.5E-8;
        int nmaxit=100;
        try{
            double x=MetodosEcuacionNoLineal.newton(f, x0, prec, nmaxit);
            System.out.println("cero (newton) en "+ x);         
        }catch(MetodosEcuacionNoLineal.ERROR nerr){
        }
        try{
            //secante
            double x1=1;
            double x=MetodosEcuacionNoLineal.secante(f, x0, x1, prec, nmaxit);
            System.out.println("cero (secante) en "+ x);
        }catch(MetodosEcuacionNoLineal.ERROR nerr2){
        }
        //steffensen
        G1 g1=new G1();
        G2 g2=new G2();
        try{
            //steffensen g1
            double x=MetodosEcuacionNoLineal.steffensenPF(g1, x0, prec, nmaxit);
            System.out.println("cero (steffensen g1) en "+ x);
            
        }catch(MetodosEcuacionNoLineal.ERROR nerr3){
        }
        try{
            //steffensen g2
            double x=MetodosEcuacionNoLineal.steffensenPF(g2, x0, prec, nmaxit);
            System.out.println("cero (steffensen g2) en "+ x);
            
        }catch(MetodosEcuacionNoLineal.ERROR nerr3){
        }
        //NewtonMultiple
        try{
            double x=MetodosEcuacionNoLineal.newtonRaizMultiple(f, x0, prec, nmaxit);
            System.out.println("cero (newton multiple) en "+ x);
        }catch(MetodosEcuacionNoLineal.ERROR nerr4){
        }
        
    }
    
}
