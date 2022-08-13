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
import static java.awt.image.ImageObserver.WIDTH;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

//NO TOCAR ESTA CLASE
public class ventana extends JFrame {

    Color negro = new Color(0, 0, 0);
    Color grisclaro = new Color(180, 180, 180);
    Color gris = new Color(100, 100, 100);
    Color grisosc = new Color(50, 50, 50);
    Color blanco = new Color(250, 250, 250);

    JPanel panel0, panel;
    JPanel panel2;
    ArbolBinario b = new ArbolBinario();

    JLabel etiqueta0, etiqueta1, etiqueta2, etiqueta3, etiqueta4, etiquetault;

    JButton insertar, retirar, dibujar, existe, insertarlista, salir;

    JTextField listaNums = new JTextField();
    JTextField numerosI = new JTextField();
    JTextField numerosE = new JTextField();
    JTextField numeroEx = new JTextField();
    dibujar d = new dibujar();

    public ventana() {
        setTitle("Arbol Binario");
        setIconImage(new ImageIcon(ventana.class.getResource("/imagenes/icono.png")).getImage());
        setUndecorated(true);
        getRootPane().setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));
        this.getContentPane().setBackground(Color.DARK_GRAY);
        setLayout(null);
        setBounds(20, 20, 1300, 700);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        componentes();
    }

    private void componentes() {
        Paneles();
        etiquetas();
        botones();
        cajasDeTexto();
    }

    private void Paneles() {
        panel0 = new JPanel();
        panel0.setLayout(null);
        panel0.setBounds(0, 0, 1300, 50);
        panel0.setBackground(grisclaro);
        this.getContentPane().add(panel0);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 50, 1300, 250);
        panel.setBackground(grisosc);
        this.getContentPane().add(panel);
        panel.setVisible(false);

        panel2 = new JPanel();
        panel2.setBounds(0, 300, 1300, 400);
        this.getContentPane().add(panel2);
        panel2.setBackground(gris);
        panel2.setLayout(null);
        panel2.setVisible(false);

    }

    private void etiquetas() {
        etiqueta1 = new JLabel("Ingresar arbol: ");
        etiqueta1.setForeground(Color.BLACK);
        etiqueta1.setFont(new Font("berlin sans fb demi", Font.BOLD, 20));
        etiqueta1.setBounds(5, 10, 200, 20);
        panel0.add(etiqueta1);

        etiqueta2 = new JLabel("Ingresar número al arbol:");
        etiqueta2.setForeground(Color.WHITE);
        etiqueta2.setFont(new Font("berlin sans fb demi", Font.BOLD, 15));
        etiqueta2.setBounds(20, 30, 200, 20);
        panel.add(etiqueta2);

        etiqueta3 = new JLabel("Retirar número del arbol:");
        etiqueta3.setForeground(Color.WHITE);
        etiqueta3.setFont(new Font("berlin sans fb demi", Font.BOLD, 15));
        etiqueta3.setBounds(20, 70, 200, 20);
        panel.add(etiqueta3);

        etiqueta0 = new JLabel("Existe el numero:");
        etiqueta0.setForeground(Color.WHITE);
        etiqueta0.setFont(new Font("berlin sans fb demi", Font.BOLD, 15));
        etiqueta0.setBounds(20, 100, 200, 20);
        panel.add(etiqueta0);

        etiquetault = new JLabel("");
        etiquetault.setForeground(Color.WHITE);
        etiquetault.setFont(new Font("berlin sans fb demi", Font.BOLD, 15));
        etiquetault.setBounds(500, 10, 400, 200);
        panel.add(etiquetault);

    }

    private void cajasDeTexto() {
        panel.add(numerosI);
        numerosI.setBounds(230, 30, 30, 20);

        panel.add(numerosE);
        numerosE.setBounds(230, 60, 30, 20);

        panel.add(numeroEx);
        numeroEx.setBounds(230, 100, 30, 20);

        panel0.add(listaNums);
        listaNums.setBounds(300, 10, 500, 20);

    }

    private void botones() {
        insertarlista = new JButton("OK");
        insertarlista.setFont(new Font("berlin sans fb demi", Font.BOLD, 14));
        insertarlista.setBounds(850, 10, 150, 20);
        panel0.add(insertarlista);

        insertar = new JButton("Insertar");
        insertar.setFont(new Font("berlin sans fb demi", Font.BOLD, 14));
        insertar.setBounds(280, 30, 150, 20);
        panel.add(insertar);

        retirar = new JButton("Retirar");
        retirar.setFont(new Font("berlin sans fb demi", Font.BOLD, 14));
        panel.add(retirar);
        retirar.setBounds(280, 60, 150, 20);

        existe = new JButton("Verificar");
        existe.setFont(new Font("berlpos sans fb demi", Font.BOLD, 14));
        existe.setBounds(280, 100, 150, 20);
        panel.add(existe);

        dibujar = new JButton("Dibujar");
        dibujar.setFont(new Font("berlin sans fb demi", Font.BOLD, 14));
        panel.add(dibujar);
        dibujar.setBounds(280, 150, 150, 20);

        salir = new JButton("<-");
        salir.setFont(new Font("berlin sans fb demi", Font.BOLD, 14));
        panel0.add(salir);
        salir.setBounds(panel0.getWidth() - 80, 5, 50, 20);

        ActionListener oyente = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == insertarlista) {
                    try {
                        String totalText = listaNums.getText();
                        String[] textElements = totalText.split(",");
                        int[] arr1 = new int[textElements.length];
                        for (int i = 0; i < textElements.length; i++) {
                            arr1[i] = Integer.parseInt(textElements[i]);
                        }
                        for (int k = 0; k < arr1.length; k++) {
                            if (b.insertar(arr1[k]) == 1) {
                                System.out.println("Numero " + arr1[k] + " insertado");
                            } else {
                                JOptionPane.showMessageDialog(null, "Numero " + arr1[k] + " repetido");
                            }
                            panel.setVisible(true);
                            panel2.setVisible(true);
                        }
                    } catch (Exception e1) {
                        System.out.println("Inserte valores validos");
                    }
                }

                if (e.getSource() == insertar) {
                    if (isNumeric(numerosI.getText())) {
                        System.out.println("Valor Insertado");
                        if (b.insertar(Integer.parseInt(numerosI.getText())) == 1) {
                            JOptionPane.showMessageDialog(null, "Numero " + Integer.parseInt(numerosI.getText()) + " insertado");
                        } else {
                            JOptionPane.showMessageDialog(null, "Numero " + Integer.parseInt(numerosI.getText()) + " repetido");
                        }
                        etiquetault.setText("");
                        panel2.repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "Inserte un numero valido");
                    }

                }

                if (e.getSource() == retirar) {
                    try {
                        if (b.retirar(b.getRaiz(), Integer.parseInt(numerosE.getText()))) {
                            etiquetault.setText("");
                            panel2.repaint();
                            JOptionPane.showMessageDialog(null, "Numero " + numerosE.getText() + " eliminado");
                        } else {
                            JOptionPane.showMessageDialog(null, "Numero inexistente en el arbol");
                        }
                        numerosI.setText("");
                    } catch (Exception e1) {
                        System.out.println("Inserte un valor valido");
                    }
                }

                if (e.getSource() == existe) {
                    String esta;
                    if (b.esta(b.getRaiz(), Integer.parseInt(numeroEx.getText()))) {
                        esta = "si";
                    } else {
                        esta = "no";
                    }
                    JOptionPane.showMessageDialog(null, "El numero " + numeroEx.getText() + " " + esta + " existe en el arbol");
                }

                if (e.getSource() == dibujar) {
                    b.initst();
                    ArrayList array = b.getArr(b.getRaiz());
                    
                    d.DibujaArbol(panel2.getGraphics(), panel2.getWidth()/2, 30, b.getRaiz(), panel2);

                    String compl;
                    b.niveles(b.getRaiz());
                    if (b.completo()) {
                        compl = "Si";
                    } else {
                        compl = "No";
                    }
                    etiquetault.setText("<html>InOrden:   " + b.inorden(b.getRaiz()) + " <br>"
                            + "PreOrden:   " + b.posorden(b.getRaiz()) + " <br>"
                            + "PosOrden:   " + b.preorden(b.getRaiz()) + " <br>"
                            + "Por niveles:   " + b.niveles(b.getRaiz()) + " <br>"
                            + "Altrura:   " + b.altura(b.getRaiz()) + " <br>"
                            + "Gordura:   " + b.gordura(b.getRaiz()) + " <br>"
                            + "Num. hojas:   " + b.numero_de_hojas(b.getRaiz()) + " <br>"
                            + "Completo:   " + compl + " <br>" + "<html>");

                }
                if (e.getSource() == salir) {
                    b.initst();
                    b.inicializarAr();
                    dispose();
                }
            }
        };
        insertar.addActionListener(oyente);
        retirar.addActionListener(oyente);
        dibujar.addActionListener(oyente);
        existe.addActionListener(oyente);
        insertarlista.addActionListener(oyente);
        salir.addActionListener(oyente);

    }

    private static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /*private static boolean isChar(String cadena) {
        try {
            cadena.charAt(0);
            return true;
        } catch (NumberFormatException nf0) {
            return false;
        }
    }*/
}
