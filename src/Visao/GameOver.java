/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Visao;

import Visao.TelaPrincipal;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
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
public class GameOver extends Form implements Painter{
    
    private int pontos;
    private String pontuacao;
    private String pontuacaoTotal;
    private Button btReiniciar;
    private Button btSair;
    private Container grupo;
    
    private Image imgMenu;
    private Image imgSair;
    private Image image;

    public GameOver(int pontos) {
        
        this.pontos = pontos;
        
        PainterChain.installGlassPane(this, this);
        
        try {
            imgMenu = image.createImage("/menu.png");
            imgSair = image.createImage("/sairTudo.png");
        } catch (IOException ex) {
            
        }
        
        pontuacao = "Pontuação: "+pontos;
        pontuacaoTotal = "Pontuação Total: "+pontos;
        
        setLayout(new BorderLayout());
        
        btReiniciar = new Button();
        btSair = new Button();
        
        btReiniciar.setIcon(imgMenu);
        btReiniciar.setFlatten(true);
        btSair.setIcon(imgSair);
        btSair.setFlatten(true);
        
        grupo = new Container();
        grupo.setLayout(new BorderLayout());
        grupo.addComponent(BorderLayout.WEST, btReiniciar);
        grupo.addComponent(BorderLayout.EAST, btSair);
        
        addComponent(BorderLayout.SOUTH, grupo);
        
        
        
        btReiniciar.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent evt) {
            
                TelaPrincipal telaInicial = new TelaPrincipal(0,0);
                telaInicial.show();
            
            }
        });
        btSair.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent evt) {
            
                System.exit(0);
            
            }
        });
        
        
        
        
    }

    public void paint(Graphics g, Rectangle rect) {
   
        g.setColor(0xff4444);
        g.drawString("Game Over", 140, 200);
        g.setColor(0xCC4444);
        g.drawString(pontuacao, 100, 300);
        g.drawString(pontuacaoTotal, 100, 400);
    
    }
    
    
    
    
    
    
}
