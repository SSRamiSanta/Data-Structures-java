package arbolrojonegro;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author ssrs_
 */
public class dibujar {

    ArbolRojoNegro arbolitoAVL = new ArbolRojoNegro();

    public static int nodoCompleto(Nodo nodo) {
        Nodo p = nodo;
        if (p == null) {
            return 0;
        } else {
            if (p.hijoIzquierdo != null && p.hijoDerecho != null) {
                return nodoCompleto(p.hijoIzquierdo) + nodoCompleto(p.hijoDerecho) + 1;
            } else {
                return nodoCompleto(p.hijoIzquierdo) + nodoCompleto(p.hijoDerecho);
            }
        }
    }

    public void DibujaArbol(Graphics g, int x, int y, Nodo nodo, JPanel panel) {
        ArbolRojoNegro arbolitoAVL = new ArbolRojoNegro();
        Nodo padre = nodo;
        DibujaArbol2(g, panel.getWidth() / 2, y, nodo);

    }

    public static void DibujaArbol2(Graphics g, int x, int y, Nodo nodo) {
        ArbolRojoNegro arbolitoAVL = new ArbolRojoNegro();
        Nodo padre = nodo;

        if (padre == null) {
            System.out.println("nodo vacio");
        } else {
            if (padre.clave == 0) {
                return;
            }
            int extra = nodoCompleto(padre) * 15;
            System.out.println("extra " + extra);
            arbolitoAVL.getColor(padre);
            if (arbolitoAVL.getColor(padre) == 1) {
                g.setColor(Color.red);
                g.drawString(Integer.toString(padre.clave), x, y);
            } else if (arbolitoAVL.getColor(padre) == 0) {
                g.setColor(Color.black);
                g.drawString(Integer.toString(padre.clave), x, y);
            }
            if (padre.hijoDerecho.color == 1) {
                g.setColor(Color.red);
            } else {
                g.setColor(Color.black);
            }

            if (padre.hijoIzquierdo != null && padre.hijoIzquierdo.clave != 0) {
                if (padre.hijoIzquierdo.color == 1) {
                    g.setColor(Color.red);
                } else {
                    g.setColor(Color.black);
                }
                g.drawLine(x - 3, y + 3, x - 13 - extra, y + 20);
            }
            if (padre.hijoDerecho != null && padre.hijoDerecho.clave != 0) {
                if (padre.hijoDerecho.color == 1) {
                    g.setColor(Color.red);
                } else {
                    g.setColor(Color.black);
                }
                g.drawLine(x + 13, y - 3, x + 26 + extra, y + 20);
            }
            DibujaArbol2(g, x - 20 - extra, y + 30, padre.hijoIzquierdo);
            DibujaArbol2(g, x + 20 + extra, y + 30, padre.hijoDerecho);

        }
    }

    public static void inicializaArbol(Graphics g, int x, int y, String i) {
        System.out.println("inicializado");
        g.drawString(i, x, y);

    }

}
