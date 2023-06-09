package br.com.fiap.fintech.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.fintech.dao.CategoriaDAO;
import br.com.fiap.fintech.dao.DespesaDAO;
import br.com.fiap.fintech.factory.DAOFactory;

@WebServlet("/despesa")
public class DespesaServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private DespesaDAO despesaDao;
	private CategoriaDAO categoriaDao;
	
	public void init() throws ServletException{
		super.init();
		despesaDao = DAOFactory.getDespesaDAO();
		categoriaDao = DAOFactory.getCategoriaDAO();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "cadastrarDespesa":
			cadastrar(request, response);
			break;
		case "editarDespesa":
			editar(request,response);
			break;
		case "excluirDespesa":
			excluir(request, response);
			break;
		}
	}
	
	private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "listarDespesa":
			listar(request, response);
			break;
		case "abrir-form-edicaoDespesa":
			abrirFormEdicao(request, response);
			break;
		case "abrir-form-cadastroDespesa":
			abrirFormCadastro(request, response);
			break;
		}	
	}

	private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}
	
	private void abrirFormCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	private void carregarOpcoesCategoria(HttpServletRequest request) {
		
	}

}
