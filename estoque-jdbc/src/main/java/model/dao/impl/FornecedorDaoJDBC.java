package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.FornecedorDao;
import model.entities.Fornecedor;

public class FornecedorDaoJDBC implements FornecedorDao {
	
	private Connection conn;
	
	public FornecedorDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Fornecedor obj) {
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("INSERT INTO fornecedor (nome, email, telefone, endereco, datacadastro) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getNome());
			st.setString(2, obj.getEmail());
			st.setString(3, obj.getTelefone());
			st.setString(4, obj.getEndereco());
			st.setDate(5, new java.sql.Date( obj.getDatacadastro().getTime() ) );
			
			int linhasAfetadas = st.executeUpdate();
			
			
			if (linhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					obj.setId(rs.getInt(1));
				}
				
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Unexpected error! No rows affected!");
			}				
			
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());	
			
		} finally {
			DB.closeStatement(st);	
		}
		
	}

	@Override
	public void update(Fornecedor obj) {
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("UPDATE fornecedor SET nome = ?, email = ?, telefone = ?, endereco = ?, datacadastro = ? WHERE id = ?");
			
			st.setString(1, obj.getNome());
			st.setString(2, obj.getEmail());
			st.setString(3, obj.getTelefone());
			st.setString(4, obj.getEndereco());
			st.setDate(5, new java.sql.Date( obj.getDatacadastro().getTime() ) );
			st.setInt(6, obj.getId());
			
			st.executeUpdate();	
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());	
			
		} finally {
			DB.closeStatement(st);	
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("DELETE FROM fornecedor WHERE id = ?");
			st.setInt(1, id);			
			st.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		} finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Fornecedor findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM fornecedor WHERE id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Fornecedor fornecedor = intanciarFornecedor(rs);
				return fornecedor;
				
				
			}
			return null;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());	
			
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public List<Fornecedor> findAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Fornecedor> fornecedores = new ArrayList<>();
		
		try {
			st = conn.prepareStatement("SELECT * FROM fornecedor ORDER BY nome");			
			
			rs = st.executeQuery();			
		
			
			while (rs.next()) {					
				fornecedores.add(intanciarFornecedor(rs));							
			}
			
			return fornecedores;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());	
			
		} finally {			
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	private Fornecedor intanciarFornecedor(ResultSet rs) throws SQLException {
		
		return new Fornecedor( rs.getInt("id"), 
				               rs.getString("nome"), 
				               rs.getDate("datacadastro"), 
				               rs.getString("email"), 
				               rs.getString("endereco"), 
				               rs.getString("telefone"));
	}

}
