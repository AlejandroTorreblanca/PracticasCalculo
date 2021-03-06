
package segundaEntregaCN1v;

import ORG.netlib.math.complex.Complex;
import auxiliar.*;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Alejandro
 */
public class Ejercico1 {

    /**
     * Definimos una nueva función que nos calcule a que solución converge el polinomio mediante el método de Newton,
     * pero esta vez teniendo en cuenta valores complejos.
     * @param p Polinomio al que se le desea aplicar Newton.
     * @param x Valor complejo desde el que queremos saber a que solución converge el polinomio.
     * @return Solución a la que converge el polinomio.
     * @throws segundaEntregaCN1v.Ejercico1.ERROR 
     */
    public static Complex newton(Polinomio p, Complex x) throws ERROR
    {
        int nmaxiter=50;
        double precision=0.001;
        Complex menos1= new Complex(-1.);
        Complex xx = x;
        Complex h;
        Complex u = p.eval(xx);
        Polinomio p1=p.derivada();
        Complex du = p1.eval(xx);

        for (int k = 1; k <= nmaxiter; k++) {
            if (u.abs() < precision) {
                return xx;
            }
            if (du.abs() < precision) {
                throw new ERROR();
            }
            h=u.mul(menos1).div(du);    //h = -u / du;
            xx=xx.add(h);               //xx = xx + h;
            u = p.eval(xx);
            du = p1.eval(xx);
            if (h.abs() < precision) {
                return xx;
            }
        }
        throw new ERROR();
    }
    
