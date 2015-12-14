
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import objects.Bullet;
import objects.Player;
import framework.GameObject;
import framework.ObjectId;

public class Controles extends KeyAdapter {
	Handler handler;
	public Controles(Handler handler) {
		this.handler = handler;
	}
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		GameObject tempObject;
			for(int i=0; i<handler.objectlist.size(); i++){
				if(handler.objectlist.get(i) instanceof Player){
					tempObject = handler.objectlist.get(i);
					if(key== KeyEvent.VK_RIGHT){
					tempObject.setMovingRight(true);
					tempObject.setMovingLeft(false);
					}
				if(e.getKeyCode() == KeyEvent.VK_LEFT){
					tempObject.setMovingRight(false);
					tempObject.setMovingLeft(true);
					}
				if(e.getKeyCode() == KeyEvent.VK_SPACE)
					tempObject.setJumping(true);
				if(e.getKeyCode() == KeyEvent.VK_Z)
					if(tempObject.isFacingRight())
					handler.addObject(new Bullet(tempObject.getX()+(tempObject.getBounds().width), tempObject.getY()+3, ObjectId.Bullet, handler.objectlist.get(i).isFacingRight()));
					else
						handler.addObject(new Bullet(tempObject.getX(), tempObject.getY()+3, ObjectId.Bullet, handler.objectlist.get(i).isFacingRight()));
				}
			}
	}
	public void keyReleased(KeyEvent e){
		for(int i=0; i<handler.objectlist.size(); i++){
			if(handler.objectlist.get(i) instanceof Player){
				if(e.getKeyCode() == KeyEvent.VK_RIGHT)
					handler.objectlist.get(i).setMovingRight(false);
				if(e.getKeyCode() == KeyEvent.VK_LEFT)
					handler.objectlist.get(i).setMovingLeft(false);
		}
		}
	}
}
