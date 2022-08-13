/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;

/**
 *
 * @author ssrs_
 */
public class Arbol {

    Nodo raiz;

    public int insertarDato(String n, int pos, int[][] matriz, List<List<Integer>> miLista, int contador) {
        Nodo p, q;
        if (raiz == null) {
            raiz = new Nodo(n);
            return 1;
        }
        q = raiz;
        int j = contador;
        int i = miLista.get(j).size() - 2;
        while (i >= 0) {
            if (miLista.get(j).get(i) == 1) {
                q = q.izq;
            } else {
                q = q.der;
            }
            i--;
        }
        p = q;
        if (matriz[2][pos] == 1) {
            Nodo nuevo = new Nodo(n);
            p.izq = nuevo;
        } else {
            Nodo nuevo = new Nodo(n);
            p.der = nuevo;
        }
        return 1;
    }

    public class Nodo {

        public String info;
        public int pos, tipo;
        public Nodo izq;
        public Nodo der;
        public Nodo padre;

        Nodo(String info) {
            this.info = info;
            izq = der = null;
        }

        Nodo(int pos) {
            this.pos = pos;
            izq = der = null;
        }

        Nodo(int tipo, int k) {
            this.tipo = tipo;
            izq = der = null;
        }

        Nodo() {
            izq = der = null;
        }

        Nodo getIzq() {
            return izq;
        }

        Nodo getDer() {
            return der;
        }
    }

    public Nodo getRaiz() {
        return raiz;
    }
}
