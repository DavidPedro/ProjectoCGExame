/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlo;

import com.codename1.ui.Component;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Rectangle;

/**
 *
 * @author David
 */
public class ArmaControlo extends Component
{

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    public int getDireccao() {
        return direccao;
    }

    public void setDireccao(int direccao) {
        this.direccao = direccao;
    }
    private int x;
    private int y;
    
    private int direccao;
    private int velocidade;
    private Dimension size;
    private int altura;
    private int largura;
    private boolean colisao;

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }
    
    public ArmaControlo ()
    {
        x = 0;
    }

    public ArmaControlo(int x, int y, int direccao, int velocidade) {
        this.x = x;
        this.y = y;
        this.altura = 5;
        this.largura = 5;
        this.direccao = direccao;
        this.velocidade = velocidade;
    }
    
    public void setaDisparo(){
    
        this.direccao = 0;
    
    }    
    
    public Rectangle getLocalizacao() {
         
        return new Rectangle(x, y, 10, 10);
        
    }
    
    public void tiro(Graphics g){
    
        g.setColor(0x0043);
        g.fillRect(x, y, 5, 5);
    
        mover ();
    }
    
    public void mover ()
    {
        y += direccao;
    }
     
    public Rectangle intersection(int rX, int rY, int rW, int rH) {
        int tx1 = this.x;
        int ty1 = this.y;
        int rx1 = rX;
        int ry1 = rY;
        int tx2 = tx1; tx2 += this.size.getWidth();
        int ty2 = ty1; ty2 += this.size.getHeight();
        int rx2 = rx1; rx2 += rW;
        int ry2 = ry1; ry2 += rH;
        if (tx1 < rx1) {
            tx1 = rx1;
        }
        if (ty1 < ry1) {
            ty1 = ry1;
        }
        if (tx2 > rx2) {
            tx2 = rx2;
        }
        if (ty2 > ry2) {
            ty2 = ry2;
        }
        tx2 -= tx1;
        ty2 -= ty1;

        if (tx2 < Integer.MIN_VALUE) {
            tx2 = Integer.MIN_VALUE;
        }
        if (ty2 < Integer.MIN_VALUE) {
            ty2 = Integer.MIN_VALUE;
        }
        return new Rectangle(tx1, ty1, tx2, ty2);
    }
    public Rectangle intersection(NaveInimigaControlo r) {
        return intersection(r.getX(), r.getY(), r.getLargura(), r.getAltura());
    }

    public boolean intersects(int x, int y, int width, int height) {
        int tw = getLargura();
        int th = getAltura();
        return intersects(this.x, this.y, tw, th, x, y, width, height);
    }

   
    public boolean intersects(NaveInimigaControlo rect) {
        return intersects(rect.getX(), rect.getY(), rect.getLargura(), rect.getAltura());
    }

   public static boolean intersects(int tx, int ty, int tw, int th, int x, int y, int width, int height) {
        int rw = width;
        int rh = height;
        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }
        int rx = x;
        int ry = y;
        rw += rx;
        rh += ry;
        tw += tx;
        th += ty;
        return ((rw < rx || rw > tx) &&
                (rh < ry || rh > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry));

    }

    /**
     * @return the altura
     */
    public int getAltura() {
        return altura;
    }

    /**
     * @param altura the altura to set
     */
    public void setAltura(int altura) {
        this.altura = altura;
    }

    /**
     * @return the largura
     */
    public int getLargura() {
        return largura;
    }

    /**
     * @param largura the largura to set
     */
    public void setLargura(int largura) {
        this.largura = largura;
    }

    /**
     * @return the colisao
     */
    public boolean isColisao() {
        return colisao;
    }

    /**
     * @param colisao the colisao to set
     */
    public void setColisao(boolean colisao) {
        this.colisao = colisao;
    }

   
}

