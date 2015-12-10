
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
			for(GameObject object : handler.objectlist){
				if(object instanceof Player){
					if(key== KeyEvent.VK_RIGHT){
					object.setMovingRight(true);
					object.setMovingLeft(false);
					}
				if(e.getKeyCode() == KeyEvent.VK_LEFT){
					object.setMovingRight(false);
					object.setMovingLeft(true);
					}
			}
			}
	}
	public void keyReleased(KeyEvent e){
		for(GameObject object : handler.objectlist){
			if(object instanceof Player){
				if(e.getKeyCode() == KeyEvent.VK_RIGHT)
				object.setMovingRight(false);
				if(e.getKeyCode() == KeyEvent.VK_LEFT)
				object.setMovingLeft(false);
		}
		}
	}
}
