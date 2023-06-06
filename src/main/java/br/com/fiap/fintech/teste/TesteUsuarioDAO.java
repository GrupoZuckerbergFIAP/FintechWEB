package br.com.fiap.fintech.teste;

import java.util.Calendar;

import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;


public class TesteUsuarioDAO {
	
	public static void main(String[] args) {
		
		UsuarioDAO dao = DAOFactory.getUsuarioDAO();
		
		Usuario usuario = new Usuario("FELIPEjd@GMAIL.COM", "123", "123.123.123-12", "FELIPE", 123123123, Calendar.getInstance(), "masculino");
		
		try {
			dao.cadastrarUsuario(usuario);
			System.out.println("Produto cadastrado.");
		}catch(DBException e) {
			e.printStackTrace();
		}
		
	}
	
	
}


		
			
			

