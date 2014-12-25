/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chetan
 */

import java.io.IOException;
import java.util.Random;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Surface {

    int surface_x;
    int surface_y;
    int move_speed;
    int surface_width ;
    int surface_height;
    int cur_surface_y;
    boolean magnet_value;
    public Image surface_image;
    Random ran = new Random();
    
    public Surface()
    {
    load_Image();
    }
 
    private void load_Image()
    {
         try {
               surface_image = Image.createImage("/surface.png");
             }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public Surface(int surface_x, int surface_y, int move_speed , int surface_width , int surface_height ,  boolean magnet_value)
    {
            this.surface_x = surface_x;
            this.surface_y = surface_y;
            this.move_speed = move_speed;
            this.surface_width = surface_width;
            this.surface_height = surface_height;
            this.magnet_value = magnet_value;
            this.surface_image = surface_image;
            
    }

    public void Create_Surface(Graphics g , int canvas_width , int canvas_height , int view_port_y)
    {
        if(magnet_value)
        {
           g.setColor(89,89,89);
        }
        else
        {
          g.setColor(139,37,0);
        }
           cur_surface_y = surface_y + view_port_y;
           g.fillRect(surface_x, cur_surface_y , surface_width , surface_height);
          // g.drawImage(surface_image,surface_x ,cur_surface_y , Graphics.HCENTER|Graphics.VCENTER);
           surface_x = surface_x + move_speed;


           if((surface_x >= (canvas_width-surface_width)) || (surface_x <= (0)))
           {
                move_speed = -move_speed;
           }


    }
    
    public int get_surface_x()
    {
        
        return surface_x;
    }
    public int get_surface_Y()
    {

        return cur_surface_y;
    }
    public int get_surface_width()
    {

        return surface_width;
    }
    public boolean get_magnet_value()
    {

        return magnet_value;
    }

    public int GenerateRandomNumber(int min, int max)  {
                int ra = Math.abs(ran.nextInt());
                if (ra == 0) {
                return (min + max) / 2;
                }
                if (ra < 0) {
                ra = (-ra);
                }
                if (min <= max) {
                return (ra % ((max - min + 1))) + min;
                } else {
                return (ra % ((min - max + 1))) + max;
                }
}


}
