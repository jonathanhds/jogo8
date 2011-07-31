package jogo8.model.impl.jogo8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import jogo8.model.Estado;
import jogo8.model.EstadoHeuristica;
import jogo8.util.MatrizUtils;

/**
 * Classe que representa um estado do Jogo do 8.
 * 
 * @author Jonathan Henrique de Souza
 * 
 */
public class Jogo8 implements EstadoHeuristica {

	/**
	 * Matriz deste estado.
	 */
	private Integer[][] matriz;

	/**
	 * Referencia para o estado pai.
	 */
	private Jogo8 pai;

	/**
	 * Funcao G deste estado.
	 */
	private int funcaoG;

	/**
	 * Funcao H deste estado.
	 */
	private int funcaoH;

	/**
	 * Posicao da coluna onde se encontra a celula vazia.
	 */
	private int posColunaVazio;

	/**
	 * Posicao da linha ond se encontra a celula vazia.
	 */
	private int posLinhaVazio;

	/**
	 * Construtor default. Gera uma matriz randomicamente atraves do metodo
	 * {@link MatrizUtils#gerarMatrizRandomica()}.
	 * 
	 * @param pai
	 */
	public Jogo8(Jogo8 pai) {
		this(pai, MatrizUtils.gerarMatrizRandomica());
	}

	/**
	 * Constroi um novo estado do Jogo do 8.
	 * 
	 * @param matriz
	 *            A matriz pertencente a este estado.
	 * 
	 * @param pai
	 * 
	 * @throws IllegalArgumentException
	 *             Caso a matriz seja nula ou se a matriz for diferente do
	 *             formato 3x3.
	 */
	public Jogo8(Jogo8 pai, Integer[][] matriz) {
		this.pai = pai;

		// Calcular funcao G
		if (pai != null) {
			funcaoG = pai.getFuncaoG() + 1;
		}

		// Verificar se a matriz e nula
		if (matriz == null) {
			throw new IllegalArgumentException("matriz nula!");
		}

		// Verificar o tamanho da matriz
		for (int i = 0; i < matriz.length; i++) {
			if (matriz[i].length != 3) {
				throw new IllegalArgumentException("matriz[" + i + "].lenght e diferente de 3!");
			}
		}

		// Setar atributo matriz
		this.matriz = matriz;

		// Setar atributos default
		posColunaVazio = -1;
		posLinhaVazio = -1;
		funcaoH = -1;
	}

	@Override
	public Collection<Estado> aplicarOperadores() {
		// Procurar pela celula vazia se a mesma ainda nao foi procurada
		if ((posColunaVazio == -1) || (posLinhaVazio == -1)) {
			procurarVazio();
		}

		// Lista com os resultados encontrados
		Collection<Estado> resultado = new ArrayList<Estado>();

		// Movimentar para cima
		if (posLinhaVazio < 2) {
			Integer[][] copia = MatrizUtils.copiar(matriz);

			copia[posLinhaVazio + 1][posColunaVazio] = null;
			copia[posLinhaVazio][posColunaVazio] = matriz[posLinhaVazio + 1][posColunaVazio];

			resultado.add(new Jogo8(this, copia));
		}

		// Movimentar para baixo
		if (posLinhaVazio > 0) {
			Integer[][] copia = MatrizUtils.copiar(matriz);

			copia[posLinhaVazio - 1][posColunaVazio] = null;
			copia[posLinhaVazio][posColunaVazio] = matriz[posLinhaVazio - 1][posColunaVazio];

			resultado.add(new Jogo8(this, copia));
		}

		// Movimentar para esquerda
		if (posColunaVazio < 2) {
			Integer[][] copia = MatrizUtils.copiar(matriz);

			copia[posLinhaVazio][posColunaVazio + 1] = null;
			copia[posLinhaVazio][posColunaVazio] = matriz[posLinhaVazio][posColunaVazio + 1];

			resultado.add(new Jogo8(this, copia));
		}

		// Movimentar para direita
		if (posColunaVazio > 0) {
			Integer[][] copia = MatrizUtils.copiar(matriz);

			copia[posLinhaVazio][posColunaVazio - 1] = null;
			copia[posLinhaVazio][posColunaVazio] = matriz[posLinhaVazio][posColunaVazio - 1];

			resultado.add(new Jogo8(this, copia));
		}

		// Retornar resultado
		return resultado;
	}

	@Override
	public String toString() {
		return MatrizUtils.print(matriz);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		for (Integer[] m : matriz) {
			result = prime * result + Arrays.hashCode(m);
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Jogo8)) {
			return false;
		}

		Jogo8 other = (Jogo8) obj;

		if (!MatrizUtils.equals(matriz, other.matriz)) {
			return false;
		}

		return true;
	}

	@Override
	public int compareTo(Estado o) {
		if (!(o instanceof Jogo8)) {
			return 1;
		}

		Jogo8 other = (Jogo8) o;

		return getFuncaoF() - other.getFuncaoF();
	}

	/**
	 * Procura as posicoes linha e coluna da celula vazia e seta-as nos
	 * atributos apropriados.
	 */
	private void procurarVazio() {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if (matriz[i][j] == null) { // Celula encontrada
					posLinhaVazio = i; // Armazenar linha
					posColunaVazio = j; // Armazenar coluna
					return; // Sair do laco
				}
			}
		}

		// Celula vazia nao encontrada
		throw new IllegalArgumentException("Esta matriz nao tem uma celula vazia!");
	}

	/**
	 * Retorna o valor do atributo matriz
	 * 
	 * @return O valor do atributo matriz
	 */
	public Integer[][] getMatriz() {
		return matriz;
	}

	/**
	 * Retorna o valor do atributo posColunaVazio
	 * 
	 * @return O valor do atributo posColunaVazio
	 */
	public int getPosColunaVazio() {
		if (posColunaVazio == -1) {
			procurarVazio();
		}

		return posColunaVazio;
	}

	/**
	 * Retorna o valor do atributo posLinhaVazio
	 * 
	 * @return O valor do atributo posLinhaVazio
	 */
	public int getPosLinhaVazio() {
		if (posLinhaVazio == -1) {
			procurarVazio();
		}

		return posLinhaVazio;
	}

	/**
	 * Retorna o valor do atributo pai
	 * 
	 * @return O valor do atributo pai
	 */
	public Jogo8 getPai() {
		return pai;
	}

	/**
	 * Retorna o valor do atributo funcaoG
	 * 
	 * @return O valor do atributo funcaoG
	 */
	public int getFuncaoG() {
		return funcaoG;
	}

	/**
	 * Retorna o valor do atributo funcaoH
	 * 
	 * @return O valor do atributo funcaoH
	 */
	public int getFuncaoH() {
		return funcaoH;
	}

	@Override
	public int getFuncaoF() {
		return funcaoH + funcaoG;
	}

	/**
	 * Altera o valor do atributo funcaoG
	 * 
	 * @param funcaoG
	 *            O novo valor para o atributo funcaoG
	 */
	public void setFuncaoG(int funcaoG) {
		this.funcaoG = funcaoG;
	}

	/**
	 * Altera o valor do atributo funcaoH
	 * 
	 * @param funcaoH
	 *            O novo valor para o atributo funcaoH
	 */
	public void setFuncaoH(int funcaoH) {
		this.funcaoH = funcaoH;
	}

}