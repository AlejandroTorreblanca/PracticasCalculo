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
 * @author Alexjandro
 */
public class Ejercicio2 {

    public static class F1 implements Funcion{
        public F1(){
        }
        @Override
        public double eval(double x){
            return Math.sin(Math.PI*x*x);
        }
    } 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        F1 f=new F1();
        int numpuntos=13;
        double[][] xys=MetodosFunciones.tablaGrafica(f, numpuntos, 0, 1);
        long hora_inicio,hora_final;
        hora_inicio=System.currentTimeMillis();
        Polinomio p=Polinomio.interpoladorLagrange(xys);
        int numEval=100;
        double[][] tabla=MetodosFunciones.tablaGrafica(f, numEval, 0, 1);
        hora_final=System.currentTimeMillis();
        System.out.println("Tiempo de construccion del polinomio interpolador en "+numpuntos+" y la evaluaión en "+ numEval+" \n tiempo="+(hora_final-hora_inicio));

        PanelDibujo pd=new PanelDibujo("Ejercicio 2");
        pd.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
        double a=0;
        double b=1;
         
        pd.addEjesCoordenados(true, a+0.5, b+0.5, -2, 2);
        pd.addListaPuntos(Color.yellow, xys, 5);
        pd.addCurva(Color.blue, p, numEval, a, b);
        pd.repaint();
    }
    
}
