/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.DataInput;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;
import java.util.Vector;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;



import javax.microedition.lcdui.*;
//import javax.microedition.m3g.*;
import javax.microedition.io.Connector;
//import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.Sprite;




import javax.microedition.midlet.MIDlet;


/**
 *
 * @author Mamta Bhamre
 */
public class AstuteCanvas extends Canvas implements Runnable  {

    public Graphics g;
 
    public Sound sound;
    public Random random;
    public Vector vector_surface;
    public Ball ball;
    public Collision collision;
   
    MIDlet midlet;

    boolean first_time = true;
//    int x_pos = 0;
    int y_pos = Constant.screen_height;
    int score = 0;
//    int speed = 0;
    int update_y;
    int min_speed = Constant.minimum_speed;
    int max_speed = Constant.maximum_speed;
    int min_plat_width = 0;
    int max_plat_width = 0;
    int ball_x_value = 0;
    int ball_y_value = 0;
    int surface_width = 0;
    int collision_x_value = 0;
    private Image menuBackGround;
    int view_port_y = 0;
  
    int gap = Constant.screen_height/4;
    boolean fire_pressed = false;
    boolean up_move = true;
    boolean down_move = false;
    boolean update_ball_x = true;
    boolean check_collision = false;
    boolean fall=false;

    Image logo,splash,icon;
    GameGraphics gt;
    GameGraphics gtfont;
    GameGraphics gtfontfocus;
    GameGraphics gtsmallfont;
    GameGraphics lskrskfont;
    GameGraphics textfont;

    int SCREEN_WIDTH;
    int SCREEN_HEIGHT;
    int prev_speed = 0;
    boolean theme[] = {true, false, false};
    static final int theme_id[] = {(Constant.MODULE_ID_THEME1), (Constant.MODULE_ID_THEME2), (Constant.MODULE_ID_THEME3)};

    static final int MENUSTRIP1_THEME[] = {(Constant.FRAME_ID_MENUSTRIP1_THEME1), (Constant.FRAME_ID_MENUSTRIP1_THEME2), (Constant.FRAME_ID_MENUSTRIP1_THEME3)};
    static final int MENUSTRIP2_THEME[] = {(Constant.FRAME_ID_MENUSTRIP2_THEME1), (Constant.FRAME_ID_MENUSTRIP1_THEME2), (Constant.FRAME_ID_MENUSTRIP1_THEME3)};
    static final int MENUSTRIP_FOCUS1[] = {(Constant.FRAME_ID_MENUSTRIP_FOCUS1), (Constant.FRAME_ID_MENUSTRIP_FOCUS1_THEME2), (Constant.FRAME_ID_MENUSTRIP_FOCUS1_THEME3)};
    static final int MENUSTRIP_FOCUS2[] = {(Constant.FRAME_ID_MENUSTRIP_FOCUS2), (Constant.FRAME_ID_MENUSTRIP_FOCUS1_THEME2), (Constant.FRAME_ID_MENUSTRIP_FOCUS1_THEME3)};
    static final int CANCLE_BUTTON[] = {(Constant.MODULE_ID_CANCELBUTTON_BLUE), (Constant.MODULE_ID_CANCELBUTTON_RED), (Constant.MODULE_ID_CANCELBUTTON_GREEN)};
    static final int BUTTON[] = {(Constant.MODULE_ID_BUTTON_BLUE), (Constant.MODULE_ID_BUTTON_RED), (Constant.MODULE_ID_BUTTON_GREEN)};
    static final int BACK_BUTTON[] = {(Constant.MODULE_ID_BACKBUTTON), (Constant.MODULE_ID_BACKBUTTON_RED), (Constant.MODULE_ID_BACKBUTTON_GREEN)};
    static final int NEXT_BUTTON[] = {(Constant.MODULE_ID_NEXTBUTTON), (Constant.MODULE_ID_NEXTBUTTON_RED), (Constant.MODULE_ID_NEXTBUTTON_GREEN)};
    static final int OK_BUTTON[] = {(Constant.MODULE_ID_OKBUTTON_BLUE), (Constant.MODULE_ID_OKBUTTON_RED), (Constant.MODULE_ID_OKBUTTON_GREEN)};
    static final int REPLAY_BUTTON[] = {(Constant.MODULE_ID_REPLAYBUTTON_BLUE), (Constant.MODULE_ID_REPLAYBUTTON_RED), (Constant.MODULE_ID_REPLAYBUTTON_GREEN)};
    static final int PAUSE_BUTTON[] = {(Constant.MODULE_ID_PAUSE_BLUE), (Constant.MODULE_ID_PAUSE_RED), (Constant.MODULE_ID_PAUSE_GREEN)};

    boolean power_up = true;
    boolean is_sound = true;
    String sound_status_value[] = {"ON","OFF"};
    int on = 0;
    int off = 1;
    int sound_value = off;
    String name[][] = {
                        {"PLAY", "SETTING","HIGH SCORE", "INSTRUCTIONS", "ABOUT"},
                        {"CHANGE THEME","SOUND "+sound_status_value[sound_value]},
                        {"RESUME","HOME"}
                      };
    String instruction[]={"1. Tap on Screen to Jump Ball","2. Objective of the Game is to Reach High","3. Grey Color Plates automatic Catch the Ball on Ball Jump","4. Each successful jump increses score by 10","5. To Change theme go to Settings","6. To ON/OFF Sounds Go to Settings","7. For High Score Go HighScores in Main Menu"};
   int show_down = 0;
    int final_out = 1;
    int add_pos=0;
    int pos = 0;
    int name_index = 0;
    final int blue = 0;
    final int red = 1;
    final int green = 2;
    int theme_type = blue;

    static Sound sound_ball_jump;
    static Sound sound_ball_set;
    static Sound sound_ball_miss;
    //________________________________________________________________________________________________________________



    public AstuteCanvas(MIDlet m) {

         setFullScreenMode(true);
         midlet = m;


         SCREEN_WIDTH = Constant.screen_width;
         SCREEN_HEIGHT = Constant.screen_height;

        gt = new GameGraphics();
        gtfont = new GameGraphics();
        gtfontfocus = new GameGraphics();
        gtsmallfont = new GameGraphics();
        textfont = new GameGraphics();

         random = new Random();
         vector_surface = new Vector();
         ball = new Ball();
         collision = new Collision();

         load_Image();


         gt.Load(GameGraphics.getFileByteData("/newgtgt.GT"), true);

         gtsmallfont.LoadFont(GameGraphics.getFileByteData("/newdigi.GT"), "0123546789:", true);
         gtfontfocus.LoadFont(GameGraphics.getFileByteData("/font1-9.GT"), "0123456789", true);
         gtfont.LoadFont(GameGraphics.getFileByteData("/fontstrip.GT"), "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz![],.-'|/=%#$~+<>?:;(){}@^0123456789*&", 5, 12, true);

         sound_ball_jump    =  new Sound(Constant.ball_jump     ,  Constant.wav);
         sound_ball_set    =  new Sound(Constant.ball_set     ,  Constant.wav);
         sound_ball_miss    =  new Sound(Constant.ball_miss     ,  Constant.wav);


    }

