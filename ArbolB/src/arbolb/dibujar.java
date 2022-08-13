package arbolb;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class dibujar {

    Color blanco = new Color(250, 250, 250);
    ProcesosArbolesB ParbB;
    Nodo nodo;

    public void pintar(Graphics g, Nodo padre, Nodo hijo) {

    }

    public void DibujaArbol(Graphics g, int x, int y, Nodo nodo) {
        y = 20;
        DibujaArbol2(g, 500, y, nodo);
    }

    public void DibujaArbol2(Graphics g, int x, int y, Nodo nodo) {
        Nodo padre = nodo;
        if (padre == null) {
        } else {
            int extra;
            switch (padre.getNivel()) {
                case 0:
                    extra = 0;
                    break;
                case 1:
                    extra = 120;
                    break;
                case 2:
                    extra = 180;
                    break;
                case 3:
                    extra = 220;
                    break;
                case 4:
                    extra = 350;
                    break;
                default:
                    extra = 0;
            }
            if (nodo.getValoresNodos().size() > 1) {
                String nodos = "|";
                for (int i = 0; i <= nodo.getValoresNodos().size() - 1; i++) {
                    nodos = nodos + nodo.getValoresNodos().get(i);
                    if (!nodo.getValoresNodos().get(i).equals(nodo.getValoresNodos().get(nodo.getValoresNodos().size() - 1))) {
                        nodos = nodos + ",";
                    }
                }
                nodos = nodos + "|";
                g.setColor(blanco);
                g.setFont(new Font("Times new roman", Font.BOLD, 18));
                g.drawString(nodos, x, y);
            } else {
                String nodos = "|" + nodo.getValoresNodos().get(0) + "|";
                g.setColor(blanco);
                g.setFont(new Font("Times new roman", Font.BOLD, 18));
                g.drawString(nodos, x, y);
            }

            if (padre.hijoizquierda != null && padre.hijoizquierda.getValoresNodos() != null) {
                
                g.setColor(blanco);
                g.drawLine(x - 3, y + 3, (x - 186 + extra), y + 15);
            }
            if (padre.hijoderecha != null && padre.hijoderecha.getValoresNodos() != null) {
                g.setColor(blanco);
                g.drawLine(x + 6, y + 3, (x + 216 - extra), y + 25);

            }
            if (padre.hijomitad != null && padre.hijoderecha.getValoresNodos() != null) {
                g.setColor(blanco);
                g.drawLine(x, y + 3, x + 3, y + 20);
            }

            DibujaArbol2(g, (x - 220 + extra), y + 30, padre.hijoizquierda);
            DibujaArbol2(g, (x + 220 - extra), y + 30, padre.hijoderecha);
            DibujaArbol2(g, x, y + 30, padre.hijomitad);

        }
    }

    public void DibujarFlechas(Graphics g, int x, int y, int difx, int dify) {
        g.drawLine(x, y, x + difx, y + dify);

    }

    public static void inicializaArbol(Graphics g, int x, int y, String i) {
        System.out.println("inicializado");

        g.drawString(i, x, y);

    }

}
