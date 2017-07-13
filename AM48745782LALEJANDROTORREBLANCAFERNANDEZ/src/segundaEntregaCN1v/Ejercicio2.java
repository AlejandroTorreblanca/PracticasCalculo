
package segundaEntregaCN1v;

import auxiliar.*;

/**
 * @author Alejandro
 */
public class Ejercicio2 {

    public static class F1 implements Funcion{
        @Override
        public double eval(double x){
            return Math.exp(x)+x*x-x-1;
        }
    }
    
    public static class F2 implements Funcion{
        @Override
        public double eval(double x){
            return x*x*x-x-1;
        }
    }
    
    public static class F3 implements Funcion{
        @Override
        public double eval(double x){
            return x*x*x*x*x*x-x-1;
        }
    }
     public static class F4 implements Funcion{
        @Override
        public double eval(double x){
            return (x-2)*(x-2)*(x-2)*Math.log(2+x*x);
        }
    }
     
    public static void main(String[] args) {
        
        F1 f= new F1();
        F2 g= new F2();
        F3 h= new F3();
        F4 p= new F4();
        
        //Apartado 2
        double raiz1;
        try {
            raiz1 = MetodosEcuacionNoLineal.interpolacionInversa3puntos(f, 1., 1.5, 2., 0.000001, 200);
            System.out.println("Raiz de F en: "+raiz1); 
        } catch (MetodosEcuacionNoLineal.ERROR ex) {
        }
        double raiz2;
        try {
            raiz2 = MetodosEcuacionNoLineal.interpolacionInversa3puntos(g, 1., 1.5, 2., 0.00001, 200);
            System.out.println("Raiz de G en: "+raiz2); 
        } catch (MetodosEcuacionNoLineal.ERROR ex) {
        }
        double raiz3;
        try {
            raiz3 = MetodosEcuacionNoLineal.interpolacionInversa3puntos(h, 1., 1.5, 2.5, 0.00001, 200);
            System.out.println("Raiz de H en: "+raiz3); 
        } catch (MetodosEcuacionNoLineal.ERROR ex) {
        }
       
        //Apartado 3
        
        double raiz4;
        System.out.println("\nApartado 3"); 
        try {
            double x=1.; double y=3.; double z=2.5;
            raiz4 = MetodosEcuacionNoLineal.interpolacionInversa3puntos(p, x, y, z, 10e-14, 200);
            System.out.println("Raiz de p en : "+raiz4+" puntos iniciales utilizados: "+x+" "+y+" "+z); 
            x=1.; y=1.5; z=2.5; 
            raiz4 = MetodosEcuacionNoLineal.interpolacionInversa3puntos(p, x, y, z, 10e-14, 200);
            System.out.println("Raiz de p en : "+raiz4+" puntos iniciales utilizados: "+x+" "+y+" "+z); 
            x=0.; y=-1.; z=4;
            raiz4 = MetodosEcuacionNoLineal.interpolacionInversa3puntos(p, x, y, z, 10e-14, 200);
            System.out.println("Raiz de p en : "+raiz4+" puntos iniciales utilizados: "+x+" "+y+" "+z); 
            //A pesar de establecer una precisión muy pequeña no hemos conseguido aproximar la raiz X=2 todo lo finamente que deseabamos.
        } catch (MetodosEcuacionNoLineal.ERROR ex) {
        }
    }
}
