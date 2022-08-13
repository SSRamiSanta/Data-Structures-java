/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import java.util.ArrayList;

public class Logica {

    private int nPrimo, disponible;

    private int[] cabezaListas, valores;
    private String[] letras;
    public boolean esta;
    private int[][] matriz;
    private String[][] matrizSalida;
    private String[][] matrizBuscar;
    ArrayList[] cabeza;

    public Logica(int nPrimo, int[] valores, String[] letras) {
        this.valores = valores;
        this.nPrimo = nPrimo;
        this.letras = letras;

        cabezaListas = new int[nPrimo];
        for (int i = 0; i < nPrimo; i++) {
            cabezaListas[i] = -1;
        }
        this.disponible = valores.length;
    }

    public void llenar() {

        for (int i = 0; i < valores.length; i++) {
            if (cabezaListas[valores[i] % nPrimo] == -1) {
                cabezaListas[valores[i] % nPrimo] = i + 1;
            }
        }

        for (int i = 0; i < cabezaListas.length; i++) {
            System.out.print(cabezaListas[i] + " ");
        }
        System.out.println("");
    }

    public void guardar() {
        cabeza = new ArrayList[cabezaListas.length];
        for (int i = 0; i < cabeza.length; i++) {
            cabeza[i] = new ArrayList();
        }
        for (int i = 0; i < valores.length; i++) {
            cabeza[valores[i] % nPrimo].add(valores[i]);
        }
        for (int i = 0; i < cabeza.length; i++) {
            System.out.println("");
            for (int j = 0; j < cabeza[i].size(); j++) {
                System.out.print(cabeza[i].get(j) + " ");
            }
        }
    }

    public String[][] crearMatriz() {
        matrizSalida = new String[4][valores.length + 4];
        for (int i = 0; i < matrizSalida[0].length; i++) {
            matrizSalida[0][i] = (i + 1) + "";
            if (i + 1 > valores.length) {
                matrizSalida[1][i] = 0 + "";
                matrizSalida[2][i] = "";
            } else {
                matrizSalida[1][i] = valores[i] + "";
                matrizSalida[2][i] = letras[i];
            }
        }
        for (int i = 0; i < matriz[0].length; i++) {
            matrizSalida[3][i] = matriz[1][i] + "";
        }
        for (int i = 0; i < matrizSalida[0].length; i++) {
            if (matrizSalida[3][i] == null) {
            }
        }
        return matrizSalida;
    }

    public void llenarMatriz() {
        matriz = new int[2][valores.length + 4];
        for (int i = 0; i < valores.length + 4; i++) {
            matriz[1][i] = i + 2;
        }
        for (int i = 0; i < valores.length; i++) {
            matriz[0][i] = valores[i];
            for (int j = 0; j < cabeza[valores[i] % nPrimo].size(); j++) {
                if (valores[i] == (Integer) cabeza[valores[i] % nPrimo].get(j)) {

                    if (j + 1 >= cabeza[valores[i] % nPrimo].size()) {
                        matriz[1][i] = 0;
                    } else {
                        matriz[1][i] = buscar((Integer) cabeza[valores[i] % nPrimo].get(j + 1)) + 1;
                    }
                }
            }
        }
        System.out.println("");
        for (int i = 0; i < matriz.length; i++) {
            System.out.println("");
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
        }
    }

    public int buscar(int valor) {
        int pos = 0;
        for (int i = 0; i < valores.length; i++) {
            if (valores[i] == valor) {
                pos = i;
            }
        }
        return pos;
    }

    public void borrar(int llave) {
        int posicion = 0;
        for (int i = 0; i < matrizSalida[0].length; i++) {
            if (llave == Integer.parseInt(matrizSalida[1][i])) {
                posicion = i;
                System.out.println("Llave encontrada ");
                break;
            }
        }
        int padre = Integer.parseInt(matrizSalida[3][posicion]);
        if (padre == 0) {
            for (int i = 0; i < matrizSalida[0].length; i++) {
                if (matrizSalida[1][i].equals("0")) {
                    matrizSalida[3][posicion] = matrizSalida[0][i];
                    break;
                }
            }
            matrizSalida[1][posicion] = 0 + "";
            matrizSalida[2][posicion] = "";
        } else {
            for (int i = 0; i < matrizSalida[0].length; i++) {
                if (posicion + 1 == Integer.parseInt(matrizSalida[3][i])) {
                    matrizSalida[3][i] = String.valueOf(padre);
                }
            }
            for (int i = 0; i < matrizSalida[0].length; i++) {
                if (matrizSalida[1][i].equals("0")) {
                    matrizSalida[3][posicion] = matrizSalida[0][i];
                    break;
                }
            }
            matrizSalida[1][posicion] = 0 + "";
            matrizSalida[2][posicion] = "";
        }

        disponible = posicion;
        for (int i = 0; i < cabeza[llave % nPrimo].size(); i++) {
            if (cabeza[llave % nPrimo].get(i).equals(llave)) {
                posicion = i;
            }
        }
        cabeza[llave % nPrimo].remove(posicion);
        borrarLlaves(valores, letras, llave);
    }

