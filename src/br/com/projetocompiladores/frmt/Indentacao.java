/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetocompiladores.frmt;

/**
 *
 * @author WILLIAM
 */
public class Indentacao {
    private static int stack=1;
    
    public Indentacao(){
        super();
    }
    public String Tab(){
        return "\t".repeat(stack);
    }
    
    public void Indenta(){
        stack++;
    }
    
    public void DeIndenta(){
        stack--;
    }
    
    @Override
    public String toString(){
        return Tab();
    }
}
