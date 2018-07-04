/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gunworld;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class GunWorldDust extends GunWorldEntity{
    int xVel, yVel;
    int tick = 0;
    
    public GunWorldDust(int x, int y){
        super(x, y, 10, 10, EntityID.Dust);
        initVel();
    }
    
    public void initVel()
    {
        xVel = Math.round((long)(Math.random() * 10)) - 5;
        yVel = -Math.round((long)(Math.random() * 5));
    }
    
    void paint(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillRect(x, y, width, height);
    }

    void update(boolean[] keys, ArrayList<GunWorldEntity> ents) {
        tick++;
        if(tick % 2 == 0)
        {
            this.height--;
            this.width--;
            tick = 0;
        }
        
        this.x += this.xVel;
        this.y += this.yVel;
    }
    
}
