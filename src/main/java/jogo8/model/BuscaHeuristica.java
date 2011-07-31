package jogo8.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import jogo8.model.impl.jogo8.Jogo8;

/**
 * Classe pai de todas as buscas heuristicas.
 * 
 * @author Jonathan Henrique de Souza
 * 
 */
public abstract class BuscaHeuristica extends Busca {

	/**
	 * Constroi um novo objeto capaz de realizar buscas heuristicas.
	 * 
	 * @param estadoInicial
	 *            O estado inicial desta busca.
	 * @param estadoFinal
	 *            O estado final que esta busca devera encontrar.
	 */
	public BuscaHeuristica(EstadoHeuristica estadoInicial, EstadoHeuristica estadoFinal) {
		super(estadoInicial, estadoFinal);
	}

	/**
	 * Metodo para calcular a funcao H de um objeto. Depende de cada heuristica.
	 * 
	 * @param estado
	 *            O estado que sera calculado a funcao H.
	 * @return O resultado do calculo da funcao H.
	 */
	protected abstract int funcaoH(Estado estado);

	@Override
	protected void adicionarEstadosListaAberta(List<Estado> listaAberta, Collection<Estado> estados) {
		/*
		 * Antes de adicionar os estados na lista, calcular funcao H de todos os
		 * elementos
		 */
		for (Estado estado : estados) {
			Jogo8 jogo8 = (Jogo8) estado;

			int funcaoH = funcaoH(jogo8); // calcular funcao H

			jogo8.setFuncaoH(funcaoH); // setar funcao H no objeto
		}

		listaAberta.addAll(estados); // adicionar estados

		Collections.sort(listaAberta); // ordenar lista
	}

}