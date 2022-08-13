/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package djikstra.prim;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author ssrs_
 */
public class Logica {

    private ArrayList<String> CoordVertices;
    private ArrayList<Vertices> Vertices;
    private ArrayList<String> Aristas;
    private ArrayList<Integer>[] Adyacencia;
    private int[] minimos, padres, marcados;
    private int[][] matrizDeAdyacencia;
    private ArrayList<String> datosPrim;

    public void Dijkstra(int origen) {
        setMinimos(new int[getVertices().size()]);
        setPadres(new int[getVertices().size()]);
        setMarcados(new int[getVertices().size()]);

        for (int i = 0; i < getVertices().size(); i++) { // inicializo los minimos con -1
            getMinimos()[i] = -1;
        }

        ArrayList<Integer> auxV = new ArrayList<Integer>(); // cola de prioridad para no recursividad

        auxV.add(origen); // agrego el vertice de origen a la cola
        getMinimos()[origen] = 0;

        while (!auxV.isEmpty()) { // mientras la cola no este vacia
            for (int i = 0; i < getVertices().size(); i++) {
                if (getMatrizDeAdyacencia()[auxV.get(0)][i] != 99 && auxV.get(0) != i) { // si el vertice es adyacente y no es el actual
                    if (getMarcados()[i] == 0) { // si el vertice no esta marcado
                        auxV.add(i); // lo agrego a la cola
                    }

                    if (getMinimos()[i] == -1) {// si es la primera vez que llego al vertice
                        getMinimos()[i] = getMinimos()[auxV.get(0)] + getMatrizDeAdyacencia()[auxV.get(0)][i]; // el minimo es el minimo del actual mas el peso al adyacente
                        getPadres()[i] = auxV.get(0);
                    } else if (getMinimos()[i] > getMinimos()[auxV.get(0)] + getMatrizDeAdyacencia()[auxV.get(0)][i]) { // si el minimo del actual mas el peso del adyacente es menor que el minimo del adyacente
                        getMinimos()[i] = getMinimos()[auxV.get(0)] + getMatrizDeAdyacencia()[auxV.get(0)][i]; // actualizo el minimo
                        getPadres()[i] = auxV.get(0); // actualizo el padre
                    }
                }
            }

            getMarcados()[auxV.get(0)] = 1; // marco el actual
            auxV.remove(0); // remuevo el actual de la cola
        }

    }

    public void Prim(CrearGrafo c, int v) {
        ArrayList<String> arAct = new ArrayList<>();
        // visitado [] marca si el nodo (vértice) ha sido visitado
        int visited[] = new int[c.verxs];
        // visitado [] El valor del elemento predeterminado es 0, lo que significa que no ha sido visitado
        // Marcar el nodo actual como visitado
        visited[v] = 1;
        // h1 y h2 registran los subíndices de los dos vértices
        int h1 = -1;
        int h2 = -1;
        int minWeight = 99; // Inicializar minWeight a un número grande, que será reemplazado más adelante en el proceso transversal
        for (int k = 1; k < c.verxs; k++) {// Debido a que hay vértices graph.verxs, después de que finaliza el algoritmo de Plim, hay bordes graph.verxs-1
            // Esto es para determinar cada subgrafo generado, qué nodo es el más cercano
            for (int i = 0; i < c.verxs; i++) {// i nodo representa el nodo que se ha visitado
                for (int j = 0; j < c.verxs; j++) {// j nodo significa un nodo que no ha sido visitado
                    if (visited[i] == 1 && visited[j] == 0 && c.weight[i][j] < minWeight) {
                        // Reemplazar minWeight (encuentra el borde con el menor peso entre el nodo que se ha visitado y el nodo que no se ha visitado)
                        minWeight = c.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            // Encuentra una arista que sea la más pequeña
            String datos;
            if (c.data[h1] < c.data[h2]) {
                datos = c.data[h1] + "," + c.data[h2] + "," + minWeight;
            } else {
                datos = c.data[h2] + "," + c.data[h1] + "," + minWeight;
            }

            arAct.add(datos);
            System.out.println("Lado <" + c.data[h1] + "," + c.data[h2] + "> Peso:" + minWeight); // Marcar el nodo actual como visitado
            visited[h2] = 1;
            // minWeight se restablece al valor máximo de 10000
            minWeight = 99;
        }
        setDatosPrim(new ArrayList<>());
        while (!arAct.isEmpty()) {
            String[] dataArista = arAct.get(0).split(",");
            for (int i = 0; i < Aristas.size(); i++) {
                String[] datosArista = Aristas.get(i).split(",");
                if (datosArista[0].equals(dataArista[0]) && datosArista[1].equals(dataArista[1])) {
                    System.out.println("Arista encontrada y es " + dataArista[0] + "," + datosArista[1]);
                    //String datosC = datosArista[2] + "," + datosArista[3] + "," + datosArista[5] + "," + datosArista[4] + "," + datosArista[6];
                    String datosC = dataArista[0] + "," + datosArista[1] + "," + datosArista[2];
                    getDatosPrim().add(datosC);
                    arAct.remove(0);
                }
            }

        }

    }

    /**
     * @return the Circulos
     */
    public ArrayList<String> getCoordVertices() {
        return CoordVertices;
    }

    public void setDatos() {
        CoordVertices = new ArrayList<>();
        setVertices(new ArrayList<>());
        matrizDeAdyacencia = null;

    }

    /**
     * @return the Circulos
     */
    public ArrayList<Vertices> getVertices() {
        return Vertices;
    }

    /**
     * @return the Aristas
     */
    public ArrayList<String> getAristas() {
        return Aristas;
    }

    /**
     * @param Aristas the Aristas to set
     */
    public void setAristas(ArrayList<String> Aristas) {
        this.Aristas = Aristas;
    }

    /**
     * @return the Adyacencia
     */
    public ArrayList<Integer>[] getAdyacencia() {
        return Adyacencia;
    }

    /**
     * @param Adyacencia the Adyacencia to set
     */
    public void setAdyacencia(ArrayList<Integer>[] Adyacencia) {
        this.Adyacencia = Adyacencia;
    }

    /**
     * @return the matrizDeAdyacencia
     */
    public int[][] getMatrizDeAdyacencia() {
        return matrizDeAdyacencia;
    }

    /**
     * @param matrizDeAdyacencia the matrizDeAdyacencia to set
     */
    public void setMatrizDeAdyacencia(int[][] matrizDeAdyacencia) {
        this.matrizDeAdyacencia = matrizDeAdyacencia;
    }

    /**
     * @return the minimos
     */
    public int[] getMinimos() {
        return minimos;
    }

    /**
     * @param minimos the minimos to set
     */
    public void setMinimos(int[] minimos) {
        this.minimos = minimos;
    }

    /**
     * @return the padres
     */
    public int[] getPadres() {
        return padres;
    }

    /**
     * @param padres the padres to set
     */
    public void setPadres(int[] padres) {
        this.padres = padres;
    }

    /**
     * @return the marcados
     */
    public int[] getMarcados() {
        return marcados;
    }

    /**
     * @param marcados the marcados to set
     */
    public void setMarcados(int[] marcados) {
        this.marcados = marcados;
    }

    /**
     * @param Vertices the Vertices to set
     */
    public void setVertices(ArrayList<Vertices> Vertices) {
        this.Vertices = Vertices;
    }

    /**
     * @return the datosPrim
     */
    public ArrayList<String> getDatosPrim() {
        return datosPrim;
    }

    /**
     * @param datosPrim the datosPrim to set
     */
    public void setDatosPrim(ArrayList<String> datosPrim) {
        this.datosPrim = datosPrim;
    }
}
