import java.io.InputStream;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
/*
 * GTantra.java
 *
 * Created on July 9, 2007, 5:13 PM
 *
 */

/**
 *
 * @author MumbaiGameFactory
 */

/** This is file is GTantra Engine which worked with GameJinni s/w.
 * (c) All Rights reserved
 * Language used: J2ME ( MIDP 2.0 )
 *
 */

/** How To use :
 *  To use this, you need to have .GT file ( Exported using GameJinni ).
 *  1) Then to load that file you need to pass the byte data of the file to Load method. ( to get the byte data of file you can use getFileByteData(String str) method.
 *  2) Then to drawing a frame you can directly use the DrawFrame method.
 *  3) similarly to draw animation you need to use render method and similarly all the methods are available.
 */
public class GTantra {

    private static final boolean _bDebug = true;
    /** Creates a new instance of GTantra */
    private static int version_no;
    private int _nModules;
    private int _nFrames;
    private int _nAniamtion;
    private int _nImagesUsed;
    private boolean _bIsCollisionInfo=false;
    // for modules
    public Image _module_images[][];
    static boolean processAlpha=false;
    private int _width[];
    private int _height[];
   // private int _iImageIndex[];
    // for frames info
    private int _iFramesModCnt[];
    private int _iFrameModules[][];
    private int _iFrameModX[][];
    private int _iFrameModY[][];
    // frame collision
    private int _iFrameCollX[];
    private int _iFrameCollY[];
    private int _iFrameCollWidth[];
    private int _iFrameCollHeight[];
    // for animation data
    private int _iAnimFrameCnt[];
    private int _iAnimFrames[][];
    private int _iAnimFrameX[][];
    private int _iAnimFrameY[][];
    /** For Image Formate */
    public static int _iImageFormate;
    final static int IMAGE_1=0X01;   // for 1 bit
    final static int IMAGE_4=0X02;   // for 2 bit
    final static int IMAGE_16=0X03;  // for 4 bit
    final static int IMAGE_256=0X04; // for 8 bit
    /** For pixel formate */
    public static int _iPixelFormate;
    final static int PIXEL_8888=0x00;
    final static int PIXEL_0888=0x01;
    final static int PIXEL_4444=0x02;
    final static int PIXEL_0333=0x03;
    final static int PIXEL_0555=0x04;
    final static int PIXEL_0565=0x05;
////-----------------------------

    static int colors[][] = new int[256][];
    static int _iNColors ;
    static int modulePixels[];
    byte module_flag[][];
    byte frame_flag[][];
    byte frameTimer[][];
    int localTimeCounter[][][];

   /// --- For Image Decoading-----------
    byte pixel_locations[];
    byte image_data[];
    int module_data_off[];
    int num_pal = 0;
    int crt_pal = 0;
    ////----- for GT drawing
    public final static byte FLAG_FLIP_X	= 0x01;
    public final static byte FLAG_FLIP_Y	= 0x02;
    int currentFrame[][];

