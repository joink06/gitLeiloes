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
        
        return listagem;
    }
    
    
    
        
}

