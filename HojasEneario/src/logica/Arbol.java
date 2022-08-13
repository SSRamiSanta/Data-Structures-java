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
public class Arbol {

    private Nodo raiz;
    ArrayList<String> valores;

    public Arbol(ArrayList<String> valores) {
        this.valores = valores;
        this.raiz = new Nodo("a,0");
    }

    public Arbol(String nombre, int valor) {

    }

    public Arbol(Nodo raiz) {
        this.raiz = raiz;
    }

    public void insertarDatos(String[][] matriz) {
        for (int i = 0; i < valores.size(); i++) {
            int pos = 0;
            String[] datos = valores.get(i).split(",");
            String comparacion = datos[1] + "," + datos[2];
            char padre = datos[0].charAt(0);
            
            for (int j = 0; j < matriz.length; j++) {
                for (int k = 0; k < matriz[0].length; k++) {
                    if (matriz[j][k].equals(comparacion)) {
                        if (k == 0) {
                            pos = 0;
                        } else {
                            pos = 1;
                            datos = valores.get(i-1).split(",");
                            padre = datos[1].charAt(0);
                        }
                    }
                }
            }
            Nodo nuevo = new Nodo(comparacion);
            insertarNodo(nuevo, raiz, padre, pos);
        }

    }

    private void insertarNodo(Nodo nuevo, Nodo r, char padre, int pos) {
        String[] datoNodo = r.info.split(",");
        if (datoNodo[0].charAt(0) == padre) {
            if (pos == 0) {
                r.izq = nuevo;
            } else {
                r.der = nuevo;
            }
        } else {
            if (r.der != null) {
                insertarNodo(nuevo, r.der, padre, pos);
            }
            if (r.izq != null) {
                insertarNodo(nuevo, r.izq, padre, pos);
            }
        }

    }

    public class Nodo {

        public String info;
        public Nodo izq;
        public Nodo der;
        public Nodo padre;

        Nodo(String info) {
            this.info = info;
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

    public String getValor(Nodo n) {
        return n.info;
    }

    public Nodo getRaiz() {
        return raiz;
    }

}
