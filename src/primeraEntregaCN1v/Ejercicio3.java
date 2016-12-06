/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primeraEntregaCN1v;

import auxiliar.*;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Alexjandro
 */
public class Ejercicio3 {

    public static class F implements Funcion{
        public double eval(double x){
          return (100/(x*x))*Math.sin(10/x);
        }
    }
    
    public static class G implements Funcion{
        public double eval(double x){
          return Math.cos(x*x);
        }
    }
    
    public static class H implements Funcion{
        public double eval(double x){
          return x*Math.cos(x*x*Math.PI/2);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        F f=new F();
        G g=new G();
        H h=new H();
        
        //double aux=MetodosFunciones.IntegralAdaptativaIt(g, 10, 30, 0.01, 4);
      
       //double aux=MetodosFunciones.integralAdaptada(f, 1, 3, 0.0001, 1000);
       //double aux=MetodosFunciones.IntegralAdaptativaIt(f, 1, 3, 0.0001, 1000);
      //double aux=MetodosFunciones.IntegralAdaptativaIt(f, 1, 3, 0.000001);
       
       
       // System.out.println("resultado= "+ aux1);
       
       //Apartado 1
        double resultado=MetodosFunciones.IntegralAdaptativaIt(g, 10, 30, 0.0001, 1000);
        
        System.out.println("Apartado 1:");
        System.out.println("resultado= "+ resultado);
        System.out.println("");
        
        //Apartado 2
        double[] tabla =new double[10];
        for (int i = 0; i < 10; i++) {
            tabla[i]=MetodosFunciones.IntegralAdaptativaIt(h,0,i*0.1, 0.00001, 1000);
        }
        System.out.println("Apartado 1:");
        for (int i = 0; i < 10; i++) {
            System.out.println("Tabla["+i+"]= "+ tabla[i]);
        }
        System.out.println("");

                
        //Apartado 3
        double[][] valores=new double[1000][1000];
        resultado=MetodosFunciones.IntegralAdaptativaIt(f,1,3, 0.0001, 1000,valores);
        
        int numPuntos=20;
        double[][] xys=MetodosFunciones.tablaGraficaTchev(f, numPuntos, 1,3);
        PolinomioInterpolador p = new PolinomioInterpolador(xys);
        PanelDibujo pd = new PanelDibujo("Ejercicio 3, apartado 3, entrega");
        pd.addWindowListener(
                new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        double xinf=-60;
        double xsup=80;
        double yinf=-1;
        double ysup=4;
        pd.addEjesCoordenados(true,yinf,ysup,xinf, xsup,0,0);
        pd.addListaPuntos(Color.blue,valores, 3);
        pd.addCurva(Color.blue, p, 100, 1, 3);
        pd.repaint();
    }
    
}
