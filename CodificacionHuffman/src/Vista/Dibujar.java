/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Color;
import java.awt.Graphics;
import logica.Arbol.Nodo;

/**
 *
 * @author ssrs_
 */
public class Dibujar {

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

    public void DibujaArbol(Graphics g, int y, Nodo nodo) {
        //DibujaArbol2(g, 500, y, nodo);
    }

    public void DibujaArbol2(Graphics g, int x, int y,int cantidad, Nodo nodo) {
        Nodo padre = nodo;
        if (padre == null) {
        } else {
            if (padre.info == null) {
                return;
            }
            
            int colocacion = nodoCompleto(padre) * (cantidad);
            g.setColor(Color.WHITE);

            g.drawString(padre.info, x, y);
            if (padre.izq != null) {
                g.drawLine(x - 5, y , x - colocacion, y + 15);
            }
            if (padre.der != null) {
                g.drawLine(x + 12, y - 3, x + colocacion, y + 15);
            }
            DibujaArbol2(g, x - 22 - (colocacion- 20), y + 30,cantidad, padre.izq);
            DibujaArbol2(g, x + 22 + (colocacion-20), y + 30,cantidad, padre.der);

        }
    }
}
