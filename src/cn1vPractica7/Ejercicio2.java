/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn1vPractica7;
import auxiliar.*;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 *
 * @author Alexjandro
 */
public class Ejercicio2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //NO FUNCIONA¡¡¡
       
        int n=3;
        Polinomio[] L=Polinomio.legendre(n);
        for (int i = 0; i <=n; i++) {
            System.out.println("L["+i+"]="+L[i].escribe());
        }
        
        PanelDibujo pd = new PanelDibujo("Ejercicio 2 práctica 7");
        /*
         * Una buena estrategia para evitar dejar progamas en ejecucion es crear
         * un "listener" (modulo de escucha) sobre la cruz de cerrar la ventana del panel de dibujo
         * para poder cerrar este panel en cualquier momento y detener la ejecucion del programa		 *
         */
        pd.addWindowListener(
                new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        double xinf=-1.5;
        double xsup=1.5;
        double yinf=-5;
        double ysup=5;
        pd.addEjesCoordenados(true,xinf, xsup,yinf,ysup,0,0);
        pd.addCurva(Color.blue, L[n], 101, -1, 1);
        pd.repaint();
    }
    
}
