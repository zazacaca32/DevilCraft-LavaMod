package net.minecraft.client.addon.tco.tiny.gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.addon.tco.tiny.gui.GuiSlotItem;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import org.lwjgl.opengl.GL11;

public abstract class GuiContainerSlotItem extends GuiScreen {

   protected static RenderItem itemRenderer = new RenderItem();
   GuiSlotItem theSlot = null;
   protected int guiLeft = 10;
   protected int guiTop = 10;
   private GuiSlotItem clickedSlot = null;
   protected ArrayList slots;
   protected int xSize = 128;
   protected int ySize = 200;
   protected int xDelta = 0;
   protected int yDelta = 0;


   public GuiContainerSlotItem(int guiLeft, int guiTop, int xSize, int ySize) {
      this.guiLeft = guiLeft;
      this.guiTop = guiTop;
      this.xSize = xSize;
      this.ySize = ySize;
   }

   public void initGui() {
      super.initGui();
      this.guiLeft = (super.width - this.xSize - this.xDelta) / 2;
      this.guiTop = (super.height - this.ySize - this.yDelta) / 2;
   }

   public void drawScreen(int par1, int par2, float par3) {
      this.drawDefaultBackground();
      int k = this.guiLeft;
      int l = this.guiTop;
      this.drawGuiContainerBackgroundLayer(par3, par1, par2);
      GL11.glDisable('\u803a');
      RenderHelper.disableStandardItemLighting();
      GL11.glDisable(2896);
      GL11.glDisable(2929);
      super.drawScreen(par1, par2, par3);
      RenderHelper.enableGUIStandardItemLighting();
      GL11.glPushMatrix();
      GL11.glTranslatef((float)k, (float)l, 0.0F);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable('\u803a');
      this.theSlot = null;
      short short1 = 240;
      short short2 = 240;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)short1 / 1.0F, (float)short2 / 1.0F);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

      for(int var121 = 0; var121 < this.slots.size(); ++var121) {
         GuiSlotItem slot = (GuiSlotItem)this.slots.get(var121);
         this.drawSlot(slot);
         if(this.isMouseOverSlot(slot, par1, par2)) {
            this.theSlot = slot;
            GL11.glDisable(2896);
            GL11.glDisable(2929);
            int k1 = slot.xDisplayPosition;
            int i1 = slot.yDisplayPosition;
            this.drawGradientRect(k1, i1, k1 + 16, i1 + 16, -2130706433, -2130706433);
            GL11.glEnable(2896);
            GL11.glEnable(2929);
         }
      }

      GL11.glDisable(2896);
      this.drawGuiContainerForegroundLayer(par1, par2);
      GL11.glEnable(2896);
      GL11.glPopMatrix();
      if(this.theSlot != null && this.theSlot.getHasStack()) {
         ItemStack var12 = this.theSlot.getStack();
         this.drawItemStackTooltip(var12, par1, par2);
      }

      GL11.glEnable(2896);
      GL11.glEnable(2929);
      RenderHelper.enableStandardItemLighting();
   }

   private void drawItemStack(ItemStack par1ItemStack, int par2, int par3, String par4Str) {
      GL11.glTranslatef(0.0F, 0.0F, 32.0F);
      super.zLevel = 200.0F;
      itemRenderer.zLevel = 200.0F;
      FontRenderer font = null;
      if(par1ItemStack != null) {
         font = par1ItemStack.getItem().getFontRenderer(par1ItemStack);
      }

      if(font == null) {
         font = super.fontRenderer;
      }

      itemRenderer.renderItemAndEffectIntoGUI(font, super.mc.renderEngine, par1ItemStack, par2, par3);
      itemRenderer.renderItemOverlayIntoGUI(font, super.mc.renderEngine, par1ItemStack, par2, par3, par4Str);
      super.zLevel = 0.0F;
      itemRenderer.zLevel = 0.0F;
   }

   protected void drawItemStackTooltip(ItemStack par1ItemStack, int par2, int par3) {
      List list = par1ItemStack.getTooltip(super.mc.thePlayer, super.mc.gameSettings.advancedItemTooltips);

      for(int var61 = 0; var61 < list.size(); ++var61) {
         if(var61 == 0) {
            list.set(var61, "" + Integer.toHexString(par1ItemStack.getRarity().rarityColor) + (String)list.get(var61));
         } else {
            list.set(var61, EnumChatFormatting.GRAY + (String)list.get(var61));
         }
      }

      FontRenderer var6 = par1ItemStack.getItem().getFontRenderer(par1ItemStack);
      this.drawHoveringText(list, par2, par3, var6 == null?super.fontRenderer:var6);
   }

   protected void drawHoveringText(List par1List, int par2, int par3, FontRenderer font) {
      if(!par1List.isEmpty()) {
         GL11.glDisable('\u803a');
         RenderHelper.disableStandardItemLighting();
         GL11.glDisable(2896);
         GL11.glDisable(2929);
         int k = 0;
         Iterator i1 = par1List.iterator();

         int k1;
         while(i1.hasNext()) {
            String var151 = (String)i1.next();
            k1 = font.getStringWidth(var151);
            if(k1 > k) {
               k = k1;
            }
         }

         int var151 = par2 + 12;
         int var15 = par3 - 12;
         k1 = 8;
         if(par1List.size() > 1) {
            k1 += 2 + (par1List.size() - 1) * 10;
         }

         if(var151 + k > super.width) {
            var151 -= 28 + k;
         }

         if(var15 + k1 + 6 > super.height) {
            var15 = super.height - k1 - 6;
         }

         super.zLevel = 300.0F;
         itemRenderer.zLevel = 300.0F;
         int l1 = -267386864;
         this.drawGradientRect(var151 - 3, var15 - 4, var151 + k + 3, var15 - 3, l1, l1);
         this.drawGradientRect(var151 - 3, var15 + k1 + 3, var151 + k + 3, var15 + k1 + 4, l1, l1);
         this.drawGradientRect(var151 - 3, var15 - 3, var151 + k + 3, var15 + k1 + 3, l1, l1);
         this.drawGradientRect(var151 - 4, var15 - 3, var151 - 3, var15 + k1 + 3, l1, l1);
         this.drawGradientRect(var151 + k + 3, var15 - 3, var151 + k + 4, var15 + k1 + 3, l1, l1);
         int i2 = 1347420415;
         int j2 = (i2 & 16711422) >> 1 | i2 & -16777216;
         this.drawGradientRect(var151 - 3, var15 - 3 + 1, var151 - 3 + 1, var15 + k1 + 3 - 1, i2, j2);
         this.drawGradientRect(var151 + k + 2, var15 - 3 + 1, var151 + k + 3, var15 + k1 + 3 - 1, i2, j2);
         this.drawGradientRect(var151 - 3, var15 - 3, var151 + k + 3, var15 - 3 + 1, i2, i2);
         this.drawGradientRect(var151 - 3, var15 + k1 + 2, var151 + k + 3, var15 + k1 + 3, j2, j2);

         for(int k2 = 0; k2 < par1List.size(); ++k2) {
            String s1 = (String)par1List.get(k2);
            font.drawStringWithShadow(s1, var151, var15, -1);
            if(k2 == 0) {
               var15 += 2;
            }

            var15 += 10;
         }

         super.zLevel = 0.0F;
         itemRenderer.zLevel = 0.0F;
         GL11.glEnable(2896);
         GL11.glEnable(2929);
         RenderHelper.enableStandardItemLighting();
         GL11.glEnable('\u803a');
      }

   }

   protected void drawGuiContainerForegroundLayer(int par1, int par2) {}

   protected abstract void drawGuiContainerBackgroundLayer(float var1, int var2, int var3);

   protected void drawSlot(GuiSlotItem par1Slot) {
      int i = par1Slot.xDisplayPosition;
      int j = par1Slot.yDisplayPosition;
      ItemStack itemstack = par1Slot.getStack();
      boolean flag = false;
      boolean flag1 = par1Slot == this.clickedSlot;
      Object s = null;
      super.zLevel = 100.0F;
      itemRenderer.zLevel = 100.0F;
      Icon icon;
      if(itemstack == null && (icon = par1Slot.getBackgroundIconIndex()) != null) {
         GL11.glDisable(2896);
         super.mc.renderEngine.bindTexture("/gui/items.png");
         this.drawTexturedModelRectFromIcon(i, j, icon, 16, 16);
         GL11.glEnable(2896);
         flag1 = true;
      }

      if(!flag1) {
         if(flag) {
            drawRect(i, j, i + 16, j + 16, -2130706433);
         }

         GL11.glEnable(2929);
         itemRenderer.renderItemAndEffectIntoGUI(super.fontRenderer, super.mc.renderEngine, itemstack, i, j);
         itemRenderer.renderItemOverlayIntoGUI(super.fontRenderer, super.mc.renderEngine, itemstack, i, j, (String)s);
      }

      itemRenderer.zLevel = 0.0F;
      super.zLevel = 0.0F;
   }

   protected GuiSlotItem getSlotAtPosition(int par1, int par2) {
      for(int k = 0; k < this.slots.size(); ++k) {
         GuiSlotItem slot = (GuiSlotItem)this.slots.get(k);
         if(this.isMouseOverSlot(slot, par1, par2)) {
            return slot;
         }
      }

      return null;
   }

   protected void mouseClicked(int par1, int par2, int par3) {
      super.mouseClicked(par1, par2, par3);
   }

   protected void mouseMovedOrUp(int par1, int par2, int par3) {
      GuiSlotItem slot = this.getSlotAtPosition(par1, par2);
      int l = this.guiLeft;
      int i1 = this.guiTop;
      boolean flag = par1 < l || par2 < i1 || par1 >= l + this.xSize || par2 >= i1 + this.ySize;
      boolean j1 = true;
      if(slot != null) {
         int var9 = slot.slotNumber;
      }

      if(flag) {
         j1 = true;
      }

      if(slot != null && par3 == 0) {
         ;
      }

   }

   private boolean isMouseOverSlot(GuiSlotItem par1Slot, int par2, int par3) {
      return this.isPointInRegion(par1Slot.xDisplayPosition, par1Slot.yDisplayPosition, 16, 16, par2, par3);
   }

   protected boolean isPointInRegion(int par1, int par2, int par3, int par4, int par5, int par6) {
      int k1 = this.guiLeft;
      int l1 = this.guiTop;
      return (par5 -= k1) >= par1 - 1 && par5 < par1 + par3 + 1 && par6 >= par2 - 1 && par6 - l1 < par2 + par4 + 1;
   }

   protected void handleMouseClick(GuiSlotItem par1Slot, int par2, int par3, int par4) {
      if(par1Slot != null) {
         par2 = par1Slot.slotNumber;
      }

   }

   protected void keyTyped(char par1, int par2) {
      if(par2 == 1 || par2 == super.mc.gameSettings.keyBindInventory.keyCode) {
         super.mc.thePlayer.closeScreen();
      }

   }

   public void onGuiClosed() {
      if(super.mc.thePlayer != null) {
         ;
      }

   }

   public boolean doesGuiPauseGame() {
      return false;
   }

   public void updateScreen() {
      super.updateScreen();
      if(!super.mc.thePlayer.isEntityAlive() || super.mc.thePlayer.isDead) {
         super.mc.thePlayer.closeScreen();
      }

   }

}
