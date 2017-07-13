
package primeraEntregaCN1v;

import auxiliar.*;

/**
 * Ejercicio 4 de la entrega.
 * @author AlejandroTorreblanca
 */
public class Ejercicio4 {

    /**
     * Función de la primera integral.
     */
    public static class F implements Funcion{
        public double eval(double x){
          return Math.sqrt(x)*Math.pow(Math.E,-x);
        }
    }
    
    /**
     * Establecemos como acotación de la primera integral la función x*e^(-x), si la integramos tenemos la función
     * que se muestra acontinuación.
     */
    public static class Acotacion implements Funcion{
        public double eval(double x){
          return -Math.pow(Math.E, -x)*(1-x);
        }
    }
    
    /**
     * Función de la segunda integral.
     */
    public static class G implements Funcion{
        public double eval(double x){
          return Math.sin(1/x);
        }
    }
    
    /**
     * Función de la tercera integral.
     */
    public static class H implements Funcion{
        public double eval(double x){
            if(x==1)    //Realizamos esta comprobación para que la función sea continua y de esta manera no tener problemas a la hora de integrar.
                return 0;
            return Math.log(x)/(Math.pow(x-1, 1/3));
        }
    }
    
    public static void main(String[] args) {
       
        F f=new F();
        G g=new G();
        H h=new H();
        Acotacion a=new Acotacion();
        double tolerancia=0.0001;
        double T=tolerancia/2;
        
    /*Primera Integral.
        Realizamos una acotación de la cola mediante la función Acotacion, el punto k a apartir del cual se utilizará
        la cota depende del error que deesemos obtener.
        */
        int k=3;
        double aux=a.eval(k);
        while(aux>T)    //Buscamos un punto a partir del cual la función es menor que el error que deseamos cometer.
        {
            k*=2;
            aux=a.eval(k);
        }
        double resultado1=MetodosFunciones.IntegralAdaptativaItUsandoPila(f, 0, k, T); //Integramos en el intervalo (0,k).
        resultado1+=aux;    //Sumamos la acotación del intervalo (k,+infinito).
        System.out.println("Resultado 1= "+resultado1 );
    
    /*Segunda integral
        En este caso el problema lo tenemos en los intervalos de la forma (0,k), para resolverlo acotamos este intervalo por
        la función j(x)=2 cuya integral será una cota de la integral de g(x).
        El punto k vendrá determinado por la tolerancia, si establecemos k=Tolerancia tenemos obtenemos una buena aproximación.
        */
        double resultado2=MetodosFunciones.IntegralAdaptativaItUsandoPila(g, T, 1, T); //Integramos en el intervalo (T,1).
        resultado2+=2*T;    
        System.out.println("Resultado 2= "+resultado2 );

    /*Tercera integral
        En este caso ya hemos solucionado el problema que había en x=1, ya que tal y como hemos definido la función ahora
        es continua y podemos integrar sin problemas.
        */
        double resultado3=MetodosFunciones.IntegralAdaptativaItUsandoPila(h, 1, 2, T);
        System.out.println("Resultado 3= "+resultado3 );
    }
}
