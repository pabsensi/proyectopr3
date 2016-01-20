package juego;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import niveles.Nivel1;
import objects.Bat;
import objects.Crusher;
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
	private int fin;
	private Player player;
	boolean deadend = false;
	boolean winend = false;
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
		//for(int i =0; i<32000 ; i += 32)
		//handler.addObject(new Block(i, getHeight()-32, ObjectId.Block));
		//for(int i=getWidth()/3;i< (int)(getWidth()*2)/3;i+=32){ 
		//handler.addObject(new Block(i+5, getHeight()-100,ObjectId.Block));
		//handler.addObject(new Block(i+7, getHeight()-200,ObjectId.Block));
		//}
		fin = Nivel1.createLevel1(handler);
		player = new Player(100, 100, ObjectId.Player);
	    handler.addObject(player);
		addKeyListener(new Controles(handler));
		for(int i=0; i<10000; i=i+50){
		handler.addObject(new Bat(800+i, 400, ObjectId.Enemy));
		}
		handler.addObject(new Crusher(200, 200, ObjectId.Enemy));

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
			if(((Player)tempObject).getVida()<=0){
				deadend = true;
				handler.objectlist.remove(i);
				addKeyListener(new KeyListener(){

					@Override
					public void keyPressed(KeyEvent e) {
						// TODO Auto-generated method stub
						if(e.getKeyCode() == KeyEvent.VK_Y){
							init();
							deadend = false;
						}

						if(e.getKeyCode() == KeyEvent.VK_N){
							ventana.close();							
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
					
				});
			}

			if(((Player)tempObject).getX()>=fin){
				winend = true;
				addKeyListener(new KeyListener(){

					@Override
					public void keyPressed(KeyEvent e) {
						// TODO Auto-generated method stub
						if(e.getKeyCode() == KeyEvent.VK_Y){
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
					
				});
				
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
		g.setColor(Color.blue);
		g.fillRect(0, 0, getWidth(), getHeight());

		g2d.translate(cam.getX(), cam.getY()); //begin of cam

		if(deadend)
			g.drawImage(dead, (int) -cam.getX()+  WIDTH/6, HEIGHT/2 - dead.getHeight(), null);
		else if(winend)
			g.drawImage(win, (int) -cam.getX()+  WIDTH/4, HEIGHT/2, null);
			
		handler.render(g);

		g2d.translate(-cam.getX(), -cam.getY()); //end of cam
		/////////////////////////
		g.dispose();
		bs.show();
		
	}
}
