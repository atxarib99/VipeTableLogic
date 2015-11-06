   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vipetablelogic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Arib
 */
//used to create the grid on a panel
public class GridPanel extends JPanel {
    private int[] sectors = new int[600]; //spot of the grid
    private Color[] colors = new Color[600];
    //add values to sectors
    public GridPanel() {
        repaint();
    }
    //get the color of the file based on its id
    private Color getColor(int id) {
        Color color;
        switch (id) {
            case 1 : color = Color.red; break;
            case 2 : color = Color.green; break;
            case 3 : color = Color.blue; break;
            case 4 : color = Color.yellow; break;
            case 5 : color = Color.magenta; break;
            case 6 : color = Color.orange; break;
            default: color = Color.white;
        }
        return color;
    }
    //updates the grid
    public void updateGrid(int[] sects) {
        this.sectors = sects;
        repaint();
    }
    //sets the sectors to parameter
    public void setSectors(int[] sectors)
    {
        this.sectors = sectors;
        repaint();
    }
    //repaints the files after updating colors
    public void setColors(Color[] cols) {
        colors = cols;
        repaint();
    }
    //paints the panel as needed
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //calls super method
        Graphics2D graphics = (Graphics2D) g; //creates graphic component to use
        graphics.setColor(Color.white); //set the background color
        int index = 0; //index of the sector
        String out; //outpit string for debugging
        for (int j = 0; j < 20; j++) {
            for (int i = 0; i < 30; i++) { //nested for loop to go through the entire grid
                
                graphics.setColor(getColor(sectors[(j * 30) + i])); //sets the color
                graphics.fillRect(i * 20, j * 20, 20, 20); //fills the file
                graphics.setColor(Color.black); //sets the color
                if (sectors[index] == 0) {
                    out = ""; 
                }
                else {
                    out = "" + sectors[index];
                }
                graphics.drawString(out, i * 20 + 7, j * 20 + 15); //draws the string onto the places
                index++;
            }

        }
        

        //sets the color of the lines
        graphics.setColor(Color.black);
        
        for (int i = 0; i < 30; i++) {
            
            graphics.drawLine(i * 20, 0, i * 20, 400); //draws the line
        }
        
        for (int i = 0; i < 20; i++) {
            graphics.drawLine(0, i * 20, 600, i * 20); //draws the line
        }

    }
    
}
