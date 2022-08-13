/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import logica.Arbol;
import logica.CalculoMin;

/**
 *
 * @author ssrs_
 */
public class Ingreso extends JFrame implements ActionListener {

    Color negro = new Color(0, 0, 0);
    Color grisclaro = new Color(180, 180, 180);
    Color gris = new Color(100, 100, 100);
    Color grisosc = new Color(80, 80, 80);

    JPanel PTitulo, PMatriz;
    JScrollPane scrollMat = new JScrollPane(PMatriz, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    JLabel titulolbl, letraslbl;
    JButton ingresobtn;
    JLabel coordxlbl;
    JTextField[][] leer;

    String[][] matriz;

    public Ingreso() {
        setTitle("Lista Doble");
        setUndecorated(true);
        setIconImage(new ImageIcon(Ingreso.class.getResource("/imagenes/icono.png")).getImage());
        getRootPane().setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));
        this.getContentPane().setBackground(Color.DARK_GRAY);
        setLayout(null);
        setBounds(200, 200, 880, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        requestFocus();
        getRootPane().setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));
        getContentPane().setLayout(null);
        setBackground(Color.BLACK);
        setForeground(Color.BLACK);
        iniciarComponentes();
    }

    void iniciarComponentes() {
        PanelTitulo();
        PanelMatriz();
    }

    private void PanelTitulo() {
        PTitulo = new JPanel();
        PTitulo.setBackground(grisosc);
        PTitulo.setForeground(grisosc);
        PTitulo.setBounds(0, 0, 1000, 100);
        PTitulo.setLayout(null);
        add(PTitulo).setVisible(true);
        componentesTitulo();
    }

    private void componentesTitulo() {
        titulolbl = new JLabel();
        titulolbl.setText("<html><left> Camino más corto de la raiz a las hojas de un arbol n-ario <br> Ingresar hijos y su valor en cada nodo ej: A [c,2]<html>");
        titulolbl.setFont(new Font("Times new roman", Font.BOLD, 25));
        titulolbl.setBounds(20, 10, 980, 90);
        titulolbl.setForeground(negro);
        PTitulo.add(titulolbl).setVisible(true);

        ingresobtn = new JButton("<html><center> Ingresar <html>");
        ingresobtn.setFont(new Font("Times new roman", Font.BOLD, 15));
        ingresobtn.setBounds(640, 60, 100, 30);
        ingresobtn.addActionListener(this);
        PTitulo.add(ingresobtn).setVisible(true);
    }

    private void PanelMatriz() {
        scrollMat.setBounds(0, 100, 875, 650);
        scrollMat.getHorizontalScrollBar().setCursor(new Cursor(Cursor.HAND_CURSOR));
        scrollMat.getHorizontalScrollBar().setBackground(Color.BLACK);
        scrollMat.getVerticalScrollBar().setCursor(new Cursor(Cursor.HAND_CURSOR));
        scrollMat.getVerticalScrollBar().setBackground(Color.BLACK);

        PMatriz = new JPanel();
        PMatriz.setBackground(gris);
        PMatriz.setForeground(gris);
        PMatriz.setLayout(null);
        scrollMat.setViewportView(PMatriz);
        scrollMat.setVisible(true);
        PMatriz.setPreferredSize(new Dimension(0, (25 * 40)));
        add(scrollMat).setVisible(true);
        componentesMatriz();
    }

    private void componentesMatriz() {
        matriz = new String[26][10];
        leer = new JTextField[10][26];

        for (int y = 0; y < 26; y++) {
            for (int x = 0; x < 10; x++) {
                leer[x][y] = new JTextField();
                PMatriz.add(leer[x][y]);
                leer[x][y].setBounds(-60 + (82 * (x + 1)), (30 * (y + 1)), 50, 20);
                leer[x][y].setText("");
                leer[x][y].setBackground(grisclaro);
            }
        }
        char letra = 'A';
        for (int i = 0; i < 26; i++) {
            coordxlbl = new JLabel();
            PMatriz.add(coordxlbl);
            coordxlbl.setForeground(negro);
            coordxlbl.setText(Character.toString(letra));
            coordxlbl.setBounds(5, (30 * (i + 1)), 50, 20);
            letra++;
        }
    }

    public void guardarMatriz() {

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 10; j++) {
                matriz[i][j] = (leer[j][i].getText());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ingresobtn) {
            guardarMatriz();
            boolean condatos = false;
            for (int i = 0; i < matriz[0].length; i++) {
                if (!"".equals(matriz[0][i])) {
                    condatos = true;
                }
            }
            if (condatos) {
                CalculoMin c = new CalculoMin(matriz);
                c.reinicio();
                String[] datos = c.datosValores().split(",");
                if (c.isRetorno()) {
                    ArrayList<String> valores = c.getValores();
                    Arbol arb = new Arbol(valores);
                    arb.insertarDatos(matriz);
                    Lienzo v = new Lienzo(arb.getRaiz(), datos);
                    v.setVisible(true);
                    JOptionPane.showMessageDialog(null, "El camino mas corto de una hoja a la raiz es: " + datos[0] + "," + datos[2]);
                } else {
                    JOptionPane.showMessageDialog(null, c.datosValores());
                }
            } else {
                JOptionPane.showMessageDialog(null, "La raiz debe ser A");
            }

        }
    }
}
