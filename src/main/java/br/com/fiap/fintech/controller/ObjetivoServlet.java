package br.com.fiap.fintech.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.fintech.dao.CategoriaDAO;
import br.com.fiap.fintech.dao.ObjetivoFinanceiroDAO;
import br.com.fiap.fintech.factory.DAOFactory;

@WebServlet("/objetivo")
public class ObjetivoServlet extends HttpServlet{
	
private static final long serialVersionUID = 1L;
	
	private ObjetivoFinanceiroDAO objetivoDAO;
	private CategoriaDAO categoriaDao;
	
	public void init() throws ServletException{
		super.init();
		objetivoDAO = DAOFactory.getFinanceiroDAO();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "cadastrarObjetivo":
			cadastrar(request, response);
			break;
		case "editarObjetivo":
			editar(request,response);
			break;
		case "excluirObjetivo":
			excluir(request, response);
			break;
		}
	}
	
	private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	}

	private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "listarObjetivo":
			listar(request, response);
			break;
		case "abrir-form-edicaoObjetivo":
			abrirFormEdicao(request, response);
			break;
		case "abrir-form-cadastroObjetivo":
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
