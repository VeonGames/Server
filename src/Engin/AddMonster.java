package Engin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class AddMonster {

    public static void add()
    {
        String name = JOptionPane.showInputDialog("name");
        int type = 0;
        try
        {
           // BufferedReader read = new BufferedReader(new FileReader(new File(AddMonster.class.getResource("\\Type.txt").toString())));
            System.out.println(AddMonster.class.getResource("\\Type.txt").toString());
            BufferedReader read = new BufferedReader(new FileReader(new File("C:\\Users\\Nicno90\\Documents\\NetBeansProjects\\Server\\src\\Engin\\Type.txt")));
            String s = read.readLine();
            type = Integer.parseInt(s);
            //System.out.println(Integer.parseInt(read.readLine()));
            //PrintWriter writer = new PrintWriter(new File(AddMonster.class.getResource("\\Engin\\Type.txt").toString()));
            //writer.write(type+1);
            //writer.close();
            read.close();
        }
        catch (Exception ex)
        {
            System.out.println("here "+ex);
        }
        
        
        String out = "package Monsters;\n"
                + "\n"
                + "import brightdeathserver.Monster;\n"
                + "\n"
                + "/**\n"
                + " *\n"
                + " * @author nicno90\n"
                + " */\n"
                + "public class " + name + " extends Monster\n"
                + "{\n"
                + "\n"
                + "    public " + name + "(int x, int y) {\n"
                + "        super(x, y);\n"
                + "        type = " + type + ";\n"
                +"         passive = " + Boolean.parseBoolean(JOptionPane.showInputDialog("passive true or false")) +";\n"
                + "        starthealth = " + JOptionPane.showInputDialog("health") + ";\n"
                + "        damage = " + JOptionPane.showInputDialog("damage") + ";\n"
                + "        speed = " + JOptionPane.showInputDialog("speed") + ";\n"
                + "        agroRange = " + JOptionPane.showInputDialog("agro range") + ";\n"
                + "        chaseRange = " + JOptionPane.showInputDialog("chase range") + ";\n"
                + "        respawnTime = " + JOptionPane.showInputDialog("respawn time") + ";\n"
                + "        attackSpace = " + JOptionPane.showInputDialog("attack space (distance away from player at min)") + ";\n"
                + "    }\n"
                + "    \n"
                + "}";
        //File f = new File(AddMonster.class.getResource("/Monsters/" + name + ".java"));
        File f = new File(AddMonster.class.getResource("/Monsters/"+name+".java").toString());
        
        try
        {
            PrintWriter writer2 = new PrintWriter(f);
            writer2.print(out);
            writer2.close();
        }
        catch (FileNotFoundException ex)
        {
            
        }
        System.out.println("print into the client Monster.java into the setType()\n\n");
        System.out.println("else if (t == " + type +")\n"
                + "        {\n"
                + "            name = \"" + name + "\";\n"
                + "        }");
    }

}
