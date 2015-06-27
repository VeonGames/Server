/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Items.Weapon;

import Attacks.AttackBox;
import Item.Item;
import Items.Rune.Rune;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author nicno90
 */
public class Weapon extends Item
{

    protected int damage;
    protected int minLevel;
    protected Rune[] runes;
    protected int timeX;
    protected int damageX;
    protected int type;
    protected File file;

    public Weapon()
    {
        super(1);
        damage = 10;
        timeX = 1;
        minLevel = 1;
        name = "Weapon";
        runes = new Rune[1];
        type = 0;
    }

    public int getLevel()
    {
        return minLevel;
    }

    public int getDamage()
    {
        int sum = 0;
        for (Rune r : runes)
        {
            sum += r.getDamage();
        }
        return damage;
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

    public int getTimeX()
    {
        return timeX;
    }
    
    public int getType()
    {
        return type;
    }
    
    public File getFile()
    {
        return file;
    }
}
