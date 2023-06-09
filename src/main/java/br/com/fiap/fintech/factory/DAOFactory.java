package br.com.fiap.fintech.factory;

import br.com.fiap.fintech.dao.CartaoDAO;
import br.com.fiap.fintech.dao.CategoriaDAO;
import br.com.fiap.fintech.dao.DespesaDAO;
import br.com.fiap.fintech.dao.ObjetivoFinanceiroDAO;
import br.com.fiap.fintech.dao.ReceitaDAO;
import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.dao.impl.OracleCartaoDAO;
import br.com.fiap.fintech.dao.impl.OracleCategoriaDAO;
import br.com.fiap.fintech.dao.impl.OracleDespesaDAO;
import br.com.fiap.fintech.dao.impl.OracleObjetivoFinanceiroDAO;
import br.com.fiap.fintech.dao.impl.OracleReceitaDAO;
import br.com.fiap.fintech.dao.impl.OracleUsuarioDAO;

public class DAOFactory {
	
	public static CartaoDAO getCartaoDAO() {
		return new OracleCartaoDAO();
	}
	public static CategoriaDAO getCategoriaDAO() {
		return new OracleCategoriaDAO();
	}
	public static DespesaDAO getDespesaDAO() {
		return new OracleDespesaDAO();
	}
	public static ObjetivoFinanceiroDAO getFinanceiroDAO() {
		return new OracleObjetivoFinanceiroDAO();
	}
	public static ReceitaDAO getReceitaDAO() {
		return new OracleReceitaDAO();
	}
	public static UsuarioDAO getUsuarioDAO() {
		return new OracleUsuarioDAO();
	}

}
