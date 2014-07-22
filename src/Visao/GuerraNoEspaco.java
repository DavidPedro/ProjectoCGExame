/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Visao;

import Controlo.ArmaControlo;
import Controlo.ColisaoControlo;
import Controlo.Controlo;
import Controlo.DefinicaoControlo;
import Controlo.NaveInimigaControlo;
import Controlo.NavePrincipalControlo;
import Modelo.DefinicaoModelo;
import Modelo.Posicao;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Painter;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.painter.PainterChain;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author David
 */
public class GuerraNoEspaco extends Form implements Painter,Runnable
{
    private Component componente;
    private Form form;
    private int altura, largura, x,y;
    private Image image;
    private Image imgDireita;
    private Image imgEsquerda;
    private Image imgFogo;
    private Image createImage;
    private Image naveImage;

    private boolean manual = false;
    Controlo controlo = new Controlo();
    
    
    private boolean jogoActivo;
    
    private Image naveimage;
    private Random random;
    private Button btDireita;
    private Button btFogo;
    private Button btEsquerda;

    private int capturaPosicao;
    private Container contentorBotoes;
    private Container contentorEstatisticas;
    
    private Label pontuacao;
    private Label resistencia;
    
    private int vidas = 0;
    private int pontos = 0;
 
    
    private String estatisticaPontos;
    private String estatisticaVidas;

    Posicao posicaoI = new Posicao();
    
    private ComponentGroup grupo;
    
    DefinicaoModelo defModelo;
    DefinicaoControlo definicoes;
    Definicoes definicoesVisao = new Definicoes();
    
    
    public NavePrincipalControlo  nave;
    
    public ArrayList<ArmaControlo>  disparo;
    public ArrayList<NaveInimigaControlo>  naveInimiga;
    
    ColisaoControlo colisao = new ColisaoControlo();
    
   Thread time;
  
   public  GuerraNoEspaco(int modo,int cenario){
       
        definicoes = new DefinicaoControlo();
        
        defModelo = new DefinicaoModelo();
    
        nave = new NavePrincipalControlo(Display.getInstance().getDisplayWidth()/2,Display.getInstance().getDisplayHeight()-200,0);
        
        disparo = new ArrayList<ArmaControlo>();
        
        naveInimiga = new ArrayList<NaveInimigaControlo>();
        
        setLayout(new BorderLayout());
        
        this.manual = true;
        
        jogoActivo = true;
        
        if(modo == 0){
        
            modo = 2;
        }    
        grupo = new ComponentGroup();
        
        btDireita = new Button();
        
        btFogo = new Button();
        
        btEsquerda = new Button();
        
        pontuacao = new Label("Pontos: " + pontos);
        resistencia = new Label("Vidas: " + vidas);

        setSize(new Dimension(Display.getInstance().getDisplayWidth(),Display.getInstance().getDisplayHeight()));

        time = new Thread(this);
        
        time.start();

        try {
            
            if(image == null){
                
                naveImage = image.createImage("/nave.png");
                imgDireita = image.createImage("/direita.png");
                imgEsquerda = image.createImage("/esquerda.png");
                imgFogo = image.createImage("/fogo.png");
                
                
                
            }
        } catch (IOException ex) {
            
            Dialog.show("Messagem", "Caminho da Imagem Invalida", "Ok", "Cancelar");
        }
        
        PainterChain.installGlassPane(this, this);
         
        btDireita.setIcon(imgDireita);
        btEsquerda.setIcon(imgEsquerda);
        btFogo.setIcon(imgFogo);
     
        btFogo.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent evt) {
                        
                       adicionarDisparo ();
                   }
         });

        
        btDireita.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent evt) {
                      
                   if(nave.getX() > (Display.getInstance().getDisplayWidth()/2)+150)
                    nave.setDirecao(-1);
                   else if(nave.getX() < 100)
                    nave.setDirecao(0);
                   
                   nave.setX(nave.getX()-8);
                       
               }
         });
        
        btEsquerda.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent evt) {
                    
                   if(nave.getX() > (Display.getInstance().getDisplayWidth()/2)+150)
                    nave.setDirecao(-1);
                   else if(nave.getX() < 100)
                    nave.setDirecao(0);
                   
                   nave.setX(nave.getX()+8);
                       
               }
         });
        
        contentorEstatisticas = new Container(new GridLayout(1, 2));
        contentorEstatisticas.addComponent(resistencia);
        contentorEstatisticas.addComponent(pontuacao);
        
        contentorBotoes = new Container(new FlowLayout());
      
        addComponent(BorderLayout.NORTH, contentorEstatisticas);
        
