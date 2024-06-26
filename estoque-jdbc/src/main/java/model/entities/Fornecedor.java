package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Fornecedor implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private Date datacadastro;
	private String email;
	private String endereco;
	private String telefone;
	
	public Fornecedor() {
		
	}
	
	
	public Fornecedor(Integer id, String nome, Date datacadastro, String email, String endereco, String telefone) {
		
		this.id = id;
		this.nome = nome;
		this.datacadastro = datacadastro;
		this.email = email;
		this.endereco = endereco;
		this.telefone = telefone;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDatacadastro() {
		return datacadastro;
	}
	public void setDatacadastro(Date datacadastro) {
		this.datacadastro = datacadastro;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
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
		Fornecedor other = (Fornecedor) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Fornecedor [id=" + id + ", nome=" + nome + ", datacadastro=" + datacadastro + ", email=" + email
				+ ", endereco=" + endereco + ", telefone=" + telefone + "]";
	} 
	
	

}
