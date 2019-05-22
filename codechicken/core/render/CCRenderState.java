package codechicken.core.render;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

public class CCRenderState {

   private static boolean useNormals;
   private static boolean hasBrightness;
   private static int brightness;
   private static boolean useModelColours;
   private static int colour;
   private static boolean hasColour;
   private static int alpha;
   private static boolean alphaOverride;


   public static void useNormals(boolean b) {
      useNormals = b;
   }

   public static boolean useNormals() {
      return useNormals;
   }

   public static void useModelColours(boolean b) {
      useModelColours = b;
   }

   public static boolean useModelColours() {
      return useModelColours;
   }

   public static void setAlpha(int a) {
      alpha = a;
      alphaOverride = true;
   }

   public static void clearAlphaOverride() {
      alphaOverride = false;
   }

   public static void vertexColour(int c) {
      if(alphaOverride) {
         setColour(c & -256 | alpha);
      } else {
         setColour(c);
      }

   }

   public static void vertexColour(int r, int g, int b, int a) {
      if(alphaOverride) {
         Tessellator.instance.setColorRGBA(r, g, b, alpha);
      } else {
         Tessellator.instance.setColorRGBA(r, g, b, a);
      }

   }

   public static void setBrightness(IBlockAccess world, int x, int y, int z) {
      Block block = Block.blocksList[world.getBlockId(x, y, z)];
      setBrightness(block == null?world.getLightBrightnessForSkyBlocks(x, y, z, 0):block.getMixedBrightnessForBlock(world, x, y, z));
      setColour(-1);
   }

   public static void setBrightness(int b) {
      hasBrightness = true;
      Tessellator var10000 = Tessellator.instance;
      brightness = b;
      var10000.setBrightness(b);
   }

   public static void setColourOpaque(int c) {
      setColour(c << 8 | 255);
   }

   public static void setColour(int c) {
      hasColour = true;
      colour = c;
      Tessellator.instance.setColorRGBA(colour >> 24 & 255, colour >> 16 & 255, colour >> 8 & 255, colour & 255);
   }

   public static void changeTexture(String texture) {
      Minecraft.getMinecraft().renderEngine.bindTexture(texture);
      if(Tessellator.instance.isDrawing) {
         apply();
      }

   }

   public static void apply() {
      if(hasBrightness) {
         Tessellator.instance.setBrightness(brightness);
      }

      if(hasColour) {
         Tessellator.instance.setColorRGBA(colour >> 24 & 255, colour >> 16 & 255, colour >> 8 & 255, colour & 255);
      }

   }

   public static void reset() {
      hasBrightness = false;
      useModelColours = false;
      hasColour = false;
      alphaOverride = false;
   }

   public static void startDrawing(int i) {
      Tessellator.instance.startDrawing(i);
      apply();
   }

   public static void draw() {
      Tessellator.instance.draw();
   }

   public static void pullLightmap() {
      setBrightness((int)OpenGlHelper.lastBrightnessY << 16 | (int)OpenGlHelper.lastBrightnessX);
   }

   public static void applyBrightnessTexCoords() {
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)(brightness & '\uffff'), (float)(brightness >>> 16));
   }
}
