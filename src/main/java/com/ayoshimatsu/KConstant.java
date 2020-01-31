package com.ayoshimatsu;

public interface KConstant {

   public	final	int		BLOCK_ROW_COUNT		= 10;
   public	final	int		BLOCK_COL_COUNT		= 15;

   public	final	int		POS_X		=  32;
   public	final	int		POS_Y		= 192;

   public	final	int		WIDTH		= 32 * BLOCK_COL_COUNT + 64;
   public	final	int		HEIGHT		= 32 * BLOCK_ROW_COUNT + POS_Y + 32;

   public	final	int		LAYOUT_COUNT		= 5;
   public	final	int		LAYOUT_BLK1			= 4;
   public	final	int		LAYOUT_BLK0			= 3;
   public	final	int		LAYOUT_HAIKEI2		= 2;
   public	final	int		LAYOUT_HAIKEI1		= 1;
   public	final	int		LAYOUT_HAIKEI0		= 0;
}