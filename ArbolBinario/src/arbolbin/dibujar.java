/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbin;

import arbolbin.ArbolBinario.Nodo;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author ssrs_
 */
public class dibujar {

    public static int nodoCompleto(Nodo nodo) {
        Nodo p = nodo;
        if (p == null) {
            return 0;
        } else {
            if (p.izq != null && p.der != null) {
                return nodoCompleto(p.izq) + nodoCompleto(p.der) + 1;
            } else {
                return nodoCompleto(p.izq) + nodoCompleto(p.der);
            }
        }
    }

    public void DibujaArbol(Graphics g, int x, int y, Nodo nodo, JPanel panel) {
        DibujaArbol2(g, x, y, nodo);
    }

    public static void DibujaArbol2(Graphics g, int x, int y, Nodo nodo) {
        Nodo padre = nodo;

        if (padre == null) {
            //System.out.println("Nodo Vacío");
        } else {
            if (padre.info == 0) {
                return;
            }
            int colocacion = nodoCompleto(padre) * 40;
            g.setColor(Color.WHITE);
            g.drawString(Integer.toString(padre.info), x, y);
            if (padre.izq != null && padre.izq.info != 0) {
                g.drawLine(x - 3, y + 3, x - 19 - colocacion, y + 25);
            }
            if (padre.der != null && padre.der.info != 0) {
                g.drawLine(x + 13, y - 3, x + 26 + colocacion, y + 25);
            }
            DibujaArbol2(g, x - 30 - colocacion, y + 30, padre.izq);
            DibujaArbol2(g, x + 30 + colocacion, y + 30, padre.der);

        }
    }
}
