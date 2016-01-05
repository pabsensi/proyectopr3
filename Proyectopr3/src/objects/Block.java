package objects;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import framework.GameObject;
import framework.ObjectId;

/**
 * Clase bloque, de la que se compondrá el suelo y las plataformas
 * @author Pablosensi
 *
 */
public class Block extends GameObject {

	public Block(float x, float y, ObjectId id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}
	private void collision(ArrayList<GameObject> object){

		for(int i =0; i<object.size(); i++){
			GameObject TempObject = object.get(i);
		if(TempObject instanceof Bullet){
			if(this.getBounds().intersects(((Bullet) TempObject).getBounds())){
			object.remove(i);
		}
				
					
			}
		}
	}
	@Override
	public void tick(ArrayList<GameObject> object) {
		collision(object);
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawRect((int)x, (int)y, 32, 32);

	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x, (int)y, 32, 32);
	}

}
