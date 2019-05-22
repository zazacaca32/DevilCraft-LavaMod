package codechicken.core.lighting;

import codechicken.core.lighting.CCRBModel;
import codechicken.core.lighting.LC;
import codechicken.core.render.CCModel;
import codechicken.core.render.CCRenderState;
import codechicken.core.render.IVertexModifier;
import codechicken.core.render.UV;
import codechicken.core.vec.BlockCoord;
import codechicken.core.vec.Vector3;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

public class LightMatrix implements IVertexModifier {

   public int computed = 0;
   public float[][] ao = new float[13][4];
   public int[][] brightness = new int[13][4];
   public BlockCoord pos = new BlockCoord();
   private float[] aSamples = new float[27];
   private int[] bSamples = new int[27];
   private Vector3 v_temp = new Vector3();
   public static final int[][] ssamplem = new int[][]{{0, 1, 2, 3, 4, 5, 6, 7, 8}, {18, 19, 20, 21, 22, 23, 24, 25, 26}, {0, 9, 18, 1, 10, 19, 2, 11, 20}, {6, 15, 24, 7, 16, 25, 8, 17, 26}, {0, 3, 6, 9, 12, 15, 18, 21, 24}, {2, 5, 8, 11, 14, 17, 20, 23, 26}, {9, 10, 11, 12, 13, 14, 15, 16, 17}, {9, 10, 11, 12, 13, 14, 15, 16, 17}, {3, 12, 21, 4, 13, 22, 5, 14, 23}, {3, 12, 21, 4, 13, 22, 5, 14, 23}, {1, 4, 7, 10, 13, 16, 19, 22, 25}, {1, 4, 7, 10, 13, 16, 19, 22, 25}, {13, 13, 13, 13, 13, 13, 13, 13, 13}};
   public static final int[][] qsamplem = new int[][]{{0, 1, 3, 4}, {5, 1, 2, 4}, {6, 7, 3, 4}, {5, 7, 8, 4}};
   public static final float[] sideao = new float[]{0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F, 0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F, 1.0F};


   public void computeAt(IBlockAccess a, int x, int y, int z) {
      this.pos.set(x, y, z);
      this.computed = 0;
      this.sample(0, this.aSamples, this.bSamples, a, x - 1, y - 1, z - 1);
      this.sample(1, this.aSamples, this.bSamples, a, x, y - 1, z - 1);
      this.sample(2, this.aSamples, this.bSamples, a, x + 1, y - 1, z - 1);
      this.sample(3, this.aSamples, this.bSamples, a, x - 1, y - 1, z);
      this.sample(4, this.aSamples, this.bSamples, a, x, y - 1, z);
      this.sample(5, this.aSamples, this.bSamples, a, x + 1, y - 1, z);
      this.sample(6, this.aSamples, this.bSamples, a, x - 1, y - 1, z + 1);
      this.sample(7, this.aSamples, this.bSamples, a, x, y - 1, z + 1);
      this.sample(8, this.aSamples, this.bSamples, a, x + 1, y - 1, z + 1);
      this.sample(9, this.aSamples, this.bSamples, a, x - 1, y, z - 1);
      this.sample(10, this.aSamples, this.bSamples, a, x, y, z - 1);
      this.sample(11, this.aSamples, this.bSamples, a, x + 1, y, z - 1);
      this.sample(12, this.aSamples, this.bSamples, a, x - 1, y, z);
      this.sample(13, this.aSamples, this.bSamples, a, x, y, z);
      this.sample(14, this.aSamples, this.bSamples, a, x + 1, y, z);
      this.sample(15, this.aSamples, this.bSamples, a, x - 1, y, z + 1);
      this.sample(16, this.aSamples, this.bSamples, a, x, y, z + 1);
      this.sample(17, this.aSamples, this.bSamples, a, x + 1, y, z + 1);
      this.sample(18, this.aSamples, this.bSamples, a, x - 1, y + 1, z - 1);
      this.sample(19, this.aSamples, this.bSamples, a, x, y + 1, z - 1);
      this.sample(20, this.aSamples, this.bSamples, a, x + 1, y + 1, z - 1);
      this.sample(21, this.aSamples, this.bSamples, a, x - 1, y + 1, z);
      this.sample(22, this.aSamples, this.bSamples, a, x, y + 1, z);
      this.sample(23, this.aSamples, this.bSamples, a, x + 1, y + 1, z);
      this.sample(24, this.aSamples, this.bSamples, a, x - 1, y + 1, z + 1);
      this.sample(25, this.aSamples, this.bSamples, a, x, y + 1, z + 1);
      this.sample(26, this.aSamples, this.bSamples, a, x + 1, y + 1, z + 1);
   }

