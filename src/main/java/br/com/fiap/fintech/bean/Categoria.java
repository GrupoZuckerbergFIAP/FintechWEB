package br.com.fiap.fintech.bean;

public class Categoria {
	
	private int codigo;
	
	private String nomeTabela;
	
	private String descricao;

	public Categoria() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Categoria(int codigo, String nomeTabela, String descricao) {
		super();
		this.codigo = codigo;
		this.nomeTabela = nomeTabela;
		this.descricao = descricao;
	}
	
	public Categoria(int codigo, String descricao) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNomeTabela() {
		return nomeTabela;
	}

	public void setNomeTabela(String nomeTabela) {
		this.nomeTabela = nomeTabela;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
