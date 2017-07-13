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
public class Ejercicio1 {

    public static class F1 implements Funcion{
        @Override
        public double eval(double x){
            return Math.cos(x*x*x)*Math.exp(-x*x);
        }
    }
    
    public static class F1derivada implements Funcion{
        @Override
        public double eval(double x){
            return Math.cos(x*x*x)*Math.exp(-x*x);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        F1 f=new F1();
        int N=7;
        
        //Apartado 1
        int a=-2;
        int b=2;
        MetodosFunciones.derivada3puntos dF1= new MetodosFunciones.derivada3puntos(f, 0.1);
        MetodosFunciones.derivadaSegunda3puntos ddF1= new MetodosFunciones.derivadaSegunda3puntos(f, 0.1);

        double [][] xHerm= new double [N][2];
        xHerm[0]=new double[4];
        xHerm[N-1]=new double [4];
        for (int i = 0; i < xHerm.length; i++) {
            xHerm[i][0]=a+i*(b-a)/(N-1.);
            xHerm[i][1]=f.eval(xHerm[i][0]);
        }
        xHerm[0][2]=dF1.eval(xHerm[0][0]);
        xHerm[0][3]=ddF1.eval(xHerm[0][0]);
        xHerm[N-1][2]=dF1.eval(xHerm[N-1][0]);
        xHerm[N-1][3]=ddF1.eval(xHerm[N-1][0]);
        PolinomioInterpolador h = new PolinomioInterpolador(xHerm);
        System.out.println("h(x)= "+h);
        
        //Apartado 2
        double[][] xys = MetodosFunciones.tablaGrafica(f, N, a, b);
        a=-5;
        b=5;
        double [][] nodCheby= MetodosFunciones.tablaGraficaTchev(f, N, a, b);
        PolinomioInterpolador q =new PolinomioInterpolador(nodCheby);
        
        //Apartado 3
        double tanini =dF1.eval(xys[0][0]);
        double tanfin =dF1.eval(xys[N-1][0]);
        
        Spline s = new Spline(xys,tanini,tanfin);
        
        
        MetodosFunciones.resta errorh= new MetodosFunciones.resta(f, h);
        MetodosFunciones.resta errorq= new MetodosFunciones.resta(f, q);
        MetodosFunciones.resta errors= new MetodosFunciones.resta(f, s);
        double[] posicion1={2.5,1.5};
        double[] posicion2={0.1,0.1};      
        double[] posicion3={4.4,0.357};      
        double[] posicion4={2.5,0.2};      
        
        PanelDibujo pd = new PanelDibujo("Ejercicio 1");
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
        pd.addEtiqueta(posicion1, "Función h", Color.red);
        pd.addEtiqueta(posicion2, "Función f", Color.pink);
        pd.addEtiqueta(posicion3, "Función q", Color.blue);
        pd.addEtiqueta(posicion4, "Función s", Color.green);
        pd.addEjesCoordenados(true, a-0.5, b+0.5,-1, 2,0,0);
        pd.addCurva(Color.red, h, 111, a, b);
        pd.addCurva(Color.pink, f, 111, a, b);
        pd.addCurva(Color.blue, q, 111, a, b);
        pd.addCurva(Color.green, s, 111, a, b);
        pd.repaint();
        
         PanelDibujo pd2 = new PanelDibujo("Ejercicio 1, errores"+ "",700,0,1500,600);
        /*
         * Una buena estrategia para evitar dejar progamas en ejecucion es crear
         * un "listener" (modulo de escucha) sobre la cruz de cerrar la ventana del panel de dibujo
         * para poder cerrar este panel en cualquier momento y detener la ejecucion del programa		 *
         */
        pd2.addWindowListener(
                new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        pd2.addEjesCoordenados(true, a-0.5, b+0.5,-1, 2,0,0);
        pd2.addCurva(Color.red, errorh, 111, a, b);
        pd2.addCurva(Color.pink, f, 111, a, b);
        pd2.addCurva(Color.blue, errorq, 111, a, b);
        pd2.addCurva(Color.green, s, 111, a, b);
        pd2.repaint();
        
    }
    
}
