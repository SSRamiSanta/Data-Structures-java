/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbin;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author ssrs_
 */
public class ArbolBinario {

    Binario b = new Binario();
    Nodo raiz;

    ///Inicializa todo cuando se cierra una ventana con Salir
    public void initst() {
        b.initst();
    }

    public void inicializarAr() {
        b.inicializarAr();
    }

    public Nodo getRaiz() {
        return b.getRaiz();
    }

    ////////////METODOS PARA NUMEROS////////////////
    public int insertar(int x) {
        return b.insertarNumero(x);
    }

    public boolean retirar(Nodo r, int x) {
        return b.eliminar(r, x);
    }

    public String inorden(Nodo raiz) {
        return b.inOrden(raiz);
    }

    public ArrayList getArr(Nodo r) {
        return b.inOrdenarr(r);
    }

    public String preorden(Nodo raiz) {
        return b.preOrden(raiz);
    }

    public String posorden(Nodo raiz) {
        return b.postOrden(raiz);
    }

    public String niveles(Nodo raiz) {
        String retorno = "";
        ArrayList array = b.niveles(raiz);
        for (int i = 0; i < array.size(); i++) {
            retorno += array.get(i) + " ";
        }
        return retorno;
    }

    public int gordura(Nodo r) {
        return b.gordura(r);
    }

    public int altura(Nodo raiz) {
        return b.retornarAltura(raiz);
    }

    public boolean esta(Nodo r, int x) {
        boolean sino;
        if (b.buscarRecur(r, x) != null) {
            sino = true;
        } else {
            sino = false;
        }
        return sino;
    }

    public boolean completo() {
        return b.completo;
    }

    public int numero_de_hojas(Nodo r) {
        return b.hojas(r);
    }

    ////////////METODOS PARA LETRAS////////////////
    public void insertarArbolpre(char[] pre, char[] in, int alto, int ancho, int correr, Graphics g) {
        ArrayList prea = new ArrayList();
        ArrayList ina = new ArrayList();
        for (int i = 0; i < pre.length; i++) {
            prea.add(pre[i]);
            ina.add(in[i]);
        }
        b.arbolinpre(prea, ina, alto, ancho, correr, g);
    }

    public void insertarArbolpos(char[] pos, char[] in, int alto, int ancho, int correr, Graphics g) {
        ArrayList posa = new ArrayList();
        ArrayList ina = new ArrayList();
        for (int i = 0; i < pos.length; i++) {
            posa.add(pos[i]);
            ina.add(in[i]);
        }
        b.arbolinpos(posa, ina, alto, ancho, correr, g);
    }

    public class Nodo {

        int info;
        char infol;
        Nodo izq;
        Nodo der;
        Nodo padre;

        Nodo(int info) {
            this.info = info;
            izq = der = null;
        }

