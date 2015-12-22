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
	private boolean crouching = false;
	private Animator animador;
	private float gravity = 0.5f;
	private BufferedImage sprite;
	private int spriteWidth, spriteHeight;
	private ArrayList<BufferedImage> currentAnim = new ArrayList<>();
	private final String default_anim = "running_right";
	private HashMap<String, ArrayList<BufferedImage>> spriteHash = new HashMap<String, ArrayList<BufferedImage>>();
	
	public Enemigos(float x, float y, ObjectId id){
		super(x, y, id);
		facingRight = true;
		spriteHash = SpriteListCreator.SpriteHashCreator(new File("resources/Player"));
		currentAnim = spriteHash.get(default_anim);
		sprite = currentAnim.get(0);
		spriteHeight = sprite.getHeight();
		spriteWidth = sprite.getWidth();
		animador = new Animator(currentAnim);
	}
	private void Collision(ArrayList<GameObject> object){
		for(int i =0; i<object.size(); i++){
			GameObject TempObject = object.get(i);
			if(TempObject instanceof Block){
				if(this.getBounds().intersects(TempObject.getBounds())){
					falling =false;
					velY=0;
					if(facingRight)
						animador.setFrames(spriteHash.get("running_right"));
					else
						animador.setFrames(spriteHash.get("running_left"));
					y = TempObject.getY()-animador.sprite.getHeight();
					break;
				}
				else
					falling = true;
			}
		}
	}
	public void tick(ArrayList<GameObject> object) {
		System.out.println(falling);
		animador.update(System.currentTimeMillis());
		Collision(object);
		sprite = animador.sprite;
		// TODO Auto-generated method stub
		x+=velX;
		y+=velY;
		if(falling || jumping){
			velY+=gravity;
			if(facingRight)
				animador.setFrames(spriteHash.get("jump_right.png"));
			else
				animador.setFrames(spriteHash.get("jump_left.png"));
		}
		if(movingRight){
			animador.resume();
			if(jumping || falling)
			animador.setFrames(spriteHash.get("jump_right.png"));
			else if(crouching)
			animador.setFrames(spriteHash.get("crouch_right"));
			else
				animador.setFrames(spriteHash.get("running_right"));
			facingRight=true;
			velX= 5;
		}
		if(movingLeft){
			animador.resume();
			if(jumping || falling)
			animador.setFrames(spriteHash.get("jump_left.png"));
			else if(crouching)
			animador.setFrames(spriteHash.get("crouch_left"));
			else
				animador.setFrames(spriteHash.get("running_left"));
			facingRight = false;
			velX= -5;
		}
		if(!(movingRight || movingLeft)){
			animador.pause();
			velX =0;
		}
		if(jumping && !falling){
			y -= 3;
			velY = -7;
			jumping = false;
			falling = true;
		}
		if(crouching && !movingRight && !movingLeft){
			if(facingRight)
				animador.setFrames(spriteHash.get("crouch_right"));
			else
				animador.setFrames(spriteHash.get("crouch_left"));
		}
		if(!(movingRight||movingLeft||falling||jumping||crouching))
			if(facingRight)
				animador.setFrames(spriteHash.get("running_right"));
			else
				animador.setFrames(spriteHash.get("running_left"));
			
	}

	public void setCrouching(boolean crouching) {
		this.crouching = crouching;
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(sprite, (int)x, (int)y, null);
		Graphics2D g2d= (Graphics2D) g;
		g.setColor(Color.red);
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
