package jogo8.model.impl.jogo8;

import jogo8.model.BuscaHeuristica;
import jogo8.model.Estado;

/**
 * Determina as regras da heuristica das posicoes incorretas. Seu objetivo é
 * calcular quantidade de posicoes incorretas encontradas na matriz em relacao
 * ao estado final. Obter o elemento que possui o menor numero de posicoes
 * incorretas.
 * 
 * @author Jonathan Henrique de Souza
 * 
 */
public class HeuristicaPosicaoIncorretas extends BuscaHeuristica {

	/**
	 * Constroi um novo objeto capaz de realizar a busca heuristica das posicoes
	 * incorretas.
	 * 
	 * @param estadoInicial
	 *            O estado inicial desta busca.
	 * @param estadoFinal
	 *            O estado que esta busca devera encontrar.
	 */
	public HeuristicaPosicaoIncorretas(Jogo8 estadoInicial, Jogo8 estadoFinal) {
		super(estadoInicial, estadoFinal);
	}

	@Override
	public int funcaoH(Estado estado) {
		Jogo8 jogo8Final = (Jogo8) getEstadoFinal();
		Jogo8 jogo8 = (Jogo8) estado;

		// obter matriz do estado atual
		Integer[][] matrizAtual = jogo8.getMatriz();

		// obter matriz do estado final
		Integer[][] matrizFinal = jogo8Final.getMatriz();

		if (matrizFinal.length != matrizAtual.length) {
			throw new IllegalArgumentException("matrizFinal.length != matrizAtual.length");
		}

		int resultado = 0;

		// Encontrar posicoes incorretas
		for (int i = 0; i < matrizAtual.length; i++) {
			for (int j = 0; j < matrizAtual.length; j++) {
				// Se os numeros sao diferentes...
				if (!comparar(matrizAtual[i][j], matrizFinal[i][j])) {
					resultado++; // Incrementar resultado
				}
			}
		}

		// Retornar resultado
		return resultado;
	}

	/**
	 * Compara dois numeros. Se <code>true</code>, os numeros sao iguais, caso
	 * <code>false</code>, os numero sao diferentes.
	 * 
	 * @param num1
	 *            O primeiro numero que sera comparado.
	 * @param num2
	 *            O segundo numero que sera comparado.
	 * @return Um valor booleano que expressara a equivalencia dos dois numeros
	 *         fornecidos.
	 */
	private boolean comparar(Integer num1, Integer num2) {
		if (num1 == null) {
			if (num2 == null) {
				return true;
			} else {
				return false;
			}
		} else {
			if (num2 == null) {
				return false;
			} else {
				return num1.equals(num2);
			}
		}
	}

	@Override
	public String toString() {
		return "Busca Heuristica - Posicao Incorretas";
	}

}