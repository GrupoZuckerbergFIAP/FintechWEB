package br.com.fiap.fintech.dao;

import java.util.List;

import br.com.fiap.fintech.bean.Cartao;
import br.com.fiap.fintech.exception.DBException;

public interface CartaoDAO {
	
	void cadastrar(Cartao cartao) throws DBException;
	
	void editar(Cartao cartao)throws DBException;
	
	void remover(int codigo)throws DBException;
	
	Cartao buscar(int codigo) throws DBException;
	
	List<Cartao> listar();
	
	

}
