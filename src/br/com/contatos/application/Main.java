package br.com.contatos.application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

import br.com.contatos.dao.ContatosDAO;
import br.com.contatos.model.Contato;

public class Main {

	/**
	 * @param args
	 * @throws Exception
	 */
	
	public static void limpaConsoleCompleto() {
		for (int i = 0; i<9; i++  ) {
			System.out.println("\n");
		}
	}
	
	public static void limpaConsolePequeno() {
		for (int i = 0; i<5; i++  ) {
			System.out.println("\n");
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		String nomeDigitado;
		String emailDigitado;
		int idadeDigitada = 0;
		int idDigitado = 0;
		
		BufferedReader entradaDados = new BufferedReader (new InputStreamReader(System.in));

		System.out.println("Seja bem-vindo ao CDD");
		System.out.println("O que deseja iniciar?");
		
		BufferedReader entradaMenu = new BufferedReader (new InputStreamReader(System.in));
		
		
		
		
		boolean esperandoResposta = false;
		int escolha = 0;
		
		while (esperandoResposta != true){
			
			System.out.println("1 - Cadastrar novo cliente");
			System.out.println("2 - Exibir lista de Clientes");
			System.out.println("3 - Atualizar um cadastro");
			System.out.println("4 - Deletar cliente do banco de dados");
			System.out.println("Digite o numero da opcao escolhida: ");
			escolha = Integer.parseInt(entradaMenu.readLine());
			
			ContatosDAO ctd = new ContatosDAO();
			Contato contato = new Contato();
			
			try {			
				
				switch(escolha) {
				case 1:
					System.out.println("Voce escolheu a opcao 1 - Cadastrar novo Cliente");
					
					esperandoResposta = true;
					
					System.out.println("Digite o nome do cliente:");
					nomeDigitado = entradaDados.readLine();
					System.out.println("\n");
					
					System.out.println("Digite o email do cliente:");
					emailDigitado = entradaDados.readLine();
					System.out.println("\n");
					
					System.out.println("Digite a idade do cliente:");
					idadeDigitada = Integer.parseInt(entradaDados.readLine());
					System.out.println("\n");
					
					contato.setNome(nomeDigitado);
					contato.setEmail(emailDigitado);
					contato.setIdade(idadeDigitada);
					contato.setDataCadastro(new Date());
					
					ctd.create(contato);
					
					
					limpaConsoleCompleto();
					break;
				case 2:
					System.out.println("Voce escolheu a opcao 2 - Exibir lista de clientes");
					esperandoResposta = true;
					
					limpaConsoleCompleto();
					for (Contato c : ctd.getContatos()) {
						System.out.println("Nome: "+c.getNome());
						System.out.println("email: "+c.getEmail());
						System.out.println("Idade: "+c.getIdade());
						System.out.println("Data de criacao: "+c.getDataCadastro());
						System.out.println("Data de atualizacao: "+c.getDataAtualizacao());
						System.out.println("\n ------------------ \n");
					}
					
					limpaConsolePequeno();
					break;
				case 3:
					System.out.println("Voce escolheu a opcao 3 - Atualizar um cadastro");
					esperandoResposta = true;
					
					Contato c1 = new Contato();
					
					System.out.println("Digite o id do cliente que quer atualizar:");
					idDigitado = Integer.parseInt(entradaDados.readLine());
					System.out.println("\n");
					
					System.out.println("Digite o nome atualizado do cliente:");
					nomeDigitado = entradaDados.readLine();
					System.out.println("\n");
					
					System.out.println("Digite o email atualizado do cliente:");
					emailDigitado = entradaDados.readLine();
					System.out.println("\n");
					
					System.out.println("Digite a idade atualizada do cliente:");
					idadeDigitada = Integer.parseInt(entradaDados.readLine());
					System.out.println("\n");
					
					
					c1.setNome(nomeDigitado);
					c1.setEmail(emailDigitado);
					c1.setIdade(idadeDigitada);
					c1.setDataAtualizacao(new Date());
					c1.setId(idDigitado);
							
					ctd.update(c1);
					
					System.out.println("Contato atualizado com sucesso!");
					limpaConsoleCompleto();
					
					break;
				case 4:
					System.out.println("Voce escolheu a opcao 4 - Deletar cliente do banco de dados");
					esperandoResposta = true;
					
					System.out.println("Digite o id do cliente a ser deletado: ");
					idDigitado = Integer.parseInt(entradaDados.readLine());
					
					ctd.deleteById(idDigitado);
					
					//PARTE DO CÓDIGO EM DESENVOLVIMENTO
					/*
					String confirmacao;
					
					System.out.println("Tem certeza que quer deletar o cliente com id: "+idDigitado+"?");
					System.out.println("Digite S ou N");
					confirmacao = entradaDados.readLine();
					
					
					if(confirmacao == "s" || confirmacao == "S") {
						ctd.deleteById(idDigitado);
					}else {
						System.out.println("Volte ao menu inicial");
					}*/
					
					
					
					break;
				default:
					System.out.println("o numero escolhido e invalido.");
					
				}
			}catch (Exception e) {
				System.err.println("Número inválido");
	
			}
			
		}

	}
}
