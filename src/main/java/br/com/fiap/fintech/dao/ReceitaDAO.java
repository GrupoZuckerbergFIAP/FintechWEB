package br.com.fiap.fintech.dao;

import java.util.List;
import br.com.fiap.fintech.bean.Receita;
import br.com.fiap.fintech.exception.DBException;

public interface ReceitaDAO {
	
	void cadastrar(Receita receita) throws DBException;
	
	void editar(Receita receita)throws DBException;
	
	void remover(int codigo)throws DBException;
	
	Receita buscar(int codigo) throws DBException;
	
	List<Receita> listar();

}
