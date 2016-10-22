/**
 * Polinomio.java
 *
 * Modificado el 16 de diciembre de 2004, 12:35
 * Modificado el 14 de enero de 2005, 22:15
 * Modificado en enero/febrero de 2006
 * Modificado en abril de 2007
 * Modificado en abril de 2008
 * Modificado en Marzo de 2009
 * Modificado en octubre de 2009
 * VERSION INICIAL para practica 2 (2009-2010).
 */
package Practica3;

import ORG.netlib.math.complex.Complex;
import Practica2.*;

//import java.util.*;   

 /**
 *
 * @author  Alumnos de "Metodos Numericos" desde 2002-2003,
 *          profesores:  Victor Jimenez y Antonio Pallares.
 *
 * utiliza la clase Complex creada por   Alexander Anderson
 * Copyright (c) 1997 - 2001, depositada en NETLIB
 *
 * METODOS NUMERICOS
 * LICENCIATURA DE MATEMATICAS
 * UNIVERSIDAD DE MURCIA
 */

/**
 * Cada objeto polinomio p(x)=a0 + a1 x + a2 x^2 + ... + ak x^k
 * tiene como atributo una sucesion de numeros complejos
 * con sus coeficientes {a0,a1,...,ak}
 */
public class Polinomio implements Funcion{

    public Complex[] coef;
    static double precision = 5E-16;

    /**
     * Constructor de un objeto Polinomio. <p>
     * Por defecto es el polinomio nulo.
     */
    public Polinomio() {
        coef = new Complex[1];
        coef[0] = new Complex();
    }

    /**
     * Sobrecarga del Constructor de un objeto Polinomio. <p>
     * Polinomio(coeficientes) crea el polinomio cuyos coeficientes son
     * los reales de la lista "coeficientes".
     * @param coeficientes
     */
    public Polinomio(double[] coeficientes) {
        int n = coeficientes.length;
        coef = new Complex[n];

        /**
         * Comenzamos eliminando los ceros superfluos de la sucesion
         * de coeficientes
         */
        for (int j = n - 1; j > 0; j--) {
            if (Math.abs(coeficientes[j]) < precision) {
                n = n - 1;
            } else {
                break;
            }
        }
        coef = new Complex[n];
        for (int i = 0; i < n; i++) {
            coef[i] = new Complex(coeficientes[i]);
        }
    }

    /**
     * Sobrecarga del Constructor de un objeto Polinomio. <p>
     * Polinomio(coeficientes) crea el polinomio cuyos coeficientes
     * son los complejos de la lista "coeficientes".
     * @param coeficientes
     */
    
     public Polinomio(Complex[] coeficientes) {
        int n = coeficientes.length;

        for (int j = n - 1; j > 0; j--) {
            if (coeficientes[j].abs() < precision) {
                n = n - 1;
            } else {
                break;
            }
        }
        coef = new Complex[n];
        for (int i = 0; i < n; i++) {
            coef[i] = new Complex(coeficientes[i]);
        }
    }

    /**
     * Sobrecarga del Constructor de un objeto Polinomio. <p>
     * Polinomio(q) crea un polinomio con los mismos coeficientes que q.
     * @param q
     */
    
    public Polinomio(Polinomio q) {
        int n = q.coef.length;
        coef = new Complex[n];

        for (int i = 0; i < n; i++) {
            coef[i] = new Complex(q.coef[i]);
        }
    }

    /**
     * Sobrecarga del Constructor de un objeto Polinomio. <p>
     * Polinomio(z) crea un polinomio constante igual al complejo z.
     * @param z
     */
    public Polinomio(Complex z) {
        coef = new Complex[1];

        coef[0] = z;
    }

    /**
     * Sobrecarga del Constructor de un objeto Polinomio. <p>
     * Polinomio(x) crea un polinomio constante igual al real x.
     * @param x
     */
    public Polinomio(double x) {
        coef = new Complex[1];

        coef[0] = new Complex(x, 0);
    }

