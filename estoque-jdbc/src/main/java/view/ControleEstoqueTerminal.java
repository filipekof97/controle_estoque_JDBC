package view;

import java.util.Date;
import java.util.Scanner;
import model.dao.DaoFactory;
import model.dao.FornecedorDao;
import model.dao.ProdutoDao;
import model.entities.Fornecedor;
import model.entities.Produto;

public class ControleEstoqueTerminal {
	
	public static void executaMenuTerminal() {
		
				
		Scanner sc = new Scanner(System.in);
		
		Integer opcaoSelecionada = 0;
		
		do {			
			
			
			 System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			 System.out.print("##----Controle de Estoque----##\n");
		     System.out.print("|-----------------------------|\n");
		     System.out.print("| Opção 1 - Produto           |\n");
		     System.out.print("| Opção 2 - Fornecedor        |\n");		    
		     System.out.print("| Opção 3 - Sair              |\n");
		     System.out.print("|-----------------------------|\n");
		     System.out.print("Digite uma opção: ");

			
              opcaoSelecionada = sc.nextInt();
              
              if (opcaoSelecionada == 1) {
            	  executaMenuProduto();
              } else if (opcaoSelecionada == 2) {
            	  executaMenuFornecedor();
              }
			
			
			
		} while ( opcaoSelecionada != 3);
		
		sc.close();
		
	}
	
	private static void executaMenuProduto() {
		
		ProdutoDao produtoDao = DaoFactory.createProdutoDao();	
		FornecedorDao fornecedorDao = DaoFactory.createFornecedorDao();
		
		Scanner sc = new Scanner(System.in);
		
		int opcaoSelecionada;
		
		do {	
						
			 System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");			 
			 System.out.print("Produtos:\n");
		     System.out.print("|-----------------------------|\n");
		     System.out.print("| Opção 1 - Listar            |\n");
		     System.out.print("| Opção 2 - Incluir           |\n");		    
		     System.out.print("| Opção 3 - Alterar           |\n");
		     System.out.print("| Opção 4 - Deletar           |\n");
		     System.out.print("| Opção 5 - Voltar            |\n");
		     System.out.print("|-----------------------------|\n");
		     System.out.print("Digite uma opção: ");

			
             opcaoSelecionada = sc.nextInt();
             
             if (opcaoSelecionada == 1) {
            	 
            	 produtoDao.findAll().forEach(x -> System.out.println(x));
            	 System.out.println("Aperte qualquer tecla e ENTER para voltar ao menu....");
            	 sc.next();
            	 sc.nextLine();
           	  
             } else if (opcaoSelecionada == 2) {
            	 
            	 Produto produto = new Produto();
            	 
            	 System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            	 System.out.println("Insira os dados do Produto");
            	 
            	 System.out.print("Descrição: ");
            	 produto.setDescricao(sc.next());
            	 
            	 System.out.print("Marca: ");
            	 produto.setMarca(sc.next());
            	 
            	 System.out.print("Unidade de Medida: ");
            	 produto.setUnidadeMedida(sc.next());
            	 
            	 System.out.print("Preço de Custo: ");
            	 produto.setPrecoCusto(sc.nextDouble());
            	 
            	 System.out.print("Preço de Venda: ");
            	 produto.setPrecoVenda(sc.nextDouble());
            	 
            	 System.out.print("Quantidade em estoque: ");
            	 produto.setQuantidade(sc.nextDouble());
            	 
            	 produto.setDatacadastro(new Date());
            	 
            	 System.out.print("Insira o ID do fornecedor: ");
            	 Integer fornecedorID = sc.nextInt();
            	 
            	 Fornecedor fornecedor = fornecedorDao.findById(fornecedorID);            	 
            	 
            	 if (fornecedor != null) {
            		 produto.setFornecedor(fornecedor);
            		 produtoDao.insert(produto);
            	 }            	 
            	 
            	 
             } else if (opcaoSelecionada == 3) {
            	 
            	 produtoDao.findAll().forEach(x -> System.out.println(x));
            	 
            	 System.out.println("Insira o ID do produto que sera alterado: ");
            	 Integer produtoID = sc.nextInt();
            	 
            	 Produto produto = produtoDao.findById(produtoID);
            	 
            	 if (produto == null) {
            		 System.out.println("Produto não encontrado");
            		 System.out.println("Aperte qualquer tecla e ENTER para voltar ao menu....");
                	 sc.next();
            		 
            	 } else {           		 
            		                 	 
                	 System.out.print("Descrição: ");
                	 produto.setDescricao(sc.next());
                	 
                	 System.out.print("Marca: ");
                	 produto.setMarca(sc.next());
                	 
                	 System.out.print("Unidade de Medida: ");
                	 produto.setUnidadeMedida(sc.next());
                	 
                	 System.out.print("Preço de Custo: ");
                	 produto.setPrecoCusto(sc.nextDouble());
                	 
                	 System.out.print("Preço de Venda: ");
                	 produto.setPrecoVenda(sc.nextDouble());
                	 
                	 System.out.print("Quantidade em estoque: ");
                	 produto.setQuantidade(sc.nextDouble());
                	 
                	 produto.setDatacadastro(new Date());
                	 
                	 System.out.print("Insira o ID do fornecedor: ");
                	 Integer fornecedorID = sc.nextInt();
                	 
                	 Fornecedor fornecedor = fornecedorDao.findById(fornecedorID);            	 
                	 
                	 if (fornecedor != null) {
                		 produto.setFornecedor(fornecedor);
                		 produtoDao.update(produto);
                	 }    
                	 
                	 sc.nextLine();
            	 }
            	 
            	 
            	 
             } else if (opcaoSelecionada == 4) {
            	 produtoDao.findAll().forEach(x -> System.out.println(x));
            	 
            	 System.out.println("Insira o ID do produto que sera deletar: ");
            	 Integer produtoID = sc.nextInt();
            	 
            	 produtoDao.deleteById(produtoID);
           	  
             }
			
			
			
		} while ( opcaoSelecionada != 5);
		
		//sc.nextLine();
		//sc.close();
		
	}
	