    public GTantra() {
    }
    public void Load(byte file[],boolean doCaching)
    {

        try {


       int offset = 0;
       // reading version number

       version_no = file[offset++];

       if(_bDebug)
//               System.out.println("version_no:"+version_no);

       // reading pixel format
       _iPixelFormate = file[offset++];

       if(_bDebug)
//               System.out.println("_iPixelFormate:"+_iPixelFormate);

       // reading image format

       _iImageFormate = file[offset++];

       if(_bDebug)
//               System.out.println("_iImageFormate:"+_iImageFormate);

       // reading number of images used
      // _nImagesUsed = file[offset++];

      // if(_bDebug)
      //         System.out.println("_nImagesUsed:"+_nImagesUsed);

       // reading number of pals used
       num_pal  = ( file[offset++]  );

        if(_bDebug)
//               System.out.println("num_pal:"+num_pal);

       // reading collision info status

       if((file[offset++] & 0xFF) != 0)
       {
           _bIsCollisionInfo = true;

//           if(_bDebug)
//               System.out.println("Collision Info present..");
       }
       else
       {
           _bIsCollisionInfo = false;

//           if(_bDebug)
//               System.out.println("Collision Info is not present..");
       }

       // reading numaber of colors for all the images

       _iNColors = (file[offset++] & 0xFF);

//       if(_bDebug)
//          System.out.println(" Total Colors: "+_iNColors);

       num_pal = (num_pal == 0) ? 1 : num_pal;

       processAlpha = false;
       // reading color table
       for(int i=0; i < num_pal ; i++)
       {
//           System.out.println("COLOR TABEL FOR:"+i);
           colors[i] = new int[_iNColors];
           for (int j = 0; j <_iNColors; j++) {

                 if (_iPixelFormate == PIXEL_8888)
                 {
                    colors[i][j] = ((file[offset++] & 0xFF)) + ((file[offset++] & 0xFF)<<8) + ((file[offset++] & 0xFF)<<16) + ((file[offset++] & 0xFF)<< 24);

                    if(colors[i][j] == 0xFF00FF)
                    {
                        colors[i][j] = 0;
                        processAlpha = true;
                    }
                    else if(colors[i][j] == 0x000000)
                    {
                         colors[i][j] = 0x000001;
                    }
                    else
                    {
                          colors[i][j] = colors[i][j] * (-1) ;
                    }

                    colors[i][j] = (colors[i][j] & 0x00FFFFFF) * (-1);

//                    System.out.println("COLOR:"+ colors[i][j]);

                 }
                 else if (_iPixelFormate == PIXEL_0888)
                 {
                      colors[i][j] = ((file[offset++] & 0xFF)) + ((file[offset++] & 0xFF)<<8) + ((file[offset++] & 0xFF)<<16);

                    if(colors[i][j] == 0xFF00FF)
                    {
                        colors[i][j] =0;
                        processAlpha = true;
                    }
                    else if(colors[i][j] == 0x000000)
                    {
                         colors[i][j] = 0x000001;
                    }
                   else
                    {
                          colors[i][j] = colors[i][j] * (-1) ;
                    }

                    colors[i][j] = (colors[i][j] & 0x00FFFFFF) * (-1);

                 }

           }

       }

//       if(_bDebug)
//               System.out.println("Pixel Format:"+_iPixelFormate);

       // reading module numaber
       _nModules = (file[offset++] & 0xFF);

//        if(_bDebug)
//               System.out.println("_nModules: "+_nModules);

       // reading module width and height
       //_iImageIndex = new int[_nModules];
       _width = new int[_nModules];
       _height = new int[_nModules];
       for (int i = 0; i < _nModules; i++) {

           //_iImageIndex[i] = (file[offset++] & 0xFF);
           _width[i] = (file[offset++] & 0xFF);
           _height[i] = (file[offset++] & 0xFF);

//           if(_bDebug)
//               System.out.println("Width: "+_width[i]+" Height: "+_height[i]);
       }

       // reading frame info
       _nFrames = (file[offset++] & 0xFF);

//       if(_bDebug)
//               System.out.println("_nFrames: "+_nFrames);

       _iFramesModCnt = new int[_nFrames];
       _iFrameModules = new int[_nFrames][];
       _iFrameModX = new int[_nFrames][];
       _iFrameModY = new int[_nFrames][];

       _iFrameCollX= new int[_nFrames];
       _iFrameCollY= new int[_nFrames];
       _iFrameCollWidth= new int[_nFrames];
       _iFrameCollHeight= new int[_nFrames];
        module_flag = new byte[_nFrames][];
       for (int i = 0; i < _nFrames; i++) {

           _iFramesModCnt[i] =  (file[offset++] & 0xFF);

           _iFrameModules[i] = new int[_iFramesModCnt[i]];
           _iFrameModX[i]    = new int[_iFramesModCnt[i]];
           _iFrameModY[i]    = new int[_iFramesModCnt[i]];
           module_flag[i]    = new byte[_iFramesModCnt[i]];
           for (int j = 0; j < _iFramesModCnt[i] ; j++) {

               _iFrameModules[i][j] = (file[offset++] & 0xFF);
                _iFrameModX[i][j] = byteToIntWithSign((byte)(file[offset++] & 0xFF));
                _iFrameModY[i][j] = byteToIntWithSign((byte)(file[offset++] & 0xFF));
                module_flag[i][j] = (byte)(file[offset++] & 0xFF);
           }
           if(_bIsCollisionInfo)
           {
                _iFrameCollX[i] = byteToIntWithSign((byte)(file[offset++] & 0xFF));
                _iFrameCollY[i]= byteToIntWithSign((byte)(file[offset++] & 0xFF));
                _iFrameCollWidth[i]  = (file[offset++] & 0xFF);
                _iFrameCollHeight[i] = (file[offset++] & 0xFF);

           }
           else
           {
               _iFrameCollX = null;
               _iFrameCollY = null;
               _iFrameCollWidth = null;
               _iFrameCollHeight = null;
           }

       }

       // reading animation info
       _nAniamtion = (file[offset++] & 0xFF);

       _iAnimFrameCnt = new int[_nAniamtion];
       _iAnimFrames = new int[_nAniamtion][];
       _iAnimFrameX = new int[_nAniamtion][];
       _iAnimFrameY = new int[_nAniamtion][];
       frame_flag   = new byte[_nAniamtion][];
       frameTimer        = new byte[_nAniamtion][];
       for (int i = 0; i < _nAniamtion; i++) {

           _iAnimFrameCnt[i] = (file[offset++] & 0xFF);
           _iAnimFrameX[i] = new int[_iAnimFrameCnt[i]];
           _iAnimFrameY[i] = new int[_iAnimFrameCnt[i]];
           _iAnimFrames[i] = new int[_iAnimFrameCnt[i]];
           frame_flag[i]   = new byte[_iAnimFrameCnt[i]];
           frameTimer[i]   = new byte[_iAnimFrameCnt[i]];
           for (int j = 0; j < _iAnimFrameCnt[i]; j++) {

               _iAnimFrames[i][j] = (file[offset++] & 0xFF);
               _iAnimFrameX[i][j] = byteToIntWithSign((byte)(file[offset++] & 0xFF));
               _iAnimFrameY[i][j] = byteToIntWithSign((byte)(file[offset++] & 0xFF));
               frame_flag[i][j] = (byte)(file[offset++] & 0xFF);
               frameTimer[i][j] = (byte)(file[offset++] & 0xFF);

           }
       }
       _module_images = new Image[num_pal][_nModules];

       // reading image data
       int image_data_length = 0;
       int mod_data_length = 0;
       module_data_off = new int[_nModules];
        for(int x = 0 ; x < _nModules ; x++)
        {
            mod_data_length = _width[x]*_height[x];
            if(_iImageFormate == IMAGE_1)
            {
                mod_data_length = ( mod_data_length >> 3 );
               if ((_width[x]*_height[x]) % 8 != 0)
               {
                   mod_data_length ++;
               }
            }
             else if(_iImageFormate == IMAGE_4)
            {
                 mod_data_length = ( mod_data_length >> 2);
                if ((_width[x]*_height[x]) % 4 != 0)
               {
                     mod_data_length ++;
               }
            }
            else if(_iImageFormate == IMAGE_16)
            {
                mod_data_length = ( mod_data_length >> 1 );
                if ((_width[x]*_height[x]) % 2 != 0)
               {
                     mod_data_length ++;
               }
            }
            else if(_iImageFormate == IMAGE_256)
            {

            }
            module_data_off[x] = mod_data_length;
            image_data_length += mod_data_length;
        }
       image_data = new byte[image_data_length];
       for(int x =0 ; x <image_data_length ; x++)
       {
           image_data[x] = file[offset++];
       }
       // trying to make images
       //SKP

       /*  int pixels[] = new int [ _iImageLength ] ;
         // getting the actual colors pixels
         for(int i=0 ; i< _iImageLength ; i++)
         {
            //System.out.println("pixels [ "+i+" ] "+pixel_locations[i]);
            pixels[i] = colors[_iImageIndex[x]][pixel_locations[i]] * (-1);
         }

         if(_bDebug)
           System.out.println("pixels length: "+pixels.length+" _width[x]: "+_width[x]+"_height[x]: "+_height[x] +"processAlpha: "+processAlpha);

         _module_images[x] = Image.createRGBImage(pixels,_width[x],_height[x],processAlpha);*/




      }catch(Exception e)
     {
           e.printStackTrace();
     }
        file = null;
        if(doCaching)
        {
            for(int i= 0 ; i < num_pal ; i++)
            {
                BuildImages(i);
            }

             freeImageData();
        }
        animInitilazation();
    }
    private int[] RetriveImageData(int pal , int moduleId , int flags)
    {

         // getting the pixel data
         int _iImageLength = _width[moduleId] * _height[moduleId];
         pixel_locations = new byte [ _iImageLength ];
         byte _bReadingByte=0;
         int _iShiftCounter=0;
         int _ILocationCounter=0;
         int offset = 0;
         for(int i=0 ; i< moduleId ; i++)
         {
             offset += module_data_off[i];
         }
         if(_iImageFormate == IMAGE_1)
         {
             for(int i=0 ; i< _iImageLength ; i++)
             {
                _bReadingByte=(byte)(( image_data[offset++] & 0xFF ));
                //System.out.println("_bReadingByte:"+_bReadingByte);
                 pixel_locations[i++] = (byte)( (_bReadingByte >> 0 ) & 0x01  );
                 if( i < _iImageLength )
                 pixel_locations[i++] = (byte)( (_bReadingByte >> 1 ) & 0x01  );
                 if( i < _iImageLength )
                 pixel_locations[i++] = (byte)( (_bReadingByte >> 2 ) & 0x01  );
                 if( i < _iImageLength )
                 pixel_locations[i++] = (byte)( (_bReadingByte >> 3 ) & 0x01  );
                 if( i < _iImageLength )
                 pixel_locations[i++] = (byte)( (_bReadingByte >> 4 ) & 0x01  );
                 if( i < _iImageLength )
                 pixel_locations[i++] = (byte)( (_bReadingByte >> 5 ) & 0x01  );
                 if( i < _iImageLength )
                 pixel_locations[i++] = (byte)( (_bReadingByte >> 6 ) & 0x01  );
                 if( i < _iImageLength )
                 pixel_locations[i  ] = (byte)( (_bReadingByte >> 7 ) & 0x01  );
            }

        }
        else if(_iImageFormate == IMAGE_4)
        {
             for(int i=0 ; i< _iImageLength ; i++)
             {
                _bReadingByte=(byte)(( image_data[offset++] & 0xFF ));
                //System.out.println("_bReadingByte:"+_bReadingByte);
                 pixel_locations[i++] = (byte)( (_bReadingByte >> 0 ) & 0x3  );
                 if( i < _iImageLength )
                 pixel_locations[i++] = (byte)( (_bReadingByte >> 2 ) & 0x3  );
                 if( i < _iImageLength )
                 pixel_locations[i++] = (byte)( (_bReadingByte >> 4 ) & 0x3  );
                 if( i < _iImageLength )
                 pixel_locations[i  ] = (byte)( (_bReadingByte >> 6 ) & 0x3  );
            }

        }
        else if(_iImageFormate == IMAGE_16)
        {
             for(int i=0 ; i< _iImageLength ; i++)
             {
                _bReadingByte=(byte)(( image_data[offset++] & 0xFF ));
                //System.out.println("_bReadingByte:"+_bReadingByte);
                 pixel_locations[i++] = (byte)( (_bReadingByte >> 0 ) & 0x0F  );
                 if( i < _iImageLength )
                 pixel_locations[i  ] = (byte)( (_bReadingByte >> 4 ) & 0x0F  );

            }

        }
        else if(_iImageFormate == IMAGE_256)
        {
             for(int i=0 ; i< _iImageLength ; i++)
             {
                 _bReadingByte=(byte)(( image_data[offset++] & 0xFF ));
                 pixel_locations[i] = (byte)(( _bReadingByte ) & 0xFF );
             }
         }

          int pixels[] = new int [_width[moduleId] * _height[moduleId]];


          for(int i=0 ; i< pixels.length ; i++)
          {
             // if(this == CGame._GIngameComponent)
              // System.out.println("i:"+ i +"colors:"+colors.length+"pixel_locations:"+pixel_locations.length+"pixel_locations[i]:"+pixel_locations[i]+"module id:"+moduleId);
              pixels[i] = colors[pal][pixel_locations[i] & 0xFF] ;

          }

          pixel_locations = null;

          int i, j, t, t2, j2,clr;

            if ((flags & FLAG_FLIP_X) != 0)
            {
                    t2 = _width[moduleId] * _height[moduleId];
                    t  = _width[moduleId]  >> 1;
                    for(i = 0; i < t2; i += _width[moduleId])
                    {

                        for(j = 0, j2 = _width[moduleId]-1; j < t; j++, j2--)
                        {
                                clr = pixels[i + j];
                                pixels[i + j] = pixels[i + j2];
                                pixels[i + j2] = clr;
                        }
                    }
            }
            if ((flags & FLAG_FLIP_Y) != 0)
            {
                    for(i = 0, t = 0, t2 = _width[moduleId]*(_height[moduleId]-1); i < (_height[moduleId]>>1); i++, t += _width[moduleId], t2 -= _width[moduleId])
                    {

                        for(j = 0; j < _width[moduleId]; j++)
                        {
                                clr = pixels[t + j];
                                pixels[t + j] = pixels[t2 + j];
                                pixels[t2 + j] = clr;
                        }
                    }
            }
         return pixels;
    }
    public void BuildImages(int pal)
    {

//            System.out.println("FOR IMG :"+pal);
//         int[] pixel =  RetriveImageData(pal,0,0);
//
//          for(int x = 0 ; x < pixel.length ; x++)
//                   System.out.println("pixel:"+pixel[x]);

         for(int x = 0 ; x < _nModules ; x++)
         {

             _module_images[pal][x] = Image.createRGBImage( RetriveImageData(pal,x,0),_width[x],_height[x],processAlpha);
             System.gc();
         }


    }
    public void freeImageData()
    {
        pixel_locations = null;
        image_data = null;
        module_data_off = null;
        System.gc();
    }
    private int byteToIntWithSign(byte val)
    {
         int j,num;
         j=val&0x80;
         j= j >>7;
         val = (byte)(val & 0x7f);
         if(j==1)
             num=((int)val)*(-1);
         else
             num=((int)val);
         return (num);
    }

