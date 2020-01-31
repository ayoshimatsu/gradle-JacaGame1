package com.ayoshimatsu;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import java.util.*;

public class KCtrlPZL extends KCtrl implements KConstant {
   private final String RESOURCE_PATH = "D:/IdeaProjects/BuildTest/gradle-intelliJ-JavaGame1";

   private final int PHASE_INIT		= 0;
   private final int PHASE_TITLE		= 1;
   private final int PHASE_STAGE_START	= 2;
   private final int PHASE_RUN			= 3;
   private final int PHASE_FAIL		= 4;
   private final int PHASE_END			= 5;
   private final int PHASE_STOP		= -1;

   private		KPanel				panel				= null;

   private		BufferedImage		imageBackground		= null;
   private		BufferedImage		imageBlock00_01		= null;
   private		BufferedImage		imageBlock00_02		= null;
   private		BufferedImage		imageBlock00_03		= null;
   private		BufferedImage		imageBlock01		= null;
   private		BufferedImage		imageBlock02		= null;
   private		BufferedImage		imageBlock03		= null;
   private		BufferedImage		imageBlock04		= null;
   private		BufferedImage		imageBlockAA		= null;

   private		KSpriteBackground	spriteBackground	= null;

   private		KTextTitle			kTextTitle			= null;
   private		KText				kTextStart			= null;
   private		KText				kTextBy				= null;
   private		KText				kTextEnd			= null;
   private		KText				kTextScoreHigh		= null;
   private		KText				kTextScore			= null;
   private		KText				kTextStage			= null;
   private		KText				kTextMessage		= null;

   private		KSpriteBlock01[][]	sprBlocks			= new KSpriteBlock01[BLOCK_ROW_COUNT][BLOCK_COL_COUNT];

   private		int					phase				= PHASE_INIT;

   private		int					score				= 0;
   private		int					scoreHigh			= 10000;

   private		int					stage				= 1;
   private		int					time				= 0;

   private		KFPMouseAdapter		kfpMouseAdapter		= null;

   public KCtrlPZL(Container con){
      super(con);
      try{
         panel = new KPanel(this);
         panel.setSize(KConstant.WIDTH, KConstant.HEIGHT);
         panel.setPreferredSize(new Dimension(KConstant.WIDTH, KConstant.HEIGHT));
         panel.setLayoutCount(LAYOUT_COUNT);

         System.out.println("@@@@@@@@@@@@@");
         imageBackground = KImage.getImage("/imageBackground.gif");
         System.out.println("AAAAAAAAAAAAA");
         imageBlock00_01 = KImage.getImage("/imageBlock00_01.gif");
         imageBlock00_02 = KImage.getImage("/imageBlock00_02.gif");
         imageBlock00_03 = KImage.getImage("/imageBlock00_03.gif");
         imageBlock01 = KImage.getImage("/imageBlock01.gif");
         imageBlock02 = KImage.getImage("/imageBlock02.gif");
         imageBlock03 = KImage.getImage("/imageBlock03.gif");
         imageBlock04 = KImage.getImage("/imageBlock04.gif");
         imageBlockAA = KImage.getImage("/imageBlockAA.gif");

         KSpriteBackground.setImage(KSpriteBackground.class, imageBackground);
         KSpriteBlock00.setImage(KSpriteBlock00.class, new BufferedImage[]{imageBlock00_01, imageBlock00_02, imageBlock00_03});
         KSpriteBlock01.setImage(KSpriteBlock01.class, new BufferedImage[]{imageBlock01, imageBlock02, imageBlock03, imageBlock04});
         KSpriteBlockAA.setImage(KSpriteBlockAA.class, imageBlockAA);

         spriteBackground = new KSpriteBackground(panel);

         kTextTitle = new KTextTitle(panel, 0, 0);

         kTextStart = new KText(panel, 176, 400);
         kTextStart.setFont(new Font("Arial", Font.PLAIN, 16));
         kTextStart.setText("CLICK MOUSE BUTTON");

         kTextBy = new KText(panel, 200, 480);
         kTextBy.setFont(new Font("Georgia", Font.PLAIN, 12));
         kTextBy.setText("PROGRAMED  BY  KIN");

         kTextEnd = new KText(panel, 250, 300);
         kTextEnd.setFont(new Font("Arial", Font.PLAIN, 16));
         kTextEnd.setText("GAME OVER");

         kTextScoreHigh = new KText(panel, 50, 20);
         panel.addText("HIGH-SCORE", kTextScoreHigh);

         kTextScore = new KText(panel, 200, 20);
         panel.addText("SCORE", kTextScore);

         kTextStage = new KText(panel, 350, 20);
         panel.addText("STAGE", kTextStage);

         kTextMessage = new KText(panel, 240, 100);
         kTextMessage.setFont(new Font("Arial", Font.BOLD, 24));

         kfpMouseAdapter = new KFPMouseAdapter();

         panel.addMouseListener(kfpMouseAdapter);
         panel.addMouseMotionListener(kfpMouseAdapter);
      }catch(Exception ex){
         ex.printStackTrace();
         JOptionPane.showMessageDialog(null, ex.toString());
      }
   } // end KCtrlPZL

