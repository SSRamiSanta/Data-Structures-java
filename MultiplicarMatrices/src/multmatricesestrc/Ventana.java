package multmatricesestrc;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Ventana extends JFrame implements ActionListener {

    Color negro = new Color(0, 0, 0);
    Color blanco = new Color(250, 250, 250);
    Color gris = new Color(100, 100, 100);
    Color grisosc = new Color(80, 80, 80);
    JPanel PDim, PTitulo, PMatriz, PBotones;
    int f, c;
    char letra = 'A';
    int matriz[][];
    JLabel dimslbl = new JLabel();
    JLabel flbl = new JLabel();
    JLabel clbl = new JLabel();
    JLabel titulolbl = new JLabel();
    JLabel multlbl = new JLabel("Matriz      *      ");
    JLabel coordxlbl, coordylbl;
    JTextField filastxt = new JTextField();
    JTextField columnastxt = new JTextField();
    JButton dimsbtn = new JButton();
    JButton guardarbtn = new JButton();
    JButton agregarbtn, mostrarbtn, multbtn, resetbtn;
    JComboBox mostrarbox, multa, multb;
    JTextField[][] leer;
    ArrayList dimensiones = new ArrayList();
    ArrayList matrices = new ArrayList();
    JScrollPane scrollMat = new JScrollPane(PMatriz, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    public Ventana() {
        setTitle("Lista Doble");
        setLayout(null);
        setIconImage(new ImageIcon(Ventana.class.getResource("/imagenes/icono.png")).getImage());
        setUndecorated(true);
        getRootPane().setBorder(BorderFactory.createSoftBevelBorder(1, Color.BLACK, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.GRAY));
        this.getContentPane().setBackground(Color.DARK_GRAY);
        setBounds(200, 200, 1000, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        requestFocus();
        setBackground(negro);
        setForeground(negro);
        iniciarComponentes();
    }

    public void iniciarComponentes() {

        PDim = new JPanel();
        PDim.setBackground(gris);
        PDim.setForeground(gris);
        PDim.setBounds(0, 0, 200, 90);
        PDim.setLayout(null);
        add(PDim);

        dimslbl.setText("Dimensiones:");
        dimslbl.setFont(new Font("OCR A Extended", Font.BOLD, 15));
        dimslbl.setBounds(10, 5, 150, 40);
        dimslbl.setForeground(negro);
        PDim.add(dimslbl).setVisible(true);

        filastxt.setText("12");
        filastxt.setBounds(20, 40, 20, 20);
        PDim.add(filastxt).setVisible(true);

        flbl.setText("f");
        flbl.setFont(new Font("OCR A Extended", Font.BOLD, 15));
        flbl.setBounds(50, 30, 20, 40);
        flbl.setForeground(negro);
        PDim.add(flbl).setVisible(true);

        columnastxt.setText("12");
        columnastxt.setBounds(85, 40, 20, 20);
        PDim.add(columnastxt).setVisible(true);

        clbl.setText("c");
        clbl.setFont(new Font("OCR A Extended", Font.BOLD, 15));
        clbl.setBounds(115, 30, 20, 40);
        clbl.setForeground(negro);
        PDim.add(clbl).setVisible(true);

        dimsbtn.setText("OK");
        dimsbtn.setBounds(130, 65, 60, 20);
        dimsbtn.addActionListener(this);
        PDim.add(dimsbtn).setVisible(true);

        PTitulo = new JPanel();
        PTitulo.setBackground(grisosc);
        PTitulo.setForeground(grisosc);
        PTitulo.setBounds(200, 0, 800, 90);
        PTitulo.setLayout(null);
        add(PTitulo).setVisible(false);

        guardarbtn.setText("<html><center> Guardar Matriz <html>");
        guardarbtn.setFont(new Font("OCR A Extended", Font.BOLD, 15));
        guardarbtn.setBounds(400, 20, 200, 50);
        guardarbtn.addActionListener(this);
        PTitulo.add(guardarbtn).setVisible(true);

        titulolbl.setFont(new Font("OCR A Extended", Font.BOLD, 25));
        titulolbl.setBounds(50, 10, 150, 40);
        titulolbl.setForeground(negro);
        PTitulo.add(titulolbl).setVisible(true);

        PBotones = new JPanel();
        PBotones.setBackground(negro);
        PBotones.setForeground(negro);
        PBotones.setBounds(0, 90, 200, 410);
        PBotones.setLayout(null);
        add(PBotones).setVisible(false);

        mostrarbox = new JComboBox();
        mostrarbox.setBounds(25, 80, 60, 30);
        PBotones.add(mostrarbox).setVisible(true);

        mostrarbtn = new JButton();
        mostrarbtn.setText("<html><center> Mostrar Matriz <html>");
        mostrarbtn.setFont(new Font("OCR A Extended", Font.BOLD, 15));
        mostrarbtn.setBounds(95, 70, 100, 50);
        mostrarbtn.addActionListener(this);
        mostrarbtn.setEnabled(false);
        PBotones.add(mostrarbtn).setVisible(true);

        agregarbtn = new JButton();
        agregarbtn.setText("<html><center> Agregar Matriz <html>");
        agregarbtn.setFont(new Font("OCR A Extended", Font.BOLD, 15));
        agregarbtn.setBounds(50, 5, 100, 50);
        agregarbtn.addActionListener(this);
        agregarbtn.setEnabled(false);
        PBotones.add(agregarbtn).setVisible(true);

        multlbl.setFont(new Font("OCR A Extended", Font.BOLD, 15));
        multlbl.setBounds(3, 200, 200, 40);
        multlbl.setForeground(blanco);
        PBotones.add(multlbl).setVisible(true);

        multa = new JComboBox();
        multa.setBounds(70, 210, 50, 20);
        PBotones.add(multa).setVisible(true);

        multb = new JComboBox();
        multb.setBounds(140, 210, 50, 20);
        PBotones.add(multb).setVisible(true);

        multbtn = new JButton();
        multbtn.setText("<html><center> Multiplicar Matrices <html>");
        multbtn.setFont(new Font("OCR A Extended", Font.BOLD, 15));
        multbtn.setBounds(25, 250, 150, 40);
        multbtn.addActionListener(this);
        multbtn.setEnabled(false);
        PBotones.add(multbtn).setVisible(true);

        resetbtn = new JButton();
        resetbtn.setText("<html><center> Reset <html>");
        resetbtn.setFont(new Font("OCR A Extended", Font.BOLD, 15));
        resetbtn.setBounds(40, 320, 120, 30);
        resetbtn.addActionListener(this);
        resetbtn.setEnabled(false);
        PBotones.add(resetbtn).setVisible(true);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dimsbtn) {
            metodoMatriz();
            dimsbtn.setVisible(false);
            PBotones.setVisible(true);
        }
        if (e.getSource() == guardarbtn) {
            agregarbtn.setEnabled(true);
            mostrarbtn.setEnabled(true);
            guardarbtn.setEnabled(false);
            multbtn.setEnabled(true);
            resetbtn.setEnabled(true);
            PMatriz.removeAll();
            PMatriz.repaint();
            mostrarbox.addItem(letra);
            multa.addItem(letra);
            multb.addItem(letra);
            guardarMatriz();
            letra++;
        }
        if (e.getSource() == agregarbtn) {
            metodoMatriz();
            agregarbtn.setEnabled(false);
            mostrarbtn.setEnabled(false);
            guardarbtn.setEnabled(true);
            multbtn.setEnabled(false);
            resetbtn.setEnabled(false);
        }
        if (e.getSource() == mostrarbtn) {
            int[][] matriz1 = null;
            String[] auxlst = null;
            String auxst;
            char h = 'A';
            for (int i = 0; i < 26; i++) {
                if (h == (char) mostrarbox.getSelectedItem()) {
                    matriz1 = (int[][]) matrices.get(i);
                    auxst = (String) dimensiones.get(i);
                    auxlst = auxst.split(",");
                    break;
                }
                h++;
            }

            Mostrar m = new Mostrar(Integer.parseInt(auxlst[0]), Integer.parseInt(auxlst[1]), matriz1);
            m.setVisible(true);

        }
        if (e.getSource() == multbtn) {
            int[][] matriza = null;
            int[][] matrizb = null;
            String[] auxlst = null;
            String[] aux2st = null;
            String auxst, auxst1;
            char h = 'A';
            for (int i = 0; i < 26; i++) {
                if (h == (char) multa.getSelectedItem()) {
                    matriza = (int[][]) matrices.get(i);
                    auxst = (String) dimensiones.get(i);
                    auxlst = auxst.split(",");
                    break;
                }
                h++;
            }
            h = 'A';
            for (int i = 0; i < 26; i++) {
                if (h == (char) multb.getSelectedItem()) {
                    matrizb = (int[][]) matrices.get(i);
                    auxst1 = (String) dimensiones.get(i);
                    aux2st = auxst1.split(",");
                    break;
                }
                h++;
            }

            int rows = Integer.parseInt(auxlst[0]);
            int cols = Integer.parseInt(aux2st[1]);

            if (Integer.parseInt(auxlst[1]) == Integer.parseInt(aux2st[0])) {
                int[][] matrizc = new int[rows][cols];

                ///////////Metodo
                // multiplicarmatrices();
                ///////////////
                String dims = rows + "," + cols;
                dimensiones.add(dims);
                multa.addItem(letra);
                multb.addItem(letra);
                mostrarbox.addItem(letra);
                letra++;
                matrices.add(matrizc);
            } else {
                JOptionPane.showMessageDialog(null, "Las dimensiones deben ser compatibles");
            }
        }
        if (e.getSource() == resetbtn) {
            letra = 'A';
            dimsbtn.setVisible(true);
            agregarbtn.setEnabled(false);
            guardarbtn.setEnabled(true);
            multbtn.setEnabled(false);
            mostrarbtn.setEnabled(false);
            resetbtn.setEnabled(false);
            PMatriz.setVisible(false);
            PBotones.setVisible(false);
            PTitulo.setVisible(false);
            matrices.clear();
            dimensiones.clear();
            mostrarbox.removeAllItems();
            multa.removeAllItems();
            multb.removeAllItems();

        }
    }

    public void metodoMatriz() {

        String titulo = String.valueOf(letra);

        PTitulo.setVisible(true);
        titulolbl.setText("Matriz " + titulo);

        f = Integer.parseInt(filastxt.getText());
        c = Integer.parseInt(columnastxt.getText());

        scrollMat.setBounds(200, 90, 800, 410);
        scrollMat.getHorizontalScrollBar().setCursor(new Cursor(Cursor.HAND_CURSOR));
        scrollMat.getHorizontalScrollBar().setBackground(Color.BLACK);
        scrollMat.getVerticalScrollBar().setCursor(new Cursor(Cursor.HAND_CURSOR));
        scrollMat.getVerticalScrollBar().setBackground(Color.BLACK);

        PMatriz = new JPanel();
        scrollMat.setViewportView(PMatriz);
        scrollMat.setVisible(true);
        PMatriz.setLayout(null);
        PMatriz.setBackground(gris);
        PMatriz.setPreferredSize(new Dimension((c * 100) + 75, (f * 60) + 75));

        add(scrollMat);

        PMatriz.setVisible(true);

        matriz = new int[f][c];
        leer = new JTextField[c][f];

        for (int y = 0; y < f; y++) {
            for (int x = 0; x < c; x++) {
                leer[x][y] = new JTextField();
                PMatriz.add(leer[x][y]);
                leer[x][y].setBounds(-60 + (82 * (x + 1)), (30 * (y + 1)), 50, 20);
                leer[x][y].setText("0");
                leer[x][y].setBackground(Color.LIGHT_GRAY);
            }
        }

        for (int i = 0; i < f; i++) {
            coordxlbl = new JLabel();
            PMatriz.add(coordxlbl);
            coordxlbl.setText(Integer.toString(i));
            coordxlbl.setBounds(5, (30 * (i + 1)), 50, 20);
        }

        for (int i = 0; i < c; i++) {
            coordxlbl = new JLabel();
            PMatriz.add(coordxlbl);
            coordxlbl.setText(Integer.toString(i));
            coordxlbl.setBounds(-60 + (82 * (i + 1)), 5, 50, 20);

        }
        String dims = f + "," + c;
        dimensiones.add(dims);
    }

    public void guardarMatriz() {

        for (int i = 0; i < f; i++) {
            for (int j = 0; j < c; j++) {
                matriz[i][j] = Integer.parseInt(leer[j][i].getText());
            }
        }
        matrices.add(matriz);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
