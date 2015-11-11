package pruebas;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import sprite.player.SpriteListCreator;

public class VentanaPrueba extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static void main(String[] args0){
		VentanaPrueba asdasa = new VentanaPrueba();
		asdasa.setVisible(true);
	}

	HashMap<String, ArrayList<BufferedImage>> spriteHash = new HashMap<>();
	boolean facingRight = true;
	public VentanaPrueba(){
	final String defaultanim = "running_right";
	spriteHash = SpriteListCreator.SpriteHashCreator();
	Animator animacion = new Animator(spriteHash.get(defaultanim));
	animacion.setSpeed(75);
	JPanel pane = new JPanel(){
		@Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);
            g.drawImage(animacion.sprite, 100, 100, null);

			animacion.update(System.currentTimeMillis());
       	
            repaint();
        }
    };
    this.addKeyListener(new KeyListener(){

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				if(!facingRight){
					animacion.frames = spriteHash.get("running_right");
					facingRight = true;
					animacion.start();
				}
				else if(!animacion.running){
					animacion.start();
					facingRight = true;
				}
					
			}
			if(e.getKeyCode() == KeyEvent.VK_LEFT){
				if(facingRight){
					animacion.frames = spriteHash.get("running_left");
					facingRight = false;
					animacion.start();
			}
				else if(!animacion.running){
					animacion.start();
					facingRight = false;
			}
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_RIGHT ^ e.getKeyCode() == KeyEvent.VK_LEFT){
				animacion.stop();
			}
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
    	
    });
    this.add(pane);
	this.setSize(200,200);
}
}

