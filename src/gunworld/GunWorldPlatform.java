/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gunworld;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author kudi
 */
public class GunWorldPlatform extends GunWorldEntity{
    PlatformID pid;
    
    public GunWorldPlatform(int x, int y, int width, int height, int pid)
    {
        super(x, y, width, height, EntityID.Platform);
        if(this.width == 50 && this.height == 50)
        {
            if(pid == 1)
            {
                this.pid = PlatformID.smallMetal1;
            }
        }
    }
    
    public void paint(Graphics g)
    {
        if(this.pid == PlatformID.smallMetal1)
        {
            try {
                Image i = ImageIO.read(new File("src/Images/SmallBlock1.png"));
                g.drawImage(i, x, y, width, height, null);
            } catch (IOException ex) {
                Logger.getLogger(GunWorldPlatform.class.getName()).log(Level.SEVERE, null, ex);
                g.setColor(Color.orange);
                g.fillRect(this.x, this.y, this.width, this.height);
            }
        }
        else
        {
            g.setColor(Color.orange);
            g.fillRect(this.x, this.y, this.width, this.height);
        }
    }
    
    public boolean blockDetect(GunWorldPlayer gwp)
    {
        return (gwp.yVel > 0 && gwp.y + gwp.height + gwp.yVel >= this.y && gwp.y + gwp.height  < this.y && this.x < gwp.x + gwp.width && x + width > gwp.x);
    }
    
    public void update(boolean[] keys, ArrayList<GunWorldEntity> ents)
    {
        
    }
}
