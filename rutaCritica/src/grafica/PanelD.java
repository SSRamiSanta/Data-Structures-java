package grafica;


import java.applet.*;
import java.awt.*;

public class PanelD extends Applet {

    public Panel c = new Panel();
    ScrollPane s = new ScrollPane();

    @Override
    public void init() {
        setLayout(null);
        s.setSize(445, 605);
        add(s);
        c = new Panel();
        c.setSize(1000, 600);
        s.add(c);
        
        c.setBounds(0, 0, 100000, 10000);
        c.setVisible(true);
    }

}
