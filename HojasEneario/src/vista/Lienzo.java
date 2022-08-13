/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Color;
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
import logica.Arbol.Nodo;

/**
 *
 * @author ssrs_
 */
public class Lienzo extends JFrame implements ActionListener {

    Color negro = new Color(0, 0, 0);
    Color grisclaro = new Color(180, 180, 180);
    Color gris = new Color(100, 100, 100);
    Color grisosc = new Color(80, 80, 80);
    Color grismuyosc = new Color(20, 20, 20);

    Dibujo PLienzo;
    JPanel PVolver;
    JLabel titulolbl;
    JButton volverbtn;

    Nodo raiz;
    String[] datos;

    public Lienzo(Nodo raiz, String[] datos) {
        this.raiz = raiz;
        this.datos = datos;
        setTitle("Lista Doble");
        setIconImage(new ImageIcon(Lienzo.class.getResource("/imagenes/icono.png")).getImage());
        setUndecorated(true);
        getRootPane().setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));
        this.getContentPane().setBackground(Color.DARK_GRAY);
        setLayout(null);
        setBounds(30, 30, 1350, 750);
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
        PLienzo = new Dibujo();
        PLienzo.setBackground(grismuyosc);
        PLienzo.setBounds(0, 0, 1350, 750);
        PLienzo.setLayout(null);
        add(PLienzo).setVisible(true);

        titulolbl = new JLabel();
        titulolbl.setText("");
        titulolbl.setFont(new Font("Times new roman", Font.BOLD, 25));
        titulolbl.setBounds(20, 10, 980, 90);
        titulolbl.setForeground(negro);
        PLienzo.add(titulolbl).setVisible(true);

        PVolver = new JPanel();
        PVolver.setBackground(gris);
        PVolver.setBounds(1290, 0, 60, 30);
        PVolver.setLayout(null);
        PLienzo.add(PVolver).setVisible(true);

        volverbtn = new JButton(" << ");
        volverbtn.setFont(new Font("Times new roman", Font.BOLD, 15));
        volverbtn.setBounds(0, 0, 60, 30);
        volverbtn.addActionListener(this);
        PVolver.add(volverbtn).setVisible(true);

        PLienzo.recibir(raiz, datos);
        PLienzo.repaint();

    }

    public void recibir(Nodo raiz, String[] datos) {
        this.raiz = raiz;
        this.datos = datos;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == volverbtn) {
            PLienzo.removeAll();
            dispose();
        }
    }
}
