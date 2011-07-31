package jogo8.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe pai de todos os objetos que representam um metodo de busca especifico.
 * 
 * @author Jonathan Henrique de Souza
 * 
 */
public abstract class Busca {

	/**
	 * O estado inicial utilizado por esta busca.
	 */
	protected Estado estadoInicial;

	/**
	 * A solucao final que a busca devera encontrar.
	 */
	protected Estado estadoFinal;

	/**
	 * Lista aberta desta busca. Tem como objetivo armazenar todos os estados
	 * que foram gerados pela lista fechada.
	 */
	protected List<Estado> listaAberta;

	/**
	 * Lista fechada desta busca. Tem como objeto armazenar todos os estados que
	 * ja foram analisados pela busca.
	 */
	protected Collection<Estado> listaFechada;

	/**
	 * Constroi um novo objeto usado para realizar um determinado metodo de
	 * busca.
	 * 
	 * @param estadoInicial
	 *            O estado inicial desta busca.
	 * @param estadoFinal
	 *            A solucao final que esta busca devera encontrar.
	 */
	public Busca(Estado estadoInicial, Estado estadoFinal) {
		this.estadoInicial = estadoInicial;
		this.estadoFinal = estadoFinal;
	}

	/**
	 * Retorna o valor do atributo estadoFinal
	 * 
	 * @return O valor do atributo estadoFinal
	 */
	public Estado getEstadoFinal() {
		return estadoFinal;
	}

	/**
	 * Retorna o valor do atributo estadoInicial
	 * 
	 * @return O valor do atributo estadoInicial
	 */
	public Estado getEstadoInicial() {
		return estadoInicial;
	}

	/**
	 * Executa a busca.
	 * 
	 * @return O resultado da busca.
	 */
	public Estado executar() {
		// Criar listas
		listaAberta = new LinkedList<Estado>();
		listaFechada = new HashSet<Estado>();

		// Inserir o estado inicial na lista aberta
		adicionarEstadosListaAberta(listaAberta, estadoInicial);

		// Executar ate encontrar um resultado
		while (true) {
			// Se lista aberta estiver vazia...
			if (listaAberta.isEmpty()) {
				return null; // Solucao nao encontrada!
			}

			// Mover o proximo elemento da lista aberta para a lista fechada
			Estado e = escolherProximoEstado(listaAberta);
			listaFechada.add(e);

			// Se este estado for o estado final...
			if (e.equals(estadoFinal)) {
				return e; // Solucao encontrada!
			}

			// Aplicar todos os operadores possiveis ao estado
			Collection<Estado> novosEstados = e.aplicarOperadores();

			// Se nao tiver novos estados gerados, continuar loop
			if (novosEstados.isEmpty()) {
				continue;
			}

			// Gerar os estados que nao estajam na lista fechada
			novosEstados.removeAll(listaFechada);

			// Incluir estados gerados na lista aberta
			adicionarEstadosListaAberta(listaAberta, novosEstados);
		}
	}

	/**
	 * Adicionar os estados na lista aberta. Cada busca determina uma regra para
	 * adicionar os estados na lista aberta.
	 * 
	 * @param listaAberta
	 *            Referencia para a lista aberta que devera receber os novos
	 *            estados.
	 * 
	 * @param estados
	 *            Os estados que deverao ser adicionados.
	 */
	protected abstract void adicionarEstadosListaAberta(List<Estado> listaAberta, Collection<Estado> estados);

	/**
	 * Adiciona um unico estado na lista aberta.
	 * 
	 * @param listaAberta
	 *            A lista que recebera o estado.
	 * @param estado
	 *            O estado que sera adicionado.
	 */
	private void adicionarEstadosListaAberta(List<Estado> listaAberta, Estado estado) {
		Collection<Estado> temp = new ArrayList<Estado>();
		temp.add(estado);
		adicionarEstadosListaAberta(listaAberta, temp);
	}

	/**
	 * Escolhe, entre os elementos da lista aberta, o proximo estado que sera
	 * analisado pelo algoritmo.
	 * 
	 * @param listaAberta
	 *            A lista aberta que contem os elementos que deverao ser
	 *            escolhidos.
	 * @return O proximo elemento presente na lista aberta que sera analisado.
	 */
	private Estado escolherProximoEstado(List<Estado> listaAberta) {
		// Remover e retornar o primeiro elemento da lista aberta
		return listaAberta.remove(0);
	}

	/**
	 * Retorna o valor do atributo <code>listaAberta</code>
	 * 
	 * @return O valor do atributo <code>listaAberta</code>
	 */
	public List<Estado> getListaAberta() {
		return listaAberta;
	}

	/**
	 * Retorna o valor do atributo <code>listaFechada</code>
	 * 
	 * @return O valor do atributo <code>listaFechada</code>
	 */
	public Collection<Estado> getListaFechada() {
		return listaFechada;
	}
}