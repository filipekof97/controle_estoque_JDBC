package model.entities;

import java.util.Date;
import java.util.Objects;

public class Produto {
	
	private Integer id;	
	private String descricao;
	private Date datacadastro;	
	private Fornecedor fornecedor;	
	private Double quantidade;	
	private String unidadeMedida;		
	private String marca;
	private Double precoCusto;
	private Double precoVenda;
	
	public Produto() {
		
	}	
	
	public Produto(Integer id, String descricao, Date datacadastro, Fornecedor fornecedor, Double quantidade,
			String unidadeMedida, String marca, Double precoCusto, Double precoVenda) {
		
		this.id = id;
		this.descricao = descricao;
		this.datacadastro = datacadastro;
		this.fornecedor = fornecedor;
		this.quantidade = quantidade;
		this.unidadeMedida = unidadeMedida;
		this.marca = marca;
		this.precoCusto = precoCusto;
		this.precoVenda = precoVenda;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Date getDatacadastro() {
		return datacadastro;
	}


	public void setDatacadastro(Date datacadastro) {
		this.datacadastro = datacadastro;
	}


	public Fornecedor getFornecedor() {
		return fornecedor;
	}


	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}


	public Double getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}


	public String getUnidadeMedida() {
		return unidadeMedida;
	}


	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public Double getPrecoCusto() {
		return precoCusto;
	}


	public void setPrecoCusto(Double precoCusto) {
		this.precoCusto = precoCusto;
	}


	public Double getPrecoVenda() {
		return precoVenda;
	}


	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}


	@Override
	public String toString() {
		return "Produto [id=" + id + ", descricao=" + descricao + ", datacadastro=" + datacadastro + ", fornecedor="
				+ fornecedor + ", quantidade=" + quantidade + ", unidadeMedida=" + unidadeMedida + ", marca=" + marca
				+ ", precoCusto=" + precoCusto + ", precoVenda=" + precoVenda + "]";
	}
	
	

}
