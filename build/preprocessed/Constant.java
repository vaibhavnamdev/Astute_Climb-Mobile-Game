
/**
 *
 * @author Mamta Bhamre
 */
public class Constant {


    static final int LOGO_STATE = 0;
    static final int SPLASH_STATE = 1;
    static final int MENU_STATE = 2;
    static final int PLAY_STATE = 3;
    static final int GAMEOVER_STATE = 4;
    static final int THEME_STATE = 5;
    static final int HIGHSCORE_STATE = 6;
    static final int INSTRUCTION_STATE = 7;
    static final int ABOUT_STATE = 8;
    static int current_state = LOGO_STATE;

    static int screen_height = 640;
    static int screen_width = 360;
    static int plat_width = 50;
    static int plat_height = 10;
    static int plat_gap = 4;
    static int total_loaded_plat = 5;
    static int minimum_speed = -5;
    static int maximum_speed = 5;
    static int distance = screen_height/4;
    static int down_again = 150;
    static int ball_width = 20;
    static int ball_height = 20;


    static int wav = 1;
    static int mid = 0;
    static String ball_jump     = "spiral_sfx_regular.wav";
    static String ball_set     = "spiral_sfx_pu_yellow.wav";
    static String ball_miss     = "spiral_sfx_pu_purple.wav";


     static int width=360;
     static int height=640;
     static final int THEME_FOCUS_BORDER=5;
     static final int FRAME_SIZE_x = 60;
     static final int FRAME_SIZE_y = 75;
     //static final int GLOWTEXT1_POS_Y=10;
     //static final int GLOWTEXT2_POS_Y=31;
     static final int STR_POS_Y=10;
     static final int STR_PUZZLE_SELECT_POS_Y=60;
     static int BUTT_WIDTH = 40, BUTT_HEIGHT = 40;
     static final int digifontwidth=13;
     static final int BG_MODULE_WIDTH=180;
     static final int BG_MODULE_HEIGHT=80;
     static final int BG_BIGMODULE_HEIGHT=240;
     static final int MODULE_ID_MENUSTRIP_WIDTH=130;
     static final int MODULE_ID_MENUSTRIP_HEIGHT=22;
     static final int MODULE_ID_MENUSTRIP_FOCUS_WIDTH=145;
     static final int MODULE_ID_MENUSTRIP_FOCUS_HEIGHT=34;
     static final int MODULE_MENU_HEIGHT_DIFF=MODULE_ID_MENUSTRIP_FOCUS_HEIGHT-MODULE_ID_MENUSTRIP_HEIGHT;
     static final int MODULE_ID_GLOWPUZZLETEXT1_WIDTH=168;
     static final int MODULE_ID_GLOWPUZZLETEXT1_HEIGHT=122;
     static final int MODULE_ID_GLOWPUZZLETEXT2_WIDTH=123;
     static final int MODULE_ID_GLOWPUZZLETEXT2_HEIGHT=91;
     static final int MODULE_ID_NEXTBACKBUTTON_WIDTH=43;
     static final int MODULE_ID_NEXTBACKBUTTON_HEIGHT=30;
     static final int MODULE_ID_BACKBUTTON_WIDTH=32;
     static final int MODULE_ID_BACKBUTTON_HEIGHT=36;
     static final int MODULE_ID_REPLAYBUTTON_WIDTH=38;
     static final int MODULE_ID_REPLAYBUTTON_HEIGHT=36;
     static final int MODULE_ID_OKBUTTON_WIDTHHEIGHT=36;
     static final int MODULE_ID_PUZZLEBACKBUTTON_WIDTHHEIGHT=20;
     static final int MODULE_ID_CANCELBUTTON_WIDTH=32;
     static final int MODULE_ID_CANCELBUTTON_HEIGHT=27;
     static final int MODULE_ID_HOMEBUTTON_WIDTH=28;
     static final int MODULE_ID_HOMEBUTTON_HEIGHT=25;
     static final int MODULE_ID_DEFAULTBUTTON_WIDTHHEIGHT=39;
     static final int MODULE_ID_POPUP_CORNER_WIDTH=16;
     static final int MODULE_ID_POPUP_CORNER_HEIGHT=124;
     static final int MODULE_ID_BUTTON_FOCUS_WIDTH=65;
     static final int MODULE_ID_BUTTON_FOCUS_HEIGHT=44;
     static final int MODULE_ID_POPUP_WIDTH=4;
     static final int MODULE_ID_POPUP_HEIGHT=124;
     static final int MODULE_ID_THEME_WIDTH=97;
     static final int MODULE_ID_THEME_HEIGHT=109;
     static final int MODULE_ID_PAUSE_WIDTH=24;
     static final int MODULE_ID_PAUSE_HEIGHT=32;
     static final int MODULE_ID_RING_WIDTH_HEIGHT=46;
     static final int MODULE_ID_BOX_WIDTH=167;
     static final int MODULE_ID_BOX_HEIGHT=115;
     static final int MODULE_ID_FOCUS_THEME_WIDTH=121;
     static final int MODULE_ID_FOCUS_THEME_HEIGHT=133;



