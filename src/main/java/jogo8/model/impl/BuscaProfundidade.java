package jogo8.model.impl;

import java.util.Collection;
import java.util.List;

import jogo8.model.Busca;
import jogo8.model.Estado;

/**
 * Classe que define as regras da Busca por Profundidade.
 * 
 * @author Jonathan Henrique de Souza
 * 
 */
public class BuscaProfundidade extends Busca {

	/**
	 * Constroi um novo objeto que realiza a Busca por Profundidade.
	 * 
	 * @param estadoInicial
	 *            O estado incial utilizado por esta busca.
	 * @param estadoFinal
	 *            O estado final que esta busca devera encontrar.
	 */
	public BuscaProfundidade(Estado estadoInicial, Estado estadoFinal) {
		super(estadoInicial, estadoFinal);
	}

	@Override
	protected void adicionarEstadosListaAberta(List<Estado> listaAberta, Collection<Estado> estados) {
		// Adicionar no inicio da lista
		listaAberta.addAll(0, estados);
	}

	@Override
	public String toString() {
		return "Busca por Profundidade";
	}
}