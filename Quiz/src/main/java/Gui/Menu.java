/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author jonat
 */
public class Menu extends JPanel{    
    private JButton btnCrear;
    private JButton btnResponder;
    
    public Menu(){
        
        this.btnCrear = new JButton("Crear Test");
        this.btnResponder = new JButton("Responder Test");
        
        super.add(this.btnCrear);
        super.add(this.btnResponder);        
    }

    public JButton getBtnCrear() {
        return btnCrear;
    }

    public void setBtnCrear(JButton btnCrear) {
        this.btnCrear = btnCrear;
    }

    public JButton getBtnResponder() {
        return btnResponder;
    }

    public void setBtnResponder(JButton btnResponder) {
        this.btnResponder = btnResponder;
    }
    
}
