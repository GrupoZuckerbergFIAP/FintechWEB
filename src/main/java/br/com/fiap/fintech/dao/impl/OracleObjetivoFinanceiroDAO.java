package br.com.fiap.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import br.com.fiap.fintech.bean.ObjetivoFinanceiro;
import br.com.fiap.fintech.dao.ObjetivoFinanceiroDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;

public class OracleObjetivoFinanceiroDAO implements ObjetivoFinanceiroDAO{
	
	private Connection conexao;

	@Override
	public void cadastrar(ObjetivoFinanceiro objetivoFinanceiro) throws DBException {
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO TB_FINTECH_OBJETIVO(CD_OBJETIVO, DS_OBJETIVO, NM_OBJETIVO, NR_META, DT_PRAZO, DS_EMAIL) VALUES (SQ_TB_FINTECH_OBJETIVO.NEXTVAL, ?, ?, ?, ?, ? )";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, objetivoFinanceiro.getDescricao());
			stmt.setString(2, objetivoFinanceiro.getNome());
			stmt.setDouble(3, objetivoFinanceiro.getMeta());
			java.sql.Date data = new java.sql.Date(objetivoFinanceiro.getPrazo().getTimeInMillis());
			stmt.setDate(4, data);
			stmt.setString(5, objetivoFinanceiro.getUsuario().getEmail());
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
	public void editar(ObjetivoFinanceiro objetivoFinanceiro) throws DBException {
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE TB_FINTECH_OBJETIVO SET DS_OBJETIVO = ?, NM_OBJETIVO = ?, NR_META = ?, DT_PRAZO = ?, CD_CATEGORIA = ?, DS_EMAIL = ? WHERE CD_OBJETIVO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, objetivoFinanceiro.getDescricao());
			stmt.setString(2, objetivoFinanceiro.getNome());
			stmt.setDouble(3, objetivoFinanceiro.getMeta());
			java.sql.Date data = new java.sql.Date(objetivoFinanceiro.getPrazo().getTimeInMillis());
			stmt.setDate(4, data);
			stmt.setInt(5, objetivoFinanceiro.getCodigo());
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
			String sql = "DELETE FROM TB_FINTECH_OBJETIVO WHERE CD_OBJETIVO";
			stmt = conexao.prepareStatement(sql);
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
	public ObjetivoFinanceiro buscar(int codigo) throws DBException {
		ObjetivoFinanceiro objetivo  = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_FINTECH_OBJETIVO WHERE CD_OBJETIVO = ?");
			stmt.setInt(1, codigo);
			rs = stmt.executeQuery();		
			
			if(rs.next()) {
				int cd = rs.getInt("CD_OBJETIVO");
				String descricao = rs.getString("DS_OBJETIVO");
				String nome = rs.getString("NM_OBJETIVO");
				double meta = rs.getDouble("NR_META");
				java.sql.Date data = rs.getDate("DT_PRAZO");
				Calendar dataVencimento = Calendar.getInstance();
				dataVencimento.setTimeInMillis(data.getTime());	
				
				objetivo = new ObjetivoFinanceiro(cd, descricao, nome, meta, dataVencimento);
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
		return objetivo;
	}

	@Override
	public List<ObjetivoFinanceiro> listar() {
		List<ObjetivoFinanceiro> lista = new ArrayList<ObjetivoFinanceiro>();	
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_FINTECH_OBJETIVO");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				int cd = rs.getInt("CD_OBJETIVO");
				String descricao = rs.getString("DS_OBJETIVO");
				String nome = rs.getString("NM_OBJETIVO");
				double meta = rs.getDouble("NR_META");
				java.sql.Date data = rs.getDate("DT_PRAZO");
				Calendar dataVencimento = Calendar.getInstance();
				dataVencimento.setTimeInMillis(data.getTime());	
				
				ObjetivoFinanceiro objetivo = new ObjetivoFinanceiro(cd, descricao, nome, meta, dataVencimento);
				
				lista.add(objetivo);
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
