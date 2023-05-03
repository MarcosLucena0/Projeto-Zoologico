
package controle;

//importações
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Animal;
import modelo.AnimalDAO;
import visao.TelaListagem;

//classe controleCadastro implementando actionListeners
public class ControleListagem implements ActionListener{
    
     //criando objeto da tela listagem
    private final TelaListagem telaListagem;
    private AnimalDAO animalDAO;

    public ControleListagem() {
        
        animalDAO = new AnimalDAO();
        
        //criando objeto 
        telaListagem = new TelaListagem(null,true);
        
        //adicionando os listenners nos botões para escutar
        telaListagem.getjButtonPesquisar().addActionListener(this);
        telaListagem.getjButtonNovo().addActionListener(this);
        telaListagem.getjButtonEditar().addActionListener(this);
        telaListagem.getjButtonExcluir().addActionListener(this);
        telaListagem.getjButtonRelatorio().addActionListener(this);
        
        //comando para tela ficar no centro 
        this.telaListagem.setLocationRelativeTo(null);
        
        preencherTabela(animalDAO.listar());
        
        //exibindo a tela
        telaListagem.setVisible(true);

    }
    //metodo que preenche a tabela com as informações cadastradas
    private void preencherTabela(ArrayList<Animal> lista){
         telaListagem.limpaTabela();
        for(int i=0;i<lista.size();i++){
            telaListagem.adicionaItem(
                    lista.get(i).getId(),
                    lista.get(i).getNome(),
                    lista.get(i).getEspecie(),
                    lista.get(i).getAltura(),
                    lista.get(i).getPeso(),
                    lista.get(i).getDataNascimento(),
                    lista.get(i).getDataChegada(),
                    lista.get(i).getLocalizacao()
            );
        }  
    }

    //metodo importado do actionlisteners
    @Override
    public void actionPerformed(ActionEvent e) {
        //evento botão pesquisar
        if(e.getSource().equals(telaListagem.getjButtonPesquisar())){
            preencherTabela (animalDAO.pesquisar(telaListagem.getjTextFieldPesquisar().getText()));
        }
        
        //evento botão novo
        if(e.getSource().equals(telaListagem.getjButtonNovo())){
            ControleCadastro controleCadastro = new ControleCadastro(false, null);
            telaListagem.setVisible(false);
        }
        
        //evento botão editar
        if(e.getSource().equals(telaListagem.getjButtonEditar())){
            int item = telaListagem.getjTableListagem().getSelectedRow();
            if(item < 0){
                JOptionPane.showMessageDialog(telaListagem, "Selecione um item na tabela");
            } else{
                Animal animal = new Animal();
                animal.setId((int)telaListagem.getModelo().getValueAt(item, 0));
                animal.setNome((String)telaListagem.getModelo().getValueAt(item, 1));
                animal.setEspecie((String)telaListagem.getModelo().getValueAt(item, 2));
                animal.setAltura((String)telaListagem.getModelo().getValueAt(item, 3));
                animal.setPeso((String)telaListagem.getModelo().getValueAt(item, 4));
                animal.setDataNascimento((String)telaListagem.getModelo().getValueAt(item, 5));
                animal.setDataChegada((String)telaListagem.getModelo().getValueAt(item, 6));
                animal.setLocalizacao((String)telaListagem.getModelo().getValueAt(item, 7));

                ControleCadastro controleCadastro = new ControleCadastro(true, animal);
                telaListagem.dispose();
            } 
        }
        
        //evento botão excluir
        if(e.getSource().equals(telaListagem.getjButtonExcluir())){
            int item = telaListagem.getjTableListagem().getSelectedRow();
            if(item < 0){
                JOptionPane.showMessageDialog(telaListagem, "Selecione um item na tabela");
            }else{
                if(JOptionPane.showConfirmDialog(telaListagem, "Deseja realmente excluir?",
                        "Confirmação de exclusão", JOptionPane.YES_NO_OPTION) == 0){
                    if(animalDAO.excluir((int)telaListagem.getModelo().getValueAt(item, 0))){
                        telaListagem.getModelo().removeRow(item);
                        JOptionPane.showMessageDialog(telaListagem, "Exclusão Realizada");                        
                    } else{
                        JOptionPane.showMessageDialog(telaListagem, "Não foi possível excluir");
                    }
                }
            }
        }
        
        if(e.getSource().equals(telaListagem.getjButtonRelatorio())){
            //comando que faz a tela anterior sumir e liberar memória
            this.telaListagem.dispose();
            //instanciando a nova tela
            ControleRelatorio controleRelatorio = new ControleRelatorio();
        }
    }
        
    public void keyTyped(KeyEvent e) {
        
    }

    public void keyPressed(KeyEvent e) {
        
    }

    public void keyReleased(KeyEvent e) {
        
        if(e.getSource().equals(telaListagem.getjTextFieldPesquisar())){
            preencherTabela(animalDAO.pesquisar(telaListagem.getjTextFieldPesquisar().getText()));
        }
        
    }
   }
