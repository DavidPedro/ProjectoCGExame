/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlo;

import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class NaveInimigaControlo extends Form{
    
    private int altura, largura, x,y;
    private boolean colisao;
    private Point ponteiro;
    private int direcao;
    private int velocidade;
    
    private int naveDistruida = 0;
    private int inimigos = 20;
    
    private Dimension size;
    
    private ArrayList<ArmaControlo>  disparo; 

    public NaveInimigaControlo(int x, int y, int direcao) {
        
        this.x = x;
        this.y = y;
        this.direcao = direcao;
        this.largura = 40;
        this.altura = 40;
                
        disparo = new ArrayList<ArmaControlo>();

    }
    
    
    public NaveInimigaControlo() {
        
        
    }

    public void criaNave(Graphics g){
        
        g.setColor(0x000000);
        g.fillArc(getX(), getY(), getLargura(), getAltura(), 360,360);
        g.setColor(0xff0000);
        g.fillArc(getX()+7, getY()+7, getLargura()-15, getAltura()-15, 180,180);
        g.setColor(0xffffff);
        g.fillLinearGradient(0xffffff, 0x000000, getX()+2, getY()+2,5, 28,false);
        g.fillLinearGradient(0xffffff, 0x000000, getX()+33, getY()+2,5, 30,false);


    }
    public void mover ()
    {
        y += direcao;
    }

     public Rectangle getLocalizacao() {
         
        return new Rectangle(x, y, largura, altura);
        
    }
    
    public void adicionarDisparo() {
        disparo.add (new ArmaControlo(getX()+25, getY(), 2,10));
    }
    
    public void moveNave(){
    
        setDraggable(true);
    
    }
            
    public void isRect(){
    
        Rectangle rect = new Rectangle();
    
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
    public Rectangle intersection(NavePrincipalControlo r) {
        return intersection(r.getX(), r.getY(), r.getLargura(), r.getAltura());
    }
    public Rectangle intersection(ArmaControlo r) {
        return intersection(r.getX(), r.getY(), r.getLargura(), r.getAltura());
    }

    public boolean intersects(int x, int y, int width, int height) {
        int tw = getLargura();
        int th = getAltura();
        return intersects(this.x, this.y, tw, th, x, y, width, height);
    }

    public boolean intersects(NavePrincipalControlo rect) {
        return intersects(rect.getX(), rect.getY(), rect.getLargura(), rect.getAltura());
    }
    public boolean intersects(ArmaControlo rect) {
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
     * @return the x
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    @Override
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    @Override
    public void setY(int y) {
        this.y = y;
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

    /**
     * @return the direcao
     */
    public int getDirecao() {
        return direcao;
    }

    /**
     * @param direcao the direcao to set
     */
    public void setDirecao(int direcao) {
        this.direcao = direcao;
    }

    /**
     * @return the velocidade
     */
    public int getVelocidade() {
        return velocidade;
    }

    /**
     * @param velocidade the velocidade to set
     */
    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }
        /**
     * @return the inimigos
     */
    public int getInimigos() {
        return inimigos;
    }

    /**
     * @param inimigos the inimigos to set
     */
    public void setInimigos() {
        this.inimigos = this.inimigos - 1;
    }
      /**
     * @return the naveDistruida
     */
    public int getNaveDistruida() {
        return naveDistruida;
    }

    /**
     * @param naveDistruida the naveDistruida to set
     */
    public void setNaveDistruida(int naveDistruida) {
        this.naveDistruida = naveDistruida;
    }

    
    
}


