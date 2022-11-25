package br.com.contatos.application;

import java.util.Date;

import br.com.contatos.dao.contatosDAO;
import br.com.contatos.model.Contato;

public class Main {

	public static void main(String[] args) throws Exception {
		
		contatosDAO ctd = new contatosDAO();
		
		Contato contato = new Contato();
		
		contato.setNome("Guilherme De Oliveira Santos");
		contato.setEmail("Emailteste@hotmail.com");
		contato.setIdade(21);
		contato.setDataCadastro(new Date());
		
		
		//chamada do create (tirar o parênteses para criar um novo contato)
		//ctd.create(contato);
		
		
		//chamada do read
		for (Contato c : ctd.getContatos()) {
			System.out.println("Contato: "+c.getNome());
		}
		
		
		//chamada do update
		Contato c1 = new Contato();
				
		c1.setNome("Guilherme Santos");
		c1.setEmail("emailAtualizado@hotmail.com");
		c1.setIdade(22);
		c1.setDataAtualizacao(new Date());
		c1.setId(1);
				
		ctd.update(c1);
		
		//chamada do delete
		
		ctd.deleteById(2);
	}

}
