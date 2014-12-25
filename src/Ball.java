import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;


public class Ball {

   public Image ball_image;
   int xx;
   int ball_x;
   int ball_y = (Constant.screen_height - Constant.distance)-20;
    
    public Ball( )
    {
         load_Image();

    }

    private void load_Image()
    {
         try {
               ball_image = Image.createImage("/ball.png");
             }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void creat_ball(Graphics g , int updated_ball_x , int updated_Ball_y , int surface_width)
    {
        g.setColor(0,205,0);
        ball_x = updated_ball_x;
        xx = ((ball_x + surface_width/2)-20/2)-7;
        ball_y = (ball_y) + updated_Ball_y;
        
        //  g.fillArc(xx, ball_y, 20, 20, 0, 360);
        
        g.drawImage(ball_image,xx ,(ball_y)-26 , 0);
    }

    public int get_ball_x()
    {
         return xx;
    }

    public int get_ball_y()
    {
        return ball_y;
    }

    public void restart_ball()
    {
      ball_y = (Constant.screen_height - Constant.distance)-20;
    }
}
