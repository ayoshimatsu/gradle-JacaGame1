package com.ayoshimatsu;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.*;

public class KTextTitle extends KText{

   private Font font = new Font("Arial", Font.ITALIC, 96);

   private String strText0 = "Puzzle";
   private String strText1 = "Game";

   private int intX0 = 40;
   private int intX1 = 120;

   private int intY0 = 160;
   private int intY1 = 256;

   private Color[] colors = {	Color.red,
           new Color(255,  64,   0),
           new Color(255, 128,   0),
           new Color(255, 192,   0),
           Color.yellow,
           new Color(192, 255,   0),
           new Color(128, 255,   0),
           new Color( 64, 255,   0),
           Color.green,
           new Color(  0, 255,  64),
           new Color(  0, 255, 128),
           new Color(  0, 255, 192),
           Color.cyan,
           new Color(  0, 192, 255),
           new Color(  0, 128, 255),
           new Color(  0,  64, 255),
           Color.blue,
           new Color( 64,   0, 255),
           new Color(128,   0, 255),
           new Color(192,   0, 255),
           Color.magenta,
           new Color(255,   0, 192),
           new Color(255,   0, 128),
           new Color(255,   0,  64)
   };

   private int time = 0;

   public KTextTitle(KPanel panel, int intX, int intY){
      super(panel, intX, intY, Color.white);
   }

   public void paint(Graphics g){
      time++;
      for(int i = 0; i < 4; i++){
         g.setFont(font);
         g.setColor(this.colors[(time / 5 + (4 - i)) % colors.length]);
         g.drawString(strText0.substring(i, i + 1), intX0 + i * 96, intY0);
      }

      for(int i = 0; i < 4; i++){
         g.setFont(font);
         g.setColor(this.colors[(time / 5 + (4 - i) + 12) % colors.length]);
         g.drawString(strText1.substring(i, i + 1), intX1 + i * 96, intY1);
      }
   }
}
