/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Visao;

import Modelo.DefinicaoModelo;
import Visao.TelaPrincipal;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import java.io.IOException;

/**
 *
 * @author David
 */
public class Definicoes extends Form{
    
    private Label lbModo;
    private Label lbDificuldade;
    private Label lbCenario;
    
    private Button voltar;
    private Button ok;
    
    private RadioButton rbtAutomatico;
    private RadioButton rbtManual;
    
    private RadioButton rbtEstagio1;
    private RadioButton rbtEstagio2;
    private RadioButton rbtEstagio3;
    
    private RadioButton rbtCenario1;
    private RadioButton rbtCenario2;
    
    private ComponentGroup grupoModo;
    private ComponentGroup grupoDificuldade;
    private ComponentGroup grupoCenario;
    private Container grupoBotao;
    private Container grupoGeral;
    
    private ButtonGroup grupoButtonModo;
    private ButtonGroup grupoButtonDificuldade;
    private ButtonGroup grupoButtonCenario;
    
    private Image imgVoltar;
    private Image image;
    
    public DefinicaoModelo defModelo = new DefinicaoModelo();

    public Definicoes(){
        
        super("Definições");
        
        try {
            imgVoltar = image.createImage("/voltar.png");
        } catch (IOException ex) {
            
        }
    
  
        setLayout(new BorderLayout());
    
        rbtAutomatico = new RadioButton("Automatico");
        rbtManual = new RadioButton("Manual");
        
        
        lbModo = new Label("Modo:");
        lbDificuldade = new Label("Dificuldade:");
        lbCenario = new Label("Cenario:");
        
        lbModo.getStyle().setFont( Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, 16));
        lbCenario.getStyle().setFont( Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, 16));
//        rbtEstagio1 = new RadioButton("Estagio 1");
//        rbtEstagio2 = new RadioButton("Estagio 2");
//        rbtEstagio3 = new RadioButton("Estagio 3");
    
        rbtCenario1 = new RadioButton("Cenario 1");
        rbtCenario2 = new RadioButton("Cenario 2");
        
        grupoButtonModo = new ButtonGroup();
        grupoButtonDificuldade = new ButtonGroup();
        grupoButtonCenario = new ButtonGroup();
        
        voltar = new Button();
        ok = new Button("OK");
        
        voltar.setIcon(imgVoltar);
        voltar.setFlatten(true);
        
//        ok.setIcon("");
//        ok.setFlatten(true);
        
        grupoModo = new ComponentGroup();
        grupoDificuldade = new ComponentGroup();
        grupoBotao = new Container(new BorderLayout());
        grupoCenario = new ComponentGroup();
        grupoGeral = new Container();
        
        grupoGeral.setLayout(new GridLayout(3,1));
        
       
        grupoModo.addComponent(lbModo);
        grupoModo.addComponent(rbtAutomatico);
        grupoModo.addComponent(rbtManual);
        
        grupoButtonModo.add(rbtAutomatico);
        grupoButtonModo.add(rbtManual);
        
//        grupoDificuldade.addComponent(lbDificuldade);
//        grupoDificuldade.addComponent(rbtEstagio1);
//        grupoDificuldade.addComponent(rbtEstagio2);
//        grupoDificuldade.addComponent(rbtEstagio3);
        
//        grupoButtonDificuldade.add(rbtEstagio1);
//        grupoButtonDificuldade.add(rbtEstagio2);
//        grupoButtonDificuldade.add(rbtEstagio3);
        
        grupoCenario.addComponent(lbCenario);
        grupoCenario.addComponent(rbtCenario1);
        grupoCenario.addComponent(rbtCenario2);
       
        grupoButtonCenario.add(rbtCenario1);
        grupoButtonCenario.add(rbtCenario2);
        
               
        grupoBotao.addComponent(BorderLayout.WEST, voltar);
        grupoBotao.addComponent(BorderLayout.EAST, ok);
        
        grupoGeral.addComponent(grupoModo);
//        grupoGeral.addComponent(grupoDificuldade);
        grupoGeral.addComponent(grupoCenario);
              
        
        addComponent(BorderLayout.CENTER, grupoGeral);
        
        addComponent(BorderLayout.SOUTH, grupoBotao);
        

        voltar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
           
               
                TelaPrincipal telaInicial =  new TelaPrincipal(defModelo.getModo(), defModelo.getCenario());
                telaInicial.showBack();
            }
        });
        ok.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
           
               if(rbtAutomatico.isSelected())
                   defModelo.setModo(1);
               
               else if(rbtManual.isSelected())
                    defModelo.setModo(2);
               
               if(rbtCenario1.isSelected())
                   defModelo.setCenario(1);
               
               else if(rbtCenario2.isSelected())
                   defModelo.setCenario(2);
               
               Dialog.show("Definições", "Dados salvos com sucesso!", "OK",null);
            }
        });
        
        rbtManual.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
           
               
               // manual.show();
            }
        });
        
        
        
    }

    
}
