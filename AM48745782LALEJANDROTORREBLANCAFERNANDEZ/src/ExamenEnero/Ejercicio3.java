/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExamenEnero;

import auxiliar.*;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Alejandro
 */
public class Ejercicio3 {

    public static class F1 implements Funcion{
        @Override
        public double eval(double x){
            return Math.exp(-x/60);
        }
    }
    public static class F2 implements Funcion{
        @Override
        public double eval(double x){
            return Math.cos(Math.PI*x);
        }
    }
    
    public static class F3 implements FuncionDeriv{
        Funcion f;
        public F3(Funcion f){
            this.f=f;
        }
        
        @Override
        public double eval(double x){
            return Math.cos(Math.PI*x);
        }
        public double derivada(double x){
            MetodosFunciones.derivada5puntos h=new MetodosFunciones.derivada5puntos(f, 0.1);
            return h.eval(x);
        }
    }
    
    public static class F4 implements FuncionDeriv{
        Funcion f;
        public F4(Funcion f){
            this.f=f;
        }
        
        @Override
        public double eval(double x){
            return Math.cos(Math.PI*x);
        }
        public double derivada(double x){
            MetodosFunciones.derivada5puntos h=new MetodosFunciones.derivada5puntos(f, 0.1);
            return h.eval(x);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MetodosEcuacionNoLineal.ERROR {
        
        F1 f=new F1();
        F2 g=new F2();
        int a=-1;
        int b=4;
        double [][] valores= new double[4][2];
        valores[0][1]=f.eval(0);
        MetodosFunciones.resta h=new MetodosFunciones.resta(f, g);
        F3 r=new F3(h);
        double aproxIni1=1.915;
        double aproxIni2=2.086;
        double aproxIni3=3.89;
        
        valores[1][0]=MetodosEcuacionNoLineal.newton(r, aproxIni1, 0.01, 500);
        valores[2][0]=MetodosEcuacionNoLineal.newton(r, aproxIni2, 0.01, 500);
        valores[3][0]=MetodosEcuacionNoLineal.newton(r, aproxIni3, 0.01, 500);
        valores[1][1]=f.eval(valores[1][0]);
        valores[2][1]=f.eval(valores[2][0]);
        valores[3][1]=f.eval(valores[3][0]);
        PanelDibujo pd = new PanelDibujo("Ejercicio 3");
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
        
        pd.addEjesCoordenados(true, a-0.5, b+0.5,-1, 2,0,0);
        pd.addCurva(Color.red, g, 111, a, b);
        pd.addCurva(Color.blue, f, 111, a, b);
        pd.addCurva(Color.green, h, 111, a, b);
        pd.addListaPuntos(Color.blue,valores, 4);
        pd.repaint();
        
    }
    
}
