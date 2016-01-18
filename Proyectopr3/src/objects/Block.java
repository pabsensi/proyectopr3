package objects;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import sprite.player.SpriteListCreator;
import framework.GameObject;
import framework.ObjectId;

/**
 * Clase bloque, de la que se compondrá el suelo y las plataformas
 * @author Pablosensi
 *
 */
public class Block extends GameObject {
	private static HashMap<String, ArrayList<BufferedImage>> textures = new HashMap<String, ArrayList<BufferedImage>>();
	static{
			textures = SpriteListCreator.SpriteHashCreator(new File("resources\\terrain"));
		
	}
	public Block(float x, float y, ObjectId id, String tex) {
		super(x, y, id);
		sprite = textures.get(tex).get(0);
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
		g.drawImage(sprite, (int) x, (int) y, null);
		//g.setColor(Color.green);
		//g.fillRect((int)x, (int)y, 32, 32);
		//g.drawRect((int)x, (int)y, 32, 32);

	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x, (int)y, 32, 32);
	}

}
