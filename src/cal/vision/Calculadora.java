package cal.vision;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Calculadora extends JFrame {
	
	public Calculadora() {
		
		organizarLayout();
		
		setSize(232, 322);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void organizarLayout() {
		setLayout(new BorderLayout());
		
		Display display = new Display();
		display.setPreferredSize(new Dimension(233, 60));//largura X altura
		add(display, BorderLayout.NORTH);//Definindo a posição do display no topo
		
		Teclado teclado = new Teclado();
		add(teclado, BorderLayout.CENTER);//Definindo a posição do display no  centro
	}

	public static void main(String[] args) {
		new Calculadora();
	}

}
