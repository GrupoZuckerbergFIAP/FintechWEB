package br.com.fiap.fintech.dao;

import java.util.List;

import br.com.fiap.fintech.bean.Categoria;
import br.com.fiap.fintech.exception.DBException;

public interface CategoriaDAO {
	
	List<Categoria> listar(String tabela) throws DBException;
	;

}
