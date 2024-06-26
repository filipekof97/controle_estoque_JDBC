package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.DaoFactory;
import model.dao.FornecedorDao;
import model.dao.ProdutoDao;
import model.entities.Fornecedor;
import model.entities.Produto;

public class ProdutoDaoJDBC implements ProdutoDao {
	
	private Connection conn;
	
	public ProdutoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Produto obj) {
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("INSERT INTO produto (descricao, unidademedida, quantidade, fornecedorid, datacadastro, precocusto, precovenda, marca) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getDescricao());
			st.setString(2, obj.getUnidadeMedida());
			st.setDouble(3, obj.getQuantidade());
			st.setInt(4, obj.getFornecedor().getId());
			st.setDate(5, new java.sql.Date( obj.getDatacadastro().getTime() ) );
			st.setDouble(6, obj.getPrecoCusto());
			st.setDouble(7, obj.getPrecoVenda());
			st.setString(8, obj.getMarca());		
			
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
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
	public void update(Produto obj) {
		
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("UPDATE produto" 
					                   + " SET descricao = ?, unidademedida = ?, quantidade = ?, fornecedorid = ?, datacadastro = ?, precocusto = ?, precovenda = ?, marca = ?" 
					                   + " WHERE produto.id = ?", Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getDescricao());
			st.setString(2, obj.getUnidadeMedida());
			st.setDouble(3, obj.getQuantidade());
			st.setInt(4, obj.getFornecedor().getId());
			st.setDate(5, new java.sql.Date( obj.getDatacadastro().getTime() ) );
			st.setDouble(6, obj.getPrecoCusto());
			st.setDouble(7, obj.getPrecoVenda());
			st.setString(8, obj.getMarca());	
			st.setInt(9, obj.getId());
			
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
			
			st = conn.prepareStatement("DELETE FROM produto WHERE id = ?");
			st.setInt(1, id);			
			st.executeUpdate();
		
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());	
			
		} finally {
			DB.closeStatement(st);			
		}		
		
	}

	@Override
	public Produto findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM produto WHERE id = ?" );
					                
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				FornecedorDao fornecedorDao = DaoFactory.createFornecedorDao();
				Fornecedor fornecedor = fornecedorDao.findById(rs.getInt("fornecedorid"));				
				Produto obj = new Produto( rs.getInt("id"),
						                   rs.getString("descricao"),
						                   rs.getDate("datacadastro"),
						                   fornecedor,
						                   rs.getDouble("quantidade"),
						                   rs.getString("unidademedida"),
						                   rs.getString("marca"),
						                   rs.getDouble("precocusto"),
						                   rs.getDouble("precovenda"));
				return obj;
				
				
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
	public List<Produto> findAll() {

		PreparedStatement st = null;
		ResultSet rs = null;
		List<Produto> produtos = new ArrayList<>();
		
		try {
			st = conn.prepareStatement("SELECT * FROM produto ORDER BY descricao");			
			
			rs = st.executeQuery();
			
			FornecedorDao fornecedorDao = DaoFactory.createFornecedorDao();
			Map<Integer, Fornecedor> departments = new HashMap<>();
			
			while (rs.next()) {	
				
				Fornecedor fornecedor = departments.get(rs.getInt("fornecedorid"));
				
				if (fornecedor == null) {
					fornecedor = fornecedorDao.findById(rs.getInt("fornecedorid"));
					departments.put(fornecedor.getId(), fornecedor);
				}
				
				Produto obj = new Produto( rs.getInt("id"),
		                                   rs.getString("descricao"),
						                   rs.getDate("datacadastro"),
						                   fornecedor,
						                   rs.getDouble("quantidade"),
						                   rs.getString("unidademedida"),
						                   rs.getString("marca"),
						                   rs.getDouble("precocusto"),
						                   rs.getDouble("precovenda"));				
				
				produtos.add(obj);							
			}
			
			return produtos;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());	
			
		} finally {			
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
