package niveles;

import juego.Juego;
import objects.Block;
import framework.Handler;
import framework.ObjectId;

public class Nivel1 {

	public static int createLevel1(Handler handler) {
		// TODO Auto-generated constructor stub

		for(int i =0; i<3200 ; i += 32)
		handler.addObject(new Block(i, Juego.HEIGHT-32, ObjectId.Block, "grass.png"));
		for(int i=Juego.WIDTH/3;i< (int)(Juego.WIDTH*2)/3;i+=32){ 
		handler.addObject(new Block(i+87, Juego.HEIGHT-100,ObjectId.Block, "grass.png"));
		handler.addObject(new Block(i+87, Juego.HEIGHT-50,ObjectId.Block, "grass.png"));
		handler.addObject(new Block(i+200,Juego.HEIGHT-125,ObjectId.Block, "grass.png"));
		handler.addObject(new Block(i+400, Juego.HEIGHT+100,ObjectId.Block, "grass.png"));
		handler.addObject(new Block(i-200, Juego.HEIGHT-64,ObjectId.Block, "grass.png"));
		handler.addObject(new Block(i-600, Juego.HEIGHT-32,ObjectId.Block, "grass.png"));
		handler.addObject(new Block(i+600, Juego.HEIGHT-64,ObjectId.Block, "grass.png"));
		handler.addObject(new Block(i+750, Juego.HEIGHT-64,ObjectId.Block, "grass.png"));
		handler.addObject(new Block(i+850, Juego.HEIGHT-90,ObjectId.Block, "grass.png"));
		handler.addObject(new Block(i+850, Juego.HEIGHT-64,ObjectId.Block, "grass.png"));
		handler.addObject(new Block(i+900, Juego.HEIGHT-90,ObjectId.Block, "grass.png"));
//		handler.addObject(new Block(i+12000, Juego.HEIGHT-64,ObjectId.Block2));
		}
		return 3000;
	}

}
