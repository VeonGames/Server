/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brightdeathserver;

import static brightdeathserver.BrightDeathServer.gui;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author PJ
 */
public class ServerConnector
{

    private Socket connection;
    private int listSpot;
    private InputStream inpt;
    private OutputStream otpt;
    private final boolean connected;
    private ArrayList removedList;
    static boolean dead = false;

    public ServerConnector()
    {
        try
        {
            
            connection = BrightDeathServer.theServer.accept();
            inpt = connection.getInputStream();
            otpt = connection.getOutputStream();
        } catch (IOException e)
        {

        }
        listSpot = BrightDeathServer.players.size();
        removedList = new ArrayList();
        connected = true;
    }

    public void begin()
    {
        try
        {
            
            gui.addCmdTxt("new connection to: " + readInt(inpt) + "." + readInt(inpt) + "." + readInt(inpt) + "." + readInt(inpt));
            int playersRemoved;
            Player thePlayer = new Player(0, 0);
            BrightDeathServer.players.add(thePlayer);
            thePlayer.setArrayPos(listSpot);
            int num = AIcontroller.monsters.size();
            writeInt(num, otpt);
            for (int k = 0; k < num; k++)
            {
                    writeInt(AIcontroller.monsters.get(k).type, otpt);
            }
            num = AIcontroller.walls.size();
            writeInt(num, otpt);
            for (int k = 0; k < num; k++)
            {
                writeInt(AIcontroller.walls.get(k).getXCoord(), otpt);
                writeInt(AIcontroller.walls.get(k).getYCoord(), otpt);
                writeInt(AIcontroller.walls.get(k).getType(), otpt);
            }
            
            boolean canHit = true;
            
            while (connected)
            {
                writeBoolean(dead, otpt);
                //System.out.println(thePlayer.getYpos());
                thePlayer.setXpos(readInt(inpt)+thePlayer.getXpos()-500);
                thePlayer.setYpos(readInt(inpt)+thePlayer.getYpos()-500);
                writeInt(thePlayer.percent(true), otpt);
                writeInt(thePlayer.percent(false), otpt);
                writeBoolean(thePlayer.hasLeveled(), otpt);
                if (thePlayer.hasLeveled())
                {
                    thePlayer.setLeveled(false);
                }
                int AttackType = readInt(inpt);
                int temp = readInt(inpt);
                thePlayer.setAttack(0, readBoolean(inpt));
                thePlayer.setAttack(2, readBoolean(inpt));
                thePlayer.setAttack(3, readBoolean(inpt));
                thePlayer.setAttack(1, readBoolean(inpt));
                thePlayer.attack(temp);
                
//                if (temp==8)
//                {
//                    thePlayer.setAttack(0, false);
//                    thePlayer.setAttack(1, false);
//                    thePlayer.setAttack(2, false);
//                    thePlayer.setAttack(3, false);
//                }
//                else
//                {
//                    if (temp==0)
//                    {
//                        thePlayer.setAttack(0,true);
//                    }
//                    else if (temp==1)
//                    {
//                        thePlayer.setAttack(0,true);
//                        thePlayer.setAttack(1,true);
//                    }
//                    else if (temp==2)
//                    {
//                        thePlayer.setAttack(1, true);
//                    }
//                    else if (temp==3)
//                    {
//                        thePlayer.setAttack(1, true);
//                        thePlayer.setAttack(2, true);
//                    }
//                    else if (temp==4)
//                    {
//                        thePlayer.setAttack(2, true);
//                    }
//                    else if (temp==5)
//                    {
//                        thePlayer.setAttack(2, true);
//                        thePlayer.setAttack(3,true);
//                    }
//                    else if (temp==6)
//                    {
//                        thePlayer.setAttack(3,true);
//                    }
//                    else if (temp==7)
//                    {
//                        thePlayer.setAttack(3,true);
//                        thePlayer.setAttack(0, true);
//                    }
//                    thePlayer.setAttackingNow(true);
//                }
//                

                playersRemoved = removedList.size();
                writeInt(playersRemoved, otpt);
                for (int i = 0; i < playersRemoved; i++)
                {
                    if ((int) removedList.get(i) <= listSpot)
                    {
                        writeInt((int) removedList.get(i), otpt);
                    }
                    else
                    {
                        writeInt((int) removedList.get(i) - 1, otpt);
                    }
                }
                removedList.clear();

                writeInt(BrightDeathServer.players.size() - 1, otpt);
                for (int i = 0; i < BrightDeathServer.players.size(); i++)
                {
                    Player otherPlayer = (Player) BrightDeathServer.players.get(i);
                    if (i != listSpot)
                    {
                        if (Math.abs(thePlayer.getXpos() - otherPlayer.getXpos()) <= 2000 & Math.abs(thePlayer.getYpos() - otherPlayer.getYpos()) <= 2000)
                        {
                            writeBoolean(true, otpt);
                            
                            writeInt(otherPlayer.getXpos(), otpt);
                            writeInt(otherPlayer.getYpos(), otpt);
                        }
                        else
                        {
                            writeBoolean(false, otpt);
                        }
                    }
                }
                
                for (Monster bill : AIcontroller.monsters)
                {                    
                    if (bill.isAlive() && Math.abs(bill.getStartX() - thePlayer.getXpos()) <= 2000 & Math.abs(bill.getStartY() - thePlayer.getYpos()) <= 2000)
                    {
                        writeInt(0, otpt);
                        writeInt(thePlayer.getXpos(),otpt);
                        writeInt(bill.getMonsterX()-thePlayer.getXpos()+2000, otpt);
//                        writeInt(bill.getMonsterX(),otpt);
                        writeInt(bill.getMonsterY()-thePlayer.getYpos()+2000, otpt);
//                        writeInt(bill.getMonsterY(),otpt);
                    }
                    else if (!bill.isAlive() && bill.inHell)
                    {
                        writeInt(1, otpt);
                    }
                    else if (!bill.isAlive())
                    {
                        writeInt(2, otpt);                        
                        bill.inHell = true;
                    }
                    else
                    {
                        writeInt(3, otpt);
                    }
                }
                
                if (!readBoolean(inpt))
                {
                    break;
                }
            }
            BrightDeathServer.removePlayer(listSpot);
            inpt.close();
            otpt.close();
            connection.close();
        } catch (IOException e)
        {
            BrightDeathServer.removePlayer(listSpot);
            try
            {
                inpt.close();
                otpt.close();
                connection.close();
            } catch (IOException err)
            {

            }
        }
    }


