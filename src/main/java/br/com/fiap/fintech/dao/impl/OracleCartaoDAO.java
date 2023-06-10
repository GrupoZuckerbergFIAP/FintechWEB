package br.com.fiap.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.fintech.bean.Cartao;
import br.com.fiap.fintech.bean.Categoria;
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
			String sql = "INSERT INTO TB_FINTECH_CARTAO(CD_CARTAO, NM_CARTAO, NR_CARTAO, DT_VENCIMENTO, DS_BANDEIRA, NR_CVV, DS_CPF_TITULAR, CD_CATEGORIA, DS_EMAIL) VALUES (SQ_TB_FINTECH_CARTAO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ? )";
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
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE TB_FINTECH_CARTAO SET NM_CARTAO = ?, NR_CARTAO = ?, DT_VENCIMENTO = ?, DS_BANDEIRA = ?, NR_CVV = ?, DS_CPF_TITULAR = ?, CD_CATEGORIA = ? WHERE CD_CARTAO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cartao.getNome());
			stmt.setDouble(2, cartao.getNumero());
			java.sql.Date data = new java.sql.Date(cartao.getDataDeVencimento().getTimeInMillis());
			stmt.setDate(3, data);
			stmt.setString(4, cartao.getBandeira());
			stmt.setInt(5, cartao.getCvv());
			stmt.setString(6, cartao.getCpfTitular());
			stmt.setInt(7, cartao.getCategoria().getCodigo());
			stmt.setInt(8, cartao.getCodigo());
					
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao editar.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void remover(int codigo) throws DBException {
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM TB_FINTECH_CARTAO WHERE CD_CARTAO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, codigo);
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao deletar.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		
	}

	@Override
	public Cartao buscar(int codigo) throws DBException {
		Cartao cartao = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_FINTECH_CARTAO INNER JOIN TB_FINTECH_CATEGORIA ON TB_FINTECH_CARTAO.CD_CATEGORIA = TB_FINTECH_CATEGORIA.CD_CATEGORIA WHERE TB_FINTECH_CARTAO.CD_CARTAO = ?");
			stmt.setInt(1, codigo);
			rs = stmt.executeQuery();		
			
			if(rs.next()) {
				int cd = rs.getInt("CD_CARTAO");
				String nome = rs.getString("NM_CARTAO");
				double numero = rs.getDouble("NR_CARTAO");
				java.sql.Date data = rs.getDate("DT_VENCIMENTO");
				Calendar dataVencimento = Calendar.getInstance();
				dataVencimento.setTimeInMillis(data.getTime());
				String bandeira = rs.getString("DS_BANDEIRA");
				int cvv = rs.getInt("NR_CVV");
				String cpftitular = rs.getString("DS_CPF_TITULAR");
				int cdcategoria = rs.getInt("CD_CATEGORIA");
				String dscategoria = rs.getString("DS_CATEGORIA");
				
				cartao = new Cartao(cd, nome, numero, dataVencimento, bandeira, cvv, cpftitular);
				Categoria categoria = new Categoria(cdcategoria, dscategoria);
				cartao.setCategoria(categoria);
			} 
			
		}catch(SQLException e) {
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
		return cartao;
	}

	@Override
	public List<Cartao> listar() {
		List<Cartao> lista = new ArrayList<Cartao>();	
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_FINTECH_CARTAO INNER JOIN TB_FINTECH_CATEGORIA ON TB_FINTECH_CARTAO.CD_CATEGORIA = TB_FINTECH_CATEGORIA.CD_CATEGORIA");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				int cd = rs.getInt("CD_CARTAO");
				String nome = rs.getString("NM_CARTAO");
				double numero = rs.getDouble("NR_CARTAO");
				java.sql.Date data = rs.getDate("DT_VENCIMENTO");
				Calendar dataVencimento = Calendar.getInstance();
				dataVencimento.setTimeInMillis(data.getTime());
				String bandeira = rs.getString("DS_BANDEIRA");
				int cvv = rs.getInt("NR_CVV");
				String cpftitular = rs.getString("DS_CPF_TITULAR");
				int cdcategoria = rs.getInt("CD_CATEGORIA");
				String dscategoria = rs.getString("DS_CATEGORIA");
				
				Cartao cartao = new Cartao(cd, nome, numero, dataVencimento, bandeira, cvv, cpftitular);
				Categoria categoria = new Categoria(cdcategoria, dscategoria);
				cartao.setCategoria(categoria);
				
				lista.add(cartao);
			}
			
		}catch(SQLException e) {
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
		
		return lista;
	}

}