    void DrawAnimation(Graphics g, int animation, int posX, int posY, int flags)
    {
          for (int animFrame = 0; animFrame < _iAnimFrameCnt[animation]; animFrame++)
            DrawAnimationFrame (g, animation, animFrame, posX, posY, flags);
    }

    void animInitilazation()
    {
        currentFrame = new int[_nAniamtion][1];
        localTimeCounter = new int[_nAniamtion][][];
        for(int i=0 ; i<_nAniamtion ; i++)
        {
            currentFrame[i][0] = 0;
            localTimeCounter = new int[_nAniamtion][_iAnimFrameCnt[i]][1];
            for(int j= 0 ; j<_iAnimFrameCnt[i] ; j++)
            {
                localTimeCounter[i][j][0] = 0;
            }
        }


    }

    void setAnimationObjects(int animId , int value)
    {
        currentFrame[animId] = new int[value];
        for(int i = 0 ; i < value ; i++)
        {
            currentFrame[animId][i] = 0;

        }
         for(int j = 0 ; j < _iAnimFrameCnt[animId] ; j ++)
            localTimeCounter[animId][j] = new int[value];
    }
    void render(Graphics g,int animation,int object, int posX, int posY, int flags,boolean loop)
    {
       // System.out.println("=========frame counter===="+_iAnimFrameCnt[animation] +"Frame Timer:"+ frameTimer[animation][currentFrame[animation]]);
        if(_iAnimFrameCnt[animation] == 0)
            return;

        int currentFrameTime = frameTimer[animation][currentFrame[animation][0]];

        if(currentFrameTime != 0)
        {

             DrawAnimationFrame(g,animation,currentFrame[animation][object],posX,posY,flags);
        }


        localTimeCounter[animation][currentFrame[animation][object]][object]++;


         if(localTimeCounter[animation][currentFrame[animation][object]][object] >= currentFrameTime)
         {
            if( currentFrame[animation][object] < _iAnimFrameCnt[animation] )
            {
                currentFrame[animation][object]++ ;
            }
            if(loop && currentFrame[animation] [object]== (_iAnimFrameCnt[animation] ))
            {
                currentFrame[animation][object] = 0;
            }
            localTimeCounter[animation][currentFrame[animation][object]][object] = 0;
         }
    }
    void render(Graphics g, int animation, int posX, int posY, int flags,boolean loop)
    {
       // System.out.println("=========frame counter===="+_iAnimFrameCnt[animation] +"Frame Timer:"+ frameTimer[animation][currentFrame[animation]]);
        if(_iAnimFrameCnt[animation] == 0)
            return;

        int currentFrameTime = frameTimer[animation][currentFrame[animation][0]];

        if(currentFrameTime != 0)
        {

           DrawAnimationFrame(g,animation,currentFrame[animation][0],posX,posY,flags);
        }


        localTimeCounter[animation][currentFrame[animation][0]][0] ++;

         if(localTimeCounter[animation][currentFrame[animation][0]][0] >= currentFrameTime)
         {
            if( currentFrame[animation][0] < _iAnimFrameCnt[animation] )
            {
                currentFrame[animation][0]++ ;
            }
            if(loop && currentFrame[animation] [0]== (_iAnimFrameCnt[animation] ))
            {
                currentFrame[animation][0] = 0;
            }
            localTimeCounter[animation][currentFrame[animation][0]][0] = 0;
         }
    }
    void DrawAnimationFrame(Graphics g, int animation,int _aframe, int posX, int posY, int flags)
    {

        int frame = _iAnimFrames[animation][_aframe];

        if ((flags & FLAG_FLIP_X) != 0)
            posX -= _iAnimFrameX[animation][_aframe];
        else
            posX += _iAnimFrameX[animation][_aframe];
        if ((flags & FLAG_FLIP_Y) != 0)
            posY -= _iAnimFrameY[animation][_aframe];
        else
            posY += _iAnimFrameY[animation][_aframe];

       /* if ((flags & FLAG_FLIP_X) != 0)
            posX -= _width[module]&0xFF;
        if ((flags & FLAG_FLIP_Y) != 0)
            posY -= _height[module]&0xFF;*/
        DrawFrame (g,frame, posX, posY, flags ^ (frame_flag[animation][_aframe]&0x0F));
    }
    void DrawFrame (Graphics g, int frame, int posX, int posY, int flags)
    {
       for (int frameModule = 0; frameModule < _iFramesModCnt[frame]; frameModule++)
            DrawFrameModule (g, frame, frameModule, posX, posY, flags);
    }
    void DrawFrameModule (Graphics g, int frame, int frameModule, int posX, int posY, int flags)
    {
        int fm_flags = 0;
        int module = _iFrameModules[frame][frameModule];

        if ((flags & FLAG_FLIP_X) != 0)
             posX -= _iFrameModX[frame][frameModule];
        else
            posX += _iFrameModX[frame][frameModule];
        if ((flags & FLAG_FLIP_Y) != 0)
            posY -= _iFrameModY[frame][frameModule];
        else
            posY += _iFrameModY[frame][frameModule];

        if ((flags & FLAG_FLIP_X) != 0)
            posX -= _width[module]&0xFF;
        if ((flags & FLAG_FLIP_Y) != 0)
            posY -= _height[module]&0xFF;

         DrawModule (g, module, posX, posY, flags ^ (module_flag[frame][frameModule]&0x0F));
    }

