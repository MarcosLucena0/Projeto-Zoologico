/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import visao.TelaRelatorio;

public class ControleRelatorio implements ActionListener{
    
    private final TelaRelatorio telaRelatorio;

    public ControleRelatorio() {
        this.telaRelatorio = new TelaRelatorio();
     
        
        this.telaRelatorio.setLocationRelativeTo(null);
        
        this.telaRelatorio.setVisible(true);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    
}
