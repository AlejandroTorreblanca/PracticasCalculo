/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primeraEntregaCN1v;
import ORG.netlib.math.complex.Complex;
import auxiliar.*;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Alexjandro
 */
public class Ejercicio2 {

    public static class FRunge implements Funcion{
        public double eval(double x){
            return 1./(1+25*x*x);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        FRunge f=new FRunge();
        int N=11;
        double a=-1;
        double b=1;
        double x=0;
        int n=3;
        double[][] equiTabla=MetodosFunciones.tablaGraficaTchev(f, N, a,b );
        PolinomioInterpolador p= new PolinomioInterpolador(equiTabla);
        /*double[][] xys={{5,1},{-7,-23},{-6,-54},{0,-954}};
        PolinomioInterpolador p = new PolinomioInterpolador(xys);
        double[] coef,coef2;
        coef = p.coefFormaNewton;
        coef2=p.nodos;
        for (int i = 0; i < coef.length; i++) {
            System.out.println("coef"+i+":"+coef[i]);
            System.out.println("nodo"+i+":"+coef2[i]);
        }*/
        double[] D=p.evalDerivadas(x, n);
        for (int i = 0; i < D.length; i++) {
            System.out.println("derivada "+i+" : "+D[i]);
            
        }
        System.out.println("p(x) = "+ p);

        PanelDibujo pd1 = new PanelDibujo("Ejercicio 3 práctica 4 interpolacion Chebychev"
                + "",700,0,1500,600);
        /*
         * Una buena estrategia para evitar dejar progamas en ejecucion es crear
         * un "listener" (modulo de escucha) sobre la cruz de cerrar la ventana del panel de dibujo
         * para poder cerrar este panel en cualquier momento y detener la ejecucion del programa		 *
         */

        pd1.addWindowListener(
                new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        pd1.addEjesCoordenados(true, a-0.5, b+0.5,-1, 2,0,0);
        pd1.addListaPuntos(Color.blue, equiTabla, 3);
        pd1.addCurva(Color.red, p, 111, a, b);
        pd1.repaint();
    }
    
}