    public String[][] matrizCabeza() {
        for (int j = 0; j < cabezaListas.length; j++) {
            if (cabeza[j].isEmpty()) {
                cabezaListas[j] = -1;
            }
        }
        int red = 0;
        for (int i = 0; i < cabezaListas.length; i++) {
            if (cabezaListas[i] == -1) {
                red += 1;
            }
        }
        String[][] matrizCab = new String[cabezaListas.length - red][3];
        red = 0;
        for (int i = 0; i < cabezaListas.length; i++) {
            if (cabezaListas[i] != -1) {
                matrizCab[red][0] = i + ": ";
                matrizCab[red][1] = cabezaListas[i] + ": ";
                matrizCab[red][2] = "";
                for (int j = 0; j < cabeza[i].size(); j++) {
                    matrizCab[red][2] += cabeza[i].get(j) + " -> ";
                }
                matrizCab[red][2] = matrizCab[red][2].substring(0, matrizCab[red][2].length() - 4);
                red += 1;
            }
        }
        return matrizCab;
    }

    public ArrayList<String> buscarDato(int valor) {
        ArrayList<String> datosBusqueda = new ArrayList<>();
        int red = 0;
        for (int i = 0; i < cabezaListas.length; i++) {
            if (cabezaListas[i] != -1) {
                for (int j = 0; j < cabeza[i].size(); j++) {
                    if (cabeza[i].get(j).equals(valor)) {
                        datosBusqueda.add(i + "");
                        datosBusqueda.add(String.valueOf(cabezaListas[i]));
                        datosBusqueda.add(matrizCabeza()[red][2]);
                    }
                }
                red += 1;
            }
        }

        for (int i = 0; i < matrizSalida[0].length; i++) {
            if (matrizSalida[1][i].equals(valor + "")) {
                for (int j = 0; j < matrizSalida.length; j++) {
                    datosBusqueda.add(matrizSalida[j][i]);
                }
            }
        }
        return datosBusqueda;
    }

    public void matrizBusqueda(ArrayList<String> cabezaDato) {
        matrizBuscar = new String[7][2];
        for (int j = 0; j < matrizBuscar[0].length; j++) {
            for (int i = 0; i < matrizBuscar.length; i++) {
                if (j == 0) {
                    if (i == 0) {
                        matrizBuscar[i][j] = "Módulo";
                    }
                    if (i == 1) {
                        matrizBuscar[i][j] = "Cabeza";
                    }
                    if (i == 2) {
                        matrizBuscar[i][j] = "Lista";
                    }
                    if (i == 3) {
                        matrizBuscar[i][j] = "Posición (Cursor)";
                    }
                    if (i == 4) {
                        matrizBuscar[i][j] = "Código";
                    }
                    if (i == 5) {
                        matrizBuscar[i][j] = "Nombre";
                    }
                    if (i == 6) {
                        matrizBuscar[i][j] = "Siguiente (Cursor)";
                    }
                } else {
                    matrizBuscar[i][j] = cabezaDato.get(i);
                }
            }
        }
    }

    private void borrarLlaves(int[] valores, String[] letras, int llave) {
        ArrayList<Integer> ll = new ArrayList<Integer>();
        ArrayList<String> le = new ArrayList<String>();
        for (int i = 0; i < valores.length; i++) {
            if (llave != valores[i]) {
                ll.add(valores[i]);
                le.add(letras[i]);
            }
        }
        this.letras = new String[le.size()];
        this.valores = new int[ll.size()];
        for (int i = 0; i < this.valores.length; i++) {
            this.valores[i] = ll.get(i);
            this.letras[i] = le.get(i);
        }
    }

    public boolean BuscarDisponible(int llave) {
        for (int i = 0; i < this.valores.length; i++) {
            if (llave == valores[i]) {
                return true;
            }
        }
        return false;
    }

    public String[][] getMatrizCab() {
        String[][] matrizCab = matrizCabeza();
        return matrizCab;
    }

    public String[][] getMatriz() {
        return matrizSalida;
    }

    public String[][] getMatrizBuscar() {
        return matrizBuscar;
    }

    public int getDisponible() {
        return disponible + 1;
    }

}
