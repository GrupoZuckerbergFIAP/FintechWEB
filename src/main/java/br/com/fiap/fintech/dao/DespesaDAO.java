package br.com.fiap.fintech.dao;

import java.util.List;
import br.com.fiap.fintech.bean.Despesa;
import br.com.fiap.fintech.exception.DBException;

public interface DespesaDAO {
	
	void cadastrar(Despesa despesa) throws DBException;
	
	void editar(Despesa despesa)throws DBException;
	
	void remover(int codigo)throws DBException;
	
	Despesa buscar(int codigo) throws DBException;
	
	List<Despesa> listar();

}
