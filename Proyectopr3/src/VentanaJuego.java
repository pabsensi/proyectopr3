import java.awt.Dimension;

import javax.swing.JFrame;


public class VentanaJuego {
	public VentanaJuego(int w, int h, String title, Juego juego){
		juego.setPreferredSize(new Dimension(w,h));
		juego.setMaximumSize(new Dimension(w, h));
		juego.setMinimumSize(new Dimension(w, h));
		
		JFrame frame = new JFrame(title);
		frame.add(juego);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		juego.start();
	}
}
