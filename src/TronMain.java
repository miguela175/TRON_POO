import Modelo.ModeloTron;
import Vista.VistaTron;
import Vista.MenuTron;
import Controlador.ControladorTron;

import javax.swing.*;
import javax.sound.sampled.*;
import java.io.File;

public class TronMain {
    public static void main(String[] args) {
        JFrame f = new JFrame("Tron MVC");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MenuTron menu = new MenuTron();
        f.setContentPane(menu);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);


        playSound("resources/menu.wav");


        menu.getPlayButton().addActionListener(e -> {
            ModeloTron model = new ModeloTron(80,60);
            VistaTron view = new VistaTron(model,10);
            new ControladorTron(model,view);

            f.setContentPane(view);
            f.pack();
            f.setLocationRelativeTo(null);


            view.requestFocusInWindow();
        });
    }

    private static void playSound(String path) {
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