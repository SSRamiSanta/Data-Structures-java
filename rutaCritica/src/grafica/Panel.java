/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafica;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Panel extends Canvas implements MouseListener {

    public int borrar = 0;
    private int x = 0;
    private int y = 0;
    public ArrayList<Punto> p = new ArrayList<>();
    public boolean dibujarlineas = false;
    public int tamaño = 0;
    public Grafo grafo = new Grafo();
    int medidor = 0;
    Graphics2D g2;
    public Object[][] listaAdya;

    public void Panel() {
        setBackground(Color.white);
        addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {

        g2 = (Graphics2D) g;
        g2.setColor(Color.lightGray);
        g2.fillRect(0, 0, 445, 400);
        g2.setColor(Color.gray);

        g2.setColor(Color.black);
        for (Punto punto : p) {
            //g2.setFont(new Font("Tahoma", Font.ITALIC, 20));
            g2.setColor(Color.red);
            g2.drawOval(punto.x - 8, punto.y - 15, 20, 20);
            g2.drawString(Integer.toString(punto.numero), punto.x, punto.y);
        }
        g2.setFont(new Font("Tahoma", Font.PLAIN, 17));
        medidor = 430;
        if (grafo.auxFinini <= 2) {
            if (dibujarlineas == true && !p.isEmpty()) {
                dibujarTiempotarde();
                grafigrafoVer();
                grafigrafoInv();
                tiempocer();
                tiemlej();
                activtem();
                activLej();
                rutascri();
                SeleccionarVertice();
                graficarRut();
            }
        } else {
            JOptionPane.showMessageDialog(null, "El grafo no es valido ");
        }
    }

    public void grafigrafoVer() {
        g2.drawString("Grafo original", 10, medidor);
        medidor += 20;
        for (int i = 0; i < p.size(); i++) {
            String ruta = "";
            ruta += "v" + Integer.toString(i + 1) + ":";
            for (int j = 0; j < p.size(); j++) {
                String[] arr = pasaranumero(listaAdya[i][j].toString());
                if (!arr[0].equals("0")) {
                    ruta += listaAdya[i][j].toString();
                }
                if (j < p.size() - 1) {
                    /*if (!pasaranumero(listaAdya[i][j + 1].toString())[0].equals("0")) {
                        ruta += "->";
                    }*/
                }

            }
            g2.drawString(ruta, 10, medidor);
            medidor = medidor + 20;

        }

        medidor = medidor + 40;
    }

    public void grafigrafoInv() {
        g2.drawString("Grafo invertido", 10, medidor);
        medidor += 20;

        for (Nodo nodo : grafo.cabezas) {
            String ruta = "";
            ruta += "v" + nodo.nodo + ":";
            int i = 0;
            for (Nodo anteriores : nodo.anteriores) {
                int[] arr = grafo.traerAnt(Integer.parseInt(anteriores.nodo), Integer.parseInt(nodo.nodo));
                String[] arre = pasaranumero(this.listaAdya[arr[0]][arr[1]].toString());

                ruta += anteriores.nodo + "," + arre[1] + "," + arre[2] + " ";

                i++;
                if (i < anteriores.anteriores.size() - 1) {
                    ruta += "->";
                }
            }
            g2.setColor(Color.BLUE);
            g2.drawString(ruta, 10, medidor);
            medidor = medidor + 20;
        }

        medidor = medidor + 40;
    }

    public void tiempocer() {

        g2.drawString("Tiempo Cercanos", 10, medidor);
        medidor += 20;
        String ruta = "";
        int i = 0;
        for (Nodo cabeza : grafo.cabezas) {
            if (i < p.size()) {
                ruta += cabeza.nodo + ":" + cabeza.tc + "   ";
                i++;
            }
        }
        g2.drawString(ruta, 10, medidor);
        medidor = medidor + 40;
    }

    public void tiemlej() {
        g2.drawString("Tiempo lejanos", 10, medidor);

        String ruta = "";
        int i = 0;
        for (Nodo cabeza : grafo.cabezas) {
            if (i < p.size()) {
                ruta += cabeza.nodo + ":" + cabeza.tl + "   ";
                i++;
            }
        }
        medidor = medidor + 20;
        g2.drawString(ruta, 10, medidor);
        medidor = medidor + 40;
    }

    public void graficarRut() {
        for (int[] is : grafo.rutaC) {
            g2.setColor(Color.green);
            dibujarLinea(is[0] - 1, is[1] - 1);

        }

    }

    public void dibujarLinea(int ver1, int ver2) {
        Line2D arista = new Line2D.Double();
        Punto p1 = p.get(ver1);
        Punto p2 = p.get(ver2);
        int xini = p1.x, yini = p1.y, xfin = p2.x, yfin = p2.y;

        int xComp = xfin - xini;
        if (xComp > 30) {
            int yComp = yfin - yini;
            if (yComp > 30) {
                yini += 5;
            } else if (yComp < -30) {
                yfin += 5;
            }
            xini += 5;
        } else if (xComp < -30) {
            int yComp = yfin - yini;
            if (yComp > 30) {
                yini += 5;
            } else if (yComp < -30) {
                yfin += 5;
            }
            xfin += 5;
        } else {
            int yComp = yfin - yini;
            if (yComp > 30) {
                yini += 5;
            } else if (yComp < -30) {
                yfin += 5;
            } else {
                yini += 5;
                yfin += 5;
            }
        }

        arista.setLine(xini, yini, xfin, yfin);
        g2.draw(arista);

    }

    public void activLej() {

        g2.drawString("actividad tarde lejana", 10, medidor);
        medidor += 20;
        String ruta = "";
        int i = 0;
        for (Nodo cabeza : grafo.actividadesle) {

            ruta += cabeza.actividad + ":" + cabeza.tiempo + "   ";
            i++;
        }

        g2.drawString(ruta, 10, medidor);
        medidor = medidor + 40;
    }

    public void activtem() {

        g2.drawString("activdad Cercana", 10, medidor);
        medidor += 20;
        String ruta = "";
        int i = 0;
        for (Nodo cabeza : grafo.actividadesTemp) {

            ruta += cabeza.actividad + ":" + cabeza.tiempo + "   ";
            i++;
        }

        g2.drawString(ruta, 10, medidor);
        medidor = medidor + 40;
    }

    public void rutascri() {
        g2.drawString("ruta critica evento", 10, medidor);

        String ruta = "";

        for (int j = 0; j < grafo.rutaCritica.size(); j++) {

            ruta += grafo.rutaCritica.get(j).nodo + "   ";

        }

        medidor = medidor + 20;
        g2.drawString(ruta, 10, medidor);
        ruta = "";
        medidor = medidor + 20;
        g2.drawString("ruta critica actividad", 10, medidor);

        for (int j = 0; j < grafo.actividadesTemp.size(); j++) {
            if (grafo.actividadesTemp.get(j).tiempo.equals(grafo.actividadesle.get(j).tiempo)) {
                ruta += grafo.actividadesTemp.get(j).actividad + "   ";
            }

        }

        medidor = medidor + 20;
        g2.drawString(ruta, 10, medidor);

        medidor = medidor + 40;
    }

    public void agregaralista(Object[][] listaAdya, boolean dibujarLineas) {
        this.listaAdya = listaAdya;
        dibujarlineas = dibujarLineas;

    }

    public void dibujarTiempotarde() {
        for (Punto punto : p) {
            g2.setColor(Color.black);
            g2.setFont(new Font("Tahoma", Font.PLAIN, 15));
            g2.drawString(Integer.toString(grafo.cabezas.get(punto.numero - 1).tc),
                    punto.x, punto.y - 20);
            g2.setColor(Color.RED);
            g2.drawString(Integer.toString(grafo.cabezas.get(punto.numero - 1).tl),
                    punto.x, punto.y + 20);
            g2.setColor(Color.blue);
        }
    }

    public void SeleccionarVertice() {

        for (int i = 0; i < p.size(); i++) {
            for (int j = 0; j < p.size(); j++) {
                String[] arr = pasaranumero(listaAdya[i][j].toString());
                if (!arr[0].equals("0")) {
                    dibujarLinea(i, Integer.parseInt(arr[0]) - 1);
                    dibujarActivitiempo(i,
                            Integer.parseInt(arr[0]) - 1,
                            arr[1],
                            arr[2]);
                }

            }
        }

    }

    public void dibujarActivitiempo(int ver1, int ver2, String act, String tiemp) {
        Line2D arista = new Line2D.Double();
        Punto p1 = p.get(ver1);
        Punto p2 = p.get(ver2);
        g2.setColor(Color.black);
        g2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        g2.drawString("a " + act
                + "="
                + tiemp, (p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
        g2.setColor(Color.black);
        //Integer.toString(punto.numero)
    }

    public String[] pasaranumero(String variables) {
        if (!variables.equals("")) {
            String sig = (variables.split(",")[0]);
            String act = (variables.split(",")[1]);
            String tiem = (variables.split(",")[2]);
            String[] arr = {sig, act, tiem};
            return arr;
        }
        String[] arr = {"0", "0", "0"};
        return arr;
    }

    public void mousePressed(MouseEvent e) {
        /*   saySomething("Mouse pressed; # of clicks: "
                + e.getClickCount(), e);*/
    }

    public void mouseReleased(MouseEvent e) {
        /*  saySomething("Mouse released; # of clicks: "
                + e.getClickCount(), e);*/
    }

    public void mouseEntered(MouseEvent e) {
        /*  saySomething("Mouse entered", e);*/
    }

    public void mouseExited(MouseEvent e) {
        /*saySomething("Mouse exited", e);*/
    }

    public void mouseClicked(MouseEvent e) {
        /*    saySomething("Mouse clicked (# of clicks: "
                + e.getClickCount() + ")", e);*/
        if (e.getY() < 400 && e.getX() < 400) {
            p.add(new Punto(e.getX(), e.getY(), p.size() + 1));
            x = e.getX();
            y = e.getY();
            repaint();
        }
    }

    void saySomething(String eventDescription, MouseEvent e) {
        System.out.println(eventDescription + " detected on "
                + e.getComponent().getClass().getName()
                + ".");
    }

}
