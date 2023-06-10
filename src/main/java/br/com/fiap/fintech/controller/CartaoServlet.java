package br.com.fiap.fintech.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.fintech.bean.Cartao;
import br.com.fiap.fintech.bean.Categoria;
import br.com.fiap.fintech.dao.CartaoDAO;
import br.com.fiap.fintech.dao.CategoriaDAO;
import br.com.fiap.fintech.factory.DAOFactory;
import br.com.fiap.fintech.exception.DBException;
import java.text.SimpleDateFormat;


@WebServlet("/cartao")
public class CartaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CartaoDAO cartaoDao;
	private CategoriaDAO categoriaDao;
	
	public void init() throws ServletException{
		super.init();
		cartaoDao = DAOFactory.getCartaoDAO();
		categoriaDao = DAOFactory.getCategoriaDAO();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "cadastrarCartao":
			cadastrar(request, response);
			break;
		case "editarCartao":
			editar(request,response);
			break;
		case "excluirCartao":
			excluir(request, response);
			break;
		}
	}
	
	private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			String nome = request.getParameter("nome");
			double numero = Double.parseDouble(request.getParameter("numero"));
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar vencimento = Calendar.getInstance();
			vencimento.setTime(format.parse(request.getParameter("vencimento")));
			String bandeira = request.getParameter("bandeira");
			int cvv = Integer.parseInt(request.getParameter("cvv"));
			int codigoCategoria = Integer.parseInt(request.getParameter("categoria"));
			String cpf = request.getParameter("cpf");
			
			Categoria categoria = new Categoria();
			categoria.setCodigo(codigoCategoria);
			
			Cartao cartao = new Cartao(0, nome, numero, vencimento, bandeira, cvv, cpf);
			cartao.setCategoria(categoria);
			
			cartaoDao.cadastrar(cartao);
			
		}catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			String nome = request.getParameter("nome");
			double numero = Double.parseDouble(request.getParameter("numero"));
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataDeVencimento = Calendar.getInstance();
			dataDeVencimento.setTime(format.parse(request.getParameter("dataDeVencimento")));
			String bandeira = request.getParameter("bandeira");
			int cvv = Integer.parseInt(request.getParameter("cvv"));
			int codigoCategoria = Integer.parseInt(request.getParameter("categoria"));
			String cpfTitular = request.getParameter("cpfTitular");
			
			Categoria categoria = new Categoria();
			categoria.setCodigo(codigoCategoria);
			
			Cartao cartao = new Cartao(codigo, nome, numero, dataDeVencimento, bandeira, cvv, cpfTitular);
			cartao.setCategoria(categoria);
			
			cartaoDao.editar(cartao);
			
			request.setAttribute("msg", "Cartao atualizado!");
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
			cartaoDao.remover(codigo);
			request.setAttribute("msg", "Cartão removido!");
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
		case "listarCartao":
			listar(request, response);
			break;
		case "abrir-form-edicaoCartao":
			abrirFormEdicao(request, response);
			break;
		case "abrir-form-cadastroCartao":
			abrirFormCadastro(request, response);
			break;
		}	
	}

	private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("codigo"));
		Cartao cartao = null;
		try {
			cartao = cartaoDao.buscar(id);
		} catch (DBException e) {
			e.printStackTrace();
		}
		request.setAttribute("cartao", cartao);
		carregarOpcoesCategoria(request);
		request.getRequestDispatcher("edicao-cartao.jsp").forward(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Cartao> lista = cartaoDao.listar();
		request.setAttribute("cartoes", lista);
		request.getRequestDispatcher("lista-cartao.jsp").forward(request, response);
		
	}
	
	private void abrirFormCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		carregarOpcoesCategoria(request);
		request.getRequestDispatcher("cadastro-cartao.jsp").forward(request, response);
	}

	private void carregarOpcoesCategoria(HttpServletRequest request) {
		List<Categoria> lista = null;
		try {
			lista = categoriaDao.listar("TB_FINTECH_CARTAO");
		} catch (DBException e) {
			e.printStackTrace();
		}
		request.setAttribute("categorias", lista);
	}

	}