     static final int SMALLBG1_MODULE_ID=46;
    static final int SMALLBG2_MODULE_ID=47;
    static final int SMALLBG3_MODULE_ID=48;
    static final int BG1_MODULE_ID=0;
    static final int BG1_BIGMODULE_ID=1;
    static final int BG2_MODULE_ID=2;
    static final int BG2_BIGMODULE_ID=3;
    static final int BG3_MODULE_ID=4;
    static final int BG3_BIGMODULE_ID=5;
    static final int MODULE_ID_MENUSTRIP=6;
    static final int MODULE_ID_MENUSTRIP_FOCUS=7;
    static final int MODULE_ID_GLOWPUZZLETEXT1=8;
    static final int MODULE_ID_GLOWPUZZLETEXT2=9;
    static final int MODULE_ID_NEXTBUTTON=10;
    static final int MODULE_ID_NEXTBUTTON_RED=42;
    static final int MODULE_ID_NEXTBUTTON_GREEN=58;
    static final int MODULE_ID_BACKBUTTON=11;
    static final int MODULE_ID_BACKBUTTON_RED=43;
    static final int MODULE_ID_BACKBUTTON_GREEN=59;
    static final int MODULE_ID_REPLAYBUTTON_BLUE=12;
    static final int MODULE_ID_REPLAYBUTTON_RED=44;
    static final int MODULE_ID_REPLAYBUTTON_GREEN=60;
    static final int MODULE_ID_OKBUTTON_BLUE=13;
    static final int MODULE_ID_OKBUTTON_RED=45;
    static final int MODULE_ID_OKBUTTON_GREEN=61;

    static final int MODULE_ID_PUZZLENEXTBUTTON=14;
    static final int MODULE_ID_PUZZLEBACKBUTTON=15;

    static final int MODULE_ID_PUZZLENEXTBUTTON_THEME2=46;
    static final int MODULE_ID_PUZZLEBACKBUTTON_THEME2=47;

    static final int MODULE_ID_PUZZLENEXTBUTTON_THEME3=62;
    static final int MODULE_ID_PUZZLEBACKBUTTON_THEME3=63;

    static final int MODULE_ID_CANCELBUTTON_BLUE=16;
    static final int MODULE_ID_CANCELBUTTON_RED=48;
    static final int MODULE_ID_CANCELBUTTON_GREEN=64;


    static final int MODULE_ID_HOMEBUTTON=17;
    static final int MODULE_ID_HOMEBUTTON_RED=49;
    static final int MODULE_ID_HOMEBUTTON_GREEN=65;

    static final int MODULE_ID_BUTTON_SELECT_BLUE=75;
    static final int MODULE_ID_BUTTON_SELECT_RED=76;
    static final int MODULE_ID_BUTTON_SELECT_GREEN=77;

