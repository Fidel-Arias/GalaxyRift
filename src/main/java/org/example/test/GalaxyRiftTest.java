package org.example.test;

import static org.junit.Assert.*;

import org.example.Asteroide;
import org.example.Balazos;
import org.example.Inicio;
import org.example.Nave;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Ellipse2D;

public class GalaxyRiftTest {
    private Nave nave;
    private Asteroide asteroide;
    private Balazos bala;
    private Inicio juego;

    @Before
    public void setUp() {
        nave = new Nave();
        juego = new Inicio();
    }
    @Test
    public void testPosicionInicialNave() {
        assertEquals(200, nave.getX());
        assertEquals(450, nave.getY());
    }
    @Test
    public void testMovimientoNave() {
        nave.setX(300);
        nave.setY(400);
        assertEquals(300, nave.getX());
        assertEquals(400, nave.getY());
    }
    @Test
    public void testHitboxNave() {
        Ellipse2D hitbox = nave.obtienePosicionNave();
        assertNotNull(hitbox);
        assertEquals(200+5, (int)hitbox.getX());
        assertEquals(450+40, (int)hitbox.getY());
    }
    @Test
    public void testPerderVida() {
        int vidasIniciales = nave.getVida();
        nave.perderVida();
        assertEquals(vidasIniciales - 1, nave.getVida());
    }
    @Test
    public void testCreacionAsteroide() {
        Asteroide ast = new Asteroide(100, 100);
        assertTrue(ast.isVisible());
        assertEquals(30, ast.getAncho());
        assertEquals(30, ast.getAlto());
    }
    @Test
    public void testAsteroideFueraDePantalla() {
        Asteroide ast = new Asteroide(100, 701);
        ast.mover();
        assertFalse(ast.isVisible());
    }
    @Test
    public void testCreacionBala() {
        Balazos bala = new Balazos(200, 200);
        assertTrue(bala.isVisible());
        assertEquals(8, bala.getAncho());
        assertEquals(70, bala.getAlto());
    }
    @Test
    public void testInicializacionJuego() {
        assertEquals(1, Inicio.nivel);
        assertEquals(0, juego.puntaje);
        assertNotNull(juego.balas);
        assertNotNull(juego.asteroides);
    }
}