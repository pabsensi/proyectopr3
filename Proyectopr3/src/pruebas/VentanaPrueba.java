package pruebas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

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
	boolean jumped;
	boolean facingRight = true;
	public VentanaPrueba(){
	final String defaultanim = "running_right";
	spriteHash = SpriteListCreator.SpriteHashCreator(new File("resources/player"));
	Animator animacion = new Animator(spriteHash.get(defaultanim));
	animacion.setSpeed(75);
	JPanel pane = new JPanel(){
		@Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);
            g.drawImage(animacion.sprite, 0, 0, null);

			animacion.update(System.currentTimeMillis());
			setSize(animacion.sprite.getWidth(), animacion.sprite.getHeight());
            repaint();
        }
    };
    pane.setBorder(BorderFactory.createLineBorder(Color.red));
    addKeyListener(new KeyListener(){

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				if(!facingRight || jumped){
					pane.setLocation(pane.getX()+5, pane.getY());
					animacion.frames = spriteHash.get("running_right");
					facingRight = true;
					animacion.start();
					jumped = false;
					
					e.consume();
				}
				else if(!animacion.running){
					pane.setLocation(pane.getX()+5, pane.getY());
					animacion.start();
					facingRight = true;
					e.consume();

				}else{
					pane.setLocation(pane.getX()+5, pane.getY());}
			}
			else if(e.getKeyCode() == KeyEvent.VK_LEFT){
				if(facingRight || jumped){
					pane.setLocation(pane.getX()-5, pane.getY());
					animacion.frames = spriteHash.get("running_left");
					facingRight = false;
					animacion.start();
					jumped = false;
					e.consume();
			}
				else if(!animacion.running){

					pane.setLocation(pane.getX()-5, pane.getY());
					animacion.start();
					facingRight = false;

					e.consume();
					
			}else{
				pane.setLocation(pane.getX()-5, pane.getY());}
			}
			else if(e.getKeyCode() == KeyEvent.VK_SPACE){
				if(facingRight){
				 animacion.frames = spriteHash.get("jump_right");
				 animacion.jump();
				 e.consume();
				 try {
					Thread.sleep(75);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 jumped = true;
				}
				else{
					animacion.frames = spriteHash.get("jump_left");
					animacion.jump();
					e.consume();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					jumped = true;
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

