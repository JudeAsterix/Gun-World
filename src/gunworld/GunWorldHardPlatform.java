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
public class GunWorldHardPlatform extends GunWorldEntity{
    GunWorldPlatform platform;
    GunWorldWall wallLeft;
    GunWorldWall wallRight;
    GunWorldCeiling ceiling;
    
    public GunWorldHardPlatform(int x, int y, int width, int height)
    {
        super(x, y, width, height, EntityID.HardPlatform);
        platform = new GunWorldPlatform(x, y, width, height / 2, 0);
        wallLeft = new GunWorldWall(x, y, width / 2, height);
        wallRight = new GunWorldWall(x + (width / 2), y, width / 2, height);
        ceiling = new GunWorldCeiling(x, y + (height / 2), width, height / 2);
    }
    
    public void paint(Graphics g)
    {
        g.setColor(Color.gray);
        g.fillRect(this.x, this.y, this.width, this.height);
    }
    
    public void update(boolean[] keys, ArrayList<GunWorldEntity> ents)
    {
        
    }
}
