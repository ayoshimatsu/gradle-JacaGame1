package com.ayoshimatsu;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.applet.*;

public class KImage {
   private static Applet applet = null;
   private static Container con = null;

   public static void setApplet(Applet applet){
      KImage.applet = applet;
   }

   public static void setContainer(Container con){
      KImage.con = con;
   }

   public static BufferedImage getImage(String fileName) throws IOException {
      if(applet != null){
         InputStream is = KImage.applet.getClass().getResourceAsStream(fileName);
         BufferedImage bi = ImageIO.read(is);
         is.close();
         return bi;
      }else{
         InputStream is = KImage.con.getClass().getResourceAsStream(fileName);
         BufferedImage bi = ImageIO.read(is);
         is.close();
         return bi;
      }
   }
}