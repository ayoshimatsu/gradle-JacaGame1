package com.ayoshimatsu;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

public class KFramePZL extends JFrame implements KConstant {
   KCtrlPZL	kCtrl = null;
   KPanel kPanel = null;

   public static void main(String[] args){
      String path = new File(".").getAbsoluteFile().getParent();
      System.out.println(path);
      KFramePZL test = new KFramePZL();
   }

   public KFramePZL(){
      super();
      super.setLayout(new BorderLayout());
      super.addWindowListener(new KFrameWindowAdapter());

      KImage.setContainer(this);
      kCtrl = new KCtrlPZL(this);
      kPanel = kCtrl.getKPanel();

      super.setTitle("PZL");
      super.setContentPane(kPanel);
      super.setVisible(true);
      super.requestFocus();
      super.pack();
      kCtrl.start();
   }

   private class KFrameWindowAdapter extends WindowAdapter {
      public void windowClosing(WindowEvent we){
         System.exit(0);
      }
   }
}