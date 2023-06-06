package br.com.fiap.fintech.bean;

import java.util.Calendar;

public class ObjetivoFinanceiro {
	
	private int codigo;
	
	private String nome;
	
	private String descricao;
	
	private double meta;
	
	private Calendar prazo;
	
	private Usuario usuario;

	public ObjetivoFinanceiro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ObjetivoFinanceiro(int codigo, String nome, String descricao, double meta, Calendar prazo) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.meta = meta;
		this.prazo = prazo;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getMeta() {
		return meta;
	}

	public void setMeta(double meta) {
		this.meta = meta;
	}

	public Calendar getPrazo() {
		return prazo;
	}

	public void setPrazo(Calendar prazo) {
		this.prazo = prazo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}
