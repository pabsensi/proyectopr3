
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

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
	private int Left =KeyEvent.VK_LEFT, Right = KeyEvent.VK_RIGHT, Crouch= KeyEvent.VK_DOWN, Jump = KeyEvent.VK_SPACE, Shoot = KeyEvent.VK_Z;
	private long time, previousTime = 0;
	Handler handler;
	public Controles(Handler handler){
		ResultSet rs;
		try {
			rs= BaseDeDatos.getStatement().executeQuery("select tecla from basecontroles where boton='izquierda'");
		
			Left = rs.getInt(1);
		rs= BaseDeDatos.getStatement().executeQuery("select tecla from basecontroles where boton='derecha'");
			Right = rs.getInt(1);
		rs= BaseDeDatos.getStatement().executeQuery("select tecla from basecontroles where boton='agachar'");
			Crouch = rs.getInt(1);
		rs= BaseDeDatos.getStatement().executeQuery("select tecla from basecontroles where boton='saltar'");
			Jump = rs.getInt(1);
		rs= BaseDeDatos.getStatement().executeQuery("select tecla from basecontroles where boton='saltar'");
			Shoot = rs.getInt(1);
		} catch (SQLException | java.lang.NullPointerException e) {
			Logger.getLogger("ERROR EN LA BASE DE DATOS");
			e.printStackTrace();
		}
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
					if(e.getKeyCode()== Right){
					tempObject.setMovingRight(true);
					tempObject.setMovingLeft(false);
					}
				if(e.getKeyCode() == Left){
					tempObject.setMovingRight(false);
					tempObject.setMovingLeft(true);
					}
				if(e.getKeyCode() == Jump)
					if(!tempObject.isFalling())
					tempObject.setJumping(true);
				if(e.getKeyCode() == Shoot){
					time = System.currentTimeMillis();
					if(time - previousTime >= 200){
					if(tempObject.isFacingRight())
						handler.addObject(new Bullet(tempObject.getX()+(tempObject.getBounds().width), tempObject.getY()+3, ObjectId.Bullet, handler.objectlist.get(i).isFacingRight()));
					else
						handler.addObject(new Bullet(tempObject.getX(), tempObject.getY()+3, ObjectId.Bullet, handler.objectlist.get(i).isFacingRight()));
					previousTime = time;
					}
				}
				if(e.getKeyCode() == Crouch){
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
