import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

import objects.Block;
import objects.Player;
import objects.Test;
import framework.GameObject;
import framework.ObjectId;


public class Juego extends Canvas implements Runnable {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 565656L;
	private boolean running = false;
	private Thread thread;
	GameObject object;
	Test test;
	Handler handler;
	Random rand = new Random();
	
	private void init(){
		handler = new Handler();
		for(int i =0; i<getWidth() ; i += 32)
		handler.addObject(new Block(i, getHeight()-32, ObjectId.Test));
		handler.addObject(new Player(100, 100, ObjectId.Player));
		addKeyListener(new Controles(handler));
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
