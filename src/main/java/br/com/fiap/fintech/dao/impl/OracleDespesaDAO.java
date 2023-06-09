package br.com.fiap.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.fintech.bean.Categoria;
import br.com.fiap.fintech.bean.Despesa;
import br.com.fiap.fintech.dao.DespesaDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;

public class OracleDespesaDAO implements DespesaDAO{

	private Connection conexao;
	@Override
	public void cadastrar(Despesa despesa) throws DBException {
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO TB_FINTECH_DESPESA(CD_DESPESA, DS_DESPESA, NR_VALOR, DT_TRANSACAO, CD_CATEGORIA, DS_EMAIL) VALUES (sq_tb_fintech_DESPESA.NEXTVAL, ?, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, despesa.getDescricao());
			stmt.setDouble(2, despesa.getValor());
			java.sql.Date data = new java.sql.Date(despesa.getDataTransacao().getTimeInMillis());
			stmt.setDate(4, data);
			stmt.setInt(5, despesa.getCategoria().getCodigo());
			stmt.setString(6, despesa.getUsuario().getEmail());
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
	public void editar(Despesa despesa) throws DBException {
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE TB_FINTECH_DESPESA SET DS_DESPESA = ?, NR_VALOR = ?, DT_TRANSACAO = ?, NR_TOTAL = ?, CD_CATEGORIA = ? WHERE CD_RECEITA = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, despesa.getDescricao());
			stmt.setDouble(2, despesa.getValor());
			java.sql.Date data = new java.sql.Date(despesa.getDataTransacao().getTimeInMillis());
			stmt.setDate(4, data);
			stmt.setInt(5, despesa.getCategoria().getCodigo());
			stmt.setInt(6, despesa.getCodigo());
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
			String sql = "DELETE FROM TB_FINTECH_DESPESA WHERE CD_DESPESA";
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
	public Despesa buscar(int codigo) throws DBException {
		Despesa despesa = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_FINTECH_DESPESA INNER JOIN TB_FINTECH_CATEGORIA ON TB_FINTECH_DESPESA.CD_OBJETIVO = TB_FINTECH_DESPESA.CD_CATEGORIA WHERE TB_FINTECH_OBJETIVO.CD_OBJETIVO = ?");
			rs = stmt.executeQuery();		
			
			if(rs.next()) {
				int cd = rs.getInt("CD_DESPESA");
				String descricao = rs.getString("DS_DESPESA");
				double valor = rs.getDouble("NR_VALOR");
				java.sql.Date data = rs.getDate("DT_TRANSACAO");
				Calendar dataVencimento = Calendar.getInstance();
				dataVencimento.setTimeInMillis(data.getTime());
				int cdcategoria = rs.getInt("CD_CATEGORIA");
				String dscategoria = rs.getString("DS_CATEGORIA");
				
				despesa = new Despesa(cd, dataVencimento, descricao, valor);
				Categoria categoria = new Categoria(cdcategoria, dscategoria);
				despesa.setCategoria(categoria);
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
		return despesa;			
	}

	@Override
	public List<Despesa> listar() {
		List<Despesa> lista = new ArrayList<Despesa>();	
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_FINTECH_DESPESA INNER JOIN TB_FINTECH_CATEGORIA ON TB_FINTECH_DESPESA.CD_OBJETIVO = TB_FINTECH_CATEGORIA.CD_CATEGORIA");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				int cd = rs.getInt("CD_DESPESA");
				String descricao = rs.getString("DS_DESPESA");
				double valor = rs.getDouble("NR_VALOR");
				java.sql.Date data = rs.getDate("DT_TRANSACAO");
				Calendar dataVencimento = Calendar.getInstance();
				dataVencimento.setTimeInMillis(data.getTime());
				int cdcategoria = rs.getInt("CD_CATEGORIA");
				String dscategoria = rs.getString("DS_CATEGORIA");
				
				Despesa despesa = new Despesa(cd, dataVencimento, descricao, valor);
				Categoria categoria = new Categoria(cdcategoria, dscategoria);
				despesa.setCategoria(categoria);
				
				lista.add(despesa);
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
