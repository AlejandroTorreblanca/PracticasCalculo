/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExamenEnero;

import auxiliar.Funcion;
import auxiliar.MetodosFunciones;


/**
 *
 * @author Alejandro
 */
public class Ejercicio2 {

     public static class F1 implements Funcion{
        @Override
        public double eval(double x){
            return Math.exp(-x*x/2);
        }
    }
     
    public static double calcularIntegral(Funcion f,double x, double h)
    {
        double integral=MetodosFunciones.integralSimpson(f, x, h, 50);
        return (1/Math.sqrt(2*Math.PI))*integral;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        double valorObjetivo=0.975723115;
        F1 f=new F1();
        double h=0.1;
        double x=0;
        double aprox=0.5+calcularIntegral(f, x,x+h);
        x=x+h;
        while((Math.abs(aprox-valorObjetivo)>10e-15))
        {
            aprox=aprox+calcularIntegral(f, x,x+h);
            x=x+h;
            h=Math.abs(aprox-valorObjetivo);
            //System.out.println(aprox+" error: "+Math.abs(aprox-valorObjetivo));
        }
            System.out.println("El punto buscado es: "+x);
            System.out.println("El valor en el punto de la integral es: "+aprox);
            System.out.println("El valor exacto es: "+valorObjetivo);
    }
    
}
