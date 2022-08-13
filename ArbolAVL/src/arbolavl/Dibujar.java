/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolavl;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author ssrs_
 */
public class Dibujar {

    Arbol b = new Arbol();

    public static int nodoCompleto(Nodo nodo) {
        Nodo p = nodo;
        if (p == null) {
            return 0;
        } else {
            if (p.nodo_izq != null && p.nodo_der != null) {
                return nodoCompleto(p.nodo_izq) + nodoCompleto(p.nodo_der) + 1;
            } else {
                return nodoCompleto(p.nodo_izq) + nodoCompleto(p.nodo_der);
            }
        }
    }

    public void DibujaArbol(Graphics g, int x, int y, Nodo nodo, JPanel panel) {
        Nodo padre = nodo;
        DibujaArbol2(g, panel.getWidth()/2, y, nodo);

    }

    public static void DibujaArbol2(Graphics g, int x, int y, Nodo nodo) {
        Nodo padre = nodo;

        if (padre == null) {
            //System.out.println("Nodo Vacío");
        } else {
            if (padre.codigo == 0) {
                return;
            }
            int colocacion = nodoCompleto(padre) * 40;
            g.setColor(Color.RED);
            g.drawString(Integer.toString(padre.codigo), x, y);
            g.setColor(Color.WHITE);
            g.drawString(Integer.toString(padre.valor_balance), x-15, y-15);
            if (padre.nodo_izq != null && padre.nodo_izq.codigo != 0) {
                g.drawLine(x - 3, y + 3, x - 19 - colocacion, y + 25);
            }
            if (padre.nodo_der != null && padre.nodo_der.codigo != 0) {
                g.drawLine(x + 13, y - 3, x + 26 + colocacion, y + 25);
            }
            DibujaArbol2(g, x - 30 - colocacion, y + 30, padre.nodo_izq);
            DibujaArbol2(g, x + 30 + colocacion, y + 30, padre.nodo_der);

        }
    }

    //Esta clase dibuja bien solo hay que ingresar los nodos en el orden que es
    public void DibujaArbolL(Graphics g, int x, int y, Nodo nodo, JPanel panel) {
        Nodo padre = nodo;
        DibujaArbol2L(g, 500, y, nodo);

    }

    public static void DibujaArbol2L(Graphics g, int x, int y, Nodo nodo) {
        Nodo padre = nodo;
        //M,K,F,R,Y,C,A,S,I
        if (padre == null) {
            //System.out.println("Nodo Vacío");

            //Verificar si un char es vacío '\0'
        } else {
            if (padre.codigo == '\0') {
                return;
            }

            int colocacion = nodoCompleto(padre) * 40;
            g.setColor(Color.WHITE);
            if (padre.nodo_izq != null && padre.nodo_izq.codigo != '\0') {
                g.drawLine(x - 3, y + 3, x - 19 - colocacion, y + 25);
            }
            if (padre.nodo_der != null && padre.nodo_der.codigo != '\0') {
                g.drawLine(x + 13, y - 3, x + 26 + colocacion, y + 25);
            }
            DibujaArbol2L(g, x - 30 - colocacion, y + 30, padre.nodo_izq);
            DibujaArbol2L(g, x + 30 + colocacion, y + 30, padre.nodo_der);

        }
    }

}

//Pos:      kjgfcbheida
//Pre:      abjkcfgdehi
//In:       jkbfgcaehdi

