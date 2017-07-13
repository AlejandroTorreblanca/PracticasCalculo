/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn1vPractica6;

import auxiliar.*;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Alejandro
 */
public class Ejercicio2 {

    
    public static class F  implements Funcion
    {
        public double eval(double x){
            return x*+Math.cos(x*x);
        }
    }
    
    public static class D3  implements Funcion{
        Funcion f;
        double h;
        public D3(Funcion f, double h){
            this.f=f;
            this.h=h;
        }
        public double eval(double x){
            return (f.eval(x+h)-f.eval(x-h))/(2*h);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PanelDibujo pd = new PanelDibujo("Ejercicio 2 práctica 6");
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
        double xinf=-1;
        double xsup=11;
        double yinf=-2.10;
        double ysup=2.10;
        pd.addEjesCoordenados(true,xinf, xsup,yinf,ysup,0,0);
        
        F f= new F();
        double h=0.001;
        D3 f1=new D3(f, h);
        pd.addCurva(Color.red, f, 100, 0, 10);
        pd.addCurva(Color.blue, f1, 100, 0, 10);
        
        double [][] tablaPrimitiva= new double[100][2];
        double paso= 10./100;
        tablaPrimitiva[0][0]=0;
        tablaPrimitiva[0][1]=0;
        for (int i = 1; i < 100; i++) {
            tablaPrimitiva[i][0]=tablaPrimitiva[i-1][0]+paso;
            tablaPrimitiva[i][1]=tablaPrimitiva[i-1][1];
            MetodosFunciones.integralSimpson(f, tablaPrimitiva[i-1][0],tablaPrimitiva[i][0],4);
        }
        pd.addCurva(Color.green,tablaPrimitiva);
        
        pd.repaint();
    }
    
}
