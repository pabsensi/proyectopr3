package juego;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;

import javax.imageio.ImageIO;

import menu.BaseDeDatos;
import niveles.Nivel1;
import niveles.Nivel2;
import niveles.Nivel3;
import niveles.Nivel4;
import objects.Block;
import objects.Player;
import framework.Camara;
import framework.Controles;
import framework.GameObject;
import framework.Handler;
import framework.ObjectId;


public class Juego extends Canvas implements Runnable {
	/**
	 * Clase juego, que es lo que se ejecutará con cada partida según el mapa que le mandemos
	 */

	static URL url = Juego.class.getResource("fondo.wav");
	static AudioClip clip = Applet.newAudioClip(url);
	KeyAdapter keyadapter;
	int xInicio;
	int yInicio;
	boolean ypressed = false;
	private int nivel = 1;
	private int fin;
	private Player player;
	boolean deadend;
	boolean winend;
	BufferedImage dead;
	BufferedImage win;
	private VentanaJuego ventana;
	public static final int HEIGHT = 600;
	public static final int WIDTH = 800;		
	private static final long serialVersionUID = 565656L;
	private boolean running = false;
	private Thread thread;
	GameObject object;
	Camara cam;
	Handler handler;
	Random rand = new Random();
	/**
	 * Método de inicialización, desde aquí se crea un nuevo handler y se añaden
	 * los objetos iniciales del juego, junto con un keylistener para los controles
	 */
	private void init(){
		xInicio=0;
		yInicio=500;
		fin = 684684684;
		removeKeyListener(keyadapter);		
		keyadapter = null;
		deadend = false;
		winend = false;
		try {
			dead = ImageIO.read(new File("resources/ded.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			win = ImageIO.read(new File("resources/win.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cam = new Camara(0,0);
		handler = new Handler();
		switch(nivel){
		case(1):
			fin = Nivel1.createLevel(handler);
			break;
		case(2):
			fin = Nivel2.createLevel(handler);
			break;
		case(3):
			fin = Nivel3.createLevel(handler);
			xInicio = -50;
			yInicio = 50;
			break;
		case(4):
			fin = Nivel4.createLevel(handler);
			break;
		default:
			fin = Nivel1.createLevel(handler);
			nivel = 1;
			break;
		}
		player = new Player(xInicio, yInicio, ObjectId.Player);
	    handler.addObject(player);
	    handler.addObject(new Block(fin-236, HEIGHT-494, ObjectId.Block, "flag.png"));
		addKeyListener(new Controles(handler));
	}
	public Juego(){
		ventana = new VentanaJuego(WIDTH, HEIGHT, "Game", this);
	}
	public static void main(String[] args) {
		new Juego();

	}
	public synchronized void start(){
			if(running)
				return;
			running = true;
			thread = new Thread(this);
			thread.start();
	}
	/**
	 * Método que se ejecutará en un hilo de juego
	 */
	public void run() {
		clip.play();
		init();
		this.requestFocus();
		System.out.println("thread is running");
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		
	}
	private void tick(){
		handler.tick();
		for(int i =0; i < handler.objectlist.size(); i++){
			GameObject tempObject = handler.objectlist.get(i);
			if(tempObject instanceof Player){
			cam.tick(tempObject);
			if(((Player)tempObject).getVida()<=0 && !deadend){
				deadend = true;
				handler.objectlist.remove(i);
				keyadapter = new KeyAdapter(){

					@Override
					public void keyPressed(KeyEvent e) {
						// TODO Auto-generated method stub
						if(e.getKeyCode() == KeyEvent.VK_Y){
							try {
							String sql = "Update BaseJugadoresDef set jugador='" + player.getPuntuacion()+"' where numJugador = 0";
							BaseDeDatos.getStatement().execute(sql);
							} catch (SQLException  | java.lang.NullPointerException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							init();
							deadend = false;
						}

						if(e.getKeyCode() == KeyEvent.VK_N){
							try {
								String sql = "Update BaseJugadoresDef set jugador='" + player.getPuntuacion()+"' where numJugador = 0";
								BaseDeDatos.getStatement().execute(sql);
								BaseDeDatos.close();
							}  catch (SQLException  | java.lang.NullPointerException e2)  {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							running = false;
							ventana.close();
							clip.stop();
						}
					}

					@Override
					public void keyReleased(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void keyTyped(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
					
				};
				addKeyListener(keyadapter);
			}

			if(((Player)tempObject).getX()>=fin &&!winend){
				winend = true;
				keyadapter = new KeyAdapter(){

					@Override
					public void keyPressed(KeyEvent e) {
						if(e.getKeyCode() == KeyEvent.VK_N){
							try {
								String sql = "Update BaseJugadoresDef set jugador='" + player.getPuntuacion()+"' where numJugador = 0";
								BaseDeDatos.getStatement().execute(sql);
								BaseDeDatos.close();
								} catch (SQLException  | java.lang.NullPointerException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
							ventana.close();
							running= false;
							clip.stop();
						}
						if(e.getKeyCode() == KeyEvent.VK_Y){
							try {
								String sql = "Update BaseJugadoresDef set jugador='" + player.getPuntuacion()+"' where numJugador = 0";
								BaseDeDatos.getStatement().execute(sql);
								}  catch (SQLException  | java.lang.NullPointerException e2)  {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
							ypressed = true;					
							
							
						// TODO Auto-generated method stub
						}
					}

					@Override
					public void keyReleased(KeyEvent e) {
						if(e.getKeyCode() == KeyEvent.VK_Y && ypressed){
							ypressed = false;
							winend = false;
							nivel += 1;
							init();
						}
					}

					@Override
					public void keyTyped(KeyEvent e) {
						
					}
					
				};
				addKeyListener(keyadapter);
				
			}
				}
		}
	}
	/**
	 * Método que renderiza el fondo del juego
	 */
	//número de buffers (para que cargue más cosas)
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		//////////////////////////
		////DIBUJAR TODO NUESTRO JUEGO AQUÍ////
		switch(nivel){
		case(1):
			g.setColor(Color.blue);
			break;
		case(2):
			g.setColor(Color.lightGray);
			break;
		case(3):
			g.setColor(Color.orange);
			break;
		case(4):
			g.setColor(Color.magenta);
			break;
		default:
			g.setColor(Color.blue);
			break;
		}
		g.fillRect(0, 0, getWidth(), getHeight());

		g2d.translate(cam.getX(), cam.getY()); //begin of cam

		if(deadend)
			g.drawImage(dead, (int) -cam.getX()+  WIDTH/6, HEIGHT/2 - dead.getHeight(), null);
		if(winend)
			g.drawImage(win, (int) -cam.getX()+  WIDTH/4, HEIGHT/2, null);
			
		handler.render(g);

		g2d.translate(-cam.getX(), -cam.getY()); //end of cam
		/////////////////////////
		g.dispose();
		bs.show();
		
	}
}
