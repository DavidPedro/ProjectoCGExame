/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Visao;

import Visao.TelaPrincipal;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Painter;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.painter.PainterChain;
import java.io.IOException;

/**
 *
 * @author David
 */
public class Ajuda extends Form implements Painter{
    
    private Button voltar;
    private Image imgVoltar;
    private Image image;
    String ajuda[];
    
    public Ajuda(){
        
        super("Ajuda");
        try {
            imgVoltar = image.createImage("/voltar.png");
        } catch (IOException ex) {
            
        }
    
        PainterChain.installGlassPane(this, this);
        
        voltar = new Button();
        voltar.setIcon(imgVoltar);
        voltar.setFlatten(true);
        
        
        setLayout(new BorderLayout());
    
        ajuda = new String[] {
            "\t Instruções ",
                 "",
                 "O objectivo principal do jogo",
                 "é distruir as naves inimigas.",
                 "",
                 "O Jogo tem dois modos:",
                 " - Modo Automatico : onde a Nave principal",
                 " se move automaticamente.",
                 " - Modo Manual: onde a Nave Principal é",
                 "movida pela acção do usuario.",
                 "",
                 "As dificuldades do jogo vão surgindo",
                 " depois da distruição de um certo numero",
                 " de naves inimigas."
        };
        
        voltar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
           
                TelaPrincipal telaInicial = new TelaPrincipal(0,0);
                telaInicial.showBack();
            }
        });
        
        addComponent(BorderLayout.SOUTH, voltar);
    
    
    }

    public void paint(Graphics g, Rectangle rect) {
        
        g.setColor(0x1E90FF);
        
        for(int i = 0; i< ajuda.length; i++){
            
            g.drawString(ajuda[i], 20, 100 + (i*40));
            
        }
    }
    
    
}