//        if(modo == 1){
            
//            Dialog.show("Teste Modo", "1", "Ok", null);
           addComponent(BorderLayout.SOUTH, modoJogo(modo));
//        }
//        else

      // Dialog.show("Teste Modo", ""+modo,"Ok", null);
            
        for(int i=0; i< 5; i++)
            naveInimiga.add( new NaveInimigaControlo(50*i+10,40,1));

        for(int i=0; i< 3; i++)
             disparo.add( new ArmaControlo(nave.getX(), nave.getY(),5 ,5));
        
        
        cenarioJogo(cenario);
    
    }

   public void cenarioJogo(int cenario){
       
       if(cenario == controlo.CENARIO1){
           try {
               
               createImage = image.createImage("/fundoEspacos1.jpg");
               
           } catch (IOException ex) {
             
           }
       
       
       }
       else if(cenario == controlo.CENARIO2){
           try {
               
               createImage = image.createImage("/fundoEspacos.jpg");
               
           } catch (IOException ex) {
              
           }
       
       }
       if(image == null && cenario == 0){
           try {
               createImage = image.createImage("/fundoEspacos.jpg");
           } catch (IOException ex) {
           }
       
       }
   
   
   }
   public ComponentGroup modoJogo(int modo){
       
       if(modo == controlo.AUTOMATICO){
           nave = new NavePrincipalControlo(Display.getInstance().getDisplayWidth()/2,Display.getInstance().getDisplayHeight()-200,1);
           
           grupo.addComponent(btFogo);
           grupo.setHorizontal(true);       
           grupo.setWidth(Display.getInstance().getDisplayWidth());
           this.manual = false;
           
           return grupo;
       
       
       }
       else if(modo == controlo.MANUAL){
       
           nave = new NavePrincipalControlo(Display.getInstance().getDisplayWidth()/2,Display.getInstance().getDisplayHeight()-200,0);
           
           grupo.addComponent(btDireita);
           grupo.addComponent(btFogo);
           grupo.addComponent(btEsquerda);
           grupo.setHorizontal(true);      
           grupo.setWidth(Display.getInstance().getDisplayWidth());
           this.manual = true;
                   
           return grupo;
       
       }
       nave = new NavePrincipalControlo(Display.getInstance().getDisplayWidth()/2,Display.getInstance().getDisplayHeight()-200,0);
           
       grupo.addComponent(btDireita);
       grupo.addComponent(btFogo);
       grupo.addComponent(btEsquerda);
       grupo.setHorizontal(true);      
       grupo.setWidth(Display.getInstance().getDisplayWidth());
       this.manual = true;
       return grupo;
       
       
   
   }
    public void paint(Graphics g, Rectangle rect) {

       
        super.paint(g);     
        
        if(!isManual()){
                
                if(nave.getX() > (Display.getInstance().getDisplayWidth()/2)+70)
                    nave.setDirecao(-1);
                else if(nave.getX() < 100)
                    nave.setDirecao(1);
        }                
        if(colisao.definicoes.jogoActivo){ 
            super.setBgImage(createImage);
            nave.criaNave(g);
            nave.mover();
            geraNavesInimiga(g);
            disparo(g);
            actualiza(g);
            resistencia.setText("Vidas: "+nave.getResistencia());
            pontuacao.setText("Pontos: "+colisao.defModelo.getPontos());
       }

    }

    /**
     * @return the capturaPosicao
     */
    public int getCapturaPosicao() {
        return capturaPosicao;
    }

    /**
     * @param capturaPosicao the capturaPosicao to set
     */
    public void setCapturaPosicao(int capturaPosicao) {
        this.capturaPosicao = capturaPosicao;
    }
    /**
     * @return the manual
     */
    public boolean isManual() {
        return manual;
    }

    /**
     * @param manual the manual to set
     */
    public void setManual(boolean manual) {
        this.manual = manual;
    }
 
   
    public int dificuldade(){
    
    
        if(defModelo.getPontos() == 1000){
        
                return 1;                 
        }
        else if(defModelo.getPontos() == 2000){
            
            return 3;
        
        }
        else if(defModelo.getPontos() == 3000){
            
            return 5;
        
        }
        
        return 1;
    
    }
    public void actualiza(Graphics g){
            
          colisao.destroiNaveInimiga(naveInimiga, disparo, colisao.colisaoNaveInimiga(naveInimiga,disparo),g);
          novasNaves();
          colisao.destroiNavePrincipal(nave,  colisao.colisaoNavePrincipal(naveInimiga,nave),g);
          novasNaves();
            
    }

    public void adicionarDisparo() {
        disparo.add (new ArmaControlo (nave.getX()+25, nave.getY(), -2, 10));
    }
     
    
     public void novasNaves()
     {
        for(int i=0;i<naveInimiga.size();i++)

            if(!naveInimiga.get(i).isColisao() && naveInimiga.get(i).getY()<600)
            {
                naveInimiga.get(i).setY(naveInimiga.get(i).getY());
            }
            else
            {
  
               if(naveInimiga.get(i).isColisao())
                    defModelo.setPontos(5);
            
               naveInimiga.get(i).setY(80);
               naveInimiga.get(i).setX((Display.getInstance().getDisplayWidth()/2)-140 + i * 55);
               naveInimiga.get(i).setColisao(false);
          
            }

    }
     
     public void geraNavesInimiga(Graphics g){
     
         for(int i=0; i <naveInimiga.size();i++)
         {

                if(!naveInimiga.get(i).isColisao()){
                     
                    if(dificuldade() == 1){
            
                        naveInimiga.get(posicaoI.getPos()).setDirecao(controlo.NORMAL);
                        naveInimiga.get(i).criaNave(g);
                        naveInimiga.get(i).mover();
                        //setPos(i);

                     }if(dificuldade() == 3){
            
                        naveInimiga.get(posicaoI.getPos()).setDirecao(controlo.DIFICIL);
                        naveInimiga.get(i).criaNave(g);
                        naveInimiga.get(i).mover();
                        posicaoI.setPos(i);

                     }if(dificuldade() == 5){
            
                        naveInimiga.get(posicaoI.getPos()).setDirecao(controlo.SUPERDIFICIL);
                        naveInimiga.get(i).criaNave(g);
                        naveInimiga.get(i).mover();
                        posicaoI.setPos(i);

                     }else{

                        naveInimiga.get(i).criaNave(g);
                        naveInimiga.get(i).mover();
                        //setPos(i);
                        
                     }

                }

            }
        

     
     
     }
      public void disparo(Graphics g){
         
         for(int i=0; i < disparo.size(); i++)
         {
//                if(!disparo.get(i).isColisao()){
                    disparo.get(i).tiro(g);
                    disparo.get(i).mover();
//                }
                
            }
         
        }
      
      
    public void run() {
        while ( true )
       {
           
           try 
           {
               this.repaint();
               Thread.currentThread ().sleep ( 20);
               
           } 
           catch ( InterruptedException ex ) 
           {
               
           }
           
       }
    }
    

}

    
 