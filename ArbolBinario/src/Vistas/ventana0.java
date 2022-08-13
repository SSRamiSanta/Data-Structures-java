/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Vistas.ventana;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ssrs_
 */
public class ventana0 extends javax.swing.JFrame implements ActionListener {

    JPanel panel;
    JLabel recons;
    JButton arbolnormal, arbolpre, arbolpos;

    public ventana0() {
        setLayout(null);
        setIconImage(new ImageIcon(ventana.class.getResource("/imagenes/icono.png")).getImage());
        setUndecorated(true);
        getRootPane().setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));
        this.getContentPane().setBackground(Color.DARK_GRAY);
        setBounds(500, 200, 300, 200);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        iniciarComponents();
    }

    void iniciarComponents() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 300, 400);
        panel.setBackground(Color.DARK_GRAY);
        this.getContentPane().add(panel);

        arbolnormal = new JButton("Arbol Binario");
        arbolnormal.setFont(new Font("berlin sans fb demi", Font.BOLD, 14));
        arbolnormal.setBounds(75, 30, 150, 20);
        panel.add(arbolnormal);

        recons = new JLabel("Reconstrucción:");
        recons.setForeground(Color.BLACK);
        recons.setFont(new Font("berlin sans fb demi", Font.BOLD, 20));
        recons.setBounds(75, 70, 150, 40);
        panel.add(recons);

        arbolpre = new JButton("Preorden");
        arbolpre.setFont(new Font("berlin sans fb demi", Font.BOLD, 14));
        arbolpre.setBounds(75, 120, 150, 20);
        panel.add(arbolpre);

        arbolpos = new JButton("Posorden");
        arbolpos.setFont(new Font("berlin sans fb demi", Font.BOLD, 14));
        arbolpos.setBounds(75, 160, 150, 20);
        panel.add(arbolpos);

        arbolnormal.addActionListener(this);
        arbolpre.addActionListener(this);
        arbolpos.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == arbolnormal) {
            ventana ventana = new ventana();
            ventana.setVisible(true);
        }
        if (e.getSource() == arbolpre) {
            ventanapre pre = new ventanapre();
            pre.setVisible(true);
        }
        if (e.getSource() == arbolpos) {
            ventanapos pos = new ventanapos();
            pos.setVisible(true);

        }
    }
}
//Pos:      kjgfcbheida
//Pre:      abjkcfgdehi
//In:       jkbfgcaehdi
