package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import pruebas.Animator;
import sprite.player.SpriteListCreator;
import framework.GameObject;
import framework.ObjectId;

public class Grenade extends Projectile {
	private int danyo = 4;
	public int getDaño() {
		return danyo;
	}

	long tiempoExplosion;
	boolean explode = false;
	private HashMap<String, ArrayList<BufferedImage>> spriteHash = new HashMap<String, ArrayList<BufferedImage>>();
	private float gravity = 0.5f;
	Animator animador;
	public Grenade(float x, float y, ObjectId id, boolean right, float vel) {
		super(x, y, id);
		danyo=4;
		spriteHash = SpriteListCreator.SpriteHashCreator(new File("resources/Grenade"));
		sprite = spriteHash.get("grenade.png").get(0);
		animador = new Animator(spriteHash.get("grenade.png"));
		// TODO Auto-generated constructor stub
		velY=-9;
		if(right)
			velX = vel+ 7;
		else
			velX= vel -7 ;
	}
	

	private void Collision(ArrayList<GameObject> object){
		for(int i =0; i<object.size(); i++){
			GameObject TempObject = object.get(i);
			if(TempObject.getId()== ObjectId.Enemy){
				if((getBounds().intersects(((Enemy) TempObject).getBoundsTop())) || getBounds().intersects(((Enemy) TempObject).getBounds()) && !explode){
					tiempoExplosion = System.currentTimeMillis();
					y = TempObject.getY()-45;		
					explode = true;
					animador.setFrames(spriteHash.get("explosion"));
					velY = 0;
					((Enemy)TempObject).setVida(danyo);
				}
			}
				else if(TempObject instanceof Block){
					if(getBounds().intersects(TempObject.getBounds())&& !explode){
					tiempoExplosion = System.currentTimeMillis();
					explode = true;
					animador.setFrames(spriteHash.get("explosion"));
					velY = 0;
					y = TempObject.getY()-95;
					}
				}
					
			}
			}
	@Override
	public void tick(ArrayList<GameObject> objectlist) {
		// TODO Auto-generated method stub
		Collision(objectlist);
		if(explode){
			sprite = animador.sprite;
			velY= 0;
			velX= 0;
			animador.update(System.currentTimeMillis());
			if(System.currentTimeMillis() - tiempoExplosion >= 500){
				for(int i =0; i<objectlist.size(); i++){
					GameObject TempObject = objectlist.get(i);
					if(TempObject.equals(this)){
						objectlist.remove(i);
					}
				}
			}
		}
		x+=velX;
		y+=velY;
		velY+= gravity;
		

	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(sprite, (int)x, (int)y, null);

	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x, (int)y, 5,5);
	}

}
