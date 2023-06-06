package br.com.fiap.fintech.dao;

import java.util.List;
import br.com.fiap.fintech.bean.ObjetivoFinanceiro;
import br.com.fiap.fintech.exception.DBException;

public interface ObjetivoFinanceiroDAO {
	
	void cadastrar(ObjetivoFinanceiro objetivoFinanceiro) throws DBException;
	
	void editar(ObjetivoFinanceiro objetivoFinanceiro)throws DBException;
	
	void remover(int codigo)throws DBException;
	
	ObjetivoFinanceiro buscar(int codigo) throws DBException;
	
	List<ObjetivoFinanceiro> listar();

}
