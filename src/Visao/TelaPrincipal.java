/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Visao;

import Modelo.DefinicaoModelo;
import com.codename1.ui.Button;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import java.io.IOException;


/**
 *
 * @author David
 */
public class TelaPrincipal extends Form{

    public int getModo() {
        return modo;
    }

    public void setModo(int modo) {
        this.modo = modo;
    }

    public int getCenario() {
        return cenario;
    }

    public void setCenario(int cenario) {
        this.cenario = cenario;
    }

  
    
    private int modo;
    private int cenario;
    
    private Container contentor;
    private Container botoesAccao;
 
    private Image imgIniciar;
    private Image image;
    
    private Button jbAjuda;
    private Button jbSair;
    private Button jbStart;
    private Button jbSobre;
    private Button jbDefinicao;
    
    private ComponentGroup grupo;
        
    private Image imgAjuda;
    private Image imgSobre;
    private Image imgDefinicoes;
    private Image imgSair;
    private Image imgMenu;
    
    public Definicoes definicoes = new Definicoes();
    
    public TelaPrincipal(int modo, int cenario) {
        
        super("Menu Principal");
        
        this.modo = modo;
        
        this.cenario = cenario;
        
        
        

            setSize(new Dimension(Display.getInstance().getDisplayWidth(),Display.getInstance().getDisplayHeight()));
            contentor = new Container(new BorderLayout());
            
            setLayout(new BorderLayout());
            try {
                
            imgIniciar = image.createImage("/iniciar.png");
            imgDefinicoes = image.createImage("/definicoes.png");
            imgAjuda = image.createImage("/ajuda.png");
            imgSobre = image.createImage("/usuario.png");
            imgSair= image.createImage("/sair.png");
            imgMenu= image.createImage("/fundoMenu.jpg");
            
            } catch (IOException ex) {
        }
            
            
            botoesAccao  = new Container(new GridLayout(20,1));
            botoesAccao.setPreferredSize(new Dimension(200,800));
            
            jbStart = new Button();
            jbDefinicao = new Button();
            jbAjuda = new Button();
            jbSair = new Button();
            jbSobre = new Button();
            
            setBgImage(imgMenu);
            
            jbStart.setIcon(imgIniciar);
            jbDefinicao.setIcon(imgDefinicoes);
            jbAjuda.setIcon(imgAjuda);
            jbSobre.setIcon(imgSobre);
            jbSair.setIcon(imgSair);
            
            jbStart.setCellRenderer(true);
            jbStart.setFlatten(true);
            jbDefinicao.setFlatten(true);
            jbSobre.setFlatten(true);
            jbAjuda.setFlatten(true);
            jbSair.setFlatten(true);
            
            this.show();
   
            grupo = new ComponentGroup();
            
            grupo.addComponent(jbStart);
            grupo.addComponent(jbDefinicao);
            grupo.addComponent(jbSobre);
            grupo.addComponent(jbAjuda);
            grupo.addComponent(jbSair);

            this.addComponent(BorderLayout.CENTER, grupo);

            
            
            final Ajuda ajuda =  new Ajuda();
            final SobreProgramador sobre =  new SobreProgramador();
            
            jbSobre.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    
                    sobre.show();
                    
                }
            });
            
            jbAjuda.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    
                    ajuda.show();
                    
                }
            });
            
            jbSair.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    
                    System.exit(0);
                    
                }
            });
            
            jbDefinicao.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    
                    Definicoes defi = new Definicoes();
                    defi.showBack();
                    
                }
            });
            final GuerraNoEspaco jogo =  new GuerraNoEspaco(getModo(), getCenario());
            
            jbStart.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    
                    jogo.show();
                    
                }
            });
                   
            
           
    }
    
    public Container criaGui(){

       
        return contentor;
    
    }

        
    
     
}
