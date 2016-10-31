/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica4;
import Auxiliar.*;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 *
 * @author Alejandro
 */
public class Ejercicio3 {

    public static class FRouge implements Funcion{
        @Override
        public double eval(double x){
            return 1./(1+25*x*x);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FRouge f = new FRouge();
        int n=15;
        double a=-1;
        double b=1;
        double[][] equiTabla= MetodosFunciones.tablaGrafica(f, n, a, b);
        PolinomioInterpolador p =new PolinomioInterpolador(equiTabla);
        PanelDibujo pd=new PanelDibujo("Ejercicio 3 Practica 4");
        pd.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
         
        pd.addEjesCoordenados(true, a+0.5, b+0.5,-1, 2,0,0);
        pd.addListaPuntos(Color.red, equiTabla, 3);
        pd.addCurva(Color.blue,p,111,a,b);
        pd.repaint();
        
        //Apartado2
        
        double[][] cheby=MetodosFunciones.tablaGraficaTchev(f, n, a, b);
        PolinomioInterpolador q=new PolinomioInterpolador(cheby);
        PanelDibujo pd1=new PanelDibujo("Ejercicio 3 Practica 4 cheby" + "",800,0,1600,600);
        pd1.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
         
        pd1.addEjesCoordenados(true, a+0.5, b+0.5,-1, 2,0,0);
        pd1.addListaPuntos(Color.red, cheby, 3);
        pd1.addCurva(Color.blue,q,111,a,b);
        pd1.repaint();
        
        //Apartado 3
        double[][] xHermite=new double[n][2];
        xHermite[0]=new double[4];
        xHermite[n-1]=new double[4];
        for (int i = 0; i < xHermite.length; i++) {
            xHermite[i][0]=a+i*(b-a)/(n-1);
            xHermite[i][1]=f.eval(xHermite[i][0]);
            }
        PolinomioInterpolador r=new PolinomioInterpolador(xHermite);       
        PanelDibujo pd2=new PanelDibujo("Ejercicio 3 Practica 4 cheby" + "",1200,600,1200,1200);
        pd2.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
         
        pd2.addEjesCoordenados(true, a+0.5, b+0.5,-1, 2,0,0);
        pd2.addListaPuntos(Color.red, cheby, 3);
        pd2.addCurva(Color.blue,q,111,a,b);
        pd2.repaint();

    }
}