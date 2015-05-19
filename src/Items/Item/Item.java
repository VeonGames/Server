/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Items.Item;

import java.awt.image.BufferedImage;
import java.io.File;

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
    private int amountInStack = 1;
    private int x = -1;
    private int y = -1;

    protected File txtAttack;

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

    public File getImage()
    {
        return txtAttack;
    }

    public void add()
    {
        amountInStack++;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

}
