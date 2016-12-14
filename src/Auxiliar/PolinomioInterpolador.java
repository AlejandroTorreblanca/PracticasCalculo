
package auxiliar;

import ORG.netlib.math.complex.Complex;

/**
 *
 * @author AlejandroTorreblanca
 */
public class PolinomioInterpolador extends Polinomio{

    public double[] coefFormaNewton;
    public double[] nodos;
    private double[] auxiliar;

    public PolinomioInterpolador(double[][] xy) {
        this.nodos=new double[xy.length];
        this.coefFormaNewton = coefyNodosH(xy);
        coeficientesPol();
        auxiliar =new double[xy.length];
    }

    public PolinomioInterpolador() {
        coefFormaNewton=new double[1];
        nodos=new double[1];
        coef =new Complex[1];
        auxiliar =new double[1];
    }

     double[] coefyNodosL(double[][] xy){
        int m=xy.length;   // m=n+1
        double[] p =new double[m];
             nodos=new double[m];
        for (int i = 0; i <m; i++) {
            p[i]=xy[i][1];
            nodos[i]=xy[i][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = m-1; j >= i; j--) {
                p[j]=(p[j]-p[j-1])/(nodos[j]-nodos[j-i]);
            }
        }
        return p;
    }
      double[] coefyNodosH(double[][] xy){
        int m=xy.length;   // m=numero de puntos a interpolar

        int[] l=new int[m];
        int[] aux=new int[m+1];
        aux[0]=0;
          for (int i = 0; i < m; i++) {
              l[i]=xy[i].length-1;
              aux[i+1]=aux[i]+l[i];
          }
        int n=aux[m]-1;
        nodos =new double[n+1]; // nodos (x_i repetidas)
        double[] p = new double[n+1]; // coeficientes de la forma de Newton
          for (int i = 0; i < m; i++) {
              for (int j = aux[i]; j < aux[i+1]; j++) {
                  nodos[j]=xy[i][0];
              }
          }
          for (int k = 0; k <= n; k++) { //recorremos las columas del diagram de DDN
              for (int i = m-1; i >= 0; i--) { //recorremos los nodos repetidos con i y j
                  for (int j = aux[i+1]-1; j >= Math.max(aux[i], k); j--) {
                      if(nodos[j-k] == nodos[j]){  // si se repiten los nodos
                          p[j]=xy[i][k+1];
                          for (int r = 2; r <= k; r++) {
                              p[j]= p[j]/r;
                          }
                      }else{  // si no se repiten los nodos
                          p[j]=(p[j]-p[j-1]) / (nodos[j] - nodos[j-k]);
                      }
                  }
              }
          }
        return p;
    }

    @Override
    public double eval(double x){
        int n= coefFormaNewton.length-1;
        double suma= coefFormaNewton[n];
        for(int i=n-1;i>=0;i--){
            suma = coefFormaNewton[i]+suma*(x-nodos[i]);
        }
        return suma;
    }

    void coeficientesPol(){
        int m=coefFormaNewton.length;
        coef = new Complex[m];
        for (int i = 0; i < m; i++) {
             coef[i]=new Complex();
        }      
        for (int i = m-1; i >=0; i--) {
            Complex auxiliar=new Complex(coefFormaNewton[i]);
            for (int k = m-1-i; k >=1; k--) {
                coef[k]=coef[k-1].sub(coef[k].scale(nodos[i]));
            }
            coef[0]=auxiliar.sub(coef[0].scale(nodos[i]));
        }
    }

    /**
     * Función  que se pide en el ejercicio 2 de la entrega.
     * Se evalúan simultáneamente las n primeras derivadas de un polinomio 
     * interpolador (de Lagrange o Hermite) utilizando la forma anidada de Newton en el punto x.
     * El procedimiento es el siguiente(utilizaremos la notación del enunciado):
     * Para calcular g0 se itera desde gm, siendo m el grado del polinomio.
     * Para calcular g'0 se itera en este caso desde g'm-1.
     * Para calcular g''0 se itera en este caso desde g''m-2.
     * Asi sucesivamente, ya que los terminos gm, g'm-1, g''m-2... son los coeficientes en la forma de Newton en las 
     * correspondientes posiciones.
     * @param x Punto de la recta real en el que se desea evaluar la derivada. 
     * @param n Número de derivadas que se desean clacular.
     * @return Lista que contiene las n derivadas del polinomio evaluadas en el punto x.
     */
    @Override
    public double[] evalDerivadas(double x, int n){
         
        int m = grado();
        double[] D= new double[n+1];
        double[][] g= new double[n+1][m+1];
        g[0][m] =coefFormaNewton[m] ;
        for (int i = m-1; i >= 0; i--) 
        {
            g[0][i] =coefFormaNewton[i]+ g[0][i+1]*(x-nodos[i]); //Calculamos los gm
            for (int j = 1; j <= n; j++) 
                g[j][i] = g[j][i+1]*(x-nodos[i])+j*g[j-1][i+1]; //Claculamos los g'm
        }
        for (int i = 0; i<=n; i++) 
            D[i]=g[i][0]; //Extraemos los g0, g'0, g''0... que son los términos buscados.
        return D;
    }
}

