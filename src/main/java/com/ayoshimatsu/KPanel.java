package com.ayoshimatsu;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class KPanel extends JPanel{
   private KCtrl kCtrl = null;
   private Font fontS = new Font("Georgia", Font.PLAIN, 11);

   public ArrayList<KSpriteGroup> kSpriteGroupLst = null;

   private ArrayList<ArrayList<KSprite>> kSpriteLayoutLst = null;
   private int kSpriteLayoutCount = 0;

   public HashMap<String, KText> kTextLst = null;
   private boolean[] keyPressTbl = null;
   private java.util.Timer timerThis = null;

   public KPanel(KCtrl kCtrl){
      super();
      this.kCtrl = kCtrl;
      init();
   }

   public void init(){
      keyPressTbl = new boolean[256];
      this.addKeyListener(new KPanelKeyAdapter());
      kSpriteGroupLst = new ArrayList<KSpriteGroup>(100);
      timerThis = new java.util.Timer();
   }

   public void setLayoutCount(int layoutCount){
      kSpriteLayoutCount = layoutCount;
      kSpriteLayoutLst = new ArrayList<ArrayList<KSprite>>(layoutCount);

      for(int i = 0; i < layoutCount; i++){
         ArrayList<KSprite> kSpriteLst = new ArrayList<KSprite>(100);
         kSpriteLayoutLst.add(kSpriteLst);
      }
      kTextLst = new HashMap<String, KText>();
   }

   public void initKSpriteLstInLayout(int layout){
      ArrayList<KSprite> kSpriteLst = new ArrayList<KSprite>(100);
      kSpriteLayoutLst.set(layout, kSpriteLst);
   }

   public void initSpriteGroup(){
      this.kSpriteGroupLst = new ArrayList<KSpriteGroup>(100);
   }

   public void start(){
      timerThis.scheduleAtFixedRate(new TimerActionListener(), 1000l, 16l);
   }

   public void run(){
      kCtrl.run();

      int countSpriteGroupLst = kSpriteGroupLst.size();

      for(int i = 0; i < countSpriteGroupLst; i++){
         KSpriteGroup spriteGroup = kSpriteGroupLst.get(i);
         if(spriteGroup != null){
            spriteGroup.forward();
            spriteGroup.run();
         }
      }

      if(countSpriteGroupLst > 100){
         for(int i = 0; i < countSpriteGroupLst; i++){
            KSpriteGroup spriteGroup = kSpriteGroupLst.get(i);
            if(spriteGroup == null){
               kSpriteGroupLst.remove(i);
               i--;
               countSpriteGroupLst--;
            }
         }
      }

      for(int i = 0; i < kSpriteLayoutCount; i++){
         ArrayList<KSprite> kSpriteLst = (ArrayList<KSprite>)kSpriteLayoutLst.get(i);
         int kSpriteLstCount = kSpriteLst.size();
         for(int j = 0; j < kSpriteLstCount; j++){
            KSprite kSprite = (KSprite)kSpriteLst.get(j);
            if(kSprite != null){
               kSprite.run();
            }
         }
      }

      Object[] objKeys = kTextLst.keySet().toArray();

      for(int i = 0; i < objKeys.length; i++){
         KText kText = (KText)kTextLst.get((String)objKeys[i]);
         if(kText != null){
            kText.run();
         }
      }

      repaint();

      for(int i = 0; i < kSpriteLayoutCount; i++){
         ArrayList<KSprite> kSpriteLst = (ArrayList<KSprite>)kSpriteLayoutLst.get(i);
         int kSpriteLstCount = kSpriteLst.size();
         if(kSpriteLstCount > 200){
            kSpriteLstCount = 100;

            for(int j = 0; j < kSpriteLstCount; j++){
               KSprite kSprite = (KSprite)kSpriteLst.get(j);
               if(kSprite == null){
                  kSpriteLst.remove(j);
                  j--;
                  kSpriteLstCount--;
               }
            }
         }
      }
   }

   public void paintComponent(Graphics g){
      for(int i = 0; i < kSpriteLayoutCount; i++){
         ArrayList<KSprite> kSpriteLst = (ArrayList<KSprite>)kSpriteLayoutLst.get(i);
         for(int j = 0; j < kSpriteLst.size(); j++){
            KSprite kSprite = kSpriteLst.get(j);
            if(kSprite != null){
               kSprite.paint(g);
            }
         }
      }

      Object[] objKeys = kTextLst.keySet().toArray();

      for(int i = 0; i < objKeys.length; i++){
         KText kText = (KText)kTextLst.get((String)objKeys[i]);
         if(kText != null){
            kText.paint(g);
         }
      }
   }

   public void addSpriteGroup(KSpriteGroup spriteGroup){
      kSpriteGroupLst.add(spriteGroup);
   }

   public void removeSpriteGroup(KSpriteGroup spriteGroup){
      int index = kSpriteGroupLst.indexOf(spriteGroup);
      if(index != -1){
         kSpriteGroupLst.set(index, null);
      }
   }

   public void addSprite(int layout, KSprite kSprite){
      ArrayList<KSprite> kSpriteLst = (ArrayList<KSprite>)kSpriteLayoutLst.get(layout);
      kSpriteLst.add(kSprite);
   }

   public void removeSprite(int layout, KSprite kSprite){
      ArrayList<KSprite> kSpriteLst = (ArrayList<KSprite>)kSpriteLayoutLst.get(layout);
      int index = kSpriteLst.indexOf(kSprite);

      if(index != -1){
         kSpriteLst.set(index, null);
      }
   }

   public void removeSprite(KSprite kSprite){
      for(int i = 0; i < kSpriteLayoutCount; i++){
         removeSprite(i, kSprite);
      }
   }

   public ArrayList<KSprite> getSpriteLst(int layout){
      ArrayList<KSprite> kSpriteLst = (ArrayList<KSprite>)kSpriteLayoutLst.get(layout);
      return kSpriteLst;
   }

   public void addText(String strKey, KText kText){
      kTextLst.put(strKey, kText);
   }

   public void removeText(String strKey) {
      kTextLst.remove(strKey);
   }

   public KCtrl getKCtrl(){
      return kCtrl;
   }

   public boolean isKeyCodePressed(int keyCode){
      return keyPressTbl[keyCode];
   }

   private class KPanelKeyAdapter extends KeyAdapter{
      public void keyPressed(KeyEvent ke){
         int code = ke.getKeyCode();
         if(code < 256){
            keyPressTbl[code] = true;
         }
      }

      public void keyReleased(KeyEvent ke){
         int code = ke.getKeyCode();
         if(code < 256){
            keyPressTbl[code] = false;
         }
      }
   }

   private class TimerActionListener extends TimerTask {
      public void run(){
         KPanel.this.run();
      }
   }
}
