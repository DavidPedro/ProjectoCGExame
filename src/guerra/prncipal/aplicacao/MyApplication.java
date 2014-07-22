package com.mycompany.myapp;

import Controlo.NavePrincipalControlo;
import EntradaSplashControlo.ProgressoControlo;
import EntradaSplashControlo.ProgressoUtilControlo;
import Visao.GuerraNoEspaco;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;

public class MyApplication {

    private Form current;
    Graphics gf = null;
    Button bt;
    GuerraNoEspaco p;
    NavePrincipalControlo nave;
    
    public void init(Object context) {
        try {
            Resources theme = Resources.openLayered("/theme");
            UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
        } catch(IOException e){
        }

    }
    
    public void start(){
        
            if(current != null){
                current.show();
                return;
            }
            
            try {
            
            Form progressForm = new Form();
            progressForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
            ProgressoControlo p2 = new ProgressoControlo(Image.createImage("/entrada.jpg"));
            p2.getStyle().setBgTransparency(0);
            progressForm.addComponent(p2);
            final ProgressoControlo p1 = new ProgressoControlo();
            progressForm.addComponent(new Label("Iniciando..."));
            progressForm.addComponent(p1);
            
            new ProgressoUtilControlo() {
                public void performTask() {
                    for(byte b = 0 ; b <= 100 ; b++) {
                        try {
                            p1.setProgress(b);
                            Thread.sleep(100);
                            
                        } catch (InterruptedException ex) {
                        }
                    }
                    
                }
            }.start();
            
            
            progressForm.show();
        } catch (IOException ex) {
        }

    }
     

    public void stop() {
        current = Display.getInstance().getCurrent();
    }
    
    public void destroy() {
    }

}
