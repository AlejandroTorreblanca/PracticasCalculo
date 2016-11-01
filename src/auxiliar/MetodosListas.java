/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliar;

/**
 *
 * @author Alejo Junior
 */
public class MetodosListas {
    public static String toString(double[] lista){
        String mensaje="Los datos de la lista son: ";
        for(int i=0;i<lista.length;i++){
            mensaje=mensaje+"Posición "+i+": "+lista[i]+", ";
        }
        return mensaje;
    }
    public static String toString(double[][] lista){
        String mensaje="Los datos de la matriz son: ";
        for(int i=0;i<lista.length;i++){
            for(int j=0;j<lista[i].length;j++){
            mensaje=mensaje+"Posición ("+i+","+j+") : "+lista[i][j]+", ";
        }
        }
        return mensaje;
    }
}
