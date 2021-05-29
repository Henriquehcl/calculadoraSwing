package cal.model;

import java.util.ArrayList;
import java.util.List;

public class Memoria {

	private enum TipoComando {
		ZERAR, SINAL, NUMERO, DIV, MULTI, SUB, ADIC, IGUAL, VIRGULA;
	};

	private static final Memoria instacia = new Memoria();

	private final List<MemoriaObserver> observadores = new ArrayList<>();

	private TipoComando ultimaOperacao = null;
	private boolean substituir = false;
	private String textoAtual = "";
	private String textoBuffer = "";

	private Memoria() {

	}

	public static Memoria getInstacia() {
		return instacia;
	}

	public void adicionarObservador(MemoriaObserver observador) {
		observadores.add(observador);
	}

	public String getTextoAtual() {
		return textoAtual.isEmpty() ? "0" : textoAtual;
	}

	public void processarComando(String valor) {

		TipoComando tipoComando = detectarTipoComando(valor);
		//System.out.println(tipoComando);
		
		if(tipoComando == null) {
			return ;
		}else if(tipoComando == TipoComando.ZERAR) {
			textoAtual = "";
			textoBuffer = "";
			substituir = false;
			ultimaOperacao = null;
		}else if(tipoComando == TipoComando.SINAL && textoAtual.contains("-")) {
			textoAtual = textoAtual.substring(1);
		}else if(tipoComando == TipoComando.SINAL && !textoAtual.contains("-")) {
			textoAtual = "-" + textoAtual;
		} else if( tipoComando == TipoComando.NUMERO || tipoComando == TipoComando.VIRGULA) {
			textoAtual = substituir ? valor : textoAtual + valor;
			substituir = false;
		} else {
			substituir = true;
			textoAtual = obterResultadoOperacao();
			textoBuffer = textoAtual;
			ultimaOperacao = tipoComando;
		}
		
/*	if ("AC".equals(valor)) {
			textoAtual = "";
		} else {
			textoAtual += valor;
		}
*/
		observadores.forEach(o -> o.valorAlterado(getTextoAtual()));
	}

	private String obterResultadoOperacao() {
		if(ultimaOperacao == null || ultimaOperacao == TipoComando.IGUAL) {
			return textoAtual;
		}
		
		double numeroBuffer = Double.parseDouble(textoBuffer.replace(",","."));
		double numeroAtual = Double.parseDouble(textoAtual.replace(".",","));
		double resultado = 0;
		
		if(ultimaOperacao == TipoComando.ADIC) {
			resultado = numeroBuffer + numeroAtual;
		} else if(ultimaOperacao == TipoComando.SUB) {
			resultado = numeroBuffer - numeroAtual;
		}else if(ultimaOperacao == TipoComando.DIV) {
			resultado = numeroBuffer / numeroAtual;
		}else if(ultimaOperacao == TipoComando. MULTI) {
			resultado = numeroBuffer * numeroAtual;
		}
		
		String resultadoString = Double.toString(resultado).replace(".", ",");
		boolean inteiro = resultadoString.endsWith(",0");
		return inteiro ? resultadoString.replace(",0", "") : resultadoString;
	}

	private TipoComando detectarTipoComando(String valor) {

		if (textoAtual.isEmpty() && valor == "0") {
			return null;
		}

		try {
			Integer.parseInt(valor);
			return TipoComando.NUMERO;

		} catch (NumberFormatException e) {
			// se não for número
			if ("AC".equals(valor)) {
				return TipoComando.ZERAR;
			} else if("/".equals(valor)) {
				return TipoComando.DIV;
			}
			else if("*".equals(valor)) {
				return TipoComando.MULTI;
			}
			else if("+".equals(valor)) {
				return TipoComando.ADIC;
			}
			else if("-".equals(valor)) {
				return TipoComando.SUB;
			}
			else if("=".equals(valor)) {
				return TipoComando.IGUAL;
			}else if("±".equals(valor)) {
				return TipoComando.SINAL;
			}else if(",".equals(valor) && !textoAtual.contains(",")) {
				return TipoComando.VIRGULA;
			}
		}

		return null;
	}

}
