/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primeraEntregaCN1v;

import auxiliar.*;

/**
 *
 * @author Alexjandro
 */
public class Ejercicio4 {

    public static class F implements Funcion{
        public double eval(double x){
          return Math.sqrt(x)*Math.pow(Math.E,-x);
        }
    }
    
    public static class G implements Funcion{
        public double eval(double x){
          return Math.sin(1/x);
        }
    }
    
    public static class H implements Funcion{
        public double eval(double x){
            return Math.log(x)/(Math.pow(x-1, 1/3));
        }
    }
    
    public static class Acotacion implements Funcion{
        public double eval(double x){
          return -Math.pow(Math.E, -x)*(1-x);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        F f=new F();
        G g=new G();
        H h=new H();
        Acotacion a=new Acotacion();
        double tolerancia=0.0001;
        double T=tolerancia/2;
        
    //Primera Integral
        int k=3;
        double aux=a.eval(k);
        while(aux>T)
        {
            k*=2;
            aux=a.eval(k);
        }
        double resultado1=MetodosFunciones.IntegralAdaptativaItPila(f, 0, k, T);
        resultado1+=aux;
        System.out.println("Resultado 1= "+resultado1 );
    
    //Segunda integral
        double resultado2=MetodosFunciones.IntegralAdaptativaItPila(g, T, 1, T);
        resultado2+=2*T;
        System.out.println("Resultado 2= "+resultado2 );

    //Tercera integral
        double resultado3=MetodosFunciones.IntegralAdaptativaItPila(h, 1, 2, T);
        System.out.println("Resultado 3= "+resultado3 );
        
    }
}
