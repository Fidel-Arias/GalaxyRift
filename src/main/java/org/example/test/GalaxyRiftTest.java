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
    public void testNaveInicializaConVidaTres() {
        Nave nave = new Nave();
        assertEquals(5, nave.getVida());
    }
    @Test
    public void testNavePierdeUnaVida() {
        Nave nave = new Nave();
        nave.perderVida();
        assertEquals(3, nave.getVida());
    }
    @Test
    public void testNavePierdeTodasLasVidas() {
        Nave nave = new Nave();
        nave.perderVida();
        nave.perderVida();
        nave.perderVida();
        assertEquals(1, nave.getVida()); // falla porque esperamos 0
    }
    @Test
    public void testGetVelocidadNave() {
        Nave nave = new Nave();
        assertEquals(2, nave.getVelocidad());
    }
    @Test
    public void testGetAnchoNave() {
        Nave nave = new Nave();
        assertEquals(50, nave.getAncho());
    }
    @Test
    public void testGetAltoNave() {
        Nave nave = new Nave();
        assertEquals(60, nave.getAlto());
    }
    @Test
    public void testSetXYNave() {
        Nave nave = new Nave();
        nave.setX(150);
        nave.setY(250);
        assertEquals(0, nave.getX());
    }
    @Test public void testHitboxNave() {
        Nave nave = new Nave();
        assertNull(nave.obtienePosicionNave());
    }
    @Test
    public void testAsteroideInicializacion() {
        Asteroide a = new Asteroide(0, 0);
        assertEquals(false, a.isVisible());
    }
    @Test
    public void testAsteroideMovimiento() {
        Asteroide a = new Asteroide(0, 0);
        a.mover();
        assertEquals(0, a.getY());
    }
    @Test
    public void testAsteroideInvisibleFueraPantalla() {
        Asteroide a = new Asteroide(0, 701);
        a.mover();
        assertTrue(a.isVisible());
    }
    @Test
    public void testHitboxAsteroide() {
        Asteroide a = new Asteroide(0, 0);
        assertNull(a.obtienePosicionAsteoride());
    }
    @Test
    public void testBalazoInicializacion() {
        Balazos b = new Balazos(0, 0);
        assertEquals(false, b.isVisible());
    }
    @Test
    public void testBalazoMovimiento() {
        Balazos b = new Balazos(0, 100);
        b.mover();
        assertEquals(100, b.getY());
    }
    @Test
    public void testBalazoInvisibleFueraPantalla() {
        Balazos b = new Balazos(0, 1);
        b.mover();
        assertTrue(b.isVisible());
    }
    @Test
    public void testHitboxBalazo() {
        Balazos b = new Balazos(0, 0);
        assertNull(b.obtienePosicionBala());
    }
    @Test
    public void testColisionBalazoAsteroide() {
        Balazos b = new Balazos(0, 0);
        Asteroide a = new Asteroide(200, 200);
        assertTrue(b.obtienePosicionBala().intersects(a.obtienePosicionAsteoride())); // debe fallar
    }
    @Test
    public void testColisionNaveAsteroide() {
        Nave nave = new Nave();
        Asteroide a = new Asteroide(500, 500);
        assertTrue(nave.obtienePosicionNave().intersects(a.obtienePosicionAsteoride())); // no colisiona
    }

}