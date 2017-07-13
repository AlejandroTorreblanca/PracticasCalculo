
package primeraEntregaCN1v;

import auxiliar.*;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Ejercicio 3 de la entrega.
 * @author AlejandroTorreblanca
 */
public class Ejercicio3 {

    /**
     * Función empleada para realizar el apartado 3.
     */
    public static class F implements Funcion{
        public double eval(double x){
          return (100/(x*x))*Math.sin(10/x);
        }
    }
    
    /**
     * Función empleada para realizar el apartado 1.
     */
    public static class G implements Funcion{
        public double eval(double x){
          return Math.cos(x*x);
        }
    }
    
    /**
     * Función empleada para realizar el apartado 2.
     */
    public static class H implements Funcion{
        public double eval(double x){
          return x*Math.cos(x*x*Math.PI/2);
        }
    }
    
    public static void main(String[] args) {
        
        F f=new F();
        G g=new G();
        H h=new H();
       
    //Apartado 1
        double resultado=MetodosFunciones.IntegralAdaptativaItUsandoPila(g, 10, 30, 0.0001);
        System.out.println("Apartado 1:");
        System.out.println("Resultado de la integral calculada: "+ resultado);
        System.out.println("Resultado exacto de la integral: 0.421613 ");
        System.out.println("");
        
    //Apartado 2
        double[] tabla =new double[10];
        for (int i = 1; i <= 10; i++) 
            tabla[i-1]=MetodosFunciones.IntegralAdaptativaItUsandoPila(h,0,i*0.1, 0.00001);
        System.out.println("Apartado 2:");
        for (int i = 0; i < 10; i++) 
            System.out.println("Tabla["+i+"]= "+ tabla[i]);
                
    //Apartado 3
        
        double[] resultadoIntegral=new double[2];
        double[][] valores=MetodosFunciones.IntegralAdaptativaItUsandoPila(f,1,3, 0.0001,resultadoIntegral);
        int numPuntos=20;
        double[][] xys=MetodosFunciones.tablaGraficaTchev(f, numPuntos, 1,3);
        PolinomioInterpolador p = new PolinomioInterpolador(xys);
        PanelDibujo pd = new PanelDibujo("Ejercicio 3, Apartado 3");
        pd.addWindowListener(
                new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        double xinf=-60;
        double xsup=80;
        double yinf=-1;
        double ysup=4;
        double[] posicion1={1.75,52};
        double[] posicion2={1.75,42};
        pd.addEjesCoordenados(true,yinf,ysup,xinf, xsup,0,0);
        pd.addListaPuntos(Color.blue,valores, 4);
        pd.addCurva(Color.blue, p, 100, 1, 3);
        pd.addEtiqueta(posicion1, "resultado de la integral aproximada: "+resultadoIntegral[0], Color.red);
        pd.addEtiqueta(posicion2, "resultado de la integral excta: -1.4260", Color.red);
        pd.repaint();
    }
    
}
