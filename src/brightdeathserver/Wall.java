/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brightdeathserver;

import java.awt.Color;

/**
 *
 * @author PJ
 */
public class Wall {
    
    private final int xcoord;
    private final int ycoord;
    private String name;
    public Wall(int x, int y, String type)
    {
        xcoord = x;
        ycoord = y;
        name = type;
    }
    
    public int getXCoord()
    {
     return xcoord;
    }
    
    public int getYCoord()
    {
     return ycoord;
    }
    
    public int getType()
    {
        if (name.equals("Rock"))
        {
            return 1;
        }
        else if (name.equals("Tree"))
        {
            return 2;
        }
        else if (name.equals("Invisible"))
        {
            return 3;
        }
        return 0;
    }
    
}