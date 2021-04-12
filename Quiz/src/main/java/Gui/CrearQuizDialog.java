/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Controller.QuizException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author jonat
 */
public class CrearQuizDialog extends JDialog{
    private JPanel pnl;
    private JLabel lbl1;
    private JLabel lbl2;
    private JLabel lbl3;
    private JTextField txtFldAsignatura;    
    private JTextField txdFldNombre;
    private JTextField txdFldNumPreguntas;
    private JButton btnAceptar;
    private JButton btnCancelar;
    
    
    public CrearQuizDialog(JFrame frame){
        super.setLocationRelativeTo(frame);
        super.setSize(325, 150);
        
        this.pnl = new JPanel();
        this.lbl1 = new JLabel("Asignatura");
        this.lbl2 = new JLabel("Nombre");
        this.lbl3 = new JLabel("Número de preguntas");
        this.txtFldAsignatura = new JTextField(20);
        this.txdFldNombre = new JTextField(20);
        this.txdFldNumPreguntas = new JTextField(10);
        this.btnAceptar = new JButton("Aceptar");
        this.btnCancelar = new JButton("Cancelar");
        
        this.pnl.add(this.lbl1);
        this.pnl.add(this.txtFldAsignatura);
        this.pnl.add(this.lbl2);
        this.pnl.add(this.txdFldNombre);
        this.pnl.add(this.lbl3);
        this.pnl.add(this.txdFldNumPreguntas);
        this.pnl.add(this.btnAceptar);
        this.pnl.add(this.btnCancelar);
        
        add(pnl);
    }

    public String getTxtFldAsignaturaTxt() throws QuizException {
        if (txtFldAsignatura.getText().equals("")) {
            this.txtFldAsignatura.requestFocus();
            throw new QuizException("Introduzca una asignatura correcta", pnl);
        }else{
            return txtFldAsignatura.getText();
        }
    }

    public String getTxdFldNombreTxt() throws QuizException {
        if (txdFldNombre.getText().equals("")) {
            this.txdFldNombre.requestFocus();
            throw new QuizException("Introduzca un nombre correcto", pnl);            
        }else{
            return txdFldNombre.getText();
        }
    }

    public Integer getTxdFldNumPreguntas() throws QuizException {
        try {
            Integer res = Integer.parseInt(this.txdFldNumPreguntas.getText());
            return res;
        } catch (Exception e) {
            this.txdFldNumPreguntas.requestFocus();
            throw new QuizException("Número incorrecto", pnl);
        }        
    }         

    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }
    
    public void clear(){
        this.txdFldNombre.setText("");
        this.txtFldAsignatura.setText("");
        this.txdFldNumPreguntas.setText("");
    }
    
}
