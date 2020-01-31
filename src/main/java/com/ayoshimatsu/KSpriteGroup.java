package com.ayoshimatsu;

abstract public class KSpriteGroup extends KSpriteObject {
   protected KPanel panel = null;
   public		int		time		= -1;

   public KSpriteGroup(KPanel panel){
      this.panel = panel;
   }

   abstract public void run();

   public void forward(){
      time++;
   }

   public KPanel getKPanel(){
      return panel;
   }
}
