/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica2;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Alejandro
 */
public class Ejercicio3 {
    public static class F1 implements Funcion{
        public F1(){
        }
        
        @Override
        public double eval(double x){
            if (x >=0) 
                return 1+ Math.cos(x*x*Math.exp(x));
            return (Math.exp(-0.1*x)+ Math.cos(x*x*Math.exp(-x)));
        }
    } 
    
    public static class F2 implements Funcion{
        public F2(){
            }

        @Override
        public double eval(double x){
            return Math.PI+Math.sqrt(1+x*x);
        }
    }  
    
    public static class F3 implements Funcion{
            int n=1;
            public F3(int n){
                this.n=n;
            }
            
        @Override
        public double eval(double x){
            double pot=1;
            for (int i = 0; i < n; i++) {
                pot *= x;
            }
            return pot*Math.exp(x);
        }
    }  
    
     public static void main(String[] args) {
         
         F1 f=new F1();
         F2 g=new F2();
         F3 h=new F3(10);
         
         MetodosFunciones.suma suma=new MetodosFunciones.suma(f, g);    
         PanelDibujo pd=new PanelDibujo("Apartado 1 del Ejercicio 3");
         
         pd.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
         double xi=-6;
         double xs=6;
         double yi=-1.5;
         double ys=14;
         
         double[][] tabla=MetodosFunciones.tablaGrafica(f, 11, -5, 5);
         pd.addEjesCoordenados(true, xi, xs, yi, ys);
         pd.addCurva(Color.PINK, f, 121, -5, 5);
         pd.addCurva(Color.red, g, 121, -5, 5);
         pd.addCurva(Color.green, suma, 121, -5, 5);
         
         pd.addListaPuntos(Color.BLUE, tabla, 4);
         double[] posicionEtiqueta={2,3};
         double[] posicionEtiqueta2={2,10};
         double[] posicionEtiqueta3={2,11};
         pd.addEtiqueta(posicionEtiqueta, "funcion f", Color.BLACK);
         pd.addEtiqueta(posicionEtiqueta2, "funcion g", Color.BLACK);
         pd.addEtiqueta(posicionEtiqueta3, "funcion suma", Color.BLACK);
         pd.repaint();
         
         PanelDibujo pd2=new PanelDibujo("Apartado 3", 550, 50, 1350, 650);
         
         pd2.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
         double xi2=-2;
         double xs2=2;
         double yi2=-4;
         double ys2=4;
         
         pd2.addEjesCoordenados(true, xi2, xs2, yi2, ys2);
         int ngraf=10;
         Color[] color=new Color[ngraf];
         for (int i = 0; i < ngraf; i++) {
             float red=(i+1.f)/(ngraf+1);
             float green=(13*i%(ngraf+1)+1.f)/(ngraf+1);
             float blue=(11*i%(ngraf+1)+1.f)/(ngraf+1);
             color[i]=new Color(red, green, blue);
             F3 fun3=new F3(i+1);
             pd2.addCurva(color[i], fun3, 121, -2, 2);
         }
         
         
     } 
}
