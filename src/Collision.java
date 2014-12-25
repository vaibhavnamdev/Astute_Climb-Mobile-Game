
public class Collision {

    
    boolean is_collision = false;

    public boolean check_collision(int ball_x , int ball_y , int surface_x , int surface_y , int surface_width)
    {
       // check collision for y

        if( ball_y+Constant.ball_height+10 == surface_y )
        {
           // check collision for x

                if( (ball_x+Constant.ball_width >= surface_x) && (ball_x <= surface_x+surface_width) )
                {
                  
                    is_collision = true;
                }
                else
                {
                   is_collision = false;
                }
        }
        else // not collide condition....
        {
            is_collision = false;
        }
       return is_collision;
    }

}
