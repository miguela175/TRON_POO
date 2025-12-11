package Controlador;

import Modelo.ModeloTron;
import Vista.VistaTron;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class ControladorTron implements ActionListener, KeyListener {
    private final ModeloTron model;
    private final VistaTron view;
    private final Timer timer;


    private boolean collisionPlayed = false;

    public ControladorTron(ModeloTron m, VistaTron v) {
        this.model = m;
        this.view = v;
        timer = new Timer(60, this);
        timer.start();
        v.addKeyListener(this);
        v.setFocusable(true);
        v.requestFocusInWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (model.state == ModeloTron.State.RUNNING) {
            model.step();
            if (model.state == ModeloTron.State.GAME_OVER && !collisionPlayed) {
                playSound("resources/collision.wav");
                collisionPlayed = true;
            }
        }
        view.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_R:
                if (model.state == ModeloTron.State.GAME_OVER) {
                    model.reset();
                    collisionPlayed = false;
                }
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            // P1
            case KeyEvent.VK_W: model.p1.dir = ModeloTron.Dir.UP; break;
            case KeyEvent.VK_S: model.p1.dir = ModeloTron.Dir.DOWN; break;
            case KeyEvent.VK_A: model.p1.dir = ModeloTron.Dir.LEFT; break;
            case KeyEvent.VK_D: model.p1.dir = ModeloTron.Dir.RIGHT; break;
            // P2
            case KeyEvent.VK_UP: model.p2.dir = ModeloTron.Dir.UP; break;
            case KeyEvent.VK_DOWN: model.p2.dir = ModeloTron.Dir.DOWN; break;
            case KeyEvent.VK_LEFT: model.p2.dir = ModeloTron.Dir.LEFT; break;
            case KeyEvent.VK_RIGHT: model.p2.dir = ModeloTron.Dir.RIGHT; break;
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    private void playSound(String path) {
        try {
            File f = new File(path);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();


            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });
        } catch(Exception ex) {
            System.out.println("Error reproduciendo sonido: " + ex.getMessage());
        }
    }
}