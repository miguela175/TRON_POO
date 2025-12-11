package Vista;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MenuTron extends JPanel {
    private final JButton playButton;

    public MenuTron() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        JLabel title = new JLabel("TRON", SwingConstants.CENTER);

        try {

            Font tronFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/TRON.TTF"));
            tronFont = tronFont.deriveFont(Font.BOLD, 64f); // tamaño grande
            title.setFont(tronFont);
        } catch (Exception e) {

            title.setFont(new Font("Consolas", Font.BOLD, 48));
        }

        title.setForeground(Color.CYAN);

        playButton = new JButton("Jugar");
        playButton.setFont(new Font("Consolas", Font.BOLD, 24));
        playButton.setBackground(Color.DARK_GRAY);
        playButton.setForeground(Color.WHITE);

        add(title, BorderLayout.CENTER);
        add(playButton, BorderLayout.SOUTH);
    }

    public JButton getPlayButton() {
        return playButton;
    }
}