package org.example;

import java.awt.geom.Ellipse2D;
public class Nave {
    private int x = 200;
    private int y = 450;
    private int velocidad=1;
    private int ancho=99;
    private int alto=57;
    private int vida=3;
    public Nave(){
        this.x=x;
        this.y=y;
        this.ancho=ancho;
        this.alto=alto;
        this.velocidad=velocidad;
        this.vida=vida;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getAlto() {
        return alto;
    }
    public int getAncho() {
        return ancho;
    }
    public int getVelocidad() {
        return velocidad;
    }
    public int getVida() {
        return vida;
    }
    public void perderVida() {
        vida--;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Ellipse2D obtienePosicionNave(){
        return new Ellipse2D.Double(x+5,y+40,ancho-20,alto-20);
    }
}
