/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejemplos;

import auxiliar.*;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Alejandro
 */
public class P_Interpolador {

    public static class F1 implements Funcion{
        public F1(){
            
        }
        public double eval(double x){
            return Math.sin(Math.PI*x*x);
        }
    }
    
    
    public static void main(String[] args) {
        //Ejemplo de creacion de array bidimiensional.
        double[][] xys={{1.5,0},{2.7,1},{3.1,-0.5},{-2.1,1},{-6.6,0.5},{11,0}};
        
        
        //Creamos la función sen(PI*x*x)
        F1 f= new F1();
        double[][] fxy=MetodosFunciones.tablaGrafica(f, 13, 0, 1);
        
        //Creamos un polinomio interpolador con Newton.
        PolinomioInterpolador p=new PolinomioInterpolador(fxy);
        
        //Creamos un polinomio interpolador con Lagrange.
        Polinomio interpolador =Polinomio.interpoladorLagrange(fxy);
        
        PanelDibujo pd=new PanelDibujo("Ejemplo");
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
        
        double a=-1;
        double b=1;
        double c=-5;
        double d=5;
        pd.addEjesCoordenados(true,a, b, c, d);
        pd.addListaPuntos(Color.blue, fxy, 5);
        pd.addCurva(Color.red, p, 50, a, b);
        pd.addCurva(Color.green, interpolador, 37, a, b);
        double[] posicionEtiqueta={0.25,1};
        double[] posicionEtiqueta2={0.5,0.5};
        pd.addEtiqueta(posicionEtiqueta, "Polinomios ", Color.blue);
        pd.addEtiqueta(posicionEtiqueta2, "Puntos ", Color.red);
        pd.repaint();
        
        
    }
    
}
