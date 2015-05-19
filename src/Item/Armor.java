/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Item;

/**
 *
 * @author nicno90
 */
public class Armor extends Item
{
    protected int health;
    
    protected int level;
    protected int part; //0 = healm     1 = chest   2 = arms    3 = legs    4 = boots
    protected Rune[] runes;
    
    public Armor()
    {
        super(1);
        health = 10;
        level = 1;
        part = 0;
        runes = new Rune[1];
    }

    public int getHealth()
    {
        int sum = 0;
        for (Rune r: runes)
        {
            sum += r.health;
        }
        return health + sum;
    }
    
    public boolean addRune(Rune r)
    {
        for (Rune rune : runes)
        {
            if (rune == null)
            {
                rune = r;
                return true;
            }
        }
        return false;
    }
    
    public int getLevel()
    {
        return level;
    }

    public int getPart()
    {
        return part;
    }

    
    
}