    public void DrawModule(Graphics g,int module_id,int posX, int posY,int flag)
    {
        int sizeX = _width[module_id]&0xFF;
        int sizeY = _height[module_id]&0xFF;

        final int x = 0;
        final int y = 0;

        Image img = _module_images[crt_pal][module_id];
        if(img == null)
        {

            g.drawRGB( RetriveImageData(crt_pal, module_id ,flag), 0, sizeX, posX, posY, sizeX, sizeY, processAlpha);
            return;
        }

        if ((flag & FLAG_FLIP_X) != 0)
        {
            if ((flag & FLAG_FLIP_Y) != 0)
            {
                g.drawRegion (img, x, y, sizeX, sizeY, Sprite.TRANS_ROT180, posX, posY, 0);
            }
            else
            {
                g.drawRegion (img, x, y, sizeX, sizeY, Sprite.TRANS_MIRROR, posX, posY, 0);
           }
        }
        else if ((flag & FLAG_FLIP_Y) != 0)
        {
              g.drawRegion (img, x, y, sizeX, sizeY, Sprite.TRANS_MIRROR_ROT180, posX, posY, 0);
        }
        else
        {
              g.drawRegion (img, x, y, sizeX, sizeY, Sprite.TRANS_NONE, posX, posY, 0);
        }

    }
    public int[] getFrameRect(int frame , int posX , int posY ,int arry[])
    {
        int X=getSmallNumber(_iFrameModX[frame]);
        int Y=getSmallNumber(_iFrameModY[frame]);
        int temp1=0;
        int temp2=0;
        temp1=_iFrameModX[frame][0]+ _width[_iFrameModules[frame][0]];
        temp2=_iFrameModY[frame][0]+ _height[_iFrameModules[frame][0]];
        for(int i=0;i<_iFramesModCnt[frame];i++)
        {
            if(temp1<(_iFrameModX[frame][i]+_width[_iFrameModules[frame][i]]))
            {
                temp1=_iFrameModX[frame][i]+_width[_iFrameModules[frame][i]];
            }
            if(temp2<(_iFrameModY[frame][i]+_height[_iFrameModules[frame][i]]))
            {
                temp2=_iFrameModY[frame][i]+_height[_iFrameModules[frame][i]];
            }

        }
        arry[0] = X + posX;
        arry[1] = Y + posY;
        arry[2] =  (temp1-X);
        arry[3] =  (temp2-Y);
        return arry;

}
public int[] getCollisionRect(int frameId, int[] tmp)
{
    tmp[0] =  _iFrameCollX[frameId];
    tmp[1] =  _iFrameCollY[frameId];
    tmp[2] = _iFrameCollWidth[frameId];
    tmp[3] = _iFrameCollHeight[frameId];

    return tmp;
}
 public int getFrameWidth(int frame)
 {

        int X=getSmallNumber(_iFrameModX[frame]);

        int temp1=0;
        temp1=_iFrameModX[frame][0]+ _width[_iFrameModules[frame][0]];

        for(int i=0;i<_iFramesModCnt[frame];i++)
        {
            if(temp1<(_iFrameModX[frame][i]+_width[_iFrameModules[frame][i]]))
            {
                temp1=_iFrameModX[frame][i]+_width[_iFrameModules[frame][i]];
            }

        }
        return (temp1-X);

  }
 public int getFrameHeight(int frame)
 {


        int Y=getSmallNumber(_iFrameModY[frame]);
        int temp2=0;

        temp2=_iFrameModY[frame][0]+ _height[_iFrameModules[frame][0]];
        for(int i=0;i<_iFramesModCnt[frame];i++)
        {
            if(temp2<(_iFrameModY[frame][i]+_height[_iFrameModules[frame][i]]))
            {
                temp2=_iFrameModY[frame][i]+_height[_iFrameModules[frame][i]];
            }
        }

        return (temp2-Y);
  }
 public void setCurrentPallete(int pal)
 {
     crt_pal = pal;
 }
    protected int getSmallNumber(int arry[])
    {
        int temp=0;
        int temp2=0;
        if(arry.length!=0)
        {
            temp=arry[0];
            for(int i=0;i<arry.length;i++)
            {
                if(temp>arry[i])
                {
                    temp=arry[i];

                }
            }
        }
        return temp;
    }

