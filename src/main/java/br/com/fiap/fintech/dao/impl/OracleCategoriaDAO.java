package br.com.fiap.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.fintech.bean.Categoria;
import br.com.fiap.fintech.dao.CategoriaDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;

public class OracleCategoriaDAO implements CategoriaDAO{

	private Connection conexao;
	
	@Override
	public List<Categoria> listar(String tabela) throws DBException {
		List<Categoria> lista = new ArrayList<Categoria>();	
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_FINTECH_CATEGORIA WHERE NM_TABELA = ?");
			stmt.setString(1, tabela);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				int cd = rs.getInt("CD_CATEGORIA");
				String descricao = rs.getString("DS_CATEGORIA");
				
				Categoria categoria = new Categoria(cd,descricao);
				
				lista.add(categoria);
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
