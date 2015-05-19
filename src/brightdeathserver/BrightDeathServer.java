 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brightdeathserver;

import Monsters.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PJ
 */
public class BrightDeathServer extends Thread {

    static ServerSocket theServer;
    static ServerGUI gui;
    static List<Player> players = new ArrayList();
    static List<ServerConnector> serverList = new ArrayList();

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        try {
            theServer = new ServerSocket(16030);
        } catch (IOException e) {

        }
        gui = new ServerGUI();
        gui.setVisible(true);
        new AIcontroller().start();
        new BrightDeathServer().start();
        try
        {
            gui.addCmdTxt("Server Open at IP: " + InetAddress.getLocalHost());
        } catch (UnknownHostException ex)
        {}
       
//        new Help().setVisible(true);
        return;
    }

    public static void removePlayer(int numberOfPlayer) {
        serverList.remove(numberOfPlayer);
        players.remove(numberOfPlayer);
        
        Monster bill;
        for (int i = numberOfPlayer; i < serverList.size(); i++)
        {
            serverList.get(i).moveDown();
        }
        for (int i = 0; i < serverList.size(); i++)
        {
            (serverList.get(i)).addPlayerRemoved(numberOfPlayer);
        }

        for (int i = 0; i < AIcontroller.monsters.size(); i++)
        {
            bill = (Monster) AIcontroller.monsters.get(i);
            if (bill.isAgro() != -1)
            {
                if (bill.isAgro() == numberOfPlayer)
                {
                    bill.setAgro(-1);
                }
                else if (bill.isAgro() > numberOfPlayer)
                {
                    bill.setAgro(bill.isAgro() - 1);
                }
            }
        }
    }
    
    public static final void addMonsters()
    {
        for (int k = 0; k < 20; k++)
        {
            //AIcontroller.monsters.add(new Goblin((int) (Math.random() * 1400) + 50, (int) (Math.random() * 1400) + 50));
        }
        //AIcontroller.monsters.add(new WhiteWolf(50,400));
        //AIcontroller.monsters.add(new WhiteWolf(300,0));
        //AIcontroller.monsters.add(new WhiteWolf(150,200));
        //AIcontroller.monsters.add(new Goblin(150,200));
        AIcontroller.monsters.add(new Goblin(00,00));
    }
    public void run() {
        ServerConnector adam = new ServerConnector();
        serverList.add(adam);
        new BrightDeathServer().start();
        adam.begin();
        
    }
}