    ////////////////// DRAW STRING SYSTEM //////////////////////

    public void LoadFont(byte[] file , String char_arry ,boolean caching)
    {
        this.map_char_arry = char_arry;
        this.Load(file, caching);
        _iSpaceCharWidth   = this._width[0];
        _iCharCommanHeight = ( this._height[0] + EXTRA_SPACE_HEIGHT );
    }
    public void LoadFont(byte[] file , String char_arry , int spaceWidth , int charHeight , boolean caching)
    {
        this.map_char_arry = char_arry;
        this.Load(file, caching);
        _iSpaceCharWidth   = spaceWidth;
        _iCharCommanHeight = (charHeight + EXTRA_SPACE_HEIGHT );
    }
    public String map_char_arry = "";
    public static final int TEXT_LEFT    = 0x1;
    public static final int TEXT_RIGHT   = 0x2;
    public static final int TEXT_VCENTER = 0x3;
    public static final int TEXT_HCENTER = 0x4;
    public static final int TEXT_VCENTER_HCENTER = (TEXT_VCENTER |  TEXT_HCENTER );

    public int _iSpaceCharWidth   = 0;
    public int _iCharCommanHeight = 0;

    public static final int FONT_FRAME_ID = 0;

    public static int EXTRA_SPACE_WIDTH = 0;
    public static int EXTRA_SPACE_HEIGHT = 1;
    public int getCharHeight()
    {
        return _iCharCommanHeight;
    }

