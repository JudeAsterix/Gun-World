/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gunworld;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import static javax.swing.Spring.width;

public abstract class GunWorldEntity {
    int x,y;
    int width, height;
    EntityID id;
    BufferedImage i;
    
    public GunWorldEntity(int x, int y, int width, int height, EntityID id)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
    }
    
    public GunWorldEntity()
    {
        this.x = 100;
        this.y = 100;
        this.width = 100;
        this.height = 100;
    }
    
    abstract void paint(Graphics g);
    
    abstract void update(boolean[] keys, ArrayList<GunWorldEntity> ents);
}
