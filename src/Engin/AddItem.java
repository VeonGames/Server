/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author nicno90
 */
public class AddItem {
    public static void main(String[] args)
    {
        boolean weapon = false;
        boolean armor = false;
        boolean food = false;
        boolean item = false;
        boolean rune = false;
        String file = "";
        String si = JOptionPane.showInputDialog("name of class/name of item in one word");
        file += "\npublic class " + si + " extends ";
        
        String typeItem = JOptionPane.showInputDialog("weapon, armor, rune, food");
        if (typeItem.equals("Weapon"))
        {
            file += "Weapon\n{\n";
            weapon = true;
        }
        else if (typeItem.equals("Armor"))
        {
            file += "Armor\n{\nprivate int dmg = 100; //out of 100% the lower the more dmg\nprivate Rune rune;";
            armor = true;
        }
        else if (typeItem.equals("Food"))
        {
            file += "Food\n{\n";
            food = true;
        }
        else if (typeItem.equals("Rune"))
        {
            file += "Rune\n{\n";
            rune = true;
        }
        else
        {
            typeItem = "Items";
            file += "Item\n{\n";
            item = true;
        }
        file = "package Items;\n" + file;
        file += "\nprivate int amountInStack = 1; \n"
                + "private int x = -1;\n"
                + "private int y = -1;\n"
                + "\npublic " + si + "()\n{\n"
                + "super();\n}\n"
                + " \npublic String name()\n{\nreturn \"";
        String s = "";
        file += si + "\";\n}\n\n\npublic int amount()\n{\nreturn amountInStack;\n}\n\npublic int amountAvailable()\n{\n"
                + "return 59;\n}\n\npublic void add()\n{\namountInStack++;\n}\n\n";
        if (!item)
        {
            
            
            if (weapon)
            {
                file += "public int range()\n{\nreturn 1;\n}\n\n";
                file += "\npublic int damage()\n{\nreturn ";
                s = JOptionPane.showInputDialog("damage");
                file += s + ";\n}\n\n";
            }
            else if (food)
            {
                s = JOptionPane.showInputDialog("how much health does it give? (int)");
                file += "public int health()\n{\nreturn " + s + ";\n}\n\n";
            }
            else if (armor)
            {
                s = JOptionPane.showInputDialog("How much health does it return?");
                file += "public int health()\n{\nreturn " + s + ";\n}\n\n";
                s = JOptionPane.showInputDialog("what type of armor:\n"
                        + "1) head\n"
                        + "2) chest\n"
                        + "3) pants\n"
                        + "4) boots\n"
                        + "5) guantlets");
                file += "public int type()\n{\nreturn " + s + ";\n}\n\n";
                s = JOptionPane.showInputDialog("Level");
                file += "public int level()\n{\nreturn " + s + ";\n}\n\npublic void damage(int amount)\n{\ndmg -= amount;\n}\n\n";
                file += "public int getDMG()\n{\nreturn dmg;\n}\n\n";
                file += "public Rune getRune()\n{\nreturn rune;\n}\n\n";
                file += "public void addRune(Rune r)\n{\nrune = r;\n}\n\n";
            }
            else if (rune)
            {
                s = JOptionPane.showInputDialog("how much stregth added");
                file += "public int stregth()\n{\nreturn " + s + ";\n}\n\n";
                s = JOptionPane.showInputDialog("how much damage added");
                file += "public int damage()\n{\nreturn " + s + ";\n}\n";
                s = JOptionPane.showInputDialog("how much speed added");
                file += "public int speed()\n{\nreturn " + s + ";\n}\n";
                s = JOptionPane.showInputDialog("how much health added");
                file += "public int health()\n{\nreturn " + s + ";\n}\n\n";
            }
            
        }
        file += "public int getX()\n{\nreturn this.x;\n}\n"
                + "public int getY()\n{\nreturn this.y;\n}\n\n"
                + "public void setX(int x)\n{\nthis.x = x;\n}\n"
                + "public void setY(int y)\n{\nthis.y = y;\n}\n\n";
        s = JOptionPane.showInputDialog("How musch is it worth on the street? (int)");
            file += "public int value()\n{\nreturn " + s + ";\n}\n\n";
            file += "public void add(int num)\n{\namountInStack += num;\n}\n\n}";
        /**
         * ok xiao bao this is what you have to do to make this program work on your comp.
         * first add an item package to your make a new project called items and you need to put this method into it
         * then you are going to go upto the url of your folder directory you will copy it to the specified area
         */
        s = JOptionPane.showInputDialog("rarity higher for less rare");
        
        int num = Integer.parseInt(s);
        
        
        
                           //this part of the string right here                                     || <== dont go past theses 2 lines
        File f = new File("C:\\Users\\nicno90\\Documents\\NetBeansProjects\\Server\\src\\" + typeItem + "\\" + si + ".java");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        writer.print(file);
        writer.close();
//        for (int k = 1; k <= num; k++)
//        {
//            GV.Rarity.add()
//        }
    }
}
