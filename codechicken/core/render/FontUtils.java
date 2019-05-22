package codechicken.core.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class FontUtils {

   public static FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
   public static final String[] prefixes = new String[]{"K", "M", "G"};


   public static void drawCenteredString(String s, int xCenter, int y, int colour) {
      fontRenderer.drawString(s, xCenter - fontRenderer.getStringWidth(s) / 2, y, colour);
   }

   public static void drawRightString(String s, int xRight, int y, int colour) {
      fontRenderer.drawString(s, xRight - fontRenderer.getStringWidth(s), y, colour);
   }

   public static void drawItemQuantity(int x, int y, ItemStack item, String quantity, int mode) {
      if(item != null && (quantity != null || item.stackSize > 1)) {
         if(quantity == null) {
            switch(mode) {
            case 2:
               int scale = item.stackSize;
               String postfix = "";

               for(int sheight = 0; sheight < 3 && scale > 1000; ++sheight) {
                  scale /= 1000;
                  postfix = prefixes[sheight];
               }

               Integer.toString(scale);
            case 1:
               quantity = "";
               if(item.stackSize / 64 > 0) {
                  quantity = quantity + item.stackSize / 64 + "s";
               }

               if(item.stackSize % 64 > 0) {
                  quantity = quantity + item.stackSize % 64;
               }
               break;
            default:
               quantity = Integer.toString(item.stackSize);
            }
         }

         double var11 = quantity.length() > 2?0.5D:1.0D;
         double var12 = 8.0D * var11;
         double swidth = (double)fontRenderer.getStringWidth(quantity) * var11;
         GL11.glDisable(2896);
         GL11.glDisable(2929);
         GL11.glPushMatrix();
         GL11.glTranslated((double)(x + 16) - swidth, (double)(y + 16) - var12, 0.0D);
         GL11.glScaled(var11, var11, 1.0D);
         fontRenderer.drawStringWithShadow(quantity, 0, 0, 16777215);
         GL11.glPopMatrix();
         GL11.glEnable(2896);
         GL11.glEnable(2929);
      }
   }
}