   public void start(){
      panel.requestFocus();
      panel.start();
      this.phase = PHASE_INIT;
   } // end start

   public void run(){
      switch(this.phase){
         case PHASE_INIT:
            score = 0;
            stage = 1;
            time = 0;
            panel.initKSpriteLstInLayout(LAYOUT_BLK0);
            panel.initKSpriteLstInLayout(LAYOUT_BLK1);

            panel.initKSpriteLstInLayout(LAYOUT_HAIKEI0);
            panel.initKSpriteLstInLayout(LAYOUT_HAIKEI1);
            panel.initKSpriteLstInLayout(LAYOUT_HAIKEI2);

            panel.initSpriteGroup();

            panel.addSprite(LAYOUT_HAIKEI0, spriteBackground);

            panel.addText("TITLE", kTextTitle);
            panel.addText("START", kTextStart);
            panel.addText("BY", kTextBy);

            kTextScoreHigh.setText("HIGH-SCORE : " + Integer.toString(scoreHigh));
            kTextScore.setText("SCORE : " + Integer.toString(score));
            kTextStage.setText("STAGE : " + Integer.toString(stage));

            for(int row = 0; row < 17; row++){
               KSpriteBlockAA kSpriteBlockAA = new KSpriteBlockAA(panel);
               panel.addSprite(LAYOUT_HAIKEI1, kSpriteBlockAA);
               kSpriteBlockAA.setY(row * 32);

               kSpriteBlockAA = new KSpriteBlockAA(panel);
               panel.addSprite(LAYOUT_HAIKEI1, kSpriteBlockAA);
               kSpriteBlockAA.setX(512);
               kSpriteBlockAA.setY(row * 32);
            }

            for(int col = 0; col < 17; col++){
               KSpriteBlockAA kSpriteBlockAA = new KSpriteBlockAA(panel);
               panel.addSprite(LAYOUT_HAIKEI1, kSpriteBlockAA);
               kSpriteBlockAA.setX(col * 32);
               kSpriteBlockAA.setY(512);
            }
            // this.phase = PHASE_TITLE;
            break;

         case PHASE_TITLE:
            break;

         case PHASE_STAGE_START:
            time++;

            if(time == 1){
               panel.removeText("TITLE");
               panel.removeText("START");
               panel.removeText("BY");

               initBlocks();
               kTextMessage.setX(216);
               kTextMessage.setText("READY");
               panel.addText("MESSAGE", kTextMessage);
            }else if(time == 120){
               kTextMessage.setX(240);
               kTextMessage.setText("GO!");
            }else if(time == 180){
               panel.removeText("MESSAGE");
               this.phase = PHASE_RUN;
            }
            break;

         case PHASE_RUN:
            break;

         case PHASE_FAIL:
            break;

         case PHASE_END:
            break;

         default:
            break;
      }
   }

   public KPanel getKPanel(){
      return this.panel;
   }

  public void mouseEvent(MouseEvent me) {
    switch (this.phase) {
      case PHASE_TITLE:
        if (me.getID() != MouseEvent.MOUSE_CLICKED) {
          return;
        }
        phase = PHASE_STAGE_START;
        break;

      default:
        break;
    }
  }

   private void initBlocks(){
      for(int row = 0; row < BLOCK_ROW_COUNT; row++){
         for(int col = 0; col < BLOCK_COL_COUNT; col++){
            KSpriteBlock01 sprBlock =  new KSpriteBlock01(panel, getNewBlock());
            sprBlocks[row][col] = sprBlock;
            sprBlock.setY(POS_Y + row * 32);
            sprBlock.setX(POS_X + col * 32);
            panel.addSprite(LAYOUT_BLK0, sprBlock);
         }
      }
   }

   private int getNewBlock(){
      return (int)(Math.random() * (stage + 2));
   }

   private class KFPMouseAdapter extends MouseAdapter {
      public void mouseClicked(MouseEvent me){
         super.mouseClicked(me);
         KCtrlPZL.this.mouseEvent(me);
      }
   }
}