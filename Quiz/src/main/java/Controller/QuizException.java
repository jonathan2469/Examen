/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 *
 * @author jonat
 */
public class QuizException extends Exception {

    /**
     * Creates a new instance of <code>Quiz1Exception</code> without detail
     * message.
     */
    public QuizException() {
    }

    /**
     * Constructs an instance of <code>Quiz1Exception</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     * @param pnl
     */
    public QuizException(String msg, JPanel pnl) {
        JOptionPane.showMessageDialog(pnl, msg);
    }
    
    public QuizException(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
    
    public QuizException(String msg, JFrame frame) {
        JOptionPane.showMessageDialog(frame, msg);
    }
}
