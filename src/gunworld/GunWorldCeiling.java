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
public class GunWorldCeiling extends GunWorldEntity{
    
    public GunWorldCeiling(int x, int y, int width, int height)
    {
        super(x, y, width, height, EntityID.Ceiling);
    }
    
    public void paint(Graphics g)
    {
        g.setColor(Color.YELLOW);
        g.fillRect(this.x, this.y, this.width, this.height);
    }
    
    public boolean blockDetect(GunWorldPlayer gwp)
    {
        return (gwp.yVel < 0 && gwp.y + gwp.yVel < this.y + this.height && gwp.y >= this.y + this.height && this.x < gwp.x + gwp.width && x + width > gwp.x);
    }
    
    public void update(boolean[] keys, ArrayList<GunWorldEntity> ents)
    {
        
    }
}
