/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import djikstra.prim.CrearGrafo;
import djikstra.prim.Logica;
import djikstra.prim.Vertices;
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

    private JPanel P_Titulo, P_Ingreso, P_Grafo, P_Adyacencia, P_Salida;
    private JLabel L_Titulo, L_Dibujo, L_Adyacencia, L_Origen, coordenadas;
    private JButton B_Dijkstra, B_Prim, B_Reinicio, B_Adyacencia, B_Grafo, B_Limpiar, B_Cerrar;
    Color black, graywhite, gray, grayheavy, grayblack, white;
    int x, y, contador, verxs;
    boolean vez;
    private Logica l;
    private ArrayList Vertices;
    private ArrayList<Vertices> v;
    private CrearGrafo c;
    private Graphics g;
    private int[] data;
    private int[][] matrizAd, weight;
    private JTextField leer[][];
    private ArrayList<String> Aristas, Ar;

    JScrollPane scrollMat = new JScrollPane(P_Adyacencia, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    public Ventana() {
        setLayout(null);
        setIconImage(new ImageIcon(Ventana.class.getResource("/imagenes/icono.png")).getImage());
        setUndecorated(true);
        getRootPane().setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));
        this.getContentPane().setBackground(Color.DARK_GRAY);
        setBounds(30, 30, 1350, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(Color.darkGray);
        getRootPane().setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));
        l = new Logica();
        v = new ArrayList<Vertices>();
        Vertices = new ArrayList();
        vez = true;
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
        P_Grafo.setBounds(0, 55, 600, 480);
        P_Grafo.setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));
        P_Grafo.setBackground(white);
        P_Grafo.addMouseListener(this);
        P_Grafo.addMouseMotionListener(this);
        P_Grafo.setVisible(true);

        P_Ingreso = new JPanel();
        P_Ingreso.setLayout(null);
        P_Ingreso.setBounds(0, 50, 600, 700);
        P_Ingreso.setBackground(gray);
        this.getContentPane().add(P_Ingreso);
        P_Ingreso.add(P_Grafo);

        P_Salida = new JPanel();
        P_Salida.setBounds(600, 400, 750, 350);
        P_Salida.setBackground(Color.darkGray);
        add(P_Salida).setVisible(true);

    }

    private void Etiquetas() {
        L_Titulo = new JLabel("Dijkstra & Prim");
        L_Titulo.setForeground(white);
        L_Titulo.setFont(new Font("Courier new", Font.BOLD, 25));
        L_Titulo.setBounds(30, 15, 400, 20);
        P_Titulo.add(L_Titulo);

        L_Dibujo = new JLabel("<html><left>Dibujar grafo:<html>");
        L_Dibujo.setForeground(black);
        L_Dibujo.setFont(new Font("Courier new", Font.BOLD, 20));
        L_Dibujo.setBounds(30, 10, 400, 40);
        P_Ingreso.add(L_Dibujo);

        L_Origen = new JLabel("");
        L_Origen.setForeground(black);
        L_Origen.setFont(new Font("Courier new", Font.BOLD, 20));
        L_Origen.setBounds(50, 550, 200, 40);
        P_Ingreso.add(L_Origen).setVisible(false);

    }

    private void Botones() {
        B_Dijkstra = new JButton("<html><center> Dijkstra <html>");
        B_Dijkstra.setFont(new Font("Times new roman", Font.BOLD, 15));
        B_Dijkstra.setBounds(50, 600, 200, 50);
        B_Dijkstra.addActionListener(this);
        P_Ingreso.add(B_Dijkstra).setVisible(false);

        B_Prim = new JButton("<html><center> Prim <html>");
        B_Prim.setFont(new Font("Times new roman", Font.BOLD, 15));
        B_Prim.setBounds(300, 600, 200, 50);
        B_Prim.addActionListener(this);
        P_Ingreso.add(B_Prim).setVisible(false);

        B_Reinicio = new JButton("<html><center> Reiniciar <html>");
        B_Reinicio.setFont(new Font("Times new roman", Font.BOLD, 15));
        B_Reinicio.setBounds(1100, 10, 100, 30);
        B_Reinicio.addActionListener(this);
        P_Titulo.add(B_Reinicio).setVisible(false);

        B_Adyacencia = new JButton("<html><center>Lista adyacencia<html>");
        B_Adyacencia.setFont(new Font("Consolas", Font.BOLD, 15));
        B_Adyacencia.setBounds(400, 5, 150, 40);
        P_Ingreso.add(B_Adyacencia).setVisible(false);
        B_Adyacencia.addActionListener(this);

        B_Cerrar = new JButton("<html><center>X<html>");
        B_Cerrar.setFont(new Font("Consolas", Font.BOLD, 30));
        B_Cerrar.setBounds(P_Titulo.getWidth()-70, 10, 40, 30);
        P_Titulo.add(B_Cerrar).setVisible(true);
        B_Cerrar.addActionListener(this);

        B_Limpiar = new JButton("<html><center>Limpiar<html>");
        B_Limpiar.setFont(new Font("Consolas", Font.BOLD, 15));
        B_Limpiar.setBounds(280, 550, 100, 30);
        P_Ingreso.add(B_Limpiar).setVisible(false);
        B_Limpiar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == B_Dijkstra) {
            System.out.println("Calculando Dijkstra...");
            l.setVertices(v);
            //B_Reinicio.setVisible(true);
            B_Limpiar.setVisible(true);
            matrizAdy();
            l.Dijkstra(0);
            Dibujar d = new Dibujar();
            g = P_Salida.getGraphics();
            d.graficarDijkstra(l.getVertices(), l.getMinimos(), l.getMatrizDeAdyacencia(), l.getPadres(), l, g);
        }
        if (e.getSource() == B_Prim) {
            System.out.println("Calculando Prim...");
            g = P_Salida.getGraphics();
            //B_Reinicio.setVisible(true);
            B_Limpiar.setVisible(true);
            llenarData();
            int f = v.size();
            int h = v.size();
            llenarWeight(f, h);
            verxs = data.length;
            c = new CrearGrafo(verxs, data, weight);
            l.Prim(c, 0);
            ArrayList<String> datosPrim = l.getDatosPrim();
            DibujarPrim(g, datosPrim);
            vez = false;
        }
        if (e.getSource() == B_Reinicio) {
            System.out.println("Reinciando...");
            setDatos();
            P_Ingreso.repaint();
            P_Salida.repaint();
            P_Adyacencia.setVisible(false);
            B_Limpiar.setVisible(false);
            B_Dijkstra.setVisible(false);
            B_Prim.setVisible(false);
            L_Origen.setVisible(false);
            matrizAd = null;
            leer = null;
        }
        if (e.getSource() == B_Adyacencia) {
            System.out.println("Adyacencia...");
            metodoMatriz();
            //B_Reinicio.setVisible(true);
        }
        if (e.getSource() == B_Grafo) {
            System.out.println("Creando grafo...");
            int f = v.size();
            int c = v.size();
            guardarMatriz(f, c);
            imprimirMatriz();
            Aristas = new ArrayList<>();
            Ar = new ArrayList<>();
            for (Object Arista : Aristas) {
                Ar.add((String) Arista);
            }
            Ar = Aristas;
            llenarAdyacencia();
            imprimirAdyacencia();
            dibujarLineas();
            B_Dijkstra.setVisible(true);
            B_Prim.setVisible(true);
            L_Origen.setVisible(true);
        }
        if (e.getSource() == B_Limpiar) {
            P_Salida.repaint();
            Aristas = Ar;
            Aristas = new ArrayList<>();
            for (Object Arista : Ar) {
                Aristas.add((String) Arista);
            }
            data = null;
            weight = null;
            verxs = 0;
        }
        if (e.getSource() == B_Cerrar) {
            System.exit(0);
        }
    }

    private void DibujarPrim(Graphics g, ArrayList<String> datosPrim) {
        int x, y, valor, minimo = 0;
        contador = 0;
        System.out.println("Tamaño v: " + v.size());
        for (int i = 0; i < v.size(); i++) {
            String[] coordenadas = l.getCoordVertices().get(i).split(",");
            x = Integer.parseInt(coordenadas[0]);
            y = Integer.parseInt(coordenadas[1]);
            anadirCirculo2(x, y, g);
        }
        //Valor,xini,yini,xfin,yfin
        g.setColor(black);
        for (int i = 0; i < datosPrim.size(); i++) {
            String[] datos = datosPrim.get(i).split(",");
            x = Integer.parseInt(datos[0]);
            y = Integer.parseInt(datos[1]);
            valor = Integer.parseInt(datos[2]);
            minimo += valor;
            anadirLinea(x, y, valor, g);
        }
        JOptionPane.showMessageDialog(null, "Recubrimiento mínimo " + minimo, "Recorrido Prim", JOptionPane.INFORMATION_MESSAGE);
    }

    public void metodoMatriz() {
        int f, c;

        f = v.size();
        c = v.size();

        scrollMat.setBounds(600, 50, 750, 350);
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

        add(scrollMat);

        P_Adyacencia.setVisible(true);

        L_Adyacencia = new JLabel("Matriz de adyacencia");
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
                leer[x][y].setBounds(-50 + (82 * (x + 1)), 40 + (30 * (y + 1)), 50, 20);
                if (x == y) {
                    leer[x][y].setText("0");
                    leer[x][y].setEditable(false);
                } else if (x < y) {
                    leer[x][y].setText("x");
                    leer[x][y].setEditable(false);
                } else {
                    leer[x][y].setText("99");
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
                try {
                    int peso = Integer.parseInt(leer[j][i].getText());
                    aux[i][j] = peso;
                    aux[j][i] = peso;
                } catch (NumberFormatException e) {

                }

            }
        }

        l.setMatrizDeAdyacencia(aux);
    }

    public boolean guardarMatriz(int f, int c) {
        for (int i = 0; i < f; i++) {
            for (int j = 0; j < c; j++) {
                if (leer[j][i].getText().equals("0") || leer[j][i].getText().equals("x")) {
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

    private void llenarAdyacencia() {
        int nVertices = l.getVertices().size();
        ArrayList<Integer>[] Ady = new ArrayList[nVertices];
        for (int i = 0; i < nVertices; i++) {
            Ady[i] = new ArrayList();
            for (int j = 0; j < nVertices; j++) {
                if (matrizAd[i][j] != 0) {
                    Ady[i].add(matrizAd[i][j]);
                }
            }
        }
        l.setAdyacencia(Ady);
    }

    private void imprimirMatriz() {
        System.out.println("MATRIZ:");
        for (int i = 0; i < matrizAd[0].length; i++) {
            for (int j = 0; j < matrizAd.length; j++) {
                System.out.print(matrizAd[i][j] + " ");
            }
            System.out.println("");
        }
    }

    private void imprimirAdyacencia() {
        for (int i = 0; i < l.getVertices().size(); i++) {
            System.out.print((i + 1) + "-> ");
            int lim = l.getAdyacencia()[i].size();
            for (int j = 0; j < lim; j++) {
                System.out.print("[" + l.getAdyacencia()[i].get(j) + "]");
            }
            System.out.println("");
        }
    }

    private void dibujarLineas() {
        g = P_Grafo.getGraphics();
        for (int i = 0; i < matrizAd[0].length; i++) {
            for (int j = 0; j < matrizAd.length; j++) {
                if (matrizAd[i][j] != 0 && matrizAd[i][j] != 99) {
                    anadirLinea(i + 1, j + 1, matrizAd[i][j], g);
                }
            }
        }
    }

    public void setDatos() {
        contador = 0;
        x = 0;
        y = 0;
        l.setDatos();
        v = new ArrayList<Vertices>();
        data = null;
        weight = null;
        verxs = 0;
    }

    public void llenarData() {
        System.out.println("Data:");
        data = new int[Vertices.size()];
        for (int i = 0; i < Vertices.size(); i++) {
            data[i] = Integer.parseInt(String.valueOf(Vertices.get(i)));
            System.out.print(data[i] + " ");
        }
        System.out.println("");
    }

    public void llenarWeight(int f, int c) {
        System.out.println("WEIGHT:");
        weight = new int[f][c];
        for (int i = 0; i < f; i++) {
            for (int j = 0; j < c; j++) {
                if (leer[j][i].getText().equals("0")) {
                    weight[i][j] = 99;
                }
                if (leer[j][i].getText().equals("x")) {
                    weight[i][j] = Integer.parseInt(leer[i][j].getText());

                } else {
                    weight[i][j] = Integer.parseInt(leer[j][i].getText());
                }
                System.out.print(weight[i][j] + " ");
            }
            System.out.println("");
        }
        l.setAristas(Aristas);
    }

    public void anadirCirculo(int x, int y, Graphics g) {
        contador++;
        g.setColor(Color.red);
        g.drawOval(x, y, 20, 20);
        g.drawString(String.valueOf(contador), x + 3, y + 15);
        l.getCoordVertices().add(x + "," + y);
        Vertices.add(contador);
        v.add(new Vertices(contador, x, y));
    }

    public void anadirCirculo2(int x, int y, Graphics g) {
        contador++;
        g.setColor(Color.red);
        g.drawOval(x, y, 20, 20);
        g.drawString(String.valueOf(contador), x + 3, y + 15);
    }

    public void anadirLinea(int x, int y, int valor, Graphics g) {
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
            g.drawString(valor + "", (xini + xfin) / 2, (yini + yfin) / 2);
            //g.drawLine(xfin, yfin, xfin, yfin);
            g.drawLine(xini, yini, xfin, yfin);
            String datos = x + "," + y + "," + valor + "," + xini + "," + xfin + "," + yini + "," + yfin;
            if (vez) {
                Aristas.add(datos);
            }

        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "No se encontro circulo");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            B_Adyacencia.setVisible(true);
            System.out.println("Evento circulo");
            x = e.getX();
            System.out.println("x es: " + x);
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
