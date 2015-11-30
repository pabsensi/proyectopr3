package sprite.player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Player {
	volatile boolean facingRight = true;
	volatile boolean movingRight = false, movingLeft = false, jumping = false, crouching = false;
	private int currentFrame = 0;
	volatile BufferedImage sprite;
	volatile boolean animate;
	volatile boolean jump;
	private ArrayList<BufferedImage> currentAnim = new ArrayList<>();
	final String default_anim = "running_right";
	public JPanel pane;
	HashMap<String, ArrayList<BufferedImage>> spriteHash = new HashMap<>();
	public Player(){

		spriteHash = SpriteListCreator.SpriteHashCreator(new File("resources/Player"));
		currentAnim = spriteHash.get(default_anim);
		sprite = currentAnim.get(currentFrame);
		pane = new JPanel(){
			@Override
	        protected void paintComponent(Graphics g) {

	            super.paintComponent(g);
	            g.drawImage(sprite, 0, 0, null);

				Player.this.update();
				setSize(sprite.getWidth(), sprite.getHeight());
				pane.setBorder(BorderFactory.createLineBorder(Color.red));
	            repaint();
	        }
	    };
		
	}
	public void update(){
		if(jump){
			ArrayList<BufferedImage> prevAnim = new ArrayList<BufferedImage>();
			if(facingRight){
			prevAnim = currentAnim;
			currentAnim = spriteHash.get("jump_right");
			}
			else{
				prevAnim = currentAnim;
				currentAnim = spriteHash.get("jump_left");
			}
			for(BufferedImage s : currentAnim){
				currentFrame++;
				System.out.println(currentFrame);
				sprite = s;
			}
			try {
				Thread.sleep(75);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else if(animate){
			currentFrame++;
			System.out.println(currentFrame);
			try{
			sprite = currentAnim.get(currentFrame);
			}catch(IndexOutOfBoundsException e){
				currentFrame = 0;
				sprite = currentAnim.get(currentFrame);
			}
			try {
				Thread.sleep(75);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}
	public void jump(){
		jump =  true;
	}
	public void run(){
		animate = true;
	}
	public void stop(){
		animate = false;
		sprite = currentAnim.get(0);
	}
	public void faceLeft(){
		facingRight = false;
		currentAnim = spriteHash.get("running_left");
	}
	public void faceRight(){
		facingRight =true;
		currentAnim = spriteHash.get("running_right");
	}
	public static void main(String[] args0){
		Player jugador = new Player();
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.setSize(600, 800);
	//	JPanel pane = new JPanel();
	//	pane.getGraphics().drawImage(jugador.sprite,100,100, null);
		window.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				switch(e.getKeyCode()){
				case(KeyEvent.VK_RIGHT):
					jugador.faceRight();
					jugador.run();
					e.consume();
					break;
				case(KeyEvent.VK_LEFT):
					jugador.faceLeft();
					jugador.run();
					e.consume();
					break;
				case(KeyEvent.VK_SPACE):
					jugador.jump = true;
					e.consume();
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_RIGHT ^ e.getKeyCode() == KeyEvent.VK_LEFT)
					jugador.stop();
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		window.add(jugador.pane);
		
		
	}
}
