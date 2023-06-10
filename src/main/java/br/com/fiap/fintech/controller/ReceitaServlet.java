package br.com.fiap.fintech.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.fiap.fintech.bean.Categoria;
import br.com.fiap.fintech.bean.Receita;
import br.com.fiap.fintech.dao.CategoriaDAO;
import br.com.fiap.fintech.dao.ReceitaDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;

@WebServlet("/receita")
public class ReceitaServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private ReceitaDAO receitaDAO;
	private CategoriaDAO categoriaDao;
	
	public void init() throws ServletException{
		super.init();
		receitaDAO = DAOFactory.getReceitaDAO();
		categoriaDao = DAOFactory.getCategoriaDAO();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "cadastrarReceita":
			cadastrar(request, response);
			break;
		case "editarReceita":
			editar(request,response);
			break;
		case "excluirReceita":
			excluir(request, response);
			break;
		}
	}
	
	private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			String descricao = request.getParameter("descricao");
			double valor = Double.parseDouble(request.getParameter("valor"));
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataTransacao = Calendar.getInstance();
			dataTransacao.setTime(format.parse(request.getParameter("dataTransacao")));
			int codigoCategoria = Integer.parseInt(request.getParameter("categoria"));
			
			Categoria categoria = new Categoria();
			categoria.setCodigo(codigoCategoria);
			
			Receita receita = new Receita(0, dataTransacao, descricao, valor);
			receita.setCategoria(categoria);
			
			receitaDAO.cadastrar(receita);
			
		}catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			String descricao = request.getParameter("descricao");
			double valor = Double.parseDouble(request.getParameter("valor"));
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataTransacao = Calendar.getInstance();
			dataTransacao.setTime(format.parse(request.getParameter("dataTransacao")));
			int codigoCategoria = Integer.parseInt(request.getParameter("categoria"));
			
			Categoria categoria = new Categoria();
			categoria.setCodigo(codigoCategoria);
			
			Receita receita = new Receita(codigo, dataTransacao, descricao, valor);
			receita.setCategoria(categoria);
			
			receitaDAO.editar(receita);
			
			request.setAttribute("msg", "Receita atualizado!");
		}catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		
		listar(request,response);
	}

	private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		try {
			receitaDAO.remover(codigo);
			request.setAttribute("msg", "Rceita removido!");
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		}
		listar(request,response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "listarReceita":
			listar(request, response);
			break;
		case "abrir-form-edicaoReceita":
			abrirFormEdicao(request, response);
			break;
		case "abrir-form-cadastroReceita":
			abrirFormCadastro(request, response);
			break;
		}	
	}

	private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("codigo"));
		Receita receita = null;
		try {
			receita = receitaDAO.buscar(id);
		} catch (DBException e) {
			e.printStackTrace();
		}
		request.setAttribute("receita", receita);
		carregarOpcoesCategoria(request);
		request.getRequestDispatcher("edicao-receita.jsp").forward(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Receita> lista = receitaDAO.listar();
		request.setAttribute("receitas", lista);
		request.getRequestDispatcher("lista-receita.jsp").forward(request, response);
		
	}
	
	private void abrirFormCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		carregarOpcoesCategoria(request);
		request.getRequestDispatcher("cadastro-receita.jsp").forward(request, response);
	}

	private void carregarOpcoesCategoria(HttpServletRequest request) {
		List<Categoria> lista = null;
		try {
			lista = categoriaDao.listar("TB_FINTECH_RECEITA");
		} catch (DBException e) {
			e.printStackTrace();
		}
		request.setAttribute("categorias", lista);
	}
}
