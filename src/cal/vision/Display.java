package cal.vision;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import cal.model.Memoria;
import cal.model.MemoriaObserver;

@SuppressWarnings("serial")
public class Display extends JPanel implements MemoriaObserver {
	
	private final JLabel label;
	
	public Display() {
		
		Memoria.getInstacia().adicionarObservador(this);
		
		setBackground(new Color(49, 49, 49));
		label  = new JLabel(Memoria.getInstacia().getTextoAtual());
		label.setForeground(Color.WHITE);
		label.setFont(new Font("courier", Font.PLAIN, 30));
		
		setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 25));//definindo o posicionamento
		
		add(label);
		
	}
	
	@Override
	public void valorAlterado(String novoValor) {
		label.setText(novoValor);
		
	}

}
