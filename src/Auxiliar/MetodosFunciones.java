
package auxiliar;
import java.util.LinkedList;
import java.util.Stack;
/**
 *
 * @author AlejandroTorreblanca
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


/**
 * Método para aproximar la derivada de una función en un punto con el
 * cociente
 * (f(x+h)-f(x))/h
 * La aproximación es de orden O(h)
 */
    public static class derivada2puntos implements Funcion{
        Funcion f;
        double h; //paso
        public derivada2puntos(Funcion f, double h){
            this.f=f;
            this.h=h;
        }
        @Override
        public double eval(double x){
            return (f.eval(x+h)-f.eval(x))/h;
        }
    }
    
  /**
   * Método para aproximar la derivada de una función en un punto con el cociente
   * (f(x+h)-f(x-h))/ 2h
   * la aproximación es de orden 0(h^2)
   * 
   */  
    public static class derivada3puntos implements Funcion{
        Funcion f;
        double h; //paso
        public derivada3puntos(Funcion f, double h){
            this.f=f;
            this.h=h;
        }
        @Override
        public double eval(double x){
            return (f.eval(x+h)-f.eval(x-h))/(2*h);
        }
    }
    
   /**
   * Método para aproximar la derivada de una función en un punto con el cociente
   * (f(x+h)+f(x-h)-2f(x))/ h^2 
   * la aproximación es de orden 0(h^2)
    */
    public static class derivadaSegunda3puntos implements Funcion{
        Funcion f;
        double h; //paso
        public derivadaSegunda3puntos(Funcion f, double h){
            this.f=f;
            this.h=h;
        }
        @Override
        public double eval(double x){
            return (f.eval(x+h)-2*f.eval(x)+f.eval(x-h))/(h*h);
        }
    }
    
    public static class derivada5puntos implements Funcion{
        Funcion f;
        double h; //paso
        public derivada5puntos(Funcion f, double h){
            this.f=f;
            this.h=h;
        }
        @Override
        public double eval(double x){
            return (f.eval(x-2*h)-8*f.eval(x-h)+8*f.eval(x+h)-f.eval(x+2*h))/(12*h);
        }
    }

    public static class derivadaExtrapolacion implements Funcion{
        double derivada;
        double h;
        int k=1;
        boolean imprimepasos=false;
        Funcion f;

        public derivadaExtrapolacion(Funcion f, double h){
            this.f=f;
            this.h=h;
        }
        public derivadaExtrapolacion(Funcion f, double h,int k, boolean imprimepasos){
            this.f=f;
            this.h=h;
            this.k=k;
            this.imprimepasos=imprimepasos;
        }

        @Override
        public double eval(double x){
            double[][] D=new double[k+1][];
            double paso=h;
            for (int n = 0; n <= k; n++) {
                D[n]=new double[n+1];
                derivada3puntos f3=new derivada3puntos(f, paso);
                D[n][0]=f3.eval(x);
                paso=paso/2;
            }
            double sde=1;
            for (int j = 1; j <= k; j++) {
                sde=4*sde;
                for (int n = j; n <= k; n++) {

                     D[n][j]=D[n][j-1]+(D[n][j-1]-D[n-1][j-1])/(sde-1);
                }
            }
            if(imprimepasos){
                System.out.println("n \t"+ "      D[n][0]    "+ "  \t     D[n][1]"+" \t    D[n,2] "
                        + "\t    .... ");
                for (int n = 0; n < k; n++) {
                    System.out.println(n+ "\t "+MetodosListas.toString(D[n]));;
                }
            }
            return D[k][k];
        }
    }


 /** Metodo que implementa la regla compuesta del trapecio en el intervalo
     * [a,b] usando N intervalos ( h=(b-a)/N )
     *
     * @param f
     * @param a
     * @param b
     * @param N
     * @return
     */
    public static double integralTrapecio(Funcion f, double a, double b, int N){
        double suma=f.eval(a)+f.eval(b);
        double h=(b-a)/N;
        for (int i = 1; i < N; i++) {
            suma =suma + 2* f.eval(a+i*h);
        }
        return suma * h / 2 ;
    }

     /** Metodo que implementa la regla compuesta de Simpson 1/3 utilizando 2N
     * divisiones del intervalo $[a,b]$  ( h= (b-a)/(2N) )
     *
     * @param f
     * @param a
     * @param b
     * @param N
     * @return
     */
     public static double integralSimpson(Funcion f, double a, double b, int N){
        double suma=f.eval(a)+f.eval(b);
        double h=(b-a)/(2*N);
        for (int i = 1; i <= N; i++) {
            suma =suma + 4* f.eval(a+(2*i-1)*h);
        }
         for (int i = 1; i < N; i++) {
            suma= suma + 2* f.eval(a+2*i*h);
         }
        return suma * h / 3;
    }

     /** Metodo que implementa la regla compuesta que usa la cuadratura de Gauss
      * de 5 puntos en N divisiones del intervalo $[a,b]$  ( h= (b-a)/N )
      *
      * @param f
      * @param a
      * @param b
      * @param N
      * @return
      */
     public static double integralGauss(Funcion f, double a, double b, int N){
         double[] x= new double[5];
         double[] c= new double[5];
         // nodos y pesos en [-1,1]
         x[2]=0.0; c[2]=0.568888888888889;
         x[1]=-0.538469310105683; c[1]=0.478628670499366;
         x[3]=0.538469310105683; c[3]=0.478628670499366;
         x[0]=-0.906179845938664; c[0]=0.236926885056189;
         x[4]=0.906179845938664; c[4]=0.236926885056189;
         double suma= 0;
         double h=(b-a)/N;
         for (int i = 0; i < N; i++) {
             double ai=a+i*h;
             for (int j = 0; j < 5; j++) {
                 suma=suma+ c[j]* f.eval(ai+(1+x[j])/2 * h );
             }
         }
         return suma * h / 2;
     }

     /**
      *
      *  Montecarlo dimension 1 con N evaluaciones
      * @param f
      * @param a
      * @param b
      * @param N
      * @return
      */
     public static double intMonteCarlo1(Funcion f, double a, double b, int N){
         double suma=0;

         for (int i = 0; i < N; i++) {
             suma=suma+f.eval(a+Math.random()*(b-a));

         }
         return suma/N*(b-a);
     }

      public static double integralAdaptada(Funcion f, double a, double b, double precision, int nProfundidad){
        if(nProfundidad==0){
            System.out.println("Se alcanzó el nivel máximo permitido ABORTAMOS");
            System.exit(0);
            return Double.NaN;
        }
        double fA=f.eval(a);
        double fB=f.eval(b);
        double h=(b-a);
        double m= a+h/2;
        double fM=f.eval(m);
        double integral= (fA+4*fM+fB)*h/6;
        h=h/2;
        double fM1=f.eval(a+h/2);
        double integral0=(fA+ 4*fM1+fM)*h/6;
        double fM2=f.eval(m+h/2);
        double integral1=(fM+ 4*fM2+fB)*h/6;
        if(Math.abs(integral - integral0 - integral1)> precision){
            nProfundidad= nProfundidad-1;
            return integralAdaptada(f, a, m, precision/2, nProfundidad )+
                    integralAdaptada(f, m, b, precision/2, nProfundidad ); 
        }
        return integral0+integral1;
    } 
      
    /**
     * Método pedido en el ejercicio 3 de la entrega.
     * La implementación es la misma que la del libro de Burden-Faires, se utilizan arrays definidos 
     * al principio del programa y de tamaño fijo. Al implementarlos de esta manera tenemos dos problemas, 
     * o bien no se llenan los arrays por completo, o se limita el tamaño de ellos ya que son finitos.
     * Debido a esto se ha implementado el algoritmo de otra forma más eficiente al final de esta clase.
     * Este programa funciona correctamente pero en el programa principal ESTE ALGORITMO NO SE USA, se usará el otro.
     * @param f
     * @param a
     * @param b
     * @param Tol
     * @param N
     * @return 
     */
    public static double IntegralAdaptativaIt(Funcion f, double a,double b, double Tol,int N)
    {
        double APP=0;
        int i=1;
        double[] TOL=new double[N+10];
        double[] A=new double[N+10];
        double[] h=new double[N+10];
        double[] FA=new double[N+10];
        double[] FB=new double[N+10];
        double[] FC=new double[N+10];
        double[] S=new double[N+10];
        double[] L=new double[N+10];
        double[] V=new double[10];
        double FD,FE,S1,S2;
        
        TOL[i]=10*Tol;
        A[i]=a;
        h[i]=(b-a)/2;
        FA[i]=f.eval(a);
        FB[i]=f.eval(b);
        FC[i]=f.eval(a+h[i]);
        S[i]=h[i]*(FA[i]+4*FC[i]+FB[i])/3;
        L[i]=1;
        
        while(i>0)
        {
            FD=f.eval(A[i]+h[i]/2);
            FE=f.eval(A[i]+3*h[i]/2);
            S1=h[i]*(FA[i]+4*FD+FC[i])/6;
            S2=h[i]*(FC[i]+4*FE+FB[i])/6;
            V[1]=A[i];
            V[2]=FA[i];
            V[3]=FC[i];
            V[4]=FB[i];
            V[5]=h[i];
            V[6]=TOL[i];
            V[7]=S[i];
            V[8]=L[i];
            i-=1;             
            if(Math.abs((S1+S2)-V[7])<V[6])
            {
                APP=APP+(S1+S2);
            }
            else
            {
                if(V[8]>=N)
                {
                  System.err.println("LEVEL EXCEDED");
                  System.exit(1);
                }
                else
                {
                    i+=1;
                    A[i]=V[1]+V[5];
                    FA[i]=V[3];
                    FC[i]=FE;
                    FB[i]=V[4];
                    h[i]=V[5]/2;
                    TOL[i]=V[6]/2;
                    S[i]=S2;
                    L[i]=V[8]+1;

                    i+=1;
                    A[i]=V[1];
                    FA[i]=V[2];
                    FC[i]=FD;
                    FB[i]=V[3];
                    h[i]=h[i-1];
                    TOL[i]=TOL[i-1];
                    S[i]=S1;
                    L[i]=L[i-1];
                }
            }
        }
        return APP;
    }
    
    /**
     * Esta clase se ha creado para almacenar las propiedades de un intervalo sobre el que estamos 
     * aplicando el método de la Integral Adaptativa Iterada.
     */
    public static class Intervalo{
         double A;      //Limite izquierdo del intervalo
         double B;      //Limite derecho del intervalo
         double m;      //Punto medio del intervalo
         double tol;    //Tolerancia que se debe aplicar al intervalo
         double S;      //Aproximación de Simpson del intervalo
        public Intervalo(double A,double B,double m,double tol, double S)
        {
            this.A=A;
            this.B=B;
            this.m=m;
            this.tol=tol;
            this.S=S;
        }
    }
    
    /**
     * Esta clase se ha creado para ir almacenando los valores de los puntos que se piden dibujar en el 
     * aparado 3 del ejercicio 3 de la entrega.
     */
    public static class Par{
        double x;   //Punto x
        double y;   //punto f(x)
        public Par (double x, double y){
            this.x=x;
            this.y=y;
        }
    }
    
    /**
     * Sobrecarga del método IntegralAdaptativaItUsandoPila, si solo se desea obtener el valor de la integral
     * se usará este metodo. Aunque se calcule la lista de puntos solicitada en el aprtado 3 no la utilizamos,
     * solo retornamos el valor de la integral.
     * @param f Función que se desea integrar.
     * @param a Límite izquierdo del intervalo de integración.
     * @param b Límite derecho del intervalo de integración.
     * @param Tol Tolerancia.
     * @return Resultado de la integral de f en (a,b).
     */
    public static double IntegralAdaptativaItUsandoPila(Funcion f, double a,double b, double Tol)
    {
        double[] resultado=new double[2];
        double[][] tabla=IntegralAdaptativaItUsandoPila(f, a, b, Tol, resultado);        
        return resultado[0];
    }
    
    /**
     * Método IntegralAdaptativaIt pero usando una pila para las iteraciones, de está forma usamos la memoria
     * de forma más eficiente. En este caso como debemos devolver la lista de puntos y además el valor de la integral,
     * la lista de puntos lo retornamos como de normal y la variable resultado la retornamos mediante el paso por referencia para poder
     * extraer su valor fuera del método.
     * @param f Función que se desea integrar.
     * @param a Límite izquierdo del intervalo de integración.
     * @param b Límite derecho del intervalo de integración.
     * @param Tol Tolerancia.
     * @param resultado Array donde se guardará el resultado de la integral.
     * @return Lista de puntos (x,f(x)) que se pide dibujar en el apartado 3 del ejercicio 3 de la entrega.
     */
    public static double[][] IntegralAdaptativaItUsandoPila(Funcion f, double a,double b, double Tol, double[] resultado)
    {
        double APP=0;
        double FA,FB,FC,FD,FE,S1,S2,S3,a_aux;
        Stack<Intervalo> pila = new Stack<>();      //Pila para ir guardando los intervalos que se deesen procesar.
        LinkedList<Par> lista=new LinkedList<>();   //Lista para guardar los puntos que se introduciran en la lista.
        double m=(b-a)/2;
        FA=f.eval(a);
        FB=f.eval(b);
        FC=f.eval(a+m);
        FD=f.eval(a+m/2);
        FE=f.eval(a+3*m/2);
        S1=m*(FA+4*FC+FB)/3;
        S2=m*(FA+4*FD+FC)/6;
        S3=m*(FC+4*FE+FB)/6;
        a_aux=a;
        
        if(Math.abs(S2+S3-S1)<Tol)
        {
            APP=APP+(S3+S2);
        }
        else    //Introducimos los dos primeros intervalos en los que iterar.
        {
            Intervalo I1=new Intervalo(a, a+m, m/2, Tol/2, S2); 
            Intervalo I2=new Intervalo(a+m, b, m/2, Tol/2, S3);
            pila.push(I2);
            pila.push(I1);
            Par p1=new Par(a, FA);
            Par p2=new Par(b, FB);
            lista.add(p1);
            lista.add(p2);
        }
        
        while (!pila.empty()) {     //Mientras que haya intervalos en la pila seguimos iterando.
            Intervalo I=pila.pop();
            a=I.A;
            b=I.B;
            m=I.m;
            S1=I.S;
            Tol=I.tol;
            FA=f.eval(a);
            FB=f.eval(b);
            FC=f.eval(a+m);
            FD=f.eval(a+m/2);
            FE=f.eval(a+3*m/2);
            S2=m*(FA+4*FD+FC)/6;
            S3=m*(FC+4*FE+FB)/6;
            if(Math.abs(S2+S3-S1)<Tol)
            {
                APP=APP+(S3+S2);
            }
            else    //Hemos sobrepasado la tolerancia asique introducimos otros dos intervalos en la pila.
            {
                Intervalo I1=new Intervalo(a, a+m, m/2, Tol/2, S2);
                Intervalo I2=new Intervalo(a+m, b, m/2, Tol/2, S3);
                pila.push(I2);
                pila.push(I1);
                if(a!=a_aux)    //Solo añadimos el punto "a" si no se había añadido anteriormente.
                 {
                    Par p1=new Par(a, FA);
                    lista.add(p1);
                    a_aux=a;
                }
            }
        }
        int j=lista.size();
        double[][] tabla=new double[lista.size()][2];
        for (int i = 0; i < j; i++) {   //Creamos el array de puntos a retornar a partir de la lista que hemos ido creando.
            Par p=lista.pop();
            tabla[i][0]=p.x;
            tabla[i][1]=p.y;
        }
        resultado[0]=APP;   //Almacenamos el valor de la integral
        return tabla;
    }
}

