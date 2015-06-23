package brightdeathserver;

import Attacks.AttackBox;
import static brightdeathserver.BrightDeathServer.gui;
import java.util.ArrayList;
import java.util.List;

public class AIcontroller extends Thread
{

    public static List<Monster> monsters = new ArrayList();
    public static List<String> attackURLs = new ArrayList();
    public static List<String> attackURLsD = new ArrayList();
    public static List<Wall> walls = new ArrayList();
    public static List<AttackBox> boxes = new ArrayList();
    public static boolean connect = true;

    public void run()
    {
        gui.setCmdPrompt("AIcontroller is online");

        Wall stop = new Wall(800, 100, "Rock");
        walls.add(stop);
        Wall stop2 = new Wall(00, 60, "Rock");
        walls.add(stop2);
        Wall stop3 = new Wall(400, 350, "Rock");
        walls.add(stop3);
        BrightDeathServer.addMonsters();
        this.intializeURLs();
        this.intializeURLsD();
        Monster bill;
        Player player;

        while (connect)
        {
//            for (int i = 0; i < BrightDeathServer.players.size(); i++)
//            {
//                player = (Player) BrightDeathServer.players.get(i);
//                checkHit(player.getAttackType(), player.getXpos(), player.getYpos(), player.getAllAttack());
//            }
            
            
            //attakcs
            for (int k = 0; k < boxes.size(); k++)
            {
                checkHit(boxes.get(k));
                if (boxes.get(k).move())
                {
                    boxes.remove(k);
                }
            }
            //monsters
            for (int i = 0; i < monsters.size(); i++)
            {
                bill = (Monster) monsters.get(i);
                if (bill.isAlive())
                {
                    bill.setAgro(checkAgro(bill.getStartX(), bill.getStartY(), bill.isAgro(), bill.getAgroRange(), bill.getChaseRange()));
                    moveMonster(i);
                }
                else
                {
                    bill.revive();
                }
            }
            
            try
            {
                Thread.sleep(30);
            }
            catch (InterruptedException ex)
            {

            }
        }
    }
    
     public static void checkHit(AttackBox b)
     {
        int x = b.getXPos();
        int y = b.getYPos();
        
        for (Monster bill : monsters)
        {
            if ((x - 50 <= bill.monsterX && x + 50 >= bill.monsterX) || (y - 50 <= bill.getMonsterY() && y + 50 >= bill.getMonsterY()))
            {
                bill.getHit(b.damage());
                if (!b.isSplash())
                {
                    return;
                }
            }
        }
        
    }
    
    public static void moveMonster(int monsterNumber)
    {
        boolean agro;
        boolean hit = false;
        Monster bill = (Monster) monsters.get(monsterNumber);
        Player player = null;
        int targetX;
        int targetY;
        if (bill.isAgro() != -1)
        {
            player = BrightDeathServer.players.get(bill.isAgro());
            targetX = player.getXpos();
            targetY = player.getYpos();
            agro = true;
        }
        else
        {
            targetX = bill.getStartX();
            targetY = bill.getStartY();
            agro = false;
        }
        
        if (agro&&bill.isRanged)
        {
            int xRelation = bill.getMonsterX()-player.getXpos();
            if (xRelation>0&&xRelation<=250)
            {
                bill.setMonsterX(bill.getMonsterX() - bill.getSpeed());
                if (isWalled(bill.getMonsterX(), bill.getMonsterY()))
                {
                    bill.setMonsterX(bill.getMonsterX() + bill.getSpeed());
                }
            }
            else if (xRelation>250&&xRelation<500)
            {                
                bill.setMonsterX(bill.getMonsterX() + bill.getSpeed());
                if (isWalled(bill.getMonsterX(), bill.getMonsterY()))
                {
                    bill.setMonsterX(bill.getMonsterX() - bill.getSpeed());
                }
            }
            else if (xRelation>500)
            {
                bill.setMonsterX(bill.getMonsterX()-bill.getSpeed());                
                if (isWalled(bill.getMonsterX(), bill.getMonsterY()))
                {
                    bill.setMonsterX(bill.getMonsterX() + bill.getSpeed());
                }
            }
        }

        if (bill.getMonsterX() > targetX + bill.getAttackSpace())
        {
            bill.setMonsterX(bill.getMonsterX() - bill.getSpeed());
            if (isWalled(bill.getMonsterX(), bill.getMonsterY()))
            {
                bill.setMonsterX(bill.getMonsterX() + bill.getSpeed());
            }
        }
        else if (bill.getMonsterX() < targetX - bill.getAttackSpace())
        {
            bill.setMonsterX(bill.getMonsterX() + bill.getSpeed());
            if (isWalled(bill.getMonsterX(), bill.getMonsterY()))
            {
                bill.setMonsterX(bill.getMonsterX() - bill.getSpeed());
            }
        }       

        if (bill.getMonsterY() > targetY + bill.getAttackSpace())
        {
            bill.setMonsterY(bill.getMonsterY() - bill.getSpeed());
            if (isWalled(bill.getMonsterX(), bill.getMonsterY()))
            {
                bill.setMonsterY(bill.getMonsterY() + bill.getSpeed());
            }
        }
        else if (bill.getMonsterY() < targetY - bill.getAttackSpace())
        {
            bill.setMonsterY(bill.getMonsterY() + bill.getSpeed());
            if (isWalled(bill.getMonsterX(), bill.getMonsterY()))
            {
                bill.setMonsterY(bill.getMonsterY() - bill.getSpeed());
            }
        }
    }
    public static boolean isWalled(int x, int y)
    {
        boolean isWall = false;
        for (int k = 0; k < walls.size(); k++)
        {
            Wall wall = walls.get(k);
            
            //System.out.println(wall.getXCoord()+" <-- wall monster--> "+x);
            if ((Math.abs(wall.getXCoord() - x) < 50) && (Math.abs(wall.getYCoord() - y) < 50))
            {
                isWall = true;
            }
        }
        return isWall;
    }

