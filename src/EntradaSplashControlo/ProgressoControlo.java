/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package EntradaSplashControlo;

import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.plaf.Style;

/**
 *
 * @author David
 */
public class ProgressoControlo  extends Component {
private byte percent;
private Image filled;

public ProgressoControlo() {
   setFocusable(false);
}

public ProgressoControlo(Image filled) {
   this();
   this.filled = filled;
}

@Override
public String getUIID() {
   return "Progress";
}

public byte getProgress() {
   return percent;
}

public void setProgress(byte percent) {
   this.percent = percent;
   repaint();
}

@Override
protected Dimension calcPreferredSize() {
   if(filled != null) {
       return new Dimension(filled.getWidth(), filled.getHeight());
   } else {
    
       return new Dimension(Display.getInstance().getDisplayWidth(),
           Font.getDefaultFont().getHeight());
   }
}

@Override
public void paint(Graphics g) {
   int width = (int)((((float)percent) / 100.0f) * getWidth());
   if(filled != null) {
       if(filled.getWidth() != getWidth()) {

           filled = filled.scaled(getWidth(), getHeight());
       }
  
       g.drawImage(filled, getX(), getY());
       g.clipRect(getX(), getY(), width, getHeight());

   } else {
       
       Style s = getStyle();
       g.setColor(s.getBgColor());
       int curve = getHeight() / 2 - 1;
       g.fillRoundRect(getX(), getY(), getWidth() - 1, getHeight() - 1, curve, curve);
       g.setColor(s.getFgColor());
       g.drawRoundRect(getX(), getY(), getWidth() - 1, getHeight() - 1, curve, curve);
       g.clipRect(getX(), getY(), width - 1, getHeight() - 1);
       g.setColor(0x5F9EA0);
       g.fillRoundRect(getX(), getY(), getWidth() - 1, getHeight() - 1, curve, curve);
   }
}
}
