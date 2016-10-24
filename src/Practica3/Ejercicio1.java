/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica3;

import ORG.netlib.math.complex.Complex;
import Practica2.PanelDibujo;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Alejandro
 */
public class Ejercicio1 {
    public static void main(String[] args) {
        
        double[] coefRe={1.,-2.,3.,-4.,0.,5.};
        Polinomio p=new Polinomio(coefRe);
        System.out.println("p(x) = " );
        double x=1;
        System.out.println("p(x)= "+ p.eval(x));
        Complex z=new Complex(0., 1.);
        System.out.println("p(z)="+ p.eval(x));
        
        PanelDibujo pd=new PanelDibujo("Apartado 1 del Ejercicio 4");
         pd.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
         double xi2=-10;
         double xs2=10;
         double yi2=-5;
         double ys2=5;
         
         pd.addEjesCoordenados(true, xi2, xs2, yi2, ys2);
         pd.addCurva(Color.red, p, 35, xi2, xs2);        
        
    }
}
