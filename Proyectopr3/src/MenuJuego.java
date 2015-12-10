import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;




public class MenuJuego extends JFrame{
	public JPanel panelPrincipal;
	public JPanel panelBotones;
	public JPanel panelJugador;
	public JLabel nombre;
	public JTextField texto;
	public static JButton listaControles;
	public static JButton opciones;
	public static JButton empezarPartida;
	static JLabel fondoMenu =new LabelEscalado("metal slug.png",800,600);
	static JLabel fondoControles =new LabelEscalado("LogoControles.jpg",30,30);
	//pabsensi
	static JLabel pabsensi =new LabelEscalado("pabsensi.PNG",70,70);
	//asier
	static JLabel murga =new LabelEscalado("murga.PNG",70,70);
	public MenuJuego(String t){
		this.setTitle(t) ;
		this.setSize(800,600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		panelPrincipal= new JPanel();
		panelPrincipal.setBackground(Color.BLACK);
		this.add(panelPrincipal,BorderLayout.CENTER);
		panelPrincipal.setLayout(null);
		
		
		panelBotones= new JPanel();
		panelBotones.setSize(100, 100);
		panelBotones.setBackground(Color.BLACK);
		this.add(panelBotones,BorderLayout.SOUTH);
		panelBotones.setLayout(new FlowLayout());
		
		panelJugador=new JPanel();
		panelJugador.setBackground(Color.BLACK);
		this.add(panelJugador,BorderLayout.NORTH);
		panelJugador.setLayout(new FlowLayout());
		
	    listaControles=new JButton("Controles");
	    opciones=new JButton("Opciones");
	    empezarPartida=new JButton("Play");
	    nombre=new JLabel("Nombre");
	    texto=new JTextField();
	   
	   
	    
	    panelBotones.add(listaControles);
	    panelBotones.add(empezarPartida); 
	    panelBotones.add(opciones);
	    //pabsensi
	    //panelPrincipal.add(pabsensi);
	    //asier
	    //panelPrincipal.add(murga);
	    
	    panelPrincipal.add(fondoMenu);
	   
	   
	    panelJugador.add(nombre);
	    panelJugador.add(texto);
	    //pabsensi
	    pabsensi.setLocation(450,190);
	    //asier
	    murga.setLocation(320,190);
	    nombre.setForeground(Color.WHITE);
	    texto.setColumns(20);
	    //listaControles.setSize(30,30);
	    //listaControles.add(fondoControles);
	    listaControles.addActionListener(new ActionListener(){
	    	@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				VentanaControles c=new VentanaControles("CONTROLES");
				c.setVisible(true);
				
			}
	    });
	    empezarPartida.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setVisible(false);
				Juego j = new Juego();
				j.setVisible(true);
				
			}
	    	
	    });
	    opciones.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				VentanaOpciones v=new VentanaOpciones("OPCIONES");
				v.setVisible(true);
				
			}
		});
	    
	  
	    addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				//CERRAMOS LA BASE DE DATOS
				BaseDeDatos.close();
			}
		});
	  
	  
	}
	public static void main(String[] args){
		MenuJuego menu =new MenuJuego("MENU");
		menu.setVisible(true);
		//Damos nombre y creamos la base d datos
		BaseDeDatos.initBD("base");
		BaseDeDatos.crearTablaBD();
	}

}