    public static int checkAgro(int x, int y, int a, int agro, int chase)
    {
        Player player = null;
        for (int i = 0; i < BrightDeathServer.players.size(); i++)
        {
            player = (Player) BrightDeathServer.players.get(i);
            if (a == -1)
            {
                if (Math.abs(x - player.getXpos()) <= agro && Math.abs(y - player.getYpos()) <= agro)
                {
                    a = i;
                    break;
                }
            } else
            {
                if (Math.abs(x - player.getXpos()) > chase || Math.abs(y - player.getYpos()) > chase)//(Math.abs(y-((int) (gV.height*.5)-50)+attackRadius+gV.yPos-(.5*agro))>=(chase*.5)||Math.abs(x-((int) (gV.width*.5)-50)+attackRadius+gV.xPos-(.5*agro))>=(chase*.5))
                {

                    a = -1;

                }

            }
            //sSystem.out.println("(" + player.getXpos() + ", " + player.getYpos() + ")");
        }

        return a;
    }
    //combat\\

    
    //monster attack.  broken.
    private static int ctr = 50;
    public static void hitPlayer(Monster bill, Player player)
    {
        
        if (ctr >= 50)
        {
            player.getHit(bill.damage);
            ctr = 0;
        }
        ctr++;
    }

    //this allows players to attack 
    public static void checkHit(int attackType, int x, int y, boolean[] direction)
    {
        if (attackType == 1)
        {
            x += 25;
            y += 25;
            if (direction[0])
            {
                y -= 50;
            } else if (direction[2])
            {
                y += 50;
            }
            if (direction[1])
            {
                x += 50;
            } else if (direction[3])
            {
                x -= 50;
            }
            for (Monster bill : monsters)
            {
                if (bill != null && bill.isAlive())
                if (Math.abs(bill.getMonsterX() - x) <= 25)
                {
                    if (Math.abs(bill.getMonsterY() - y) <= 25)
                    {
                        if (bill.isAlive())
                        {
                            bill.getHit(15);
                            break;
                        }
                    }
                }
            }
        }
    }
    
    public static void makeBoxes()
    {
        
    }

    /*public static List<Monster> checkBox(int bx, int by)
    {
        List<Monster> out = new ArrayList();
        int mx, my;
        for (Monster bill : monsters)
        {
            if (bill != null && bill.isAlive())
            {
                mx = bill.monsterX;
                my = bill.monsterY;                
                //     top x             bottom x         top y               top y
                if (Math.abs(bx-mx)<=50 && Math.abs(by-my)<=50)
                {
                    out.add(bill);
                }
            }
        }
        //System.out.println("number in out (AIC line 279) " + out.size());
        return out;
    }

    public static void attack(int t, int listSpot)
    {
        Player thePlayer = BrightDeathServer.players.get(listSpot);
        int[] xy = getXY(listSpot);
        int x = xy[0];
        int y = xy[1];
        List<Monster> hit = new ArrayList();
        List<Monster> temp;
        int sizeH;
        int sizeT;
        if (t == 0)
        { //sword stab
            hit = checkBox(x, y);
        }
        for (Monster bill : hit)
        {
            //bill.getHit(thePlayer.getDamage());
            if (bill.getHit(thePlayer.getDamage()))
            {
                //BrightDeathServer.gui.addCmdTxt("adding xp");                
                thePlayer.addXP(bill.getXP());
                
            }
        }
    }

    public static int[] getXY(int listSpot)
    { 
        Player thePlayer = BrightDeathServer.players.get(listSpot);
        int xPos = thePlayer.getXpos();
        int yPos = thePlayer.getYpos();
        boolean up = thePlayer.getAttack(0);
        boolean right = thePlayer.getAttack(1);
        boolean down = thePlayer.getAttack(2);
        boolean left = thePlayer.getAttack(3);
        if (thePlayer.getAttackType() == 0)
        {
        
            if (down)
            {
                yPos += 50;
            }
            else if (up)
            {
                yPos -= 50;
            }
            
            if (right)
            {
                xPos += 50;
            }
            else if (left)
            {
                xPos -= 50;
            }

        }
        int[] out = {xPos, yPos};
        return out;
    }*/
   private void intializeURLs()
   {
       attackURLs.add(BrightDeathServer.class.getResource("/Attacks/" + "FireBlast.txt").toString());
   }
   private void intializeURLsD()
   {
       attackURLsD.add(BrightDeathServer.class.getResource("/Attacks/" + "FireBlastD.txt").toString());
   }
}