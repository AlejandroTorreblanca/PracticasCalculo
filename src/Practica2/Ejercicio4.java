/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica2;

import ORG.netlib.math.complex.Complex;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Alejandro
 */
public class Ejercicio4 {
     public static void main(String[] args) {
         Complex z= new Complex(1., 3.);
         Complex t= new Complex(2., -6.);
         Complex f= new Complex(1.,-1.);
         Complex w= new Complex(1., 3.);
         Complex e= new Complex(1., 1.);
         Complex r1=new Complex(0);
         Complex r2=new Complex(0);
         Complex r3=new Complex(0);
         
         r1=z.add(t);
         z.toString();
         System.out.println("r1 = " + r1);
                
         double x=z.arg();
         double y=z.abs();
         System.out.println("r2 = " + x +" "+ y);
         x=f.arg();
         y=f.abs();
         System.out.println("r2 = " + x +" "+ y);
         r1=e.sqrt();
         r2=w.sqrt();
         r3=r1.mul(r2);
         System.out.println("r3 = " + r3);

         int n=10;
         z=new Complex(-1., -2.);
         x=f.arg();
         y=f.abs();
         double mod=Math.pow(y, 1./n);
         Complex[] raices=new Complex[n];
         
         PanelDibujo pd=new PanelDibujo("Apartado 1 del Ejercicio 4");
         
         double[][] vertices=new double[n][2];
         for (int i = 0; i < raices.length; i++) {
             raices[i]=Complex.polar(mod, x/n + 2*Math.PI*i/n);
             System.out.println("raiz["+i+"]"+raices[i]);
             vertices[i][0]=raices[i].re();
             vertices[i][1]=raices[i].im();
         }
         pd.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
         double xi2=-4;
         double xs2=4;
         double yi2=-4;
         double ys2=4;
         
         pd.addEjesCoordenados(true, xi2, xs2, yi2, ys2);
         pd.addListaPuntos(Color.blue, vertices, n);
     }
}
