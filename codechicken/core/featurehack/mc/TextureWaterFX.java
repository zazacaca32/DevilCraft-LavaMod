package codechicken.core.featurehack.mc;

import codechicken.core.render.TextureFX;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TextureWaterFX extends TextureFX {

   protected float[] red = new float[256];
   protected float[] green = new float[256];
   protected float[] blue = new float[256];
   protected float[] alpha = new float[256];


   public TextureWaterFX() {
      super(16, "water_still_fx");
      this.setup();
   }

   public void setup() {
      super.setup();
      this.red = new float[super.tileSizeSquare];
      this.green = new float[super.tileSizeSquare];
      this.blue = new float[super.tileSizeSquare];
      this.alpha = new float[super.tileSizeSquare];
   }

   public void onTick() {
      int var1;
      int var2;
      float var3;
      int var5;
      int var6;
      for(var1 = 0; var1 < super.tileSizeBase; ++var1) {
         for(var2 = 0; var2 < super.tileSizeBase; ++var2) {
            var3 = 0.0F;

            for(int var12 = var1 - 1; var12 <= var1 + 1; ++var12) {
               var5 = var12 & super.tileSizeMask;
               var6 = var2 & super.tileSizeMask;
               var3 += this.red[var5 + var6 * super.tileSizeBase];
            }

            this.green[var1 + var2 * super.tileSizeBase] = var3 / 3.3F + this.blue[var1 + var2 * super.tileSizeBase] * 0.8F;
         }
      }

      for(var1 = 0; var1 < super.tileSizeBase; ++var1) {
         for(var2 = 0; var2 < super.tileSizeBase; ++var2) {
            this.blue[var1 + var2 * super.tileSizeBase] += this.alpha[var1 + var2 * super.tileSizeBase] * 0.05F;
            if(this.blue[var1 + var2 * super.tileSizeBase] < 0.0F) {
               this.blue[var1 + var2 * super.tileSizeBase] = 0.0F;
            }

            this.alpha[var1 + var2 * super.tileSizeBase] -= 0.1F;
            if(Math.random() < 0.05D) {
               this.alpha[var1 + var2 * super.tileSizeBase] = 0.5F;
            }
         }
      }

      float[] var131 = this.green;
      this.green = this.red;
      this.red = var131;

      for(var2 = 0; var2 < super.tileSizeSquare; ++var2) {
         var3 = this.red[var2];
         if(var3 > 1.0F) {
            var3 = 1.0F;
         }

         if(var3 < 0.0F) {
            var3 = 0.0F;
         }

         float var13 = var3 * var3;
         var5 = (int)(32.0F + var13 * 32.0F);
         var6 = (int)(50.0F + var13 * 64.0F);
         int var7 = 255;
         int var8 = (int)(146.0F + var13 * 50.0F);
         if(super.anaglyphEnabled) {
            int var9 = (var5 * 30 + var6 * 59 + var7 * 11) / 100;
            int var10 = (var5 * 30 + var6 * 70) / 100;
            int var11 = (var5 * 30 + var7 * 70) / 100;
            var5 = var9;
            var6 = var10;
            var7 = var11;
         }

         super.imageData[var2 * 4 + 0] = (byte)var5;
         super.imageData[var2 * 4 + 1] = (byte)var6;
         super.imageData[var2 * 4 + 2] = (byte)var7;
         super.imageData[var2 * 4 + 3] = (byte)var8;
      }

   }
}
