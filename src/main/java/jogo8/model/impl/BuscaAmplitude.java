package jogo8.model.impl;

import java.util.Collection;
import java.util.List;

import jogo8.model.Busca;
import jogo8.model.Estado;

/**
 * Classe que define as regras da Busca por Amplitude.
 * 
 * @author Jonathan Henrique de Souza
 * 
 */
public class BuscaAmplitude extends Busca {

	/**
	 * Constroi um novo objeto que realiza a Busca por Amplitude.
	 * 
	 * @param estadoInicial
	 *            O estado incial utilizada por esta busca.
	 * @param estadoFinal
	 *            O estado final que esta busca devera encontrar.
	 */
	public BuscaAmplitude(Estado estadoInicial, Estado estadoFinal) {
		super(estadoInicial, estadoFinal);
	}

	@Override
	protected void adicionarEstadosListaAberta(List<Estado> listaAberta, Collection<Estado> estados) {
		// Adicionar no final da lista
		listaAberta.addAll(estados);
	}

	@Override
	public String toString() {
		return "Busca por Amplitude";
	}
}