/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlo;

import Modelo.DefinicaoModelo;
import Modelo.Posicao;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class InicializaObjectoControlo {
    
    DefinicaoModelo definicoes = new DefinicaoModelo();
    ColisaoControlo colisao = new ColisaoControlo();
    Posicao posicaoI = new Posicao();
    Controlo controlo = new Controlo();
    
    public NavePrincipalControlo  nave = new NavePrincipalControlo(Display.getInstance().getDisplayWidth()/2,Display.getInstance().getDisplayHeight()-200,0);
            
    public ArrayList<ArmaControlo>  disparo = new ArrayList<ArmaControlo>();
    public ArrayList<NaveInimigaControlo>  naveInimiga = new ArrayList<NaveInimigaControlo>();
        
//    NaveInimigaControlo naveInimig = naveInimig = new NaveInimigaControlo(x, y, altura);

    
//    public InicializaObjectoControlo(){
//        
//       
//////        for(int i=0; i< 5; i++)
//////            naveInimiga.add( new NaveInimigaControlo(50*i+10,40,1));
//////
//////        for(int i=0; i< 3; i++)
//////             disparo.add( new ArmaControlo(nave.getX(), nave.getY(),5 ,5));
//        
//    }
    
   
        
    
}