	private static void executaMenuFornecedor() {
		
		FornecedorDao fornecedorDao = DaoFactory.createFornecedorDao();
		
		Scanner sc = new Scanner(System.in);
		
		int opcaoSelecionada;
		
		do {	
						
			 System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");			 
			 System.out.print("Fornecedores:\n");
		     System.out.print("|-----------------------------|\n");
		     System.out.print("| Opção 1 - Listar            |\n");
		     System.out.print("| Opção 2 - Incluir           |\n");		    
		     System.out.print("| Opção 3 - Alterar           |\n");
		     System.out.print("| Opção 4 - Deletar           |\n");
		     System.out.print("| Opção 5 - Voltar            |\n");
		     System.out.print("|-----------------------------|\n");
		     System.out.print("Digite uma opção: ");

			
             opcaoSelecionada = sc.nextInt();
             
             if (opcaoSelecionada == 1) {
            	 
            	 fornecedorDao.findAll().forEach(x -> System.out.println(x));
            	 System.out.println("Aperte qualquer tecla e ENTER para voltar ao menu....");
            	 sc.next();
           	  
             } else if (opcaoSelecionada == 2) {
            	 
            	 Fornecedor fornecedor = new Fornecedor();
            	 
            	 System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            	 System.out.println("Insira os dados do Fornecedor");
            	 
            	 System.out.println("Nome: ");
            	 fornecedor.setNome(sc.next());
            	 
            	 System.out.println("Telefone: ");
            	 fornecedor.setTelefone(sc.next());
            	 
            	 System.out.println("Endereco: ");
            	 fornecedor.setEndereco(sc.next());
            	 
            	 System.out.println("Email: ");
            	 fornecedor.setEmail(sc.next());
            	 
            	 fornecedor.setDatacadastro(new Date());            	 
            	 
            	 fornecedorDao.insert(fornecedor);
            	 
             } else if (opcaoSelecionada == 3) {
            	 
            	 fornecedorDao.findAll().forEach(x -> System.out.println(x));
            	 
            	 System.out.println("Insira o ID do fornecedor que sera alterado: ");
            	 Integer fornecedorID = sc.nextInt();
            	 
            	 Fornecedor fornecedor = fornecedorDao.findById(fornecedorID);
            	 
            	 if (fornecedor == null) {
            		 System.out.println("Fornecedor não encontrado");
            		 System.out.println("Aperte qualquer tecla e ENTER para voltar ao menu....");
                	 sc.next();
            		 
            	 } else {
            		 
            		 System.out.println("Nome: ");
                	 fornecedor.setNome(sc.next());
                	 
                	 System.out.println("Telefone: ");
                	 fornecedor.setTelefone(sc.next());
                	 
                	 System.out.println("Endereco: ");
                	 fornecedor.setEndereco(sc.next());
                	 
                	 System.out.println("Email: ");
                	 fornecedor.setEmail(sc.next());
            		 
                	 fornecedorDao.update(fornecedor);                	 
            	 }
            	 
            	 sc.nextLine();
            	 
            	 
            	 
             } else if (opcaoSelecionada == 4) {
            	 fornecedorDao.findAll().forEach(x -> System.out.println(x));
            	 
            	 System.out.println("Insira o ID do fornecedor que sera deletar: ");
            	 Integer fornecedorID = sc.nextInt();
            	 
            	 fornecedorDao.deleteById(fornecedorID);
           	  
             }
			
			
			
		} while ( opcaoSelecionada != 5);
		
		//sc.nextLine();
		//sc.close();
		
	}
	
	

}