    public static void main(String[] args) throws Polinomio.ErrorPolinomios {
        
        //Estas son las tres raices exactas del polinomio:
       /* double raiz1RE=1.;
        double raiz1IM=0.;
        double raiz2RE=-0.5;
        double raiz2IM=-0.86623;
        double raiz3RE=-0.5;
        double raiz3IM=0.86623;*/
        
        double precision=0.001;
        double precision2=0.00001;
        double aux1, aux2;    
        double[] coef= {-1,0,0,1};
        Polinomio p=new Polinomio(coef);
        
        //Dado que disponemos de una función que nos calcula las raices de un polinomio la usaremos para comprar a cual
        //de las de ellas converge cada punto.
        Complex[] raices=p.buscaRaicesAleatorio(precision2, 200, 200);
        
        PanelDibujo pd = new PanelDibujo("Ejercicio 1 de la Segunda Entrega.");
        pd.addWindowListener(
                new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        double xinf=-1.2;
        double xsup=1.2;
        double yinf=-1.2;
        double ysup=1.2;
        Complex x;
        double numPuntos=175.;
        Complex raiz=new Complex();
        pd.addEjesCoordenados(true,xinf, xsup,yinf,ysup,0,0);
        
        //He decidido dibujar por separado cada uno de los cuatro cuadrantes, el código es el mismo las cuatro veces, cambiando
        //los arrays en donde guardo los puntos a colorear, me ha resultado mas sencillo hacerlo de esta manera.
        
        double[][] xy11= new double[30977][2];
        double[][] xy21= new double[30977][2];
        double[][] xy31= new double[30977][2];
        double[][] xy41= new double[30977][2];
        int i=0;
        boolean error=false;
        for (double j = -1.*numPuntos; j <= 0; j++) {   //Recorremos los puntos del tercer cuadrante.
            for (double k = -1.*numPuntos; k <= 0; k++) 
            {
                x=new Complex(j/numPuntos, k/numPuntos);
                try {
                    raiz= newton(p, x);
                    error=false;
                } catch (ERROR ex) {
                    error=true;
                }
                if(!error)  //En el caso de que el método de Newton falle no tenemos en cuenta ese punto y continuamos con el siguiente.
                {
                    aux1=raices[0].re()-raiz.re();
                    aux2=raices[0].im()-raiz.im();
                    if ((Math.abs(aux1)<precision) && (Math.abs(aux2)<precision)) 
                    {   //Converge a la primera raiz.
                        xy11[i][0]=j/numPuntos;
                        xy11[i][1]=k/numPuntos;
                    }
                    else
                    {
                        aux1=raices[1].re()-raiz.re();
                        aux2=raices[1].im()-raiz.im();
                        if ((Math.abs(aux1)<precision) && (Math.abs(aux2)<precision)) 
                        {   //Converge a la segunda raiz.
                            xy21[i][0]=j/numPuntos;
                            xy21[i][1]=k/numPuntos;
                        }
                        else
                        {
                            aux1=raices[2].re()-raiz.re();
                            aux2=raices[2].im()-raiz.im();
                            if ((Math.abs(aux1)<precision) && (Math.abs(aux2)<precision)) 
                            {   //Converge a la tercera raiz.
                                xy31[i][0]=j/numPuntos;
                                xy31[i][1]=k/numPuntos;
                            }
                            else
                            {   //No hay convergencia.
                                xy41[i][0]=j/numPuntos;
                                xy41[i][1]=k/numPuntos;
                            }
                        }
                    }
                    i++;
                }
            }
        }
        pd.addListaPuntos(Color.blue, xy11, 1);
        pd.addListaPuntos(Color.red, xy21, 1);
        pd.addListaPuntos(Color.green, xy31, 1);
        pd.addListaPuntos(Color.black, xy41, 1);
        
        double[][] xy12= new double[30977][2];
        double[][] xy22= new double[30977][2];
        double[][] xy32= new double[30977][2];
        double[][] xy42= new double[30977][2];
        i=0;
        for (double j = 0.; j <= numPuntos; j++) {        //Recorremos los puntos del cuarto cuadrante.
            for (double k = -numPuntos; k <= 0; k++) 
            {
                x=new Complex(j/numPuntos, k/numPuntos);
                try {
                    raiz= newton(p, x);
                    error=false;
                } catch (ERROR ex) {
                    error=true;
                }
                if(!error)
                {
                    aux1=raices[0].re()-raiz.re();
                    aux2=raices[0].im()-raiz.im();
                    if ((Math.abs(aux1)<precision) && (Math.abs(aux2)<precision)) 
                    {   //Converge a la primera raiz.
                        xy12[i][0]=j/numPuntos;
                        xy12[i][1]=k/numPuntos;
                    }
                    else
                    {
                        aux1=raices[1].re()-raiz.re();
                        aux2=raices[1].im()-raiz.im();
                        if ((Math.abs(aux1)<precision) && (Math.abs(aux2)<precision)) 
                        {   //Converge a la segunda raiz.
                            xy22[i][0]=j/numPuntos;
                            xy22[i][1]=k/numPuntos;
                        }
                        else
                        {
                            aux1=raices[2].re()-raiz.re();
                            aux2=raices[2].im()-raiz.im();
                            if ((Math.abs(aux1)<precision) && (Math.abs(aux2)<precision)) 
                            {   //Converge a la tercera raiz.
                                xy32[i][0]=j/numPuntos;
                                xy32[i][1]=k/numPuntos;
                            }
                            else
                            {   //No hay convergencia.
                                xy42[i][0]=j/numPuntos;
                                xy42[i][1]=k/numPuntos;
                            }
                        }
                    }
                    i++;
                }
            }
        }
        pd.addListaPuntos(Color.blue, xy12, 1);
        pd.addListaPuntos(Color.red, xy22, 1);
        pd.addListaPuntos(Color.green, xy32, 1);
        pd.addListaPuntos(Color.black, xy42, 1);
        
        double[][] xy13= new double[30977][2];
        double[][] xy23= new double[30977][2];
        double[][] xy33= new double[30977][2];
        double[][] xy43= new double[30977][2];
        i=0;
        for (double j = 0.; j <= numPuntos; j++) {        //Recorremos los puntos del primer cuadrante
            for (double k = 0.; k <= numPuntos; k++) 
            {
                x=new Complex(j/numPuntos, k/numPuntos);
                try {
                    raiz= newton(p, x);
                    error=false;
                } catch (ERROR ex) {
                    error=true;
                }
                if(!error)
                {
                    aux1=raices[0].re()-raiz.re();
                    aux2=raices[0].im()-raiz.im();
                    if ((Math.abs(aux1)<precision) && (Math.abs(aux2)<precision)) 
                    {   //Converge a la primera raiz.
                        xy13[i][0]=j/numPuntos;
                        xy13[i][1]=k/numPuntos;
                    }
                    else
                    {
                        aux1=raices[1].re()-raiz.re();
                        aux2=raices[1].im()-raiz.im();
                        if ((Math.abs(aux1)<precision) && (Math.abs(aux2)<precision)) 
                        {   //Converge a la segunda raiz.
                            xy23[i][0]=j/numPuntos;
                            xy23[i][1]=k/numPuntos;
                        }
                        else
                        {
                            aux1=raices[2].re()-raiz.re();
                            aux2=raices[2].im()-raiz.im();
                            if ((Math.abs(aux1)<precision) && (Math.abs(aux2)<precision)) 
                            {   //Converge a la tercera raiz.
                                xy33[i][0]=j/numPuntos;
                                xy33[i][1]=k/numPuntos;
                            }
                            else
                            {   //No hay convergencia.
                                xy43[i][0]=j/numPuntos;
                                xy43[i][1]=k/numPuntos;
                            }
                        }
                    }
                    i++;
                }
            }
        }
        pd.addListaPuntos(Color.blue, xy13, 1);
        pd.addListaPuntos(Color.red, xy23, 1);
        pd.addListaPuntos(Color.green, xy33, 1);
        pd.addListaPuntos(Color.black, xy43, 1);
       
        double[][] xy14= new double[30977][2];
        double[][] xy24= new double[30977][2];
        double[][] xy34= new double[30977][2];
        double[][] xy44= new double[30977][2];
        i=0;
        for (double j = -1*numPuntos; j <= 0; j++) {        //Recorremos los puntos del segundo  cuadrante
            for (double k = 0.; k <= numPuntos; k++) 
            {
                x=new Complex(j/numPuntos, k/numPuntos);
                try {
                    raiz= newton(p, x);
                    error=false;
                } catch (ERROR ex) {
                    error=true;
                }
                if(!error)
                {
                    aux1=raices[0].re()-raiz.re();
                    aux2=raices[0].im()-raiz.im();
                    if ((Math.abs(aux1)<precision) && (Math.abs(aux2)<precision)) 
                    {   //Converge a la primera raiz.
                        xy14[i][0]=j/numPuntos;
                        xy14[i][1]=k/numPuntos;
                    }
                    else
                    {
                        aux1=raices[1].re()-raiz.re();
                        aux2=raices[1].im()-raiz.im();
                        if ((Math.abs(aux1)<precision) && (Math.abs(aux2)<precision)) 
                        {   //Converge a la segunda raiz.
                            xy24[i][0]=j/numPuntos;
                            xy24[i][1]=k/numPuntos;
                        }
                        else
                        {
                            aux1=raices[2].re()-raiz.re();
                            aux2=raices[2].im()-raiz.im();
                            if ((Math.abs(aux1)<precision) && (Math.abs(aux2)<precision)) 
                            {   //Converge a la tercera raiz.
                                xy34[i][0]=j/numPuntos;
                                xy34[i][1]=k/numPuntos;
                            }
                            else
                            {   //No hay convergencia.
                                xy44[i][0]=j/numPuntos;
                                xy44[i][1]=k/numPuntos;
                            }
                        }
                    }
                    i++;
                }
            }
        }
        pd.addListaPuntos(Color.blue, xy14, 1);
        pd.addListaPuntos(Color.red, xy24, 1);
        pd.addListaPuntos(Color.green, xy34, 1);
        pd.addListaPuntos(Color.black, xy44, 1);
        pd.repaint();
    }
    
    /**
     * En este ejercicio no tratamos errores como que la derivada sea nula y no se pueda realizar el método de Newton en el punto, 
     * asique simplemente no tenemos en cuenta ese punto cuando esto ocurre.
     * El defecto de esta acción es que en el dibujo hay pequeñas zonas que no se pintan, pero 
     * son difícilmente apreciables asique no nos preocupamos aunque si somos conscientes de él.
     */
    public static class ERROR extends Exception {

        ERROR() {
            super("Error al aproximar raices");
        }
    }
}
