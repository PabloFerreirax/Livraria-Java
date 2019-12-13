
package com.bookstore.project.useful;

import javax.swing.JOptionPane;

/**
 *
 * @author pablo
 */
public class ErrorHandling {
    //Tratamento de erro
    public ErrorHandling(){
    }    
    
    public void showMsgError(String titulo, String msg){
        JOptionPane.showMessageDialog( // Caixa de mensagem
		null, // Janela da aplicação (opcional, pode ser null)
		msg, // Mensagem
		titulo, // Título da caixa de mensagem
		JOptionPane.ERROR_MESSAGE // Ícone da caixa de mensagem
	);
    }
    
    public void showMsg(String titulo, String msg){
        JOptionPane.showMessageDialog(
		null,
		msg,
		titulo,
		JOptionPane.INFORMATION_MESSAGE
	);
    }
}
