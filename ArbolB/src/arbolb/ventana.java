package arbolb;

import arbolb.ProcesosArbolesB;
import arbolb.dibujar;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ventana extends JFrame implements ActionListener {

    Color negro = new Color(0, 0, 0);
    Color grisclaro = new Color(180, 180, 180);
    Color gris = new Color(100, 100, 100);
    Color grisosc = new Color(50, 50, 50);
    Color blanco = new Color(250, 250, 250);
    ProcesosArbolesB ParbB;

    JPanel PIngreso, PArbol;
    JLabel ordenlbl, insertarlbl, retirarlbl;
    JButton ordenbtn, insertarbtn, retirarbtn, dibujarbtn, limpiarbtn, cerrarbtn;
    JTextField ordentxt, insertartxt, retirartxt;

    public ArrayList<String> ino = new ArrayList<>();

    public ventana() {
        setTitle("Arboles B");
        setIconImage(new ImageIcon(ventana.class.getResource("/imagenes/icono.png")).getImage());
        setUndecorated(true);
        getRootPane().setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));
        this.getContentPane().setBackground(Color.DARK_GRAY);
        setLayout(null);
        setBounds(20, 10, 1330, 750);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        componentes();
    }

    private void componentes() {
        PanelIngreso();
        PanelArbol();
    }

    private void PanelIngreso() {
        PIngreso = new JPanel();
        PIngreso.setLayout(null);
        PIngreso.setBounds(0, 0, 1330, 230);
        PIngreso.setBackground(gris);
        this.getContentPane().add(PIngreso);
        ComponentesIngreso();
    }

    private void PanelArbol() {
        PArbol = new JPanel();
        PArbol.setBounds(0, 230, 1330, 520);
        this.getContentPane().add(PArbol);
        PArbol.setBackground(grisosc);
        PArbol.setLayout(null);

    }

    private void ComponentesIngreso() {
        Etiquetas();
        CajasTexto();
        Botones();
    }

    public void Etiquetas() {
        ordenlbl = new JLabel("Arbol B");
        ordenlbl.setFont(new Font("Times new roman", Font.BOLD, 25));
        ordenlbl.setBounds(10, 10, 120, 50);
        ordenlbl.setForeground(negro);
        PIngreso.add(ordenlbl).setVisible(true);

        insertarlbl = new JLabel("Insertar (Letras):");
        insertarlbl.setFont(new Font("Times new roman", Font.BOLD, 25));
        insertarlbl.setBounds(10, 60, 200, 50);
        insertarlbl.setForeground(negro);
        PIngreso.add(insertarlbl).setVisible(false);

        retirarlbl = new JLabel("Retirar letra:");
        retirarlbl.setFont(new Font("Times new roman", Font.BOLD, 25));
        retirarlbl.setBounds(10, 110, 200, 50);
        retirarlbl.setForeground(negro);
        PIngreso.add(retirarlbl).setVisible(false);
    }

    public void CajasTexto() {
        ordentxt = new JTextField();
        ordentxt.setBounds(220, 25, 200, 20);
        //PIngreso.add(ordentxt).setVisible(true);

        insertartxt = new JTextField();
        PIngreso.add(insertartxt).setVisible(false);
        insertartxt.setBounds(220, 80, 200, 20);

        retirartxt = new JTextField();
        PIngreso.add(retirartxt).setVisible(false);
        retirartxt.setBounds(220, 130, 200, 20);
    }

    public void Botones() {
        ordenbtn = new JButton("Comenzar");
        ordenbtn.setFont(new Font("Times new roman", Font.BOLD, 18));
        ordenbtn.setBounds(220, 25, 200, 20);
        PIngreso.add(ordenbtn).setVisible(true);
        ordenbtn.addActionListener(this);

        insertarbtn = new JButton("Insertar");
        insertarbtn.setFont(new Font("Times new roman", Font.BOLD, 18));
        PIngreso.add(insertarbtn).setVisible(false);
        insertarbtn.setBounds(430, 80, 200, 20);
        insertarbtn.addActionListener(this);

        retirarbtn = new JButton("Retirar");
        retirarbtn.setFont(new Font("Times new roman", Font.BOLD, 18));
        PIngreso.add(retirarbtn).setVisible(false);
        retirarbtn.setBounds(430, 130, 200, 20);
        retirarbtn.addActionListener(this);

        dibujarbtn = new JButton("Dibujar");
        dibujarbtn.setFont(new Font("Times new roman", Font.BOLD, 18));
        PIngreso.add(dibujarbtn).setVisible(false);
        dibujarbtn.setBounds(220, 175, 200, 20);
        dibujarbtn.addActionListener(this);

        limpiarbtn = new JButton("Limpiar");
        limpiarbtn.setFont(new Font("Times new roman", Font.BOLD, 18));
        PIngreso.add(limpiarbtn).setVisible(false);
        limpiarbtn.setBounds(430, 175, 200, 20);
        limpiarbtn.addActionListener(this);

        cerrarbtn = new JButton("<html><center>X<html>");
        cerrarbtn.setFont(new Font("Consolas", Font.BOLD, 30));
        cerrarbtn.setBackground(negro);
        cerrarbtn.setForeground(blanco);
        cerrarbtn.setBounds(PIngreso.getWidth() - 80, 10, 40, 30);
        PIngreso.add(cerrarbtn).setVisible(true);
        cerrarbtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dibujar d = new dibujar();
        if (e.getSource() == ordenbtn) {
            PArbol.removeAll();
            insertarlbl.setVisible(true);
            insertartxt.setVisible(true);
            insertarbtn.setVisible(true);
            ParbB = new ProcesosArbolesB(1);
        }
        if (e.getSource() == insertarbtn) {
            retirarlbl.setVisible(true);
            retirartxt.setVisible(true);
            retirarbtn.setVisible(true);
            dibujarbtn.setVisible(true);
            insertar(ParbB);
        }
        if (e.getSource() == retirarbtn) {
            eliminar(ParbB);
        }
        if (e.getSource() == dibujarbtn) {
            limpiarbtn.setVisible(true);
            dibujar(ParbB);
        }
        if (e.getSource() == limpiarbtn) {
            PArbol.repaint();
        }
        if (e.getSource() == cerrarbtn) {
            System.exit(0);
        }
    }

    public void insertar(ProcesosArbolesB PB) {
        String strValor = insertartxt.getText().trim();
        if (PB.buscar(strValor) == null) {
            PB.Insertar(strValor);
            ino.add(strValor);
        } else {
            JOptionPane.showMessageDialog(null, "Valor repetido");
        }
        insertartxt.setText("");
        insertartxt.requestFocus();

    }

    public void eliminar(ProcesosArbolesB PB) {

        System.out.println("Numero Eliminado");
        String strValor = retirartxt.getText().trim();
        PB.eliminar(strValor);
        for (int i = 0; i < ino.size(); i++) {
            if (ino.get(i).compareTo(strValor) == 0) {
                ino.remove(i);
            }
        }
        retirartxt.setText("");
        retirartxt.requestFocus();

    }

    public void dibujar(ProcesosArbolesB PB) {
        String arB = ParbB.imprimirarbol(PArbol.getGraphics(), ParbB);
        System.out.println("arbB " + arB);
    }

    public void buscar(ProcesosArbolesB PB) {
        String strValor = insertartxt.getText().trim();
        PB.buscar2(strValor);
    }

    public void inorden(ProcesosArbolesB PB) {
        Collections.sort(ino);
        JOptionPane.showMessageDialog(null, "Inorden: \n" + ino.toString());
    }

}
