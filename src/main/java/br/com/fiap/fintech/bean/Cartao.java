package br.com.fiap.fintech.bean;

import java.util.Calendar;

public class Cartao {
	
	private int codigo;
	
	private String nome;
	
	private double numero;
	
	private Calendar dataDeVencimento;
	
	private String bandeira;
	
	private int cvv;
	
	private Categoria categoria;
	
	private String cpfTitular;
	
	private Usuario usuario;

	public Cartao(int codigo, String nome, double numero, Calendar dataDeVencimento, String bandeira, int cvv,
			Categoria categoria, String cpfTitular) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.numero = numero;
		this.dataDeVencimento = dataDeVencimento;
		this.bandeira = bandeira;
		this.cvv = cvv;
		this.cpfTitular = cpfTitular;
	}

	public Cartao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getNumero() {
		return numero;
	}

	public void setNumero(double numero) {
		this.numero = numero;
	}

	public Calendar getDataDeVencimento() {
		return dataDeVencimento;
	}

	public void setDataDeVencimento(Calendar dataDeVencimento) {
		this.dataDeVencimento = dataDeVencimento;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getCpfTitular() {
		return cpfTitular;
	}

	public void setCpfTitular(String cpfTitular) {
		this.cpfTitular = cpfTitular;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	

}
