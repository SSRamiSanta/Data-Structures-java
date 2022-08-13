/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordentopologico;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author ssrs_
 */
public class TopoLogica {

    private ArrayList<String> CoordVertices;
    private ArrayList<Integer> Vertices;
    private ArrayList<Integer>[] Adyacencia;

    /**
     * @return the Circulos
     */
    public ArrayList<String> getCoordVertices() {
        return CoordVertices;
    }

    public void setDatos() {
        CoordVertices = new ArrayList<>();
        Vertices = new ArrayList<>();
        Adyacencia = null;
    }

    public ArrayList<Integer> crearAdyacencia() {
        int cont;
        int[] topo = new int[Vertices.size()];
        for (int i = 0; i < topo.length; i++) {
            cont = 0;
            for (ArrayList<Integer> AdyActual : Adyacencia) {
                for (int k = 0; k < AdyActual.size(); k++) {
                    if (AdyActual.get(k) == (i + 1)) {
                        cont++;
                    }
                }
            }
            topo[i] = cont;
        }
        System.out.print("Topo: ");
        for (int i : topo) {
            System.out.print(i + " ");
        }
        return DFSTopologico(topo);
    }

    private ArrayList<Integer> DFSTopologico(int[] topo) {
        ArrayList<Integer> orden = new ArrayList<>();
        ArrayList<Integer> marcado = new ArrayList<>();
        Queue<Integer> cola = new LinkedList();
        for (int i = 0; i < topo.length; i++) {
            if (topo[i] == 0) {
                topo[i] = -1;
                cola.add((i + 1));
                orden.add((i + 1));
                marcado.add((i + 1));
            }
        }

        while (!cola.isEmpty()) {
            int actual = cola.poll();
            ArrayList<Integer> vecinos = Adyacencia[actual - 1];
            if (vecinos != null) {
                for (Integer destino : vecinos) {
                    if (!marcado.contains(destino)) {
                        for (int i = 0; i < topo.length; i++) {
                            if (i == (destino - 1)) {
                                topo[i]--;
                                if (topo[i] == 0) {
                                    topo[i] = -1;
                                    orden.add(destino);
                                    marcado.add(destino);
                                    cola.offer(destino);
                                } else {
                                    cola.add(destino);
                                }
                            }
                        }

                    }
                }
            } else {
                for (int i = 0; i < topo.length; i++) {
                    if (i == actual) {
                        topo[i]--;
                        if (topo[i] == 0) {
                            orden.add(actual);
                            marcado.add(actual);
                            cola.offer(actual);
                        } else {
                            cola.add(actual);
                        }
                    }
                }
            }
        }
        return orden;
    }

    /**
     * @return the Circulos
     */
    public ArrayList<Integer> getVertices() {
        return Vertices;
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
}