    /**
    Metodo para obtener el grado del polinomio <p>
    *
    * USO   p.grado()
     * @return 
    */
    public int grado() {
        return coef.length - 1;
    }

    public Complex[] coeficientes() {
        return coef;
    }

    /**
     * MÃ©todo para escribir un polinomio. <p>
     * Construye una cadena de caracteres (String) en la
     * que aparece escrito el polinomio.
     *
     * USO 
     * p.escribe()
     * @return 
     */
    public String escribe() {
        Complex[] y = coef;
        int m = y.length;
        String cadena = "( ";
        String renglon = "";
        String aux;

        for (int k = 0; k < m; k++) {
            if ((m > 1) && (y[k].abs() < precision)) {
                continue; // evita escribir sumandos nulos
            }
            aux = "";
            switch (k) {
                case 0:
                    aux = ""; // tÃ©rmino independiente
                    break;
                case 1:
                    aux = " x"; // primera potencia
                    break;
                default:
                    aux = " x^" + k; // resto de potencias
                    break;
            }
            if (k == m - 1) {
                aux = aux + " )";
            } else {
                aux = aux + " + ";
            }
            aux = y[k].toString() + aux;
            if ((renglon + aux).length() > 90) {
                cadena = cadena + renglon + "\n  ";
                renglon = aux;
            } else {
                renglon = renglon + aux;
            }
        }
        return cadena + renglon;
    }

    /**
     * Sobrecarga del mÃ©todo toString para que pueda escribirse directamente
     * el polinomio
     */
    @Override
    public String toString() {
        return escribe();
    }

     /**
     * MÃ©todo suma para sumar dos polinomios
     *
     * DATOS DE ENTRADA
     * Polinomio q
     *
     * USO
     * p.suma(q) proporciona el Polinomio p+q
     * @param q
     * @return 
     */
    public Polinomio suma(Polinomio q) {
        Complex[] suma;
        int n = coef.length;
        int m = q.coef.length;
        int k = Math.max(n, m);
        suma = new Complex[k];

        if (n < k) {
            for (int i = 0; i < n; i++) {
                suma[i] = coef[i].add(q.coef[i]);
            }
            for (int i = n; i < k; i++) {
                suma[i] = q.coef[i];
            }
        } else {
            for (int i = 0; i < m; i++) {
                suma[i] = coef[i].add(q.coef[i]);
            }
            for (int i = m; i < k; i++) {
                suma[i] = coef[i];
            }
        }
        return new Polinomio(suma);
    }
 
    /**
     * Sobrecarga el metodo suma como metodo estatico
     * que define el operador binario suma
     * 
     * @param p
     * @param q
     * @return  p.suma(q)
     * 
     * Uso  Polinomio.suma(p,q) que equivale a 
     */
    public static Polinomio suma(Polinomio p,Polinomio q){

    	return p.suma(q);
    }

    /**
     * Metodo resta para restar dos polinomios
     *
     * DATOS DE ENTRADA
     * Polinomio q
     *
     * USO
     * p.resta(q) proporciona el Polinomio p-q
     * @param q
     */
    public Polinomio resta(Polinomio q) {

        return new Polinomio(suma(q.producto(-1)));
    }




    /**
     * MÃ©todo para multiplicar dos polinomios <p>
     *
     * DATOS DE ENTRADA <p>
     * Polinomio q <p>
     *
     * USO <p>
     * p.producto(q) proporciona el Polinomio pq
     */
    public Polinomio producto(Polinomio q) {
        Complex[] pq;
        int n = coef.length;
        int m = q.coef.length;
        int k = m + n - 1;
        pq = new Complex[k];

        for (int i = 0; i < k; i++) {
            pq[i] = new Complex(0);
            for (int j = 0; j <= i; j++) {
                if (j >= n) {
                    continue;
                }
                if ((i - j) >= m) {
                    continue;
                }
                pq[i] = pq[i].add(coef[j].mul(q.coef[i - j]));
            }
        }
        return new Polinomio(pq);
    }

