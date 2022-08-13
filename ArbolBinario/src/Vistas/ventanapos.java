/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import arbolbin.dibujar;
import arbolbin.ArbolBinario;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ssrs_
 */
public class ventanapos extends JFrame implements ActionListener {

    Color negro = new Color(0, 0, 0);
    Color grisclaro = new Color(180, 180, 180);
    Color gris = new Color(100, 100, 100);
    Color grisosc = new Color(50, 50, 50);
    Color blanco = new Color(250, 250, 250);

    JPanel panel0;
    JPanel panel2;
    JLabel etiqueta1, etiqueta2;
    JTextField listaNumsPOS = new JTextField();
    JTextField listaNumslIN = new JTextField();
    JButton ok, dibujar, salir;
    ArbolBinario b = new ArbolBinario();
    String pos = "";
    char[] arrPOS, arrIN;

    public ventanapos() {
        setTitle("Reconstrucción posorden");
        setIconImage(new ImageIcon(ventana.class.getResource("/imagenes/icono.png")).getImage());
        setUndecorated(true);
        getRootPane().setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));
        this.getContentPane().setBackground(Color.DARK_GRAY);
        setLayout(null);
        setBounds(50, 50, 1300, 520);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        iniciarComponentes();
    }

    void iniciarComponentes() {
        panel0 = new JPanel();
        panel0.setLayout(null);
        panel0.setBounds(0, 0, 1300, 70);
        panel0.setBackground(grisclaro);
        this.getContentPane().add(panel0);

        panel2 = new JPanel();
        panel2.setBounds(0, 70, 1300, 450);
        this.getContentPane().add(panel2);
        panel2.setBackground(gris);
        panel2.setLayout(null);
        panel2.setVisible(false);

        etiqueta1 = new JLabel("Arreglo en posorden (LETRAS)");
        etiqueta1.setForeground(negro);
        etiqueta1.setFont(new Font("berlin sans fb demi", Font.BOLD, 16));
        etiqueta1.setBounds(20, 10, 350, 20);
        panel0.add(etiqueta1);

        etiqueta1 = new JLabel("Arreglo en inorden (LETRAS)");
        etiqueta1.setForeground(negro);
        etiqueta1.setFont(new Font("berlin sans fb demi", Font.BOLD, 16));
        etiqueta1.setBounds(20, 30, 350, 20);
        panel0.add(etiqueta1);

        panel0.add(listaNumsPOS);
        listaNumsPOS.setBounds(400, 10, 300, 20);

        panel0.add(listaNumslIN);
        listaNumslIN.setBounds(400, 30, 300, 20);

        ok = new JButton("OK");
        ok.setFont(new Font("berlin sans fb demi", Font.BOLD, 20));
        ok.setBounds(750, 20, 150, 30);
        panel0.add(ok);
        ok.addActionListener(this);

        dibujar = new JButton("Dibujar");
        dibujar.setFont(new Font("berlin sans fb demi", Font.BOLD, 20));
        dibujar.setBounds(930, 20, 150, 30);
        panel0.add(dibujar).setVisible(false);
        dibujar.addActionListener(this);

        salir = new JButton("<-");
        salir.setFont(new Font("berlin sans fb demi", Font.BOLD, 14));
        salir.setBounds(panel0.getWidth()-80, 10, 50, 30);
        panel0.add(salir);
        salir.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ok) {
            try {
                String totalText = listaNumsPOS.getText();
                String totalTextl = listaNumslIN.getText();

                String[] textElements = totalText.split("");
                String[] textElementsIN = totalTextl.split("");

                arrPOS = new char[textElements.length];
                arrIN = new char[textElementsIN.length];

                for (int i = 0; i < textElements.length; i++) {
                    arrPOS[i] = textElements[i].charAt(0);
                }
                for (int i = 0; i < textElementsIN.length; i++) {
                    arrIN[i] = textElementsIN[i].charAt(0);
                }
                dibujar.setVisible(true);
                panel2.setVisible(true);
            } catch (Error r) {
                System.out.println("Error: "+r);
            }
        }
        if (e.getSource() == dibujar) {
            System.out.print("posorden: ");
            for (int i = 0; i < arrPOS.length; i++) {
                System.out.print(arrPOS[i] + " ");
            }
            System.out.println("");
            System.out.print("inorden: ");
            for (int i = 0; i < arrIN.length; i++) {
                System.out.print(arrIN[i] + " ");
            }
            b.initst();
            Graphics g;
            g = panel2.getGraphics();
            b.insertarArbolpos(arrPOS, arrIN, 20, panel2.getWidth()/2, 80, g);

        }
        if (e.getSource() == salir) {
            b.initst();
            b.inicializarAr();
            dispose();
        }
    }

    public boolean isLetter(char caracter) {
        if (Character.isLetter(caracter)) {
            return true;
        } else {
            return false;
        }

    }
}
