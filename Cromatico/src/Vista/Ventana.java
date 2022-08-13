/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import cromatico.Colorear;
import cromatico.Logica;
import cromatico.Vertices;
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

/**
 *
 * @author ssrs_
 */
public class Ventana extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

    private JPanel P_Titulo, P_Ingreso, P_Grafo, P_Adyacencia, P_Cromatico;
    private JLabel L_Titulo, L_Dibujo, L_Adyacencia, L_nCromatico, L_Permutacion, coordenadas;
    private JButton B_Cromatico, B_Reinicio, B_Adyacencia, B_Grafo, B_Cerrar;
    Color black, graywhite, gray, grayheavy, grayblack, white;
    private int x, y, contador, numCromatico;
    private String permutaciones;
    private Logica l;
    private ArrayList Vertices;
    private ArrayList<Vertices> v;
    private Graphics g;
    private int[][] matrizAd;
    private JTextField leer[][];
    private ArrayList<Color> colores;
    private ArrayList<Colorear> verticesColores;

    JScrollPane scrollMat = new JScrollPane(P_Adyacencia, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    JScrollPane scrollArb = new JScrollPane(P_Adyacencia, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    public Ventana() {
        setLayout(null);
        setIconImage(new ImageIcon(Ventana.class.getResource("/imagenes/icono.png")).getImage());
        setUndecorated(true);
        getRootPane().setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));
        this.getContentPane().setBackground(Color.DARK_GRAY);
        setBounds(30, 30, 1350, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(Color.darkGray);
        getRootPane().setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));

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
        P_Grafo.setBounds(0, 55, 450, 345);
        P_Grafo.setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));
        P_Grafo.setBackground(white);
        P_Grafo.addMouseListener(this);
        P_Grafo.addMouseMotionListener(this);
        P_Grafo.setVisible(true);

        P_Ingreso = new JPanel();
        P_Ingreso.setLayout(null);
        P_Ingreso.setBounds(0, 50, 900, 450);
        P_Ingreso.setBackground(gray);
        add(P_Ingreso);
        P_Ingreso.add(P_Grafo);

        P_Cromatico = new JPanel();
        P_Cromatico.setBounds(450, 55, 450, 345);
        P_Cromatico.setBackground(Color.gray);
        P_Ingreso.add(P_Cromatico).setVisible(true);

    }

    private void Etiquetas() {
        L_Titulo = new JLabel("Número Cromático");
        L_Titulo.setForeground(white);
        L_Titulo.setFont(new Font("Courier new", Font.BOLD, 25));
        L_Titulo.setBounds(30, 15, 400, 20);
        P_Titulo.add(L_Titulo);

        L_Dibujo = new JLabel("<html><left>Dibujar grafo<html>");
        L_Dibujo.setForeground(black);
        L_Dibujo.setFont(new Font("Courier new", Font.BOLD, 20));
        L_Dibujo.setBounds(30, 10, 400, 40);
        P_Ingreso.add(L_Dibujo);

        L_nCromatico = new JLabel("");
        L_nCromatico.setForeground(black);
        L_nCromatico.setFont(new Font("Courier new", Font.BOLD, 30));
        L_nCromatico.setBounds(450, 10, 450, 40);
        P_Ingreso.add(L_nCromatico).setVisible(false);

        L_Permutacion = new JLabel("");
        L_Permutacion.setForeground(black);
        L_Permutacion.setFont(new Font("Courier new", Font.BOLD, 30));
        L_Permutacion.setBounds(15, 408, 1000, 40);
        P_Ingreso.add(L_Permutacion).setVisible(true);
    }

    private void Botones() {

        B_Reinicio = new JButton("<html><center> Reiniciar <html>");
        B_Reinicio.setFont(new Font("Times new roman", Font.BOLD, 15));
        B_Reinicio.setBounds(1100, 10, 100, 30);
        B_Reinicio.addActionListener(this);
        P_Titulo.add(B_Reinicio).setVisible(false);

        B_Adyacencia = new JButton("<html><center>Matriz adyacencia<html>");
        B_Adyacencia.setFont(new Font("Consolas", Font.BOLD, 15));
        B_Adyacencia.setBounds(250, 5, 150, 40);
        P_Ingreso.add(B_Adyacencia).setVisible(false);
        B_Adyacencia.addActionListener(this);

        B_Cerrar = new JButton("<html><center>X<html>");
        B_Cerrar.setFont(new Font("Consolas", Font.BOLD, 30));
        B_Cerrar.setForeground(black);
        B_Cerrar.setBackground(gray);
        B_Cerrar.setBounds(P_Titulo.getWidth() - 70, 10, 40, 30);
        P_Titulo.add(B_Cerrar).setVisible(true);
        B_Cerrar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == B_Grafo) {
            System.out.println("Creando grafo...");
            int f = v.size();
            int c = v.size();
            guardarMatriz(f, c);
            g = P_Grafo.getGraphics();
            dibujarLineas(g);
            B_Cromatico.setVisible(true);
            L_nCromatico.setVisible(true);
            B_Reinicio.setVisible(true);
        }
        if (e.getSource() == B_Cromatico) {
            System.out.println("Calculando Cromatico...");
            l.setVertices(v);
            matrizAdy();
            colores = new ArrayList();
            crearColores();
            verticesColores = l.getGrafoColor().getVertices();
            numCromatico = l.getNumeroCromatico();
            permutaciones = l.getPermutaciones();
            g = P_Cromatico.getGraphics();
            String permuta = "";
            for (int i = 0; i < permutaciones.length() - 1; i++) {
                permuta += permutaciones.charAt(i);
            }
            DibujarCromatico(g);
            L_nCromatico.setText("Número cromático: " + numCromatico + "");
            L_nCromatico.setVisible(true);
            L_Permutacion.setText("Permutacion: " + permuta);
        }
        if (e.getSource() == B_Reinicio) {
            System.out.println("Reinciando...");
            setDatos();
            P_Ingreso.repaint();
            P_Cromatico.repaint();
            P_Adyacencia.setVisible(false);
            B_Cromatico.setVisible(false);
            B_Adyacencia.setVisible(false);
            L_nCromatico.setText("");
            L_Permutacion.setText("");
            L_nCromatico.setVisible(false);
            matrizAd = null;
            leer = null;
        }
        if (e.getSource() == B_Adyacencia) {
            System.out.println("Adyacencia...");
            metodoMatriz();
        }

        if (e.getSource() == B_Cerrar) {
            System.exit(0);
        }
    }

    private int DibujarCromatico(Graphics g) {
        int x, y, minimo = 0;
        contador = 0;
        System.out.println("Tamaño v: " + v.size());

        for (int i = 0; i < v.size(); i++) {

            String[] coordenadas = l.getCoordVertices().get(i).split(",");

            x = Integer.parseInt(coordenadas[0]);
            y = Integer.parseInt(coordenadas[1]);
            anadirCirculo2(x, y, g, i);
        }
        g.setColor(black);
        dibujarLineas(g);
        return minimo;
    }

    public void metodoMatriz() {
        int f, c;

        f = v.size();
        c = v.size();

        scrollMat.setBounds(900, 50, 450, 450);
        scrollMat.getHorizontalScrollBar().setCursor(new Cursor(Cursor.HAND_CURSOR));
        scrollMat.getHorizontalScrollBar().setBackground(Color.BLACK);
        scrollMat.getVerticalScrollBar().setCursor(new Cursor(Cursor.HAND_CURSOR));
        scrollMat.getVerticalScrollBar().setBackground(Color.BLACK);

        P_Adyacencia = new JPanel();
        scrollMat.setViewportView(P_Adyacencia);
        scrollMat.setVisible(true);
        P_Adyacencia.setLayout(null);
        P_Adyacencia.setBackground(graywhite);
        P_Adyacencia.setPreferredSize(new Dimension((c * 100) + 75, (f * 200) + 75));

        add(scrollMat);

        P_Adyacencia.setVisible(true);

        L_Adyacencia = new JLabel("Matriz de adyacencia");
        L_Adyacencia.setForeground(black);
        L_Adyacencia.setFont(new Font("Courier new", Font.BOLD, 25));
        L_Adyacencia.setBounds(30, 15, 400, 20);
        P_Adyacencia.add(L_Adyacencia).setVisible(true);

        matrizAd = new int[f][c];
        leer = new JTextField[c][f];

        for (y = 0; y < f; y++) {
            for (x = 0; x < c; x++) {
                leer[x][y] = new JTextField();
                P_Adyacencia.add(leer[x][y]);
                leer[x][y].setBounds(-50 + (82 * (x + 1)), 40 + (30 * (y + 1)), 50, 20);
                if (x == y) {
                    leer[x][y].setText("0");
                    leer[x][y].setEditable(false);
                } else if (x < y) {
                    leer[x][y].setText("x");
                    leer[x][y].setEditable(false);
                }
                leer[x][y].setBackground(Color.LIGHT_GRAY);

            }
        }
        int posBoton = 40 + (30 * (y + 1));
        B_Grafo = new JButton("<html><center>Crear Grafo<html>");
        B_Grafo.setFont(new Font("Consolas", Font.BOLD, 15));
        P_Adyacencia.add(B_Grafo).setVisible(true);
        B_Grafo.addActionListener(this);
        B_Grafo.setBounds(20, posBoton, 150, 40);

        B_Cromatico = new JButton("<html><center> Cromatico <html>");
        B_Cromatico.setFont(new Font("Times new roman", Font.BOLD, 15));
        B_Cromatico.setBounds(200, posBoton, 130, 40);
        B_Cromatico.addActionListener(this);
        P_Adyacencia.add(B_Cromatico).setVisible(false);

        for (int i = 0; i < f; i++) {
            coordenadas = new JLabel();
            P_Adyacencia.add(coordenadas);
            coordenadas.setText(Integer.toString(i + 1));
            coordenadas.setBounds(5, 40 + (30 * (i + 1)), 50, 20);
        }
        for (int i = 0; i < c; i++) {
            coordenadas = new JLabel();
            P_Adyacencia.add(coordenadas);
            coordenadas.setText(Integer.toString(i + 1));
            coordenadas.setBounds(-40 + (82 * (i + 1)), 40, 50, 20);
        }
    }

    public void matrizAdy() {
        int n = v.size();
        int aux[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int dato;
                if ("x".equals(leer[j][i].getText())) {
                    dato = 0;
                } else {
                    dato = Integer.parseInt(leer[j][i].getText());
                }
                aux[i][j] = dato;
                aux[j][i] = dato;
            }
        }
        l.setAdyacencias(aux);
        l.imprimirAdy();
    }

    public boolean guardarMatriz(int f, int c) {
        for (int i = 0; i < f; i++) {
            for (int j = 0; j < c; j++) {
                if (leer[j][i].getText().equals("0") || leer[j][i].getText().equals("x") || leer[j][i].getText().equals("99")) {
                    matrizAd[i][j] = 0;
                } else {
                    matrizAd[i][j] = Integer.parseInt(leer[j][i].getText());
                }
                System.out.print(matrizAd[i][j] + " ");
            }
            System.out.println("");
        }

        return true;
    }

    private void dibujarLineas(Graphics g) {
        for (int i = 0; i < matrizAd[0].length; i++) {
            for (int j = 0; j < matrizAd.length; j++) {
                if (matrizAd[i][j] != 0 && matrizAd[i][j] != 99) {
                    anadirLinea(i + 1, j + 1, g);
                }
            }
        }
    }

    public void setDatos() {
        l = new Logica();
        v = new ArrayList<Vertices>();
        Vertices = new ArrayList();
        contador = 0;
        x = 0;
        y = 0;
        l.setDatos();
    }

    public void anadirCirculo(int x, int y, Graphics g) {
        contador++;
        g.setColor(Color.BLACK);
        g.drawOval(x, y, 20, 20);
        g.drawString(String.valueOf(contador), x + 3, y + 15);
        l.getCoordVertices().add(x + "," + y);
        Vertices.add(contador);
        v.add(new Vertices(contador, x, y));
    }

    public void anadirCirculo2(int x, int y, Graphics g, int i) {
        contador++;
        g.setColor(colores.get(verticesColores.get(i).getColor()));
        g.drawOval(x, y, 20, 20);
        g.drawString(String.valueOf(contador), x + 3, y + 15);
    }

    public void anadirLinea(int x, int y, Graphics g) {
        String[] cooIn, cooFn;
        int xini = 0, yini = 0, xfin = 0, yfin = 0;
        try {
            for (int i = 0; i < l.getCoordVertices().size(); i++) {
                if (x == (i + 1)) {
                    cooIn = l.getCoordVertices().get(i).split(",");
                    xini = Integer.parseInt(cooIn[0]);
                    yini = Integer.parseInt(cooIn[1]);
                } else if (y == (i + 1)) {
                    cooFn = l.getCoordVertices().get(i).split(",");
                    xfin = Integer.parseInt(cooFn[0]);
                    yfin = Integer.parseInt(cooFn[1]);
                }
            }

            int xComp = xfin - xini;
            if (xComp > 30) {
                int yComp = yfin - yini;
                if (yComp > 30) {
                    yini += 20;
                } else if (yComp < -30) {
                    yfin += 20;
                } else {
                    yini += 5;
                    yfin += 5;
                }
                xini += 20;
            } else if (xComp < -30) {
                int yComp = yfin - yini;
                if (yComp > 30) {
                    yini += 20;
                } else if (yComp < -30) {
                    yfin += 20;
                } else {
                    yini += 5;
                    yfin += 5;
                }
                xfin += 20;
            } else {
                int yComp = yfin - yini;
                if (yComp > 30) {
                    yini += 20;
                } else if (yComp < -30) {
                    yfin += 20;
                } else {
                    yini += 5;
                    yfin += 5;
                }
                xini += 5;
                xfin += 5;
            }
            //g.drawLine(xfin, yfin, xfin, yfin);
            g.drawLine(xini, yini, xfin, yfin);

        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "No se encontro circulo");
        }
    }

    private void crearColores() {
        Color c1 = new Color(0, 14, 254);
        colores.add(c1);//Bien azul
        Color c2 = new Color(200, 0, 0);
        colores.add(c2);//Bien rojo
        Color c3 = new Color(46, 255, 0);
        colores.add(c3);//Bien verde
        Color c4 = new Color(255, 251, 0);
        colores.add(c4);//Bien amarillo
        Color c5 = new Color(0, 200, 172);
        colores.add(c5);//Bien celeste
        Color c6 = new Color(255, 0, 255);
        colores.add(c6);//Bien rosa
        Color c7 = new Color(123, 82, 19);
        colores.add(c7);//Bien cafe
        Color c8 = new Color(133, 0, 169);
        colores.add(c8);//Bien púrpura
        Color c9 = new Color(255, 135, 0);
        colores.add(c9);//Bien naranja
        Color c10 = new Color(73, 255, 175);
        colores.add(c10);//Bien aguamarina
        Color c11 = new Color(171, 255, 41);
        colores.add(c11);//Bien limon
        Color c12 = new Color(250, 128, 114);
        colores.add(c12);//Bien salmón
        Color c13 = new Color(255, 255, 255);
        colores.add(c13);//Bien blanco
        Color c14 = new Color(0, 0, 0);
        colores.add(c14);//Bien negro
        Color c15 = new Color(192, 192, 192);
        colores.add(c15);//Bien plata
        Color c16 = new Color(213, 206, 0);
        colores.add(c16);//Bien dorado

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            B_Adyacencia.setVisible(true);
            x = e.getX();
            y = e.getY();
            g = P_Grafo.getGraphics();
            anadirCirculo(x, y, g);
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
