/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica3;

import Practica2.PanelDibujo;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Alexjandro
 */
public class Ejercicio3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        double[][] xys={{1.5,0},{2.7,1},{3.1,-0.5},{-2.1,1},{-6.6,0.5},{11,0}};
        Polinomio interpolador=Polinomio.interpoladorLagrange(xys);
        
        
        PanelDibujo pd=new PanelDibujo("Apartado 1 del Ejercicio 4");
         pd.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
         double xi2=-10;
         double xs2=10;
         double yi2=-5;
         double ys2=5;
         
        pd.addEjesCoordenados(true, xi2, xs2, yi2, ys2);
        pd.addListaPuntos(Color.yellow, xys, 5);
        pd.addCurva(Color.yellow, interpolador, 5, xi2, xs2);
    }
    
}
