/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gunworld;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author kudi
 */
public class GunWorld {

    public static void createAndMakeGUI()
    {
        JFrame frame = new JFrame("Gun World!");
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GunWorldCanvas gwc = new GunWorldCanvas();
        frame.add(gwc);
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        createAndMakeGUI();
    }
    
}
