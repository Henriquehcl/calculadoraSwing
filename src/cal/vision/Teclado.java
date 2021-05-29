package cal.vision;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import cal.model.Memoria;

@SuppressWarnings("serial")
public class Teclado extends JPanel implements ActionListener {

	private final Color CINZA_ESCURO = new Color(68, 68, 68);
	private final Color CINZA_CLARO = new Color(99, 99, 99);
	private final Color LARANJA = new Color(242, 163, 60);

	public Teclado() {

		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		setLayout(layout);
		
		c.weightx = 1;//ocupando toda a janela no eixo x - occupying the entire window on the X axis
		c.weighty = 1;//ocupando toda a janela no eixo Y - occupying the entire window on the Y axis
		c.fill = GridBagConstraints.BOTH;//removendo espaço em branco entre botões - removing white space between buttons

		// primeira linha do teclado - first line with keyboard
		c.gridwidth = 2;//usand0 o espaço de três botões - using the three buttons space
		adicionarBotao("AC", CINZA_ESCURO, c, 0, 0);
		c.gridwidth = 1;//voltando para usar o espaço normal - coming back to use normal space
		adicionarBotao("±", CINZA_ESCURO, c, 2, 0);
		adicionarBotao("/", LARANJA, c, 3, 0);

		// Segunda linha - second line
		adicionarBotao("7", CINZA_CLARO, c, 0, 1);
		adicionarBotao("8", CINZA_CLARO, c, 1, 1);
		adicionarBotao("9", CINZA_CLARO, c, 2, 1);
		adicionarBotao("*", LARANJA, c, 3, 1);

		// terceira linha - third line
		adicionarBotao("4", CINZA_CLARO, c, 0, 2);
		adicionarBotao("5", CINZA_CLARO, c, 1, 2);
		adicionarBotao("6", CINZA_CLARO, c, 2, 2);
		adicionarBotao("-", LARANJA, c, 3, 2);

		// quarta linha - fourth line
		adicionarBotao("1", CINZA_CLARO, c, 0, 3);
		adicionarBotao("2", CINZA_CLARO, c, 1, 3);
		adicionarBotao("3", CINZA_CLARO, c, 2, 3);
		adicionarBotao("+", LARANJA, c, 3, 3);

		// quinta linha - fifth line
		c.gridwidth = 2;//usando o espaço de dois botôes / using the two buttons space
		adicionarBotao("0", CINZA_CLARO, c, 0, 4);
		c.gridwidth = 1;//voltando para pegar o usar o espaço normal - coming back to use normal space
		adicionarBotao(",", CINZA_CLARO, c, 2, 4);
		adicionarBotao("=", LARANJA, c, 3, 4);

	}

	private void adicionarBotao(String texto, Color cor, GridBagConstraints c, int x, int y) {

		c.gridx = x;
		c.gridy = y;
		Botao botao = new Botao(texto, cor);
		botao.addActionListener(this);
		add(botao, c);
	}
	
	//capturando o valor digitado no teclado - capturing the value typed  on the keyboard
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() instanceof JButton) {
			JButton botao = (JButton) e.getSource();
			//System.out.println(botao.getText());
			Memoria.getInstacia().processarComando(botao.getText());
		}
		
	}
}
