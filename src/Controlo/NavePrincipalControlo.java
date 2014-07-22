/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlo;

import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author David
 */
public class NavePrincipalControlo extends Form{
    
    private int altura;
    private int largura;
    private int x;
    private int y;
    private boolean colisao;

    private int resistencia = 4;

    private int pontos = 0;
    
    private int ataqueSofrido;
    private  Image image,naveImage;
    private Image createImage;
    private ArrayList<ArmaControlo>  disparo; 
    Point ponteiro;
    private ArrayList disparar;
    private Dimension size;
    private int direcao;
    ImagemControlo imgControlo = new ImagemControlo();
            

    public ArrayList getDisparar() {
        return disparar;
    }

    public void setDisparar(ArrayList disparar) {
        this.disparar = disparar;
    }

    public NavePrincipalControlo(){
    
    }
    public NavePrincipalControlo(int x, int y, int direcao) {
        

        this.largura = 5;
        this.altura = 5;
        this.x = x;
        this.y = y;
        this.direcao = direcao;
        setFocusable(true);
        
        disparo = new ArrayList<ArmaControlo>();
        
         try {
            
            if(image ==null){
                
                naveImage = image.createImage("/naveP.png");
             
            }
        } catch (IOException ex) {
            
            Dialog.show("Messagem", "Caminho da Imagem Invalida", "Ok", "Cancelar");
        }
         
                   
    }
    
    public void evento(){
    
         this.addPointerDraggedListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
        
                setX(getX());
                setY(getY());
                System.out.println("moveu:"+getY());
                
            }
        });
    
    }

        
    public void criaNave(Graphics g){
        evento();
        ponteiro = new Point(getX(), getY());

        drawDraggedImage(g, naveImage, ponteiro.getX(), ponteiro.getY());
        mover();
          
    }
    public void mover()
    {
        setX(getX() + direcao);
    }
    
    public Rectangle getLocalizacao() {
         
        return new Rectangle(getX(), getY(), largura, altura);
        
    }
    public void adicionarDisparo() {
        disparo.add (new ArmaControlo (getX()+25, getY(), -2, 10));
        setDisparar(disparo);
    }

    public void moveNave(){
    
        setDraggable(true);

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
    
     public Rectangle intersection(int rX, int rY, int rW, int rH) {
        int tx1 = this.getX();
        int ty1 = this.getY();
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
        return intersects(this.getX(), this.getY(), tw, th, x, y, width, height);
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
    
    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia() {
        this.resistencia = this.resistencia -1;
    }
        public int getAtaqueSofrido() {
        return ataqueSofrido;
    }

    public void setAtaqueSofrido() {
        this.ataqueSofrido = this.ataqueSofrido + 1;
    }
    
    public int getPontos() {
        return pontos;
    }

    public void setPontos() {
        this.pontos = this.pontos + 5;
    }
    
}
    

