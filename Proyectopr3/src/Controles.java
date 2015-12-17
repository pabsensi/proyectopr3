
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import objects.Bullet;
import objects.Player;
import framework.GameObject;
import framework.ObjectId;
/**
 * Clase para los controles del juego, hija de KeyAdapter ya que
 *  hereda de ella los métodos de keypressed y keyreleased
 * @author Pablosensi
 *
 */
public class Controles extends KeyAdapter {
	private long time, previousTime = 0;
	Handler handler;
	public Controles(Handler handler) {
		this.handler = handler;
	}
	/**
	 * Método para el pulsado de teclas.
	 *  Recorre el array de objetos en pantalla y en cuanto encuentra el del jugador
	 *   ejecuta las instrucciones convenientes dependiendo de la tecla que pulse.
	 *   Si se pulsa el botón derecho, al objeto jugador se le cambiará MovingRight
	 *   a cierto y MovingLeft a falso, ejecutándose desde la clase jugador el conjunto
	 *   de instrucciones que hagan que se mueva hacia la derecha. Lo mismo ocurre si 
	 *   pulsamos el botón izquierdo, pero al revés. Si saltamos, hará que jumping sea true,
	 *   lo que permite que el jugador salte y lo mismo con crouch para agacharse.
	 */
	public void keyPressed(KeyEvent e){
		GameObject tempObject;
			for(int i=0; i<handler.objectlist.size(); i++){
				if(handler.objectlist.get(i) instanceof Player){
					tempObject = handler.objectlist.get(i);
					if(e.getKeyCode()== KeyEvent.VK_RIGHT){
					tempObject.setMovingRight(true);
					tempObject.setMovingLeft(false);
					}
				if(e.getKeyCode() == KeyEvent.VK_LEFT){
					tempObject.setMovingRight(false);
					tempObject.setMovingLeft(true);
					}
				if(e.getKeyCode() == KeyEvent.VK_SPACE)
					if(!tempObject.isFalling())
					tempObject.setJumping(true);
				if(e.getKeyCode() == KeyEvent.VK_Z){
					time = System.currentTimeMillis();
					if(time - previousTime >= 200){
					if(tempObject.isFacingRight())
						handler.addObject(new Bullet(tempObject.getX()+(tempObject.getBounds().width), tempObject.getY()+3, ObjectId.Bullet, handler.objectlist.get(i).isFacingRight()));
					else
						handler.addObject(new Bullet(tempObject.getX(), tempObject.getY()+3, ObjectId.Bullet, handler.objectlist.get(i).isFacingRight()));
					previousTime = time;
					}
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN){
					tempObject.setCrouching(true);
				}
				}
			}
	}
	/**
	 *   Una vez se sueltan las teclas que normalmente se mantienen pulsadas como las direccionales o 
	 *   agacharse, se vuelve a cambiar el estado boolean que habíamos modificado
	 *   en KeyPressed a su estado previo, o sea false.
	 */
	public void keyReleased(KeyEvent e){
		for(int i=0; i<handler.objectlist.size(); i++){
			if(handler.objectlist.get(i) instanceof Player){
				if(e.getKeyCode() == KeyEvent.VK_RIGHT)
					handler.objectlist.get(i).setMovingRight(false);
				if(e.getKeyCode() == KeyEvent.VK_LEFT)
					handler.objectlist.get(i).setMovingLeft(false);
				if(e.getKeyCode() == KeyEvent.VK_DOWN)
					handler.objectlist.get(i).setCrouching(false);
		}
		}
	}
}
