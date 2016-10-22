/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica1;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 *
 * @author Alexjandro
 */
public class Ejercicio5 {
    
//    public static BigDecimal sqrt(BigDecimal a, MathContext mc )
//    {
//        int cont=0;
//        BigDecimal unmedio=new BigDecimal(0.5, mc);
//        BigDecimal error=new BigDecimal(1, mc);
//        BigDecimal raiz=new BigDecimal(a.toString());
//        BigDecimal oldraiz=new BigDecimal(0, mc);
//        while (raiz.compareTo(oldraiz) !=0 && cont < 1000) {
//           cont++;
//           oldraiz = new BigDecimal(raiz.toString());
//           raiz=unmedio.multiply(raiz.add(a.divide(raiz,mc),mc),mc);
//        }        
//        return raiz;
//    }
//    
//   public static void main(String[] args) {
//       
//       int precision=200;
//       MathContext mc=new MathContext(precision,RoundingMode.HALF_EVEN);
//       BigDecimal dos=new BigDecimal(2, mc);
//       BigDecimal raiz2= sqrt(dos, mc);
//       System.out.println("raiz2= "+raiz2);
//       
//   }
    
    
    public static void main(String[] args) 
    {
        int nsum=50;
        double[] ncomb= new double[nsum];
        ncomb[0]=1;
        ncomb[1]=0.5;
        for (int i = 2; i < nsum; i++) {
            ncomb[i]=ncomb[i-1]*(0.5-i+1)/i;
            System.out.println("ncom= " + ncomb[i]);
        }
   
        //apartado 1
        double sumapos=1.5;
        double sumaneg=0;
        for (int i = 2; i < nsum; i++) {
            if (i%2==0) {
                sumaneg+=ncomb[i];
            }
            else{
                sumapos+=ncomb[i];
            } 
        }
        System.out.println("raiz aprox1= " + (sumaneg + sumapos));
       
        //apartado 2
        double suma = 1;
        double pot = 1;
        for (int i = 1; i < nsum; i++) {
            pot *= -0.5;
            suma +=ncomb[i]*pot;
        }
        System.out.println("raiz aprox2= " + (1./suma)); 
    }
}

