package jogo8.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Classe utilitaria para manipulacao de matrizes.
 * 
 * @author Jonathan Henrique de Souza
 * 
 */
public class MatrizUtils {

	/**
	 * Criar uma matriz 3x3. Suas celular terao valores de 1 a 8 e mais uma
	 * celula vazia. Suas posicoes serao randomicas.
	 * 
	 * @return Uma matriz 3x3 com valores randomicos.
	 */
	public static Integer[][] gerarMatrizRandomica() {
		// Lista contendo os valores do jogo do 8
		List<Integer> numeros = new ArrayList<Integer>();

		// Adicionar possiveis valores presentes no Jogo do 8
		numeros.add(1);
		numeros.add(2);
		numeros.add(3);
		numeros.add(4);
		numeros.add(5);
		numeros.add(6);
		numeros.add(7);
		numeros.add(8);
		numeros.add(null); // Celula vazia

		// Embaralahar lista
		Collections.shuffle(numeros);

		// Transformar lista em array
		Integer[] matriz = numeros.toArray(new Integer[numeros.size()]);

		// Criar matriz
		Integer[][] resultado = new Integer[][] { { matriz[0], matriz[1], matriz[2] }, { matriz[3], matriz[4], matriz[5] }, { matriz[6], matriz[7], matriz[8] } };

		// Retornar resultado
		return resultado;
	}

	/**
	 * Retorna uma {@link String} representando esta matriz.
	 * 
	 * @param matriz
	 *            A matriz que sera transformada em {@link String}.
	 * @return Uma {@link String} que representara visualmente a matriz.
	 * @throws IllegalArgumentException
	 *             Caso a matriz seja nula ou se a matriz for diferente do
	 *             formato 3x3.
	 */
	public static String print(Integer[][] matriz) {
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

		// Criar StringBuilder
		StringBuilder stringBuilder = new StringBuilder();

		// Criar string resultado
		stringBuilder.append("+---+---+---+");
		stringBuilder.append("\n");
		stringBuilder.append(String.format("| %1s | %1s | %1s |", MatrizUtils.transformarCelula(matriz[0][0]), MatrizUtils.transformarCelula(matriz[0][1]), MatrizUtils.transformarCelula(matriz[0][2])));
		stringBuilder.append("\n");
		stringBuilder.append("+---+---+---+");
		stringBuilder.append("\n");
		stringBuilder.append(String.format("| %1s | %1s | %1s |", MatrizUtils.transformarCelula(matriz[1][0]), MatrizUtils.transformarCelula(matriz[1][1]), MatrizUtils.transformarCelula(matriz[1][2])));
		stringBuilder.append("\n");
		stringBuilder.append("+---+---+---+");
		stringBuilder.append("\n");
		stringBuilder.append(String.format("| %1s | %1s | %1s |", MatrizUtils.transformarCelula(matriz[2][0]), MatrizUtils.transformarCelula(matriz[2][1]), MatrizUtils.transformarCelula(matriz[2][2])));
		stringBuilder.append("\n");
		stringBuilder.append("+---+---+---+");
		stringBuilder.append("\n");

		// Retornar resultado
		return stringBuilder.toString();
	}

	/**
	 * Metodo utilitario do metodo {@link MatrizUtils#print(Integer[][])}. Tem
	 * como objetivo evitar um {@link NullPointerException}. Transforma uma
	 * celula em uma {@link String}.
	 * 
	 * @param valorCelula
	 *            A celula que sera verificada.
	 * @return Retornara uma {@link String} vazia se a celula for nula, caso
	 *         contrario, retornara uma {@link String} representando esta
	 *         celula.
	 */
	private static String transformarCelula(Integer valorCelula) {
		if (valorCelula == null) {
			return ""; // String vazia
		} else {
			return valorCelula.toString(); // Transforma a celula em String
		}
	}

	/**
	 * Cria uma copia de uma matriz.
	 * 
	 * @param matriz
	 *            A matriz que sera copiada.
	 * @return Uma copia da matriz informada.
	 * @throws IllegalArgumentException
	 *             Caso a matriz seja nula ou se a matriz for diferente do
	 *             formato 3x3.
	 */
	public static Integer[][] copiar(Integer[][] matriz) {
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

		// Construir e retornar matriz resultado
		return new Integer[][] { { matriz[0][0], matriz[0][1], matriz[0][2] }, { matriz[1][0], matriz[1][1], matriz[1][2] }, { matriz[2][0], matriz[2][1], matriz[2][2] } };
	}

	/**
	 * Compara duas matriz para verificar se ambas sao iguais.
	 * 
	 * @param matriz1
	 *            A primeira matriz da comparacao.
	 * @param matriz2
	 *            A segunda matriz da comparacao.
	 * @return <code>true</code> se estas matriz foram iguais,
	 *         <code>false</code> caso contrario.
	 */
	public static boolean equals(Integer[][] matriz1, Integer[][] matriz2) {
		if (matriz1 == matriz2) {
			return true;
		}

		if (matriz1.length != matriz2.length) {
			return false;
		}

		for (int i = 0; i < matriz1.length; i++) {
			if (!(Arrays.equals(matriz1[i], matriz2[i]))) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Cria um estado final especifico que sera utilizada pelas busca para ser
	 * solucionado.
	 * 
	 * @return Uma matriz representando o estado final.
	 */
	public static Integer[][] criarEstadoFinal() {
		Integer[][] estadoFinal = { { 1, 2, 3 }, { 8, null, 4 }, { 7, 6, 5 } };
		return estadoFinal;
	}
}