package br.com.fiap.fintech.bean;

import java.util.Calendar;

public abstract class Capital {
	
	private int codigo;
	
	private Calendar dataTransacao;
	
	private String descricao;
	
	private double valor;
	
	private Categoria categoria;
	
	private double total;
	
	private Usuario usuario;
	

	public Capital() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Capital(int codigo, Calendar dataTransacao, String descricao, double valor) {
		super();
		this.codigo = codigo;
		this.dataTransacao = dataTransacao;
		this.descricao = descricao;
		this.valor = valor;
	}



	public int getCodigo() {
		return codigo;
	}
	
	public Calendar getDataTransacao() {
		return dataTransacao;
	}

	public void setDataTransacao(Calendar dataTransacao) {
		this.dataTransacao = dataTransacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}



	public Categoria getCategoria() {
		return categoria;
	}



	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}



	public double getTotal() {
		return total;
	}



	public void setTotal(double total) {
		this.total = total;
	}



	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}