   public int[] brightness(int side) {
      this.sideSample(side);
      return this.brightness[side];
   }

   public float[] ao(int side) {
      this.sideSample(side);
      return this.ao[side];
   }

   public void sideSample(int side) {
      if((this.computed & 1 << side) == 0) {
         int[] ssample = ssamplem[side];

         for(int q = 0; q < 4; ++q) {
            int[] qsample = qsamplem[q];
            if(Minecraft.isAmbientOcclusionEnabled()) {
               this.interp(side, q, ssample[qsample[0]], ssample[qsample[1]], ssample[qsample[2]], ssample[qsample[3]]);
            } else {
               this.interp(side, q, ssample[4], ssample[4], ssample[4], ssample[4]);
            }
         }

         this.computed |= 1 << side;
      }

   }

   private void sample(int i, float[] aSamples, int[] bSamples, IBlockAccess a, int x, int y, int z) {
      int bid = a.getBlockId(x, y, z);
      Block b = Block.blocksList[bid];
      if(b == null) {
         bSamples[i] = a.getLightBrightnessForSkyBlocks(x, y, z, 0);
         aSamples[i] = 1.0F;
      } else {
         bSamples[i] = a.getLightBrightnessForSkyBlocks(x, y, z, b.getLightValue(a, x, y, z));
         aSamples[i] = b.getAmbientOcclusionLightValue(a, x, y, z);
      }

   }

   private void interp(int s, int q, int a, int b, int c, int d) {
      this.ao[s][q] = interpAO(this.aSamples[a], this.aSamples[b], this.aSamples[c], this.aSamples[d]) * sideao[s];
      this.brightness[s][q] = interpBrightness(this.bSamples[a], this.bSamples[b], this.bSamples[c], this.bSamples[d]);
   }

   public static float interpAO(float a, float b, float c, float d) {
      return (a + b + c + d) / 4.0F;
   }

   public static int interpBrightness(int a, int b, int c, int d) {
      if(a == 0) {
         a = d;
      }

      if(b == 0) {
         b = d;
      }

      if(c == 0) {
         c = d;
      }

      return a + b + c + d >> 2 & 16711935;
   }

   public void setColour(Tessellator tess, LC lc, int c) {
      float[] a = this.ao(lc.side);
      float f = a[0] * lc.fa + a[1] * lc.fb + a[2] * lc.fc + a[3] * lc.fd;
      CCRenderState.vertexColour((int)((float)(c >>> 24) * f), (int)((float)(c >> 16 & 255) * f), (int)((float)(c >> 8 & 255) * f), c & 255);
   }

   public void setBrightness(Tessellator tess, LC lc) {
      int[] b = this.brightness(lc.side);
      tess.setBrightness((int)((float)b[0] * lc.fa + (float)b[1] * lc.fb + (float)b[2] * lc.fc + (float)b[3] * lc.fd) & 16711935);
   }

   public void applyModifiers(CCModel m, Tessellator tess, Vector3 vec, UV uv, Vector3 normal, int i) {
      LC lc;
      if(m instanceof CCRBModel) {
         lc = ((CCRBModel)m).lightCoefficents[i];
      } else {
         lc = LC.compute(this.v_temp.set(vec).add((double)(-this.pos.x), (double)(-this.pos.y), (double)(-this.pos.z)), normal);
      }

      this.setColour(tess, lc, m != null && m.colours != null?m.colours[i]:-1);
      this.setBrightness(tess, lc);
   }

   public boolean needsNormals() {
      return true;
   }
}
