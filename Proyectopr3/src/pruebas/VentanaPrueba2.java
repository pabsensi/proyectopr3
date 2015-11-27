package pruebas;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaPrueba2 extends JPanel {
	 static Integer[] controles = {KeyEvent.VK_LEFT , KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_SPACE};
	 static List<Integer> listaTeclas= Arrays.asList(controles);
	boolean[] teclasPulsadas;
	public VentanaPrueba2(){
		Thread runner = new Thread(new TecladoRunner());
		teclasPulsadas = new boolean[controles.length];
		runner.start();
	}
	
	public static void main(String[] args) {
		VentanaPrueba2 ventana = new VentanaPrueba2();
		JFrame f = new JFrame();
		f.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				if(listaTeclas.contains(e.getKeyCode())){
					System.out.println(e.getKeyCode());
					
					ventana.teclasPulsadas[listaTeclas.indexOf(e.getKeyCode())] = true;
					e.consume();
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

				if(listaTeclas.contains(e.getKeyCode())){
					ventana.teclasPulsadas[listaTeclas.indexOf(e.getKeyCode())] = false;
				}
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		f.add(ventana);
		f.setVisible(true);
		
	}


public class TecladoRunner implements Runnable{
	TecladoRunner(){}
	boolean sigo = true;

	@Override
	public void run() {
		while(sigo){
		if(teclasPulsadas[listaTeclas.indexOf(KeyEvent.VK_RIGHT)]) System.out.println("derecha");
		if(teclasPulsadas[listaTeclas.indexOf(KeyEvent.VK_LEFT)]) System.out.println("izquierda");
		if(teclasPulsadas[listaTeclas.indexOf(KeyEvent.VK_SPACE)])System.out.println("saltar");
		}
	}
}
}

