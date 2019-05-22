package net.minecraft.client.addon.tco.tiny.blocks.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.tco.tiny.Utils.DarkItem;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockDarkEnergyControler;
import net.minecraft.client.addon.tco.tiny.blocks.containers.ContainerDarkController;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiDarkController extends GuiContainer {

   TileEntityBlockDarkEnergyControler tileEntity;
   public DarkItem theDarkItemSlot;


   public GuiDarkController(InventoryPlayer inventory, TileEntityBlockDarkEnergyControler tileEntity) {
      super(new ContainerDarkController(inventory, tileEntity));
      this.tileEntity = tileEntity;
   }

   protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
      super.mc.renderEngine.bindTexture("/mods/tco/gui/darkcontroller.png");
      int b = (super.width - super.xSize) / 2;
      int k = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(b, k, 0, 0, super.xSize, super.ySize + 50);
   }

   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
      super.fontRenderer.drawString(String.valueOf(this.tileEntity.darkEnergy), 73, 15, 4210752);
   }

   public void drawScreen(int par1, int par2, float par3) {
      this.drawDefaultBackground();
      int k = super.guiLeft;
      int l = super.guiTop;
      this.drawGuiContainerBackgroundLayer(par3, par1, par2);
      GL11.glDisable('\u803a');
      RenderHelper.disableStandardItemLighting();
      GL11.glDisable(2896);
      GL11.glDisable(2929);
      RenderHelper.enableGUIStandardItemLighting();
      GL11.glPushMatrix();
      GL11.glTranslatef((float)k, (float)l, 0.0F);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable('\u803a');
      this.theDarkItemSlot = null;
      short short1 = 240;
      short short2 = 240;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)short1 / 1.0F, (float)short2 / 1.0F);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      if(this.tileEntity.itemstacks != null && this.tileEntity.itemstacks.size() > 0) {
         super.fontRenderer.drawString("Онлайн", 40, 27, 4210752);
      } else {
         super.fontRenderer.drawString("Оффлайн", 40, 27, 4210752);
      }

      for(int j1 = 0; j1 < super.inventorySlots.inventorySlots.size(); ++j1) {
         if(this.tileEntity.itemstacks != null && j1 < this.tileEntity.itemstacks.size()) {
            DarkItem di = (DarkItem)this.tileEntity.itemstacks.get(j1);
            di.slot = (Slot)super.inventorySlots.inventorySlots.get(j1);
            this.drawItemStack(di.itemstack, di.slot.xDisplayPosition, di.slot.yDisplayPosition, "", String.valueOf(di.count));
            if(this.isMouseOverSlot(di.slot, par1, par2)) {
               this.theDarkItemSlot = di;
            }
         }
      }

      GL11.glDisable(2896);
      this.drawGuiContainerForegroundLayer(par1, par2);
      GL11.glEnable(2896);
      GL11.glPopMatrix();
      if(this.theDarkItemSlot != null && this.theDarkItemSlot.getHasStack()) {
         this.drawItemStackTooltip(this.theDarkItemSlot.itemstack, par1, par2);
      }

      GL11.glEnable(2896);
      GL11.glEnable(2929);
      RenderHelper.enableStandardItemLighting();
   }

   private void drawItemStack(ItemStack par1ItemStack, int par2, int par3, String par4Str, String count) {
      GL11.glTranslatef(0.0F, 0.0F, 32.0F);
      super.zLevel = 200.0F;
      GuiContainer.itemRenderer.zLevel = 200.0F;
      FontRenderer font = null;
      if(par1ItemStack != null) {
         font = par1ItemStack.getItem().getFontRenderer(par1ItemStack);
      }

      if(font == null) {
         font = super.fontRenderer;
      }

      GuiContainer.itemRenderer.renderItemAndEffectIntoGUI(font, super.mc.renderEngine, par1ItemStack, par2, par3);
      if(par1ItemStack != null) {
         this.RenderCount(font, count, par2, par3);
      }

      super.zLevel = 0.0F;
      GuiContainer.itemRenderer.zLevel = 0.0F;
   }

   private void RenderCount(FontRenderer font, String str, int par4, int par5) {
      GL11.glDisable(2896);
      par4 -= font.getStringWidth(str);
      GL11.glPushMatrix();
      float f = 0.77777F;
      GL11.glScalef(f, f, f);
      font.drawStringWithShadow(str, (int)((float)par4 * 1.2959F + (float)str.length()), par5 + 19, 16777215);
      GL11.glPopMatrix();
      GL11.glEnable(2896);
   }

   private boolean isMouseOverSlot(Slot par1Slot, int par2, int par3) {
      return this.isPointInRegion(par1Slot.xDisplayPosition, par1Slot.yDisplayPosition, 16, 16, par2, par3);
   }
}
