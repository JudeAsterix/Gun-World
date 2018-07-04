/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gunworld;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author kudi
 */
public class GunWorldWall extends GunWorldEntity{
    public GunWorldWall(int x, int y, int width, int height)
    {
        super(x, y, width, height, EntityID.Wall);
    }
    
    public void paint(Graphics g)
    {
        g.setColor(Color.CYAN);
        g.fillRect(this.x, this.y, this.width, this.height);
    }
    
    public boolean blockDetect(GunWorldPlayer gwp)
    {
        return (gwp.x < x + width && gwp.x + gwp.width > x && y < gwp.y + gwp.height && y + height > gwp.y);
    }
    
    public void update(boolean[] keys, ArrayList<GunWorldEntity> ents)
    {
        
    }
}
