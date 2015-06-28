/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brightdeathserver;

import Attacks.AttackBox;
import static brightdeathserver.BrightDeathServer.gui;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
    private String name;

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
            List<AttackBox> localBoxes = new ArrayList();

            Player thePlayer = new Player(readString(inpt), 0, 0);
            BrightDeathServer.players.add(thePlayer);
            name = thePlayer.getName();
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
                writeBoolean(false, otpt);
                //System.out.println(thePlayer.getYpos());
                thePlayer.setXpos(readInt(inpt) + thePlayer.getXpos() - 500);
                thePlayer.setYpos(readInt(inpt) + thePlayer.getYpos() - 500);
                writeInt(thePlayer.percent(true), otpt);
                writeInt(thePlayer.percent(false), otpt);
                writeBoolean(thePlayer.hasLeveled(), otpt);
                if (thePlayer.hasLeveled())
                {
                    thePlayer.setLeveled(false);
                }
                //attack
                if (readBoolean(inpt))
                {
                    thePlayer.attack(readInt(inpt));
                }

                playersRemoved = removedList.size();
                writeInt(playersRemoved, otpt);
                for (int i = 0; i < playersRemoved; i++)
                {
                    if ((int) removedList.get(i) <= listSpot)
                    {
                        writeInt((int) removedList.get(i), otpt);
                    } else
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
                        } else
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
                        //writeInt(thePlayer.getXpos(), otpt);
                        writeInt(bill.getMonsterX() - thePlayer.getXpos() + 2000, otpt);
//                        writeInt(bill.getMonsterX(),otpt);
                        writeInt(bill.getMonsterY() - thePlayer.getYpos() + 2000, otpt);
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
            
            AttackBox temp;
            for(int i=0;i<AIcontroller.boxes.size();i++)
            {
                temp = AIcontroller.boxes.get(i);
                if(Math.abs(temp.getXPos()-thePlayer.getXpos())<=2000&&Math.abs(temp.getYPos()-thePlayer.getYpos())<=2000)
                {
                    localBoxes.add(temp);
                }
            }
            
            writeInt(localBoxes.size(),otpt);
            for(int i=0;i<localBoxes.size();i++)
            {
                temp = localBoxes.get(i);
                writeInt(temp.getXPos() - thePlayer.getXpos() + 2000,otpt);
                writeInt(temp.getYPos() - thePlayer.getYpos() + 2000,otpt);
                writeInt(temp.getType(),otpt);
            }
            
            gui.addCmdTxt(name + " with player code " + listSpot + " has left the game");
            BrightDeathServer.removePlayer(listSpot);
            inpt.close();
            otpt.close();
            connection.close();
        } catch (IOException e)
        {
            gui.addCmdTxt(name + " with player code " + listSpot + " has left the game");
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
    private int debugger = 0;
    private boolean debug = false;

    public void writeBoolean(boolean boolToSend, OutputStream otpt)
    {
        if (debug)
        {
            System.out.println("Writing Bool: " + debugger);
            debugger++;
        }
        
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

    public boolean readBoolean(InputStream inpt) //change this back to orginal booleans
    {
        if (debug)
        {
            System.out.println("Reading Bool: " + debugger);
            debugger++;
        }

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

    public void writeInt(int intToSend, OutputStream otpt)
    {
        if (debug)
        {
            System.out.println("Writing int: " + debugger);
            debugger++;
        }

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
        if (debug)
        {
            System.out.println("Reading int: " + debugger);
            debugger++;
        }

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

    public void writeString(String s, OutputStream otpt)
    {
        if (debug)
        {
            System.out.println("Writing String: " + debugger);
            debugger++;
        }

        char[] send = s.toCharArray();
        try
        {
            for (int k = 0; k < send.length; k++)
            {
                otpt.write((int) send[k]);
            }
            otpt.write(3);
        } catch (IOException e)
        {

        }
    }

    public String readString(InputStream inpt)
    {
        if (debug)
        {
            System.out.println("Reading String: " + debugger);
            debugger++;
        }

        String s = "";
        int intRead = -1;

        try
        {
            while (intRead != 3)
            {
                intRead = inpt.read();
                if (intRead == 3)
                {
                    return s;
                } else
                {
                    s += (char) intRead;
                }
            }
        } catch (IOException e)
        {
            return s;
        }
        return s;
    }
}
