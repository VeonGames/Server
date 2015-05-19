/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brightdeathserver;

import Item.Item;

import java.util.ArrayList;

/**
 *
 * @author nicno90
 */
public class Storage {

    private int c = 5;
    private int r = 4;
    private Item[][] inv;
    private int nextX;
    private int nextY;

    public Storage(int c, int r) {
        this.c = c;
        this.r = r;
        inv = new Item[c][r];
        nextX = 0;
        nextY = 0;
    }

    public Storage() {
        inv = new Item[c][r];
    }

    public Item add(Item i, int x, int y) {
        Item ret = inv[x][y];
        inv[x][y] = i;
        return ret;
    }
    public void add(Item i)
    {
        inv[nextX][nextY] = i;
    }

    public void remove(int indexX, int indexY) {
        inv[indexX][indexY] = null;
    }
    
}
