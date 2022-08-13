package multmatricesestrc;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Mostrar extends JFrame implements ActionListener {

    int[][] matriz;
    int f, c;
    JPanel PMat, PSal;
    JLabel[][] matrizlbl;
    Color negro = new Color(0, 0, 0);
    Color blanco = new Color(250, 250, 250);
    Color gris = new Color(100, 100, 100);
    Color grisosc = new Color(80, 80, 80);
    JLabel dimslbl = new JLabel();
    JButton salirbtn;

    JScrollPane scrollMat = new JScrollPane(PMat, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    Mostrar(int f, int c, int[][] matriz) {
        this.matriz = matriz;
        this.f = f;
        this.c = c;
        //setUndecorated(true);
        setLayout(null);
        setBounds(200, 200, 520, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        requestFocus();
        getRootPane().setBorder(BorderFactory.createSoftBevelBorder(1, Color.YELLOW, Color.GREEN, Color.MAGENTA, Color.BLUE));
        getContentPane().setLayout(null);
        iniciarComponentes();
    }

    public void iniciarComponentes() {

        PSal = new JPanel();
        PSal.setLayout(null);
        PSal.setBackground(grisosc);
        PSal.setForeground(grisosc);
        PSal.setBounds(0, 500, 500, 100);
        add(PSal).setVisible(true);

        String dms = "Matriz de " + f + " , " + c;

        dimslbl.setText(dms);
        dimslbl.setFont(new Font("OCR A Extended", Font.BOLD, 15));
        dimslbl.setBounds(20, 20, 300, 40);
        dimslbl.setForeground(negro);
        PSal.add(dimslbl).setVisible(true);

        salirbtn = new JButton();
        salirbtn.setText("<html><center> Salir <html>");
        salirbtn.setFont(new Font("OCR A Extended", Font.BOLD, 15));
        salirbtn.setBounds(380, 10, 100, 50);
        salirbtn.addActionListener(this);
        salirbtn.setEnabled(true);
        PSal.add(salirbtn).setVisible(true);

        matrizlbl = new JLabel[c][f];
        scrollMat.setBounds(0, -80, 500, 580);
        scrollMat.getHorizontalScrollBar().setCursor(new Cursor(Cursor.HAND_CURSOR));
        scrollMat.getHorizontalScrollBar().setBackground(Color.BLACK);
        scrollMat.getVerticalScrollBar().setCursor(new Cursor(Cursor.HAND_CURSOR));
        scrollMat.getVerticalScrollBar().setBackground(Color.BLACK);

        PMat = new JPanel();
        scrollMat.setViewportView(PMat);
        scrollMat.setVisible(true);
        PMat.setLayout(null);
        PMat.setBackground(gris);
        PMat.setPreferredSize(new Dimension((c * 40) + 75, (f * 80) + 75));

        add(scrollMat);

        for (int i = 0; i < c; i++) {
            for (int j = 0; j < f; j++) {
                matrizlbl[i][j] = new JLabel();
                PMat.add(matrizlbl[i][j]);
                matrizlbl[i][j].setBounds((40 * (i + 1)), 30 + (30 * (j + 3)), 50, 20);
                matrizlbl[i][j].setText(Integer.toString(matriz[j][i]));
                matrizlbl[i][j].setBackground(Color.LIGHT_GRAY);
            }
        }
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
        if (e.getSource() == salirbtn) {
            dispose();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
