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

    
    public void cadastrarProduto(ProdutosDTO produto) {
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

    
    public void venderProduto(int produtoId) {
        conn = new conectaDAO().connectDB(); 

        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

        try {
            prep = conn.prepareStatement(sql);
            prep.setInt(1, produtoId); 

            int resultado = prep.executeUpdate(); // Executa a atualização no banco

            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao vender o produto.");
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao vender o produto: " + erro.getMessage());
        } finally {
            try {
                if (conn != null) conn.close(); // Fecha a conexão
                if (prep != null) prep.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    
    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        conn = new conectaDAO().connectDB(); 

        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";

        try {
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery(); 

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto); 
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + erro.getMessage());
        } finally {
            try {
                if (conn != null) conn.close(); 
                if (prep != null) prep.close();
                if (resultset != null) resultset.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
            }
        }

        return listagem; // Retorna a lista de produtos vendidos
    }

    
    public ArrayList<ProdutosDTO> listarProdutos() {
        conn = new conectaDAO().connectDB(); 

        String sql = "SELECT * FROM produtos";

        try {
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery(); 

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto); 
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + erro.getMessage());
        } finally {
            try {
                if (conn != null) conn.close(); 
                if (prep != null) prep.close();
                if (resultset != null) resultset.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
            }
        }

        return listagem; 
    }
}
