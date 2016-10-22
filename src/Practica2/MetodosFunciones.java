/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Practica2;

/**
 *
 * @author Alejandro
 */
public class MetodosFunciones {

    public static double[][] tablaGrafica(Funcion f,int numpuntos, double a, double b){
        double[][] tabla=new double[numpuntos][2];
        for (int i = 0; i < tabla.length; i++) {
            tabla[i][0]=a+i*(b-a)/(numpuntos-1);
            tabla[i][1]=f.eval(tabla[i][0]);

        }
        return tabla;
    }
    public static class suma implements Funcion{
        Funcion f;
        Funcion g;

        public suma(Funcion f1,Funcion g1){
            f=f1;
            g=g1;
        }
        @Override
        public double eval(double x){
            return f.eval(x)+g.eval(x);
        }
    }
     public static class resta implements Funcion{
        Funcion f;
        Funcion g;

        public resta(Funcion f1,Funcion g1){
            f=f1;
            g=g1;
        }
        @Override
        public double eval(double x){
            return f.eval(x)-g.eval(x);
        }
    }
     
     public static class producto implements Funcion{
        Funcion f;
        Funcion g;

        public producto(Funcion f1,Funcion g1){
            f=f1;
            g=g1;
        }
        @Override
        public double eval(double x){
            return f.eval(x)*g.eval(x);
        }
    }
     
    public static class composicion implements Funcion{
        Funcion f;
        Funcion g;

        public composicion(Funcion f1,Funcion g1){
            f=f1;
            g=g1;
        }
        @Override
        public double eval(double x){
            return f.eval(g.eval(x));
        }
    } 
}