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
        assertEquals(3, nave.getVida());
    }

    @Test
    public void testNavePierdeUnaVida() {
        Nave nave = new Nave();
        nave.perderVida();
        assertEquals(2, nave.getVida());
    }

    @Test
    public void testNavePierdeTodasLasVidas() {
        Nave nave = new Nave();
        nave.perderVida();
        nave.perderVida();
        nave.perderVida();
        assertEquals(0, nave.getVida());
    }

    @Test
    public void testGetVelocidadNave() {
        Nave nave = new Nave();
        assertEquals(1, nave.getVelocidad());
    }

    @Test
    public void testGetAnchoNave() {
        Nave nave = new Nave();
        assertEquals(99, nave.getAncho());
    }

    @Test
    public void testGetAltoNave() {
        Nave nave = new Nave();
        assertEquals(57, nave.getAlto());
    }

    @Test
    public void testSetXYNave() {
        Nave nave = new Nave();
        nave.setX(100);
        nave.setY(200);
        assertEquals(100, nave.getX());
        assertEquals(200, nave.getY());
    }

    @Test
    public void testHitboxNave() {
        Nave nave = new Nave();
        assertNotNull(nave.obtienePosicionNave());
    }

    @Test
    public void testAsteroideInicializacion() {
        Asteroide a = new Asteroide(10, 20);
        assertTrue(a.isVisible());
        assertEquals(30, a.getAncho());
        assertEquals(30, a.getAlto());
    }

    @Test
    public void testAsteroideMovimiento() {
        Asteroide a = new Asteroide(0, 0);
        a.mover();
        assertEquals(1, a.getY());
    }

    @Test
    public void testAsteroideInvisibleFueraPantalla() {
        Asteroide a = new Asteroide(0, 700);
        a.mover();
        assertFalse(a.isVisible());
    }

    @Test
    public void testHitboxAsteroide() {
        Asteroide a = new Asteroide(0, 0);
        assertNotNull(a.obtienePosicionAsteoride());
    }

    @Test
    public void testBalazoInicializacion() {
        Balazos b = new Balazos(10, 20);
        assertTrue(b.isVisible());
        assertEquals(8, b.getAncho());
        assertEquals(70, b.getAlto());
    }

    @Test
    public void testBalazoMovimiento() {
        Balazos b = new Balazos(0, 100);
        b.mover();
        assertEquals(95, b.getY());
    }

    @Test
    public void testBalazoInvisibleFueraPantalla() {
        Balazos b = new Balazos(0, 1);
        b.mover();
        assertFalse(b.isVisible());
    }

    @Test
    public void testHitboxBalazo() {
        Balazos b = new Balazos(0, 0);
        assertNotNull(b.obtienePosicionBala());
    }

}