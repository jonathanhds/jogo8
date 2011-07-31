package jogo8.model;

/**
 * Classe que representa um estado que e capaz de calcular a funcao F.
 * 
 * @author Jonathan Henrique de Souza
 * 
 */
public interface EstadoHeuristica extends Estado {

	/**
	 * Calcula e retorna o resultado da funcao F.
	 * 
	 * @return O valor da funcao F.
	 */
	int getFuncaoF();
}