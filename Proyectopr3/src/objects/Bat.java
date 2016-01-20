package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import pruebas.Animator;
import sprite.player.SpriteListCreator;
import framework.GameObject;
import framework.ObjectId;

public class Bat extends Enemy {
	long tiempoAtaque = 0;
	private boolean up = false, down = false;
	private float firstY;
	private Animator animador;
	private BufferedImage sprite;
	private int spriteHeight;
	private ArrayList<BufferedImage> currentAnim = new ArrayList<>();
	private final String default_anim = "flying";
	private HashMap<String, ArrayList<BufferedImage>> spriteHash = new HashMap<String, ArrayList<BufferedImage>>();

	public Bat(float x, float y, ObjectId id){
		super(x, y, id);
		vida= 1;
		firstY = y;
		facingRight = true;
		//meter el fichero con los sprites del enemigo
		spriteHash = SpriteListCreator.SpriteHashCreator(new File("resources/Bat"));
		currentAnim = spriteHash.get(default_anim);
		sprite = currentAnim.get(0);
		spriteHeight = sprite.getHeight();
		animador = new Animator(currentAnim);
	}
	private void Collision(ArrayList<GameObject> object){
		for(int i =0; i<object.size(); i++){
			GameObject TempObject = object.get(i);
		if(TempObject instanceof Bullet){
			if(this.getBoundsRight().intersects( TempObject.getBounds()) || this.getBoundsLeft().intersects(TempObject.getBounds())){
				object.remove(i);
		}
			}
		if(TempObject instanceof Player){
			if(this.getBounds().intersects(TempObject.getBounds())){
				if(System.currentTimeMillis() - tiempoAtaque >= 1000){
				((Player) TempObject).setVida();
				tiempoAtaque = System.currentTimeMillis();
				}
			}
		}
		}
	}
	public void tick(ArrayList<GameObject> object)  {
		for(int i =0; i<object.size(); i++){
			GameObject TempObject = object.get(i);
			if(TempObject instanceof Player){

				if( this.getX() - TempObject.getX() < 600 && this.getX() - TempObject.getX() > 0){
					movingLeft = true;
				}
			}
			
		}if(vida<=0)
		for(int i =0; i<object.size(); i++){
			GameObject TempObject = object.get(i);

			if(TempObject instanceof Player){
				((Player)TempObject).setPuntuacion(10);
			}
			if(TempObject.equals(this)){
				object.remove(i);
			}
		}
		
		animador.update(System.currentTimeMillis());
		Collision(object);
		sprite = animador.sprite;
		// TODO Auto-generated method stub
		x+=velX;
		y+=velY;
		if(movingLeft){
			velX=-7;
			if(up)
				y+=5;
			else if(down)
				y-=5;
		}
		if(y-firstY==0){
			up = true;
			down = false;
		}
		else if (y-firstY == 70){
			down = true;
			up = false;
		}
	}


	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(sprite, (int)x, (int)y, null);
	
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x,(int) y , 75, 75);
	}
	public Rectangle getBoundsTop(){
		return new Rectangle((int)x+(sprite.getWidth()/2)-(sprite.getWidth()/4),(int) y, sprite.getWidth()/2, spriteHeight/2);

	}
	public Rectangle getBoundsRight(){
		return new Rectangle((int)x+sprite.getWidth()-5,(int) y+5, 5, sprite.getHeight()-10);
	}
	public Rectangle getBoundsLeft(){
		return new Rectangle((int)x,(int) y+5, 5, sprite.getHeight()-10);
	}

}
