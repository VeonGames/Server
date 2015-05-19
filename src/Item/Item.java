/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Item;

import java.awt.image.BufferedImage;

/**
 *
 * @author nicno90
 */
public class Item 
{
    protected int value;
    protected int amount;
    protected String name;
    protected int type;
    protected BufferedImage image;
    
    public Item(int amount)
    {
        
        this.amount = amount;
        name = "Item";
        type = 0;
        value = 10;
    }

    public int getValue()
    {
        return value;
    }

    public int getAmount()
    {
        return amount;
    }

    public String getName()
    {
        return name;
    }

    public int getType()
    {
        return type;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public BufferedImage getImage()
    {
        return image;
    }
    
    
}
