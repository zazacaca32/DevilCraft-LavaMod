package codechicken.core.featurehack.mc;

import codechicken.core.render.TextureFX;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TextureWaterFlowFX extends TextureFX {

   protected float[] field_76880_g = new float[256];
   protected float[] field_76883_h = new float[256];
   protected float[] field_76884_i = new float[256];
   protected float[] field_76881_j = new float[256];
   private int tickCounter = 0;
   public int tileSizeBase = 16;
   public int tileSizeSquare = 256;
   public int tileSizeMask = 15;
   public int tileSizeSquareMask = 255;


   public TextureWaterFlowFX() {
      super(32, "water_flow_fx");
   }

   public void setup() {
      super.setup();
      this.field_76880_g = new float[this.tileSizeSquare];
      this.field_76883_h = new float[this.tileSizeSquare];
      this.field_76884_i = new float[this.tileSizeSquare];
      this.field_76881_j = new float[this.tileSizeSquare];
      this.tickCounter = 0;
   }

   public void onTick() {
      ++this.tickCounter;

      int var1;
      int var2;
      float var3;
      int var5;
      int var6;
      for(var1 = 0; var1 < this.tileSizeBase; ++var1) {
         for(var2 = 0; var2 < this.tileSizeBase; ++var2) {
            var3 = 0.0F;

            for(int var12 = var2 - 2; var12 <= var2; ++var12) {
               var5 = var1 & this.tileSizeMask;
               var6 = var12 & this.tileSizeMask;
               var3 += this.field_76880_g[var5 + var6 * this.tileSizeBase];
            }

            this.field_76883_h[var1 + var2 * this.tileSizeBase] = var3 / 3.2F + this.field_76884_i[var1 + var2 * this.tileSizeBase] * 0.8F;
         }
      }

      for(var1 = 0; var1 < this.tileSizeBase; ++var1) {
         for(var2 = 0; var2 < this.tileSizeBase; ++var2) {
            this.field_76884_i[var1 + var2 * this.tileSizeBase] += this.field_76881_j[var1 + var2 * this.tileSizeBase] * 0.05F;
            if(this.field_76884_i[var1 + var2 * this.tileSizeBase] < 0.0F) {
               this.field_76884_i[var1 + var2 * this.tileSizeBase] = 0.0F;
            }

            this.field_76881_j[var1 + var2 * this.tileSizeBase] -= 0.3F;
            if(Math.random() < 0.2D) {
               this.field_76881_j[var1 + var2 * this.tileSizeBase] = 0.5F;
            }
         }
      }

      float[] var131 = this.field_76883_h;
      this.field_76883_h = this.field_76880_g;
      this.field_76880_g = var131;

      for(var2 = 0; var2 < this.tileSizeSquare; ++var2) {
         var3 = this.field_76880_g[var2 - this.tickCounter * this.tileSizeBase & this.tileSizeSquareMask];
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
         int px;
         int py;
         if(super.anaglyphEnabled) {
            px = (var5 * 30 + var6 * 59 + var7 * 11) / 100;
            py = (var5 * 30 + var6 * 70) / 100;
            int var11 = (var5 * 30 + var7 * 70) / 100;
            var5 = px;
            var6 = py;
            var7 = var11;
         }

         px = var2 & this.tileSizeMask;
         py = var2 / this.tileSizeBase;
         this.writeColour(px, py, var5, var6, var7, var8);
         this.writeColour(px + 16, py, var5, var6, var7, var8);
         this.writeColour(px, py + 16, var5, var6, var7, var8);
         this.writeColour(px + 16, py + 16, var5, var6, var7, var8);
      }

   }

   private void writeColour(int px, int py, int var5, int var6, int var7, int var8) {
      int var2 = (py * 32 + px) * 4;
      super.imageData[var2 + 0] = (byte)var5;
      super.imageData[var2 + 1] = (byte)var6;
      super.imageData[var2 + 2] = (byte)var7;
      super.imageData[var2 + 3] = (byte)var8;
   }
}
