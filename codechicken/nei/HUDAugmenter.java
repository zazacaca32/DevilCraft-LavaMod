package codechicken.nei;

import codechicken.nei.NEIClientConfig;
import codechicken.nei.api.ItemInfo;
import codechicken.nei.forge.GuiContainerManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import org.lwjgl.opengl.GL11;

public class HUDAugmenter {

   public static void renderOverlay() {
      Minecraft mc = Minecraft.getMinecraft();
      if(mc.currentScreen == null && mc.theWorld != null && !mc.gameSettings.keyBindPlayerList.pressed && NEIClientConfig.getBooleanSetting("options.inworld tooltips") && mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == EnumMovingObjectType.TILE) {
         WorldClient world = mc.theWorld;
         ArrayList items = ItemInfo.getIdentifierItems(world, mc.thePlayer, mc.objectMouseOver);
         List textData = null;
         ItemStack stack = null;
         if(items.isEmpty()) {
            return;
         }

         Collections.sort(items, new Comparator() {
            public int compare(ItemStack stack0, ItemStack stack1) {
               return stack1.getItemDamage() - stack0.getItemDamage();
            }

			@Override
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				return 0;
			}
         });
         stack = (ItemStack)items.get(0);
         textData = ItemInfo.getText(stack, world, mc.thePlayer, mc.objectMouseOver);
         ScaledResolution res = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
         GL11.glDisable('\u803a');
         RenderHelper.disableStandardItemLighting();
         GL11.glDisable(2896);
         GL11.glDisable(2929);
         int w = 0;

         String h;
         for(Iterator drawx = textData.iterator(); drawx.hasNext(); w = Math.max(w, GuiContainerManager.getStringWidthNoColours(mc.fontRenderer, h) + 22)) {
            h = (String)drawx.next();
         }

         int var14 = 16 + 8 * (textData.size() - 1);
         int var15 = res.getScaledWidth() / 2 - w / 2 - 5;
         byte drawy = 5;
         int i4 = -267386864;
         drawGradientRect(var15 - 3, drawy - 4, w + 6, 1, i4, i4, 0);
         drawGradientRect(var15 - 3, drawy + var14 + 3, w + 6, 1, i4, i4, 0);
         drawGradientRect(var15 - 3, drawy - 3, w + 6, var14 + 6, i4, i4, 0);
         drawGradientRect(var15 - 4, drawy - 3, 1, var14 + 6, i4, i4, 0);
         drawGradientRect(var15 + w + 3, drawy - 3, 1, var14 + 6, i4, i4, 0);
         int colour1 = 1347420415;
         int colour2 = (colour1 & 16711422) >> 1 | colour1 & -16777216;
         drawGradientRect(var15 - 3, drawy - 2, 1, var14 + 4, colour1, colour2, 0);
         drawGradientRect(var15 + w + 2, drawy - 2, 1, var14 + 4, colour1, colour2, 0);
         drawGradientRect(var15 - 3, drawy - 3, w + 6, 1, colour1, colour1, 0);
         drawGradientRect(var15 - 3, drawy + var14 + 2, w + 6, 1, colour2, colour2, 0);

         for(int i = 0; i < textData.size(); ++i) {
            mc.fontRenderer.drawStringWithShadow((String)textData.get(i), var15 + 20, 9 + 10 * i, -6250336);
         }

         RenderHelper.enableGUIStandardItemLighting();
         GL11.glEnable('\u803a');
         if(Item.itemsList[stack.itemID] != null) {
            GuiContainerManager.drawItem(var15 + 1, (var14 - 6) / 2, stack, mc.fontRenderer, mc.renderEngine);
         }
      }

   }

   public static void drawGradientRect(int par1, int par2, int par3, int par4, int par5, int par6, int zLevel) {
      float var7 = (float)(par5 >> 24 & 255) / 255.0F;
      float var8 = (float)(par5 >> 16 & 255) / 255.0F;
      float var9 = (float)(par5 >> 8 & 255) / 255.0F;
      float var10 = (float)(par5 & 255) / 255.0F;
      float var11 = (float)(par6 >> 24 & 255) / 255.0F;
      float var12 = (float)(par6 >> 16 & 255) / 255.0F;
      float var13 = (float)(par6 >> 8 & 255) / 255.0F;
      float var14 = (float)(par6 & 255) / 255.0F;
      GL11.glDisable(3553);
      GL11.glEnable(3042);
      GL11.glDisable(3008);
      GL11.glBlendFunc(770, 771);
      GL11.glShadeModel(7425);
      Tessellator var15 = Tessellator.instance;
      var15.startDrawingQuads();
      var15.setColorRGBA_F(var8, var9, var10, var7);
      var15.addVertex((double)(par3 + par1), (double)par2, (double)zLevel);
      var15.addVertex((double)par1, (double)par2, (double)zLevel);
      var15.setColorRGBA_F(var12, var13, var14, var11);
      var15.addVertex((double)par1, (double)(par4 + par2), (double)zLevel);
      var15.addVertex((double)(par3 + par1), (double)(par4 + par2), (double)zLevel);
      var15.draw();
      GL11.glShadeModel(7424);
      GL11.glDisable(3042);
      GL11.glEnable(3008);
      GL11.glEnable(3553);
   }
}
