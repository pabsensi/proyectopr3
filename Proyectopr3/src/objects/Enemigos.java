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

public class Enemigos extends GameObject {
	private boolean attack = false;
	private Animator animador;
	private float gravity = 0.5f;
	private BufferedImage sprite;
	private int spriteWidth, spriteHeight;
	private ArrayList<BufferedImage> currentAnim2 = new ArrayList<>();
	private final String default_anim = "idle_right";
	private HashMap<String, ArrayList<BufferedImage>> spriteHash = new HashMap<String, ArrayList<BufferedImage>>();
	
	public Enemigos(float x, float y, ObjectId id){
		super(x, y, id);
		facingRight = true;
		//meter el fichero con los sprites del enemigo
		spriteHash = SpriteListCreator.SpriteHashCreator(new File("resources/Enemy1"));
		currentAnim2 = spriteHash.get(default_anim);
		sprite = currentAnim2.get(0);
		spriteHeight = sprite.getHeight();
		spriteWidth = sprite.getWidth();
		animador = new Animator(currentAnim2);
	}
	private void Collision(ArrayList<GameObject> object){
		for(int i =0; i<object.size(); i++){
			GameObject TempObject = object.get(i);
			if(TempObject instanceof Block){
				if(this.getBounds().intersects(TempObject.getBounds())){
					falling =false;
					velY=0;
					if(facingRight)
						animador.setFrames(spriteHash.get("walking_right"));
					else
						animador.setFrames(spriteHash.get("walking_left"));
					y = TempObject.getY()-animador.sprite.getHeight();
					break;
				}
				else
					falling = true;
			}
		}
		for(int i =0; i<object.size(); i++){
			GameObject TempObject = object.get(i);
			if(TempObject instanceof Player){
				if(this.getBoundsRight().intersects(((Player) TempObject).getBounds()) || this.getBoundsLeft().intersects(((Player) TempObject).getBounds())){
					attack = true;
					movingRight = false;
					movingLeft = false;
				}
					
				else
					attack = false;
			}
		}
	}
	public void tick(ArrayList<GameObject> object)  {
		for(int i =0; i<object.size(); i++){
			GameObject TempObject = object.get(i);
			if(TempObject instanceof Player){
				
				if(TempObject.getX() - this.getX() < 0){
					movingRight = false;
					movingLeft = true;
				}
				else if(TempObject.getX() - this.getX() > 0){
					movingLeft = false;
					movingRight = true;
			}
					else{
						movingRight = false;
						movingLeft = false;
				}
		}
		}
		System.out.println(falling);
		animador.update(System.currentTimeMillis());
		Collision(object);
		sprite = animador.sprite;
		// TODO Auto-generated method stub
		x+=velX;
		y+=velY;
		if(attack){
			animador.resume();
			if(facingRight)
			animador.setFrames(spriteHash.get("attack_right"));
			else
				animador.setFrames(spriteHash.get("attack_left"));
		}
		if(falling || jumping){
			velY+=gravity;
			if(facingRight)
				animador.setFrames(spriteHash.get("jumping.png"));
			else
				animador.setFrames(spriteHash.get("jumping.png"));
		}
		if(movingRight){
			animador.resume();
			if(jumping || falling)
			animador.setFrames(spriteHash.get("jumping.png"));
			else
				animador.setFrames(spriteHash.get("walking_right"));
			facingRight=true;
			velX= 1;
		}
		if(movingLeft){
			animador.resume();
			if(jumping || falling)
			animador.setFrames(spriteHash.get("jumping.png"));
			else
				animador.setFrames(spriteHash.get("walking_left"));
			facingRight = false;
			velX= -1;
		}
		if(!(movingRight || movingLeft)){
			velX =0;
		}
		if(jumping && !falling){
			y -= 3;
			velY = -7;
			jumping = false;
			falling = true;
		}
			
	}


	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(sprite, (int)x, (int)y, null);
		Graphics2D g2d= (Graphics2D) g;
		g.setColor(Color.yellow);
		g2d.draw(getBounds());
		g2d.draw(getBoundsTop());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsRight());
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x+(sprite.getWidth()/2)-(sprite.getWidth()/4),(int) y + (sprite.getHeight()/2), sprite.getWidth()/2, sprite.getHeight()/2 +2);
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
