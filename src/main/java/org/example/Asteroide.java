package org.example;

import java.awt.Rectangle;

public class Asteroide {
    private int x;
    private int y;
    private int velocidad;
    private int ancho;
    private int alto;
    private boolean visible;
    public Asteroide(int x, int y){
        this.x=x;
        this.y=y;
        this.velocidad=1;
        this.ancho=30;
        this.alto=30;
        this.visible=true;
    }
    public void mover(){

        y+=velocidad;//mover hacia arriba
        if (y>700) {
            visible=false; //ocultar el disparo cuando sale de la pantalla
        }
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
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public boolean isVisible() {
        return visible;
    }
    public Rectangle obtienePosicionAsteoride(){
        return new Rectangle(x,y,ancho,alto);
    }
}
