package br.com.fiap.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;

public class OracleUsuarioDAO implements UsuarioDAO{
	
	private Connection conexao;

	@Override
	public boolean validarUsuario(Usuario usuario) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_USUARIO WHERE DS_EMAIL = ? AND DS_SENHA = ?");
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getSenha());
			rs = stmt.executeQuery();

			if (rs.next()){
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public void cadastrarUsuario(Usuario usuario) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO TB_FINTECH_USUARIO (DS_EMAIL, DS_SENHA, DS_CPF, NM_NOME, NR_TELEFONE, DT_NASCIMENTO, DS_GENERO) VALUES (?, ?, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getSenha());
			stmt.setString(3, usuario.getCpf());
			stmt.setString(4, usuario.getNome());
			stmt.setInt(5, usuario.getTelefone());
			java.sql.Date data = new java.sql.Date(usuario.getDataDeNascimento().getTimeInMillis());
			stmt.setDate(6, data);
			stmt.setString(7, usuario.getGenero());
			
			stmt.executeUpdate();	
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastradar.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
