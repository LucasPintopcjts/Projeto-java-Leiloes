/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
       conn = new conectaDAO().connectDB(); // Conecta ao banco de dados

    String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
    
    try {
        prep = conn.prepareStatement(sql);
        prep.setString(1, produto.getNome());
        prep.setInt(2, produto.getValor());
        prep.setString(3, produto.getStatus());
        
        int resultado = prep.executeUpdate(); // Executa a inserção no banco
        
        if (resultado > 0) {
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o produto.");
        }
        
    } catch (SQLException erro) {
        JOptionPane.showMessageDialog(null, "Erro no cadastro: " + erro.getMessage());
    } finally {
        try {
            if (conn != null) conn.close(); // Fecha a conexão
            if (prep != null) prep.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
        }
    }
} 
        
        //conn = new conectaDAO().connectDB();
        
        
    
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

