/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complejidad;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ssrs_
 */
public class Complejidad extends JFrame implements ActionListener {

    Color black, graywhite, gray, grayheavy, grayblack, white;
    Font fuente = new Font("TimesRoman", Font.BOLD, 20);
    int n_totales, n = 100, y0 = 650, x0 = 85, factor;
    double y_in = 650, x_in = 80, x_fin = 80, y_fin = 650, operaciones;

    Burbuja burbuja = new Burbuja();
    Insercion insercion = new Insercion();
    Seleccion seleccion = new Seleccion();
    RadixSort radixSort = new RadixSort();
    QuickSort quickSort = new QuickSort();

    JPanel P_Titulo, P_Grafica;
    JLabel texto = new JLabel(), L_Titulo;
    JLabel texto_oe, texto_tam_array;
    JButton boton_graficar = new JButton("Graficar"), B_Cerrar = new JButton("<html><center>X</html>");

    public Complejidad() {
        Colores();

        setTitle("Complejidad");
        setUndecorated(true);
        setLayout(null);
        setIconImage(new ImageIcon(Complejidad.class.getResource("/imagenes/icono.png")).getImage());
        getRootPane().setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));
        this.getContentPane().setBackground(black);
        setSize(620, 730);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        requestFocus();

        P_Titulo = new JPanel();
        P_Titulo.setLayout(null);
        P_Titulo.setBounds(0, 0, 620, 50);
        P_Titulo.setBackground(black);
        add(P_Titulo);

        P_Grafica = new JPanel();
        P_Grafica.setLayout(null);
        P_Grafica.setBounds(0, -15, 620, 700);
        P_Grafica.setBackground(grayblack);
        add(P_Grafica);

        boton_graficar.setBackground(gray);
        boton_graficar.setForeground(white);
        boton_graficar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton_graficar.setBounds(360, 20, 100, 20);
        boton_graficar.addActionListener(this);
        P_Titulo.add(boton_graficar);

        B_Cerrar.setBackground(gray);
        B_Cerrar.setForeground(white);
        B_Cerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        B_Cerrar.setBounds(P_Titulo.getWidth() - 60, 5, 40, 20);
        B_Cerrar.addActionListener(this);
        P_Titulo.add(B_Cerrar);

        L_Titulo = new JLabel("Complejidad");
        L_Titulo.setForeground(white);
        L_Titulo.setFont(new Font("Courier new", Font.BOLD, 25));
        L_Titulo.setBounds(30, 15, 400, 25);
        P_Titulo.add(L_Titulo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boton_graficar) {

            texto_oe = new JLabel("<html><div style='text-align: center;'>O<p>p<p>e<p>r<p>a<p>c<p>i<p>o<p>n<p>e<p>s<p><p>E<p>l<p>e<p>m<p>e<p>n<p>t<p>a<p>l<p>e<p>s</div><html>");
            P_Grafica.add(texto_oe).setVisible(true);
            texto_oe.setForeground(white);
            texto_oe.setBounds(10, 150, 10, 400);

            texto_tam_array = new JLabel("<html><center>Tamaño Arreglo</html>");
            P_Grafica.add(texto_tam_array);
            texto_tam_array.setForeground(white);
            texto_tam_array.setBounds(240, 670, 100, 20);

            n_totales = 500;
            n = n_totales;
            factor = n * 6;
            System.out.println(factor);

            Graphics g;
            g = P_Grafica.getGraphics();
            g.setColor(grayblack);
            g.fillRect(0, 70, 620, 700);
            g.setColor(gray);

            for (int i = 0; i <= 500; i++) {
                if (i % 100 == 0) {
                    String s = Integer.toString(i);
                    g.drawString(s, i + x0, 665);
                }
            }

            for (int i = 0; i <= 1500000; i++) {
                if (i % 100000 == 0) {
                    String s = Integer.toString(i);
                    g.drawString(s, 30, (y0 + 4 - i / factor));
                    g.drawString("_", x0 - 3, (y0 - 1 - i / factor));
                }
            }

            g.drawLine(x0, y0, x0 + 500, y0);
            g.drawLine(x0, y0, x0, y0 - 500);

            ///////////////////////////////Burbuja////////////////////////////////////////////
            g.setColor(Color.CYAN);
            y_fin = y0;
            x_fin = x0;
            for (int i = 1; i <= n; i++) {//burbuja

                x_in = x_fin;
                y_in = y_fin;
                x_fin = i + x0;
                operaciones = burbuja.complejidad(i, n_totales);
                y_fin = y0 - (operaciones / factor);
                g.drawLine((int) x_in, (int) y_in, (int) x_fin, (int) y_fin);

            }
            g.drawString("Burbuja", (int) x_fin - 50, (int) y_fin);
            g.drawString(Integer.toString((int) operaciones) + "--", 30, (int) y_in + 4);
            System.out.println("Burbuja: " + operaciones);

            //////////////////////////////////Seleccion///////////////////////////////////////
            g.setColor(Color.GREEN);
            y_fin = y0;
            x_fin = x0;
            for (int i = 1; i <= n; i++) {//seleccion

                x_in = x_fin;
                y_in = y_fin;
                x_fin = i + x0;
                operaciones = seleccion.complejidad(i, n_totales);
                y_fin = y0 - (operaciones / factor);
                g.drawLine((int) x_in, (int) y_in, (int) x_fin, (int) y_fin);

            }
            g.drawString("Seleccion", (int) x_fin - 50, (int) y_fin);
            g.drawString(Integer.toString((int) operaciones) + "--", 30, (int) y_fin + 4);
            System.out.println("Seleccion: " + operaciones);

            //////////////////////////////////Insercion///////////////////////////////////////
            g.setColor(Color.MAGENTA);
            y_fin = y0;
            x_fin = x0;

            for (int i = 1; i <= n; i++) {//Insercion

                x_in = x_fin;
                y_in = y_fin;
                x_fin = i + x0;
                operaciones = insercion.complejidad(i, n_totales);
                y_fin = y0 - (operaciones / factor);
                g.drawLine((int) x_in, (int) y_in, (int) x_fin, (int) y_fin);

            }
            g.drawString("Insercion", (int) x_fin - 50, (int) y_fin);
            g.drawString(Integer.toString((int) operaciones) + "--", 30, (int) y_fin + 4);
            System.out.println("Insercion: " + operaciones);
            ///////////////////////////////////Radix////////////////////////////////////////
            g.setColor(Color.ORANGE);
            y_fin = y0;
            x_fin = x0;

            for (int i = 1; i <= n; i++) {//Radix

                x_in = x_fin;
                y_in = y_fin;
                x_fin = i + x0;
                operaciones = radixSort.complejidad(i, n_totales);
                y_fin = y0 - (operaciones / factor);
                g.drawLine((int) x_in, (int) y_in, (int) x_fin, (int) y_fin);

            }
            g.drawString("RadixSort", (int) x_fin - 50, (int) y_fin);
            g.drawString(Integer.toString((int) operaciones) + "--", 30, (int) y_fin + 4);
            System.out.println("RadixSort: " + operaciones);
            ///////////////////////////////////Quick////////////////////////////////////////
            g.setColor(Color.WHITE);
            y_fin = y0;
            x_fin = x0;

            for (int i = 1; i <= n; i++) {//Quick

                x_in = x_fin;
                y_in = y_fin;
                x_fin = i + x0;
                quickSort.crearMatriz(n);
                operaciones = quickSort.complejidad(i, n_totales);
                y_fin = y0 - (operaciones / factor);
                g.drawLine((int) x_in, (int) y_in, (int) x_fin, (int) y_fin);

            }

            g.drawString("QuickSort", (int) x_fin - 50, (int) y_fin);
            g.drawString(Integer.toString((int) operaciones) + "--", 30, (int) y_fin + 4);
            System.out.println("QuickSort: " + operaciones);

        }
        if (e.getSource() == B_Cerrar) {
            System.exit(0);
        }
    }

    private void Colores() {
        black = new Color(0, 0, 0);
        graywhite = new Color(180, 180, 180);
        gray = new Color(100, 100, 100);
        grayheavy = new Color(50, 50, 50);
        grayblack = new Color(25, 25, 25);
        white = new Color(250, 250, 250);
    }
}
