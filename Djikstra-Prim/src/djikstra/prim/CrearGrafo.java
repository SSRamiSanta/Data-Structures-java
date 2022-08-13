/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package djikstra.prim;

/**
 *
 * @author ssrs_
 */
public class CrearGrafo {

    int verxs; // Indica el número de nodos en el gráfico
    int[] data;// Almacenar datos del nodo
    int[][] weight; // Almacenar bordes, que es nuestra matriz de adyacencia

    public CrearGrafo(int verxs, int[] data, int[][] weight) {
        this.verxs = verxs;
        this.data = data;
        this.weight = weight;
    }
}