    public int getCharWidth(char c)
    {
        if((int)c == 94 )
        {
            return 0;
        }
        if((int)c == 32 )
        {
            return _iSpaceCharWidth;
        }
        int index =  map_char_arry.indexOf(c);
        if( index < 0 || index >= _iFramesModCnt[FONT_FRAME_ID])
        {
            System.err.println("Invalid Charactor in GetCharWidth");
            return -1;
        }
        index = _iFrameModules[FONT_FRAME_ID][index];
        return (_width[index] + EXTRA_SPACE_WIDTH);
    }
    public int getStringWidth(String str)
    {
        int length = 0;
        for(int i=0 ; i< str.length() ; i++)
        {
            length += getCharWidth(str.charAt(i));
        }
        return length;
    }
    public void drawString(Graphics g, String str ,int posX , int posY , int anchor)
    {
        if(anchor == TEXT_RIGHT)
        {
            posX -= getStringWidth(str);
        }
        if(anchor == TEXT_VCENTER || anchor == TEXT_VCENTER_HCENTER)
        {
            posX -= (getStringWidth(str) >> 1);
        }
        if(anchor == TEXT_HCENTER || anchor == TEXT_VCENTER_HCENTER)
        {
            posY -= (_iCharCommanHeight >> 1);
        }

         for(int i=0 ; i< str.length() ; i++)
         {
             if((int) str.charAt(i) == 32)
             {
                  posX += _iSpaceCharWidth;
              }
             else
             {
                  int index =  map_char_arry.indexOf(str.charAt(i));
                  if( index < 0 || index >= _iFramesModCnt[FONT_FRAME_ID] )
                  {
                        System.err.println("Invalid Charactor In Draw String");
                        return ;
                  }
                  DrawFrameModule (g, FONT_FRAME_ID, index , posX, posY, 0)  ;
                  posX +=  getCharWidth(str.charAt(i));
             }
         }
    }
    private static int MAX_LINES_ALLOWED = 60;
    private static char pageChars[][];
    private static int numberOfLines;
    private static int line_width[] = new int[MAX_LINES_ALLOWED];
    public void drawPage(Graphics g, String str ,int posX , int posY , int width , int height , int anchor)
    {
        try {

        pageChars = new char[MAX_LINES_ALLOWED][];
        int tmpWidth = 0;
        StringBuffer tmpStr = new StringBuffer("");
        StringBuffer lineStr = new StringBuffer("");
        numberOfLines = 0;

        for(int i=0 ; i< str.length() ; i++)
        {

            tmpWidth += getCharWidth(str.charAt(i));

            if( ((tmpWidth < width ) ||  (int)str.charAt(i) == 32 ) && (int)str.charAt(i) != 94)
            {
                tmpStr.append(str.charAt(i));
                if((char)str.charAt(i) == 32 )
                {
                     lineStr.append(tmpStr);
                     tmpStr.delete(0 , tmpStr.length());

                }
            }
            else
            {
                if((int)str.charAt(i) == 94)
                {
                      tmpStr.append((char)94);
                      lineStr.append(tmpStr);
                      tmpStr.delete(0 , tmpStr.length());
                }
                tmpWidth = 0;
                if(lineStr.length() == 0)
                {
                   break;
                }
                if ( (int)lineStr.charAt(lineStr.length() - 1) == 32 )
                {
                    lineStr.delete(lineStr.length() - 1 , lineStr.length() );
                }
                if ( (int)lineStr.charAt(lineStr.length() - 1) == 94 )
                {
                    lineStr.delete(lineStr.length() - 1 , lineStr.length() );
                }

                pageChars[numberOfLines] = new char[lineStr.length()];


                for(int x= 0 ; x <lineStr.length() ; x++ )
                {
                     pageChars[numberOfLines][x] = lineStr.charAt(x);
                }

                line_width[numberOfLines] = getStringWidth(lineStr.toString());
                numberOfLines++;

                lineStr.delete(0 , lineStr.length() );
                if((int)str.charAt(i) != 94)
                {
                    i -= (tmpStr.length() + 1);
                }
                else
                {
                    i -= (tmpStr.length() );
                }
                tmpStr.delete(0 , tmpStr.length() );
            }
        }
        lineStr.append(tmpStr);
        if(lineStr.length() > 0 )
        {
             if ( (int)lineStr.charAt(lineStr.length() - 1) == 32 )
            {
                lineStr.delete(lineStr.length() - 1 , lineStr.length() );
            }

            line_width[numberOfLines] = getStringWidth(lineStr.toString());

            pageChars[numberOfLines] = new char[lineStr.length()];
            for(int x= 0 ; x <lineStr.length() ; x++ )
            {
                 pageChars[numberOfLines][x] = lineStr.charAt(x);
            }
            numberOfLines ++;
        }

//        for(int i= 0 ; i < numberOfLines ; i++)
//        {
//            for(int j= 0 ; j < pageChars[i].length ; j++)
//                System.out.print(""+pageChars[i][j]);
//
//             System.out.println("");
//        }


        int line_y  = posY;

        for(int i=0 ; i< numberOfLines ; i++)
        {
            int line_x  = posX;

            if( anchor == TEXT_RIGHT )
            {
                 line_x +=  (width - line_width[i]);
            }
            if(anchor == TEXT_HCENTER || anchor ==  TEXT_VCENTER_HCENTER)
            {
                 line_x +=  ((width - line_width[i]) >> 1 );
            }
            if( anchor == TEXT_VCENTER || anchor ==  TEXT_VCENTER_HCENTER)
            {
                 if( i  < ( numberOfLines >> 1))
	      	{
	      		line_y = (posY + ( (height + _iCharCommanHeight ) >> 1 )) - (_iCharCommanHeight *  ((numberOfLines >> 1 ) - i  ));
	      	}
		else
		{
		 	line_y = (posY + ((height + _iCharCommanHeight ) >> 1 )) + (_iCharCommanHeight * ( i - (numberOfLines >> 1 ) ) );
		}
                 line_y -= (_iCharCommanHeight >> 1);

            }
            for(int j=0 ; j< pageChars[i].length ; j++)
            {
                if((int) pageChars[i][j] == 32)
                {
                  line_x += _iSpaceCharWidth;
                }
                 else
                 {
                     int index =  map_char_arry.indexOf(pageChars[i][j]);

                      if(( index < 0 || index >= _iFramesModCnt[FONT_FRAME_ID] ) && (int) pageChars[i][j] != 94)
                      {
//                            System.err.println("Invalid Charactor In Draw Page:"+(char)( pageChars[i][j])+"index:"+index+"_iFramesModCnt[FONT_FRAME_ID]:"+_iFramesModCnt[FONT_FRAME_ID]);
                            return ;
                      }
                     if((int) pageChars[i][j] != 94)
                          DrawFrameModule (g, FONT_FRAME_ID, index , line_x , line_y , 0)  ;
                     line_x +=  getCharWidth(pageChars[i][j]);
                 }
            }

            line_y += _iCharCommanHeight;
        }

        tmpStr  = null;
        lineStr = null;

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static byte[] getFileByteData(String path)
    {
        int file_size = 0;
        byte buffer[]  = new byte[file_size];
        try{
            InputStream _is = "".getClass().getResourceAsStream(path);
            file_size = _is.available();

            buffer = new byte[file_size];
            for(int i= 0 ; i< file_size ; i++)
            {
              buffer[i] = (byte)(_is.read() & 0xFF);
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return buffer;
    }
}