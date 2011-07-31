package jogo8.main;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import jogo8.model.Busca;
import jogo8.model.Estado;
import jogo8.model.impl.BuscaAmplitude;
import jogo8.model.impl.BuscaProfundidade;
import jogo8.model.impl.jogo8.HeuristicaManhattan;
import jogo8.model.impl.jogo8.HeuristicaPosicaoIncorretas;
import jogo8.model.impl.jogo8.Jogo8;
import jogo8.util.MatrizUtils;

/**
 * Classe principal que executara o aplicativo.
 * 
 * @author Jonathan Henrique de Souza
 * 
 */
public class Main {

	/**
	 * Metodo responsavel pela execucao do aplicativo.
	 * 
	 * @param args
	 *            Nao utilizado.
	 * 
	 */
	public static void main(String[] args) {
		Busca buscaEscolhida = Main.mostrarMenu();
		Main.executarBusca(buscaEscolhida);
	}

	/**
	 * Mostra um menu no console e aguarda a escolha do usuario.
	 * 
	 * @return A metodo de busca escolhido pelo usuario.
	 */
	private static Busca mostrarMenu() {
		// Mostra no console as possiveis escolhas
		System.out.println("Escolha uma busca:");
		System.out.println("1) Busca por Amplitude");
		System.out.println("2) Busca por Profundidade");
		System.out.println("3) Heuristica - Quantidade de numeros fora da posicao correta");
		System.out.println("4) Heuristica - Distancia Manhattan");

		// Ler uma escolha
		int escolha = Main.lerNumero();

		// Criar um objeto do tipo Busca de acordo com a escolha
		Busca buscaEscolhida = Main.criarBusca(escolha);

		// Repetir ate que a criacao da busca for concluida
		while (buscaEscolhida == null) {
			System.out.println("Busca nao encontrada. Favor, escolha uma opcao valida (exit para sair)");
			escolha = Main.lerNumero();
			buscaEscolhida = Main.criarBusca(escolha);
		}

		// Retornar a busca recem-criada
		return buscaEscolhida;
	}

	/**
	 * Cria um objeto do tipo {@link Busca} baseado na escolha do usuario.
	 * 
	 * @param escolha
	 *            A escolha do usuario.
	 * @return Um objeto {@link Busca} ou <code>null</code> se esta escolha nao
	 *         existir na aplicacao.
	 */
	private static Busca criarBusca(int escolha) {
		switch (escolha) {
		case 1: // Busca por Amplitude
			return new BuscaAmplitude(new Jogo8(null), new Jogo8(null, MatrizUtils.criarEstadoFinal()));
		case 2: // Busca por Profundidade
			return new BuscaProfundidade(new Jogo8(null), new Jogo8(null, MatrizUtils.criarEstadoFinal()));
		case 3: // Heuristica - Posicoes incorretas
			return new HeuristicaPosicaoIncorretas(new Jogo8(null), new Jogo8(null, MatrizUtils.criarEstadoFinal()));
		case 4: // Heuristica - Distancia Manhattan
			return new HeuristicaManhattan(new Jogo8(null), new Jogo8(null, MatrizUtils.criarEstadoFinal()));
		default:
			return null; // Busca nao existente!
		}
	}

	/**
	 * Le um numero no console.
	 * 
	 * @return O numero informado pelo usuario.
	 */
	private static int lerNumero() {
		// Objeto para ler teclado
		Scanner scanner = new Scanner(System.in);

		// O resultado que contera a escolha do usuario
		String escolha;

		int resultado;

		try {
			System.out.print("-> ");
			escolha = scanner.nextLine(); // Ler um numero inteiro

			if (escolha.isEmpty()) {
				throw new Exception("Valor vazio!");
			}

			if (escolha.equalsIgnoreCase("exit")) {
				System.exit(0);
			}

			resultado = Integer.parseInt(escolha);

			if (resultado <= 0) {
				throw new Exception("Numero nao positivo!");
			}
		} catch (Exception e) {
			System.out.println("Numero invalido. Favor, informe um numero valido (exit para sair)");
			resultado = Main.lerNumero(); // Chamar o metodo recursivamente
		}

		// Adicionar linha em branco
		System.out.println();

		// Retornar a escolha do usuario
		return resultado;
	}

	/**
	 * Le um valor booleano (s/n) no console.
	 * 
	 * @return <code>true</code> se o usuario confirmou a escolha,
	 *         <code>false</code> caso contrario.
	 */
	private static boolean lerBooleano() {
		// Objeto para ler teclado
		Scanner scanner = new Scanner(System.in);

		// O resultado que contera a escolha do usuario
		String escolha;

		try {
			System.out.print("-> ");
			escolha = scanner.nextLine(); // Ler linha

			if (escolha.isEmpty()) {
				throw new Exception("Valor vazio");
			} else if (escolha.equalsIgnoreCase("s")) {
				return true;
			} else if (escolha.equalsIgnoreCase("n")) {
				return false;
			} else if (escolha.equalsIgnoreCase("exit")) {
				System.exit(0);
				return false;
			} else {
				throw new Exception("Valor invalido");
			}
		} catch (Exception e) {
			System.out.println("Valor invalido. Favor, informe um valor valido (exit para sair)");
			return Main.lerBooleano(); // Chamar o metodo recursivamente
		}
	}