        Nodo(int l, char infol) {
            this.infol = infol;
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

    class Binario {

        Nodo raiz;
        int fila;
        String stin, stpre, stpos, stniv;
        int altura, gordura;
        boolean completo;
        ArrayList arr = new ArrayList();
        int[] niveles;

        Binario() {
            raiz = null;
        }

        //Inicializa todo
        void initst() {
            altura = 0;
            gordura = 0;
            stin = stpre = stpos = stniv = "";
            completo = true;
            arr.clear();
            niveles = null;
        }

        void inicializarAr() {
            Nodo n = new Nodo();
            raiz = null;
            n.der = n.izq = n.padre = null;
        }

        /////////////////////-----NUMEROS----------////////////////////
        void filaEnCero() {
            fila = 0;
        }

        int insertarNumero(int n) {
            Nodo p, q;
            if (raiz == null) {
                raiz = new Nodo(n);
                return 1;
            }
            p = q = raiz;
            while (q != null && p.info != n) {
                p = q;
                if (n < p.info) {
                    q = q.izq;
                } else {
                    q = q.der;
                }
            }
            if (p.info == n) {
                return -1; // repetido
            } else if (n < p.info) {
                Nodo nuevo = new Nodo(n);
                p.izq = nuevo;
            } else {
                Nodo nuevo = new Nodo(n);
                p.der = nuevo;
            }
            return 1;
        }

        public boolean eliminar(Nodo r, int n) { // recibe el nodo raiz
            Nodo aux;
            Nodo padre;
            aux = padre = r;
            boolean esizq = true;
            while (aux.info != n) {
                padre = aux;
                if (n < aux.info) {
                    esizq = true;
                    aux = aux.izq;
                } else {
                    esizq = false;
                    aux = aux.der;
                }
                if (aux == null) {
                    return false;
                }
            }
            if (aux.izq == null && aux.der == null) {
                if (aux == r) {
                    raiz = null;
                } else if (esizq) {
                    padre.izq = null;
                } else {
                    padre.der = null;
                }
            } else if (aux.der == null) {
                if (aux == r) {
                    raiz = aux.izq;
                } else if (esizq) {
                    padre.izq = aux.izq;
                } else {
                    padre.der = aux.izq;
                }
            } else if (aux.izq == null) {
                if (aux == r) {
                    raiz = aux.der;
                } else if (esizq) {
                    padre.izq = aux.der;
                } else {
                    padre.der = aux.izq;
                }
            } else {
                Nodo reempl = obtenerReempl(aux);
                if (aux == raiz) {
                    raiz = reempl;
                } else if (esizq) {
                    padre.izq = reempl;
                } else {
                    padre.der = reempl;
                }
                reempl.izq = aux.izq;
            }
            return true;
        }

        Nodo obtenerReempl(Nodo n) {
            Nodo p = n;
            Nodo r = n;
            Nodo aux = n.der;
            while (aux != null) {
                p = r;
                r = aux;
                aux = aux.izq;
            }
            if (r != n.der) {
                p.izq = r.der;
                r.der = n.der;
            }
            return r;
        }

        Nodo buscarRecur(Nodo p, int n) {
            while (p.info != n) {
                if (n < p.info) {
                    p = p.izq;
                } else {
                    p = p.der;
                }
                if (p == null) {
                    return null;
                }
            }
            return p;
        }

        String inOrden(Nodo n) {
            if (n != null && n.info != 0) {
                inOrden(n.izq);
                stin += n.info + "  ";
                inOrden(n.der);
            }
            return stin;
        }

        ArrayList inOrdenarr(Nodo n) {
            if (n != null && n.info != 0) {
                inOrdenarr(n.izq);
                arr.add(n.info);
                inOrdenarr(n.der);
            }
            return arr;
        }

        String preOrden(Nodo n) {
            if (n != null && n.info != 0) {
                stpre += n.info + "  ";
                preOrden(n.izq);
                preOrden(n.der);
            }
            return stpre;
        }

        String postOrden(Nodo n) {
            if (n != null && n.info != 0) {
                postOrden(n.izq);
                postOrden(n.der);
                stpos += n.info + "  ";
            }
            return stpos;
        }

        ArrayList niveles(Nodo p) {
            ArrayList array = new ArrayList();

            Cola cola = null;
            int fila = 0;

            if (p != null) {
                cola = new Cola();
                cola.sumar(p);
            }

            while (!cola.vacia()) {
                p = cola.atender();
                if (completo) {
                    if (p.der != null && p.izq != null) {
                        completo = true;
                    } else if (p.der == null && p.izq == null) {
                        completo = true;
                    } else {
                        completo = false;
                    }
                }
                stniv += p.info + "  ";
                array.add(p.info);
                if (p.izq != null) {
                    cola.sumar(p.izq);
                }
                if (p.der != null) {
                    cola.sumar(p.der);
                }
            }
            return array;
        }

        int hojas(Nodo p) {
            Pila pila = new Pila();
            int i = 0;
            pila.initPila();
            while (p != null) {
                pila.insPila(p);
                p = p.izq;
            }
            while (!pila.pilaVacia()) {
                p = pila.retiraPila();
                if (p.izq == null && p.der == null) {
                    i++;
                }
                p = p.der;
                while (p != null) {
                    pila.insPila(p);
                    p = p.izq;
                }
            }
            return i;
        }

        public int retornarAltura(Nodo r) {
            altura = 0;
            retornarAltura(r, 1);
            return altura - 1;
        }

        private void retornarAltura(Nodo r, int nivel) {
            if (r != null) {
                retornarAltura(r.izq, nivel + 1);
                if (nivel > altura) {
                    altura = nivel;
                }
                retornarAltura(r.der, nivel + 1);
            }
        }

        int altura(Nodo r) {
            if (r == null) {
                return 0;
            }
            return Math.max(altura(r.izq), altura(r.der)) - 1;
        }

        int gordura(Nodo r) {
            niveles = new int[altura];
            for (int i = 0; i < niveles.length; i++) {
                niveles[i] = nodoN(r, i);
            }
            for (int i = 0; i < niveles.length; i++) {
                if (gordura <= niveles[i]) {
                    gordura = niveles[i];
                }
            }
            return gordura;
        }

        int nodoN(Nodo r, int n) {
            if (r != null) {
                if (n == 0) {
                    return nodoN(r.izq, n - 1) + nodoN(r.der, n - 1) + 1;
                } else {
                    return nodoN(r.izq, n - 1) + nodoN(r.der, n - 1);
                }
            }
            return 0;
        }

        /////////////////////-----LETRAS----------////////////////////
        void arbolinpre(ArrayList pre, ArrayList in, int alto, int ancho, int correr, Graphics g) {
            Nodo n = new Nodo(pre.get(0).toString().charAt(0));
            g.drawString(pre.get(0).toString(), ancho, alto);
            int medio = in.indexOf(pre.get(0));
            pre.remove(0);
            if (medio != 0) {
                int inicioizquierdopre = 0;
                int inicioizquierdoin = 0;
                int finizquierdoin = medio - 1;
                ArrayList izquierdaIn = new ArrayList();
                for (int i = inicioizquierdoin; i < finizquierdoin + 1; i++) {
                    izquierdaIn.add(in.get(i));
                }
                int finizquierdopre = izquierdaIn.size();
                ArrayList izquierdaPre = new ArrayList();
                for (int i = inicioizquierdopre; i < finizquierdopre; i++) {
                    izquierdaPre.add(pre.get(i));
                }
                g.drawLine(ancho, alto + 7, ancho - correr + 50, alto + 38);
                arbolinpre(izquierdaPre, izquierdaIn, alto + 50, ancho - correr + 50, correr, g);
            }
            if (medio != in.size() - 1) {
                int finderechopre = pre.size();
                int inicioderechoin = medio + 1;
                int finderechoin = in.size();
                ArrayList derechaIn = new ArrayList();
                for (int i = inicioderechoin; i < finderechoin; i++) {
                    derechaIn.add(in.get(i));
                }
                int inicioderechopre = finderechopre - derechaIn.size();
                ArrayList derechaPre = new ArrayList();
                for (int i = inicioderechopre; i < finderechopre; i++) {
                    derechaPre.add(pre.get(i));
                }
                g.drawLine(ancho, alto + 7, ancho + correr + 50, alto + 38);
                arbolinpre(derechaPre, derechaIn, alto + 50, ancho + correr + 50, correr, g);
            }
        }

        void arbolinpos(ArrayList pos, ArrayList in, int alto, int ancho, int correr, Graphics g) {
            g.drawString(pos.get(pos.size() - 1).toString(), ancho, alto);
            int dato = pos.size() - 1;
            Nodo n = new Nodo(pos.get(dato).toString().charAt(0));
            int medio = in.indexOf(pos.get(dato));
            //pos.remove(0);
            if (medio != 0) {
                int inicioizquierdopos = 0;
                int inicioizquierdoin = 0;
                int finizquierdoin = medio;
                ArrayList izquierdaIn = new ArrayList();
                for (int i = inicioizquierdoin; i < finizquierdoin; i++) {
                    izquierdaIn.add(in.get(i));
                }
                int finizquierdopos = izquierdaIn.size();
                ArrayList izquierdaPos = new ArrayList();
                for (int i = inicioizquierdopos; i < finizquierdopos; i++) {
                    izquierdaPos.add(pos.get(i));
                }
                g.drawLine(ancho, alto + 7, ancho - correr + 50, alto + 38);
                arbolinpos(izquierdaPos, izquierdaIn, alto + 50, ancho - correr + 50, correr, g);
            }
            if (medio != in.size() - 1) {
                int finderechopos = pos.size() - 1;
                int inicioderechoin = medio + 1;
                int finderechoin = in.size();
                ArrayList derechaIn = new ArrayList();
                for (int i = inicioderechoin; i < finderechoin; i++) {
                    derechaIn.add(in.get(i));
                }
                int inicioderechopos = finderechopos - derechaIn.size();
                ArrayList derechaPos = new ArrayList();
                for (int i = inicioderechopos; i < finderechopos; i++) {
                    derechaPos.add(pos.get(i));
                }
                g.drawLine(ancho, alto + 7, ancho + correr + 50, alto + 38);
                arbolinpos(derechaPos, derechaIn, alto + 50, ancho + correr + 50, correr, g);
            }
        }

        class NodoCola {

            public Nodo info;

            NodoCola(Nodo info) {
                this.info = info;
            }
            NodoCola sig;
        }

        class Cola {

            NodoCola cola;

            Cola() {
                cola = new NodoCola(null);
                cola.sig = cola;
            }

            void sumar(Nodo objeto) {
                NodoCola nuevo = new NodoCola(objeto);
                nuevo.sig = cola.sig;
                cola.sig = nuevo;
                cola = nuevo;
            }

            Nodo atender() {
                NodoCola q, r;
                Nodo temp;
                if (cola == cola.sig) {
                    return null;
                }
                q = cola.sig;
                r = q.sig;
                temp = r.info;
                q.sig = r.sig;
                if (q == q.sig) {
                    cola = q;
                }
                return temp;
            }

            boolean vacia() {
                return cola == cola.sig;
            }
        }

        class Pila {

            final int MAXIMO = 10;
            int t;
            Nodo[] a;

            Pila() {
                t = 0;
                a = new Nodo[MAXIMO];
            }

            void initPila() {
                t = 0;
            }

            boolean pilaVacia() {
                return t == 0;
            }

            int insPila(Nodo objeto) {
                if (t == MAXIMO - 1) {
                    return -1; // No soporta mas elementos
                } else {
                    t++;
                    a[t - 1] = objeto;
                }
                return 1;
            }

            Nodo retiraPila() {
                if (pilaVacia()) {
                    return null;
                } else {
                    t--;
                    return a[t];
                }
            }
        }

        Nodo getRaiz() {
            return raiz;
        }

    }
}
