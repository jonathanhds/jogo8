package jogo8.model.impl.jogo8;

import jogo8.model.BuscaHeuristica;
import jogo8.model.Estado;

/**
 * Determina as regras da heuristica das distancia Manhattan. Seu objetivo é
 * calcular a distancia de cada celula da matriz em relacao ao estado final.
 * Obter o elemento que possui a menor distancia para o objetivo final.
 * 
 * @author Jonathan Henrique de Souza
 * 
 */
public class HeuristicaManhattan extends BuscaHeuristica {

	/**
	 * Constroi um novo objeto capaz de realizada a busca heuristica da
	 * distancia Manhattan.
	 * 
	 * @param estadoInicial
	 *            O estado inicial desta busca.
	 * @param estadoFinal
	 *            O estado que esta busca devera encontrar.
	 */
	public HeuristicaManhattan(Jogo8 estadoInicial, Jogo8 estadoFinal) {
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

		// Iterar sobre a matriz do estado fornecido
		for (int i = 0; i < matrizAtual.length; i++) {
			for (int j = 0; j < matrizAtual[i].length; j++) {
				// Procurar este valor na matriz final
				int[] posicao = procurar(matrizFinal, matrizAtual[i][j]);

				// Calcular a distancia deste valor em relacao ao estado final
				resultado += calcularDistancia(posicao[0], i, posicao[1], j);
			}
		}

		return resultado; // Retornar resultado
	}

	/**
	 * Procurar um numero e devolver sua posicao na matriz.
	 * 
	 * @param matriz
	 *            A matriz que sera usada na busca.
	 * @param numero
	 *            O numero que devera ser encontrado.
	 * @return Um vetor contendo as posicoes i e j do numero fornecido nesta
	 *         matriz.
	 */
	private int[] procurar(Integer[][] matriz, Integer numero) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if (matriz[i][j] == null) {
					if (numero == null) {
						return new int[] { i, j };
					}
				} else {
					if (matriz[i][j] == numero) {
						return new int[] { i, j };
					}
				}
			}
		}

		// Numero nao encontrado. Nunca devera chegar aqui!
		throw new IllegalStateException("numero nao encontrado!");
	}

	/**
	 * Calcular a distancia de uma posicao incial ate uma posicao final.
	 * 
	 * @param i1
	 *            A posicao linha inicial.
	 * @param i2
	 *            A posicao linha final.
	 * @param j1
	 *            A posicao coluna inicial.
	 * @param j2
	 *            A posicao coluna final.
	 * @return A distancia das posiscoes fornecidas.
	 */
	private int calcularDistancia(int i1, int i2, int j1, int j2) {
		return Math.abs((i1 + j1) - (i2 + j2));
	}

	@Override
	public String toString() {
		return "Busca Heuristica - Distancia Manhattan";
	}

}