package br.com.fiap.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.fintech.bean.Categoria;
import br.com.fiap.fintech.bean.Receita;
import br.com.fiap.fintech.dao.ReceitaDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;

public class OracleReceitaDAO implements ReceitaDAO{

	private Connection conexao;
	
	@Override
	public void cadastrar(Receita receita) throws DBException {
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO TB_FINTECH_RECEITA(CD_RECEITA, DS_RECEITA, NR_VALOR, DT_TRANSACAO, CD_CATEGORIA, DS_EMAIL) VALUES (sq_tb_fintech_receita.NEXTVAL, ?, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, receita.getDescricao());
			stmt.setDouble(2, receita.getValor());
			java.sql.Date data = new java.sql.Date(receita.getDataTransacao().getTimeInMillis());
			stmt.setDate(4, data);
			stmt.setInt(5, receita.getCategoria().getCodigo());
			stmt.setString(6, receita.getUsuario().getEmail());
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastradar.");
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
	public void editar(Receita receita) throws DBException {
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE TB_FINTECH_RECEITA SET DS_RECEITA = ?, NR_VALOR = ?, DT_TRANSACAO = ?, NR_TOTAL = ?, CD_CATEGORIA = ? WHERE CD_RECEITA = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, receita.getDescricao());
			stmt.setDouble(2, receita.getValor());
			java.sql.Date data = new java.sql.Date(receita.getDataTransacao().getTimeInMillis());
			stmt.setDate(4, data);
			stmt.setInt(5, receita.getCategoria().getCodigo());
			stmt.setInt(6, receita.getCodigo());
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
			String sql = "DELETE FROM TB_FINTECH_RECEITA WHERE CD_RECEITA";
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
	public Receita buscar(int codigo) throws DBException {
		Receita receita = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_FINTECH_RECEITA INNER JOIN TB_FINTECH_CATEGORIA ON TB_FINTECH_RECEITA.CD_CATEGORIA = TB_FINTECH_CATEGORIA.CD_CATEGORIA WHERE TB_FINTECH_RECEITA.CD_RECEITA = ?");
			stmt.setInt(1, codigo);
			rs = stmt.executeQuery();		
			
			if(rs.next()) {
				int cd = rs.getInt("CD_RECEITA");
				String descricao = rs.getString("DS_RECEITA");
				double valor = rs.getDouble("NR_VALOR");
				java.sql.Date data = rs.getDate("DT_TRANSACAO");
				Calendar dataVencimento = Calendar.getInstance();
				dataVencimento.setTimeInMillis(data.getTime());
				int cdcategoria = rs.getInt("CD_CATEGORIA");
				String dscategoria = rs.getString("DS_CATEGORIA");
				
				receita = new Receita(cd, dataVencimento, descricao, valor);
				Categoria categoria = new Categoria(cdcategoria, dscategoria);
				receita.setCategoria(categoria);
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
		return receita;
	}

	@Override
	public List<Receita> listar() {
		List<Receita> lista = new ArrayList<Receita>();	
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_FINTECH_RECEITA INNER JOIN TB_FINTECH_CATEGORIA ON TB_FINTECH_RECEITA.CD_CATEGORIA = TB_FINTECH_CATEGORIA.CD_CATEGORIA");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				int cd = rs.getInt("CD_RECEITA");
				String descricao = rs.getString("DS_RECEITA");
				double valor = rs.getDouble("NR_VALOR");
				java.sql.Date data = rs.getDate("DT_TRANSACAO");
				Calendar dataVencimento = Calendar.getInstance();
				dataVencimento.setTimeInMillis(data.getTime());
				int cdcategoria = rs.getInt("CD_CATEGORIA");
				String dscategoria = rs.getString("DS_CATEGORIA");
				
				Receita receita = new Receita(cd, dataVencimento, descricao, valor);
				Categoria categoria = new Categoria(cdcategoria, dscategoria);
				receita.setCategoria(categoria);
				
				lista.add(receita);
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