    public void initiate_variables()
    {
                  fire_pressed = false;
                  up_move = true;
                  down_move = false;
                  view_port_y = 0;
                  gap = Constant.screen_height/4;
                  update_ball_x = true;
                  score = 0;
                  fall = false;
                  check_collision = false;
                  first_time = true;
                  ball_x_value = 0;
                  ball_y_value = 0;
                  surface_width = 0;
                  y_pos = Constant.screen_height;

    }
  
   private void load_Image()
    {

        try {

            logo   = Image.createImage("/logo.png");
            icon = Image.createImage("/icon.png");
            splash = Image.createImage("/splash.png");

        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public void paint(Graphics g) {

//     g.setColor(0,0,0);
//     g.fillRect(0, 0 , Constant.screen_width, Constant.screen_height);
//     draw_score(g);
//     draw_back_screen(g, 0);
//     drawPopup(g, 0);
////     drawSmallBG(g , 0);
//     draw_theme_Select(g);

     switch(Constant.current_state)
     {
         case Constant.LOGO_STATE:
                  {
         g.drawImage(logo, 0, 0, 0);
         }
         break;

         case Constant.SPLASH_STATE:
                  {
         g.drawImage(splash, 0, 0, 0);
         }
         break;

         case Constant.MENU_STATE:
         {
            draw_back_screen(g, theme_type);
            if(name_index == 2)
            {
               drawPopup(g, theme_type);
            }
            draw_mainmenu_option(g, name_index);
            
         }
         break;
         case Constant.THEME_STATE:
         {
             draw_back_screen(g, theme_type);
             drawPopup(g, theme_type);
//             drawSmallBG(g , theme_type);
             draw_theme_Select(g);
             draw_select_button(g);
         }
         break;


         case Constant.PLAY_STATE:
         {
              draw_back_screen(g, theme_type);
                  update();
                  
                  draw_surface(g , view_port_y);
                  g.drawString(("Score : "+score), (Constant.screen_width/2)-30 , (Constant.screen_height)-30 ,0);
                  draw_ball(g);
                  draw_replay_button(g);
                  draw_pause_button(g);
                  
//                   System.out.println("Score = "+(score+10));
         }
         break;
         case Constant.GAMEOVER_STATE:
         {
                draw_back_screen(g, theme_type);
                drawPopup(g, theme_type);
                draw_replay_button(g);

                for (int u = 0; u < 3; u++) {
            if (theme[u]) {

              gt.DrawModule(g, BUTTON[u], 0, (getHeight() - (Constant.MODULE_ID_BUTTON_FOCUS_HEIGHT)), 0);
              gt.DrawModule(g, BACK_BUTTON[u], (Constant.MODULE_ID_BUTTON_FOCUS_WIDTH / 2) - (Constant.MODULE_ID_OKBUTTON_WIDTHHEIGHT / 2), getHeight() - ((Constant.MODULE_ID_BUTTON_FOCUS_HEIGHT / 2)) - ((Constant.MODULE_ID_OKBUTTON_WIDTHHEIGHT / 2)), 0);

            }
        }

                gtfont.drawString(g, "GAME - OVER", (Constant.screen_width/2)-(gtfont.getStringWidth("GAME - OVER"))/2, (Constant.screen_height/2)-100 , 0);
                draw_score(g);
                GameUserData.setHighestScore(score);
         }
         break;

         case Constant.HIGHSCORE_STATE:
         {
             draw_back_screen(g, theme_type);
             draw_high_score(g);
             draw_back_button(g);
         }
         break;
         
         case Constant.INSTRUCTION_STATE:
         {
             draw_back_screen(g, theme_type);
             draw_instruction(g);
             draw_back_button(g);
         
         }
         break;
         
         case Constant.ABOUT_STATE:
         {
             draw_back_screen(g, theme_type);
             draw_about(g);
             draw_back_button(g);
         }
         break;
            
     }
       

    }

    public void update()
    {
            if(fire_pressed)
            {
                   
                    if(up_move)
                   {
                       view_port_y = view_port_y + 5;

                     if(view_port_y >= gap+30)
                     {
            
//                        view_port_y = 0;
                        up_move = false;
                        down_move = true;
                     }
                   }
                   if(down_move)
                   {
                       view_port_y = view_port_y - 10;

                     if(view_port_y <= gap)
                     {

                        up_move = true;
                        down_move = false;
                        fire_pressed = false;
                        gap = Constant.distance + view_port_y;

                        update_y = Constant.screen_height/Constant.plat_gap;
                        vector_surface.removeElementAt(0);
                        new_creat();


                        Surface ss = (Surface)vector_surface.elementAt(0);
                        boolean is_collide = collision.check_collision(ball.get_ball_x(), ball.get_ball_y(), ss.get_surface_x() , ss.get_surface_Y() , ss.get_surface_width());

                        if(ss.get_magnet_value()) // msgnet active for this plat...
                        {
                          is_collide = true;
                        }
                        if(is_collide)
                        {
                            update_ball_x = true;
                            score=score+10;
                            System.out.println("Score = "+score);
                            if(is_sound)
                            {
                            sound_ball_set.sound_0N(0, 1);
                            }
                        }
                        else
                        {
                             update_ball_x = false;
                            fall= true;

                        }

                     }
                   }

            }
    }


    public void draw_surface(Graphics graphics , int y_change)
    {
        if(first_time)
        {
            first_time = false;
            for(int i = 0 ; i < Constant.total_loaded_plat ; i++)
            {
                 if(i == (Constant.total_loaded_plat))
                {
                     update_y = 0;
                }
                else
                {
                     update_y = Constant.screen_height/Constant.plat_gap;
                }
                 new_creat();
            }
        }

        for(int i = 0 ; i < vector_surface.size() ; i++)
        {
            Surface s  =   (Surface)vector_surface.elementAt(i);

            s.Create_Surface(graphics, Constant.screen_width, Constant.screen_height , y_change);
           
        }

    }

    public void draw_ball(Graphics graphics)
    {
       if(update_ball_x)
       {
         Surface ss = (Surface)vector_surface.elementAt(0);
         ball_x_value = ss.get_surface_x();
         surface_width = ss.get_surface_width();

       }
       if(fall)
       {
          ball_y_value = ball_y_value + 1;
          if(ball_y_value >= 8*Constant.plat_gap)
          {
              if(is_sound)
             {
              sound_ball_miss.sound_0N(0, 1);
              }
             Constant.current_state = Constant.GAMEOVER_STATE;
          }
       }


         ball.creat_ball(graphics, ball_x_value , ball_y_value, surface_width);
    }

    public void new_creat()
    {

        int x_pos = GenerateRandomNumber(0, (Constant.screen_width-Constant.plat_width));
         y_pos = y_pos - (update_y);

         if(score < 100)
         {min_speed = -2;       max_speed =  2; }
         else
         {
            if(score < 200){ min_speed = -4; max_speed = 4; }
            else
            {
               if(score < 500){min_speed = -6; max_speed = 6;}
               else{ min_speed = -8; max_speed = 8; }
            }
         }
         int speed = GenerateRandomNumber(min_speed, max_speed);
         if(speed == prev_speed)
         {
             if(speed == 0)
             {
                 speed+=2;
             }
         }
         prev_speed = speed;
         if(score < 100){ min_plat_width = 70; max_plat_width = 90;}
         else
         {
            if(score < 200){ min_plat_width = 60; max_plat_width = 80;}
            else
            {
               if(score < 300){ min_plat_width = 40; max_plat_width = 70;}
               else
               {
                  if(score < 500){ min_plat_width = 30; max_plat_width = 60;}
               }
            }
         }
         int surface_width = GenerateRandomNumber(min_plat_width, max_plat_width);
         boolean magnet_on = false;
         int rrr = GenerateRandomNumber(0, 100);
         if(rrr > 80 && rrr < 100)
         {
            magnet_on = true;
         }

         Surface sur = new Surface(x_pos, y_pos, speed, surface_width, Constant.plat_height , magnet_on);
   
         vector_surface.addElement(sur);
         
    }

     public int GenerateRandomNumber(int min, int max)  {
                int ra = Math.abs(random.nextInt());
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

     public void draw_restart(Graphics g)
    {
         g.setColor(229,229,229);
         g.fillRect(Constant.screen_width-30, Constant.screen_height-30, 30, 30);
     }

//________________________________________________________________________________________________________________________________

     private void draw_score(Graphics g)
    {
         g.setColor(255,255,255);
         gtfont.drawString(g, ("YOUR SCORE = "), (Constant.screen_width/2)-((gtfont.getStringWidth("YOUR SCORE = "+(score)))/2) , (Constant.screen_height/2)-50 ,0);
         g.drawString((""+score), (270) , (Constant.screen_height/2)-50 ,0);

     }

     public void restart_game()
    {
         vector_surface.removeAllElements();
         ball.restart_ball();
         initiate_variables();
                  
     }

     public void draw_back_screen(Graphics g, int type) {
        switch (type) {
            case (blue): {
                gt.DrawFrame(g, Constant.BG1_MODULE_ID, 0, 0, 0);
                gt.DrawFrame(g, Constant.BG1_MODULE_ID, SCREEN_WIDTH, 0, 1);
                gt.DrawFrame(g, Constant.BG1_MODULE_ID, 0, SCREEN_HEIGHT, 2);
                gt.DrawFrame(g, Constant.BG1_MODULE_ID, SCREEN_WIDTH, SCREEN_HEIGHT, 3);
                gt.DrawFrame(g, Constant.BG1_BIGMODULE_ID, 0, Constant.BG_MODULE_HEIGHT, 0);
                gt.DrawFrame(g, Constant.BG1_BIGMODULE_ID, SCREEN_WIDTH, Constant.BG_MODULE_HEIGHT, 1);
                gt.DrawFrame(g, Constant.BG1_BIGMODULE_ID, 0, SCREEN_HEIGHT - Constant.BG_MODULE_HEIGHT, 2);
                gt.DrawFrame(g, Constant.BG1_BIGMODULE_ID, SCREEN_WIDTH, SCREEN_HEIGHT - Constant.BG_MODULE_HEIGHT, 3);
                break;
            }
            case (red): {
                gt.DrawFrame(g, Constant.BG3_MODULE_ID, 0, 0, 0);
                gt.DrawFrame(g, Constant.BG3_MODULE_ID, SCREEN_WIDTH, 0, 1);
                gt.DrawFrame(g, Constant.BG3_MODULE_ID, 0, SCREEN_HEIGHT, 2);
                gt.DrawFrame(g, Constant.BG3_MODULE_ID, SCREEN_WIDTH, SCREEN_HEIGHT, 3);
                gt.DrawFrame(g, Constant.BG3_BIGMODULE_ID, 0, Constant.BG_MODULE_HEIGHT, 0);
                gt.DrawFrame(g, Constant.BG3_BIGMODULE_ID, SCREEN_WIDTH, Constant.BG_MODULE_HEIGHT, 1);
                gt.DrawFrame(g, Constant.BG3_BIGMODULE_ID, 0, SCREEN_HEIGHT - Constant.BG_MODULE_HEIGHT, 2);
                gt.DrawFrame(g, Constant.BG3_BIGMODULE_ID, SCREEN_WIDTH, SCREEN_HEIGHT - Constant.BG_MODULE_HEIGHT, 3);
                break;
            }
            case (green): {
                gt.DrawFrame(g, Constant.BG2_MODULE_ID, 0, 0, 0);
                gt.DrawFrame(g, Constant.BG2_MODULE_ID, SCREEN_WIDTH, 0, 1);
                gt.DrawFrame(g, Constant.BG2_MODULE_ID, 0, SCREEN_HEIGHT, 2);
                gt.DrawFrame(g, Constant.BG2_MODULE_ID, SCREEN_WIDTH, SCREEN_HEIGHT, 3);
                gt.DrawFrame(g, Constant.BG2_BIGMODULE_ID, 0, Constant.BG_MODULE_HEIGHT, 0);
                gt.DrawFrame(g, Constant.BG2_BIGMODULE_ID, SCREEN_WIDTH, Constant.BG_MODULE_HEIGHT, 1);
                gt.DrawFrame(g, Constant.BG2_BIGMODULE_ID, 0, SCREEN_HEIGHT - Constant.BG_MODULE_HEIGHT, 2);
                gt.DrawFrame(g, Constant.BG2_BIGMODULE_ID, SCREEN_WIDTH, SCREEN_HEIGHT - Constant.BG_MODULE_HEIGHT, 3);
                break;

            }
        }
    }

     public void drawPopup(Graphics g, int type) {
        int add = 0;
        int add1 = 0;
//        if (name_index == 2) {
//            add = Constant.MODULE_ID_MENUSTRIP_HEIGHT / 2;
//        }
//        if (name_index == 3) {
//            add = Constant.MODULE_ID_MENUSTRIP_HEIGHT;
//        }

        switch (type) {
            case (0): {
                gt.DrawFrame(g, Constant.FRAME_ID_POPUP_CENTER_BLUE, SCREEN_WIDTH / 2 - gt.getFrameWidth(Constant.FRAME_ID_POPUP_CENTER_BLUE) / 2, SCREEN_HEIGHT / 2 - gt.getFrameHeight(Constant.FRAME_ID_POPUP_CENTER_BLUE) + add-add1, 0);
                gt.DrawFrame(g, Constant.FRAME_ID_POPUP_CENTER_BLUE, SCREEN_WIDTH / 2 - gt.getFrameWidth(Constant.FRAME_ID_POPUP_CENTER_BLUE) / 2, SCREEN_HEIGHT / 2 + gt.getFrameHeight(Constant.FRAME_ID_POPUP_CENTER_BLUE) + add-add1, 2);
                gt.DrawFrame(g, Constant.FRAME_ID_POPUP_BLUE, SCREEN_WIDTH / 2 - gt.getFrameWidth(Constant.FRAME_ID_POPUP_BLUE) - gt.getFrameWidth(Constant.FRAME_ID_POPUP_CENTER_BLUE) / 2, SCREEN_HEIGHT / 2 - gt.getFrameHeight(Constant.FRAME_ID_POPUP_BLUE) + add-add1, 0);
                gt.DrawFrame(g, Constant.FRAME_ID_POPUP_BLUE, SCREEN_WIDTH / 2 + gt.getFrameWidth(Constant.FRAME_ID_POPUP_BLUE) + gt.getFrameWidth(Constant.FRAME_ID_POPUP_CENTER_BLUE) / 2, SCREEN_HEIGHT / 2 - gt.getFrameHeight(Constant.FRAME_ID_POPUP_BLUE) + add-add1, 1);
                gt.DrawFrame(g, Constant.FRAME_ID_POPUP_BLUE, SCREEN_WIDTH / 2 - gt.getFrameWidth(Constant.FRAME_ID_POPUP_BLUE) - gt.getFrameWidth(Constant.FRAME_ID_POPUP_CENTER_BLUE) / 2, SCREEN_HEIGHT / 2 + gt.getFrameHeight(Constant.FRAME_ID_POPUP_BLUE) + add-add1, 2);
                gt.DrawFrame(g, Constant.FRAME_ID_POPUP_BLUE, SCREEN_WIDTH / 2 + gt.getFrameWidth(Constant.FRAME_ID_POPUP_BLUE) + gt.getFrameWidth(Constant.FRAME_ID_POPUP_CENTER_BLUE) / 2, SCREEN_HEIGHT / 2 + gt.getFrameHeight(Constant.FRAME_ID_POPUP_BLUE) + add-add1, 3);
                break;
            }
            case (1): {
                gt.DrawFrame(g, Constant.FRAME_ID_POPUP_CENTER_RED, SCREEN_WIDTH / 2 - gt.getFrameWidth(Constant.FRAME_ID_POPUP_CENTER_BLUE) / 2, SCREEN_HEIGHT / 2 - gt.getFrameHeight(Constant.FRAME_ID_POPUP_CENTER_BLUE) + add-add1, 0);
                gt.DrawFrame(g, Constant.FRAME_ID_POPUP_CENTER_RED, SCREEN_WIDTH / 2 - gt.getFrameWidth(Constant.FRAME_ID_POPUP_CENTER_BLUE) / 2, SCREEN_HEIGHT / 2 + gt.getFrameHeight(Constant.FRAME_ID_POPUP_CENTER_BLUE) + add-add1, 2);
                gt.DrawFrame(g, Constant.FRAME_ID_POPUP_RED, SCREEN_WIDTH / 2 - gt.getFrameWidth(Constant.FRAME_ID_POPUP_BLUE) - gt.getFrameWidth(Constant.FRAME_ID_POPUP_CENTER_BLUE) / 2, SCREEN_HEIGHT / 2 - gt.getFrameHeight(Constant.FRAME_ID_POPUP_BLUE) + add-add1, 0);
                gt.DrawFrame(g, Constant.FRAME_ID_POPUP_RED, SCREEN_WIDTH / 2 + gt.getFrameWidth(Constant.FRAME_ID_POPUP_BLUE) + gt.getFrameWidth(Constant.FRAME_ID_POPUP_CENTER_BLUE) / 2, SCREEN_HEIGHT / 2 - gt.getFrameHeight(Constant.FRAME_ID_POPUP_BLUE) + add-add1, 1);
                gt.DrawFrame(g, Constant.FRAME_ID_POPUP_RED, SCREEN_WIDTH / 2 - gt.getFrameWidth(Constant.FRAME_ID_POPUP_BLUE) - gt.getFrameWidth(Constant.FRAME_ID_POPUP_CENTER_BLUE) / 2, SCREEN_HEIGHT / 2 + gt.getFrameHeight(Constant.FRAME_ID_POPUP_BLUE) + add-add1, 2);
                gt.DrawFrame(g, Constant.FRAME_ID_POPUP_RED, SCREEN_WIDTH / 2 + gt.getFrameWidth(Constant.FRAME_ID_POPUP_BLUE) + gt.getFrameWidth(Constant.FRAME_ID_POPUP_CENTER_BLUE) / 2, SCREEN_HEIGHT / 2 + gt.getFrameHeight(Constant.FRAME_ID_POPUP_BLUE) + add-add1, 3);
                break;
            }
            case (2): {
                gt.DrawFrame(g, Constant.FRAME_ID_POPUP_CENTER_GREEN, SCREEN_WIDTH / 2 - gt.getFrameWidth(Constant.FRAME_ID_POPUP_CENTER_BLUE) / 2, SCREEN_HEIGHT / 2 - gt.getFrameHeight(Constant.FRAME_ID_POPUP_CENTER_BLUE) + add-add1, 0);
                gt.DrawFrame(g, Constant.FRAME_ID_POPUP_CENTER_GREEN, SCREEN_WIDTH / 2 - gt.getFrameWidth(Constant.FRAME_ID_POPUP_CENTER_BLUE) / 2, SCREEN_HEIGHT / 2 + gt.getFrameHeight(Constant.FRAME_ID_POPUP_CENTER_BLUE) + add-add1, 2);
                gt.DrawFrame(g, Constant.FRAME_ID_POPUP_GREEN, SCREEN_WIDTH / 2 - gt.getFrameWidth(Constant.FRAME_ID_POPUP_BLUE) - gt.getFrameWidth(Constant.FRAME_ID_POPUP_CENTER_BLUE) / 2, SCREEN_HEIGHT / 2 - gt.getFrameHeight(Constant.FRAME_ID_POPUP_BLUE) + add-add1, 0);
                gt.DrawFrame(g, Constant.FRAME_ID_POPUP_GREEN, SCREEN_WIDTH / 2 + gt.getFrameWidth(Constant.FRAME_ID_POPUP_BLUE) + gt.getFrameWidth(Constant.FRAME_ID_POPUP_CENTER_BLUE) / 2, SCREEN_HEIGHT / 2 - gt.getFrameHeight(Constant.FRAME_ID_POPUP_BLUE) + add-add1, 1);
                gt.DrawFrame(g, Constant.FRAME_ID_POPUP_GREEN, SCREEN_WIDTH / 2 - gt.getFrameWidth(Constant.FRAME_ID_POPUP_BLUE) - gt.getFrameWidth(Constant.FRAME_ID_POPUP_CENTER_BLUE) / 2, SCREEN_HEIGHT / 2 + gt.getFrameHeight(Constant.FRAME_ID_POPUP_BLUE) + add-add1, 2);
                gt.DrawFrame(g, Constant.FRAME_ID_POPUP_GREEN, SCREEN_WIDTH / 2 + gt.getFrameWidth(Constant.FRAME_ID_POPUP_BLUE) + gt.getFrameWidth(Constant.FRAME_ID_POPUP_CENTER_BLUE) / 2, SCREEN_HEIGHT / 2 + gt.getFrameHeight(Constant.FRAME_ID_POPUP_BLUE) + add-add1, 3);
                break;
            }
        }
    }

      public void drawSmallBG(Graphics g, int type) {
        switch (type) {
            case (0): {
                gt.DrawFrame(g, Constant.SMALLBG1_MODULE_ID, 0, SCREEN_HEIGHT, 2);
                gt.DrawFrame(g, Constant.SMALLBG1_MODULE_ID, SCREEN_WIDTH, SCREEN_HEIGHT, 3);
                break;
            }
            case (1): {
                gt.DrawFrame(g, Constant.SMALLBG2_MODULE_ID, 0, SCREEN_HEIGHT, 2);
                gt.DrawFrame(g, Constant.SMALLBG2_MODULE_ID, SCREEN_WIDTH, SCREEN_HEIGHT, 3);
                break;
            }
            case (2): {
                gt.DrawFrame(g, Constant.SMALLBG3_MODULE_ID, 0, SCREEN_HEIGHT, 2);
                gt.DrawFrame(g, Constant.SMALLBG3_MODULE_ID, SCREEN_WIDTH, SCREEN_HEIGHT, 3);
                break;
            }

        }
    }

      public void draw_theme_Select(Graphics g) {

        int popup_width = ((2 * gt.getFrameWidth(Constant.FRAME_ID_POPUP_BLUE)) + (gt.getFrameWidth(Constant.FRAME_ID_POPUP_CENTER_BLUE)));
        int theme_distance = ((popup_width) - (3 * (Constant.MODULE_ID_THEME_WIDTH))) / 4 + (getWidth() / 2 - popup_width / 2);
        int fix_distance = theme_distance;

        int theme_distance_focus = ((popup_width) - (3 * (Constant.MODULE_ID_THEME_WIDTH+2*Constant.THEME_FOCUS_BORDER))) / 4 + (getWidth() / 2 - popup_width / 2);
        int fix_distance_focus = theme_distance_focus;

        for (int i = 0; i < 3; i++) {
               if(theme[i])
        {  g.setColor(205,181,205);
              g.fillRect(theme_distance-Constant.THEME_FOCUS_BORDER, (getHeight() / 2) - (Constant.MODULE_ID_THEME_HEIGHT / 2)-Constant.THEME_FOCUS_BORDER, Constant.MODULE_ID_THEME_WIDTH+2*Constant.THEME_FOCUS_BORDER, (Constant.MODULE_ID_THEME_HEIGHT )+2*Constant.THEME_FOCUS_BORDER);
        }
            gt.DrawModule(g, theme_id[i], theme_distance, (getHeight() / 2) - (Constant.MODULE_ID_THEME_HEIGHT / 2), 0);
            theme_distance = (theme_distance - ((getWidth() / 2 - popup_width / 2))) + (Constant.MODULE_ID_THEME_WIDTH + fix_distance);
        }
    }

     public void draw_mainmenu_option(Graphics g, int s_array) {



//        if (name_index == 0) {



        int pos = 0;
        int a = name[s_array].length - 1;
        int lcount = 0;
        show_down = 0;
        final_out = 1;

   
            show_down = 0;
    

        for (int i = 2; i <= name[s_array].length; i++) {
            if (lcount == 2) {
                final_out = final_out + 2;
                lcount = 1;
            } else {
                final_out = final_out + 1;
                lcount++;
            }
        }


        for (int i = 0; i < name[s_array].length; i++) {

            int font_width = gtfont.getStringWidth(name[s_array][i]);
            int font_height = gtfont.getCharHeight();

           font_height=font_height/2;


            for (int u = 0; u < 3; u++) {
                int menu = 0;
                int menu1 = 0;
                int flag = 0;
                if (theme[u] == true) {
                    if (u != 0) {
                        menu = Constant.MODULE_ID_MENUSTRIP_WIDTH;
                        menu1 = Constant.MODULE_ID_MENUSTRIP_FOCUS_WIDTH;
                        flag = 1;
                    }
                    gt.DrawFrame(g, MENUSTRIP1_THEME[u], getWidth() / 2 - Constant.MODULE_ID_MENUSTRIP_WIDTH, (((((((getHeight() - show_down) / 2)) + show_down) - ((final_out) * Constant.MODULE_ID_MENUSTRIP_HEIGHT))) + (pos * Constant.MODULE_ID_MENUSTRIP_HEIGHT))-add_pos, 0);
                    gt.DrawFrame(g, MENUSTRIP2_THEME[u], getWidth() / 2 + menu, (((((((getHeight() - show_down) / 2)) + show_down) - ((final_out) * Constant.MODULE_ID_MENUSTRIP_HEIGHT))) + (pos * Constant.MODULE_ID_MENUSTRIP_HEIGHT))-add_pos, flag);
//                if(i==which_mainbutton && touch==false)
//                {
//
//                    gt.DrawFrame(g, MENUSTRIP_FOCUS1[u], getWidth() / 2-Constant.MODULE_ID_MENUSTRIP_FOCUS_WIDTH, (((((((getHeight()-show_down) / 2))+show_down) - ((final_out) * Constant.MODULE_ID_MENUSTRIP_HEIGHT))) + (glow * Constant.MODULE_ID_MENUSTRIP_HEIGHT))-Constant.MODULE_MENU_HEIGHT_DIFF-add_pos , 0);
//                    gt.DrawFrame(g, MENUSTRIP_FOCUS2[u], getWidth() / 2+menu1, (((((((getHeight()-show_down) / 2))+show_down) - ((final_out) * Constant.MODULE_ID_MENUSTRIP_HEIGHT))) + (glow * Constant.MODULE_ID_MENUSTRIP_HEIGHT))-Constant.MODULE_MENU_HEIGHT_DIFF-add_pos , flag);
//
//                }
                }

            }


            gtfont.drawString(g, name[s_array][i], (getWidth() / 2 - (font_width / 2)), (((((((getHeight() - show_down) / 2)) + show_down) - ((final_out) * Constant.MODULE_ID_MENUSTRIP_HEIGHT))) + (pos * Constant.MODULE_ID_MENUSTRIP_HEIGHT)) + (3*font_height)/2, 0);

            pos = pos + 3;

            if (name_index == 0) {
                draw_cancle_button(g);
            }
            if (name_index == 1) {
                draw_back_button(g);
            }

        }
    }

     public void draw_exit_screen(Graphics g) {
        midlet.notifyDestroyed();
    }

     public void draw_back_button(Graphics g) {
        for (int u = 0; u < 3; u++) {
            if (theme[u]) {

               gt.DrawModule(g, BUTTON[u], (getWidth() - (Constant.MODULE_ID_BUTTON_FOCUS_WIDTH)), (getHeight() - (Constant.MODULE_ID_BUTTON_FOCUS_HEIGHT)), 0);

               gt.DrawModule(g, BACK_BUTTON[u], (getWidth() - (Constant.MODULE_ID_BACKBUTTON_WIDTH / 2)) - ((Constant.MODULE_ID_BUTTON_FOCUS_WIDTH) / 2), (getHeight() - (Constant.MODULE_ID_BACKBUTTON_HEIGHT / 2)) - (Constant.MODULE_ID_BUTTON_FOCUS_HEIGHT / 2), 0);

            }
        }
    }
     public void draw_pause_button(Graphics g) {
        for (int u = 0; u < 3; u++) {
            if (theme[u]) {

              gt.DrawModule(g, BUTTON[u], 0, (getHeight() - (Constant.MODULE_ID_BUTTON_FOCUS_HEIGHT)), 0);
              gt.DrawModule(g, PAUSE_BUTTON[u], (Constant.MODULE_ID_BUTTON_FOCUS_WIDTH / 2) - (Constant.MODULE_ID_OKBUTTON_WIDTHHEIGHT / 2), getHeight() - ((Constant.MODULE_ID_BUTTON_FOCUS_HEIGHT / 2)) - ((Constant.MODULE_ID_OKBUTTON_WIDTHHEIGHT / 2)), 0);

            }
        }
    }
     public void draw_replay_button(Graphics g) {
        for (int u = 0; u < 3; u++) {
            if (theme[u]) {

                gt.DrawModule(g, BUTTON[u], (getWidth() - (Constant.MODULE_ID_BUTTON_FOCUS_WIDTH)), (getHeight() - (Constant.MODULE_ID_BUTTON_FOCUS_HEIGHT)), 0);
              gt.DrawModule(g, REPLAY_BUTTON[u], (getWidth() - (Constant.MODULE_ID_REPLAYBUTTON_WIDTH / 2)) - ((Constant.MODULE_ID_BUTTON_FOCUS_WIDTH) / 2), (getHeight() - (Constant.MODULE_ID_REPLAYBUTTON_HEIGHT / 2)) - (Constant.MODULE_ID_BUTTON_FOCUS_HEIGHT / 2), 0);
            }
        }
    }
     public void draw_cancle_button(Graphics g) {
        for (int u = 0; u < 3; u++) {
            if (theme[u]) {

              gt.DrawModule(g, BUTTON[u], (getWidth() - (Constant.MODULE_ID_BUTTON_FOCUS_WIDTH)), (getHeight() - (Constant.MODULE_ID_BUTTON_FOCUS_HEIGHT)), 0);
              gt.DrawModule(g, CANCLE_BUTTON[u], (getWidth() - (Constant.MODULE_ID_REPLAYBUTTON_WIDTH / 2)) - ((Constant.MODULE_ID_BUTTON_FOCUS_WIDTH) / 2), (getHeight() - (Constant.MODULE_ID_REPLAYBUTTON_HEIGHT / 2)) - (Constant.MODULE_ID_BUTTON_FOCUS_HEIGHT / 2), 0);

            }
        }
    }
     public void draw_select_button(Graphics g) {
        for (int u = 0; u < 3; u++) {
            if (theme[u]) {

                gt.DrawModule(g, BUTTON[u], 0, (getHeight() - (Constant.MODULE_ID_BUTTON_FOCUS_HEIGHT)), 0);
                gt.DrawModule(g, OK_BUTTON[u], (Constant.MODULE_ID_BUTTON_FOCUS_WIDTH / 2) - (Constant.MODULE_ID_OKBUTTON_WIDTHHEIGHT / 2), getHeight() - ((Constant.MODULE_ID_BUTTON_FOCUS_HEIGHT / 2)) - ((Constant.MODULE_ID_OKBUTTON_WIDTHHEIGHT / 2)), 0);

            }
        }
    }

     public void draw_high_score(Graphics g)
    {



                g.setColor(255,255,255);
                    String scoreSt[] = GameUserData.getHighestScores();
                    gtfont.drawString(g, ("TOP TEN HIGH SCORE : "), 10 , 15 ,0);

//                    g.fillRect(50, 20, 240-50, 360-30);
                    for (int i = 0; i < scoreSt.length; i++) {
//                    gtfont.drawString(g, ""+scoreSt[i],120 ,30*(i+1) , 0);
                    g.drawString( ""+scoreSt[i],120 ,(40*(i+1))+30 , 0);
               
            }
     }
     
     public void draw_instruction(Graphics g)
     {
     
     gtfont.drawString(g,("HOW TO PLAY"), (((Constant.screen_width)/2)-100), 15, 0);
     g.setColor(255, 255, 255);
     int height=100;
        for(int set=1;set<=7;set++)
        {
        g.drawString(instruction[set-1],20,(height),0);
        g.drawImage(icon, (Constant.screen_width)-75, 15, 0);
        height=height+40;
        }
        
     }
     
     public void draw_about(Graphics g)
     {
     gtfont.drawString(g,("ABOUT"),(((Constant.screen_width)/2)-50),15,0);
     g.drawImage(icon, (Constant.screen_width)-75, 15, 0);
     g.setColor(255,255,255);
     g.drawString("Game Developed and designed by: ", 20, 100, 0);
     g.drawString("Swami Vivekanand College Students", 30, 120, 0);
     g.drawString("Game Head: Prof. Brajesh Chaturvedi", 20, 150, 0);
     g.drawString("Developed By Computer Science and Department Students",20,200,0);
     g.drawString("Developers : Chetan Saraf", 20, 250, 0);
     g.drawString("                           Mamta Bhamre",20,270,0);
     g.drawString("                                Vaibhav Namdev", 20, 290, score);
     g.drawString("Graphis: Team members", 20, 350, 0);
     g.drawString("Creadits: SVCE Faculty Members and Friends", 20, 420, 0);
     g.drawString("Version: 1.0", 20, 470, 0);
     g.drawString("@Copyright: All rights reserved to SVCE", 90, 500, 0);
     }
     

    //_______________________________________________________________________________________________________________________________

    public void pointerPressed(int x, int y) {

        switch(Constant.current_state)
        {
            case Constant.MENU_STATE:
            {

               pos = 0;
                int a = name[name_index].length - 1;
                for (int i = 0; i < name[name_index].length; i++)
                {

                    if (x > getWidth() / 2 - Constant.MODULE_ID_MENUSTRIP_WIDTH && x < getWidth() / 2 + Constant.MODULE_ID_MENUSTRIP_WIDTH && y > (((((((getHeight() - show_down) / 2)) + show_down) - ((final_out) * Constant.MODULE_ID_MENUSTRIP_HEIGHT))) + (pos * Constant.MODULE_ID_MENUSTRIP_HEIGHT)) - add_pos && y < (((((((getHeight() - show_down) / 2)) + show_down) - ((final_out) * Constant.MODULE_ID_MENUSTRIP_HEIGHT))) + (pos * Constant.MODULE_ID_MENUSTRIP_HEIGHT)) + (Constant.MODULE_ID_MENUSTRIP_HEIGHT * 2) - add_pos) {
                        sound_ball_miss.sound_0N(0, 1);
                        switch(i)
                        {

                            case 0: {


                                if (name_index == 0) {
                                    Constant.current_state = Constant.PLAY_STATE;
                                } else {
                                    if (name_index == 1) {
//                                        System.out.println("change theam");
                                        Constant.current_state = Constant.THEME_STATE;
                                    }
                                    else
                                    {
                                       if(name_index == 2)
                                       {
                                           Constant.current_state = Constant.PLAY_STATE;
                                       }
                                    }
                                }
                            }
                            break;
                            case 1: {
                                if (name_index == 0) {
                                    name_index = 1;
                                } else {
                                    if (name_index == 1) {

                                        if (is_sound) {
                                            is_sound = false;
                                            sound_value = on;
                                            name[1][1] = ("SOUND " + sound_status_value[sound_value]);
                                        } else {
                                            is_sound = true;
                                            sound_value = off;
                                            name[1][1] = ("SOUND " + sound_status_value[sound_value]);
                                        }

                                    }
                                    else
                                    {
                                       if(name_index == 2)
                                       {
                                           name_index = 0;
                                           restart_game();
                                           Constant.current_state = Constant.MENU_STATE;
                                       }
                                    }
                                }
                            }
                            break;
                            case 2:
                            {
                                if (name_index == 0) {
                                    Constant.current_state = Constant.HIGHSCORE_STATE;
                                }
                            }
                           break;
                            case 3:
                            {
                            if (name_index == 0) {
                                    System.out.println("Instruction pressed......");
                                    Constant.current_state = Constant.INSTRUCTION_STATE;
                                }
                            }
                           break;
                            case 4:
                            {
                            if (name_index == 0) {
                                    System.out.println("About Pressed......");
                                    Constant.current_state = Constant.ABOUT_STATE;
                                }
                            }
                           break;
                        }
                       
                    }
                    pos = pos + 3;
                }

                // pressed cancel button start...
                if ( name_index == 0 && x > getWidth() - (Constant.MODULE_ID_BUTTON_FOCUS_WIDTH) && x < getWidth() && y > getHeight() - Constant.MODULE_ID_BUTTON_FOCUS_HEIGHT && y < getHeight())
                {
                    draw_exit_screen(g);
                }
                // pressed cancel button end...


                // pressed back button start...
                if ( name_index == 1 && x > getWidth() - (Constant.MODULE_ID_BUTTON_FOCUS_WIDTH) && x < getWidth() && y > getHeight() - Constant.MODULE_ID_BUTTON_FOCUS_HEIGHT && y < getHeight())
                {
                    sound_ball_miss.sound_0N(0, 1);
                    name_index = 0;
                }
                // pressed back button end...

            }
            break;



            case (Constant.THEME_STATE):
            {


                int popup_width = ((2 * gt.getFrameWidth(Constant.FRAME_ID_POPUP_BLUE)) + (gt.getFrameWidth(Constant.FRAME_ID_POPUP_CENTER_BLUE)));
                int theme_distance = ((popup_width) - (3 * (Constant.MODULE_ID_THEME_WIDTH))) / 4 + (getWidth() / 2 - popup_width / 2);
                int fix_distance = theme_distance;


                for (int i = 0; i < 3; i++) {
                    if (x > theme_distance && x < theme_distance + (Constant.MODULE_ID_THEME_WIDTH) && y > (getHeight() / 2) - (Constant.MODULE_ID_THEME_HEIGHT / 2) && y < (getHeight() / 2) + (Constant.MODULE_ID_THEME_HEIGHT / 2)) {

                        sound_ball_miss.sound_0N(0, 1);
                        theme_type = i;

                        switch (i + 1) {
                            case (1): {
                                theme_type = 0;
//                                gs.setthemevalue(0, 0);
                                theme[0] = true;
                                theme[1] = false;
                                theme[2] = false;
                                break;
                            }
                            case (2): {

                                theme_type = 1;
//                                gs.setthemevalue(0, 1);
                                theme[0] = false;
                                theme[1] = true;
                                theme[2] = false;
                                break;
                            }
                            case (3): {

                                theme_type = 2;
//                                gs.setthemevalue(0, 2);
                                theme[0] = false;
                                theme[1] = false;
                                theme[2] = true;
                                break;
                            }
                        }
                    }
                    theme_distance = (theme_distance - ((getWidth() / 2 - popup_width / 2))) + (Constant.MODULE_ID_THEME_WIDTH + fix_distance);
                }

             // pressed select button ... start..
                if (x > 0 && x < ((Constant.MODULE_ID_BUTTON_FOCUS_WIDTH)) && y > getHeight() - ((Constant.MODULE_ID_BUTTON_FOCUS_HEIGHT)) && y < getHeight()) {

                    sound_ball_miss.sound_0N(0, 1);
                    name_index = 0;
                    Constant.current_state = Constant.MENU_STATE;
                }
              // pressed select button ... end..
            }
            break;


            case Constant.PLAY_STATE:
            {
                
                if(y < (Constant.screen_height-(Constant.MODULE_ID_BUTTON_FOCUS_HEIGHT+20)))
                {
                    if(update_ball_x )
                         {
                        if(fire_pressed == false)
                            {
                               fire_pressed = true;
                            }

                         
                            update_ball_x = false;
                            ball_x_value = ((Surface)(vector_surface.elementAt(0))).get_surface_x();

                            if(is_sound)
                            {
                               sound_ball_jump.sound_0N(0, 1);
                            }
                          }
                }
                    // pressed replay button ... start
                   if ( x > getWidth() - (Constant.MODULE_ID_BUTTON_FOCUS_WIDTH) && x < getWidth() && y > getHeight() - Constant.MODULE_ID_BUTTON_FOCUS_HEIGHT && y < getHeight())
                    {
                       sound_ball_miss.sound_0N(0, 1);
                        restart_game();
                    }
                     // pressed replay button ... close...

                    // pressed pause button ... start
                        if (x > 0 && x < ((Constant.MODULE_ID_BUTTON_FOCUS_WIDTH)) && y > getHeight() - ((Constant.MODULE_ID_BUTTON_FOCUS_HEIGHT)) && y < getHeight())
                 {
                            sound_ball_miss.sound_0N(0, 1);
                      name_index = 2;
                      Constant.current_state = Constant.MENU_STATE;
                    }
                     // pressed pause button ... close...
            }
            break;

            case Constant.GAMEOVER_STATE:
            {

                if (x > 0 && x < ((Constant.MODULE_ID_BUTTON_FOCUS_WIDTH)) && y > getHeight() - ((Constant.MODULE_ID_BUTTON_FOCUS_HEIGHT)) && y < getHeight())
                 {
                    sound_ball_miss.sound_0N(0, 1);
                       restart_game();
                      name_index = 0;
                      Constant.current_state = Constant.MENU_STATE;
                    }

                if ( x > getWidth() - (Constant.MODULE_ID_BUTTON_FOCUS_WIDTH) && x < getWidth() && y > getHeight() - Constant.MODULE_ID_BUTTON_FOCUS_HEIGHT && y < getHeight())
                    {
                    sound_ball_miss.sound_0N(0, 1);
                        restart_game();
                        Constant.current_state = Constant.PLAY_STATE;
                    }
            }
            break;

            case Constant.HIGHSCORE_STATE:
            {
               if ( x > getWidth() - (Constant.MODULE_ID_BUTTON_FOCUS_WIDTH) && x < getWidth() && y > getHeight() - Constant.MODULE_ID_BUTTON_FOCUS_HEIGHT && y < getHeight())
                {
                   sound_ball_miss.sound_0N(0, 1);
                    name_index = 0;
                    Constant.current_state = Constant.MENU_STATE;
                }
            }
            break;
             
            case Constant.INSTRUCTION_STATE:
            {
            
            if ( x > getWidth() - (Constant.MODULE_ID_BUTTON_FOCUS_WIDTH) && x < getWidth() && y > getHeight() - Constant.MODULE_ID_BUTTON_FOCUS_HEIGHT && y < getHeight())
                {
                   sound_ball_miss.sound_0N(0, 1);
                    name_index = 0;
                    Constant.current_state = Constant.MENU_STATE;
                }
            }
                
            case Constant.ABOUT_STATE:
            {
             
             if ( x > getWidth() - (Constant.MODULE_ID_BUTTON_FOCUS_WIDTH) && x < getWidth() && y > getHeight() - Constant.MODULE_ID_BUTTON_FOCUS_HEIGHT && y < getHeight())
                {
                   sound_ball_miss.sound_0N(0, 1);
                    name_index = 0;
                    Constant.current_state = Constant.MENU_STATE;
                }
            
            }
    }


    }
//_____________________________________________________________________________________________________________________________
    public void pointerReleased(int x, int y) {

        
    }

      public void pointerDragged(int x, int y) {
      }

//______________________________________________________________________________________________________________________________

    public void keyPressed(int key) {
        boolean querty = false;
        int gameKey = getGameAction(key);
        boolean keyChanged = false;
        if ((getGameAction(48) == 0) && (getGameAction(49) == 0) && (getGameAction(50) == 0) && (getGameAction(51) == 0) && (getGameAction(52) == 0) && (getGameAction(53) == 0) && (getGameAction(54) == 0) && (getGameAction(55) == 0) && (getGameAction(56) == 0) && (getGameAction(57) == 0)) {
            querty = true;
            if (key == (int) 'j' && key == (int) 'J') {
                key = Canvas.KEY_POUND;
                keyChanged = true;
            }
            if (key == (int) 'u' && keyChanged == false && key == (int) 'U') {
                key = Canvas.KEY_STAR;
                keyChanged = true;
            }
            if (key == (int) 'm' && keyChanged == false && key == (int) 'M') {
                key = Canvas.KEY_NUM0;
                keyChanged = true;
            }
            if (key == (int) 'r' && keyChanged == false && key == (int) 'R') {
                key = Canvas.KEY_NUM1;
                keyChanged = true;
            }
            if (key == (int) 't' && keyChanged == false && key == (int) 'T') {
                key = Canvas.KEY_NUM2;
                keyChanged = true;
            }

            if (key == (int) 'y' && keyChanged == false && key == (int) 'Y') {
                key = Canvas.KEY_NUM3;
                keyChanged = true;
            }
            if (key == (int) 'f' && keyChanged == false && key == (int) 'F') {
                key = Canvas.KEY_NUM4;
                keyChanged = true;
            }

            if (key == (int) 'g' && keyChanged == false && key == (int) 'G') {
                key = Canvas.KEY_NUM5;
                keyChanged = true;
            }

            if (key == (int) 'h' && keyChanged == false && key == (int) 'H') {
                key = Canvas.KEY_NUM6;
                keyChanged = true;
            }

            if (key == (int) 'v' && keyChanged == false && key == (int) 'V') {
                key = Canvas.KEY_NUM7;
                keyChanged = true;
            }

            if (key == (int) 'b' && keyChanged == false && key == (int) 'B') {
                key = Canvas.KEY_NUM8;
                keyChanged = true;
            }
        }

        if (gameKey == Canvas.LEFT) {
                 }
        if (gameKey == Canvas.RIGHT) {
       
        }

        if (gameKey == Canvas.FIRE) {

            if(fire_pressed == false)
            {
               fire_pressed = true;
            }

            update_ball_x = false;
            ball_x_value = ((Surface)(vector_surface.elementAt(0))).get_surface_x();

        }

    }
    //__________________________________________________________________________________________________________________________

    public void keyRepeated(int key) {


        int gameKey = getGameAction(key);

       

        if (gameKey == Canvas.LEFT)
        {
       
        }
        if (gameKey == Canvas.RIGHT) 
        {
       
        }

    }
//________________________________________________________________________________________________________________

    protected void keyReleased(int key) {

        int gameKey = getGameAction(key);
        if (gameKey == Canvas.LEFT) 
        {
         
        }
        if (gameKey == Canvas.RIGHT)
        {
       
        }


    }

//________________________________________________________________________________________________________________
//    public void DrawInfo(Graphics g) {
//
//        //if (DEF.DbgDrawFPS) // FPS
//        {
//            _fps_count++;
//            long tick = System.currentTimeMillis();
//            if (tick - _last_fps_tick > 1000) {
//                _fps = (_fps_count * 1000) / (int) (tick - _last_fps_tick);
//                _last_fps_tick = tick;
//                /*
//                if (tick - _last_fps_tick > 2000)
//                {
//                _last_fps_tick = tick;
//                _fps = 0;
//                }
//                else
//                {
//                _last_fps_tick += 1000;
//                _fps = fps_count;
//                }
//                 */
//                _fps_count = 0;
//            }
////                g.setColor(0x000000);
////                g.fillRect(0, 0, 30, 18);
////                g.setFont(font);
////                g.setColor(0xFF7F00);
////                g.drawString("" + _fps, 1, 0, Graphics.LEFT | Graphics.TOP);
////                System.out.println("fps.... " + _fps);
//        }
//
//        {
//            //if ( (cGame._keys & KEY.STAR) != 0 ) System.gc();
//            _free_mem = Runtime.getRuntime().freeMemory() / 1024; // [Kb]
//            _total_mem = Runtime.getRuntime().totalMemory() / 1024; // [Kb]
//            _occ_mem = _total_mem - _free_mem; // [Kb]
//
////                g.setColor(0x000000);
////                g.fillRect( 0 , 0, DEF.SCR_W, 18);
////                g.setColor(0xFF7F00);
//            //g.drawString( "" + cGame._gameStateTimer + " F:" + _free_mem + "K " + " O:" + _occ_mem + "K", DEF.SCR_W, 0, Graphics.RIGHT | Graphics.TOP);
//        }
//
//    }

    


    public void run()
    {
        int w = 0, ww = 0;
        int r = 0, r1 = 0;
        while (true) {

            if (Constant.current_state == Constant.LOGO_STATE) {
                w++;
            }

            if (w > 50) {
                w = 0;
                Constant.current_state = Constant.SPLASH_STATE;
            }
            if (Constant.current_state == Constant.SPLASH_STATE) {
                ww++;
            }
            if (ww > 50) {
                ww = 0;
                Constant.current_state = Constant.MENU_STATE;

            }
            repaint();

            try
            {
//                 System.gc();
                   Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

  
//________________________________________________________________________________________________________________
}
//________________________________________________________________________________________________________________

