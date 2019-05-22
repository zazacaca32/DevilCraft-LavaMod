package codechicken.core.featurehack.mc;

import codechicken.core.render.TextureFX;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class TextureLavaFX extends TextureFX {

   protected float[] field_76876_g = new float[256];
   protected float[] field_76878_h = new float[256];
   protected float[] field_76879_i = new float[256];
   protected float[] field_76877_j = new float[256];


   public TextureLavaFX() {
      super(16, "lava_still_fx");
      this.setup();
   }

   public void setup() {
      super.setup();
      this.field_76876_g = new float[super.tileSizeSquare];
      this.field_76878_h = new float[super.tileSizeSquare];
      this.field_76879_i = new float[super.tileSizeSquare];
      this.field_76877_j = new float[super.tileSizeSquare];
   }

   public void onTick() {
      int var2;
      float var3;
      int var5;
      int var6;
      int var7;
      int var8;
      int var9;
      int var10;
      for(int var11 = 0; var11 < super.tileSizeBase; ++var11) {
         for(var2 = 0; var2 < super.tileSizeBase; ++var2) {
            var3 = 0.0F;
            var10 = (int)(MathHelper.sin((float)var2 * 3.1415927F * 2.0F / 16.0F) * 1.2F);
            var5 = (int)(MathHelper.sin((float)var11 * 3.1415927F * 2.0F / 16.0F) * 1.2F);

            for(var6 = var11 - 1; var6 <= var11 + 1; ++var6) {
               for(var7 = var2 - 1; var7 <= var2 + 1; ++var7) {
                  var8 = var6 + var10 & super.tileSizeMask;
                  var9 = var7 + var5 & super.tileSizeMask;
                  var3 += this.field_76876_g[var8 + var9 * super.tileSizeBase];
               }
            }

            this.field_76878_h[var11 + var2 * super.tileSizeBase] = var3 / 10.0F + (this.field_76879_i[(var11 + 0 & super.tileSizeMask) + (var2 + 0 & super.tileSizeMask) * super.tileSizeBase] + this.field_76879_i[(var11 + 1 & super.tileSizeMask) + (var2 + 0 & super.tileSizeMask) * super.tileSizeBase] + this.field_76879_i[(var11 + 1 & super.tileSizeMask) + (var2 + 1 & super.tileSizeMask) * super.tileSizeBase] + this.field_76879_i[(var11 + 0 & super.tileSizeMask) + (var2 + 1 & super.tileSizeMask) * super.tileSizeBase]) / 4.0F * 0.8F;
            this.field_76879_i[var11 + var2 * super.tileSizeBase] += this.field_76877_j[var11 + var2 * super.tileSizeBase] * 0.01F;
            if(this.field_76879_i[var11 + var2 * super.tileSizeBase] < 0.0F) {
               this.field_76879_i[var11 + var2 * super.tileSizeBase] = 0.0F;
            }

            this.field_76877_j[var11 + var2 * super.tileSizeBase] -= 0.06F;
            if(Math.random() < 0.005D) {
               this.field_76877_j[var11 + var2 * super.tileSizeBase] = 1.5F;
            }
         }
      }

      float[] var101 = this.field_76878_h;
      this.field_76878_h = this.field_76876_g;
      this.field_76876_g = var101;

      for(var2 = 0; var2 < super.tileSizeSquare; ++var2) {
         var3 = this.field_76876_g[var2] * 2.0F;
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
            var10 = (var5 * 30 + var7 * 70) / 100;
            var5 = var8;
            var6 = var9;
            var7 = var10;
         }

         super.imageData[var2 * 4 + 0] = (byte)var5;
         super.imageData[var2 * 4 + 1] = (byte)var6;
         super.imageData[var2 * 4 + 2] = (byte)var7;
         super.imageData[var2 * 4 + 3] = -1;
      }

   }
}
