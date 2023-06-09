package br.com.fiap.fintech.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.fintech.bo.EmailBO;
import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.factory.DAOFactory;
import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.exception.EmailException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	private UsuarioDAO dao;
	private EmailBO bo;
	
	public LoginServlet() {
        dao = DAOFactory.getUsuarioDAO();
        bo = new EmailBO();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		Usuario usuario = new Usuario(email, senha);
		
		if (dao.validarUsuario(usuario)) {
			HttpSession session = request.getSession();
			session.setAttribute("user", email);
			String mensagem = "Um login foi realizado";
			try {
				bo.enviarEmail(email, "Login Realizado", mensagem);
			} catch (EmailException e) {
				e.printStackTrace();
			}
		}else {
			request.setAttribute("erro", "Usuário e/ou senha inválidos");
		}
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}
}
