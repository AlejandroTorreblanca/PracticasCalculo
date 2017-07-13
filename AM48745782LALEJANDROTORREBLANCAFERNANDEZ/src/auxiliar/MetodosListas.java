/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliar;

import java.util.*;


/**
 *
 * @author Alejo Junior
 */
public class MetodosListas {
    /**
     * Imprime la lista de puntos y la devuelve en forma de string.
     * @param lista
     * @return 
     */
    public static String toString(double[] lista){
        String mensaje="Los datos de la lista son: ";
        for(int i=0;i<lista.length;i++){
            mensaje=mensaje+"Posición "+i+": "+lista[i]+", ";
        }
        return mensaje;
    }
    
    /**
     * Imprime la lista de puntos y la devuelve en forma de string.
     * @param lista
     * @return 
     */
    public static String toString(double[][] lista){
        String mensaje="Los datos de la matriz son: ";
        for(int i=0;i<lista.length;i++){
            for(int j=0;j<lista[i].length;j++){
            mensaje=mensaje+"Posición ("+i+","+j+") : "+lista[i][j]+", ";
        }
        }
        return mensaje;
    }
     
    public static class Orden1columna implements Comparator<double[]> {

        @Override
        public int compare(double[] x, double[] y) {


            if (x[0] > y[0]) {
                return +1;
            } else if (x[0] < y[0]) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    /**
     * Método que se utiliza para reordenar los elementos de una tabla.
     * @param xy
     * @return 
     */
    @SuppressWarnings("ManualArrayToCollectionCopy")
    public static double[][] Ordenada1columna(double[][] xy) {
        Orden1columna Orden1 = new Orden1columna();

        int n = xy.length;
        List<double[]> lista = new ArrayList<>();

        for (int i = 0; i < xy.length; i++) {
            lista.add(xy[i]);
        }
        Collections.sort(lista, Orden1);
        double[][] ordenada = new double[n][];
        for (int i = 0; i < ordenada.length; i++) {
            ordenada[i] = lista.get(i);

        }
        return ordenada;
    }
}
