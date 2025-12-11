package Vista;

import Modelo.ModeloTron;

import javax.swing.*;
import java.awt.*;

public class VistaTron extends JPanel {
    private final ModeloTron model;
    private final int cellSize;

    public VistaTron(ModeloTron m, int cellSize) {
        this.model = m;
        this.cellSize = cellSize;
        setPreferredSize(new Dimension(m.getCols()*cellSize, m.getRows()*cellSize));
        setBackground(Color.BLACK);


        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.DARK_GRAY);
        for(Long k: model.getOccupied()) {
            int x=(int)(k>>32), y=(int)(k&0xffffffffL);
            g.fillRect(x*cellSize,y*cellSize,cellSize,cellSize);
        }

        if(model.p1.alive) {
            g.setColor(model.p1.color);
            g.fillRect(model.p1.x*cellSize, model.p1.y*cellSize, cellSize, cellSize);
        }
        if(model.p2.alive) {
            g.setColor(model.p2.color);
            g.fillRect(model.p2.x*cellSize, model.p2.y*cellSize, cellSize, cellSize);
        }

        g.setColor(Color.WHITE);
        g.drawString("P1: WASD | P2: Flechas | R: Reiniciar | Esc: Salir", 10, 20);

        if(model.state==ModeloTron.State.GAME_OVER) {
            g.drawString("Game Over - R para reiniciar", getWidth()/2-80, getHeight()/2);
        }
    }
}