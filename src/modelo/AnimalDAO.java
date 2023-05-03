
package modelo;

//importações
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnimalDAO {
    
    //metodo salvar recebendo como parametro o objeto animal
    public void salvar(Animal animal) {
        
        
        try{
            //codigo INSERT que salva no banco
            String sql= "INSERT INTO animal (nome,especie,altura,peso,dataNascimento,dataChegada,localizacao) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = Conexao.getConnection().prepareStatement(sql);
            
            pst.setString(1, animal.getNome());
            pst.setString(2, animal.getEspecie());
            pst.setString(3, animal.getAltura());
            pst.setString(4, animal.getPeso());
            pst.setString(5, animal.getDataNascimento());
            pst.setString(6, animal.getDataChegada());
            pst.setString(7, animal.getLocalizacao());
            
            pst.execute();
            pst.close();
        } catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
    //metodo alterar
    public void alterar(Animal animal) {
         try{
             //codigo UPDATE para atualizar o animal cadastrado
                String sql= "UPDATE  animal SET nome = ?, especie = ?, altura = ?,peso = ?, dataNascimento = ?, dataChegada = ?,localizacao = ? WHERE id = ?";
            PreparedStatement pst = Conexao.getConnection().prepareStatement(sql);
            
            pst.setString(1, animal.getNome());
            pst.setString(2, animal.getEspecie());
            pst.setString(3, animal.getAltura());
            pst.setString(4, animal.getPeso());
            pst.setString(5, animal.getDataNascimento());
            pst.setString(6, animal.getDataChegada());
            pst.setString(7, animal.getLocalizacao());
            pst.setInt(8, animal.getId());
            
            pst.execute();
            pst.close();
        } catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
    //metodo pesquisar
     public ArrayList<Animal> pesquisar(String nome) {
        
        ArrayList<Animal> listaAnimais = new ArrayList<>();
        
        try{
            //codigo de pesquisar
            String sql= "SELECT * FROM animal WHERE nome LIKE ? ORDER BY nome";
            PreparedStatement pst = Conexao.getConnection().prepareStatement(sql);
            pst.setString(1, '%' + nome + '%');
            ResultSet rs = pst.executeQuery();
            
            
            while(rs.next()){
                Animal animal = new Animal();
                animal.setNome(rs.getString("nome"));
                animal.setEspecie(rs.getString("especie"));
                animal.setAltura(rs.getString("altura"));
                animal.setPeso(rs.getString("peso"));
                animal.setDataNascimento(rs.getString("dataNascimento"));
                animal.setDataChegada(rs.getString("dataChegada"));
                animal.setLocalizacao(rs.getString("localizacao"));
                animal.setId(rs.getInt("id"));
                listaAnimais.add(animal);
                
            } 
            rs.close();
            pst.close();
            
        } catch(SQLException ex){
                System.out.println(ex);
                }
        
        return listaAnimais;  
    }
     
     //metodo listar
     public ArrayList<Animal> listar() {
        ArrayList<Animal> listaAnimais = new ArrayList<>();
        
        try{
            //codigo que lista todos usuarios cadastrados
            String sql = "SELECT * FROM animal ORDER BY nome" ;
            
            PreparedStatement pst = Conexao.getConnection().prepareStatement(sql);
                        
            ResultSet rs = pst.executeQuery();
            
            
            while(rs.next()){
                Animal animal = new Animal();
                animal.setNome(rs.getString("nome"));
                animal.setEspecie(rs.getString("especie"));
                animal.setAltura(rs.getString("altura"));
                animal.setPeso(rs.getString("peso"));
                animal.setDataNascimento(rs.getString("dataNascimento"));
                animal.setDataChegada(rs.getString("dataChegada"));
                animal.setLocalizacao(rs.getString("localizacao"));
                animal.setId(rs.getInt("id"));
                listaAnimais.add(animal);
                
            } 
            rs.close();
            pst.close();
            
        } catch(SQLException ex){
                System.out.println(ex);
                }
        
        return listaAnimais;
    }
     
     //metodo excluir
     public boolean excluir(int id) {
        try {
            //codigo que exclui o usuário
            String sql = "DELETE FROM animal WHERE id = ?";
            
            PreparedStatement pst = Conexao.getConnection().prepareStatement(sql);
            
            pst.setInt(1, id);
            
            pst.execute();
            pst.close();
        } catch (SQLException ex) {            
            System.out.println(ex);
            return false;
        }
        return true;
}

    
}
