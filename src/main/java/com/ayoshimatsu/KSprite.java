package com.ayoshimatsu;

import java.awt.*;
import java.awt.image.*;
import java.util.*;

abstract public class KSprite extends KSpriteObject {
   protected KPanel panel = null;
   private static HashMap<Class, BufferedImage[]>imageThiss = new HashMap<Class, BufferedImage[]>();
   private BufferedImage imageThis = null;

   protected int intX = 0;
   protected int intY = 0;

   private Color colorBackground = Color.black;

   public KSprite(KPanel panel){
      super();
      this.panel = panel;
      this.imageThis = imageThiss.get(this.getClass())[0];
   }

   public static void setImage(Class theClass, BufferedImage image){
      KSprite.setImage(theClass, new BufferedImage[]{image});
   }

   public static void setImage(Class theClass, BufferedImage[] images){
      KSprite.imageThiss.put(theClass, images);
   }

   public void paint(Graphics g){
      g.drawImage(this.imageThis, intX, intY, getWidth(), getHeight(), panel);
   }

   abstract public void run();

   public int getX(){
      return this.intX;
   }

   public void setX(int x){
      this.intX = x;
   }

   public int getY(){
      return this.intY;
   }

   public void setY(int y){
      this.intY = y;
   }

   public void setImage(int imagePhase){
      this.imageThis = imageThiss.get(this.getClass())[imagePhase];
   }

   public void setImage(Class theClass, int imagePhase){
      this.imageThis = imageThiss.get(theClass)[imagePhase];
   }

   public int getWidth(){
      return imageThiss.get(this.getClass())[0].getWidth(panel);
   }

   public int getHeight(){
      return imageThiss.get(this.getClass())[0].getHeight(panel);
   }

   public void removeSprite(){
      panel.removeSprite(this);
   }

   public KPanel getKPanel(){
      return panel;
   }
}