    /**
     * Sobrecarga del mÃ©todo producto para multiplicar
     * un polinomio por un escalar <p>
     *
     * DATOS DE ENTRADA <p>
     * Complex z <p>
     *
     * USO <p>
     * p.producto(z) proporciona el Polinomio pq
     */
    public Polinomio producto(Complex z) {
        Complex[] pq;
        int n = coef.length;
        pq = new Complex[n];

        for (int i = 0; i < n; i++) {
            pq[i] = z.mul(coef[i]);
        }
        return new Polinomio(pq);
    }

     /**
     * Sobrecarga del mÃ©todo producto para multiplicar
     * un polinomio por un escalar real
     */
    public Polinomio producto(double x) {
        Complex z = new Complex(x, 0);

        return producto(z);
    }

    /* Metodo para calcular el polinomio derivada */
    public Polinomio derivada() {
        Complex[] dp;
        int n = coef.length;

        if (n == 1) {
            return new Polinomio();
        } else {
            dp = new Complex[n - 1];
            for (int i = 0; i < n - 1; i++) {
                dp[i] = coef[i + 1].mul(new Complex(i + 1));
            }
            return new Polinomio(dp);
        }
    }

   

    /**
     * Metodo para dividir dos polinomios <p>
     *
     * DATOS DE ENTRADA <p>
     * Polinomio q <p>
     *
     * UTILIZACION <p>
     * p.div(q) proporciona el resto y el cociente de p/q
     */
    public Polinomio[] div(Polinomio divisor) throws ErrorPolinomios {
        Polinomio[] div = new Polinomio[2]; // div[0] contendra el resto
        // div[1] contendra el cociente
        int n = coef.length; // grado del dividendo + 1
        int m = divisor.coef.length; // grado del divisor + 1
        int k = n - m + 1;
        Complex[] aux = new Complex[n];
        Complex[] resto = new Complex[n]; // lista para guardar los restos
        Complex[] cociente = new Complex[k]; // lista para guardar los cocientes

        if (divisor.coef[m - 1].abs() == 0) {
            throw new ErrorPolinomios(); // el divisor es nulo
        }
        if (m > n) {
            div[1] = new Polinomio(); // el cociente es cero
            div[0] = new Polinomio(coef);  // el resto es el dividendo
            return div;
        }
        if (m == 1) {
            div[0] = new Polinomio(); // el resto es 0
            for (int j = 0; j < n; j++) {
                aux[j] = coef[j].div(divisor.coef[0]);
            }
            div[1] = new Polinomio(aux);
            return div;
        }
        for (int j = 0; j <= n - 1; j++) {
            resto[j] = coef[j];
        }
        for (int j = n - m; j >= 0; j--) {
            cociente[j] = resto[m + j - 1].div(divisor.coef[m - 1]);
            resto[m + j - 1] = new Complex(0);
            for (int h = m - 2; h >= 0; h--) {
                resto[j + h] =
                        resto[j + h].sub(divisor.coef[h].mul(cociente[j]));
            }
        }
        div[0] = new Polinomio(resto);
        div[1] = new Polinomio(cociente);
        return div;
    }

    /**
     * Metodo de evaluacion de polinomios
     * con coeficientes y variable compleja
     */
    public Complex eval(Complex z) {
        
	// falta determinar n=grado del polinomio


        Complex z2 = new Complex(0); //para devolver el valor del polinomio en z

        
	// falta evaluar z2 con el mÃ©todo de Horner


        return z2;
    }

    /**
     * Sobrecarga del metodo eval para evaluar polinomios reales
     * en variables reales devolviendo el nÃºmero real con el valor del polinomio
     */
    @Override
    public double eval(double x) {
     
        return 0;
    }

   
    /* Exception para ser lanzada por los distintos metodos */
    public static class ErrorPolinomios extends Exception {

        ErrorPolinomios() {
            super("Error: polinomios");
        }
    }
}