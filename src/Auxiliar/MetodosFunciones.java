/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar;

/**
 *
 * @author antoniopallaresruiz
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
    
// para incluir en MetodosFunciones.java

   public static double[][] tablaGraficaTchev(Funcion f,
            int N, double a, double b){
        double[][] tabla=new double[N][2];
        for (int i = 0; i < tabla.length; i++) {
            tabla[i][0]=a+(Math.cos((2*i+1)*Math.PI/(2*N))+1)/2*(b-a);
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
        public double eval(double x){
            return f.eval(x) * g.eval(x);
        }
    }
public static class division implements Funcion{
        Funcion f;
        Funcion g;

        public division(Funcion f1,Funcion g1){
            f=f1;
            g=g1;
        }
        public double eval(double x){
            return f.eval(x) / g.eval(x);
        }
    }
/**
 * Es una clase para construir la composicion de dos funciones
 * Uso es
 * f.compuesta(g)
 * para construir fog
 */
public static class compuesta implements Funcion{
        Funcion f;
        Funcion g;

        public compuesta(Funcion f1,Funcion g1){
            f=f1;
            g=g1;
        }
        public double eval(double x){
            return f.eval(g.eval(x));
        }
    }

}
