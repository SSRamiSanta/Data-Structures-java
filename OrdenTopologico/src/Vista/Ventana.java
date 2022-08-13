/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import ordentopologico.TopoLogica;

/**
 *
 * @author ssrs_
 */
public class Ventana extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

    private JPanel P_Titulo, P_Ingreso, P_Grafo, P_Adyacencia;
    private JLabel L_Titulo, L_Dibujo, L_Adyacencia, coordenadas;
    private JButton B_Reinicio, B_Adyacencia, B_Grafo, B_Topologico, B_Cerrar;
    Color black, graywhite, gray, grayheavy, grayblack, white;
    int x, y, contador;
    private TopoLogica tl;
    private Graphics g;

    private int matrizAd[][];
    private JTextField leer[][];

    JScrollPane scrollMat = new JScrollPane(P_Adyacencia, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    public Ventana() {
        setTitle("Orden topologico");
        setLayout(null);
        setIconImage(new ImageIcon(Ventana.class.getResource("/imagenes/icono.png")).getImage());
        getRootPane().setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));
        this.getContentPane().setBackground(Color.DARK_GRAY);
        setUndecorated(true);
        setBounds(30, 30, 800, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(Color.darkGray);
        getRootPane().setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));
        tl = new TopoLogica();
        setDatos();
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        Colores();
        Paneles();
        Etiquetas();
        Botones();
    }

    private void Colores() {
        black = new Color(0, 0, 0);
        graywhite = new Color(180, 180, 180);
        gray = new Color(100, 100, 100);
        grayheavy = new Color(50, 50, 50);
        grayblack = new Color(25, 25, 25);
        white = new Color(250, 250, 250);
    }

    private void Paneles() {
        P_Titulo = new JPanel();
        P_Titulo.setLayout(null);
        P_Titulo.setBounds(0, 0, 1350, 50);
        P_Titulo.setBackground(grayblack);
        this.getContentPane().add(P_Titulo);

        P_Grafo = new JPanel();
        P_Grafo.setLayout(null);
        P_Grafo.setBounds(0, 55, 800, 300);
        P_Grafo.setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));
        P_Grafo.setBackground(white);
        P_Grafo.addMouseListener(this);
        P_Grafo.addMouseMotionListener(this);
        P_Grafo.setVisible(true);

        P_Ingreso = new JPanel();
        P_Ingreso.setLayout(null);
        P_Ingreso.setBounds(0, 50, 800, 700);
        P_Ingreso.setBackground(gray);
        this.getContentPane().add(P_Ingreso);
        P_Ingreso.add(P_Grafo);

    }

    private void Etiquetas() {
        L_Titulo = new JLabel("Orden Topológico");
        L_Titulo.setForeground(white);
        L_Titulo.setFont(new Font("Courier new", Font.BOLD, 25));
        L_Titulo.setBounds(30, 15, 400, 25);
        P_Titulo.add(L_Titulo);

        L_Dibujo = new JLabel("<html><left>Dibujar grafo dirigido<html>");
        L_Dibujo.setForeground(black);
        L_Dibujo.setFont(new Font("Courier new", Font.BOLD, 25));
        L_Dibujo.setBounds(30, 10, 400, 40);
        P_Ingreso.add(L_Dibujo);

    }

    private void Botones() {

        B_Reinicio = new JButton("<html><center> Reiniciar <html>");
        B_Reinicio.setFont(new Font("Times new roman", Font.BOLD, 15));
        B_Reinicio.setBounds(this.getWidth() - 200, 10, 100, 30);
        B_Reinicio.addActionListener(this);
        P_Titulo.add(B_Reinicio).setVisible(false);

        B_Adyacencia = new JButton("<html><center>Lista adyacencia<html>");
        B_Adyacencia.setFont(new Font("Consolas", Font.BOLD, 15));
        B_Adyacencia.setBounds(400, 5, 150, 40);
        P_Ingreso.add(B_Adyacencia).setVisible(false);
        B_Adyacencia.addActionListener(this);

        B_Cerrar = new JButton("<html><center>X<html>");
        B_Cerrar.setFont(new Font("Consolas", Font.BOLD, 30));
        B_Cerrar.setBounds(this.getWidth() - 70, 10, 40, 30);
        P_Titulo.add(B_Cerrar).setVisible(true);
        B_Cerrar.addActionListener(this);

        B_Topologico = new JButton("<html><center>Orden Topologico<html>");
        B_Topologico.setFont(new Font("Consolas", Font.BOLD, 18));
        B_Topologico.setBounds(this.getWidth() - 600, 660, 400, 30);
        P_Ingreso.add(B_Topologico).setVisible(false);
        B_Topologico.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == B_Adyacencia) {
            System.out.println("Adyacencia...");
            metodoMatriz();
            B_Reinicio.setVisible(true);
        }
        if (e.getSource() == B_Grafo) {
            System.out.println("Creando grafo...");
            int f = tl.getVertices().size();
            int c = tl.getVertices().size();
            if (guardarMatriz(f, c)) {
                llenarAdyacencia();
                imprimirAdyacencia();
                dibujarLineas();
                B_Topologico.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "El vertice no existe o se referencia a si mismo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == B_Reinicio) {
            System.out.println("Reinciando...");
            setDatos();
            P_Ingreso.repaint();
            B_Topologico.setVisible(false);
            P_Adyacencia.setVisible(false);
            matrizAd = null;
            leer = null;

        }
        if (e.getSource() == B_Cerrar) {
            System.exit(0);
        }
        if (e.getSource() == B_Topologico) {
            System.out.println("Calculando orden topologico...");
            JOptionPane.showMessageDialog(null, "Orden: " + tl.crearAdyacencia());
        }
    }

    private void metodoMatriz() {
        int f, c;

        f = tl.getVertices().size();
        c = tl.getVertices().size();

        scrollMat.setBounds(0, 355, 800, 300);
        scrollMat.getHorizontalScrollBar().setCursor(new Cursor(Cursor.HAND_CURSOR));
        scrollMat.getHorizontalScrollBar().setBackground(Color.BLACK);
        scrollMat.getVerticalScrollBar().setCursor(new Cursor(Cursor.HAND_CURSOR));
        scrollMat.getVerticalScrollBar().setBackground(Color.BLACK);

        P_Adyacencia = new JPanel();
        scrollMat.setViewportView(P_Adyacencia);
        scrollMat.setVisible(true);
        P_Adyacencia.setLayout(null);
        P_Adyacencia.setBackground(graywhite);
        P_Adyacencia.setPreferredSize(new Dimension((c * 100) + 75, (f * 60) + 75));

        P_Ingreso.add(scrollMat);

        P_Adyacencia.setVisible(true);

        L_Adyacencia = new JLabel("Lista Adyacencia");
        L_Adyacencia.setForeground(white);
        L_Adyacencia.setFont(new Font("Courier new", Font.BOLD, 25));
        L_Adyacencia.setBounds(30, 15, 400, 20);
        P_Adyacencia.add(L_Adyacencia).setVisible(true);

        matrizAd = new int[f][c];
        leer = new JTextField[c][f];

        for (y = 0; y < f; y++) {
            for (x = 0; x < c; x++) {
                leer[x][y] = new JTextField();
                P_Adyacencia.add(leer[x][y]);
                leer[x][y].setBounds(-50 + (82 * (x + 1)), 20 + (30 * (y + 1)), 50, 20);
                leer[x][y].setText("");
                leer[x][y].setBackground(Color.LIGHT_GRAY);
            }
        }
        int posBoton = 30 + (30 * (y + 1));
        B_Grafo = new JButton("<html><center>Crear Grafo<html>");
        B_Grafo.setFont(new Font("Consolas", Font.BOLD, 15));
        P_Adyacencia.add(B_Grafo).setVisible(true);
        B_Grafo.addActionListener(this);
        B_Grafo.setBounds(20, posBoton, 150, 40);

        for (int i = 0; i < f; i++) {
            coordenadas = new JLabel();
            P_Adyacencia.add(coordenadas);
            coordenadas.setText(Integer.toString(i + 1) + "->");
            coordenadas.setBounds(5, 20 + (30 * (i + 1)), 50, 20);
        }
    }

    private boolean guardarMatriz(int f, int c) {
        for (int i = 0; i < f; i++) {
            for (int j = 0; j < c; j++) {
                if (leer[j][i].getText().equals("")) {
                    matrizAd[i][j] = 0;
                } else {
                    matrizAd[i][j] = Integer.parseInt(leer[j][i].getText());
                }
                if (matrizAd[i][j] != 0) {
                    if (matrizAd[i][j] == (i + 1) || !tl.getVertices().contains(matrizAd[i][j])) {
                        return false;
                    }
                }
                System.out.print(matrizAd[i][j] + " ");
            }
            System.out.println("");
        }

        return true;
    }

    private void llenarAdyacencia() {
        int nVertices = tl.getVertices().size();
        ArrayList<Integer>[] Ady = new ArrayList[nVertices];
        for (int i = 0; i < nVertices; i++) {
            Ady[i] = new ArrayList();
            for (int j = 0; j < nVertices - 1; j++) {
                if (matrizAd[i][j] != 0) {
                    Ady[i].add(matrizAd[i][j]);
                }
            }
        }
        tl.setAdyacencia(Ady);
    }

    private void imprimirAdyacencia() {
        for (int i = 0; i < tl.getVertices().size(); i++) {
            System.out.print((i + 1) + "-> ");
            int lim = tl.getAdyacencia()[i].size();
            for (int j = 0; j < lim; j++) {
                System.out.print("[" + tl.getAdyacencia()[i].get(j) + "]");
            }
            System.out.println("");
        }
    }

    private void dibujarLineas() {
        g = P_Grafo.getGraphics();
        ArrayList<Integer>[] Ady = tl.getAdyacencia();
        for (int i = 0; i < Ady.length; i++) {
            for (int j = 0; j < Ady[i].size(); j++) {
                anadirLinea(i + 1, Ady[i].get(j), g);
            }
        }
    }

    private void setDatos() {
        contador = 0;
        x = 0;
        y = 0;
        tl.setDatos();
    }

    public void anadirVertice(int x, int y, Graphics g) {
        contador++;
        g.setColor(Color.red);
        g.drawOval(x, y, 20, 20);
        g.drawString(String.valueOf(contador), x + 3, y + 15);
        tl.getCoordVertices().add(x + "," + y);
        tl.getVertices().add(contador);
        System.out.println("Vertice " + contador + " creado");
    }

    public void anadirLinea(int x, int y, Graphics g) {
        String[] cooIn, cooFn;
        int xini = 0, yini = 0, xfin = 0, yfin = 0;
        int f1xfin = 0, f1yfin = 0, f2xfin = 0, f2yfin = 0;
        try {
            for (int i = 0; i < tl.getCoordVertices().size(); i++) {
                if (x == (i + 1)) {
                    cooIn = tl.getCoordVertices().get(i).split(",");
                    xini = Integer.parseInt(cooIn[0]);
                    yini = Integer.parseInt(cooIn[1]);
                } else if (y == (i + 1)) {
                    cooFn = tl.getCoordVertices().get(i).split(",");
                    xfin = Integer.parseInt(cooFn[0]);
                    yfin = Integer.parseInt(cooFn[1]);
                }
            }

            int xComp = xfin - xini;
            if (xComp > 30) {
                int yComp = yfin - yini;
                //Diagonal arriba-abajo derecha
                if (yComp > 30) {
                    yini += 20;
                    f1xfin = -5;
                    f1yfin = -10;
                    f2xfin = -10;
                    f2yfin = +10;
                    //Diagonal abajo-arriba derecha
                } else if (yComp < -30) {
                    yfin += 20;
                    f1xfin = 0;
                    f1yfin = +10;
                    f2xfin = -10;
                    f2yfin = -5;
                    //Horizontal a la derecha
                } else {
                    f1xfin = -5;
                    f1yfin = -10;
                    f2xfin = -5;
                    f2yfin = +10;
                    yini += 5;
                    yfin += 5;
                }
                xini += 20;
            } else if (xComp < -30) {
                int yComp = yfin - yini;
                //Diagonal arriba-abajo izquierda
                if (yComp > 30) {
                    f1xfin = -5;
                    f1yfin = -10;
                    f2xfin = +15;
                    f2yfin = -5;
                    yini += 20;
                    //Diagonal abajo-arriba izquierda
                } else if (yComp < -30) {
                    yfin += 20;
                    f1xfin = +10;
                    f1yfin = -5;
                    f2xfin = -5;
                    f2yfin = +15;
                    //Horizontal a la izquierda
                } else {
                    yini += 5;
                    yfin += 5;
                    f1xfin = +10;
                    f1yfin = -5;
                    f2xfin = +10;
                    f2yfin = +10;
                }
                xfin += 20;
            } else {
                int yComp = yfin - yini;
                //Vertical hacia abajo
                if (yComp > 30) {
                    yini += 20;
                    f1xfin = +10;
                    f1yfin = -5;
                    f2xfin = -10;
                    f2yfin = -5;
                    //Vertical hacia arriba
                } else if (yComp < -30) {
                    yfin += 20;
                    f1xfin = +10;
                    f1yfin = +5;
                    f2xfin = -10;
                    f2yfin = +5;
                    //Linea muy recta
                } else {
                    yini += 5;
                    yfin += 5;
                }
                xini += 5;
                xfin += 5;
            }
            g.drawLine(xini, yini, xfin, yfin);
            g.drawLine(xfin, yfin, xfin + f1xfin, yfin + f1yfin);
            g.drawLine(xfin, yfin, xfin + f2xfin, yfin + f2yfin);
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "No se encontro vertice");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            B_Adyacencia.setVisible(true);
            x = e.getX();
            y = e.getY();
            g = P_Grafo.getGraphics();
            anadirVertice(x, y, g);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
