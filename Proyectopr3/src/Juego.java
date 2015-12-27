import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.sql.SQLException;
import java.util.Random;

import objects.Block;
import objects.Enemigos;
import objects.Player;
import framework.GameObject;
import framework.ObjectId;


public class Juego extends Canvas implements Runnable {
	/**
	 * Clase juego, que es lo que se ejecutará con cada partida según el mapa que le mandemos
	 */
	
	private static final long serialVersionUID = 565656L;
	private boolean running = false;
	private Thread thread;
	GameObject object;
	Handler handler;
	Random rand = new Random();
	/**
	 * Método de inicialización, desde aquí se crea un nuevo handler y se añaden
	 * los objetos iniciales del juego, junto con un keylistener para los controles
	 */
	private void init(){
		handler = new Handler();
		for(int i =0; i<getWidth() ; i += 32)
		handler.addObject(new Block(i, getHeight()-32, ObjectId.Block));
		for(int i=getWidth()/3;i< (int)(getWidth()*2)/3;i+=32){ 
		handler.addObject(new Block(i+5, getHeight()-100,ObjectId.Block));
		handler.addObject(new Block(i+7, getHeight()-200,ObjectId.Block));
		}
	    handler.addObject(new Player(100, 100, ObjectId.Player));
		addKeyListener(new Controles(handler));
		
		handler.addObject(new Enemigos(200, 200, ObjectId.Enemigos));
		//addKeyListener(new Controles(handler));

	}
	
	public Juego(){
		new VentanaJuego(800, 600, "Game", this);
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
		//////////////////////////
		
		////DIBUJAR TODO NUESTRO JUEGO AQUÍ////
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		handler.render(g);
		/////////////////////////
		g.dispose();
		bs.show();
		
	}
}
