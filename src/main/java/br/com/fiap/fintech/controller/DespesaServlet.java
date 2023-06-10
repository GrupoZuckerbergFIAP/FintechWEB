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
import br.com.fiap.fintech.bean.Despesa;
import br.com.fiap.fintech.dao.CategoriaDAO;
import br.com.fiap.fintech.dao.DespesaDAO;
import br.com.fiap.fintech.exception.DBException;
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
		try {
			String descricao = request.getParameter("descricao");
			double valor = Double.parseDouble(request.getParameter("valor"));
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataTransacao = Calendar.getInstance();
			dataTransacao.setTime(format.parse(request.getParameter("dataTransacao")));
			int codigoCategoria = Integer.parseInt(request.getParameter("categoria"));
			
			Categoria categoria = new Categoria();
			categoria.setCodigo(codigoCategoria);
			
			Despesa despesa = new Despesa(0, dataTransacao, descricao, valor);
			despesa.setCategoria(categoria);
			
			despesaDao.cadastrar(despesa);
			
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
			
			Despesa despesa = new Despesa(codigo, dataTransacao, descricao, valor);
			despesa.setCategoria(categoria);
			
			despesaDao.editar(despesa);
			
			request.setAttribute("msg", "Despesa atualizado!");
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
			despesaDao.remover(codigo);
			request.setAttribute("msg", "Despesa removido!");
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
		int id = Integer.parseInt(request.getParameter("codigo"));
		Despesa despesa = null;
		try {
			despesa = despesaDao.buscar(id);
		} catch (DBException e) {
			e.printStackTrace();
		}
		request.setAttribute("despesa", despesa);
		carregarOpcoesCategoria(request);
		request.getRequestDispatcher("edicao-despesa.jsp").forward(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Despesa> lista = despesaDao.listar();
		request.setAttribute("despesas", lista);
		request.getRequestDispatcher("lista-despesa.jsp").forward(request, response);
		
	}
	
	private void abrirFormCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		carregarOpcoesCategoria(request);
		request.getRequestDispatcher("cadastro-despesa.jsp").forward(request, response);
	}

	private void carregarOpcoesCategoria(HttpServletRequest request) {
		List<Categoria> lista = null;
		try {
			lista = categoriaDao.listar("TB_FINTECH_DEPESA");
		} catch (DBException e) {
			e.printStackTrace();
		}
		request.setAttribute("categorias", lista);
	}

}
