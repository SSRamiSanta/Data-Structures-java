package arbolrojonegro;

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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ventana extends JFrame {
    
    Color negro = new Color(0, 0, 0);
    Color grisclaro = new Color(180, 180, 180);
    Color gris = new Color(100, 100, 100);
    Color grisosc = new Color(80, 80, 80);
    
    JPanel panel;
    JPanel panel2;
    ArbolRojoNegro arbolitoAVL = new ArbolRojoNegro();
    
    JButton inicializar = new JButton("Inicializar Arbol");
    JButton insertar = new JButton("Insertar numero");
    JButton retirar = new JButton("Retirar numero");
    JButton dibujar = new JButton("Dibujar arbol");
    JButton limpiar = new JButton("Limpiar lienzo");
    JButton salir = new JButton("X");
    
    JTextField numerosI = new JTextField();
    JTextField numerosE = new JTextField();
    
    public ventana() {
        setTitle("ArbolRoji-Negro");
        setIconImage(new ImageIcon(ventana.class.getResource("/imagenes/icono.png")).getImage());
        setUndecorated(true);
        getRootPane().setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));
        this.getContentPane().setBackground(Color.BLACK);
        setLayout(null);
        setBounds(100, 50, 1200, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        componentes();
    }
    
    private void componentes() {
        colocarPanel();
        colocarPanel2();
        etiquetas();
        botones();
        cajasDeTexto();
    }
    
    private void colocarPanel() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(5, 5, 1185, 250);
        panel.setBackground(gris);
        this.getContentPane().add(panel);
        
    }
    
    private void colocarPanel2() {
        panel2 = new JPanel();
        panel2.setBounds(5, 260, 1185, 330);
        this.getContentPane().add(panel2);
        panel2.setBackground(grisosc);
        panel2.setLayout(null);
        
    }
    
    private void etiquetas() {
        JLabel etiqueta0 = new JLabel("Arbol Roji-Negro");
        JLabel etiqueta1 = new JLabel("Color Nodos");
        JLabel etiqueta2 = new JLabel("0 -> Negro");
        JLabel etiqueta4 = new JLabel("1 -> Rojo ");
        
        etiqueta0.setForeground(negro);
        etiqueta0.setFont(new Font("Times new roman", Font.BOLD, 40));
        etiqueta0.setBounds(25, 10, 600, 50);
        panel.add(etiqueta0);
        
        etiqueta1.setForeground(negro);
        panel.add(etiqueta1);
        etiqueta1.setBounds(220, 65, 120, 50);
        etiqueta2.setForeground(negro);
        panel.add(etiqueta2);
        etiqueta2.setBounds(340, 65, 120, 50);
        
        panel.add(etiqueta4);
        etiqueta4.setBounds(460, 65, 120, 50);
        etiqueta4.setForeground(Color.red);
    }
    
    private void cajasDeTexto() {
        panel.add(numerosI);
        numerosI.setBounds(25, 115, 190, 20);
        
        panel.add(numerosE);
        numerosE.setBounds(25, 155, 190, 20);
    }
    
    private void botones() {
        
        panel.add(inicializar).setVisible(false);
        inicializar.setFont(new Font("Times new roman", Font.BOLD, 15));
        inicializar.setBackground(grisclaro);
        inicializar.setForeground(negro);
        inicializar.setBounds(25, 65, 150, 20);
        
        panel.add(insertar);
        insertar.setFont(new Font("Times new roman", Font.BOLD, 15));
        insertar.setBackground(grisclaro);
        insertar.setForeground(negro);
        insertar.setBounds(300, 115, 190, 20);
        
        panel.add(retirar);
        retirar.setFont(new Font("Times new roman", Font.BOLD, 15));
        retirar.setBackground(grisclaro);
        retirar.setForeground(negro);
        retirar.setBounds(300, 155, 190, 20);
        
        panel.add(dibujar);
        dibujar.setFont(new Font("Times new roman", Font.BOLD, 15));
        dibujar.setBackground(grisclaro);
        dibujar.setForeground(negro);
        dibujar.setBounds(25, 215, 190, 20);
        
        panel.add(limpiar);
        limpiar.setFont(new Font("Times new roman", Font.BOLD, 15));
        limpiar.setBackground(grisclaro);
        limpiar.setForeground(negro);
        limpiar.setBounds(250, 215, 190, 20);
        
        panel.add(salir);
        salir.setFont(new Font("Times new roman", Font.BOLD, 15));
        salir.setBackground(grisclaro);
        salir.setForeground(negro);
        salir.setBounds(panel.getWidth() - 45, 0, 50, 30);
        
        ActionListener oyente = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dibujar d = new dibujar();
                
                if (e.getSource() == inicializar) {
                    panel2.repaint();
                    System.out.println("Arbol inicializado");
                    ArbolRojoNegro a = new ArbolRojoNegro();
                    a.inicializar();
                    d.inicializaArbol(panel2.getGraphics(), 60, 30, "Arbol inicializado");
                }
                if (e.getSource() == insertar) {
                    try {
                        
                        String totalText = numerosI.getText();
                        if (arbolitoAVL.insert(Integer.parseInt(totalText)) == 0) {
                            JOptionPane.showMessageDialog(null, "Numero repetido");
                        } else {
                            System.out.println("Numero insertado");
                        }
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, "Inserte un valor valido");
                        System.out.println("Inserte un valor valido");
                    }
                    numerosI.setText("");
                }
                
                if (e.getSource() == retirar) {
                    try {
                        System.out.println("Numero Eliminado");
                        arbolitoAVL.eliminarNodo(Integer.parseInt(numerosE.getText()));
                        numerosI.setText("");
                        panel2.repaint();
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, "Inserte un valor valido");
                    }
                    
                }
                
                if (e.getSource() == dibujar) {
                    ArrayList array = arbolitoAVL.inOrden(arbolitoAVL.getRaiz());
                    int indice = array.indexOf(Integer.toString(arbolitoAVL.getRaiz().clave));
                    d.DibujaArbol(panel2.getGraphics(), (indice + 1) * 60, 30, arbolitoAVL.getRaiz(), panel2);
                    
                }
                if (e.getSource() == limpiar) {
                    panel2.repaint();
                }
                if (e.getSource() == salir) {
                    System.exit(0);
                }
            }
        };
        inicializar.addActionListener(oyente);
        
        insertar.addActionListener(oyente);
        
        retirar.addActionListener(oyente);
        
        dibujar.addActionListener(oyente);
        
        limpiar.addActionListener(oyente);
        
        salir.addActionListener(oyente);
    }
}
