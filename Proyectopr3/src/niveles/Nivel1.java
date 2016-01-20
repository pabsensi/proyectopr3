package niveles;

import juego.Juego;
import objects.Bat;
import objects.Block;
import objects.Crusher;
import framework.Handler;
import framework.ObjectId;

public class Nivel1 {

	public static int createLevel(Handler handler) {
		// TODO Auto-generated constructor stub

		handler.addObject(new Crusher(600, 200, ObjectId.Enemy));
		handler.addObject(new Crusher(1000, 200, ObjectId.Enemy));
		handler.addObject(new Crusher(1800, 200, ObjectId.Enemy));
		handler.addObject(new Crusher(2000, 200, ObjectId.Enemy));
		handler.addObject(new Crusher(2500, 200, ObjectId.Enemy));
		for(int i=2600; i<4000; i+=100 )
			handler.addObject(new Bat(i, 400, ObjectId.Enemy));
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
