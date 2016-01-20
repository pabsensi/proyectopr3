package objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import pruebas.Animator;
import sprite.player.SpriteListCreator;
import framework.GameObject;
import framework.ObjectId;
/**
 * Clase Jugador: \n
 * clase que contiene al jugador
 * @author Pablosensi
 *
 */
public class Player extends GameObject {

	private int puntuacion = 0;
	public int getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(int puntos) {
		this.puntuacion += puntos;
	}
	int vida = 5;
	public int getVida() {
		return vida;
	}
	public void setVida() {
		this.vida -= 1;
	}
	int numGranadas = 4;
	public int getNumGranadas() {
		return numGranadas;
	}
	public void setNumGranadas() {
		this.numGranadas -= 1;
	}
	private BufferedImage corazon;
	private boolean crouching = false;
	boolean horizontalcollision=false;
	private Animator animador;
	private float gravity = 0.5f;
	private BufferedImage sprite;
	private int spriteHeight;
	private ArrayList<BufferedImage> currentAnim = new ArrayList<>();
	private final String default_anim = "running_right";
	private HashMap<String, ArrayList<BufferedImage>> spriteHash = new HashMap<String, ArrayList<BufferedImage>>();
	/**
	 * Constructor del jugador:\n
	 * aquí se crea al jugador. Siempre se inicializa mirando hacia la derecha.
	 *  Se carga primero un spriteHash conteniendo todas las animaciones del jugador y
	 *  se inicializa el animador con el arraylist conteniendo las animaciones de mirando
	 *  a la derecha, pero al empezar cayendo inmediatamente cambia al sprite de caída.
	 * @param x
	 * @param y
	 * @param id
	 */
	public Player(float x, float y, ObjectId id) {
		super(x, y, id);
		try {
			corazon = ImageIO.read(new File("resources/corazon.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		facingRight = true;
		spriteHash = SpriteListCreator.SpriteHashCreator(new File("resources/Player"));
		currentAnim = spriteHash.get(default_anim);
		sprite = currentAnim.get(0);
		spriteHeight = sprite.getHeight();
		animador = new Animator(currentAnim);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Método de colisión para cuando un objeto de pantalla choca con el jugador.
	 *  Si choca con un bloque, ello significa que ha caído y por tanto el boolean
	 *   de falling se vuelve false. Dependiendo de si está mirando hacia la izquierda
	 *    o la derecha, el arraylist que se le envíe será mirando hacia la izquierda o a
	 *    la derecha respectivamente.
	 * @param object
	 */
	private void Collision(ArrayList<GameObject> object){
		for(int i =0; i<object.size(); i++){
			GameObject TempObject = object.get(i);
			if(TempObject instanceof Block){
				
				if(getBoundsTop().intersects(TempObject.getBounds())){
					y = TempObject.getY() + 34;
					setvelY(0);
				}
				
				if(getBounds().intersects(TempObject.getBounds())){
					falling =false;
					velY=0;
					if(facingRight)
						animador.setFrames(spriteHash.get("running_right"));
					else
						animador.setFrames(spriteHash.get("running_left"));
					y = TempObject.getY()-animador.sprite.getHeight();
					break;
				}else
					falling = true;
				
			}
			}

		for(int i =0; i<object.size(); i++){
			GameObject TempObject = object.get(i);
			if(TempObject instanceof Block){
				//RIGHT
				if(getBoundsRight().intersects(TempObject.getBounds())){
					x = TempObject.getX() - sprite.getWidth();
				}
				//LEFT
				if(getBoundsLeft().intersects(TempObject.getBounds())){
					x = TempObject.getX() + sprite.getWidth();
				}
			}
		}
	}
	@Override
	/**
	 * Método que se ejecuta desde la clase Juego 60 veces por segundo (el número de fps).
	 * \n Desde aquí se ejecutan los métodos de colisión y se controla la velocidad del jugador dependiendo
	 *  de si está cayendo/saltando (si es así se modifica la velocidad Y)
	 *  o si está corriendo (si es así se modifica la velocidad X).
	 *   Además de la velocidad, se modifica el arraylist que se pasa al animador
	 *   según la instrucción que le estemos mandando (saltar, andar agachado, correr...)
	 */
	public void tick(ArrayList<GameObject> object) {
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
		if(y>600)
			vida=0;
		}
			
			

	public void setCrouching(boolean crouching) {
		this.crouching = crouching;
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(sprite, (int)x, (int)y, null);
		for(int i=0; i<vida*85; i+=85)
			g.drawImage(corazon,  i+ (int)x-300, 0, null);
		g.setColor(Color.black);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString("Puntuacion: " + puntuacion, (int) x + 200, 30);
//		Graphics2D g2d= (Graphics2D) g;
//		g.setColor(Color.red);
//		g2d.draw(getBounds());
//		g2d.draw(getBoundsTop());
//		g2d.draw(getBoundsLeft());
//		g2d.draw(getBoundsRight());
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
		return new Rectangle((int)x+sprite.getWidth()-5,(int) y+5, 5, sprite.getHeight()-25);
	}
	public Rectangle getBoundsLeft(){
		return new Rectangle((int)x,(int) y+5, 5, sprite.getHeight()-25);
	}
}
