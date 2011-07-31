package jogo8.model;

import java.util.Collection;

/**
 * Interface para todos os objetos que representam um estado.
 * 
 * @author Jonathan Henrique de Souza
 * 
 */
public interface Estado extends Comparable<Estado> {

	/**
	 * Aplicar todos os operadores possiveis neste estado.
	 * 
	 * @return Os resultados da aplicacao dos operadores.
	 */
	Collection<Estado> aplicarOperadores();

	/**
	 * Retorna a referencia para o pai que gerou este estado.
	 * 
	 * @return O pai deste estado.
	 */
	Estado getPai();

}