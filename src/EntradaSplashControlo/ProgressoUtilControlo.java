/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package EntradaSplashControlo;

import com.codename1.ui.Display;
import Visao.TelaPrincipal;

/**
 *
 * @author David
 */
public abstract class ProgressoUtilControlo {
    
    

public final void start() {
    
   if(Display.getInstance().isEdt()) {
       
       taskStarted();
       
   } else {
       
       Display.getInstance().callSeriallyAndWait(new Runnable() {
           public void run() {
               
               taskStarted();
               
           }
       });
   }
   new Thread(new Runnable() {
       
       public void run() {          
           
           if(Display.getInstance().isEdt()) {
               taskFinished();
           } else {
               
               performTask();
               Display.getInstance().callSerially(this);
           }
       }
   }).start();
}

public void taskStarted() {
 
}

public abstract void performTask();

public void taskFinished() {
    
     TelaPrincipal tela = new TelaPrincipal(0,0);
     tela.show();
}
    
}