    static final int MODULE_ID_DEFAULTBUTTON=18;
    static final int MODULE_ID_DEFAULTBUTTON_RED=67;
    static final int MODULE_ID_DEFAULTBUTTON_GREEN=69;

    static final int MODULE_ID_GLOWTHEME1_BUTTON=20;
    static final int MODULE_ID_GLOWTHEME2_BUTTON=19;
    static final int MODULE_ID_GLOWTHEME3_BUTTON=21;
    static final int MODULE_ID_LEVEL_BUTTON_GREEN=22;
    static final int MODULE_ID_LEVEL_BUTTON_ORENGE=23;


    static final int MODULE_ID_POPUP_CORNER=24;


    static final int MODULE_ID_BUTTON_FOCUS=73;
    static final int MODULE_ID_BUTTON_BLUE=74;
    static final int MODULE_ID_BUTTON_RED=72;
    static final int MODULE_ID_BUTTON_GREEN=71;



    static final int MODULE_ID_POPUP=25;


    static final int MODULE_ID_THEME1=26;
    static final int MODULE_ID_THEME2=27;
    static final int MODULE_ID_THEME3=28;


    static final int MODULE_ID_THEME_FOCUS_BLUE=84;
    static final int MODULE_ID_THEME_FOCUS_RED=85;
    static final int MODULE_ID_THEME_FOCUS_GREEN=86;

    static final int MODULE_ID_PAUSE_BLUE=30;
    static final int MODULE_ID_PAUSE_RED=70;
    static final int MODULE_ID_PAUSE_GREEN=66;


    static final int MODULE_ID_BOX_BLUE=78;
    static final int MODULE_ID_BOX_RED=79;
    static final int MODULE_ID_BOX_GREEN=80;


    static final int MODULE_ID_RING_BLUE=33;
    static final int MODULE_ID_RING_RED=57;
    static final int MODULE_ID_RING_GREEN=56;


    static final int FRAME_ID_MENUSTRIP1_THEME1=6;
    static final int FRAME_ID_MENUSTRIP2_THEME1=13;

    static final int FRAME_ID_GLOWTEXT1_THEME1=8;
    static final int FRAME_ID_GLOWTEXT2_THEME1=9;

    static final int FRAME_ID_GLOWTEXT1_THEME2=33;
    static final int FRAME_ID_GLOWTEXT2_THEME2=34;

    static final int FRAME_ID_GLOWTEXT1_THEME3=35;
    static final int FRAME_ID_GLOWTEXT2_THEME3=36;

    static final int FRAME_ID_MENUSTRIP1_THEME2=22;
    static final int FRAME_ID_MENUSTRIP2_THEME2=13;

    static final int FRAME_ID_MENUSTRIP1_THEME3=23;
    static final int FRAME_ID_MENUSTRIP2_THEME3=13;

    static final int FRAME_ID_MENUSTRIP_FOCUS1=7;
    static final int FRAME_ID_MENUSTRIP_FOCUS2=14;

    static final int FRAME_ID_MENUSTRIP_FOCUS1_THEME2=25;
    static final int FRAME_ID_MENUSTRIP_FOCUS1_THEME3=24;

    static final int FRAME_ID_MENUSTRIP_FOCUS1_ONPOPUP_THEME2=31;
    static final int FRAME_ID_MENUSTRIP_FOCUS1_ONPOPUP_THEME3=32;

    static final int FRAME_ID_MENUSTRIP_FOCUS1_ON_POPUP=21;
    static final int FRAME_ID_MENUSTRIP_FOCUS2_ON_POPUP=15;
    static final int FRAME_ID_POPUP_BLUE=19;
    static final int FRAME_ID_POPUP_CENTER_BLUE=20;
    static final int FRAME_ID_POPUP_RED=26;
    static final int FRAME_ID_POPUP_CENTER_RED=27;
    static final int FRAME_ID_POPUP_GREEN=28;
    static final int FRAME_ID_POPUP_CENTER_GREEN=29;



}
