package br.com.fiap.fintech.bean;

import java.util.Calendar;

import br.com.fiap.fintech.util.CriptografiaUtils;

public class Usuario {
	
	private String email;
	
	private String senha;
	
	private String cpf;
	
	private String nome;
	
	private int telefone;
	
	private Calendar dataDeNascimento;
	
	private String genero;
	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Usuario(String email, String senha, String cpf, String nome, int telefone,
			Calendar dataDeNascimento, String genero) {
		super();
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.dataDeNascimento = dataDeNascimento;
		this.genero = genero;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		try {
			this.senha = CriptografiaUtils.criptografar(senha);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTelefone() {
		return telefone;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

	public Calendar getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(Calendar dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}


}
