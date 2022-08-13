/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import djikstra.prim.Logica;
import djikstra.prim.Vertices;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author ssrs_
 */
public class Dibujar {

    public void graficarDijkstra(ArrayList<Vertices> vertices, int[] minimos, int[][] matrizDeAdyacencia, int[] padres, Logica l, Graphics g) {

        for (Vertices v : vertices) {
            g.setColor(Color.red);
            g.drawOval(v.getPosX()-5, v.getPosY()-20, 25, 25);
            g.drawString(v.getLlave() + "," + minimos[v.getLlave() - 1], v.getPosX(), v.getPosY()); // pinto el
        }
        g.setColor(Color.black);
        int n = vertices.size();
        if (matrizDeAdyacencia != null) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrizDeAdyacencia[i][j] != -1 && padres[j] == i && i != j) { // si hay arista entre el vertice
                        // i y el vertice j e i es padre
                        // de j
                        int x1 = vertices.get(i).getPosX();
                        int y1 = vertices.get(i).getPosY();
                        int x2 = 0;
                        int y2 = 0;

                        if (vertices.get(j).getPosX() - x1 <= 5 && vertices.get(j).getPosX() - x1 >= -5) { // si los
                            // vertices
                            // estan uno
                            // sobre
                            // otro
                            x1 = x1 + 4;
                            x2 = vertices.get(j).getPosX() + 4;
                        } else if (vertices.get(j).getPosX() >= x1) { // si el vertice j esta a la derecha
                            x1 = x1 + 15;
                            x2 = vertices.get(j).getPosX();
                        } else { // si el vertice j esta a la izquierda
                            x2 = vertices.get(j).getPosX() + 15;
                        }

                        if (vertices.get(j).getPosY() - y1 <= 10 && vertices.get(j).getPosY() - y1 >= -10) { // si la
                            // diferencia
                            // entre
                            // alturas
                            // es
                            // menor
                            // a 10
                            y1 = y1 - 5;
                            y2 = vertices.get(j).getPosY() - 5;
                        } else if (vertices.get(j).getPosY() >= y1) { // si el vertice j esta abajo
                            y2 = vertices.get(j).getPosY() - 10;
                        } else { // si el vertice j esta arriba
                            y1 = y1 - 10;
                            y2 = vertices.get(j).getPosY();
                        }
                        g.drawLine(x1, y1, x2, y2); // pinto la arista
                    }
                }
            }
        }
    }
    
    
}
