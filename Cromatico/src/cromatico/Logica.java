/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cromatico;

import java.util.ArrayList;

/**
 *
 * @author ssrs_
 */
public class Logica {

    private ArrayList<String> CoordVertices;
    private ArrayList<Vertices> Vertices;
    private int[][] adyacencias;
    private ArrayList<int[]> listas;
    private int[] orden;
    private int i, j;
    private ArrayList<Integer> permutaciones;

    public Logica() {

        listas = new ArrayList<>();
    }

    public void setAdyacencias(int[][] adyacencias) {

        this.adyacencias = adyacencias;
        iniciarOrden();
    }

    private void iniciarOrden() {
        orden = new int[adyacencias.length];

        for (i = 0; i < orden.length; i++) {
            orden[i] = i;
        }
    }

    public void imprimirAdy() {
        System.out.println("Adyacencias: ");
        for (int[] ady : adyacencias) {
            for (int k = 0; k < ady.length; k++) {
                System.out.print(ady[k] + " ");
            }
            System.out.println("");
        }
    }

    public void verificarAdyaadyacenciascencias() {

        int[] listaUno = new int[adyacencias.length];
        inicializarLista(listaUno);
        listaUno[0] = 0;

        obtenerNumeroDeListas();
        if (listas.size() != 2) {

        }
    }

    private int obtenerNumeroDeListas() {
        int listaDisponible;

        i = 1;
        while (orden.length != i) {
            for (j = 0; j < adyacencias[orden[i]].length; j++) {

                if (adyacencias[orden[i]][j] == 1) {
                    listaDisponible = verificarListas(orden[i]);
                    if (listaDisponible == -1) {
                        int[] nuevaLista = new int[adyacencias.length];
                        inicializarLista(nuevaLista);
                        nuevaLista[0] = orden[i];
                        listas.add(nuevaLista);
                    } else {
                        listas.get(listaDisponible)[dondeIngresar(listaDisponible)] = orden[i];
                    }
                }
            }
            i++;
        }
        return listas.size();
    }

    private void inicializarLista(int[] nuevaLista) {
        for (i = 0; i < nuevaLista.length; i++) {
            nuevaLista[i] = -1;
        }
    }

    private int dondeIngresar(int listaDisponible) {
        for (i = 0; i < listas.get(listaDisponible).length; i++) {
            if (listas.get(listaDisponible)[i] == -1) {
                return i;
            }
        }
        return -1;
    }

    private int verificarListas(int a) {
        int k = 0;
        boolean puedeEntrar = true;
        while (k != listas.size()) {

            for (i = 0; i < listas.get(k).length; i++) {
                if (listas.get(k)[i] == a) {
                    puedeEntrar = false;
                    break;
                }
            }
            if (puedeEntrar) {
                return k;
            }
            k++;
        }
        return -1;
    }

    public int getNumeroCromatico() {
        this.asimetrizar(adyacencias);
        ColorGrafo grafoColor = new ColorGrafo(adyacencias);
        ArrayList<Integer> candidatos = new ArrayList<Integer>();
        //Se obtiene un nodo desde el cual empezar el algoritmo
        int actual = orden[0];
        grafoColor.darColor(actual);
        permutaciones = new ArrayList<>();
        permutaciones.add(actual);
        for (int[] adyacencia : adyacencias) {
            int vecinos[] = grafoColor.getVecinos(actual);
            for (int k = 0; k < vecinos.length; k++) {
                if (!candidatos.contains(vecinos[k]) && grafoColor.getVertices().get(vecinos[k]).getColor() == -1) {
                    candidatos.add(vecinos[k]);
                }
            }
            if (candidatos.isEmpty() == false) {
                grafoColor.darColor(candidatos.get(0));
                actual = candidatos.get(0);
                candidatos.remove(0);
                permutaciones.add(actual);

            } else {
                break;
            }
        }
        return grafoColor.getColoresTotales();
    }

    private void asimetrizar(int[][] adyacencias) {
        for (int i = 0; i < adyacencias.length; i++) {
            for (int j = 0; j < adyacencias.length; j++) {
                if (i > j) {
                    adyacencias[j][i] = adyacencias[i][j];
                }
            }
        }

    }

    public ColorGrafo getGrafoColor() {
        this.asimetrizar(adyacencias);
        ColorGrafo grafoColor = new ColorGrafo(adyacencias);
        ArrayList<Integer> candidatos = new ArrayList<>();
        //Se obtiene un nodo desde el cual empezar el algoritmo
        int actual = orden[0];
        grafoColor.darColor(actual);
        permutaciones = new ArrayList<>();
        permutaciones.add(actual);
        for (int[] adyacencia : adyacencias) {
            int vecinos[] = grafoColor.getVecinos(actual);
            for (int k = 0; k < vecinos.length; k++) {
                if (!candidatos.contains(vecinos[k]) && grafoColor.getVertices().get(vecinos[k]).getColor() == -1) {
                    candidatos.add(vecinos[k]);
                }
            }
            if (candidatos.isEmpty() == false) {
                grafoColor.darColor(candidatos.get(0));
                actual = candidatos.get(0);
                candidatos.remove(0);
                permutaciones.add(actual);

            } else {
                break;
            }
        }
        return grafoColor;
    }

    public String getPermutaciones() {
        String salida = "";
        for (int i = 0; i < permutaciones.size(); i++) {
            salida += permutaciones.get(i) + 1;
            salida += ",";
        }
        return salida;
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
        adyacencias = null;

    }

    /**
     * @return the Circulos
     */
    public ArrayList<Vertices> getVertices() {
        return Vertices;
    }

    /**
     * @return the matrizDeAdyacencia
     */
    public int[][] getAdyacencias() {
        return adyacencias;
    }

    /**
     * @param Vertices the Vertices to set
     */
    public void setVertices(ArrayList<Vertices> Vertices) {
        this.Vertices = Vertices;
    }
}
