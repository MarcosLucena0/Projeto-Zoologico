
package controle;

//importações
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Animal;
import visao.TelaListagem;
import visao.TelaPrincipal;

public class ControlePrincipal implements ActionListener{
    
    //criando objeto da tela principal
    private final TelaPrincipal telaPrincipal;
    
    //metodo construtor
    public ControlePrincipal() {
        //Instanciando a tela
        this.telaPrincipal = new TelaPrincipal();
        
        //adicionando os listenners nos botões para escutar
        telaPrincipal.getjButtonCadastrar().addActionListener(this);
        telaPrincipal.getjButtonListar().addActionListener(this);
        telaPrincipal.getjButtonSair().addActionListener(this);
        
        //comando para tela ficar no centro 
        this.telaPrincipal.setLocationRelativeTo(null);
        
        //exibindo a tela
        this.telaPrincipal.setVisible(true);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //evento do botão cadastrar
        if(e.getSource().equals(this.telaPrincipal.getjButtonCadastrar())){
            
            //comando que faz a tela anterior sumir e liberar memória
            this.telaPrincipal.dispose();
            //instanciando a nova tela
            ControleCadastro controleCadastro = new ControleCadastro(false,null);
        }
        
        //evento botão listar
        else if(e.getSource().equals(this.telaPrincipal.getjButtonListar())){
            //comando que faz a tela anterior sumir e liberar memória
            this.telaPrincipal.dispose();
            //instanciando a nova tela
            ControleListagem controleListagem = new ControleListagem();
        }
        
        //evento botão sair
        else if(e.getSource().equals(this.telaPrincipal.getjButtonSair())){
            System.exit(0);
        }
    }
    
}
