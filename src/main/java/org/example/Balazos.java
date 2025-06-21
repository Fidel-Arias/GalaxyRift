package org.example;

import java.awt.Rectangle;

public class Balazos {
    private int x;
    private int y;
    private int velocidad;
    private int ancho;
    private int alto;
    private boolean visible;

    public Balazos(int x, int y){
        this.x=x;
        this.y=y;
        this.velocidad=5;
        this.ancho=8;
        this.alto=70;
        this.visible=true;
    }
    public void mover(){

        y-=velocidad;//mover hacia arriba
        if (y<0) {
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
    public Rectangle obtienePosicionBala(){
        return new Rectangle(x,y,ancho,alto);
    }
}
