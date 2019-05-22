package codechicken.core.featurehack.mc;

import codechicken.core.render.TextureFX;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class TextureLavaFlowFX extends TextureFX {

   protected float[] field_76871_g = new float[256];
   protected float[] field_76874_h = new float[256];
   protected float[] field_76875_i = new float[256];
   protected float[] field_76872_j = new float[256];
   int field_76873_k = 0;
   public int tileSizeBase = 16;
   public int tileSizeSquare = 256;
   public int tileSizeMask = 15;
   public int tileSizeSquareMask = 255;


   public TextureLavaFlowFX() {
      super(32, "lava_flow_fx");
   }

   public void setup() {
      super.setup();
      this.field_76871_g = new float[this.tileSizeSquare];
      this.field_76874_h = new float[this.tileSizeSquare];
      this.field_76875_i = new float[this.tileSizeSquare];
      this.field_76872_j = new float[this.tileSizeSquare];
      this.field_76873_k = 0;
   }

   public void onTick() {
      ++this.field_76873_k;

      int var2;
      float var3;
      int var5;
      int var6;
      int var7;
      int var8;
      int var9;
      int px;
      for(int var11 = 0; var11 < this.tileSizeBase; ++var11) {
         for(var2 = 0; var2 < this.tileSizeBase; ++var2) {
            var3 = 0.0F;
            px = (int)(MathHelper.sin((float)var2 * 3.1415927F * 2.0F / 16.0F) * 1.2F);
            var5 = (int)(MathHelper.sin((float)var11 * 3.1415927F * 2.0F / 16.0F) * 1.2F);

            for(var6 = var11 - 1; var6 <= var11 + 1; ++var6) {
               for(var7 = var2 - 1; var7 <= var2 + 1; ++var7) {
                  var8 = var6 + px & this.tileSizeMask;
                  var9 = var7 + var5 & this.tileSizeMask;
                  var3 += this.field_76871_g[var8 + var9 * this.tileSizeBase];
               }
            }

            this.field_76874_h[var11 + var2 * this.tileSizeBase] = var3 / 10.0F + (this.field_76875_i[(var11 + 0 & this.tileSizeMask) + (var2 + 0 & this.tileSizeMask) * this.tileSizeBase] + this.field_76875_i[(var11 + 1 & this.tileSizeMask) + (var2 + 0 & this.tileSizeMask) * this.tileSizeBase] + this.field_76875_i[(var11 + 1 & this.tileSizeMask) + (var2 + 1 & this.tileSizeMask) * this.tileSizeBase] + this.field_76875_i[(var11 + 0 & this.tileSizeMask) + (var2 + 1 & this.tileSizeMask) * this.tileSizeBase]) / 4.0F * 0.8F;
            this.field_76875_i[var11 + var2 * this.tileSizeBase] += this.field_76872_j[var11 + var2 * this.tileSizeBase] * 0.01F;
            if(this.field_76875_i[var11 + var2 * this.tileSizeBase] < 0.0F) {
               this.field_76875_i[var11 + var2 * this.tileSizeBase] = 0.0F;
            }

            this.field_76872_j[var11 + var2 * this.tileSizeBase] -= 0.06F;
            if(Math.random() < 0.005D) {
               this.field_76872_j[var11 + var2 * this.tileSizeBase] = 1.5F;
            }
         }
      }

      float[] var111 = this.field_76874_h;
      this.field_76874_h = this.field_76871_g;
      this.field_76871_g = var111;

      for(var2 = 0; var2 < this.tileSizeSquare; ++var2) {
         var3 = this.field_76871_g[var2 - this.field_76873_k / 3 * this.tileSizeBase & this.tileSizeSquareMask] * 2.0F;
         if(var3 > 1.0F) {
            var3 = 1.0F;
         }

         if(var3 < 0.0F) {
            var3 = 0.0F;
         }

         var5 = (int)(var3 * 100.0F + 155.0F);
         var6 = (int)(var3 * var3 * 255.0F);
         var7 = (int)(var3 * var3 * var3 * var3 * 128.0F);
         if(super.anaglyphEnabled) {
            var8 = (var5 * 30 + var6 * 59 + var7 * 11) / 100;
            var9 = (var5 * 30 + var6 * 70) / 100;
            px = (var5 * 30 + var7 * 70) / 100;
            var5 = var8;
            var6 = var9;
            var7 = px;
         }

         px = var2 & this.tileSizeMask;
         int py = var2 / this.tileSizeBase;
         this.writeColour(px, py, var5, var6, var7, -1);
         this.writeColour(px + 16, py, var5, var6, var7, -1);
         this.writeColour(px, py + 16, var5, var6, var7, -1);
         this.writeColour(px + 16, py + 16, var5, var6, var7, -1);
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