    public void moveDown()
    {
        listSpot -= 1;
    }
    
    public void writeBoolean(boolean boolToSend, OutputStream otpt)
    {
        try
        {
            if (boolToSend)
            {
                otpt.write(1);
            } else
            {
                otpt.write(0);
            }
        } catch (IOException e)
        {

        }
    }

    public boolean readBoolean(InputStream inpt)
    {
        try
        {
            if (inpt.read() == 0)
            {
                return false;
            } else
            {
                return true;
            }
        } catch (IOException e)
        {
            return false;
        }
    }

    public int getListSpot()
    {
        return listSpot;
    }

    public void setListSpot(int newSpot)
    {
        listSpot = newSpot;
    }

    public void addPlayerRemoved(int numberOfPlayer)
    {
        removedList.add(numberOfPlayer);
    }

    public void writeInt(int intToSend, OutputStream otpt)
    {
        try
        {
            while (intToSend > 0)
            {
                if (intToSend <= 255)
                {
                    otpt.write(intToSend);
                    intToSend = 0;
                } else
                {
                    otpt.write(255);
                    intToSend -= 255;
                }
            }
            otpt.write(0);
        } catch (IOException e)
        {

        }
    }

    public int readInt(InputStream inpt)
    {
        int intRead = -1;
        int intToUse = 0;
        try
        {
            while (intRead != 0)
            {
                intRead = inpt.read();
                if (intRead == 0)
                {
                    return intToUse;
                } else
                {
                    intToUse += intRead;
                }
            }
        } catch (IOException e)
        {

        }
        return intToUse;
    }
}
