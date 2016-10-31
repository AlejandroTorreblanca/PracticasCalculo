/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica3;
import Auxiliar.*;
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
            return Math.sin(Math.PI*x*x);
        }
    } 
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

//        double[][] xys={{1.5,0},{2.7,1},{3.1,-0.5},{-2.1,1},{-6.6,0.5},{11,0}};
//        Polinomio interpolador=Polinomio.interpoladorLagrange(xys);
        //System.out.println(interpolador.escribe());
        
//        PanelDibujo pd=new PanelDibujo("Apartado 1 del Ejercicio 4");
//         pd.addWindowListener(
//                new WindowAdapter() {
//                    @Override
//                    public void windowClosing(WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//        double xi2=-20;
//        double xs2=20;
//        double yi2=-20;
//        double ys2=20;
//         
//        pd.addEjesCoordenados(true, xi2, xs2, yi2, ys2);
//        pd.addListaPuntos(Color.yellow, xys, 5);
//        pd.addCurva(Color.blue, interpolador, 5, xi2, xs2);
        
        //Apartado 2
        F1 f=new F1();
        int numpuntos=17;
        double[][] xys=MetodosFunciones.tablaGrafica(f, numpuntos, 0, 1);
        long hora_inicio,hora_final;
        hora_inicio=System.currentTimeMillis();
        Polinomio p=Polinomio.interpoladorLagrange(xys);
        int numEval=100;
        double[][] tabla=MetodosFunciones.tablaGrafica(f, numEval, 0, 1);
        hora_final=System.currentTimeMillis();
        System.out.println("Tiempo de construccion del polinomio interpolador en "+numpuntos+" y la evaluaión en "+ numEval+" \n tiempo="+(hora_final-hora_inicio));
        PanelDibujo pd=new PanelDibujo("Ejercicio 3");
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
        pd.addCurva(Color.blue,tabla);
        pd.repaint();
    }
    
}
