/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExamenEnero;

import ORG.netlib.math.complex.Complex;
import auxiliar.Polinomio;
import com.sun.corba.se.impl.oa.poa.Policies;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejandro
 */
public class Ejercicio4 {

    
    public static double a(int k)
    {
     int dni= 48745782; 
     int signo;
     int exponente=(int)dni/(k+1);
     if (exponente%2==0)
         signo=1;
     else signo=-1;
     double aux=(dni*(k+1))%(Math.E+1);
     return signo*aux;  
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double[] coef=new double[7];
        for (int i = 0; i < 7; i++) {
            coef[i]=a(i);
        }
        Polinomio p= new Polinomio(coef);
        try {
            Complex[] raices= p.buscaRaicesAleatorioPCR(0.01, 500, 500);
            for (int i = 0; i < raices.length; i++) {
                System.out.println("La imagen de la raiz "+(i+1)+" es :"+p.eval(raices[i]));
            }
        } catch (Polinomio.ErrorPolinomios ex) {
        }
        
               
    }
}