	/**
	 * Executa a busca.
	 * 
	 * @param busca
	 *            A busca que sera executada.
	 */
	private static void executarBusca(Busca busca) {
		// Mostrar estados
		System.out.println("Estado inicial:");
		System.out.println(busca.getEstadoInicial());
		System.out.println("Estado final:");
		System.out.println(busca.getEstadoFinal());

		System.out.println("Executando...");

		// Obter inicio
		long inicio = System.currentTimeMillis();

		// Executar busca
		Estado resultado = busca.executar();

		// Obter fim
		long fim = System.currentTimeMillis();

		System.out.println();
		System.out.println("Finalizado!");

		// Mostrar detalhes
		Main.mostrarDetalhes(busca, resultado, (fim - inicio));

		if (resultado != null) { // Se o resultado foi encontrado
			System.out.println("\nVoce deseja visualizar o caminho encontrado pelo algoritmo? (s / n)");
			if (lerBooleano()) { // Ler escolha do usuario
				mostrarCaminho(resultado); // Mostrar caminho na tela
			}
		}
	}

	/**
	 * Mostra no console os detalhes da busca realizada.
	 * 
	 * @param busca
	 *            A busca que sera analisada.
	 * @param resultado
	 *            O resultado encontrado da busca
	 * @param tempoTotal
	 *            O tempo total utilizado pela busca
	 */
	private static void mostrarDetalhes(Busca busca, Estado resultado, long tempoTotal) {
		System.out.println(); // Adicionar linha em branco
		System.out.println("---------- Detalhes da Busca ----------");

		// Tipo
		System.out.println("Tipo busca: " + busca);

		// Resultado
		System.out.println("Resultado: " + (resultado != null ? "Encontado" : "Nao encontrado"));

		// Tempo total
		System.out.printf("Tempo total: %,d ms%n", tempoTotal);

		// Memoria utilizada
		System.out.println("Memoria utilizada: " + memoriaUtilizada());

		// Tamanho das listas abertas e fechadas
		System.out.println("Lista aberta: " + Main.mostrarTamanhoLista(busca.getListaAberta()));
		System.out.println("Lista fechada: " + Main.mostrarTamanhoLista(busca.getListaFechada()));

		System.out.println("---------------------------------------");
	}

	/**
	 * Retorna uma descricao da memoria utilizada.
	 * 
	 * @return Uma {@link String} representando a memoria utilizada.
	 */
	private static String memoriaUtilizada() {
		Runtime runtime = Runtime.getRuntime();

		long memoriaTotal = runtime.totalMemory();
		long memoriaLivre = runtime.freeMemory();
		long memoriaUsada = memoriaTotal - memoriaLivre;

		return String.format("%,d / %,d (%,d %%)", memoriaUsada, memoriaTotal, (memoriaUsada / memoriaTotal));
	}

	/**
	 * Imprime na tela o caminho encontrado pela busca.
	 * 
	 * @param resultado
	 *            O estado encontrado pela busca que sera utilizada para
	 *            imprimir o caminho feito pela busca.
	 */
	private static void mostrarCaminho(Estado resultado) {
		Estado e = resultado; // Variavel auxiliar

		// Lista contendo o caminho
		List<Estado> listaCaminho = new LinkedList<Estado>();

		// Adicionar o caminho na lista
		while (e != null) {
			listaCaminho.add(0, e);
			e = e.getPai();
		}

		// Total de passos utilizado pela busca
		int passos = listaCaminho.size();

		// Imprimir caminho
		for (Estado estado : listaCaminho) {
			System.out.println(estado);
		}

		// Imprimir total de passos encontrado pela busca
		System.out.println("\nTotal de passos: " + passos);
	}

	/**
	 * Retorna um status referente ao tamanho da lista informada.
	 * 
	 * @param lista
	 *            A lista que sera analisada.
	 * @return Uma {@link String} informando ao usuario o tamanho desta lista.
	 */
	private static String mostrarTamanhoLista(Collection<Estado> lista) {
		// Obter tamanho da lista
		int tamanhoLista = lista.size();

		if (tamanhoLista > 0) {
			if (tamanhoLista == 1) {
				return "1 elemento";
			} else {
				return String.format("%,d elementos", tamanhoLista);
			}
		} else {
			return "Vazia";
		}
	}
}