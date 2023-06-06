package br.com.fiap.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.fintech.bean.Cartao;
import br.com.fiap.fintech.dao.CartaoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;

public class OracleCartaoDAO implements CartaoDAO{
	
	private Connection conexao;

	@Override
	public void cadastrar(Cartao cartao) throws DBException {
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO TB_FINTECH_CARTAO(CD_CARTAO, NM_CARTAO, NR_CARTAO, DT_VENCIMENTO, DS_BANDEIRA, NR_CVV, DS_CPF_TITULAR, CD_CATEGORIA, DS_EMAIL ) VALUES (SQ_TB_FINTECH_CARTAO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ? )";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cartao.getNome());
			stmt.setDouble(2, cartao.getNumero());
			java.sql.Date data = new java.sql.Date(cartao.getDataDeVencimento().getTimeInMillis());
			stmt.setDate(3, data);
			stmt.setString(4, cartao.getBandeira());
			stmt.setInt(5, cartao.getCvv());
			stmt.setString(6, cartao.getCpfTitular());
			stmt.setInt(7, cartao.getCategoria().getCodigo());
			stmt.setString(8, cartao.getUsuario().getEmail());
			
			stmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastradar.");
		}finally {
			try {
				stmt.close();
				conexao.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void editar(Cartao cartao) throws DBException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remover(int codigo) throws DBException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cartao buscar(int codigo) throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cartao> listar() {
		// TODO Auto-generated method stub
		return null;
	}

}
