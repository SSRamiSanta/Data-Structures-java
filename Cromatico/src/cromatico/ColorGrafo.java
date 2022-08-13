package cromatico;

import java.util.ArrayList;

public class ColorGrafo {
    //La matriz de adyacencia normalita

    int[][] matrizAdyacencia;
    //Los vertices, esta el numero del vertice y el color de este
    ArrayList<Colorear> vertices = new ArrayList<Colorear>();

    //Lo colores usados
    ArrayList<Integer> color = new ArrayList<Integer>();

    public ColorGrafo(int[][] m) {
        this.matrizAdyacencia = m;
        for (int i = 0; i < matrizAdyacencia.length; i++) {
            vertices.add(i, new Colorear(i, -1));
        }
        color.add(0);
    }

    /**
     * Se obtienen los vecinos de un nodo.
     *
     * @param nodo Nodo del que se quiere conocer sus vecinos
     * @return arreglo de enteros con los nodos vecinos
     */
    public int[] getVecinos(int nodo) {
        int cantidadVecinos = 0;
        int vecinos[];

        // Se obtiene la cantidad de vecinos;
        for (int i = 0; i < matrizAdyacencia.length; i++) {
            if (matrizAdyacencia[nodo][i] != 0) {
                cantidadVecinos++;
            }
        }
        // Se obtienen los vecinos
        vecinos = new int[cantidadVecinos];
        int contador = 0;
        for (int i = 0; i < matrizAdyacencia.length; i++) {
            if (matrizAdyacencia[nodo][i] != 0) {
                vecinos[contador] = i;
                contador++;
            }
        }
        return vecinos;

    }

    public void darColor(int nodo) {
        if (vertices.get(nodo).getColor() == -1) {
            int vecinos[] = this.getVecinos(nodo);
            int colorCandidato = color.get(0);

            boolean tocaCambiarColor = false;
            for (int i = 0; i < vecinos.length; i++) {
                if (vertices.get(vecinos[i]).getColor() == colorCandidato) {
                    tocaCambiarColor = true;
                    colorCandidato = this.getSiguienteColor(colorCandidato);
                    i = 0;
                }
            }
            vertices.get(nodo).setColor(colorCandidato);
        }

    }

    public int getSiguienteColor(int ubicacionColorActual) {
        if (ubicacionColorActual == color.size() - 1) {
            // Se agrega un nuevo color en el arreglo de colores
            color.add(ubicacionColorActual + 1);
            return color.get(color.size() - 1);
        } else {
            return color.get(ubicacionColorActual + 1);
        }
    }

    public int getColoresTotales() {
        return color.size();
    }

    public ArrayList<Colorear> getVertices() {
        return vertices;
    }

}
