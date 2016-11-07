/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn1vPractica5;

import auxiliar.*;
import com.sun.org.apache.bcel.internal.generic.SIPUSH;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Alejandro
 */
public class Ejercicio2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        double a=0;
        double b=4*Math.PI;
        int n = 20;
        double [][] puntos= new double[20][2];
        double paso = (b-a)/(n-1);
        for (int i = 0; i < n; i++) {
            double theta=a+paso*i;
            puntos[i][0]=2*theta*Math.cos(theta);
            puntos[i][1]=2*theta*Math.sin(theta);
        }
         PanelDibujo pd = new PanelDibujo("Ejercicio 2 práctica 5 ");
        /*
         * Una buena estrategia para evitar dejar progamas en ejecucion es crear
         * un "listener" (modulo de escucha) sobre la cruz de cerrar la ventana del panel de dibujo
         * para poder cerrar este panel en cualquier momento y detener la ejecucion del programa		 *
         */

        pd.addWindowListener(
                new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        double xinf=-28;
        double xsup=28;
        double yinf=-21;
        double ysup=21;
        pd.addEjesCoordenados(true,xinf, xsup,yinf,ysup,0,0);
        pd.addListaPuntos(Color.blue, puntos, 3);
        pd.repaint();
        
        
        //Apartado 2
        double[][] nodosx=new double[n][2];
        double[][] nodosy=new double[n][2];
        for (int i = 0; i < n; i++) {
            nodosx[i][0]=a+paso*i;
            nodosy[i][0]=nodosx[i][0];
            nodosx[i][1]=puntos[i][0];
            nodosy[i][1]=puntos[i][1];

        }
        Spline sx= new Spline(nodosx);
        Spline sy= new Spline(nodosy);

        //Apartado 3
        Espiralx xt= new Espiralx();
        Espiraly yt= new Espiraly();
        pd.addCurva(Color.blue, xt, yt, 115, a, b);
        pd.addCurva(Color.red, sx,sy, 115, a, b);
        
        pd.repaint();
    }
    
    public static class Espiralx implements Funcion{
        public double eval(double x){
          return 2*x*Math.cos(x);
        }
    }
    public static class Espiraly implements Funcion{
        public double eval(double x){
          return 2*x*Math.sin(x);
        }
    }
}
