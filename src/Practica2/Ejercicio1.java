/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica2;

/**
 *
 * @author Alejandro Torreblanca
 */
public class Ejercicio1 {
    //Apartado 2
    public static class F1 implements Funcion{
        public F1(){
        }
        
        @Override
        public double eval(double x){
            if (x >=0) 
                return 1+ Math.cos(x*x*Math.exp(x));
            return (Math.exp(-0.1*x)+ Math.cos(x*x*Math.exp(-x)));
        }
    }
    //Apartado 3
    public static class F2 implements Funcion{
        public F2(){
            }

        @Override
        public double eval(double x){
            return Math.PI+Math.sqrt(1+x*x);
        }
    }  
    
    public static class F3 implements Funcion{
            int n=1;
            public F3(int n){
                this.n=n;
            }
            
        @Override
        public double eval(double x){
            double pot=1;
            for (int i = 0; i < n; i++) {
                pot *= x;
            }
            return pot*Math.exp(x);
        }
    }  

    public static void main(String[] args) {
        F1 f=new F1();
        F2 g=new F2();
        int numpuntos=11;
        double[][] tabla_f= MetodosFunciones.tablaGrafica(f, numpuntos, -5, 5);
        
        double[][] tabla_g= MetodosFunciones.tablaGrafica(g, numpuntos, -5, 5);
//            System.out.println("g(3.4) = "+ g.eval(3.4));
            
        MetodosFunciones.suma fMaSg= new MetodosFunciones.suma(f,g);
        double[][] tabla_suma= MetodosFunciones.tablaGrafica(fMaSg, numpuntos, -5, 5);
        
        
        for (int i = 0; i < numpuntos; i++) {
           //System.out.println("tabla_f["+i+"]= " + tabla_f[i][0] +" , "+ tabla_f[i][1] );
           
           // System.out.println("tabla_f["+i+"]= " + tabla_g[i][0] +" , "+ tabla_g[i][1] );
          
           // System.out.println("tabla_f["+i+"]= " + tabla_suma[i][0] +" , "+ tabla_suma[i][1] );
        }

        F3 p1= new F3(1);
        F3 p3= new F3(3);
        double[][] tabla_p1= MetodosFunciones.tablaGrafica(p1, numpuntos, -5, 5);
        double[][] tabla_p3= MetodosFunciones.tablaGrafica(p3, numpuntos, -5, 5);

        for (int i = 0; i < numpuntos; i++) {
           System.out.println("tabla_p1["+i+"]= " + tabla_p1[i][0] +" , "+ tabla_p1[i][1] );
           System.out.println("tabla_p3["+i+"]= " + tabla_p3[i][0] +" , "+ tabla_p3[i][1] );
        }

    }
}