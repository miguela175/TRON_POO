package Modelo;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class ModeloTron {
    public enum Dir { UP, DOWN, LEFT, RIGHT }
    public enum State { MENU, RUNNING, GAME_OVER }

    public static class Player {
        public Color color;
        public int x, y;
        public Dir dir;
        public boolean alive = true;
        public Player(Color c, int x, int y, Dir d) {
            color = c; this.x = x; this.y = y; this.dir = d;
        }
    }

    private final int cols, rows;
    private final Set<Long> occupied = new HashSet<>();
    public Player p1, p2;
    public State state = State.MENU;

    public ModeloTron(int cols, int rows) {
        this.cols = cols; this.rows = rows;
        reset();
    }

    private long key(int x, int y) {
        return ((long)x << 32) ^ (y & 0xffffffffL);
    }

    public void reset() {
        occupied.clear();
        p1 = new Player(Color.CYAN, cols/4, rows/2, Dir.RIGHT);
        p2 = new Player(Color.MAGENTA, cols*3/4, rows/2, Dir.LEFT);
        occupied.add(key(p1.x,p1.y));
        occupied.add(key(p2.x,p2.y));
        state = State.RUNNING;
    }

    public void step() {
        if(state != State.RUNNING) return;
        move(p1);
        move(p2);
        if(!p1.alive || !p2.alive) state = State.GAME_OVER;
    }

    private void move(Player p) {
        if(!p.alive) return;
        int nx=p.x, ny=p.y;
        switch(p.dir) {
            case UP: ny--; break;
            case DOWN: ny++; break;
            case LEFT: nx--; break;
            case RIGHT: nx++; break;
        }
        if(nx<0||ny<0||nx>=cols||ny>=rows || occupied.contains(key(nx,ny))) {
            p.alive=false;
            return;
        }
        p.x=nx; p.y=ny;
        occupied.add(key(nx,ny));
    }

    public Set<Long> getOccupied(){ return occupied; }
    public int getCols(){ return cols; }
    public int getRows(){ return rows; }
}