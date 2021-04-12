/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Controller.Controller;
import Controller.FrameActionListener;
import Controller.Pregunta;
import Controller.Quiz;
import Controller.QuizException;
import java.awt.event.ActionEvent;
import static java.awt.image.ImageObserver.HEIGHT;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


/**
 *
 * @author jonat
 */
public class PrincipalPanel extends JFrame {

    private Menu mainPnl;
    private CrearQuizDialog crearDialog;
    private ArrayList<Quiz> quizes;
    private Controller control;
    private PreguntaDialog preguntasDialog;
    private ResponderDialog responderDialog;
    private QuizDialog quizDialog;
    private int cont;
    private int numPre;
    private int numPreguntas;
    private int numCorrectas;

    public PrincipalPanel() throws ClassNotFoundException {
        super("Test");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(230, 120);
        super.setLocationRelativeTo(null);

        cont = 0;
        numPre = 0;
        numPreguntas = 0;
        numCorrectas = 0;

        this.quizes = new ArrayList();

        this.mainPnl = new Menu();

        this.crearDialog = new CrearQuizDialog(this);

        this.preguntasDialog = new PreguntaDialog(this);

        this.control = new Controller();

        responderDialog = new ResponderDialog(this);

        quizDialog = new QuizDialog(this);

        control.cargar();

        FrameActionListener crearDialogButton = new FrameActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearDialog.setVisible(true);
                numPre = 0;
                numPreguntas = 0;
            }
        };

        FrameActionListener crearDialogAceptarButton = new FrameActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    quizes.add(new Quiz(crearDialog.getTxdFldNombreTxt(), crearDialog.getTxtFldAsignaturaTxt(), crearDialog.getTxdFldNumPreguntas()));
                    cont += 1;
                    crearDialog.setVisible(false);
                    numPreguntas = crearDialog.getTxdFldNumPreguntas();
                    crearDialog.clear();
                    preguntasDialog.setVisible(true);
                    JOptionPane.showMessageDialog(preguntasDialog, "Para seleccionar la correcta pulse el botón enfrente de la respuesta correcta ");
                } catch (QuizException ex) {

                }
            }
        };

        FrameActionListener crearDialogCancelarButton = new FrameActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearDialog.setVisible(false);
                crearDialog.clear();
            }
        };

        FrameActionListener preguntasDialogSiguienteButton = new FrameActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    if ((numPre == (numPreguntas - 1))) {
                        Pregunta pregunta = new Pregunta(preguntasDialog.getTxtFldEnunciadoTxt(),
                                preguntasDialog.getTxtFldR1Txt(),
                                preguntasDialog.getTxtFldR2Txt(),
                                preguntasDialog.getTxtFldR3Txt(),
                                preguntasDialog.getTxtFldR4Txt(),
                                correcta());
                        quizes.get(cont - 1).add(pregunta);
                        control.add(quizes.get(cont - 1));
                        control.guardar();
                        preguntasDialog.clear();
                        preguntasDialog.setVisible(false);
                    } else {

                        try {
                            Pregunta pregunta = new Pregunta(preguntasDialog.getTxtFldEnunciadoTxt(),
                                    preguntasDialog.getTxtFldR1Txt(),
                                    preguntasDialog.getTxtFldR2Txt(),
                                    preguntasDialog.getTxtFldR3Txt(),
                                    preguntasDialog.getTxtFldR4Txt(),
                                    correcta());
                            quizes.get(cont - 1).add(pregunta);
                            numPre += 1;
                            preguntasDialog.clear();
                        } catch (QuizException ex) {

                        }

                    }
                } catch (Exception ex) {

                }
                preguntasDialog.limpiarSeleccion();
            }
            
        };

        FrameActionListener responderButton = new FrameActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                responderDialog.cleanCombo();
                responderDialog.setVisible(true);
                responderDialog.fillCombo(control.getPreguntas());
                numPre = 0;
                numPreguntas = 0;
            }
        };

        FrameActionListener responderDialogCancelarButton = new FrameActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                responderDialog.setVisible(false);
            }
        };

        FrameActionListener responderDialogAceptarButton = new FrameActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numCorrectas = 0;
                numPre = 0;
                JOptionPane.showMessageDialog(responderDialog, "Para contestar seleccione el botón a la derecha de la respuesta correcta");
                Quiz quiz = control.getPreguntasAt(responderDialog.getComboBoxIndex());
                numPreguntas = quiz.getNumPreguntas();
                ArrayList<Pregunta> preguntas = quiz.getPreguntas();
                quizDialog.setLblEnunciado(preguntas.get(0).getPregunta());
                quizDialog.setLblOpcion1(preguntas.get(0).getOp1());
                quizDialog.setLblOpcion2(preguntas.get(0).getOp2());
                quizDialog.setLblOpcion3(preguntas.get(0).getOp3());
                quizDialog.setLblOpcion4(preguntas.get(0).getOp4());
                numPre += 1;
                quizDialog.setVisible(true);                
            }
            
        };

        FrameActionListener quizDialogSiguienteButton = new FrameActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {     
                
                Quiz quiz = control.getPreguntasAt(responderDialog.getComboBoxIndex());
                numPreguntas = quiz.getNumPreguntas();
                ArrayList<Pregunta> preguntas = quiz.getPreguntas();
                if (esCorrecta(preguntas.get(numPre-1), getCorrecta())) {
                    numCorrectas+=1;
                }
                
                if (!respuestaVacia()){
                    JOptionPane.showMessageDialog(quizDialog, "Selecciones una respuesta");
                }else if((numPre != quiz.getNumPreguntas())) {
                    quizDialog.limpiarSeleccion();
                    quizDialog.setLblEnunciado(preguntas.get(numPre).getPregunta());
                    quizDialog.setLblOpcion1(preguntas.get(numPre).getOp1());
                    quizDialog.setLblOpcion2(preguntas.get(numPre).getOp2());
                    quizDialog.setLblOpcion3(preguntas.get(numPre).getOp3());
                    quizDialog.setLblOpcion4(preguntas.get(numPre).getOp4());
                    numPre += 1;
                }else if((numPre == quiz.getNumPreguntas())){
                    quizDialog.limpiarSeleccion();
                    JOptionPane.showMessageDialog(quizDialog, "Ha contestado el examen, su calificación es: \n"+(float)(((float)numCorrectas/(float)quiz.getNumPreguntas())*100)+"\nCon "+numCorrectas+"/"+quiz.getNumPreguntas()+" aciertos" );
                    quizDialog.setVisible(false);
                }

            }
        };

        mainPnl.getBtnCrear().addActionListener(crearDialogButton);
        mainPnl.getBtnResponder().addActionListener(responderButton);
        responderDialog.getBtnAceptar().addActionListener(responderDialogAceptarButton);
        responderDialog.getBtnCancelar().addActionListener(responderDialogCancelarButton);
        quizDialog.getBtnSiguiente().addActionListener(quizDialogSiguienteButton);
        crearDialog.getBtnAceptar().addActionListener(crearDialogAceptarButton);
        crearDialog.getBtnCancelar().addActionListener(crearDialogCancelarButton);
        preguntasDialog.getBtnSiguiente().addActionListener(preguntasDialogSiguienteButton);

        super.add(this.mainPnl);
        super.setVisible(true);
    }

    public int correcta() {
        if (preguntasDialog.getBtnR1().isSelected()) {
            return 1;
        } else if (preguntasDialog.getBtnR2().isSelected()) {
            return 2;
        } else if (preguntasDialog.getBtnR3().isSelected()) {
            return 3;
        } else if (preguntasDialog.getBtnR4().isSelected()) {
            return 4;
        } else {
            return 0;
        }
    }
    
    public int getCorrecta() {
        if (quizDialog.getrBtnOpcion1().isSelected()) {
            return 1;
        } else if (quizDialog.getrBtnOpcion2().isSelected()) {
            return 2;
        } else if (quizDialog.getrBtnOpcion3().isSelected()) {
            return 3;
        } else if (quizDialog.getrBtnOpcion4().isSelected()) {
            return 4;
        } else {
            return 0;
        }
    }
    
    public boolean esCorrecta(Pregunta pregunta, int resp){
        if (resp == pregunta.getCorrecta()) {
            return true;
        }else{
            return false;
        }
    }
    
    public boolean respuestaVacia(){
        if ((quizDialog.getrBtnOpcion1().isSelected())||(quizDialog.getrBtnOpcion2().isSelected())||(quizDialog.getrBtnOpcion3().isSelected())||(quizDialog.getrBtnOpcion4().isSelected())) {
           return true;
        } else{
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new PrincipalPanel();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(PrincipalPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
