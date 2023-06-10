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
import br.com.fiap.fintech.bean.ObjetivoFinanceiro;
import br.com.fiap.fintech.dao.ObjetivoFinanceiroDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;

@WebServlet("/objetivo")
public class ObjetivoServlet extends HttpServlet{
	
private static final long serialVersionUID = 1L;
	
	private ObjetivoFinanceiroDAO objetivoDAO;
	
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
		try {
			String descricao = request.getParameter("descricao");
			String nome = request.getParameter("nome");
			double meta = Double.parseDouble(request.getParameter("meta"));
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataPrazo = Calendar.getInstance();
			dataPrazo.setTime(format.parse(request.getParameter("dataPrazo")));
			
			ObjetivoFinanceiro objetivo = new ObjetivoFinanceiro(0, descricao, nome, meta, dataPrazo);
			
			objetivoDAO.cadastrar(objetivo);
			
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
			String descricao = request.getParameter("descricao");
			String nome = request.getParameter("nome");
			double meta = Double.parseDouble(request.getParameter("meta"));
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataPrazo = Calendar.getInstance();
			dataPrazo.setTime(format.parse(request.getParameter("dataPrazo")));
			
			ObjetivoFinanceiro objetivo = new ObjetivoFinanceiro(codigo, descricao, nome, meta, dataPrazo);
			
			objetivoDAO.cadastrar(objetivo);
			
			
			request.setAttribute("msg", "Objetivo atualizado!");
			
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
			objetivoDAO.remover(codigo);
			request.setAttribute("msg", "Objetivo removido!");
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
		int id = Integer.parseInt(request.getParameter("codigo"));
		ObjetivoFinanceiro objetivo = null;
		try {
			objetivo = objetivoDAO.buscar(id);
		} catch (DBException e) {
			e.printStackTrace();
		}
		request.setAttribute("despesa", objetivo);
		request.getRequestDispatcher("edicao-objetivo.jsp").forward(request, response);
		
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ObjetivoFinanceiro> lista = objetivoDAO.listar();
		request.setAttribute("objetivos", lista);
		request.getRequestDispatcher("lista-objetivo.jsp").forward(request, response);
		
	}
	
	private void abrirFormCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("cadastro-objetivo.jsp").forward(request, response);
	}


}
