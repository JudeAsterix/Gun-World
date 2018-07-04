/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gunworld;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GunWorldCanvas extends Canvas implements Runnable, KeyListener
{
    GunWorldObjects gwo = new GunWorldObjects();
    Image image;
    Graphics bg;
    public GunWorldCanvas()
    {
        t.start();
        addKeyListener(this);
    }
    
    public void paint(Graphics g)
    {
        if(image == null)
        {
            image = createImage(800, 600);
            bg = image.getGraphics();
        }
        
        bg.setColor(Color.white);
        bg.fillRect(0, 0, 800, 600);
        gwo.paint(bg);
        g.drawImage(image, 0, 0, this);
    }
    
    Thread t = new Thread(this);
    
    public void start()
    {
        if(t == null)
        {
            t = new Thread(this);
            t.start();
        }
    }
    
    public void update(Graphics g)
    {
        paint(g);
    }
    
    public void stop()
    {
        if(t != null)
        {
            t.stop();
            t = null;
        }
    }

    public void run() 
    {
        while(true)
        {
            gwo.updateEnts();
            repaint();
            try 
            {
                Thread.sleep(33);
            } catch (InterruptedException ex) {}
        }
    }
    
    public void keyPressed(KeyEvent e) 
    {
        if(e.getKeyCode() == KeyEvent.VK_A)
        {
            gwo.updateKeys(0, true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_D)
        {
            gwo.updateKeys(2, true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_W)
        {
            gwo.updateKeys(3, true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_S)
        {
            gwo.updateKeys(1, true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_V)
        {
            gwo.updateKeys(4, true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_C)
        {
            gwo.updateKeys(5, true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_J)
        {
            gwo.updateKeys(6, true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_K)
        {
            gwo.updateKeys(7, true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_L)
        {
            gwo.updateKeys(8, true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_I)
        {
            gwo.updateKeys(9, true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_B)
        {
            gwo.updateKeys(10, true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_N)
        {
            gwo.updateKeys(11, true);
        }
    }

    public void keyReleased(KeyEvent e) 
    {
        if(e.getKeyCode() == KeyEvent.VK_A)
        {
            gwo.updateKeys(0, false);
        }
        else if(e.getKeyCode() == KeyEvent.VK_D)
        {
            gwo.updateKeys(2, false);
        }
        else if(e.getKeyCode() == KeyEvent.VK_W)
        {
            gwo.updateKeys(3, false);
        }
        else if(e.getKeyCode() == KeyEvent.VK_S)
        {
            gwo.updateKeys(1, false);
        }
        else if(e.getKeyCode() == KeyEvent.VK_V)
        {
            gwo.updateKeys(4, false);
        }
        else if(e.getKeyCode() == KeyEvent.VK_C)
        {
            gwo.updateKeys(5, false);
        }
        else if(e.getKeyCode() == KeyEvent.VK_J)
        {
            gwo.updateKeys(6, false);
        }
        else if(e.getKeyCode() == KeyEvent.VK_K)
        {
            gwo.updateKeys(7, false);
        }
        else if(e.getKeyCode() == KeyEvent.VK_L)
        {
            gwo.updateKeys(8, false);
        }
        else if(e.getKeyCode() == KeyEvent.VK_I)
        {
            gwo.updateKeys(9, false);
        }
        else if(e.getKeyCode() == KeyEvent.VK_B)
        {
            gwo.updateKeys(10, false);
        }
        else if(e.getKeyCode() == KeyEvent.VK_N)
        {
            gwo.updateKeys(11, false);
        }
    }

    public void keyTyped(KeyEvent e) {}
    
}
