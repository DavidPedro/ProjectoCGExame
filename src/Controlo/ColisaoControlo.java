/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlo;

import Modelo.DefinicaoModelo;
import Modelo.Posicao;
import Visao.GameOver;
import com.codename1.ui.Graphics;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class ColisaoControlo {
    
    public DefinicaoControlo definicoes = new DefinicaoControlo();
    Posicao posicaoI = new Posicao();
    public NaveInimigaControlo naveInimig = new NaveInimigaControlo();
    public NavePrincipalControlo navePrincipal = new NavePrincipalControlo();

    public DefinicaoModelo defModelo = new DefinicaoModelo();

    
   
        public int colisaoNavePrincipal(ArrayList<NaveInimigaControlo> naveInimiga, NavePrincipalControlo nave){
        
        for(int i=0; i < naveInimiga.size();i++)
        {
                
                if(naveInimiga.get(i).intersects(nave)){
                    nave.setColisao(true);
                    return 1;

                }
                else if(nave.intersects(naveInimiga.get(i))){

                     nave.setColisao(true);

                     return 1;

                }
        
            }      
      
        return -1;
    }
    
        public int colisaoNaveInimiga(ArrayList<NaveInimigaControlo> naveInimiga, ArrayList<ArmaControlo> disparo){
               
        for(int i=0; i < naveInimiga.size();i++)
        {
            for(int j = 0; j < disparo.size();j++ ){
                
                if(naveInimiga.get(i).intersects(disparo.get(j))){
                    naveInimig.setInimigos();
                    defModelo.setPontos(5);
                    naveInimiga.get(i).setColisao(true);
                    disparo.get(j).setColisao(true);
                    disparo.get(i).setColisao(true);
                  //  setCapturaPosicao(i);                    
                    return 1;

                }
                else if(disparo.get(j).intersects(naveInimiga.get(i))){

                     disparo.get(j).setColisao(true);
                   //  setCapturaPosicao(j);
                     return 1;

                }
        
            }
     
        }
    
        return -1;
    }
    
     public void destroiNavePrincipal(NavePrincipalControlo nave, int i, Graphics g){
    
         if(i > -1){
             nave.setAtaqueSofrido();
             
             if(nave.getAtaqueSofrido()==22)
                 nave.setResistencia();
             
             if(nave.getAtaqueSofrido() == 44)
                 nave.setResistencia();   
             
             if(nave.getAtaqueSofrido() == 66)
                 nave.setResistencia(); 
              
            if(nave.getAtaqueSofrido()== 88)
            {
                 definicoes.jogoActivo = false; 
                 terminaOJogo();
            }
            else{
                
               // nave.setResistencia();
//                novasNaves();
                            
           }
              
              
        }
         
         
    
    }
    public void destroiNaveInimiga(ArrayList<NaveInimigaControlo> naveInimiga, ArrayList<ArmaControlo> disparo,int i, Graphics g){
    
        if(i > -1){

          //  pontos = defModelo.getPontos();

            naveInimiga.get(i).setVisible(false);
            naveInimiga.get(i).repaint();
            
//            disparo.get(getCapturaPosicao()).setVisible(false);
//            disparo.get(getCapturaPosicao()).repaint();
            
            disparo.get(i).setVisible(false);
            disparo.get(i).repaint();

        
        }
    }
    public void terminaOJogo(){  
    
         GameOver fimdoJogo = new GameOver(defModelo.getPontos());
         fimdoJogo.show();
    
    }
}
