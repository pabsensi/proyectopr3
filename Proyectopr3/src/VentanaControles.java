import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTable;




public class VentanaControles extends JFrame {
	public JPanel c;

	static JLabel drch=new LabelEscalado("drch.PNG",90,90);
	static JLabel izq=new LabelEscalado("izq.PNG",90,90);
	static JLabel agacharse=new LabelEscalado("agacharse.PNG",90,90);
	static JLabel arribaM=new LabelEscalado("arriba.PNG",100,90);
	static JLabel salto=new LabelEscalado("salto.PNG",90,90);
	static JLabel granada=new LabelEscalado("granada.PNG",90,90);
	static JLabel disparo=new LabelEscalado("disparo.PNG",100,90);
	static JLabel fondoMenu2 =new LabelEscalado("FondoOpciones2.png",700,600);//700,600 predeterminado

	public static JButton derecha;
	public static JButton izquierda;
	public static JButton arriba;
	public static JButton agachar;
	public static JButton saltar;
	public static JButton disparar;
	public static JButton hacer;

	static Integer codsTeclasControladas[] = { KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,KeyEvent.VK_SPACE }; 
	static List<Integer> listaTeclas = Arrays.asList( codsTeclasControladas );
	boolean teclasPulsadas[];


	public VentanaControles(String t){
		this.setTitle(t) ;
		this.setSize(800,600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());

		c= new JPanel();
		c.setBackground(Color.BLACK);
		//this.add(c,BorderLayout.CENTER);
		//c.setLayout(new FlowLayout());

		teclasPulsadas = new boolean[ codsTeclasControladas.length ];
		//for(int i=65;i<90;i++) listaTeclas.add(i);
			

		//Hay que a�ador dos columnas una de descripci�n y otra del dibujo de los controles
		derecha= new JButton("DERECHA");
		izquierda= new JButton("IZQUIERDA");
		arriba=new JButton("ARRIBA");
		agachar= new JButton("AGACHARSE");
		saltar= new JButton("SALTAR");
		disparar= new JButton("DISPARAR");
		hacer= new JButton("GRANADA");

		drch.setIcon(new ImageIcon("drch.PNG"));
		izq.setIcon(new ImageIcon("izq.PNG"));
		arribaM.setIcon(new ImageIcon("ariba.PNG"));
		agacharse.setIcon(new ImageIcon("agacharse.PNG"));
		salto.setIcon(new ImageIcon("salto.PNG"));
		disparo.setIcon(new ImageIcon("disparo.PNG"));
		granada.setIcon(new ImageIcon("granada.PNG"));


		c.add(derecha);
		c.add(izquierda);
		c.add(arriba);
		c.add(agachar);
		c.add(saltar);
		c.add(disparar);
		c.add(hacer);


		c.add(drch);
		c.add(izq);
		c.add(arribaM);
		c.add(agacharse);
		c.add(salto);
		c.add(disparo);
		c.add(granada);
		c.add(fondoMenu2);

		c.setLayout(null);
		drch.setBorder(BorderFactory.createLineBorder(Color.orange));
		izq.setBorder(BorderFactory.createLineBorder(Color.orange));
		arribaM.setBorder(BorderFactory.createLineBorder(Color.orange));
		agacharse.setBorder(BorderFactory.createLineBorder(Color.orange));
		salto.setBorder(BorderFactory.createLineBorder(Color.orange));
		disparo.setBorder(BorderFactory.createLineBorder(Color.orange));
		granada.setBorder(BorderFactory.createLineBorder(Color.orange));

		drch.setLocation(200,50);
		izq.setLocation(550, 50);
		arribaM.setLocation(540,200);
		agacharse.setLocation(200, 220);
		salto.setLocation(580, 320);
		disparo.setLocation(200, 380);
		granada.setLocation(550, 460);

		derecha.setBounds(50,50,130,80);
		izquierda.setBounds(400,50,130,80);
		arriba.setBounds(400,200,130,80);
		agachar.setBounds(50,220,130,80);
		saltar.setBounds(400,320,160,80);
		disparar.setBounds(50,380,130,80);
		hacer.setBounds(400,460,130,80);

		getContentPane().add(c,BorderLayout.CENTER);

		
				derecha.addKeyListener( new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						String sql;
						if (listaTeclas.contains(e.getKeyCode())) {
							// Si la tecla es de las que controlamos (cursores)
							teclasPulsadas[ listaTeclas.indexOf( e.getKeyCode() ) ] = true;
							try {
								//PARA INTRODUCIR LOS DATOS DE UNA TABLA sin m�todo 
								//sql="insert into BaseControles values ("+e.getExtendedKeyCode()+",'derecha')";
								sql="update basecontroles set tecla="+e.getExtendedKeyCode()+" where boton='derecha'";
								BaseDeDatos.getStatement().execute(sql);
								System.out.println(sql);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}

					}
				
		});
				izquierda.addKeyListener( new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						String sql;
						if (listaTeclas.contains(e.getKeyCode())) {
							// Si la tecla es de las que controlamos (cursores)
							teclasPulsadas[ listaTeclas.indexOf( e.getKeyCode() ) ] = true;
							try {
								//PARA INTRODUCIR LOS DATOS DE UNA TABLA sin m�todo 
								//sql="insert into BaseControles values ("+e.getExtendedKeyCode()+",'izquierda')";
								sql="update basecontroles set tecla="+e.getExtendedKeyCode()+" where boton='izquierda'";
								BaseDeDatos.getStatement().execute(sql);
								System.out.println(sql);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}

					}
				
		});	
				arriba.addKeyListener( new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						String sql;
						if (listaTeclas.contains(e.getKeyCode())) {
							// Si la tecla es de las que controlamos (cursores)
							teclasPulsadas[ listaTeclas.indexOf( e.getKeyCode() ) ] = true;
							try {
								//PARA INTRODUCIR LOS DATOS DE UNA TABLA sin m�todo 
								//sql="insert into BaseControles values ("+e.getExtendedKeyCode()+",'arriba')";
								sql="update basecontroles set tecla="+e.getExtendedKeyCode()+" where boton='arriba'";
								BaseDeDatos.getStatement().execute(sql);
								System.out.println(sql);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}

					}
				
		});	
				agachar.addKeyListener( new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						String sql;
						if (listaTeclas.contains(e.getKeyCode())) {
							// Si la tecla es de las que controlamos (cursores)
							teclasPulsadas[ listaTeclas.indexOf( e.getKeyCode() ) ] = true;
							try {
								//PARA INTRODUCIR LOS DATOS DE UNA TABLA sin m�todo 
								//sql="insert into BaseControles values ("+e.getExtendedKeyCode()+",'agachar')";
								sql="update basecontroles set tecla="+e.getExtendedKeyCode()+" where boton='agachar'";
								BaseDeDatos.getStatement().execute(sql);
								System.out.println(sql);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}

					}
				
		});	
				saltar.addKeyListener( new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						String sql;
						if (listaTeclas.contains(e.getKeyCode())) {
							// Si la tecla es de las que controlamos (cursores)
							teclasPulsadas[ listaTeclas.indexOf( e.getKeyCode() ) ] = true;
							try {
								//PARA INTRODUCIR LOS DATOS DE UNA TABLA sin m�todo 
								//sql="insert into BaseControles values ("+e.getExtendedKeyCode()+",'saltar')";
								sql="update basecontroles set tecla="+e.getExtendedKeyCode()+" where boton='saltar'";
								BaseDeDatos.getStatement().execute(sql);
								System.out.println(sql);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}

					}
				
		});	
				disparar.addKeyListener( new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						String sql;
						if (listaTeclas.contains(e.getKeyCode())) {
							// Si la tecla es de las que controlamos (cursores)
							teclasPulsadas[ listaTeclas.indexOf( e.getKeyCode() ) ] = true;
							try {
								//PARA INTRODUCIR LOS DATOS DE UNA TABLA sin m�todo 
								//sql="insert into BaseControles values ("+e.getExtendedKeyCode()+",'disparar')";
								sql="update basecontroles set tecla="+e.getExtendedKeyCode()+" where boton='disparar'";
								BaseDeDatos.getStatement().execute(sql);
								System.out.println(sql);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}

					}
				
		});	
				hacer.addKeyListener( new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						String sql;
						if (listaTeclas.contains(e.getKeyCode())) {
							// Si la tecla es de las que controlamos (cursores)
							teclasPulsadas[ listaTeclas.indexOf( e.getKeyCode() ) ] = true;
							try {
								//PARA INTRODUCIR LOS DATOS DE UNA TABLA sin m�todo 
								//sql="insert into BaseControles values ("+e.getExtendedKeyCode()+",'hacer')";
								sql="update basecontroles set tecla="+e.getExtendedKeyCode()+" where boton='hacer'";
								BaseDeDatos.getStatement().execute(sql);
								System.out.println(sql);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}

					}
				
		});	
	}
}
