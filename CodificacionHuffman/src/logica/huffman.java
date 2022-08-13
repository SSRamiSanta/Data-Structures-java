/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public class huffman {

    private int[][] matriz;
    private ArrayList<Integer> frecuencias;

    public huffman(){
        
    }
    
    public huffman(int matriz[][], ArrayList frecuencias) {
        this.matriz = matriz;
        this.frecuencias = frecuencias;
    }

    public int[][] llenar() {
        int posMenor = hallarpmenor();
        int posMenor2 = hallarpmenor();
        boolean enc = false;
        int contador = 0;
        while (enc == false) {
            if (getMatriz()[0][contador] == 0) {
                getMatriz()[0][contador] = getMatriz()[0][posMenor] + getMatriz()[0][posMenor2];
                getMatriz()[1][posMenor] = contador;
                getMatriz()[1][posMenor2] = contador;
                getMatriz()[2][posMenor] = 1;
                getMatriz()[2][posMenor2] = 2;
                getMatriz()[3][contador] = posMenor;
                getMatriz()[4][contador] = posMenor2;
                getFrecuencias().set(posMenor, 99);
                getFrecuencias().set(posMenor2, 99);
                getFrecuencias().add(getMatriz()[0][contador]);
                enc = true;
            }
            contador++;
        }
        return getMatriz();
    }

    private int hallarpmenor() {
        int posMenor = -1, k = 99;
        for (int i = 0; i < frecuencias.size(); i++) {
            if (frecuencias.get(i) < k
                    && frecuencias.get(i) > 0) {
                posMenor = i;
                k = frecuencias.get(i);
            }
        }
        frecuencias.set(posMenor, 99);
        return posMenor;
    }

    public int[][] getMatriz() {
        return matriz;
    }
    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }
    public ArrayList<Integer> getFrecuencias() {
        return frecuencias;
    }
    public void setFrecuencias(ArrayList<Integer> frecuencias) {
        this.frecuencias = frecuencias;
    }


}
