/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.util.ArrayList;

/**
 *
 * @author David
 */
public class DefinicaoModelo {
    
    private int cenario;
    private int dificuldade;
    private int modo;
    private int pontos;

    public int getCenario() {
        return cenario;
    }

    public void setCenario(int cenario) {
        this.cenario = cenario;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }

    public int getModo() {
        return modo;
    }

    public void setModo(int modo) {
        this.modo = modo;
    }
    
    /**
     * @return the pontos
     */
    public int getPontos() {
        return pontos;
    }

    /**
     */
    public void setPontos(int pontos) {
        this.pontos = this.pontos+pontos;
    }
    
}
