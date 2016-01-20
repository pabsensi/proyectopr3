package niveles;

import juego.Juego;
import objects.Bat;
import objects.Block;
import objects.Crusher;
import framework.Handler;
import framework.ObjectId;

public class Nivel2 {
	
	public static int createLevel(Handler handler) {

		handler.addObject(new Crusher(600, 200, ObjectId.Enemy));
		handler.addObject(new Crusher(1000, 200, ObjectId.Enemy));
		handler.addObject(new Crusher(1800, 200, ObjectId.Enemy));
		handler.addObject(new Crusher(2000, 200, ObjectId.Enemy));
		handler.addObject(new Crusher(2500, 200, ObjectId.Enemy));
		for(int i=4000; i<5500; i+=100 )
			handler.addObject(new Bat(i, 400, ObjectId.Enemy));
		for(int i =0; i<32000 ; i += 32)
		handler.addObject(new Block(i, Juego.HEIGHT-20, ObjectId.Block, "grass.png"));
		for(int i=Juego.WIDTH/3;i< (int)(Juego.WIDTH*2)/3;i+=32){
			
			handler.addObject(new Block(i+1200,Juego.HEIGHT-295,ObjectId.Block,"rock.png"));;
			handler.addObject(new Block(i+1000,Juego.HEIGHT-250,ObjectId.Block,"rock.png"));
			handler.addObject(new Block(i+1600,Juego.HEIGHT-90,ObjectId.Block,"rock.png"));
			handler.addObject(new Block(i+800,Juego.HEIGHT-205,ObjectId.Block,"rock.png"));
			handler.addObject(new Block(i+600,Juego.HEIGHT-160,ObjectId.Block,"grass.png"));
			handler.addObject(new Block(i+400,Juego.HEIGHT-90,ObjectId.Block,"grass.png"));
			handler.addObject(new Block(i+400, Juego.HEIGHT+100,ObjectId.Block,"grass.png"));
			//izquierda//
			handler.addObject(new Block(i+1800, Juego.HEIGHT-160,ObjectId.Block,"rock.png"));
			handler.addObject(new Block(i+2000, Juego.HEIGHT-210,ObjectId.Block,"rock.png"));
			handler.addObject(new Block(i+2400, Juego.HEIGHT-130,ObjectId.Block,"rock.png"));
			handler.addObject(new Block(i+2700, Juego.HEIGHT-110,ObjectId.Block,"rock.png"));
			handler.addObject(new Block(i+2900, Juego.HEIGHT-140,ObjectId.Block,"rock.png"));
			handler.addObject(new Block(i+3100, Juego.HEIGHT-175,ObjectId.Block,"rock.png"));
			
		}

		return 6000;
	}

}
