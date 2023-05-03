
package controle;

//importações
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Animal;
import modelo.AnimalDAO;
import visao.TelaCadastro;

//classe controleCadastro implementando actionListeners
public class ControleCadastro implements ActionListener{
    
    //criando objeto da tela cadastro
    private final TelaCadastro telaCadastro;
    //criando objeto 
    private final AnimalDAO animalDAO;
    private boolean status;
    private int id;
    
    //construtor
    public ControleCadastro(boolean status, Animal animal) {
        
        this.status = status;
        this.id = 0; 
        
        //instanciando a tela
        telaCadastro = new TelaCadastro();
        
        animalDAO = new AnimalDAO();
        
        //adicionando os listenners nos botões para escutar
        telaCadastro.getjButtonSalvar().addActionListener(this);
        telaCadastro.getjButtonLimpar().addActionListener(this);
        telaCadastro.getjButtonVoltar().addActionListener(this);
        
        //comando para tela ficar no centro 
        this.telaCadastro.setLocationRelativeTo(null);
        
        //exibindo a tela
        telaCadastro.setVisible(true);
        
        //condição chamando o metódo
        if(status){
            telaCadastro.getjTextFieldNome().setText(animal.getNome());
            telaCadastro.getjTextFieldEspecie().setText(animal.getEspecie());
            telaCadastro.getjTextFieldAltura().setText(animal.getAltura());
            telaCadastro.getjTextFieldPeso().setText(animal.getPeso());
            telaCadastro.getjTextFieldDataNascimento().setText(animal.getDataNascimento());
            telaCadastro.getjTextFieldDataChegada().setText(animal.getDataChegada());
            telaCadastro.getjTextFieldLocalizaçao().setText(animal.getLocalizacao());
            this.id = animal.getId();
        }
        telaCadastro.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
        //evento do botão limpar
        if(e.getSource().equals(this.telaCadastro.getjButtonLimpar())){
           this.telaCadastro.getjTextFieldNome().setText("");
           this.telaCadastro.getjTextFieldEspecie().setText("");
           this.telaCadastro.getjTextFieldAltura().setText("");
           this.telaCadastro.getjTextFieldPeso().setText("");
           this.telaCadastro.getjTextFieldDataNascimento().setText("");
           this.telaCadastro.getjTextFieldDataChegada().setText("");
           this.telaCadastro.getjTextFieldLocalizaçao().setText("");
        }
        
        //evento do botão salvar
        else if(e.getSource().equals(this.telaCadastro.getjButtonSalvar())){
            if(validaCampos()){
                Animal animal = new Animal();
                animal.setNome(telaCadastro.getjTextFieldNome().getText());
                animal.setEspecie(telaCadastro.getjTextFieldEspecie().getText());
                animal.setAltura(telaCadastro.getjTextFieldAltura().getText());
                animal.setPeso(telaCadastro.getjTextFieldPeso().getText());
                animal.setDataNascimento(telaCadastro.getjTextFieldDataNascimento().getText());
                animal.setDataChegada(telaCadastro.getjTextFieldDataChegada().getText());
                animal.setLocalizacao(telaCadastro.getjTextFieldLocalizaçao().getText());
                
                //condicao caso o id for o mesmo, alterará os dados do animal
                if(status){
                    animal.setId(id);
                    animalDAO.alterar(animal);
                    JOptionPane.showMessageDialog(telaCadastro, "Cadastro atualizado com sucesso", 
                        "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                }
                //condição para salvar
                else{
                    animalDAO.salvar(animal);
                    
                    JOptionPane.showMessageDialog(telaCadastro, "Cadastro realizado com sucesso", 
                        "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                }

                telaCadastro.setVisible(false);
                ControleListagem controleListagem = new ControleListagem();
                    }else{
                        JOptionPane.showMessageDialog(telaCadastro, "Preencha todos os campos", 
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
            }
        
        
        //evento botão voltar
        else if(e.getSource().equals(this.telaCadastro.getjButtonVoltar())){
            //comando que faz a tela anterior sumir e liberar memória
            this.telaCadastro.dispose();
            //instanciando a nova tela
            ControlePrincipal controlePrincipal = new ControlePrincipal();
        }


      }
    
    //metodo para saber se todos os campos estão preenchidos corretamente e salvar
     private boolean validaCampos(){
            if(telaCadastro.getjTextFieldNome().getText().equals(""))return false;
            else if(telaCadastro.getjTextFieldEspecie().getText().equals(""))return false;
            else if(telaCadastro.getjTextFieldAltura().getText().equals(""))return false;
            else if(telaCadastro.getjTextFieldPeso().getText().equals(""))return false;
            else if(telaCadastro.getjTextFieldDataNascimento().getText().equals(""))return false;
            else if(telaCadastro.getjTextFieldDataChegada().getText().equals(""))return false;
            else if(telaCadastro.getjTextFieldLocalizaçao().getText().equals(""))return false;
            else return true;
     }
    
    }

   
    
    

