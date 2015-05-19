/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Attacks;

import brightdeathserver.AIcontroller;
import Attacks.AttackBox;
import brightdeathserver.Monster;
import brightdeathserver.Player;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Nicno90
 */
public class Attack
{
    private File side;
    private File dia;
    private char[][] map = new char[9][9];
    private char[][] mapD;
    //working on
    public Attack(File side, File dia)
    {
        this.side = side;
        toCharAA(side, map);
        this.dia = dia;
        toCharAA(dia, mapD);
        
    }

    public Attack(File side)
    {
        this.side = side;
        toCharAA(side, map);
    }
    
    private void toCharAA(File txt, char[][] map)
    {
        //map = new char[y][x] because cant change [x][y] would npt work trust me i thought about it.
        //if [x][y] the lines would come out sideways 
        BufferedReader in = null;
        
        int lines = 0;
        try {
            in = new BufferedReader(new FileReader(txt));
            
            String s = in.readLine();
            
            while (s != null)
            {
                
                s = in.readLine();
                map[lines] = s.toCharArray();
                lines++;
                
            }
            
        } catch (Exception e) {
            
        }
        finally{
            if (in != null)
                try {
                    in.close();
            } catch (Exception e) {
                
            }
        }
        return;
    }
    
    public List<AttackBox> strike(int dir, Player player)
    {
        //0 up
        //1 right up
        //2 right
        //3 right down
        //4 down
        //5 left down 
        //6 left
        //7 left up
        //y,x
        if (dia == null && !(dir == 0 || dir == 1 || dir == 2 || dir == 3))
        {
            return null;
        }
        
        List<AttackBox> box = new ArrayList();
        int[] indexP = {4, 4};
        int px = player.getXpos();
        int py = player.getYpos();
        int cy;
        int cx;
        List<Monster> boxes;
        if (dir == 1)
        {
            for (int y = 0; y < 9; y ++)
            {
                for (int x = 0; x < 9; x++)
                {         //y  x
                    cy = y;
                    cx = x;
                    if (map[cy][cx] == 'O') // flip
                    {
                        
                    }
                    else if (map[cy][cx] == 'X')
                    {
                        box.add(new AttackBox(px + ((cx - 4) * 50), py - ((cy - 4) * 50), player.getDamage(), player.getTeam(), dir, 4, 0));
                    }
                    else if (map[cy][cx] == '>')
                    {
                        box.add(new AttackBox(px + ((cx - 4) * 50), py - ((cy - 4) * 50), player.getDamage(), player.getTeam(), dir, next(cx, cy, dir), 3));
                    }
                }
            }
        }
//        else if (dir == 0)
//        {
//            for (int y = 0; y < 9; y ++)
//            {
//                for (int x = 0; x < 9; x++)
//                {         //y  x
//                    if (map[y][x] == 'X') // flip
//                    {
//                       
//                        box.add(new AttackBox(px + ((y - 4) * 50), py - ((x - 4) * 50), player.getDamage(), true, player.getTeam()));
//                    }
//                }
//            }
//        }
//        else if (dir == 2)
//        {
//            for (int y = 0; y < 9; y ++)
//            {
//                for (int x = 0; x < 9; x++)
//                {         //y  x
//                    if (map[y][x] == 'X') // flip
//                    {
//                        box.add(new AttackBox(px + ((y - 4) * 50), py + (x * 50), player.getDamage(), true, player.getTeam()));
//                    }
//                }
//            }
//        }
//        else if (dir == 3)
//        {
//            for (int y = 0; y < 9; y ++)
//            {
//                for (int x = 0; x < 9; x++)
//                {         //y  x
//                    if (map[y][x] == 'X') // flip
//                    {
//                        box.add(new AttackBox(px - ((x - 4) * 50), py + (y * 50), player.getDamage(), true, player.getTeam()));
//                    }
//                }
//            }
//        }
////        for (int k = 0; k < 9; k ++)
//        
////        {
////            for (int i = 0; i < 9; i++)
////            {         //y  x
////                if (map[k][i] == 'X')
////                {
////                    boxes = checkBox((i - 4), (k + 4));
////                    for (Monster bill : boxes)
////                    {
////                        bills.add(bill);
////                    }
////                }
////            }
////        }
        return box;
    }
    
    //
    private int next(int x, int y, int dir)
    {
        //0 up
        //1 right up
        //2 right
        //3 right down
        //4 down
        //5 left down 
        //6 left
        //7 left up
        int xc;
        int yc;
        
        if (dir == 0)
        {
            yc = -1;
            xc = 0;
        }
        else if (dir == 1)
        {
            yc = -1;
            xc = 1;
        }
        else if (dir == 2)
        {
            yc = 0;
            xc = 1;
        }
        else if (dir == 3)
        {
            yc = 1;
            xc = 1;
        }
        else if (dir == 4)
        {
            yc = -1;
            xc = 0;
        }
        else if (dir == 5)
        {
            yc = -1;
            xc = -1;
        }
        else if (dir == 6)
        {
            yc = 0;
            xc = -1;
        }
        else if (dir == 7)
        {
            yc = -1;
            xc = -1;
        }
        else
        {
            return -1;
        }
        
        char c = 'O';
        int ctr = 0;
        if (dir == 0 || dir % 2 == 0)
        {
            while (c == 'O')
            {
                c = this.map[y + yc][x + xc];
                y += yc;
                x += xc;
                ctr++;
            }
        }
        else
        {
             while (c == 'O')
            {
                c = this.mapD[y + yc][x + xc];
                y += yc;
                x += xc;
                ctr++;
            }
        }
        return ctr;
    }
    
    private int[] find(char c, char[][] map)
    {
        int[] index = new int[2];
        for (int k = 0; k < 9; k ++)
        {
            for (int i = 0; i < 9; i++)
            {
                if (map[k][i] == c)
                {
                    index[0] = k;
                    index[1] = i;
                    return index;
                }
            }
        }
        return null;
    }
    
    public static List<Monster> checkBox(int bx, int by)
    {
        List<Monster> out = new ArrayList();
        int mx, my;
        for (Monster bill : AIcontroller.monsters)
        {
            if (bill != null && bill.isAlive())
            {
                mx = bill.getMonsterX();
                my = bill.getMonsterY();
                
                //     top x             bottom x         top y               top y
                if ((bx - 50 <= mx && mx <= bx + 50) && (by - 50 <= my && my <= by + 50))
                {
                    out.add(bill);
                }
            }
        }
        //System.out.println("number in out (AIC line 279) " + out.size());
        return out;
    }
    
}
