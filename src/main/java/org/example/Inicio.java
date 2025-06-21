package org.example;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Inicio extends JPanel implements ActionListener {
    URL direccionSonidoChoque1,direccionSonidoChoque2,direccionSonidoDisparo;
    String sonidoChoque1 = "src/main/resources/sounds/golpe.wav";
    String sonidoChoque2 = "src/main/resources/sounds/golperoca.wav";
    String sonidoDisparo = "src/main/resources/sounds/blaster.wav";
    Nave nave=new Nave();
    List<Balazos> balas;
    List<Asteroide> asteroides;
    Timer timer;
    public static int nivel=1;
    private int puntaje;
    boolean explosion=false;

    private void ReproducirSonido(String sound) {
        new Thread(() -> {
            try {
                File audifile = new File(sound);
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audifile);
                AudioFormat format = audioInputStream.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class, format);
                Clip clip = (Clip) AudioSystem.getLine(info);
                clip.open(audioInputStream);
                clip.start();
                // No usar Thread.sleep aquí
                clip.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.close();
                    }
                });
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                System.err.println("Error al reproducir el audio: " + e.getMessage());
            }
        }).start();
    }

    public Inicio() {
        balas = new ArrayList<>();
        asteroides = new ArrayList<>();
        puntaje = 0;
        nivel = 1;

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                nave.setX(mouseEvent.getX()-50);
                nave.setY(mouseEvent.getY());
            }
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) { }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                //izquierda
                if(keyEvent.getKeyCode()==37){}
                    //nave.teclaIzquierda Presionada(e);
                //arriba
                if (keyEvent.getKeyCode()==38){}
                    //nave.teclaArriba Presionada(e);
                //deracha
                if(keyEvent.getKeyCode()==39){}
                    //nave.teclaDerecha Presionada(e);
                //abajo
                if(keyEvent.getKeyCode()==40){}
                    //nave.teclaAbajo Presionada(e);
                //espacio
                if(keyEvent.getKeyCode()==32) {
                    ReproducirSonido(sonidoDisparo);
                    balas.add(new Balazos (nave.getX()+50, nave.getY()));
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                    //izquierda
                    if (keyEvent.getKeyCode()==37){}
                        //nave.teclaIzquierdaLevantada(e);
                    //arriba
                    if(keyEvent.getKeyCode()==38){}
                        //nave.teclaArribaLevantada(e);
                    //deracha
                    if (keyEvent.getKeyCode()==39){}
                        //nave.teclaDerechaLevantada(e);
                    //abajo
                    if (keyEvent.getKeyCode()==40){}
                        //nave.teclaAbajoLevantada(e);
            }
        });

        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        setFocusTraversalKeysEnabled(false);

        timer = new Timer(4, this);
        timer.start();

        //Generar asteroides en el nivel inicial
        generarAsteroides(5);
    }

    public void actionPerformed(ActionEvent e) {
        verificarColisiones();
        moverBalas();
        moverNave();
        moverAsteroides();

        // Repaint(): Se llama para actualizar la pantalla
        repaint(); // Actualizar la pantalla
    }

    public void moverNave() {
        nave.obtienePosicionNave();
    }

    public void moverBalas() {
        for (int i = 0; i < balas.size(); i++) {
            Balazos bala = balas.get(i);
            if (bala.isVisible()) {
                bala.mover();
            } else {
                balas.remove(i);
                i--;
            }
        }
    }

    public void moverAsteroides() {
        for (int i = 0; i < asteroides.size(); i++) {
            Asteroide asteroide = asteroides.get(i);
            if (asteroide.isVisible()) {
                asteroide.mover();
            } else {
                asteroides.remove(i);
                i--;
            }
        }
    }

    public void generarAsteroides(int cantidad) {
        Random rand = new Random();
        for (int i = 0; i < cantidad; i++) {
            int x = rand.nextInt(470); // Ancho de la pantalla
            int y = rand.nextInt(100) - 200; // Generar fuera de la pantalla
            asteroides.add(new Asteroide(x, y));
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Cargar y dibujar el fondo
        ImageIcon fondo = new ImageIcon("src/main/resources/images/fondo.jpg");
        g.drawImage(fondo.getImage(), 0, 0, 500, 700, null);

        //Cargar y dibujar la nave
        ImageIcon naveIcon = new ImageIcon("src/main/resources/images/ovni.png");
        g.drawImage(naveIcon.getImage(), nave.getX(), nave.getY(), nave.getAncho(), nave.getAlto(), null);
        //Contorno de Figura
        g.setColor(Color.RED);
        g.drawOval(nave.getX() + 15, nave.getY() + 10, nave.getAncho() - 30, nave.getAlto() - 20);

        //Dibujar balas
        ImageIcon laser = new ImageIcon("src/main/resources/images/laser_nave.png");
        for (Balazos bala : balas) {
            if (bala.isVisible()) {
                g.drawImage(laser.getImage(), bala.getX(), bala.getY(), bala.getAncho(), bala.getAlto(), null);
                g.drawRect(bala.getX(), bala.getY(), bala.getAncho(), bala.getAlto());
            }
        }

        // Dibujar asteroides
        ImageIcon piedra = new ImageIcon("src/main/resources/images/stone.png");
        for (Asteroide asteroide : asteroides) {
            if (asteroide.isVisible()) {
                g.setColor(Color.RED);
                g.drawImage(piedra.getImage(), asteroide.getX(), asteroide.getY(), asteroide.getAncho(), asteroide.getAlto(), null);
                g.drawRect(asteroide.getX()+5, asteroide.getY()+5, asteroide.getAncho()-10, asteroide.getAlto()-70);
            }
        }

        // Dibujar puntaje y nivel
        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier", Font.PLAIN, 22));
        g.drawString("Puntaje: " + puntaje, 10, 20);
        g.drawString("Nivel: " + nivel, 10, 45);
        // Dibujar vida de la nave
        g.drawString("Vidas: " + nave.getVida(), 10, 70);

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void verificarColisiones() {
        Ellipse2D naveHitbox = nave.obtienePosicionNave();

        // Verificar colisiones entre balas y asteroides
        for (int i = 0; i < balas.size(); i++) {
            Balazos bala = balas.get(i);
            Rectangle balaHitbox = bala.obtienePosicionBala();

            for (int j = 0; j < asteroides.size(); j++) {
                Asteroide asteroide = asteroides.get(j);
                Rectangle asteroideHitbox = asteroide.obtienePosicionAsteoride();

                if (balaHitbox.intersects(asteroideHitbox)) {
                    try {
                        ReproducirSonido(sonidoChoque2);
                        balas.remove(i);
                        i--;
                        asteroides.remove(j);
                        j--;
                        puntaje += 10; // Incrementar puntaje por destruir un asteroide
                    } catch (IndexOutOfBoundsException ex) {
                        System.out.println("Error al eliminar balas o asteroides: " + ex.getMessage());
                    }
                }
            }
        }

        // Verificar colisiones entre la nave y los asteroides
        for (int i = 0; i < asteroides.size(); i++) {
            Asteroide asteroide = asteroides.get(i);
            Rectangle asteroideHitbox = asteroide.obtienePosicionAsteoride();

            if (naveHitbox.intersects(asteroideHitbox)) {
                ReproducirSonido(sonidoChoque1);
                asteroides.remove(i);
                i--;
                nave.perderVida();

                if (nave.getVida() <= 0) {
                    // Fin del juego
                    JOptionPane.showMessageDialog(this, "¡Game Over! Puntaje final: " + puntaje);
                    System.exit(0);
                }
            }
        }
        // Verificar si el nivel ha sido completado
        if (asteroides.isEmpty()) {
            nivel++;
            generarAsteroides(5 + nivel * 2); // Aumentar la cantidad de asteroides en cada nivel
        }
    }

    public static void main(String[] args) {
        JFrame ventana = new JFrame("Galaxy Rift");
        Inicio juego = new Inicio();
        ventana.add(juego);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int alto = 700, ancho = 500;
        int x = (screenSize.width - ancho) / 2;
        int y = (screenSize.height - alto) / 2;

        ventana.setLocation(x, y);
        ventana.setVisible(true);
        ventana.setSize(ancho, alto);
    }
}
