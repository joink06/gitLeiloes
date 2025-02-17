/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection con;
    PreparedStatement st;
    ResultSet rs;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void salvar (ProdutosDTO p){
        int status;
        try{
            con = new conectaDAO().connectDB();
            st = con.prepareStatement("insert into produtos (nome,valor,status) values (?,?,?)");
            st.setString(1, p.getNome());
            st.setInt(2, p.getValor());
            st.setString(3, p.getStatus());
            status = st.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
                    
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Erro no cadastro!\n"+ ex.getMessage());
        }
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        con = new conectaDAO().connectDB();
        String sql = "select * from produtos";
        
        try{
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            
            while(rs.next()){
                ProdutosDTO p = new ProdutosDTO();
                
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getInt("valor"));
                p.setStatus(rs.getString("status"));
                
                listagem.add(p);
            }
            return listagem;
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Erro na listagem! "+ ex.getMessage());
            return null;
        }
    }
    
    public void venderProduto(int idProduto){
        try{
            con = new conectaDAO().connectDB();
            st = con.prepareStatement("update produtos set status = 'Vendido' where id = ?");
            st.setInt(1, idProduto);
            st.executeUpdate();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Erro na modificação! "+ ex.getMessage());
        }
    }
    
    
        
}

