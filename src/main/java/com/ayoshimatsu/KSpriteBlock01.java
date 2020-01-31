package com.ayoshimatsu;

import java.awt.*;

public class KSpriteBlock01 extends KSpriteBlock00 implements KConstant {
   private			int		color			= -1;
   private			int		deleteStage		= -1;

   public KSpriteBlock01(KPanel panel){
      super(panel);
   }

   public KSpriteBlock01(KPanel panel, int color){
      super(panel);
      this.color = color;
      this.setImage(color);
   }

   public void run(){
      super.run();
   }

   public void paint(Graphics g){
      this.setImage(color);
      super.paint(g);
      if(deleteStage != -1){
         this.setImage(KSpriteBlock00.class, deleteStage);
         super.paint(g);
      }
   }

   public int getColor(){
      return color;
   }

   public int getDeleteStage(){
      return this.deleteStage;
   }

   public void setDeleteStage(int deleteStage){
      this.deleteStage = deleteStage;
   }
}