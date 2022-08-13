/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import Vista.Dibujar;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Daniel
 */
public class Matriz {

    private String entrada;
    private char caracteres[];
    private ArrayList<Integer> frecuencias;
    Arbol a;

    public Matriz() {

    }

    public Matriz(String entrada) {
        this.entrada = entrada;
    }

    public void ordenar() {
        setCaracteres(new char[getEntrada().length()]);
        for (int i = 0; i < getEntrada().length(); i++) {
            getCaracteres()[i] = getEntrada().charAt(i);
        }
        for (int i = 0; i < getEntrada().length(); i++) {
            for (int j = 0; j < getEntrada().length(); j++) {
                if (getCaracteres()[i] < getCaracteres()[j]) {
                    char aux = getCaracteres()[i];
                    getCaracteres()[i] = getCaracteres()[j];
                    getCaracteres()[j] = aux;
                }
            }
        }
    }

    public char[] contar() {
        setFrecuencias((ArrayList<Integer>) new ArrayList());
        int frecuencia = 1;
        for (int i = 0; i < getEntrada().length(); i++) {
            if (i < getEntrada().length() - 1) {
                if (getCaracteres()[i] == getCaracteres()[i + 1]) {
                    frecuencia++;
                } else {
                    getFrecuencias().add(frecuencia);
                    frecuencia = 1;
                }
            } else {
                getFrecuencias().add(frecuencia);
            }
        }
        ArrayList<Character> norep = new ArrayList();
        for (int i = 0; i < getEntrada().length(); i++) {
            if (i < getEntrada().length() - 1) {
                if (getCaracteres()[i] != getCaracteres()[i + 1]) {
                    norep.add(getCaracteres()[i]);
                }
            } else {
                norep.add(getCaracteres()[i]);
            }
        }

        char norepenvia[] = new char[norep.size()];
        for (int i = 0; i < norep.size(); i++) {
            norepenvia[i] = norep.get(i);
        }
        return norepenvia;
    }

    public int[][] crearMatriz() {
        int matriz[][] = new int[5][getFrecuencias().size() * 2 - 1];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (i == 0 && j < getFrecuencias().size()) {
                    matriz[i][j] = getFrecuencias().get(j);
                } else {
                    matriz[i][j] = 0;
                }
            }
        }
        return matriz;
    }

    public ArrayList codigos(List<List<Integer>> miLista, int[][] matriz, char[] caracteres) {
        ArrayList<String> codigos = new ArrayList();
        String cod;
        int veces = caracteres.length;
        int numero = miLista.size() - 1;
        for (int i = 0; i < veces; i++) {
            cod = "";
            for (int k = miLista.get(numero).size() - 1; k >= 0; k--) {
                if (miLista.get(numero).get(k) == 1) {
                    cod += "0";
                } else {
                    cod += "1";
                }
            }
            if (matriz[2][i] == 1) {
                cod += "0";
            } else {
                cod += "1";
            }
            codigos.add(cod);
            numero--;
        }
        System.out.println("");
        for (int i = 0; i < codigos.size(); i++) {
            System.out.println("Letra: " + caracteres[i] + " codigo: " + codigos.get(i));
        }
        return codigos;
    }

    public void rellenarArbol(List<List<Integer>> miLista, int[][] matriz,char []caracteres) {
        int[] frecuenc = new int[frecuencias.size() - caracteres.length];
        System.out.println("Frecuencias: " + frecuenc.length);
        ArrayList<String> preorden = new ArrayList<>();
        for (int i = (matriz[0].length); i > matriz[0].length - frecuenc.length; i--) {
            preorden.add(Integer.toString(matriz[0][i - 1]));
        }

        for (int i = (caracteres.length); i > 0; i--) {
            preorden.add(Character.toString(caracteres[i - 1]));
        }
        
        System.out.println("OPreorden");
        for (int i = 0; i < preorden.size(); i++) {
            System.out.print(preorden.get(i)+" ");
        }
        int j = preorden.size() - 1;
        int contador = -1;
        a = new Arbol();
        for (int i = 0; i < preorden.size(); i++) {
            a.insertarDato(preorden.get(i), j, matriz, miLista, contador);
            contador++;
            j--;
        }
    }

    public void dibujarArbol(int cantidad,JPanel panel2) {
        Dibujar d = new Dibujar();
        Graphics g = panel2.getGraphics();
        System.out.println("Graphics es: "+g);
        g.setColor(Color.GREEN);
        d.DibujaArbol2(g,600, 20, cantidad, a.getRaiz());
    }

    public String[][] matriztotal(char[] caracteres, int[][] matriz) {

        String[][] matriztotal = new String[7][matriz[0].length + 1];
        matriztotal[1][0] = "SIMBOLO";
        matriztotal[2][0] = "FRECUENCIA";
        matriztotal[3][0] = "PADRE";
        matriztotal[4][0] = "TIPO";
        matriztotal[5][0] = "IZQ";
        matriztotal[6][0] = "DER";

        for (int i = 0; i < caracteres.length; i++) {
            matriztotal[1][i+1] = Character.toString(caracteres[i]);
        }

        for (int i = 1; i < matriztotal[0].length; i++) {
            matriztotal[0][i] = Integer.toString(i - 1);
            matriztotal[2][i] = Integer.toString(matriz[0][i - 1]);
            matriztotal[3][i] = Integer.toString(matriz[1][i - 1]);
            matriztotal[4][i] = Integer.toString(matriz[2][i - 1]);
            matriztotal[5][i] = Integer.toString(matriz[3][i - 1]);
            matriztotal[6][i] = Integer.toString(matriz[4][i - 1]);
        }
        System.out.println("Matriz: ");
        System.out.println();
        for (int i = 0; i < matriztotal.length; i++) {
            for (int j = 0; j < matriztotal[0].length; j++) {
                if (matriztotal[i][j] == null) {
                    matriztotal[i][j] = "";
                }
                System.out.print(matriztotal[i][j] + "\t ");
            }
            System.out.println("");
        }
        System.out.println("");
        return matriztotal;
    }
    
    public List<List<Integer>> miLista(int [][]matriz){
        List<List<Integer>> miLista = new ArrayList<List<Integer>>();
                    int h = matriz[0].length - 2;
                    int l = 0;
                    for (int o = 0; o < matriz[0].length - 1; o++) {
                        miLista.add(new ArrayList<Integer>());
                        int i = h;
                        int tipo = 99;
                        while (tipo != 0) {
                            int padre = matriz[1][i];
                            tipo = matriz[2][padre];
                            i = padre;
                            miLista.get(l).add(tipo);
                        }
                        l++;
                        h--;
                    }
                    return miLista;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public char[] getCaracteres() {
        return caracteres;
    }

    public void setCaracteres(char[] caracteres) {
        this.caracteres = caracteres;
    }

    public ArrayList<Integer> getFrecuencias() {
        return frecuencias;
    }

    public void setFrecuencias(ArrayList<Integer> frecuencias) {
        this.frecuencias = frecuencias;
    }
}
