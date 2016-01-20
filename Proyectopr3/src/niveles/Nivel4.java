package niveles;

import juego.Juego;
import objects.Bat;
import objects.Block;
import objects.Crusher;
import framework.Handler;
import framework.ObjectId;

public class Nivel4 {
	
	public static int createLevel(Handler handler) {
		for(int i=0; i<10000; i=i+50)
			handler.addObject(new Bat(800+i, 400, ObjectId.Enemy));
		for(int i=0; i<25; i++)
		handler.addObject(new Crusher(4000+i, 200, ObjectId.Enemy));
		
		
		for(int i =0; i<32000 ; i += 32)
		handler.addObject(new Block(i, Juego.HEIGHT-20, ObjectId.Block, "rock.png"));
		for(int i=Juego.WIDTH/3;i< (int)(Juego.WIDTH*2)/3;i+=32){
			
			handler.addObject(new Block(i+90,Juego.HEIGHT-50,ObjectId.Block,"rock.png"));;
			handler.addObject(new Block(i+150,Juego.HEIGHT-82,ObjectId.Block,"rock.png"));;
			handler.addObject(new Block(i+220,Juego.HEIGHT-50,ObjectId.Block,"rock.png"));;
			handler.addObject(new Block(i+1000,Juego.HEIGHT-100,ObjectId.Block,"wood.png"));;
			handler.addObject(new Block(i+800,Juego.HEIGHT-50,ObjectId.Block,"wood.png"));;
			handler.addObject(new Block(i+1200,Juego.HEIGHT-150,ObjectId.Block,"wood.png"));;
			handler.addObject(new Block(i+1600,Juego.HEIGHT-100,ObjectId.Block,"wood.png"));;
			handler.addObject(new Block(i+1600,Juego.HEIGHT-132,ObjectId.Block,"wood.png"));;
			handler.addObject(new Block(i+2000,Juego.HEIGHT-50,ObjectId.Block,"rock.png"));;
			handler.addObject(new Block(i+2050,Juego.HEIGHT-82,ObjectId.Block,"rock.png"));;
			handler.addObject(new Block(i+2090,Juego.HEIGHT-50,ObjectId.Block,"rock.png"));;
			handler.addObject(new Block(i+2370,Juego.HEIGHT-150,ObjectId.Block,"wood.png"));;
			handler.addObject(new Block(i+2450,Juego.HEIGHT-250,ObjectId.Block,"wood.png"));;
			handler.addObject(new Block(i+2600,Juego.HEIGHT-250,ObjectId.Block,"wood.png"));;
			handler.addObject(new Block(i+2600,Juego.HEIGHT-150,ObjectId.Block,"wood.png"));;
			handler.addObject(new Block(i+2800,Juego.HEIGHT-250,ObjectId.Block,"wood.png"));;
			handler.addObject(new Block(i+2800,Juego.HEIGHT-150,ObjectId.Block,"wood.png"));;
			handler.addObject(new Block(i+3100,Juego.HEIGHT-50,ObjectId.Block,"sand.png"));;
			handler.addObject(new Block(i+3150,Juego.HEIGHT-82,ObjectId.Block,"sand.png"));;
			handler.addObject(new Block(i+3190,Juego.HEIGHT-50,ObjectId.Block,"sand.png"));;
			handler.addObject(new Block(i+3250,Juego.HEIGHT-50,ObjectId.Block,"sand.png"));;
			handler.addObject(new Block(i+3250,Juego.HEIGHT-82,ObjectId.Block,"sand.png"));;
			handler.addObject(new Block(i+3400,Juego.HEIGHT-50,ObjectId.Block,"sand.png"));;
			handler.addObject(new Block(i+3400,Juego.HEIGHT-82,ObjectId.Block,"sand.png"));;
			handler.addObject(new Block(i+3500,Juego.HEIGHT-50,ObjectId.Block,"sand.png"));;
			//izquierda//
			
		}
		return 6000;
	}

	
}
