/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import logica.Arbol.Nodo;

/**
 *
 * @author ssrs_
 */
public class Dibujo extends JPanel {

    Nodo nodo;
    String[] datos;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        DibujaArbol(g, 600, 50, nodo, datos);

    }

    public void recibir(Nodo nodo, String[] datos) {
        this.nodo = nodo;
        this.datos = datos;
    }

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
    int cont = 0;

    public void DibujaArbol(Graphics g, int x, int y, Nodo nodo, String[] datos) {
        if (nodo == null) {
            return;
        } else {
            if (nodo.info == null) {
                return;
            }

            int colocacion = nodoCompleto(nodo) * 40;
            String[] datos1 = nodo.info.split(",");
            g.setFont(new Font("Times new roman", Font.BOLD, 20));
            g.setColor(Color.WHITE);
            if (datos1[0].equals(datos[0])) {
                g.setColor(Color.RED);
                g.drawString(nodo.info + "(" + datos[2] + ")", x, y);
            } else {
                g.drawString(nodo.info, x, y);
            }
            g.setColor(Color.WHITE);
            if (nodo.izq != null) {
                g.drawLine(x - 1, y + 1, x - 14 - colocacion, y + 23);
            }
            if (nodo.der != null) {
                g.drawLine(x + 25, y +4, x + 40 + colocacion, y + 20);
            }
            DibujaArbol(g, x - 40 - colocacion, y + 40, nodo.izq, datos);
            DibujaArbol(g, x + 40 + colocacion, y + 40, nodo.der, datos);

        }
    }
}
