package menu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;


public class VentanaOpciones extends JFrame {
	public JPanel v;
    public static JButton dificultad;
    public static JSlider sonido;
    public JLabel ControlS;
    static JLabel fondoMenu2 =new LabelEscalado("FondoOpciones2.png",700,600);
  
    public VentanaOpciones(String t){
    	this.setTitle(t) ;
		this.setSize(800,600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		v= new JPanel();
		v.setBackground(Color.BLACK);
		this.add(v,BorderLayout.CENTER);
		v.setLayout(new FlowLayout());
		
		dificultad= new JButton("dificultad");
		sonido= new JSlider();
		ControlS= new JLabel("Sonido");
		
		ControlS.setForeground(Color.WHITE);
		
		v.add(dificultad);
		v.add(ControlS);
		v.add(sonido);
		v.add(fondoMenu2);
		
		
    	
    }
}
