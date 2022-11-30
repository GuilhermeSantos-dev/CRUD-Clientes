package br.com.contatos.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.contatos.FabricaDeConexao.FabricaDeConexao;
import br.com.contatos.model.Contato;

public class ContatosDAO {

	/*
	 * CRIANDO CRUD
	 * 
	 * C - CREATE - OK
	 * R - READ - OK
	 * U - UPDATE - OK
	 * D - DELETE
	 * 
	 */
	
	//create ok
	public void create(Contato contato) throws Exception {
		String sql = "INSERT INTO contatodentista(nome, email, idade, dataCadastro) VALUES (?, ?, ?, ?)";
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		
		//adicionar os valores esperados pela query
		
		
		
		try {
			
			conn = FabricaDeConexao.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setString(1, contato.getNome());
			pstm.setString(2, contato.getEmail());
			pstm.setInt(3, contato.getIdade());
			pstm.setDate(4, new Date(contato.getDataCadastro().getTime()));
			
			pstm.execute();
			
			System.out.println("Contato Salvo com Sucesso");
			} catch (Exception e) {
				e.printStackTrace();
		}finally {
			try {
				if(conn!=null) {
					conn.close();
				}
				if(pstm!=null) {
					pstm.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	//read ok
	public List<Contato> getContatos(){
		
		String sql = "SELECT * FROM contatodentista";
		
		List<Contato> contatos = new ArrayList<Contato>();
		
		Connection conn = null;
		
		PreparedStatement pst = null;
		
		ResultSet rst = null;
		
		try {
			
			conn = FabricaDeConexao.createConnectionToMySQL();
			
			pst = (PreparedStatement) conn.prepareStatement(sql);
			
			rst = pst.executeQuery();
			
			while(rst.next()) {
				
				Contato contato = new Contato();
				
				// recuperar os dados em um novo objeto
				
				contato.setId(rst.getInt("id"));
				
				contato.setNome(rst.getString("nome"));
				
				contato.setEmail(rst.getString("email"));
				
				contato.setIdade(rst.getInt("idade"));
				
				contato.setDataCadastro(rst.getDate("dataCadastro"));
				
				contatos.add(contato);
				
			}
			
		} catch (Exception e) {
		
			e.printStackTrace();
			
		}finally {
			try {
				
				if(conn!=null) {
					conn.close();
				}
				
				if(pst!=null) {
					pst.close();
				}
				
				if(rst!=null) {
					rst.close();
				}
				
			} catch (Exception e) {

				e.printStackTrace();
				
			}
		}
		
		return contatos;

	}
	
	//update ok
	public void update(Contato contato) {
		
		String sql = "UPDATE contatodentista set nome = ?, email = ?, idade = ?, dataAtualizacao = ?"+" WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			
			//criando a conexão com o banco
			conn = FabricaDeConexao.createConnectionToMySQL();
			
			//gerando a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//adicionar os valores para atualizar
			
			pstm.setString(1, contato.getNome());
			pstm.setString(2, contato.getEmail());
			pstm.setInt(3, contato.getIdade());
			pstm.setDate(4, new Date(contato.getDataAtualizacao().getTime()));
			pstm.setInt(5, contato.getId());
			
			//executar a query
			pstm.execute();
			
			
			
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		
		}
	}

	//delete
	public void deleteById(int id) {
		String sql = "DELETE FROM contatodentista where id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = FabricaDeConexao.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setInt(1, id);
			
			pstm.execute();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
